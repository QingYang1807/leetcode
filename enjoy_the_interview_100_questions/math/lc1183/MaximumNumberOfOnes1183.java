package enjoy_the_interview_100_questions.math.lc1183;

import java.util.PriorityQueue;

/**
 * 题目名称：矩阵中 1 的最大数量
 * 题目链接：https://leetcode.cn/problems/maximum-number-of-ones
 * 
 * 题目描述：现在有一个尺寸为 width * height 的矩阵 M，矩阵中的每个单元格的值不是 0 就是 1。
 * 而且矩阵 M 中每个大小为 sideLength * sideLength 的 正方形 子阵中，1 的数量不得超过 maxOnes。
 * 请你设计一个算法，计算矩阵中最多可以有多少个 1。
 */
public class MaximumNumberOfOnes1183 {
    public static void main(String[] args) {
        // 预期结果：4
        int width = 3;
        int height = 3;
        int sideLength = 2;
        int maxOnes = 1;
        int result1 = maximumNumberOfOnes(width, height, sideLength, maxOnes);
        System.out.println(result1);

        // 预期结果：6
        int width2 = 3;
        int height2 = 3;
        int sideLength2 = 2;
        int maxOnes2 = 2;
        int result2 = maximumNumberOfOnes(width2, height2, sideLength2, maxOnes2);
        System.out.println(result2);
    }

    /**
     * 此方法使用优先队列来存储每个区域中1的数量，并保持队列的大小不超过maxOnes。
     * 通过迭代遍历sideLength x sideLength的区域，并计算区域中1的数量，然后将数量添加到优先队列中。
     * 如果优先队列的大小超过了maxOnes，就移除数量最小的元素。最后，将优先队列中的元素累加到结果ans中，并返回结果。
     * 
     * @param width
     * @param height
     * @param sideLength
     * @param maxOnes
     * @return
     */
    public static int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int ans = 0; // 存储结果的变量
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 优先队列，用于存储当前区域中1的数量
        for (int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                // 计算当前区域中1的数量，并添加到优先队列中
                pq.add(((width - 1 - i) / sideLength + 1) * ((height - 1 - j) / sideLength + 1));
                if (pq.size() > maxOnes) {
                    pq.poll(); // 如果优先队列中的元素数量超过maxOnes，则移除数量最小的元素
                }
            }
        }
        while (!pq.isEmpty()) {
            ans += pq.poll(); // 将优先队列中的元素累加到结果中
        }
        return ans; // 返回最终的结果
    }
}
