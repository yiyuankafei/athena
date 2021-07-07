package com.athena.spring.asm.metadata;

import lombok.Data;

import java.util.List;

@Data
public class AthenaClassMetaData {

    private String className;

    private List<AthenaAnnotationMetaData> annotations;


}
