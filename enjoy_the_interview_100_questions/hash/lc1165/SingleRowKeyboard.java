package enjoy_the_interview_100_questions.hash.lc1165;

import java.util.HashMap;

/**
 * 题目标题：单行键盘
 * 题目链接：https://leetcode.cn/problems/single-row-keyboard
 * 题目描述：我们定制了一款特殊的键盘，所有的键都 排列在一行上 。
 * 给定一个长度为 26 的字符串 keyboard ，来表示键盘的布局(索引从 0 到 25 )。一开始，你的手指在索引 0
 * 处。要输入一个字符，你必须把你的手指移动到所需字符的索引处。手指从索引 i 移动到索引 j 所需要的时间是 |i - j|。
 * 您需要输入一个字符串 word 。写一个函数来计算用一个手指输入需要多少时间。
 */
public class SingleRowKeyboard {
    public static void main(String[] args) {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "cba";
        int result = calculateTime(keyboard, word);
        System.out.println(result); // 4

        String keyboard2 = "pqrstuvwxyzabcdefghijklmno";
        String word2 = "leetcode";
        int result2 = calculateTime(keyboard2, word2);
        System.out.println(result2); // 73

        int result3 = calculateTime2(keyboard, word);
        System.out.println(result3); // 4

        int result4 = calculateTime2(keyboard2, word2);
        System.out.println(result4); // 73
    }

    // 定义一个方法，输入键盘布局和单词，返回敲打单词所需要的时间
    public static int calculateTime(String keyboard, String word) {
        // 初始化总时间和上一个字母的位置
        int sum = 0, pre = 0;
        // 遍历单词的每一个字母
        for (int i = 0; i < word.length(); ++i) {
            // 在键盘布局上找到对应的字母
            for (int j = 0; j < 26; ++j) {
                // 如果键盘布局的当前位置的字母等于单词的当前字母
                if (keyboard.charAt(j) == word.charAt(i)) {
                    // 计算当前字母与上一个字母的位置差，累加到总时间上
                    sum += Math.abs(j - pre);
                    // 更新上一个字母的位置
                    pre = j;
                    // 找到字母后就可以结束内部循环
                    break;
                }
            }
        }
        // 返回总时间
        return sum;
    }

    public static int calculateTime2(String keyboard, String word) {
        // 创建一个HashMap来存储键盘布局中每个字母的位置
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < keyboard.length(); ++i) {
            // 将键盘布局中的字母及其位置添加到map中
            map.put(keyboard.charAt(i), i);
        }
        // 初始化总时间和上一个字母的位置
        int sum = 0, pre = 0;
        // 遍历单词的每一个字母
        for (int i = 0; i < word.length(); ++i) {
            // 从map中获取当前字母的位置，并计算与上一个字母的位置差，累加到总时间上
            sum += Math.abs(map.get(word.charAt(i)) - pre);
            // 更新上一个字母的位置为当前字母的位置
            pre = map.get(word.charAt(i));
        }
        // 返回总时间
        return sum;
    }
}
