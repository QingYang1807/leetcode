package enjoy_the_interview_100_questions.array_string.lc624;

/**
 * 题目标题：数组列表中的最大距离
 * 题目链接：https://leetcode.cn/problems/maximum-distance-in-arrays/?envType=study-plan-v2&envId=premium-algo-100
 * 
 * 题目描述：给定 m 个数组，每个数组都已经按照升序排好序了。
 * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。
 * 两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。
 * 你的任务就是去找到最大距离。
 * 
 * 难度：中等
 */
public class MaximumDistanceInArrays624 {
    public static void main(String[] args) {
        int[][] list = { { 1, 2, 3 }, { 4, 5 }, { 1, 2, 3 } };
        int result = maxDistance(list);
        System.out.println(result);
    }

    public static int maxDistance(int[][] list) {
        int res = 0; // 存储最大距离的变量
        int min_val = list[0][0]; // 当前最小值
        int max_val = list[0][list[0].length - 1]; // 当前最大值

        // 遍历数组列表
        for (int i = 1; i < list.length; i++) {
            // 计算最大距离
            res = Math.max(res,
                    Math.max(Math.abs(list[i][list[i].length - 1] - min_val), Math.abs(max_val - list[i][0])));
            // 更新最小值和最大值
            min_val = Math.min(min_val, list[i][0]);
            max_val = Math.max(max_val, list[i][list[i].length - 1]);
        }

        return res; // 返回最大距离
    }
}