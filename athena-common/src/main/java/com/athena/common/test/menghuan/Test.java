package com.athena.common.test.menghuan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Test {

    /**
     * 怪物数量
     */
    private static final Integer NPC_NUMBER = 10;

    /**
     * 怪物基础血量区间-最低3000
     */
    private static final Integer HP_MIN = 3000;

    /**
     * 怪物基础血量区间-最高10000
     */
    private static final Integer HP_MAX = 10000;

    /**
     * 须弥数量
     */
    private static final Integer MEMBER_COUNT = 5;

    /**
     * 须弥基础伤害-2000
     */
    private static final Integer BASE_ATK = 2000;

    /**
     * 分灵补偿系数
     */
    private static final Float compensate = 0.15f;



    /**
     * 初始化怪物列表
     */
    private static List<NPC> generateNpc(int number, int baseHp) {
        Random random = new Random();
        List<NPC> npcList = new ArrayList<>();
        //主怪为基础血量3倍，并设置速度最快，另有两只怪血量为基础血量的2倍
        npcList.add(new NPC(baseHp * 3, 1));
        npcList.add(new NPC(baseHp * 2));
        npcList.add(new NPC(baseHp * 2));
        //剩余7只怪，血量在基础血量上下浮动30%
        for (int i = 0; i < number - 3; i++) {
            //生成60以内随机整数，加70即为随机血量范围（70%——130%）
            Integer randomHp = Math.round(baseHp * ((float)(random.nextInt(130-70 + 1) + 70)/100));
            npcList.add(new NPC(randomHp));
        }
        //打乱顺序
        Collections.shuffle(npcList);
        //将主怪移到第一位
        npcList = npcList.stream().sorted(Comparator.comparing(NPC::getSpeed)).collect(Collectors.toList());
        return npcList;
    }

    /**
     *
     * 模拟实际战斗场景，获取对应点阵序列
     * @param isFloat 是否有高级法术波动
     * @param limit 波动下限（上限固定150）
     * @return key:怪物血量  value:战斗耗时
     */
    public static LinkedHashMap<Integer, Float> generateDataMap(boolean isFloat, Integer limit) {
        LinkedHashMap<Integer, Float> resultMap = new LinkedHashMap<>();
        //怪物血量从3000开始，每次递增100，直到10000
        for (int i = HP_MIN; i <= HP_MAX; i = i + 100) {
            //血量为i，十万次战斗结束总耗时
            Integer totalTime = 0;
            //每次设置好怪物血量，循环十万次，统计总耗时
            for (int j = 0; j < 10000; j ++ ) {
                //初始化怪物列表
                List<NPC> npcList = generateNpc(NPC_NUMBER, i);
                //模拟战斗结束，返回时间
                Integer time = mock(npcList, isFloat, limit, BASE_ATK, MEMBER_COUNT);
                totalTime = totalTime + time;
            }
            float timeAvg = (float)totalTime / 10000;
            resultMap.put(i, timeAvg);
        }
        return resultMap;
    }

    /**
     * 模拟单场战斗
     */
    private static Integer mock(List<NPC> npcList, boolean isFloat, Integer limit, Integer baseAtk, Integer memberCount) {
        Random random = new Random();
        Integer time = 0;
        //只要怪没死完，就继续下回合
        while (!CollectionUtils.isEmpty(npcList.stream().filter(NPC::isAlived).collect(Collectors.toList()))) {
            //每回合开头耗时3秒
            time = time + 2;
            // 须弥依次出手
            for (int i = 0; i < memberCount; i ++) {
                List<NPC> list = npcList.stream().filter(NPC::isAlived).collect(Collectors.toList());
                //怪没死完，继续结算当前须弥伤害
                if (!CollectionUtils.isEmpty(list)) {
                    //单次出手，耗时2秒
                    time = time + 2;
                    //大于3只怪，先随机选取1只，然后顺序从前选取2只,由于主怪排在第一位，必然会命中主怪
                    if (list.size() > 3) {
                        Integer randomTarget = random.nextInt(list.size());
                        list.get(randomTarget).setHp(list.get(randomTarget).getHp() - calculAtk(isFloat, limit, baseAtk, list.size()));
                        list.remove(randomTarget);
                        list.get(0).setHp(list.get(0).getHp() - calculAtk(isFloat, limit, baseAtk, list.size()));
                        list.get(1).setHp(list.get(1).getHp() - calculAtk(isFloat, limit, baseAtk, list.size()));
                    } else {
                        //小于等于3只怪，依次扣除血量
                        list.forEach(item -> {
                            item.setHp(item.getHp() - calculAtk(isFloat, limit, baseAtk, list.size()));
                        });
                    }
                }
            }
            //所有须弥出手结束，存活怪物每只出手耗时2秒
            List<NPC> aliveList = npcList.stream().filter(NPC::isAlived).collect(Collectors.toList());
            time = time + aliveList.size() * 2;
        }
        return time;
    }

    /**
     *
     * 计算真实伤害
     */
    private static Integer calculAtk(boolean isFloat, Integer limit, Integer baseAtk, Integer aliveCount) {
        //如果存活怪物少于3只，根据分灵系数提高基础伤害
        if (aliveCount < 3) {
            baseAtk = Math.round(baseAtk * (1 + compensate * (3 - aliveCount)));
        }
        //无法波，返回基础伤害
        if (!isFloat) {
            return baseAtk;
        }
        //有法波，真实伤害=基础伤害*波动比率
        Random random = new Random();
        //催心浪层数不同，表现为下限不同，获取下限-150内的随机整数
        Integer coefficient  = random.nextInt(150 - limit + 1) + limit;
        Integer realAtk = Math.round(baseAtk * ((float)coefficient/100));
        return realAtk;
    }


    @Data
    public static class NPC {

        /**
         * 血量
         */
        private Integer hp;

        /**
         * 速度，数字越小，速度越快，默认为10
         */
        private Integer speed = 10;

        public NPC(Integer hp) {
            this.hp = hp;
        }

        public NPC(Integer hp, Integer speed) {
            this.hp = hp;
            this.speed = speed;
        }

        public boolean isAlived () {
            return hp > 0;
        }
    }

    @Data
    @AllArgsConstructor
    public static class SingleResult {

        /**
         * 结束战斗回合数
         */
        private Integer roundCount;

        /**
         * 出手数
         */
        private Integer attackCount;

    }

    public static void main(String[] args) throws Exception {
        LinkedHashMap<Integer, Float> baseMap = generateDataMap(false, null);
        Map<String , LinkedHashMap<Integer, Float>> result = new HashMap<>();
        result.put("0层", generateDataMap(true, 50));
        result.put("1层", generateDataMap(true, 54));
        result.put("2层", generateDataMap(true, 55));
        result.put("3层", generateDataMap(true, 56));
        result.put("4层", generateDataMap(true, 58));
        result.put("5层", generateDataMap(true, 60));

        Set<Integer> baseKey = baseMap.keySet();
        baseKey.forEach(item -> {
            Float baseValue = baseMap.get(item);
            result.keySet().forEach(i -> {
                LinkedHashMap<Integer, Float> map = result.get(i);
                map.put(item, baseValue - map.get(item));
            });
        });

        JFreeChat.exportResult(result);
    }

}
