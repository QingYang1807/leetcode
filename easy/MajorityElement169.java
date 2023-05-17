package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 题目链接:
 * https://leetcode.cn/problems/majority-element/?envType=study-plan-v2&id=top-interview-150
 * 
 * 解题思路: 哈希表 | 排序 | 随机化 | 分治 | Boyer-Moore 投票算法 | 其他
 */
public class MajorityElement169 {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[] nums1 = { 2, 2, 1, 1, 1, 2, 2 };
        int result1 = solution1.majorityElement(nums1);
        System.out.println(result1);

        Solution2 solution2 = new Solution2();
        int[] nums2 = { 2, 2, 1, 1, 1, 2, 2 };
        int result2 = solution1.majorityElement(nums2);
        System.out.println(result2);

        Solution3 solution3 = new Solution3();
        int[] nums3 = { 2, 2, 1, 1, 1, 2, 2 };
        int result3 = solution3.majorityElement(nums3);
        System.out.println(result3);

        Solution4 solution4 = new Solution4();
        int[] nums4 = { 2, 2, 1, 1, 1, 2, 2 };
        int result4 = solution4.majorityElement(nums4);
        System.out.println(result4);

        Solution5 solution5 = new Solution5();
        int[] nums5 = { 2, 2, 1, 1, 1, 2, 2 };
        int result5 = solution5.majorityElement(nums5);
        System.out.println(result5);

        Solution2 solution6 = new Solution2();
        int[] nums6 = { 2, 2, 1, 1, 1, 2, 2 };
        int result6 = solution6.majorityElement(nums6);
        System.out.println(result6);
    }
}

// 哈希表
class Solution1 {
    // 哈希表方法
    private Map<Integer, Integer> countNums(int[] nums) {
        // 创建一个哈希表来记录每个数字出现的次数
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                // 如果哈希表中没有该数字，则将其添加到哈希表并设置计数为1
                counts.put(num, 1);
            } else {
                // 如果哈希表中已经包含该数字，则将其计数加1
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    public int majorityElement(int[] nums) {
        // 调用countNums方法获得数字计数的哈希表
        Map<Integer, Integer> counts = countNums(nums);

        // 遍历哈希表，找到出现次数最多的数字
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                // 如果当前数字的计数大于目前的最多次数，则更新最多次数的数字为当前数字
                majorityEntry = entry;
            }
        }

        // 返回出现次数最多的数字
        return majorityEntry.getKey();
    }
}

// 排序
class Solution2 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}

// 随机化
class Solution3 {
    private int randRange(Random rand, int min, int max) {
        // 生成[min, max)范围内的随机数
        return rand.nextInt(max - min) + min;
    }

    private int countOccurrences(int[] nums, int num) {
        // 统计数组中数字num的出现次数
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    public int majorityElement(int[] nums) {
        Random rand = new Random();

        int majorityCount = nums.length / 2;

        while (true) {
            // 随机选择一个候选元素
            int candidate = nums[randRange(rand, 0, nums.length)];

            // 判断候选元素是否为主要元素
            if (countOccurrences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }
}

// 分治
class Solution4 {
    private int countInRange(int[] nums, int num, int lo, int hi) {
        // 统计数组nums在[lo, hi]范围内数字num的出现次数
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // 递归函数，用于在nums数组的[lo, hi]范围内查找主要元素

        // 基本情况：当数组只有一个元素时，该元素即为主要元素
        if (lo == hi) {
            return nums[lo];
        }

        // 递归调用：将数组划分为左右两个子区间，分别进行递归查找主要元素
        int mid = (hi - lo) / 2 + lo; // 计算中间索引
        int left = majorityElementRec(nums, lo, mid); // 递归查找左子区间的主要元素
        int right = majorityElementRec(nums, mid + 1, hi); // 递归查找右子区间的主要元素

        // 合并结果：如果左右子区间的主要元素相同，直接返回该元素
        if (left == right) {
            return left;
        }

        // 否则，统计左右子区间的主要元素出现次数，并返回出现次数较多的主要元素
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }

    public int majorityElement(int[] nums) {
        // 外部接口函数，调用递归函数在整个数组范围内查找主要元素
        return majorityElementRec(nums, 0, nums.length - 1);
    }

}

// Boyer-Moore 投票算法
class Solution5 {
    public int majorityElement(int[] nums) {
        int count = 0; // 计数器，用于记录当前数字的出现次数
        Integer candidate = null; // 当前的候选元素

        for (int num : nums) {
            if (count == 0) {
                // 如果计数器为0，表示当前没有候选元素，将当前数字设为候选元素
                candidate = num;
            }
            // 如果当前数字与候选元素相同，则计数器加1；否则，计数器减1
            count += (num == candidate) ? 1 : -1;
        }

        return candidate; // 返回候选元素，即出现次数最多的元素
    }
}

// 其他
class Solution6 {
    public int majorityElement(int[] nums) {
        int count = 0; // 初始化计数器为 0
        Integer candidate = null; // 初始化候选元素为空

        for (int num : nums) { // 遍历数组中的每个元素
            if (count == 0) { // 如果计数器为 0，将当前元素作为候选元素
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1; // 如果当前元素与候选元素相同，计数器加 1；否则，减 1
        }

        return candidate; // 返回多数元素
    }
}