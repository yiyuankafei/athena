package com.athena.common.test.menghuan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Cuixinlang1 {
    /**
     * 怪物数量
     */
    private static final Integer NPC_NUMBER = 1;

    /**
     * 怪物血量区间-最低500
     */
    private  static final Integer HP_MIN = 2100;

    /**
     * 怪物血量区间-最高10000
     */
    private  static final Integer HP_MAX = 10000;

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
            int result = 0;
            //每次设置好怪物血量，循环十万次，统计收益
            for (int j = 0; j < 10000; j ++ ) {
                //回合开始，初始怪物X只，血量为i
                List<NPC> npcList = generateNpc(NPC_NUMBER, i);
                //无波回合数
                Integer roundNormal = executeNormal(npcList, NORMAL_ATK);
                //有波回合数
                npcList = generateNpc(NPC_NUMBER, i);
                Integer roundFloat = executeFloat(npcList, NORMAL_ATK);
                //计算收益
                result += roundNormal > roundFloat ? 1 : (roundNormal == roundFloat ? 0 : -1);
            }
            float resultAvg = (float)result / 10000;
            resultMap.put(i, resultAvg);
        }

        int a = 0;
        int b = 0;
        int c = 0;
        Set<Integer> integers = resultMap.keySet();
        for (Integer i : integers) {
            if (resultMap.get(i) > 0) {
                a ++;
            } else if (resultMap.get(i) < 0) {
                b ++;
            } else {
                c ++;
            }
        }

        System.out.println("==============");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println("==============");


        CategoryDataset dataset = JFreeChat.createDataset(resultMap);
        // 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
        JFreeChart freeChart = JFreeChat.createChart(dataset);
        // 步骤3：将JFreeChart对象输出到文件，Servlet输出流等
        JFreeChat.saveAsFile(freeChart, "E:\\line.jpg", 1200, 800);
    }


    /**
     * 正常结算
     */
    private static Integer executeNormal(List<NPC> npcList, Integer normalAtk) {
        int roundCount = 0;
        //只要怪没死完，就继续下回合
        while (!CollectionUtils.isEmpty(npcList.stream().filter(NPC::isAlived).collect(Collectors.toList()))) {
            roundCount ++;
            //只有3只怪，不需要考虑攻击目标，所有存活对象依次输出
            List<NPC> list = npcList.stream().filter(NPC::isAlived).collect(Collectors.toList());
            list.forEach(item -> {
                item.setHp(item.getHp() - normalAtk);
            });
        }
        return roundCount;
    }

    /**
     * 无催心浪，伤害上下浮动50%
     */
    private static Integer executeFloat(List<NPC> npcList, Integer normalAtk) {
        int roundCount = 0;
        Random random = new Random();
        //只要怪没死完，就继续下回合
        while (!CollectionUtils.isEmpty(npcList.stream().filter(NPC::isAlived).collect(Collectors.toList()))) {
            roundCount ++;
            //只有3只怪，不需要考虑攻击目标，所有存活对象依次输出
            List<NPC> list = npcList.stream().filter(NPC::isAlived).collect(Collectors.toList());
            list.forEach(item -> {
                //random.nextInt()生成0-100随机整数，+50即高级法波范围50-150
                Integer floatAtk = random.nextInt(101) + 50;
                Integer realAtk = Math.round(normalAtk * ((float)floatAtk/100));
                item.setHp(item.getHp() - realAtk);
            });
        }
        return roundCount;
    }


    /**
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
}
