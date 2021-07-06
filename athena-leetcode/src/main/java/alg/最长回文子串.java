package alg;

public class 最长回文子串 {

    public static void main(String[] args) {
        String origin = "abaabaabaabaabaeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeea";
        System.out.println("===result===");
        System.out.println(longestPalindrome(origin));
        System.out.println("===result===");
    }

    public static String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        String target = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            char start = s.charAt(i);
            int lastIndex = s.lastIndexOf(start);
            while (lastIndex > i) {
                String currentString = s.substring(i, lastIndex + 1);
                if (currentString.length() < target.length()) {
                    break;
                }
                if (check(currentString)) {
                    if (currentString.length() > target.length()) {
                        target = currentString;
                    }
                    break;
                } else {
                    String nextString = s.substring(0, lastIndex);
                    lastIndex = nextString.lastIndexOf(start);
                }
            }
        }

        return target;
    }

    private static boolean check(String currentString) {
        int length = currentString.length();
        int checkCount = length / 2;
        for (int i = 0; i < checkCount; i++) {
            if (currentString.charAt(i) != currentString.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /*public void mock(String s) {
        遍历字符串中的每个字符
                从字符串末尾依次往前找到与其相同的字符
                    则中间的子串，有可能是回文串
                        判断若是
                            是否大于最大长度，是则替换后结束，不是则结束
                        若不是
                            再往前找相同字符串
    }*/

}
