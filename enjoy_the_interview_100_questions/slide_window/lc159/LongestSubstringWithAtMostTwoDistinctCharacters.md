<span style="background-color: #ffa500; color: #fff; padding: 4px 8px; border-radius: 4px;">中等</span>

# 题目

给你一个字符串 `s` ，请你找出 **至多** 包含 **两个不同字符** 的最长子串，并返回该子串的长度。

 

**示例 1：**

```
输入：s = "eceba"
输出：3
解释：满足题目要求的子串是 "ece" ，长度为 3 。
```

**示例 2：**

```
输入：s = "ccaabbb"
输出：5
解释：满足题目要求的子串是 "aabbb" ，长度为 5 。
```

**提示：**

- `1 <= s.length <= 105`
- `s` 由英文字母组成

---

# 方法 1：滑动窗口

## **想法**

为了遍历一遍就得到答案，我们使用一个左指针和一个右指针表示滑动窗口的边界。

一开始，让两个指针都指向 0 ，当窗口包含的字符不超过 2 个不同的字符时，就不断将右指针往右边移动。如果在某一个位置有 3 个不同的字符，就开始移动左指针，直到窗口内包含不超过 2 个不同字符。

![](D:\picture\markdown\LongestSubstringWithAtMostTwoDistinctCharacters\202306061113174.png)

这就是基本的想法：沿着字符串移动滑动窗口，并保持窗口内只有不超过 2 个不同字符，同时每一步都更新最长子串的长度。

只有一个问题还没解决 - 如何移动左指针确保窗口内只有 2 种不同的字符？

我们使用一个 hashmap ，把字符串里的字符都当做键，在窗口中的最右边的字符位置作为值。每一个时刻，这个 hashmap 包括不超过 3 个元素。

![](D:\picture\markdown\LongestSubstringWithAtMostTwoDistinctCharacters\202306061113287.png)

比方说，通过这个 hashmap ，你可以知道窗口 "eeeeeeeet" 中字符 e 最右边的位置是 8 ，所以必须要至少将左指针移动到 8 + 1 = 9 的位置来将 e 从滑动窗口中移除。

我们的方法时间复杂度是否是最优的呢？答案是是的。我们只将字符串的 N 个字符遍历了一次，时间复杂度是 �(�)O(*N*) 。

## **算法**

现在我们可以写出如下算法：

- 如果 `N` 的长度小于 `3` ，返回 `N` 。
- 将左右指针都初始化成字符串的左端点 `left = 0` 和 `right = 0` ，且初始化最大子字符串为 `max_len = 2`
- 当右指针小于`N`时：
  - 如果 hashmap 包含小于 `3` 个不同字符，那么将当前字符 `s[right]` 放到 hashmap 中并将右指针往右移动一次。
  - 如果 hashmap 包含 `3` 个不同字符，将最左边的字符从 哈希表中删去，并移动左指针，以便滑动窗口只包含 `2` 个不同的字符。
  - 更新 `max_len`

## 

![](https://1-1256857171.cos.ap-beijing.myqcloud.com/linqingyang/202306061117371.gif)

## **算法实现**

### Java

```java
class Solution {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
    int n = s.length();
    if (n < 3) return n;

    // sliding window left and right pointers
    int left = 0;
    int right = 0;
    // hashmap character -> its rightmost position 
    // in the sliding window
    HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

    int max_len = 2;

    while (right < n) {
      // slidewindow contains less than 3 characters
      if (hashmap.size() < 3)
        hashmap.put(s.charAt(right), right++);

      // slidewindow contains 3 characters
      if (hashmap.size() == 3) {
        // delete the leftmost character
        int del_idx = Collections.min(hashmap.values());
        hashmap.remove(s.charAt(del_idx));
        // move left pointer of the slidewindow
        left = del_idx + 1;
      }

      max_len = Math.max(max_len, right - left);
    }
    return max_len;
  }
}
```

### Python

```python
from collections import defaultdict
class Solution:
    def lengthOfLongestSubstringTwoDistinct(self, s: 'str') -> 'int':
        n = len(s) 
        if n < 3:
            return n
        
        # sliding window left and right pointers
        left, right = 0, 0
        # hashmap character -> its rightmost position 
        # in the sliding window
        hashmap = defaultdict()

        max_len = 2
        
        while right < n:
            # slidewindow contains less than 3 characters
            if len(hashmap) < 3:
                hashmap[s[right]] = right
                right += 1

            # slidewindow contains 3 characters
            if len(hashmap) == 3:
                # delete the leftmost character
                del_idx = min(hashmap.values())
                del hashmap[s[del_idx]]
                # move left pointer of the slidewindow
                left = del_idx + 1

            max_len = max(max_len, right - left)

        return max_len
```

## **复杂度分析**

- 时间复杂度：�(�)O(*N*) 其中 `N` 是输入串的字符数目。
- 空间复杂度：�(1)O(1)，这是因为额外的空间只有 hashmap ，且它包含不超过 `3` 个元素。

## **相似问题**

相同的滑动窗口问题还可以用来解决如下问题：[《340. 至多包含 K 个不同字符的最长子串》](https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters/)