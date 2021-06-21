package alg;

/**
 * https://leetcode-cn.com/problems/regular-expression-matching/
 */
public class 正则 {
    /**
     * "mississippi"
     * "mis*is*ip*."
     * @param args
     *
     *
     * "mississippi"
     * "mis*is*p*."
     *
     *
     *
     * "aaa"
     * "ab*a*c*a"
     */

    public static void main(String[] args) {
        System.out.println(isMatch("aaa", "ab*a*c*a"));
    }


    public static boolean isMatch(String s, String p) {
        //return s.matches(p);
        char prefix = 0;
        if (p.equals(s)) {
            return true;
        }
        if (p.length() == 1) {
            if (p.equals(".") && s.length() == 1) {
                return true;
            } else {
                return false;
            }
        }
        //p.length >= 2
        while (p.length() >= 2 && s.length() != 0) {
            System.out.println("s=:" + s);
            System.out.println("p=:" + p);
            if (s.length() == 0) {
                return false;
            }
            if (p.charAt(1) == '*') {
                if (p.charAt(0) == '.') {
                    if (p.length() > 2) {
                        if (p.charAt(p.length() -1) != '*') {
                            return false;
                        }
                        for (int q = 0; q + 2 < p.length(); q = q + 2) {
                            if (p.charAt(q + 1) != '*') {
                                return false;
                            }
                        }
                        return true;
                    } else {
                        return true;
                    }
                }
                System.out.println("****************");
                int same = 0;
                for (int j = 0; j < s.length(); j++) {
                    System.out.println("s.charat:" + j + "---" + s.charAt(j));
                    System.out.println("p,chatat:" + 0 + "---" + p.charAt(0));
                    prefix = p.charAt(0);
                    if (s.charAt(j) == p.charAt(0)) {
                        same++;
                    } else {
                        break;
                    }
                    System.out.println(same);
                }
                if (same > 0) {
                    s = s.substring(same);
                }
                p = p.substring(2);
                continue;
            } else {
                if (p.charAt(0) == '.') {
                    s = s.substring(1);
                    p = p.substring(1);
                    continue;
                } else {
                    if (s.charAt(0) != p.charAt(0)) {
                        return false;
                    }
                    s = s.substring(1);
                    p = p.substring(1);
                    continue;
                }
            }
        }
        System.out.println("==========");
        System.out.println(s);
        System.out.println(p);
        if (s.equals("")) {
            System.out.println("aaaaaaaaaaaaaaaaaaa");
            while (p.contains("*")) {
                for (int k = 0; k < p.length(); k ++) {
                    System.out.println(k);
                    if (p.charAt(k) == '*') {
                        System.out.println("-----------------");
                        p = p.substring(0, k - 1) + p.substring(k + 1);
                        System.out.println(p);
                    }
                }
            }
            System.out.println("!!!");
            System.out.println("@@@");
            System.out.println(prefix);
            System.out.println("@@@");
            for (int z = 0; z < p.length(); z ++) {
                System.out.println("~~~");
                System.out.println(prefix);
                System.out.println(p.charAt(z));
                if (prefix != p.charAt(z)) {
                    return false;
                }
            }
            return true;
        }  else {
            if (s.equals(p) || (p.equals(".") && s.length() == 1)) {
                return true;
            } else {
                return false;
            }
        }
    }
}