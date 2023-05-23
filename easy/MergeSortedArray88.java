package easy;

import java.util.Arrays;

/**
 * 题目链接:
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/
 * 
 * 解题思路: 直接合并后排序 | 双指针
 */
public class MergeSortedArray88 {
    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int m = 3;
        int[] nums2 = { 2, 5, 6 };
        int n = 3;

        merge1(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));

        int[] nums3 = { 1, 2, 3, 0, 0, 0 };
        int m2 = 3;
        int[] nums4 = { 2, 5, 6 };
        int n2 = 3;

        merge2(nums3, m2, nums4, n2);
        System.out.println(Arrays.toString(nums3));
    }

    // 直接合并后排序
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) { // 遍历nums2数组的每个元素
            nums1[m + i] = nums2[i]; // 将nums2的元素追加到nums1的末尾
        }
        Arrays.sort(nums1); // 对合并后的数组进行排序
    }

    // 双指针
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0; // 双指针，分别指向nums1和nums2数组的起始位置
        int[] sorted = new int[m + n]; // 创建一个新的数组来存储合并后的结果
        int cur; // 当前要合并的元素
        while (p1 < m || p2 < n) { // 当两个数组都未遍历完时
            if (p1 == m) { // 如果nums1已经遍历完
                cur = nums2[p2++]; // 取剩下的nums2元素
            } else if (p2 == n) { // 如果nums2已经遍历完
                cur = nums1[p1++]; // 取剩下的nums1元素
            } else if (nums1[p1] < nums2[p2]) { // 如果nums1当前元素小于nums2当前元素
                cur = nums1[p1++]; // 取nums1当前元素
            } else { // 否则，取nums2当前元素
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur; // 将当前元素放入合并后的数组
        }
        for (int i = 0; i != m + n; ++i) { // 将合并后的数组复制回nums1数组
            nums1[i] = sorted[i];
        }
    }
}
