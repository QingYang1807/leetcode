package enjoy_the_interview_100_questions.hash.lc249;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Stop;

/**
 * 题目标题：移位字符串分组
 * 题目链接：https://leetcode.cn/problems/group-shifted-strings/
 * 题目描述：
 * 给定一个字符串，对该字符串可以进行 “移位” 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如："abc" -> "bcd"。这样，我们可以持续进行 “移位” 操作，从而生成如下移位序列：
 * "abc" -> "bcd" -> ... -> "xyz"
 * 给定一个包含仅小写字母字符串的列表，将该列表中所有满足 “移位” 操作规律的组合进行分组并返回。
 */
public class GroupShiftedStrings {
    public static void main(String[] args) {
        List<String> strArr = Arrays.asList("abc", "bcd", "acef", "xyz", "az", "ba", "a", "z");
        List<List<String>> result = groupStrings(strArr);
        System.out.println(result);
    }

    // 将单词转化为由逗号分隔的字符串，每个单词对应字母之间的差值
    public static String wordToNumber(String word) {
        StringBuilder res = new StringBuilder();
        if (word.length() == 1) {
            return "";
        }
        for (int i = 0; i < word.length() - 1; i++) {
            int num = (word.charAt(i + 1) - word.charAt(i)) % 26;
            res.append(",").append(num);
        }
        return res.toString();
    }

    // 对一组字符串进行分类，相同偏移量的字符串放在同一个列表中
    public static List<List<String>> groupStrings(List<String> strings) {
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String word : strings) {
            String key = wordToNumber(word);
            if (!groupMap.containsKey(key)) {
                groupMap.put(key, new ArrayList<>());
            }
            groupMap.get(key).add(word);
        }
        return new ArrayList<>(groupMap.values());
    }
}
