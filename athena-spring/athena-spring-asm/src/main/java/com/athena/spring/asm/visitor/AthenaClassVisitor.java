package com.athena.spring.asm.visitor;

import com.athena.spring.asm.metadata.AthenaAnnotationMetaData;
import com.athena.spring.asm.metadata.AthenaClassMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.SpringAsmInfo;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AthenaClassVisitor extends ClassVisitor {

    private AthenaClassMetaData metadata = new AthenaClassMetaData();

    private List<AthenaAnnotationMetaData> annotationMeteDatas = new ArrayList<>();

    public AthenaClassVisitor() {
        super(SpringAsmInfo.ASM_VERSION);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        metadata.setClassName(name);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return new AthenaAnnotationVisitor(metadata.getClassName(), descriptor, annotationMeteDatas);
    }

    @Override
    public void visitEnd() {
        metadata.setAnnotations(annotationMeteDatas);
    }


    public AthenaClassMetaData getMetadata() {
        return metadata;
    }



}
