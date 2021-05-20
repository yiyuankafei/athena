package com.athena.thirdpart.ali.bccr.application.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        List<Works> worksList = generateWorks();
        List<WorksPo> poList = generatePo();

        CompareResult<Works, WorksPo> compare = CompareUtil.compare(worksList, poList);
        System.out.println(compare.getAddResult());
        System.out.println(compare.getModifyResult());
        System.out.println(compare.getDeleteResult());

    }

    private static List<WorksPo> generatePo() {
        WorksPo po1 = new WorksPo();
        po1.setId(1l);
        po1.setThirdId("99999");
        po1.setWorksName("火凤燎原");
        po1.setAuthor("E");

        WorksPo po2 = new WorksPo();
        po2.setId(1l);
        po2.setThirdId("11111");
        po2.setWorksName("编程思想");
        po2.setAuthor("AAAAA");

        WorksPo po3 = new WorksPo();
        po3.setId(1l);
        po3.setThirdId("22222");
        po3.setWorksName("设计模式");
        po3.setAuthor("B");

        WorksPo po4 = new WorksPo();
        po4.setId(1l);
        po4.setThirdId("33333");
        po4.setWorksName("redis");
        po4.setAuthor("A");

        WorksPo po5 = new WorksPo();
        po5.setId(1l);
        po5.setThirdId("55555");
        po5.setWorksName("并发编程");
        po5.setAuthor("FFF");

        List<WorksPo> worksPos = new ArrayList<>();
        worksPos.add(po1);
        worksPos.add(po2);
        worksPos.add(po3);
        worksPos.add(po4);
        worksPos.add(po5);
        return worksPos;
    }

    private static List<Works> generateWorks() {
        Works works1 = new Works();
        works1.setWorksId("11111");
        works1.setName("编程思想");
        works1.setAuthor("A");
        works1.setPrice(new BigDecimal("100"));

        Works works2 = new Works();
        works2.setWorksId("22222");
        works2.setName("设计模式");
        works2.setAuthor("B");
        works2.setPrice(new BigDecimal("101"));

        Works works3 = new Works();
        works3.setWorksId("33333");
        works3.setName("redis");
        works3.setAuthor("A");
        works3.setPrice(new BigDecimal("103"));

        Works works4 = new Works();
        works4.setWorksId("44444");
        works4.setName("nginx");
        works4.setAuthor("C");
        works4.setPrice(new BigDecimal("100"));

        Works works5 = new Works();
        works5.setWorksId("55555");
        works5.setName("并发编程");
        works5.setAuthor("D");
        works5.setPrice(new BigDecimal("100"));

        List<Works> works = new ArrayList<>();
        works.add(works1);
        works.add(works2);
        works.add(works3);
        works.add(works4);
        works.add(works5);
        return works;
    }


}
