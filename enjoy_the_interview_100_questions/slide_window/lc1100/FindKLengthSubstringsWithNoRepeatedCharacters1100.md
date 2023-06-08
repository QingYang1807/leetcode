<span style="background-color: #ffa500; color: #fff; padding: 4px 8px; border-radius: 4px;">中等</span>


# 题目
长度为 K 的无重复字符子串

给你一个字符串 `S`，找出所有长度为 `K` 且不含重复字符的子串，请你返回全部满足要求的子串的 **数目**。

 

**示例 1：**

```
输入：S = "havefunonleetcode", K = 5
输出：6
解释：
这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。
```

**示例 2：**

```
输入：S = "home", K = 5
输出：0
解释：
注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
```

 

**提示：**

1. `1 <= S.length <= 10^4`
2. `S` 中的所有字符均为小写英文字母
3. `1 <= K <= 10^4`

# 解题思路

1. `k` 值检查
   - 由于字符均为小写英文字母，故当 `k > 26`时，所有子串必有重复字符。
   - 当 `k > S.length`时，不存在子串。
2. 用一个计数数组 `freq`，保存子串中字符对应的字符数
3. 同时维护一个变量 `cnt`，保存子串中的不同字符数
   - 当 `cnt == k`时，子串即满足要求。
   - 而不用遍历计数数组。

# 代码

## Java

```java
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int len = s.length();
        if (k > 26 || k > len)
            return 0;

        int[] freq = new int[123];
        int cnt = 0;
        int ans = 0;
        char[] chars = s.toCharArray();

        //初始化窗口
        for (int i = 0; i < k; i++)
            if (freq[chars[i]]++ == 0)
                cnt++;
        if (cnt == k)
            ans++;

        //固定窗口大小，向右滑动
        for (int l = 0, r = k; r < len; ) {
            if (--freq[chars[l++]] == 0)
                cnt--;
            if (freq[chars[r++]]++ == 0)
                cnt++;
            if (cnt == k)
                ans++;
        }

        return ans;
    }
}
```

## Java2

```java
class Solution {
    public int numKLenSubstrNoRepeats(String S, int K) {
        // 和无重复最长的子串类似，使用滑动窗口
        int length = S.length();

        int res = 0;
        int left = 0; 
        Map<Character, Integer> window = new HashMap<>();
        for (int right = 0; right < length; right++) {
            char ch = S.charAt(right);
            window.put(ch, window.getOrDefault(ch, 0) + 1);
            // 添加的无重复，且窗口长度为K, 则累加，并将窗口整体右移，继续判断后面的
            if (window.get(ch) == 1 && (right - left + 1 == K)) {
                res++;
                window.put(S.charAt(left), window.get(S.charAt(left)) - 1);
                left++;
                continue;
            }

            // 有重复则整体右移，直到把重复的那个给排除在外
            while (window.get(ch) > 1) {
                window.put(S.charAt(left), window.get(S.charAt(left)) - 1);
                left++;
            }

        }

        return res;
    }
}
```



