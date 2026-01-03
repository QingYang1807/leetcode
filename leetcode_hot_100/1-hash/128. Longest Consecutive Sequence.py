# 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
# 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

# 示例 1：

# 输入：nums = [100,4,200,1,3,2]
# 输出：4
# 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
# 示例 2：

# 输入：nums = [0,3,7,2,5,8,4,6,0,1]
# 输出：9
# 示例 3：

# 输入：nums = [1,0,1,2]
# 输出：3
 

# 提示：

# 0 <= nums.length <= 105
# -109 <= nums[i] <= 109


class Solution(object):
    def longestConsecutive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        # 方法一：哈希表 - 时间复杂度 O(n)，空间复杂度 O(n)
        # 使用哈希表存储数组中的元素
        # EN: Use a hash table to store the elements in the array
        nums_set = set(nums)
        # 初始化最长连续序列的长度为0
        # EN: Initialize the length of the longest consecutive sequence to 0
        longest_streak = 0
        # 遍历哈希表中的每个元素
        # EN: Traverse each element in the hash table
        for num in nums_set:
            # 如果当前元素的前一个元素不在哈希表中，则当前元素是连续序列的起点
            # EN: If the previous element of the current element is not in the hash table, then the current element is the starting point of the consecutive sequence
            if num - 1 not in nums_set:
                # 初始化当前连续序列的起点为当前元素
                # EN: Initialize the starting point of the current consecutive sequence to the current element
                current_num = num
                # 初始化当前连续序列的长度为1
                # EN: Initialize the length of the current consecutive sequence to 1
                current_streak = 1
                # 当当前元素的下一个元素在哈希表中时，继续遍历
                # EN: When the next element of the current element is in the hash table, continue to traverse
                while current_num + 1 in nums_set:
                    # 当前元素的下一个元素在哈希表中，则当前连续序列的长度加1
                    # EN: When the next element of the current element is in the hash table, the length of the current consecutive sequence is incremented by 1
                    current_num += 1
                    # 当前连续序列的长度加1
                    # EN: The length of the current consecutive sequence is incremented by 1
                    current_streak += 1
                # 更新最长连续序列的长度
                # EN: Update the length of the longest consecutive sequence
                longest_streak = max(longest_streak, current_streak)
        # 返回最长连续序列的长度
        # EN: Return the length of the longest consecutive sequence
        return longest_streak