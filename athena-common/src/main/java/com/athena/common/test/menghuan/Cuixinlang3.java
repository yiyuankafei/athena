package com.athena.common.test.menghuan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
public class Cuixinlang3 {

    /**
     * 怪物数量
     */
    private static final Integer NPC_NUMBER = 10;

    /**
     * 怪物血量区间-最低500
     */
    private  static final Integer HP_MIN = 3000;

    /**
     * 怪物血量区间-最高10000
     */
    private  static final Integer HP_MAX = 20000;

    /**
     * 须弥无催心浪伤害 2000
     */
    private  static final Integer NORMAL_ATK = 2000;

    /**
     * 须弥数量
     */
    private  static final Integer MEMBER_COUNT = 5;


    public static void main(String[] args) throws Exception {
        LinkedHashMap<Integer, Float> resultMap = new LinkedHashMap<>();
        //怪物血量从500开始，每次递增100，直到10000
        for (int i = HP_MIN; i <= HP_MAX; i = i + 100) {
            //血量为i，总收益（正收益计1，负收益计-1，零收益计0）,十万次结束统计平均收益
            float result = 0;
            //每次设置好怪物血量，循环十万次，统计收益
            for (int j = 0; j < 10000; j ++ ) {
                //回合开始，初始怪物X只，血量为i
                List<NPC> npcList = generateNpc(NPC_NUMBER, i);
                //无波回合数
                SingleResult roundNormal = executeNormal(npcList, NORMAL_ATK, MEMBER_COUNT);
                //有波回合数
                npcList = generateNpc(NPC_NUMBER, i);
                SingleResult roundFloat = executeFloat(npcList, NORMAL_ATK, MEMBER_COUNT);
                //计算收益
                if (roundNormal.getRoundCount() > roundFloat.getRoundCount()) {
                    result = result + 1;
                } else if (roundNormal.getRoundCount() < roundFloat.getRoundCount()) {
                    result = result - 1;
                } else {
                    if (roundNormal.getAttackCount() > roundFloat.getAttackCount()) {
                        result = result + 0.5f;
                    } else if (roundNormal.getAttackCount() < roundFloat.getAttackCount()) {
                        result = result - 0.5f;
                    }
                }
            }
            float resultAvg = result / 10000;
            resultMap.put(i, resultAvg);
        }

        for (Integer item : resultMap.keySet()) {
            float v = resultMap.get(item);
            if (v == 0) {
                System.out.println("0收益：" + item);
            }
            if (v > 0) {
                System.out.println("正收益:" + item);
            }
        }
        CategoryDataset dataset = JFreeChat.createDataset(resultMap);
        // 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
        JFreeChart freeChart = JFreeChat.createChart(dataset);
        // 步骤3：将JFreeChart对象输出到文件，Servlet输出流等
        JFreeChat.saveAsFile(freeChart, "E:\\line.jpg", 1200, 800);
    }


    /**
     * 正常结算
     */
    private static SingleResult executeNormal(List<NPC> npcList, Integer normalAtk, Integer memberCount) {
        Random random = new Random();
        int roundCount = 0;
        int attackCount = 0;
        //只要怪没死完，就继续下回合
        while (!CollectionUtils.isEmpty(npcList.stream().filter(NPC::isAlived).collect(Collectors.toList()))) {
            roundCount ++;
            //须弥依次出手
            for (int i = 0; i < memberCount; i ++) {
                List<NPC> list = npcList.stream().filter(NPC::isAlived).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(list)) {
                    attackCount ++;
                    //大于3只怪，先随机选取1只，然后顺序从前选取2只
                    if (list.size() > 3) {
                        Integer randomTarget = random.nextInt(list.size());
                        list.get(randomTarget).setHp(list.get(randomTarget).getHp() - normalAtk);
                        list.remove(randomTarget);
                        list.get(0).setHp(list.get(0).getHp() - normalAtk);
                        list.get(1).setHp(list.get(1).getHp() - normalAtk);
                    } else {
                        //小于等于3只怪，依次扣除血量
                        list.forEach(item -> {
                            item.setHp(item.getHp() - normalAtk);
                        });
                    }
                }
            }
        }
        SingleResult result = new SingleResult(roundCount, attackCount);
        return result;
    }

    /**
     * 无催心浪，伤害上下浮动50%
     */
    private static SingleResult executeFloat(List<NPC> npcList, Integer normalAtk, Integer memberCount) {
        Random random = new Random();
        int roundCount = 0;
        int attackCount = 0;
        //只要怪没死完，就继续下回合
        while (!CollectionUtils.isEmpty(npcList.stream().filter(NPC::isAlived).collect(Collectors.toList()))) {
            roundCount ++;
            //须弥依次出手
            for (int i = 0; i < memberCount; i ++) {
                List<NPC> list = npcList.stream().filter(NPC::isAlived).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(list)) {
                    attackCount ++;
                    //大于3只怪，先随机选取1只，然后顺序从前选取2只
                    if (list.size() > 3) {
                        Integer randomTarget = random.nextInt(list.size());
                        list.get(randomTarget).setHp(list.get(randomTarget).getHp() - getRealAtk(random, NORMAL_ATK));
                        list.remove(randomTarget);
                        list.get(0).setHp(list.get(0).getHp() - getRealAtk(random, NORMAL_ATK));
                        list.get(1).setHp(list.get(1).getHp() - getRealAtk(random, NORMAL_ATK));
                    } else {
                        //小于等于3只怪，依次扣除血量
                        list.forEach(item -> {
                            item.setHp(item.getHp() - getRealAtk(random, NORMAL_ATK));
                        });
                    }
                }
            }
        }
        SingleResult result = new SingleResult(roundCount, attackCount);
        return result;
    }

    private static Integer getRealAtk (Random random, Integer normalAtk) {
        Integer floatAtk = random.nextInt(150 - 60 + 1) + 60;
        Integer realAtk = Math.round(normalAtk * ((float)floatAtk/100));
        return realAtk;
    }


    /**
     *
     * 生成指定数量NPC
     */
    private static List<NPC> generateNpc(int number, int hp) {
        List<NPC> npcs = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            NPC npc = new NPC(hp);
            npcs.add(npc);
        }
        return npcs;
    }


    @Data
    @AllArgsConstructor
    public static class NPC {

        private Integer hp;

        public boolean isAlived () {
            return hp > 0 ? true : false;
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

}
