package enjoy_the_interview_100_questions.hash.lc266;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 题目标题：回文排列
 * 题目链接：给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。
 * 题目描述：
 */
public class PalindromePermutation {
    public static void main(String[] args) {
        String text1 = "code";
        String text2 = "aab";
        String text3 = "carerac";

        boolean result1 = canPermutePalindrome1(text1);
        boolean result2 = canPermutePalindrome2(text2);
        boolean result3 = canPermutePalindrome3(text3);
        boolean result4 = canPermutePalindrome4(text2);
        boolean result5 = canPermutePalindrome5(text1);

        System.out.println(result1); // false
        System.out.println(result2); // true
        System.out.println(result3); // true
        System.out.println(result4); // true
        System.out.println(result5); // false
    }

    // 穷举
    public static boolean canPermutePalindrome1(String s) {
        // 初始化一个变量 count，用于记录出现次数为奇数的字符数量
        int count = 0;
        // 遍历 ASCII 表的前 128 个字符，并且在 count 大于 1 时停止遍历
        for (char i = 0; i < 128 && count <= 1; i++) {
            // 初始化一个变量 ct，用于记录当前字符在字符串中出现的次数
            int ct = 0;
            // 遍历字符串中的每一个字符
            for (int j = 0; j < s.length(); j++) {
                // 如果当前字符等于 i，则 ct 自增 1
                if (s.charAt(j) == i)
                    ct++;
            }
            // 将 ct 对 2 取模的结果加到 count 上，如果 ct 为奇数则会让 count 自增 1
            count += ct % 2;
        }
        // 如果出现次数为奇数的字符数量小于等于 1，则返回 true，表示可以通过重新排列变为一个回文字符串
        // 否则，返回 false
        return count <= 1;
    }

    // 基于哈希的映射表（HashMap）
    public static boolean canPermutePalindrome2(String s) {
        // 创建一个 HashMap，键为字符，值为该字符在字符串中出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        // 遍历字符串中的每一个字符
        for (int i = 0; i < s.length(); i++) {
            // 将当前字符及其出现次数放入 HashMap 中
            // 如果当前字符在 HashMap 中不存在，则默认其出现次数为0
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        // 初始化一个变量 count，用于记录出现次数为奇数的字符数量
        int count = 0;
        // 遍历 HashMap 中的每一个键，也就是字符串中的每一个字符
        for (char key : map.keySet()) {
            // 将当前字符的出现次数对 2 取模的结果加到 count 上
            // 如果当前字符的出现次数为奇数，则会让 count 自增 1
            count += map.get(key) % 2;
        }
        // 如果出现次数为奇数的字符数量小于等于 1，则返回 true，表示可以通过重新排列变为一个回文字符串
        // 否则，返回 false
        return count <= 1;
    }

    // 数组
    public static boolean canPermutePalindrome3(String s) {
        // 初始化一个大小为 128 的整型数组，用于存储每个字符在字符串中出现的次数
        int[] map = new int[128];
        // 遍历字符串中的每一个字符
        for (int i = 0; i < s.length(); i++) {
            // 使用字符的 ASCII 值作为下标，对应的数组元素值加一，记录每个字符出现的次数
            map[s.charAt(i)]++;
        }
        // 初始化一个变量 count，用于记录出现次数为奇数的字符数量
        int count = 0;
        // 遍历数组中的每一个元素，元素的下标代表字符的 ASCII 值
        for (int key = 0; key < map.length && count <= 1; key++) {
            // 将当前字符的出现次数对 2 取模的结果加到 count 上
            // 如果当前字符的出现次数为奇数，则会让 count 自增 1
            count += map[key] % 2;
        }
        // 如果出现次数为奇数的字符数量小于等于 1，则返回 true，表示可以通过重新排列变为一个回文字符串
        // 否则，返回 false
        return count <= 1;
    }

    // 数组+单次循环
    public static boolean canPermutePalindrome4(String s) {
        // 初始化一个大小为 128 的整型数组，用于存储每个字符在字符串中出现的次数
        int[] map = new int[128];
        // 初始化一个变量 count，用于记录出现次数为奇数的字符数量
        int count = 0;
        // 遍历字符串中的每一个字符
        for (int i = 0; i < s.length(); i++) {
            // 使用字符的 ASCII 值作为下标，对应的数组元素值加一，记录每个字符出现的次数
            map[s.charAt(i)]++;
            // 如果当前字符出现的次数是偶数，那么将 count 减一，否则将 count 加一
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        // 如果出现次数为奇数的字符数量小于等于 1，则返回 true，表示可以通过重新排列变为一个回文字符串
        // 否则，返回 false
        return count <= 1;
    }

    // 集合
    public static boolean canPermutePalindrome5(String s) {
        // 初始化一个字符型 HashSet，用于存储出现次数为奇数的字符
        Set<Character> set = new HashSet<>();
        // 遍历字符串中的每一个字符
        for (int i = 0; i < s.length(); i++) {
            // 尝试将字符添加到 set 中，如果添加失败（即该字符已经存在于 set 中），则从 set 中移除这个字符
            // 这样，每个字符在 set 中出现的次数就是它在字符串中出现的次数除以 2 的余数（即 0 或 1）
            if (!set.add(s.charAt(i)))
                set.remove(s.charAt(i));
        }
        // 如果 set 的大小（即出现次数为奇数的字符的数量）小于等于 1，则返回 true，表示可以通过重新排列变为一个回文字符串
        // 否则，返回 false
        return set.size() <= 1;
    }
}
