package easy;

import java.util.Arrays;

/**
 * 题目链接:
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/
 * 
 * 解题思路: 双指针
 */
public class RemoveDuplicatesFromSortedArray26 {
    public static void main(String[] args) {
        int[] nums = { 1, 1, 2 };
        int result = removeDuplicates(nums);
        System.out.println(result + ", nums = " +
                Arrays.toString(Arrays.copyOfRange(nums, 0, result)));

        int[] nums2 = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        int result2 = removeDuplicates(nums2);
        System.out.println(result2 + ", nums = " + Arrays.toString(Arrays.copyOfRange(nums2, 0, result2)));
    }

    public static  int removeDuplicates(int[] nums) {
        int n = nums.length; // 数组的长度
        if (n == 0) {
            return 0; // 如果数组为空，则返回0
        }

        int fast = 1, slow = 1; // 双指针，fast和slow分别指向下一个要检查的元素和下一个要填充的位置
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) { // 如果当前元素与前一个元素不相等
                nums[slow] = nums[fast]; // 将当前元素复制到slow指针位置
                ++slow; // slow指针向后移动一位
            }
            ++fast; // fast指针向后移动一位
        }
        return slow; // 返回新数组的长度，即有效元素的数量
    }
}