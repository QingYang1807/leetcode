package lc487;

/**
 * 题目标题：最大连续1的s个数 II
 * 题目链接：https://leetcode.cn/problems/max-consecutive-ones-ii
 * 题目描述：给定一个二进制数组 nums ，如果最多可以翻转一个 0 ，则返回数组中连续 1 的最大个数。
 * 解题思路：滑动窗口，双指针
 */
public class MaxConsecutiveOnesII487 {
    public static void main(String[] args) {
        int[] nums1 = { 1, 0, 1, 1, 0 }; // 输出：4

        int[] nums2 = { 1, 0, 1, 1, 0, 1 }; // 输出：4

        int result1 = findMaxConsecutiveOnes1(nums1);
        int result2 = findMaxConsecutiveOnes1(nums2);

        System.out.println(result1);
        System.out.println(result2);

        int result3 = findMaxConsecutiveOnes2(nums1);
        int result4 = findMaxConsecutiveOnes2(nums2);

        System.out.println(result3);
        System.out.println(result4);
    }

    // 方法一：预处理 + 枚举
    public static int findMaxConsecutiveOnes1(int[] nums) {

        // 定义数组长度
        int n = nums.length;

        // 定义前缀数组和后缀数组,用于记录最长连续1的长度
        int[] pre = new int[n];
        int[] suff = new int[n];

        // 初始化最大长度为0
        int ans = 0;

        // 遍历数组,填充前缀数组
        for (int i = 0; i < n; ++i) {

            // 如果不是第一个元素,前缀长度为前一个元素的前缀长度
            if (i > 0)
                pre[i] = pre[i - 1];

            // 如果当前元素为1,前缀长度+1
            if (nums[i] == 1)
                pre[i]++;

            // 如果当前元素不为1,前缀长度清0
            else
                pre[i] = 0;

            // 更新最大长度
            ans = Math.max(ans, pre[i]);
        }

        // 遍历数组,填充后缀数组,逻辑同前缀数组
        for (int i = n - 1; i >= 0; --i) {

            if (i < n - 1)
                suff[i] = suff[i + 1];

            if (nums[i] == 1)
                suff[i]++;

            else
                suff[i] = 0;
        }

        // 如果当前元素为0,则最大长度为前缀长度+后缀长度+1
        for (int i = 0; i < n; ++i) {

            if (nums[i] == 0) {
                int res = 0;
                if (i > 0)
                    res += pre[i - 1];
                if (i < n - 1)
                    res += suff[i + 1];
                ans = Math.max(ans, res + 1);
            }
        }

        // 返回最大长度
        return ans;
    }

    // 方法二：动态规划
    public static int findMaxConsecutiveOnes2(int[] nums) {

        // 定义数组长度
        int n = nums.length;

        // 初始化最大长度为0
        int ans = 0;

        // 定义dp0和dp1,分别记录当前元素前的0的最大长度和1的最大长度
        int dp0 = 0;
        int dp1 = 0;

        // 遍历数组
        for (int i = 0; i < n; ++i) {

            // 如果当前元素为1
            if (nums[i] == 1) {

                // 1的最大长度加1
                dp1++;

                // 0的最大长度加1
                dp0++;
            }

            // 如果当前元素为0
            else {

                // 1的最大长度为之前0的最大长度+1
                dp1 = dp0 + 1;

                // 0的最大长度清0
                dp0 = 0;
            }

            // 更新最大长度,为dp0和dp1的最大值
            ans = Math.max(ans, Math.max(dp0, dp1));
        }

        // 返回最大长度
        return ans;
    }
}
