<span style="background-color: #ffa500; color: #fff; padding: 4px 8px; border-radius: 4px;">中等</span>

# 题目

给你一个字符串 `s` 和一个整数 `k` ，请你找出 **至多** 包含 *`k`* 个 **不同** 字符的最长子串，并返回该子串的长度。 

**示例 1：**

```
输入：s = "eceba", k = 2
输出：3
解释：满足题目要求的子串是 "ece" ，长度为 3 。
```

**示例 2：**

```
输入：s = "aa", k = 1
输出：2
解释：满足题目要求的子串是 "aa" ，长度为 2 。
```

**提示：**

- `1 <= s.length <= 5 * 104`
- `0 <= k <= 50`

# 方法 1：滑动窗口 + 哈希表

## **想法**

我们使用这个简单问题[至多包含两个不同字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/)的方法。

为了通过一次遍历解决这个问题，我们使用*滑动窗口*方法，使用两个指针 `left` 和 `right` 标记窗口的边界。

思路是将左右指针都设置为 `0`，然后向右移动 `right` 指针保证区间内含有不超过 `k` 个不同字符。当移动到含有 `k + 1` 个不同字符的时候，移动 `left` 指针直到区间内不含有超过 `k + 1` 个不同字符。

![340-1.png](D:\picture\markdown\LongestSubstringWithAtMostXXXDistinctCharacters\495d270a130239f3213ebc8f9369c38604ee826024db3e6ba7c59dcfca18bf0e-340-1.png)

这个算法的基本思想是：在字符串上移动滑动窗口，保证窗口内有不超过 `k` 个不同字符，同时在每一步更新最大子串长度。

> 只有一个问题需要解决：如何移动左指针保证子串中只包含 `k` 个不同字符？

我们可以使用一个哈希表，建立从字符到滑动窗口最右出现位置的映射，在任意时刻，哈希表不能包含 `k+1` 个元素。

![340-2.png](D:\picture\markdown\LongestSubstringWithAtMostXXXDistinctCharacters\74f1aeb7c3bf1fc9f6a55083e4512e928d89178577594bde96fc35e0bead2c41-340-2.png)

例如，使用哈希表可以知道字符 `O` 在 `"LOVELEE"` 窗口最右出现位置为 `1`，所以只需要将 `left` 指针移动到位置 `1 + 1 = 2` 保证将字符 `O` 排除在滑动窗口外。

## **算法**

算法实现如下：

- 如果字符串为空或者 `k` 是零的话返回 `0`。
- 设置指针为字符串开头 `left = 0` 和 `right = 0`，初始化最大子串长度 `max_len = 1`。
- 当`right`指针小于`N`时：
  - 将当前字符 `s[right]` 加入哈希表并且向右移动 `right` 指针。
  - 如果哈希表包含 `k + 1` 个不同字符，在哈希表中移去最左出现的字符，移动 `left` 指针使得滑动窗口只包含 `k` 个不同字符。
  - 更新 `max_len`。



![](D:\picture\markdown\LongestSubstringWithAtMostXXXDistinctCharacters\202306061131958.gif)

## **算法实现**

### Java

```java
class Solution {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int n = s.length();
    if (n*k == 0) return 0;

    // sliding window left and right pointers
    int left = 0;
    int right = 0;
    // hashmap character -> its rightmost position 
    // in the sliding window
    HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

    int max_len = 1;

    while (right < n) {
      // add new character and move right pointer
      hashmap.put(s.charAt(right), right++);

      // slidewindow contains 3 characters
      if (hashmap.size() == k + 1) {
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
    def lengthOfLongestSubstringKDistinct(self, s: 'str', k: 'int') -> 'int':
        n = len(s) 
        if k == 0 or n == 0:
            return 0
        
        # sliding window left and right pointers
        left, right = 0, 0
        # hashmap character -> its rightmost position 
        # in the sliding window
        hashmap = defaultdict()

        max_len = 1
        
        while right < n:
            # add new character and move right pointer
            hashmap[s[right]] = right
            right += 1

            # slidewindow contains 3 characters
            if len(hashmap) == k + 1:
                # delete the leftmost character
                del_idx = min(hashmap.values())
                del hashmap[s[del_idx]]
                # move left pointer of the slidewindow
                left = del_idx + 1

            max_len = max(max_len, right - left)

        return max_len
```

## **复杂度分析**

> 使用了[至多包含两个不同字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/)问题的解法，我们可能达到最好的时间复杂度 *O(N)*嘛？

对于最好情况，如果字符串不超过 `k` 个不同字符，答案是肯定的。因为只需要一次遍历就可以得到结果，时间复杂度是 *O(N)*。

对于最坏情况，当输入字符串包含 `n` 个不同字符，答案是不能。因为每一步都需要花费 *O(k)*时间找到哈希表中的最小值，所以总的复杂度是 *O*(*N k*)。

