package com.athena.common.test.jol;

import org.openjdk.jol.info.ClassLayout;

public class SynTest {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        System.out.println(o.hashCode());
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
            System.out.println(o.hashCode());
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

    }

}
