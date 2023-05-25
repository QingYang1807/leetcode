package enjoy_the_interview_100_questions.math;

/**
 * 题目名称：阿姆斯特朗数 1134
 * 题目链接：https://leetcode.cn/problems/armstrong-number/?envType=study-plan-v2&envId=premium-algo-100
 * 
 * 题目描述：
 * 给你一个整数 n ，让你来判定他是否是 阿姆斯特朗数，是则返回 true，不是则返回 false。
 * 假设存在一个 k 位数 n ，其每一位上的数字的 k 次幂的总和也是 n ，那么这个数是阿姆斯特朗数 。
 * 
 */
public class ArmstrongNumber1134 {
    public static void main(String[] args) {
        int n1 = 153;
        int n2 = 123;

        boolean result1 = isArmstrong(n1);
        boolean result2 = isArmstrong(n2);

        System.out.println(n1 + (result1 ? "是阿姆斯特朗数" : "不是阿姆斯特朗数"));
        System.out.println(n2 + (result2 ? "是阿姆斯特朗数" : "不是阿姆斯特朗数"));
    }

    /**
     * 判断一个整数是否是阿姆斯特朗数
     * 
     * @param N 待判断的整数
     * @return 如果是阿姆斯特朗数，返回 true；否则返回 false
     */
    public static boolean isArmstrong(int N) {
        int tmp = N, sum = 0;
        int k = String.valueOf(N).length(); // 获取整数 N 的位数
        for (int i = 0; i < k; i++) {
            sum += Math.pow(tmp % 10, k); // 计算每一位上数字的 k 次幂，并累加到 sum 中
            tmp /= 10; // 将 tmp 除以 10，将下一位数字移到个位上
        }
        return sum == N; // 判断 sum 是否等于原始数 N，如果相等则是阿姆斯特朗数，否则不是
    }
}