- 时间复杂度：最好情况下是 *O(N)*，最坏情况下是 *O(N k)*。
- 空间复杂度：哈希表的空间开销：*O(k)*。

# 方法 2：滑动窗口 + 有序字典

**如何达到 *O(N)* 时间复杂度**

方法 1 使用了标准的哈希表，不能够保证 *O(N)*的复杂度。

为了达到 *O(N)* 的效率，我们可以使用一种数据结构，保证以下四种操作都可以在 *O(1)*时间完成：

- 插入键
- 获取键 / 检查键是否存在
- 删除键
- 返回最先 / 最后插入的键值对

前三个操作通过标准的哈希表就可以实现，第四个操作使用链表可以实现。

> 使用有序字典结构，可以同时支持哈希表和链表操作，在 Python 中这个结构叫做 [*OrderedDict*](https://leetcode.cn/link/?target=https%3A%2F%2Fdocs.python.org%2F3%2Flibrary%2Fcollections.html%23collections.OrderedDict)， Java 中为 [*LinkedHashMap*](https://leetcode.cn/link/?target=https%3A%2F%2Fdocs.oracle.com%2Fjavase%2F8%2Fdocs%2Fapi%2Fjava%2Futil%2FLinkedHashMap.html)。

有序字典在面试中很常见，相关例题可以查看[LRU缓存机制](https://leetcode-cn.com/problems/lru-cache/)问题。

## **算法**

使用有序字典代替标准哈希表解决方法 1：

- 如果字符串为空或者 `k` 是零的话返回 `0`。
- 设置指针为字符串开头 `left = 0` 和 `right = 0`，初始化最大子串长度 `max_len = 1`。
- 当`right`指针小于`N`时：
  - 如果当前字符 `s[right]` 已经在有序字典中 `hashmap` 中，删除它，以保证 `hashmap` 的第一个元素是滑动窗口的最左侧元素。
  - 将 `s[right]` 加入有序字典，并右移 `right` 指针。
  - 如果有序字典 `hashmap` 包含 `k + 1` 个不同字符，在哈希表中移去最左出现的字符，移动 `left` 指针使得滑动窗口只包含 `k` 个不同字符。
  - 更新 `max_len`。

## **算法实现**

### Java

```java
class Solution {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int n = s.length();
    if (n*k == 0) return 0;

    // sliding window left and right pointers
    int left = 0;
    int right = 0;
    // hashmap character -> its rightmost position 
    // in the sliding window
    LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<Character, Integer>(k + 1);

    int max_len = 1;

    while (right < n) {
      Character character = s.charAt(right);
      // if character is already in the hashmap -
      // delete it, so that after insert it becomes
      // the rightmost element in the hashmap
      if (hashmap.containsKey(character))
        hashmap.remove(character);
      hashmap.put(character, right++);

      // slidewindow contains k + 1 characters
      if (hashmap.size() == k + 1) {
        // delete the leftmost character
        Map.Entry<Character, Integer> leftmost = hashmap.entrySet().iterator().next();
        hashmap.remove(leftmost.getKey());
        // move left pointer of the slidewindow
        left = leftmost.getValue() + 1;
      }

      max_len = Math.max(max_len, right - left);
    }
    return max_len;
  }
}
```

### Python

```python
from collections import OrderedDict
class Solution:
    def lengthOfLongestSubstringKDistinct(self, s: 'str', k: 'int') -> 'int':
        n = len(s) 
        if k == 0 or n == 0:
            return 0
        
        # sliding window left and right pointers
        left, right = 0, 0
        # hashmap character -> its rightmost position 
        # in the sliding window
        hashmap = OrderedDict()

        max_len = 1
        
        while right < n:
            character = s[right]
            # if character is already in the hashmap -
            # delete it, so that after insert it becomes
            # the rightmost element in the hashmap
            if character in hashmap:
                del hashmap[character]
            hashmap[character] = right
            right += 1

            # slidewindow contains k + 1 characters
            if len(hashmap) == k + 1:
                # delete the leftmost character
                _, del_idx = hashmap.popitem(last = False)
                # move left pointer of the slidewindow
                left = del_idx + 1

            max_len = max(max_len, right - left)

        return max_len
```

## **复杂度分析**

- 时间复杂度：*O(N)*因为有序字典的所有操作 `insert/get/delete/popitem` (`put/containsKey/remove`) 都在常数时间内完成。
- 空间复杂度：*O(k)*，有序字典的空间开销，最多保存 `k + 1` 个元素。



> 作者：LeetCode
> 链接：https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters/solutions/11309/zhi-shao-bao-han-k-ge-bu-tong-zi-fu-de-zui-chang-z/
> 来源：力扣（LeetCode）
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。