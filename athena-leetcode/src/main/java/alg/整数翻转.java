package alg;

public class 整数翻转 {

    public static void main(String[] args) {
        int a = 1534236469;
        System.out.println(reverse(a));
    }

    public static int reverse(int x) {
        int max = 2147483647;
        int balance = 2147483647;
        boolean zs = x > 0;
        if (!zs) {
            x = -x;
        }
        int length = String.valueOf(x).length();
        int[] intArray = new int[length];
        int index = 0;
        while (x > 0) {
            intArray[index] = x % 10;
            x = x / 10;
            index = index + 1;
        }

        int result = 0;
        for (int i = 0; i < intArray.length; i++) {
            int temp = intArray[i];
            for (int j = 0; j < intArray.length - 1 - i; j++) {
                if (temp > (max / 10)) return 0;
                temp = temp * 10;
            }
            if (temp > balance) return 0;
            result = result + temp;
            balance = balance - temp;
        }
        return zs ? result : -result;
    }
}
