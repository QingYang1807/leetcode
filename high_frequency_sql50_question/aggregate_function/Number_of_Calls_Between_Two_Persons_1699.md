# 1699 两人之间的通话次数

<span style="background-color: #ffa500; color: #fff; padding: 4px 8px; border-radius: 4px;">中等</span>


[地址](https://leetcode.cn/problems/number-of-calls-between-two-persons/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schema
```sql
Create table If Not Exists Calls (from_id int, to_id int, duration int)
Truncate table Calls
insert into Calls (from_id, to_id, duration) values ('1', '2', '59')
insert into Calls (from_id, to_id, duration) values ('2', '1', '11')
insert into Calls (from_id, to_id, duration) values ('1', '3', '20')
insert into Calls (from_id, to_id, duration) values ('3', '4', '100')
insert into Calls (from_id, to_id, duration) values ('3', '4', '200')
insert into Calls (from_id, to_id, duration) values ('3', '4', '200')
insert into Calls (from_id, to_id, duration) values ('4', '3', '499')
```

表： `Calls`

```
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| from_id     | int     |
| to_id       | int     |
| duration    | int     |
+-------------+---------+
该表没有主键，可能存在重复项。
该表包含 from_id 与 to_id 间的一次电话的时长。
from_id != to_id
```

 

编写 SQL 语句，查询每一对用户 `(person1, person2)` 之间的通话次数和通话总时长，其中 `person1 < person2` 。

以 **任意顺序** 返回结果表。

查询结果格式如下示例所示。

 

**示例 1：**

```
输入：
Calls 表：
+---------+-------+----------+
| from_id | to_id | duration |
+---------+-------+----------+
| 1       | 2     | 59       |
| 2       | 1     | 11       |
| 1       | 3     | 20       |
| 3       | 4     | 100      |
| 3       | 4     | 200      |
| 3       | 4     | 200      |
| 4       | 3     | 499      |
+---------+-------+----------+
输出：
+---------+---------+------------+----------------+
| person1 | person2 | call_count | total_duration |
+---------+---------+------------+----------------+
| 1       | 2       | 2          | 70             |
| 1       | 3       | 1          | 20             |
| 3       | 4       | 4          | 999            |
+---------+---------+------------+----------------+
解释：
用户 1 和 2 打过 2 次电话，总时长为 70 (59 + 11)。
用户 1 和 3 打过 1 次电话，总时长为 20。
用户 3 和 4 打过 4 次电话，总时长为 999 (100 + 200 + 200 + 499)。
```



## 方法一

这里拐了一个弯儿，需要将反过来的通话调转回去，然后就可以按照正常的分组，然后组内计算统计即可，具体做法：

使用IF 语句判断from_id/to_id的大小，第一个IF找到两个值中比较小的一个，第二个IF找到两个值中比较大的一个即可；
处理好的字段，按照维度字段 person1 person2 进行分组，然后组内统计个数以及求和即可。

```sql
-- 先将 from_id，to_id 转换为 person_1 person_2
-- 再嵌套外层循环，按照维度字段 person1 person2 分组，然后组内计数，组内求和

SELECT
    IF(from_id > to_id,to_id,from_id) person1,
    IF(from_id < to_id,to_id,from_id) person2,
    COUNT(*) call_count,
    SUM(duration) total_duration
FROM Calls
GROUP BY person1,person2
```

