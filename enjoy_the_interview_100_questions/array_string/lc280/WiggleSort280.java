package enjoy_the_interview_100_questions.array_string.lc280;

import java.util.Arrays;

/**
 * 题目标题：摆动排序
 * 题目链接：https://leetcode.cn/problems/wiggle-sort/?envType=study-plan-v2&envId=premium-algo-100
 * 
 * 题目描述：给你一个的整数数组 nums, 将该数组重新排序后使 nums[0] <= nums[1] >= nums[2] <= nums[3]...
 * 输入数组总是有一个有效的答案。
 * 
 * 输入：nums = [3,5,2,1,6,4]
 * 输出：[3,5,1,6,2,4]
 * 解释：[1,6,2,5,3,4]也是有效的答案
 */
public class WiggleSort280 {
    public static void main(String[] args) {
        int[] nums1 = { 3, 5, 2, 1, 6, 4 };
        wiggleSort1(nums1);
        System.out.println(Arrays.toString(nums1)); // [1, 3, 2, 5, 4, 6]
        

        int[] nums2 = { 3, 5, 2, 1, 6, 4 };
        wiggleSort2(nums2);
        System.out.println(Arrays.toString(nums2)); // [3, 5, 1, 6, 2, 4]

        int[] nums3 = { 3, 5, 2, 1, 6, 4 };
        wiggleSort3(nums3);
        System.out.println(Arrays.toString(nums3)); // [3, 5, 1, 6, 2, 4]

        int[] nums4 = { 3, 5, 2, 1, 6, 4 };
        wiggleSort2(nums4);
        System.out.println(Arrays.toString(nums4)); // [3, 5, 1, 6, 2, 4]
    }

    // 解法一：排序 【通过】
    public static void wiggleSort1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            swap(nums, i, i + 1);
        }
    }
    
    // 方法二：一遍交换 【通过】
    public static void wiggleSort2(int[] nums) {
        boolean less = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (less) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            less = !less;
        }
    }

    // 将条件压缩到一行来简化代码
    public static void wiggleSort3(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (((i % 2 == 0) && nums[i] > nums[i + 1])
                    || ((i % 2 == 1) && nums[i] < nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
    }

    // 另一个令人惊讶的解法
    public static void wiggleSort4(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0) == (nums[i] > nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
