package easy;

/**
 * 题目链接: https://leetcode.cn/problems/search-insert-position/
 * <p>
 * 解题思路: 二分查找
 */
public class SearchInsertPosition35 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 5;
        int result1 = searchInsert(nums, target);

        int[] nums2 = {1,3,5,6};
        int target2 = 2;
        int result2 = searchInsert(nums2, target2);

        int[] nums3 = {1,3,5,6};
        int target3 = 7;
        int result3 = searchInsert(nums3, target3);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

    }

    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}

