package alg;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */
public class 两数之和 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,7,2,9,3};
        int target = 9;
        int[] ints = twoSum(nums, target);
        System.out.println(JSON.toJSONString(ints));
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> exceptMap = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i ++) {
            int current = nums[i];
            if (exceptMap.get(current) != null) {
                result[0] = i;
                result[1] = exceptMap.get(current);
                return result;
            } else {
                exceptMap.put(target - current, i);
            }
        }
        return result;
    }
}
