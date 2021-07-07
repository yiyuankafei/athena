package com.athena.spring.asm.visitor;

import com.athena.spring.asm.metadata.AthenaAnnotationMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;

import java.util.List;

@Slf4j
public class AthenaAnnotationVisitor extends AnnotationVisitor {

    private AthenaAnnotationMetaData annotationMeteData = new AthenaAnnotationMetaData();
    private List<AthenaAnnotationMetaData> annotations;

    public AthenaAnnotationVisitor(String onClassName, String descriptor, List<AthenaAnnotationMetaData> annotations) {
        super(SpringAsmInfo.ASM_VERSION);
        annotationMeteData.setOnClassName(onClassName);
        annotationMeteData.setAnnotationClassName(descriptor);
        this.annotations = annotations;
    }

    @Override
    public void visitEnd() {
        annotations.add(annotationMeteData);
    }

}
