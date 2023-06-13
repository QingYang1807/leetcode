<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>


# 题目

我们可以将一个句子表示为一个单词数组，例如，句子 `"I am happy with leetcode"` 可以表示为 `arr = ["I","am",happy","with","leetcode"]`

给定两个句子 `sentence1` 和 `sentence2` 分别表示为一个字符串数组，并给定一个字符串对 `similarPairs` ，其中 `similarPairs[i] = [xi, yi]` 表示两个单词 `xi` and `yi` 是相似的。

如果 `sentence1` 和 `sentence2` 相似则返回 `true` ，如果不相似则返回 `false` 。

两个句子是相似的，如果:

- 它们具有 **相同的长度** (即相同的字数)
- `sentence1[i]` 和 `sentence2[i]` 是相似的

请注意，一个词总是与它自己相似，也请注意，相似关系是不可传递的。例如，如果单词 `a` 和 `b` 是相似的，单词 `b` 和 `c` 也是相似的，那么 `a` 和 `c` **不一定相似** 。

 

**示例 1:**

```
输入: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","fine"],["drama","acting"],["skills","talent"]]
输出: true
解释: 这两个句子长度相同，每个单词都相似。
```

**示例 2:**

```
输入: sentence1 = ["great"], sentence2 = ["great"], similarPairs = []
输出: true
解释: 一个单词和它本身相似。
```

**示例 3:**

```
输入: sentence1 = ["great"], sentence2 = ["doubleplus","good"], similarPairs = [["great","doubleplus"]]
输出: false
解释: 因为它们长度不同，所以返回false。
```

 

**提示:**

- `1 <= sentence1.length, sentence2.length <= 1000`
- `1 <= sentence1[i].length, sentence2[i].length <= 20`
- `sentence1[i]` 和 `sentence2[i]` 只包含大小写英文字母
- `0 <= similarPairs.length <= 2000`
- `similarPairs[i].length == 2`
- `1 <= xi.length, yi.length <= 20`
- 所有对 `(xi, yi)` 都是 **不同** 的

# 方法：Set [Accepted]

**算法：**

- 判断 `words1[i]` 和 `words2[i]` 是否相似，可以是同一个单词，或者 `(words1[i], words2[i])` 或 `(words2[i], words1[i])` 在相似单词对 `pairs` 中。
- 为了检查 `(words1[i], words2[i])` 在相似单词对中 `pairs` 是否存在，我们可以将所有单词对放入集合 `Set` 结构中。

## Python

```py
# 定义一个解决方案类
class Solution(object):
    # 定义一个方法，输入两个句子（单词列表）和一个单词对列表，返回两个句子是否相似
    def areSentencesSimilar(self, words1, words2, pairs):
        # 如果两个句子的长度（单词数量）不相等，直接返回 False
        if len(words1) != len(words2): return False

        # 将单词对列表转换为元组，并存入一个集合中，用于后续的查找操作
        # 在 Python 中，集合的查找操作比列表快，所以这里用集合来存储单词对
        pairset = set(map(tuple, pairs))

        # 使用 all 函数和生成器表达式，对两个句子的每一个对应位置的单词进行检查
        # 如果所有的单词对都满足以下条件之一，则返回 True：
        #   1) 两个单词相等
        #   2) 单词对存在于 pairset 中（注意考虑顺序）
        # 否则，返回 False
        return all(w1 == w2 or (w1, w2) in pairset or (w2, w1) in pairset
                   for w1, w2 in zip(words1, words2))

```

## Java

```java
// 定义一个解决方案类
class Solution {
    // 定义一个方法，输入两个句子（字符串数组）和一个单词对数组，返回两个句子是否相似
    public boolean areSentencesSimilar(
            String[] words1, String[] words2, String[][] pairs) {
        // 如果两个句子的长度（单词数量）不相等，直接返回 false
        if (words1.length != words2.length) return false;

        // 创建一个集合来存储单词对，将每对单词通过 "#" 连接为一个字符串
        // 在 Java 中，HashSet 的查找操作比数组快，所以这里用 HashSet 来存储单词对
        Set<String> pairset = new HashSet();
        for (String[] pair: pairs)
            pairset.add(pair[0] + "#" + pair[1]);

        // 遍历两个句子的每一个对应位置的单词
        for (int i = 0; i < words1.length; ++i) {
            // 如果两个单词不相等，并且无法在 pairset 中找到对应的单词对（注意考虑顺序），则返回 false
            if (!words1[i].equals(words2[i]) &&
                    !pairset.contains(words1[i] + "#" + words2[i]) &&
                    !pairset.contains(words2[i] + "#" + words1[i]))
                return false;
        }
        // 如果所有的单词对都满足相似的条件，则返回 true
        return true;
    }
}

```

**复杂度分析**

- 时间复杂度：O(N+P)。其中 N 是 `words1` 的长度和 `words2` 的长度的最大值，P 单词对的长度。
- 空间复杂度：O(P)，集合 `Set` 中需要存放 P 个单词对。
