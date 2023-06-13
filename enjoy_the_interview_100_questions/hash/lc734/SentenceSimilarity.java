import java.util.HashSet;
import java.util.Set;

/**
 * 题目标题：句子相似性
 * 题目链接：https://leetcode.cn/problems/sentence-similarity/
 * 题目描述：我们可以将一个句子表示为一个单词数组，例如，句子 "I am happy with leetcode" 可以表示为 arr =
 * ["I","am",happy","with","leetcode"]
 * 给定两个句子 sentence1 和 sentence2 分别表示为一个字符串数组，并给定一个字符串对 similarPairs ，其中
 * similarPairs[i] = [xi, yi] 表示两个单词 xi and yi 是相似的。
 * 
 * 如果 sentence1 和 sentence2 相似则返回 true ，如果不相似则返回 false 。
 * 
 * 两个句子是相似的，如果:
 * 
 * 它们具有 相同的长度 (即相同的字数)
 * sentence1[i] 和 sentence2[i] 是相似的
 * 请注意，一个词总是与它自己相似，也请注意，相似关系是不可传递的。例如，如果单词 a 和 b 是相似的，单词 b 和 c 也是相似的，那么 a 和 c
 * 不一定相似 。
 */
public class SentenceSimilarity {
    public static void main(String[] args) {
        String[] sentence1 = { "great", "acting", "skills" };
        String[] sentence2 = { "fine", "drama", "talent" };
        String[][] similarPairs = { { "great", "fine" }, { "drama", "acting" }, { "skills", "talent" } };

        boolean result = areSentencesSimilar(sentence1, sentence2, similarPairs);
        System.out.println(result); // true

        String[] sentence3 = { "great" };
        String[] sentence4 = { "great" };
        String[][] similarPairs2 = {};
        boolean result2 = areSentencesSimilar(sentence3, sentence4, similarPairs2);
        System.out.println(result2); // true

        String[] sentence5 = { "great" };
        String[] sentence6 = { "doubleplus", "good" };
        String[][] similarPairs3 = { { "great", "doubleplus" } };
        boolean result3 = areSentencesSimilar(sentence5, sentence6, similarPairs3);
        System.out.println(result3); // false

    }

    // 定义一个方法，输入两个句子（字符串数组）和一个单词对数组，返回两个句子是否相似
    public static boolean areSentencesSimilar(
            String[] words1, String[] words2, String[][] pairs) {
        // 如果两个句子的长度（单词数量）不相等，直接返回 false
        if (words1.length != words2.length)
            return false;

        // 创建一个集合来存储单词对，将每对单词通过 "#" 连接为一个字符串
        // 在 Java 中，HashSet 的查找操作比数组快，所以这里用 HashSet 来存储单词对
        Set<String> pairset = new HashSet();
        for (String[] pair : pairs)
            pairset.add(pair[0] + "#" + pair[1]);

        // 遍历两个句子的每一个对应位置的单词
        for (int i = 0; i < words1.length; ++i) {
            // 如果两个单词不相等，并且无法在 pairset 中找到对应的单词对（注意考虑顺序），则返回 false
            if (!words1[i].equals(words2[i]) &&
                    !pairset.contains(words1[i] + "#" + words2[i]) &&
                    !pairset.contains(words2[i] + "#" + words1[i]))
                return false;
        }
        // 如果所有的单词对都满足相似的条件，则返回 true
        return true;
    }
}
