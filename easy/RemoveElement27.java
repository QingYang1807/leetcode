package easy;

import java.util.Arrays;

/**
 * 题目链接: https://leetcode-cn.com/problems/remove-element/
 * 
 * 解题思路: 双指针
 */
public class RemoveElement27 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = { 3, 2, 2, 3 };
        int result = solution.removeElement(nums, 3);

        System.out.println(result + ", nums = " + Arrays.toString(Arrays.copyOfRange(nums, 0, result)));
    }

}

class Solution {
    public int removeElement(int[] nums, int val) {
        int n = nums.length; // 数组的长度
        int left = 0; // 左指针，指向当前有效元素的位置
        for (int right = 0; right < n; right++) { // 右指针，遍历数组中的每个元素
            if (nums[right] != val) { // 如果当前元素不等于目标值
                nums[left] = nums[right]; // 将当前元素复制到左指针位置
                left++; // 左指针向后移动一位，指向下一个有效元素的位置
            }
        }
        return left; // 返回有效元素的数量
    }
}