package alg;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class 删除有序数组重复项 {
    public static void main(String[] args) {
        int[] a = {0,1,2,2,3,4,4,5,5,5,6};
        int i = removeDuplicates(a);
        System.out.println(i);
    }

    /**
     *
     * 从第一个开始遍历，一直遍历到倒数第二个，只要符合要求，就依次往数组最前面填充，不符合要求跳过，维护一个填充的位置newIndex
     * 符合要求指当前元素不等于后一个元素
     * 按此规则原数组最后一个元素在结束时直接填入
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        int newIndex = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[newIndex++] = nums[i];
            }
        }
        nums[newIndex++] = nums[nums.length -1];
        return newIndex;
    }
}
