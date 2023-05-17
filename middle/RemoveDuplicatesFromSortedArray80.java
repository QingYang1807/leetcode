package easy;

import java.util.Arrays;

/**
 * 题目链接:
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/?envType=study-plan-v2&id=top-interview-150
 * 
 * 解题思路: 双指针
 */
public class RemoveDuplicatesFromSortedArray80 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int result = solution.removeDuplicates(nums);
        System.out.println(result + ", nums = " + Arrays.toString(Arrays.copyOfRange(nums, 0, result)));

        int[] nums2 = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };
        int result2 = solution.removeDuplicates(nums2);
        System.out.println(result + ", nums2 = " + Arrays.toString(Arrays.copyOfRange(nums2, 0, result2)));

    }
}

class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length; // 数组的长度
        if (n <= 2) {
            return n; // 如果数组长度小于等于2，无需删除，直接返回原数组长度
        }

        int slow = 2, fast = 2; // 双指针，slow和fast分别指向下一个要填充的位置和遍历的元素位置
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) { // 如果当前元素与slow指针前两个位置的元素不相等
                nums[slow] = nums[fast]; // 将当前元素复制到slow指针位置
                ++slow; // slow指针向后移动一位
            }
            ++fast; // fast指针向后移动一位
        }
        return slow; // 返回新数组的长度，即有效元素的数量
    }
}