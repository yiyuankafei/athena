package com.yiyuankafei.athena.data.es.util;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PinYinUtil {
	
	@SuppressWarnings("serial")
	private static Map<String,String> map = new HashMap<String,String>(){
	{
		this.put("栃", "li");
	}};
	
    /**
     * 
     * 将汉字转换为全拼
     */
    public static String getPinYin(String src){
        char[] hz = null;
        log.info(src);
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
                	log.info(Character.toString(hz[i]));
                	if (map.containsKey(Character.toString(hz[i]))) {
                		pys += map.get(Character.toString(hz[i]));
                	} else {
                        py = PinyinHelper.toHanyuPinyinStringArray(hz[i],format);
                        log.info(String.valueOf(py.length));
                        pys += py[0];
                	}
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
