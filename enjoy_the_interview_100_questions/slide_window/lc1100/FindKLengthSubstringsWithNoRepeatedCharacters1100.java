package lc1100;

/**
 * 题目标题：长度为 K 的无重复字符子串
 * 题目链接：https://leetcode.cn/problems/find-k-length-substrings-with-no-repeated-characters/
 * 题目描述：给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目。
 */
public class FindKLengthSubstringsWithNoRepeatedCharacters1100 {

    public static void main(String[] args) {
        String s = "havefunonleetcode";
        int k = 5;

        int result = numKLenSubstrNoRepeats(s, k); // 6
        System.out.println(result); // 这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。

        String s2 = "home";
        int k2 = 5;
        int result2 = numKLenSubstrNoRepeats(s2, k2); // 0
        System.out.println(result2); // 注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
    }

    public static int numKLenSubstrNoRepeats(String s, int k) {
        int len = s.length();
        if (k > 26 || k > len)
            return 0;

        int[] freq = new int[123];
        int cnt = 0;
        int ans = 0;
        char[] chars = s.toCharArray();

        //初始化窗口
        for (int i = 0; i < k; i++)
            if (freq[chars[i]]++ == 0)
                cnt++;
        if (cnt == k)
            ans++;

        //固定窗口大小，向右滑动
        for (int l = 0, r = k; r < len; ) {
            if (--freq[chars[l++]] == 0)
                cnt--;
            if (freq[chars[r++]]++ == 0)
                cnt++;
            if (cnt == k)
                ans++;
        }

        return ans;
    }
}
