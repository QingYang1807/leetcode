package easy;

import java.util.Arrays;

/**
 * 题目链接: https://leetcode.cn/problems/missing-number/description/
 * 解题思路: 排序
 */
public class MissingNumber268 {
    public static void main(String[] args) {

        int[] caseNums1 = {3, 0, 1};
        int[] caseNums2 = {0, 1};
        int[] caseNums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};

        int result1 = missingNumber1(caseNums1);
        int result2 = missingNumber1(caseNums2);
        int result3 = missingNumber1(caseNums3);

        int result4 = missingNumber2(caseNums1);
        int result5 = missingNumber2(caseNums2);
        int result6 = missingNumber2(caseNums3);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
        System.out.println(result6);
    }

    public static int missingNumber1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }

    public static int missingNumber2(int[] nums) {
        int n = nums.length; // 0 ~ n
        // 排序
        selectionSort(nums);
        // 遍历查找
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        // 有边界比对
        if (nums[n - 1] != n) {
            return n;
        }

        return 0;
    }

    // 选择排序
    public static void selectionSort(int[] arr) {
        // 如果数组为空或者长度小于等于1，不需要排序，直接返回
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 外层循环，用于控制排序的轮数，每轮确定一个最小值的位置
        for (int i = 0; i < arr.length; i++) {
            // 初始化当前轮次的最小值索引为i
            int minIndex = i;

            // 内层循环，用于查找最小值的索引
            for (int j = i + 1; j < arr.length; j++) {
                // 如果发现新的最小值，更新最小值索引
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 如果最小值索引不等于i，说明找到了新的最小值，需要交换
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
