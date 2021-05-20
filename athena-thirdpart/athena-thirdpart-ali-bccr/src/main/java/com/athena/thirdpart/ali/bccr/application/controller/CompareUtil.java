
package com.athena.thirdpart.ali.bccr.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompareUtil {

    public static<T extends  YoukuComparable, E extends YoukuComparable>  CompareResult<T, E> compare(List<T> requestData, List<E> currentData) {
        CompareResult<T, E> result = new CompareResult<>();
        List<T> addList = new ArrayList<>();
        List<T> modifyList = new ArrayList<>();
        List<E> deleteList = new ArrayList<>();

        Map<String, T> map = requestData.stream().collect(Collectors.toMap(item -> item.getYoukuUniqueKey(), item -> item));

        for (E item : currentData) {
            if (map.get(item.getYoukuUniqueKey()) == null) {
                deleteList.add(item);
            } else {
                T requestItem = map.get(item.getYoukuUniqueKey());
                requestItem.setCount(requestItem.getCount() + 1);
                if (!item.getYoukuHash().equals(requestItem.getYoukuHash())) {
                    modifyList.add(requestItem);
                }
            }
        }
        addList = map.values().stream().filter(item -> item.getCount() == 1).collect(Collectors.toList());
        result.setAddResult(addList);
        result.setModifyResult(modifyList);
        result.setDeleteResult(deleteList);
        return result;


       /*requestData.stream().collect(Collectors.toMap(item -> item.toString(), item ->item))
               .entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().map))
        for (T item2 : list2) {
            String unique = item2.toString();
            if ()
        }*/

        /*for(T item : list1) {
            if (!list2.contains(item)) {
                addList.add(item);
            } else if (){

            }
        }*/

    }

}
