package enjoy_the_interview_100_questions.math.lc1180;

/**
 * 题目名称：统计只含单一字母的子串
 * 题目链接：https://leetcode.cn/problems/count-substrings-with-only-one-distinct-letter/?envType=study-plan-v2&envId=premium-algo-100
 * 
 * 题目描述：给你一个字符串 s，返回 只含 单一字母 的子串个数。
 */
public class CountSubstringsWithOnlyOneDistinctLetter1180 {
    public static void main(String[] args) {
        String s1 = "aaaba";
        String s2 = "aaaaaaaaaa";

        int result1 = countLetters(s1);
        int result2 = countLetters(s2);

        System.out.println(result1);
        System.out.println(result2);
    }

    /**
     * 统计只含单一字母的子串个数
     * 
     * @param s 给定的字符串
     * @return 只含单一字母的子串个数
     */
    public static int countLetters(String s) {
        int ans = 0; // 统计结果
        int count = 1; // 连续相同字母的计数
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++; // 当前字母与前一个字母相同，计数加一
            } else {
                ans += count * (count + 1) / 2; // 当前字母与前一个字母不同，计算前一个连续相同字母的子串个数并累加到结果中
                count = 1; // 重置计数为 1
            }
        }
        ans += count * (count + 1) / 2; // 计算最后一个连续相同字母的子串个数并累加到结果中
        return ans;
    }
}
