package com.athena.spring.asm.test;

import com.alibaba.fastjson.JSON;
import com.athena.spring.asm.metadata.AthenaClassMetaData;
import com.athena.spring.asm.po.TargetClass1;
import com.athena.spring.asm.visitor.AthenaClassVisitor;
import org.springframework.asm.ClassReader;

public class Test {

    public static void main(String[] args) throws Exception {
        AthenaClassVisitor classVisitor = new AthenaClassVisitor();
        ClassReader classReader = new ClassReader("com.athena.spring.asm.po.TargetClass1");
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_CODE | ClassReader.SKIP_FRAMES);

        AthenaClassMetaData metadata = classVisitor.getMetadata();
        System.out.println(JSON.toJSONString(metadata));

        //使用ASM解析类信息，不会导致类初始化，而反射会将class信息加载到JVM并完成类初始化
        //Class.forName("com.athena.spring.asm.po.TargetClass1");


    }

}
