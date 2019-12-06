package com.athena.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 
 * 
 * 拼音工具类
 */
public class PinYinUtil {

    /**
     * 
     * 将汉字转换为全拼
     */
    public static String getPinYin(String src){
        char[] hz = null;
        hz = src.toCharArray();
        String[] py = new String[hz.length];
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        String pys = "";
        int len = hz.length;

        try {
            for (int i = 0; i < len ; i++ ) {
                if (Character.toString(hz[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    py = PinyinHelper.toHanyuPinyinStringArray(hz[i],format);
                    pys += py[0];
                } else {
                    pys += Character.toString(hz[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pys;
    }
}
