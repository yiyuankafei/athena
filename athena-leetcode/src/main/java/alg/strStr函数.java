package alg;

/**
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
 * 如果不存在，则返回  -1 。
 */
public class strStr函数 {
    public static void main(String[] args) {
        String haystack = "abc";
        String needle = "c";
        System.out.println(strStr(haystack, needle));
    }

    public static int strStr(String haystack, String needle) {
        // return haystack.indexOf(needle);
        if (needle.length() == 0 || haystack.equals(needle)) return 0;
        if (needle.length() > haystack.length()) return -1;
        char c = needle.charAt(0);
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            System.out.println(haystack.charAt(i));
            if (haystack.charAt(i) == c) {
                String temp = haystack.substring(i, i + needle.length());
                if (temp.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
