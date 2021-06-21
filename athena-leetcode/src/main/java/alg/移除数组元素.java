package alg;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class 移除数组元素 {

    public static void main(String[] args) {
        int[] a = {1,2,3,4,3,2,1,6,7,8};
        System.out.println(removeElement(a, 2));

    }

    /**
     * 依次遍历，相等就依次填充到数组前面
     */
    public static int removeElement(int[] nums, int val) {
        int newIndex = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != val) {
                nums[newIndex++] = nums[i];
            }
        }
        return newIndex;
    }

}
