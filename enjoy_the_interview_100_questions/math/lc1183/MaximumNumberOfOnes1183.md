`1-2年` `高通`

## 题目

现在有一个尺寸为 `width * height` 的矩阵 `M`，矩阵中的每个单元格的值不是 `0` 就是 `1`。

而且矩阵 `M` 中每个大小为 `sideLength * sideLength` 的 **正方形** 子阵中，`1` 的数量不得超过 `maxOnes`。

请你设计一个算法，计算矩阵中最多可以有多少个 `1`。

**示例 1：**

```
输入：width = 3, height = 3, sideLength = 2, maxOnes = 1
输出：4
解释：
题目要求：在一个 3*3 的矩阵中，每一个 2*2 的子阵中的 1 的数目不超过 1 个。
最好的解决方案中，矩阵 M 里最多可以有 4 个 1，如下所示：
[1,0,1]
[0,0,0]
[1,0,1]
```

**示例 2：**

```
输入：width = 3, height = 3, sideLength = 2, maxOnes = 2
输出：6
解释：
[1,0,1]
[1,0,1]
[1,0,1]
```

**提示：**

- `1 <= width, height <= 100`
- `1 <= sideLength <= width, height`
- `0 <= maxOnes <= sideLength * sideLength`



## 思路

注意到，假设在某个点(i, j)上放置一个1，则可以再所有满足x % sideLength == i和y % sideLength == j的位置(x, y)放置一个1且互相之间不影响。所以只需要找出在第一个边长为sideLength正方形内的哪些位置放置1能使得整个矩形内的1最多即可。遍历第一个边长为sideLength正方形内的每个点，找出前maxOnes个能使得在矩阵内放尽可能多的点即可。

## 代码

### Java

```java
import java.util.PriorityQueue;

class Solution {
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int ans = 0;  // 存储结果的变量
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 优先队列，用于存储当前区域中1的数量
        for (int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                // 计算当前区域中1的数量，并添加到优先队列中
                pq.add(((width - 1 - i) / sideLength + 1) * ((height - 1 - j) / sideLength + 1));
                if (pq.size() > maxOnes) {
                    pq.poll();  // 如果优先队列中的元素数量超过maxOnes，则移除数量最小的元素
                }
            }
        }
        while (!pq.isEmpty()) {
            ans += pq.poll();  // 将优先队列中的元素累加到结果中
        }
        return ans;  // 返回最终的结果
    }
}
```

> 作者：Gaaakki
> 链接：https://leetcode.cn/problems/maximum-number-of-ones/solutions/41902/java-osidelength2-by-gaaakki/
> 来源：力扣（LeetCode）
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。