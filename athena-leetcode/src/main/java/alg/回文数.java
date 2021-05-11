package alg;

/**
 * 反转数字的一半，比较是否相等
 */
public class 回文数 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(110101));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x > 0 && x % 10 == 0) {
            return true;
        } else {
            int rev = 0;
            while (x > rev) {
                rev = rev * 10 + x % 10;
                x = x / 10;
            }
            return x == rev || x == rev / 10;
        }
    }

}
