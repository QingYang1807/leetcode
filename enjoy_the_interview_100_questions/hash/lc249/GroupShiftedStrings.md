<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>

# 题目

给定一个字符串，对该字符串可以进行 “移位” 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如：`"abc" -> "bcd"`。这样，我们可以持续进行 “移位” 操作，从而生成如下移位序列：

```
"abc" -> "bcd" -> ... -> "xyz"
```

给定一个包含仅小写字母字符串的列表，将该列表中所有满足 “移位” 操作规律的组合进行分组并返回。

 

**示例：**

```
输入：["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
输出：
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
解释：可以认为字母表首尾相接，所以 'z' 的后续为 'a'，所以 ["az","ba"] 也满足 “移位” 操作规律。
```

# 思路

因为移动一位，所以一个单词字母之间的差和之前的保持一样，可以计算后归入同一个字典的key中

## 解题方法

1.定义函数`word_to_number(self, word)`将字母转换为数字字符串，注意模26保证循环

2.遍历列表strings,对每一个单词，将其转化为数字字符串并加入相应的key对应的列表中

3.遍历结束后，将字典存储的值列表化并返回结果

## 复杂度

- 时间复杂度: *O*(*n*)

- 空间复杂度: *O*(*n*)

## 代码

### Java

```java
// 将单词转化为由逗号分隔的字符串，每个单词对应字母之间的差值
    public String wordToNumber(String word) {
        StringBuilder res = new StringBuilder();
        if (word.length() == 1) {
            return "";
        }
        for (int i = 0; i < word.length() - 1; i++) {
            int num = (word.charAt(i + 1) - word.charAt(i)) % 26;
            res.append(",").append(num);
        }
        return res.toString();
    }

    // 对一组字符串进行分类，相同偏移量的字符串放在同一个列表中
    public List<List<String>> groupStrings(List<String> strings) {
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String word : strings) {
            String key = wordToNumber(word);
            if (!groupMap.containsKey(key)) {
                groupMap.put(key, new ArrayList<>());
            }
            groupMap.get(key).add(word);
        }
        return new ArrayList<>(groupMap.values());
    }
```



### Python

```
class Solution:
    def word_to_number(self, word):
        res = ''
        if len(word) == 1:
            return ''
        for i in range(len(word) - 1):
            num = (ord(word[i + 1]) - ord(word[i]))%26
            res += ',' + str(num)
        return res

    def groupStrings(self, strings: list[str]) -> list[list[str]]:
        group_dict = defaultdict(list)
        for word in strings:
            group_dict[self.word_to_number(word)].append(word)
        return list(group_dict.values())
```
