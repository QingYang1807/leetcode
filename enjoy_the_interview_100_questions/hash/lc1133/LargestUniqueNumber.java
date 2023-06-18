package enjoy_the_interview_100_questions.hash.lc1133;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目标题：最大唯一数
 * 题目链接：https://leetcode.cn/problems/largest-unique-number
 * 题目描述：给你一个整数数组 A，请找出并返回在该数组中仅出现一次的最大整数。
 * 如果不存在这个只出现一次的整数，则返回 -1。
 */
public class LargestUniqueNumber {
    public static void main(String[] args) {
        int[] nums = { 5, 7, 3, 9, 4, 9, 8, 3, 1 };
        // 哈希表
        int result = largestUniqueNumber1(nums);
        System.out.println(result);

        // 计数排序
        int result2 = largestUniqueNumber2(nums);
        System.out.println(result2);
    }

    // 哈希表
    public static int largestUniqueNumber1(int[] A) {
        // 创建一个哈希映射来存储每个数字及其出现的次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : A) { // 遍历整个数组
            // 对于数组中的每个数字，如果它在哈希映射中存在，就将其计数加一，否则将其添加到哈希映射中并设置计数为一
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int max = -1; // 初始化最大的唯一数字为-1
        for (int num : countMap.keySet()) { // 遍历哈希映射的所有键（也就是数组中的所有数字）
            // 如果一个数字只出现了一次，并且这个数字比当前的最大唯一数字要大，就更新最大唯一数字
            if (countMap.get(num) == 1 && num > max) {
                max = num;
            }
        }
        return max; // 返回最大的唯一数字
    }

    public static int largestUniqueNumber2(int[] A) {
        // 初始化一个长度为1001的数组r，数组的索引代表数字，数组的值代表该数字在输入数组A中出现的次数
        int[] r = new int[1001];
        // 遍历输入数组A
        for (int i = 0; i < A.length; i++) {
            // 增加数组r中对应数字的计数
            r[A[i]]++;
        }
        // 从大到小遍历数组r
        for (int i = 1000; i >= 0; i--) {
            // 如果某个数字在输入数组A中只出现了一次
            if (r[i] == 1) {
                // 返回该数字，因为这是在数组A中只出现一次的最大整数
                return i;
            }
        }
        // 如果没有在数组A中只出现一次的整数，返回-1
        return -1;
    }
}