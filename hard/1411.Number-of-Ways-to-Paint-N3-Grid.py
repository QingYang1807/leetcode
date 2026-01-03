# 你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿 三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。

# 给你网格图的行数 n 。

# 请你返回给 grid 涂色的方案数。由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
 

# 示例 1：

# 输入：n = 1
# 输出：12
# 解释：总共有 12 种可行的方法：

# 示例 2：

# 输入：n = 2
# 输出：54
# 示例 3：

# 输入：n = 3
# 输出：246
# 示例 4：

# 输入：n = 7
# 输出：106494
# 示例 5：

# 输入：n = 5000
# 输出：30228214


class Solution(object):
    def numOfWays(self, n):
        """
        :type n: int
        :rtype: int
        """
        mod = 10**9 + 7
        # 预处理出所有满足条件的 type
        types = list()
        for i in range(3):
            for j in range(3):
                for k in range(3):
                    if i != j and j != k:
                        # 只要相邻的颜色不相同就行
                        # 将其以十进制的形式存储

                        # why i * 9 + j * 3 + k:
                        # i * 9: 第一个位置有 9 种颜色
                        # j * 3: 第二个位置有 3 种颜色
                        # k: 第三个位置有 3 种颜色
                        # 所以 i * 9 + j * 3 + k 表示所有可能的颜色组合
                        types.append(i * 9 + j * 3 + k)
        type_cnt = len(types)
        # 预处理出所有可以作为相邻行的 type 对
        related = [[0] * type_cnt for _ in range(type_cnt)]
        for i, ti in enumerate(types):
            # 得到 types[i] 三个位置的颜色
            x1, x2, x3 = ti // 9, ti // 3 % 3, ti % 3
            for j, tj in enumerate(types):
                # 得到 types[j] 三个位置的颜色
                y1, y2, y3 = tj // 9, tj // 3 % 3, tj % 3
                # 对应位置不同色，才能作为相邻的行
                if x1 != y1 and x2 != y2 and x3 != y3:
                    related[i][j] = 1

        # 递推数组
        f = [[0] * type_cnt for _ in range(n + 1)]
        # 边界情况，第一行可以使用任何 type
        f[1] = [1] * type_cnt
        for i in range(2, n + 1):
            for j in range(type_cnt):
                for k in range(type_cnt):
                    # f[i][j] 等于所有 f[i - 1][k] 的和
                    # 其中 k 和 j 可以作为相邻的行
                    if related[k][j]:
                        f[i][j] += f[i - 1][k]
                        f[i][j] %= mod
        # 最终所有的 f[n][...] 之和即为答案
        ans = sum(f[n]) % mod
        return ans


if __name__ == "__main__":
    solution = Solution()
    print(solution.numOfWays(1))
    print(solution.numOfWays(2))
    print(solution.numOfWays(3))
    print(solution.numOfWays(7))
    print(solution.numOfWays(5000))
