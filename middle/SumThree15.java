package middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目链接：https://leetcode.cn/problems/3sum/
 * 
 * 解题思路：排序、双指针
 */
class SumThree15 {
    public static void main(String[] args) {
        int[] nums = { -1, 0, 1, 2, -1, -4 };
        List<List<Integer>> result = threeSum2(nums);
        System.out.println(result);

        int[] nums2 = { 0, 0, 0, 0 };
        List<List<Integer>> result2 = threeSum2(nums2);
        System.out.println(result2);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums); // 排序 O(nLogn)
        int size = nums.length;

        for (int i = 0; i < size; i++) { // O(n^2)
            if (i > 0 && nums[i] == nums[i - 1]) { // 从小到大搜索，跳过重复值
                continue;
            }

            List<List<Integer>> result = twoSum(nums, i + 1, size - 1, -nums[i], nums[i]); // 调用子函数，简化问题为twoSum问题
            answer.addAll(answer.size(), result);
        }

        return answer;
    }

    private static List<List<Integer>> twoSum(int[] nums, int start, int end, int target, int value) {
        List<List<Integer>> answer = new ArrayList<>();

        while (start < end) {
            int sum = nums[start] + nums[end];

            if (sum == target) {
                List<Integer> result = new ArrayList<>();
                result.add(value);
                result.add(nums[start]);
                result.add(nums[end]);
                answer.add(result); // 找到目标和，加入解

                // 由于可能存在多组解，所以移动指针，继续寻找下一组2sum，并跳过重复值
                while (start < end && nums[start] == nums[start + 1]) {
                    start++;
                }
                start++;

                while (start < end && nums[end] == nums[end - 1]) {
                    end--;
                }
                end--;
            } else if (sum < target) { // 如果和比目标值小，移动头指针；
                start++;
            } else { // 否则移动右指针
                end--;
            }
        }
        return answer;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // 对数组进行升序排序，方便后续的查找和去重
        List<List<Integer>> ans = new ArrayList<List<Integer>>(); // 存储结果的列表
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同，避免重复计算相同的结果
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first]; // 目标值，使得 a+b+c=0，转化为求 -a 的两数之和
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同，避免重复计算相同的结果
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧，因为数组已经排序过
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加，就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    // 找到满足条件的解，加入结果列表
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}