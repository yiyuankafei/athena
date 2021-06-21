package alg;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入pwwkew  因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * abcdefgazqo
 *
 */
public class 无重复字符最长子串 {

    public static void main(String[] args) {
        String s = "";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.equals("")) return 0;
        int maxLength = 0;
        Set set = new HashSet();
        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i))) {
                maxLength = set.size() > maxLength ? set.size() : maxLength;
                set.clear();
            }
        }
        return maxLength;
    }
}
