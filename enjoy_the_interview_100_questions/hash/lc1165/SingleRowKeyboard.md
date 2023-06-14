<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>

# 题目

我们定制了一款特殊的键盘，所有的键都 **排列在一行上** 。

给定一个长度为 `26` 的字符串 `keyboard` ，来表示键盘的布局(索引从 `0` 到 `25` )。一开始，你的手指在索引 `0` 处。要输入一个字符，你必须把你的手指移动到所需字符的索引处。手指从索引 `i` 移动到索引 `j` 所需要的时间是 `|i - j|`。

您需要输入一个字符串 `word` 。写一个函数来计算用一个手指输入需要多少时间。

 

**示例 1：**

```
输入：keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
输出：4
解释：从 0 号键移动到 2 号键来输出 'c'，又移动到 1 号键来输出 'b'，接着移动到 0 号键来输出 'a'。
总用时 = 2 + 1 + 1 = 4. 
```

**示例 2：**

```
输入：keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
输出：73
```

 

**提示：**

- `keyboard.length == 26`
- `keyboard` 按某种特定顺序排列，并包含每个小写英文字母一次。
- `1 <= word.length <= 104`
- `word[i]` 为小写英文字母



# 方法一：暴力法

## **思路**

记录机械手上一个位置 `pre`（初始为 0）。遍历字符串 `word`，对于每一个字符 `word[i]`，再遍历 `keyboard` 找到对应的下标。对应下标和上一个位置 `pre` 的差就是移动到当前字符的时间，同样的方法计算所有字符的移动时间并累加即可。

## **代码**

### Golang

```go
func calculateTime(keyboard string, word string) int {
    sum, pre := 0, 0 
    for i := 0; i < len(word); i++ {
        for j := 0; j < 26; j++ {
            if keyboard[j] == word[i] {
                sum += abs(j - pre) // 计算移动到当前字符的时间
                pre = j // 保存上一个位置
                break
            }
        }
    }
    return sum
}

func abs(x int) int {
    if x < 0 {
        return -1 * x
    }
    return x
}
```

### C++

```cpp
class Solution {
public:
    int calculateTime(string keyboard, string word) {
        int sum = 0, pre = 0;
        for (int i = 0; i < word.size(); ++i) {
            for (int j = 0; j < 26; ++j) {
                if (keyboard[j] == word[i]) {
                    sum += abs(j - pre);
                    pre = j;
                    break;
                }
            }
        }
        return sum;
    }
};
```

### Java

```java
class Solution {
    // 定义一个方法，输入键盘布局和单词，返回敲打单词所需要的时间
    public int calculateTime(String keyboard, String word) {
        // 初始化总时间和上一个字母的位置
        int sum = 0, pre = 0;
        // 遍历单词的每一个字母
        for (int i = 0; i < word.length(); ++i) {
            // 在键盘布局上找到对应的字母
            for (int j = 0; j < 26; ++j) {
                // 如果键盘布局的当前位置的字母等于单词的当前字母
                if (keyboard.charAt(j) == word.charAt(i)) {
                    // 计算当前字母与上一个字母的位置差，累加到总时间上
                    sum += Math.abs(j - pre);
                    // 更新上一个字母的位置
                    pre = j;
                    // 找到字母后就可以结束内部循环
                    break;
                }
            }
        }
        // 返回总时间
        return sum;
    }
}
```

## **复杂度分析**

- 时间复杂度：O(n)，其中 n 为字符串 `word` 的长度，外层循环遍历一次 `word`。内层循环遍历 `keyboard`，长度固定为 26。
- 空间复杂度：O(1)，没有使用额外的空间。

# 方法二：哈希表

## **思路**

方法一每次都暴力查找当前字符的下标，当 `keyboard` 长度很长的时候时间复杂度会很高，我们可以使用哈希表，保存所有字符的下标。在遍历 `word` 的时候直接通过哈希表查找当前字符的下标。

## **代码**

### Golang

```go
func calculateTime(keyboard string, word string) int {
    m := map[byte]int{}
    // 构建哈希表，记录每个字符的下标
    for i := 0; i < len(keyboard); i++ {
        m[keyboard[i]] = i
    }
    sum, pre := 0, 0
    for i := 0; i < len(word); i++ {
        sum += abs(m[word[i]] - pre)
        pre = m[word[i]]
    }
    return sum
}

func abs(x int) int {
    if x < 0 {
        return -1 * x
    }
    return x
}
```

### C++

```cpp
class Solution {
public:
    int calculateTime(string keyboard, string word) {
        unordered_map <char, int> m;
        for (unsigned i = 0; i < keyboard.size(); ++i) {
            m[keyboard[i]] = i;
        }
        int sum = 0, pre = 0;
        for (int i = 0; i < word.size(); ++i) {
            sum += abs(m[word[i]] - pre);
            pre = m[word[i]];
        }
        return sum;
    }
};
```

### Java

```java
public int calculateTime2(String keyboard, String word) {
    // 创建一个HashMap来存储键盘布局中每个字母的位置
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < keyboard.length(); ++i) {
        // 将键盘布局中的字母及其位置添加到map中
        map.put(keyboard.charAt(i), i);
    }
    // 初始化总时间和上一个字母的位置
    int sum = 0, pre = 0;
    // 遍历单词的每一个字母
    for (int i = 0; i < word.length(); ++i) {
        // 从map中获取当前字母的位置，并计算与上一个字母的位置差，累加到总时间上
        sum += Math.abs(map.get(word.charAt(i)) - pre);
        // 更新上一个字母的位置为当前字母的位置
        pre = map.get(word.charAt(i));
    }
    // 返回总时间
    return sum;
}
```

## **复杂度分析**

- 时间复杂度：O(n)，其中 n 为字符串 `word` 的长度，遍历一次 `word` 计算移动时间。构建哈希表遍历一次 `keyboard`，长度固定为 26。
- 空间复杂度：O(1)，哈希表的大小为 `keyboard` 的长度，固定为 26。
