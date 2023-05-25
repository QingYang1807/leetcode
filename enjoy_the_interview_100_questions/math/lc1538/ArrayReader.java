package enjoy_the_interview_100_questions.math.lc1538;

public class ArrayReader {
    private int[] nums;

    public ArrayReader(int[] nums) {
        this.nums = nums;
    }

    // 返回数组中指定索引的元素值
    public int get(int index) {
        return nums[index];
    }

    // 返回数组的长度
    public int length() {
        return nums.length;
    }

    // 比较指定索引的四个元素，根据题目要求返回对应的结果
    public int query(int a, int b, int c, int d) {
        int valueA = get(a);
        int valueB = get(b);
        int valueC = get(c);
        int valueD = get(d);

        if (valueA == valueB && valueA == valueC && valueA == valueD) {
            return 4;
        } else if ((valueA == 0 && valueB == 0 && valueC == 0 && valueD == 1) ||
                (valueA == 0 && valueB == 0 && valueC == 1 && valueD == 0) ||
                (valueA == 0 && valueB == 1 && valueC == 0 && valueD == 0) ||
                (valueA == 1 && valueB == 0 && valueC == 0 && valueD == 0)) {
            return 2;
        } else {
            return 0;
        }
    }
}
