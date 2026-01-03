# 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

# 示例 1:

# 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
# 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

# 解释：
# 在 strs 中没有字符串可以通过重新排列来形成 "bat"。
# 字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
# 字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。

# 示例 2:
# 输入: strs = [""]
# 输出: [[""]]

# 示例 3:
# 输入: strs = ["a"]
# 输出: [["a"]]

# 提示：
# 1 <= strs.length <= 104
# 0 <= strs[i].length <= 100
# strs[i] 仅包含小写字母

import collections


class Solution(object):
    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """

        # # 方法一：排序 - 时间复杂度 O(n * k * log(k))，空间复杂度 O(n * k)
        # # 使用默认字典存储每个字母异位词的列表
        # # EN: Use a default dictionary to store the list of anagrams
        # mp = collections.defaultdict(list)
        # # 遍历每个字符串，将字符串排序后作为键，将字符串加入对应的列表中
        # # EN: Traverse each string, sort the string as the key, and add the string to the corresponding list
        # for st in strs:
        #     # 将字符串排序后作为键，将字符串加入对应的列表中
        #     # EN: Sort the string as the key, and add the string to the corresponding list
        #     key = "".join(sorted(st))
        #     # 将字符串加入对应的列表中
        #     # EN: Add the string to the corresponding list
        #     mp[key].append(st)
        
        # # 返回每个字母异位词的列表
        # # EN: Return the list of each anagram
        # return list(mp.values())

        # 方法二：计数 - 时间复杂度 O(n * k)，空间复杂度 O(n * k)，其中 n 是字符串数组的长度，k 是字符串的平均长度
        # 使用默认字典存储每个字母异位词的列表
        # EN: Use a default dictionary to store the list of anagrams
        mp = collections.defaultdict(list)

        for st in strs:
            counts = [0] * 26
            for ch in st:
                counts[ord(ch) - ord('a')] += 1

            # 需要将 list 转换成 tuple 才能进行哈希
            mp[tuple(counts)].append(st)
        return list(mp.values())


if __name__ == "__main__":
    solution = Solution()
    strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
    print(solution.groupAnagrams(strs))
    strs = [""]
    print(solution.groupAnagrams(strs))
    strs = ["a"]
    print(solution.groupAnagrams(strs))
    strs = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"]
    print(solution.groupAnagrams(strs))