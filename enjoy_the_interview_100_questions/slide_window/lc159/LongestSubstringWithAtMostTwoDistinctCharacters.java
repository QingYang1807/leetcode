package lc159;

import java.util.Collections;
import java.util.HashMap;

/**
 * 题目名称：至多包含两个不同字符的最长子串
 * 题目链接：https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters/
 * 题目描述：给你一个字符串 s ，请你找出 至多 包含 两个不同字符 的最长子串，并返回该子串的长度。
 * 示例1：
 * 输入：s = "eceba"
 * 输出：3
 * 解释：满足题目要求的子串是 "ece" ，长度为 3 。
 * 
 * 示例2：
 * 输入：s = "ccaabbb"
 * 输出：5
 * 解释：满足题目要求的子串是 "aabbb" ，长度为 5 。
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public static void main(String[] args) {
        String s1 = "eceba";
        String s2 = "ccaabbb";

        int result1 = lengthOfLongestSubstringTwoDistinct(s1);
        int result2 = lengthOfLongestSubstringTwoDistinct(s2);

        System.out.println(result1); // 3 - ece
        System.out.println(result2); // 5 - aabbb
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        // 如果 N 的长度小于 3 ，返回 N 。
        if (n < 3)
            return n;

        // 将左右指针都初始化成字符串的左端点 left = 0 和 right = 0
        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        // 且初始化最大子字符串为 max_len = 2
        int max_len = 2;

        // 当右指针小于 N 时
        while (right < n) {
            // 如果 hashmap 包含小于 3 个不同字符，那么将当前字符 s[right] 放到 hashmap 中并将右指针往右移动一次。
            // slidewindow contains less than 3 characters
            if (hashmap.size() < 3)
                hashmap.put(s.charAt(right), right++);

            // 如果 hashmap 包含 3 个不同字符
            // slidewindow contains 3 characters
            if (hashmap.size() == 3) {
                // 将最左边的字符从 哈希表中删去
                // delete the leftmost character
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // 并移动左指针，以便滑动窗口只包含 2 个不同的字符。
                // move left pointer of the slidewindow
                left = del_idx + 1;
            }

            // 更新 max_len
            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}
