`1-2年` `谷歌 Google` `亚马逊` `雅虎 Yahoo`

## 题目

给定一个整数数组 `nums`，且 `nums` 中的所有整数都为 **0** 或 **1**。你不能直接访问这个数组，你需要使用 API `ArrayReader` ，该 API 含有下列成员函数：

- `int query(int a, int b, int c, int d)`：其中 `0 <= a < b < c < d < ArrayReader.length()` 。此函数查询以这四个参数为下标的元素并返回：

  - **4** : 当这四个元素相同（0 或 1）时。

  - **2** : 当其中三个元素的值等于 0 且一个元素等于 1 时，或当其中三个元素的值等于 1 且一个元素等于 0 时。

  - **0** : 当其中两个元素等于 0 且两个元素等于 1 时。

- `int length()`：返回数组的长度。

你可以调用 `query()` 最多 **2 \* n 次**，其中 n 等于 `ArrayReader.length()`。

返回 `nums` 中出现次数最多的值的**任意**索引，若所有的值出现次数均相同，返回 -1。



**示例 1：**

```
输入: nums = [0,0,1,0,1,1,1,1]
输出: 5
解释: API 的调用情况如下：
reader.length() // 返回 8，因为隐藏数组中有 8 个元素。
reader.query(0,1,2,3) // 返回 2，查询元素 nums[0], nums[1], nums[2], nums[3] 间的比较。
// 三个元素等于 0 且一个元素等于 1 或出现相反情况。
reader.query(4,5,6,7) // 返回 4，因为 nums[4], nums[5], nums[6], nums[7] 有相同值。
我们可以推断，最常出现的值在最后 4 个元素中。
索引 2, 4, 6, 7 也是正确答案。
```

**示例 2:**

```
输入: nums = [0,0,1,1,0]
输出: 0
```

**示例 3:**

```
输入: nums = [1,0,1,0,1,0,1,0]
输出: -1
```

**提示:**

- `5 <= nums.length <= 10^5`
- `0 <= nums[i] <= 1`

 

**进阶：**要找到出现次数最多的元素，需要至少调用 `query()` 多少次？

## 思路/算法

### 方法一：暴力使用 API

给定的 API 最简单的一种使用方法是：

> 我们可以使用两次 API 得到任意两个位置的数是否相等。具体地，如果我们希望得到位置 p 和 q 的数是否相等，我们只要查询 p, x, y, z 和 q, x, y, z，这两次查询结果相等当且仅当位置 p 和 q 的数相等，其中 x, y, z 是不等于 p, q 的任意三个位置。

因此我们可以固定位置 p = 0，然后使用 2(n - 1) 次查询分别得到位置 q = 1, 2, 3, ..., n - 1 和位置 p 的数是否相等。这样我们就还原出了整个数组。

#### 代码

##### C++

```c++
class Solution {
public:
    int guessMajority(ArrayReader &reader) {
        int n = reader.length();
        vector<int> v(n);
        v[0] = 1;

        auto checkIfSame = [&](int p, int q) {
            vector<int> chooseP = {p};
            vector<int> chooseQ = {q};
            for (int i = 0; i < n; ++i) {
                if (i != p && i != q) {
                    chooseP.push_back(i);
                    chooseQ.push_back(i);
                    if (chooseP.size() == 4) {
                        break;
                    }
                }
            }
            sort(chooseP.begin(), chooseP.end());
            sort(chooseQ.begin(), chooseQ.end());
            return reader.query(chooseP[0], chooseP[1], chooseP[2], chooseP[3]) == \
                   reader.query(chooseQ[0], chooseQ[1], chooseQ[2], chooseQ[3]);
        };

        auto find = [&](int num) {
            int idx = -1;
            for (int i = 0; i < n; ++i) {
                if (v[i] == num) {
                    idx = i;
                    break;
                }
            }
            return idx;
        };

        for (int i = 1; i < n; ++i) {
            v[i] = checkIfSame(0, i);
        }
        
        int sum = accumulate(v.begin(), v.end(), 0);
        if (sum * 2 == n) {
            return -1;
        }
        return sum * 2 < n ? find(0) : find(1);
    }
};
```

### 方法二：有技巧地使用 API

我们可以发现，如果得到了位置 0, 1, 2, 3, 4 的数，接下来要想得到位置 i (i > 4) 的数，我们只要查询 i - 4, i - 3, i - 2, i - 1 以及 i - 3, i - 2, i - 1, i 即可得到位置 i 和位置 i - 3 的数是否相等。由于位置 i - 3 的数已知，并且相邻两个位置的查询是共用的。而位置 0, 1, 2, 3, 4 的数也可以通过一些技巧快速得到，总共只需要 n + 1 次查询，可以参考下面的代码。

#### 代码

##### C++

