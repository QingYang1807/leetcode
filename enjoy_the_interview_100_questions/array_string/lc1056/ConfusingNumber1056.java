package enjoy_the_interview_100_questions.array_string.lc1056;

/**
 * 题目标题：易混淆数
 * 题目链接：https://leetcode.cn/problems/confusing-number/?envType=study-plan-v2&envId=premium-algo-100
 * 
 * 题目描述：给定一个数字 N，当它满足以下条件的时候返回 true：
 * 原数字旋转 180° 以后可以得到新的数字。
 * 如 0, 1, 6, 8, 9 旋转 180° 以后，得到了新的数字 0, 1, 9, 8, 6 。
 * 2, 3, 4, 5, 7 旋转 180° 后，得到的不是数字。
 * 易混淆数 (confusing number) 在旋转180°以后，可以得到和原来不同的数，且新数字的每一位都是有效的。
 */
public class ConfusingNumber1056 {
    public static void main(String[] args) {
        int n1 = 6;
        boolean result1 = confusingNumber(n1); // 6 → 9
        System.out.println(result1); // true 把 6 旋转 180° 以后得到 9，9 是有效数字且 9!=6 。

        int n2 = 89;
        boolean result2 = confusingNumber(n2); // 89 → 68
        System.out.println(result2); // true 把 89 旋转 180° 以后得到 68，86 是有效数字且 86!=89 。

        int n3 = 11;
        boolean result3 = confusingNumber(n3); // 11 → 11
        System.out.println(result3); // false 把 11 旋转 180° 以后得到 11，11 是有效数字但是值保持不变，所以 11 不是易混淆数字。 

        int n4 = 25;
        boolean result4 = confusingNumber(n4); // 25 → ??
        System.out.println(result4); // false 把 25 旋转 180° 以后得到的不是数字。
    }

    public static boolean confusingNumber(int n) {
        int[] rotateMap = { 0, 1, -1, -1, -1, -1, 9, -1, 8, 6 }; // 旋转规则映射关系数组

        int num = n;
        int rotateNum = 0;
        while (num > 0) {
            int digit = num % 10; // 取得原数字的最后一位
            if (rotateMap[digit] == -1) {
                return false; // 如果最后一位数字旋转后不是有效数字，则直接返回false
            }
            rotateNum = rotateNum * 10 + rotateMap[digit]; // 构建旋转后的数字
            num /= 10; // 去掉原数字的最后一位
        }

        return rotateNum != n; // 判断旋转后的数字与原数字是否不相等
    }
}
