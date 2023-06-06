package lc340;

import java.util.Collections;
import java.util.HashMap;

/**
 * 题目名称：至多包含K个不同字符的最长子串
 * 题目链接：https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters/
 * 题目描述：给你一个字符串 s 和一个整数 k ，请你找出 至多 包含 k 个 不同 字符的最长子串，并返回该子串的长度。
 * 示例1：
 * 输入：s = "eceba", k = 2
 * 输出：3
 * 解释：满足题目要求的子串是 "ece" ，长度为 3 。
 * 
 * 示例2：
 * 输入：s = "aa", k = 1
 * 输出：2
 * 解释：满足题目要求的子串是 "aa" ，长度为 2 。
 */
public class LongestSubstringWithAtMostXXXDistinctCharacters {
    public static void main(String[] args) {
        String s1 = "eceba";
        int k1 = 2;
        int result1 = lengthOfLongestSubstringKDistinct(s1, k1); // 3
        System.out.println(result1);

        String s2 = "aa";
        int k2 = 1;
        int result2 = lengthOfLongestSubstringKDistinct(s2, k2); // 2
        System.out.println(result2);
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n * k == 0)
            return 0;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        int max_len = 1;

        while (right < n) {
            // add new character and move right pointer
            hashmap.put(s.charAt(right), right++);

            // slidewindow contains 3 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // move left pointer of the slidewindow
                left = del_idx + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}
