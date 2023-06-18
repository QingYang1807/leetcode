<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>

# 题目

最大唯一数

给你一个整数数组 `A`，请找出并返回在该数组中仅出现一次的最大整数。

如果不存在这个只出现一次的整数，则返回 -1。

 

**示例 1：**

```
输入：[5,7,3,9,4,9,8,3,1]
输出：8
解释： 
数组中最大的整数是 9，但它在数组中重复出现了。而第二大的整数是 8，它只出现了一次，所以答案是 8。
```

**示例 2：**

```
输入：[9,9,8,8]
输出：-1
解释： 
数组中不存在仅出现一次的整数。
```

 

**提示：**

1. `1 <= A.length <= 2000`
2. `0 <= A[i] <= 1000`



# 方法一：哈希表

## 思路

根据题意找到两个需要解决的重点：

1. **出现一次**
2. **最大数**

我们只需要使用哈希表统计所有数字出现的次数，然后遍历哈希表，找出所有只出现一次的数字中最大的数字就能解决上面的两个问题。

## 代码

### Java

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestUniqueNumber(int[] A) {
        // 创建一个哈希映射来存储每个数字及其出现的次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : A) { // 遍历整个数组
            // 对于数组中的每个数字，如果它在哈希映射中存在，就将其计数加一，否则将其添加到哈希映射中并设置计数为一
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int max = -1; // 初始化最大的唯一数字为-1
        for (int num : countMap.keySet()) { // 遍历哈希映射的所有键（也就是数组中的所有数字）
            // 如果一个数字只出现了一次，并且这个数字比当前的最大唯一数字要大，就更新最大唯一数字
            if (countMap.get(num) == 1 && num > max) {
                max = num;
            }
        }
        return max; // 返回最大的唯一数字
    }
}
```



### go

```go
func largestUniqueNumber(A []int) int {
    m := make(map[int]int)
    for i := 0; i < len(A); i++ {
        m[A[i]]++
    }
    max := -1
    for k, v := range m {
        if v == 1 && k > max {
            max = k
        }
    }
    return max
}
```

### C++

```c++
class Solution {
public:
    int largestUniqueNumber(vector<int>& A) {
        unordered_map<int, int> m;
        for (int num: A) {
            ++m[num];
        }

        int mx = -1;
        for (auto& [k, v]: m) {
            if (v == 1 && k > mx) {
                mx = k;
            }
        }
        return mx;
    }
};
```

### Python

```cpp
class Solution:
    def largestUniqueNumber(self, A: List[int]) -> int:
        m = collections.defaultdict(int)
        for num in A:
            m[num] += 1
        
        mx = -1
        for k, v in m.items():
            if v == 1 and k > mx:
                mx = k
        return mx
```

#### 复杂度分析

- 时间复杂度：O(n)，一次遍历数组 `A`，再一次遍历哈希表，哈希表中 `key` 的个数为数组中不重复数字的个数，最大为 `len(A)`。其中 n 组 `A` 的长度。
- 空间复杂度：O(n)，哈希表的大小为数组中不重复数字的个数，最大为 `len(A)`。其中 n 为数组 `A` 的长度。

# 方法二：计数排序

## 思路

方法一在 `A[i]` 是任意值时都可以完美解决，不过本题给了一个提示：`0 <= A[i] <= 1000`。这个提示告诉我们 `A[i]` 的范围很小，那么我们完全可以用计数排序一次遍历统计数字个数并排好序。计数排序和哈希表类似，不同之处是所有值的位置已经按照顺序排好，只需要在特定的位置计数即可。排完序后再从尾到头找到第一个只出现一次的数字即可返回，速度相对于使用哈希表更快，但是会牺牲空间。

小贴士： 计数排序是一个非基于比较的排序算法。它的优势在于在对一定范围内的整数排序时，它的复杂度为 O(n+k)Ο(n+k)O(*n*+*k*)（其中 kk*k* 是整数的范围），快于任何比较排序算法。

## 代码

### Golang

```go
func largestUniqueNumber(A []int) int {
    r := [1001]int{}
    for i := 0; i < len(A); i++ {
        r[A[i]]++
    }
    for i := 1000; i >= 0; i-- {
        if r[i] == 1 {
            return i
        }
    }
    return -1
}
```

### Java

```java
class Solution {
    public int largestUniqueNumber(int[] A) {
        // 初始化一个长度为1001的数组r，数组的索引代表数字，数组的值代表该数字在输入数组A中出现的次数
        int[] r = new int[1001];
        // 遍历输入数组A
        for (int i = 0; i < A.length; i++) {
            // 增加数组r中对应数字的计数
            r[A[i]]++;
        }
        // 从大到小遍历数组r
        for(int i = 1000; i >= 0; i--) {
            // 如果某个数字在输入数组A中只出现了一次
            if (r[i] == 1) {
                // 返回该数字，因为这是在数组A中只出现一次的最大整数
                return i;
            }
        }
        // 如果没有在数组A中只出现一次的整数，返回-1
        return -1;
    }
}

```

### C++

```c++
class Solution {
public:
    int largestUniqueNumber(vector<int>& A) {
        vector<int> r(1001);
        for (int num: A) {
            ++r[num];
        }
        for (int i = 1000; i >= 0; --i) {
            if (r[i] == 1) {
                return i;
            }
        }
        return -1;
    }
};
```

### Python

```python
class Solution:
    def largestUniqueNumber(self, A: List[int]) -> int:
        r = [0] * 1001
        for num in A:
            r[num] += 1
        for i in range(1000, -1, -1):
            if r[i] == 1:
                return i
        return -1
```

## 复杂度分析

- 时间复杂度：O(n+c)，一次遍历数组 `A`，再从尾到头遍历新的数组，其中 n 为数组 `A` 的长度，c 表示数据范围的长度，在本题中不超过 `1000`。
- 空间复杂度：O(c)，使用的空间与数据范围的长度成正比，在本题中为 c 不超过 `1000`。
