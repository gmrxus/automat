package com.zzrh.automat.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gmrxus on 2018/3/14.
 */

public class ListPageUtil {
    /**
     * 公共分页方法
     *
     * @param list
     * @param total
     * @param rows
     * @param page
     * @return
     */
    public static <T> List<T> ListSplitCommon(List<T> list, int total, int rows, int page) {
        List<T> newList = null;
        total = list.size();
        newList = list.subList(rows * (page - 1), ((rows * page) > total ? total : (rows * page)));
        return newList;
    }

    /**
     * @param list 大集合
     * @param n    分成几页
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int n) {
        List<List<T>> strList = new ArrayList<>();
        if (list == null) return strList;
        int size = list.size();
        int quotient = size / n; // 商数
        int remainder = size % n; // 余数
        int offset = 0; // 偏移量
        int len = quotient > 0 ? n : remainder; // 循环长度
        int start = 0;  // 起始下标
        int end = 0;    // 结束下标
        List<T> tempList = null;
        for (int i = 0; i < len; i++) {
            if (remainder != 0) {
                remainder--;
                offset = 1;
            } else {
                offset = 0;
            }
            end = start + quotient + offset;
            tempList = list.subList(start, end);
            start = end;
            strList.add(tempList);
        }
        return strList;
    }


    /**
     * List切割成固定大小的list,最后一个list为余数
     *
     * @param list   需要切割的list
     * @param volume 标准容量大小
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> sectionList(List<T> list, int volume) {
        //        if (list.volume() > volume) {
//            List<T> ts = list.subList(0, volume);
//            List<T> t = new ArrayList<>();
//            t.addAll(ts);
//            list.removeAll(t);
//            parentList.add(t);
//            sectionList(parentList, list, volume);
//        } else {
//            parentList.add(list);
//        }
//        return parentList;
        ArrayList<List<T>> pList = new ArrayList<>();
        int size = list.size();
        if (size > volume) {
            int ceil = (int) Math.ceil((double) size / volume);
            for (int i = 0; i < ceil; i++) {
                List<T> ts = list.subList(i * volume, (i + 1) * volume < size ? (i + 1) * volume : size);
                pList.add(ts);
            }
        } else {
            pList.add(list);
        }
        return pList;
    }
}