```c++
class Solution {
public:
    int guessMajority(ArrayReader &reader) {
        int n = reader.length();
        vector<int> v(n);
        v[0] = 1;

        auto find = [&](int num) {
            int idx = -1;
            for (int i = 0; i < n; ++i) {
                if (v[i] == num) {
                    idx = i;
                    break;
                }
            }
            return idx;
        };

        int q0123 = reader.query(0, 1, 2, 3);
        int q0124 = reader.query(0, 1, 2, 4);
        int q0134 = reader.query(0, 1, 3, 4);
        int q0234 = reader.query(0, 2, 3, 4);
        int q1234 = reader.query(1, 2, 3, 4);
        v[1] = (q0234 == q1234);
        v[2] = (q0134 == q1234);
        v[3] = (q0124 == q1234);
        v[4] = (q0123 == q1234);

        int prev = q1234;
        for (int i = 5; i < n; ++i) {
            int curr = reader.query(i - 3, i - 2, i - 1, i);
            v[i] = (prev == curr ? v[i - 4] : 1 - v[i - 4]);
            prev = curr;
        }

        int sum = accumulate(v.begin(), v.end(), 0);
        if (sum * 2 == n) {
            return -1;
        }
        return sum * 2 < n ? find(0) : find(1);
    }
};
```

### 方法三：只查询必要的信息

实际上我们并没有必要复原整个数组，如果我们知道位置 p 和 q 的数相等（假设均为 1），那么我们可以一次性查询两个新的数 x, y：

如果查询结果为 0，说明 x, y 均为 0；
如果查询结果为 2，说明 x, y 其中一个为 0，另一个为 1；
如果查询结果为 4，说明 x, y 均为 1。
这样我们虽然不能复原数组，但仍然可以求出数组中 0 和 1 的个数。如果最后还剩一个数，那么额外加两次查询判断其与位置 0 的数是否相等即可。

那么如何找出 p 和 q 呢？根据鸽巢原理，前三个数中一定有两个数是相等的，因此我们可以使用之前判断两个数是否相等的方法找出 p 和 q。

具体见下面的代码。总查询次数约为 n/2。

#### 代码

##### C++

```c++
class Solution {
public:
    int guessMajority(ArrayReader &reader) {
        // nums[0] = 0
        int cnt0 = 1, cnt1 = 0;
        int q0234 = reader.query(0, 2, 3, 4);
        int q1234 = reader.query(1, 2, 3, 4);
        // 从 start 开始每两个数一查
        int start, p, q;
        // 记录任意一个 0/1 的位置
        int where0 = -1, where1 = -1;
        if (q0234 == q1234) {
            // nums[1] = 0
            ++cnt0;
            start = 2;
            p = 0;
            q = 1;
            where0 = 0;
        }
        else {
            // nums[1] = 1
            ++cnt1;
            int q0134 = reader.query(0, 1, 3, 4);
            if (q0134 == q1234) {
                // nums[2] = 0
                ++cnt0;
                start = 3;
                p = 0;
                q = 2;
                where0 = 0;
                where1 = 1;
            }
            else {
                // nums[2] = 1，为了方便后续编码可以交换一下
                // 即 nums[0] = 1, nums[1] = nums[2] = 0
                cnt0 = 2;
                cnt1 = 1;
                start = 3;
                p = 1;
                q = 2;
                where0 = 1;
                where1 = 0;
            }
        }

        // 目前为止，0 的个数多于 1 的个数，如果在接下来的查找中没有出现两个 1 的情况（即返回值为 0），那么 1 的个数一定小于等于 0
        // 因此在出现了两个 1 的时候再记录 1 的位置也不迟
        int n = reader.length();
        int i, curr;
        for (i = start; i + 1 < n; i += 2) {
            curr = reader.query(p, q, i, i + 1);
            if (curr == 0) {
                cnt1 += 2;
                if (where1 == -1) {
                    where1 = i;
                }
            }
            else if (curr == 2) {
                ++cnt0;
                ++cnt1;
            }
            else {
                cnt0 += 2;
            }
        }

        if (i != n) {
            int q0123 = reader.query(0, 1, 2, 3);
            int extra = reader.query(1, 2, 3, n - 1);
            int status = (q0123 == extra) ^ (p == 0);
            if (status == 0) {
                ++cnt0;
            }
            else {
                ++cnt1;
            }
        }

        if (cnt0 == cnt1) {
            return -1;
        }
        return cnt0 < cnt1 ? where1 : where0;
    }
};
```

> 作者：zerotrac 🌸
> 链接：https://leetcode.cn/problems/guess-the-majority-in-a-hidden-array/solutions/363747/san-chong-fang-fa-you-yi-dao-nan-by-zerotrac2/
> 来源：力扣（LeetCode）
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。