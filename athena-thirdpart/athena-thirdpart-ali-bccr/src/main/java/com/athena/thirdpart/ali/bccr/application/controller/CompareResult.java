package com.athena.thirdpart.ali.bccr.application.controller;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompareResult<T, E> {

    private List<T> addResult = new ArrayList<>();

    private List<T> modifyResult = new ArrayList<>();

    private List<E> deleteResult = new ArrayList<>();

}
