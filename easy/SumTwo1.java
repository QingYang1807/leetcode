package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 题目链接：https://leetcode.cn/problems/two-sum/description/
 * 
 * 解题思路：暴力枚举、哈希表
 */
class SumTwo1 {
    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = twoSum1(nums, target);
        System.out.println(Arrays.toString(result));
        

        int[] nums2 = {3,2,4};
        int target2 = 6;
        int[] result2 = twoSum1(nums2, target2);
        System.out.println(Arrays.toString(result2));

        int[] nums3 = {3,3};
        int target3 = 6;
        int[] result3 = twoSum1(nums3, target3);
        System.out.println(Arrays.toString(result3));
    }

    // 暴力枚举
    public static int[] twoSum1(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[0];
    }

    // 哈希表
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}