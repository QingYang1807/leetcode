# 1890 2020年最后一次登录

<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>



[地址](https://leetcode.cn/problems/the-latest-login-in-2020/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schema

```sql
Create table If Not Exists Logins (user_id int, time_stamp datetime)
Truncate table Logins
insert into Logins (user_id, time_stamp) values ('6', '2020-06-30 15:06:07')
insert into Logins (user_id, time_stamp) values ('6', '2021-04-21 14:06:06')
insert into Logins (user_id, time_stamp) values ('6', '2019-03-07 00:18:15')
insert into Logins (user_id, time_stamp) values ('8', '2020-02-01 05:10:53')
insert into Logins (user_id, time_stamp) values ('8', '2020-12-30 00:46:50')
insert into Logins (user_id, time_stamp) values ('2', '2020-01-16 02:49:50')
insert into Logins (user_id, time_stamp) values ('2', '2019-08-25 07:59:08')
insert into Logins (user_id, time_stamp) values ('14', '2019-07-14 09:00:00')
insert into Logins (user_id, time_stamp) values ('14', '2021-01-06 11:59:59')
```

表: `Logins`

```
+----------------+----------+
| 列名           | 类型      |
+----------------+----------+
| user_id        | int      |
| time_stamp     | datetime |
+----------------+----------+
(user_id, time_stamp) 是这个表的主键。
每一行包含的信息是user_id 这个用户的登录时间。
```

编写一个 SQL 查询，该查询可以获取在 `2020` 年登录过的所有用户的本年度 **最后一次** 登录时间。结果集 **不** 包含 `2020` 年没有登录过的用户。

返回的结果集可以按 **任意顺序** 排列。

查询结果格式如下例。

 

**示例 1:**

```
输入：
Logins 表:
+---------+---------------------+
| user_id | time_stamp          |
+---------+---------------------+
| 6       | 2020-06-30 15:06:07 |
| 6       | 2021-04-21 14:06:06 |
| 6       | 2019-03-07 00:18:15 |
| 8       | 2020-02-01 05:10:53 |
| 8       | 2020-12-30 00:46:50 |
| 2       | 2020-01-16 02:49:50 |
| 2       | 2019-08-25 07:59:08 |
| 14      | 2019-07-14 09:00:00 |
| 14      | 2021-01-06 11:59:59 |
+---------+---------------------+
输出：
+---------+---------------------+
| user_id | last_stamp          |
+---------+---------------------+
| 6       | 2020-06-30 15:06:07 |
| 8       | 2020-12-30 00:46:50 |
| 2       | 2020-01-16 02:49:50 |
+---------+---------------------+
解释：
6号用户登录了3次，但是在2020年仅有一次，所以结果集应包含此次登录。
8号用户在2020年登录了2次，一次在2月，一次在12月，所以，结果集应该包含12月的这次登录。
2号用户登录了2次，但是在2020年仅有一次，所以结果集应包含此次登录。
14号用户在2020年没有登录，所以结果集不应包含。
```

思路：

- 首先，我们需要找到在2020年登录过的所有用户及其登录时间。这可以通过在WHERE子句中添加条件实现，即选择年份为2020的时间戳。
- 其次，为了得到每个用户的最后登录时间，我们可以使用GROUP BY语句将结果按照用户id分组，然后使用MAX函数找出每个用户的最大时间戳，即最后登录时间。

解答：

```sql
SELECT user_id, MAX(time_stamp) as last_stamp
FROM Logins
WHERE YEAR(time_stamp) = 2020
GROUP BY user_id;
```

这个SQL查询首先选取了在2020年有登录行为的记录，然后根据用户id进行分组，并找出每个用户的最后登录时间。结果将包含所有在2020年有过登录行为的用户的id以及他们在2020年的最后登录时间。

