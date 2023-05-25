`1~2年` `virtu` `微软 Microsoft` `英伟达 NVIDIA` `彭博 Bloomberg` `亚马逊` `字节跳动`

## 题目

给你一个字符串 `s`，返回 *只含 **单一字母** 的子串个数* 。



**示例 1：**

```
输入： s = "aaaba"
输出： 8
解释： 只含单一字母的子串分别是 "aaa"， "aa"， "a"， "b"。
"aaa" 出现 1 次。
"aa" 出现 2 次。
"a" 出现 4 次。
"b" 出现 1 次。
所以答案是 1 + 2 + 4 + 1 = 8。
```

**示例 2:**

```
输入： s = "aaaaaaaaaa"
输出： 55
```

**提示：**

- `1 <= s.length <= 1000`
- `s[i]` 仅由小写英文字母组成

## 方法一：分组计算

### 思路

本题要求只含单一字母的子串个数，首先我们可以计算每一个最长只含单一字母的连续子串。比如 `s = "aaabb"`，一共有两个符合要求的子串，分别为 aaa 和 bb。

然后考虑对于一个字符串，共有多少不同的子串。我们可以对字符串进行分组计算，比如 `aaa` 共有 6 个子串。`a` 3 个，`aa` 2 个，`aaa` 1 个。转化为通用公式就是 `n * (n + 1) / 2`，其中 `n` 为连续子串的长度。

### 算法

遍历字符串 `s`， 计算每个最长只含单一字母的连续子串的长度 `count`，每个最长连续子串可以分成 `count * (count + 1) / 2` 个不同的子串，将其累加到结果中。

### 代码

#### Golang

```go
func countLetters(s string) int {
    var pre byte
    ans, count := 0, 0
    for i := 0; i < len(s); i++ {
        if  s[i] != pre {
            ans += count * (count + 1) / 2
            count = 1
            pre = s[i]
        } else {
            count++
        }
    }
    ans += count * (count + 1) / 2
    return ans
}
```

#### C

```c
int countLetters(char* s) {
    int n = strlen(s);
    int ans = 0;
    int count = 1;
    for (int i = 1; i < n; i++) {
        if (s[i-1] == s[i])
            count++;
        else {
            ans += (count * (count + 1) / 2);
            count = 1;
        }
    }
    ans += (count * (count + 1) / 2);
    return ans;
}
```

#### Java

```java
class Solution {
    public int countLetters(String s) {
        int ans = 0;
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                ans += count * (count + 1) / 2;
                count = 1;
            }
        }
        ans += count * (count + 1) / 2;
        return ans;
    }
}
```

#### 复杂度分析

- 时间复杂度：*O(n)*，遍历一次字符串。其中 *n* 为字符串 `s` 的长度。
- 空间复杂度：*O(1)*，没有使用额外的空间。

> 作者：力扣官方题解
> 链接：https://leetcode.cn/problems/count-substrings-with-only-one-distinct-letter/solutions/199690/tong-ji-zhi-han-dan-yi-zi-mu-de-zi-chuan-by-leetco/
> 来源：力扣（LeetCode）
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



