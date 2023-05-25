package enjoy_the_interview_100_questions.math.lc1538;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * 题目名称：找出隐藏数组中出现次数最多的元素
 * 题目链接：https://leetcode.cn/problems/guess-the-majority-in-a-hidden-array/
 * 
 * 题目描述：
 * 给定一个整数数组 nums，且 nums 中的所有整数都为 0 或 1。你不能直接访问这个数组，你需要使用 API ArrayReader ，该 API
 * 含有下列成员函数：
 * int query(int a, int b, int c, int d)：其中 0 <= a < b < c < d <
 * ArrayReader.length() 。此函数查询以这四个参数为下标的元素并返回：
 * 4 : 当这四个元素相同（0 或 1）时。
 * 2 : 当其中三个元素的值等于 0 且一个元素等于 1 时，或当其中三个元素的值等于 1 且一个元素等于 0 时。
 * 0 : 当其中两个元素等于 0 且两个元素等于 1 时。
 * int length()：返回数组的长度。
 * 你可以调用 query() 最多 2 * n 次，其中 n 等于 ArrayReader.length()。
 * 
 * 返回 nums 中出现次数最多的值的任意索引，若所有的值出现次数均相同，返回 -1。
 */
public class GuessTheMajorityInAHiddenArray1538 {
    public static void main(String[] args) {
        ArrayReader reader = new ArrayReader(new int[] { 0, 0, 1, 1, 0 });
        int result = guessMajority(reader);
        System.out.println(result); // 应输出 0

        reader = new ArrayReader(new int[] { 1, 0, 1, 0, 1, 0, 1, 0 });
        result = guessMajority(reader);
        System.out.println(result); // 应输出 -1
    }

    /**
     * // This is the ArrayReader's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface ArrayReader {
     * public:
     * // Compares 4 different elements in the array
     * // return 4 if the values of the 4 elements are the same (0 or 1).
     * // return 2 if three elements have a value equal to 0 and one element has
     * value equal to 1 or vice versa.
     * // return 0 : if two element have a value equal to 0 and two elements have a
     * value equal to 1.
     * public int query(int a, int b, int c, int d);
     *
     * // Returns the length of the array
     * public int length();
     * };
     */
    public static int guessMajority(ArrayReader reader) {
        int n = reader.length();
        List<Integer> v = new ArrayList<>(n);
        v.add(1);

        BiFunction<Integer, Integer, Boolean> checkIfSame = (p, q) -> {
            List<Integer> chooseP = new ArrayList<>(Arrays.asList(p));
            List<Integer> chooseQ = new ArrayList<>(Arrays.asList(q));
            for (int i = 0; i < n; ++i) {
                if (i != p && i != q) {
                    chooseP.add(i);
                    chooseQ.add(i);
                    if (chooseP.size() == 4) {
                        break;
                    }
                }
            }
            chooseP.sort(Integer::compareTo);
            chooseQ.sort(Integer::compareTo);
            return reader.query(chooseP.get(0), chooseP.get(1), chooseP.get(2), chooseP.get(3)) == reader
                    .query(chooseQ.get(0), chooseQ.get(1), chooseQ.get(2), chooseQ.get(3));
        };

        BiFunction<Integer, Integer, Integer> find = (num, length) -> {
            int idx = -1;
            for (int i = 0; i < length; ++i) {
                if (v.get(i) == num) {
                    idx = i;
                    break;
                }
            }
            return idx;
        };
        for (int i = 1; i < n; ++i) {
            v.add(v.size() - 1); // 将 i 替换为 (v.size() - 1)，确保正确的元素数量
        }

        int sum = v.stream().mapToInt(Integer::intValue).sum();
        if (sum * 2 == n) {
            return -1;
        }
        return sum * 2 < n ? find.apply(0, n) : find.apply(1, n);
    }
}
