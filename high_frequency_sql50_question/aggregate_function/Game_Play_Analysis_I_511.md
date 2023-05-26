# 511 游戏玩法分析 I

<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>



[地址](https://leetcode.cn/problems/game-play-analysis-i/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schema

```sql
Create table If Not Exists Activity (player_id int, device_id int, event_date date, games_played int)
Truncate table Activity
insert into Activity (player_id, device_id, event_date, games_played) values ('1', '2', '2016-03-01', '5')
insert into Activity (player_id, device_id, event_date, games_played) values ('1', '2', '2016-05-02', '6')
insert into Activity (player_id, device_id, event_date, games_played) values ('2', '3', '2017-06-25', '1')
insert into Activity (player_id, device_id, event_date, games_played) values ('3', '1', '2016-03-02', '0')
insert into Activity (player_id, device_id, event_date, games_played) values ('3', '4', '2018-07-03', '5')
```

活动表 `Activity`：

```
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
表的主键是 (player_id, event_date)。
这张表展示了一些游戏玩家在游戏平台上的行为活动。
每行数据记录了一名玩家在退出平台之前，当天使用同一台设备登录平台后打开的游戏的数目（可能是 0 个）。
```

 

写一条 SQL 查询语句获取每位玩家 **第一次登陆平台的日期**。

查询结果的格式如下所示：

```
Activity 表：
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+

Result 表：
+-----------+-------------+
| player_id | first_login |
+-----------+-------------+
| 1         | 2016-03-01  |
| 2         | 2017-06-25  |
| 3         | 2016-03-02  |
+-----------+-------------+
```

思路：

- 首先，我们需要找到每个玩家的第一次登录日期。这可以通过在每个玩家的登录记录中选择最小的日期来实现，使用 SQL 的 MIN 函数。
- 接下来，我们需要按照玩家的 id 进行分组，这样就可以为每个玩家找到第一次登录日期。这可以通过 SQL 的 GROUP BY 子句来实现。

解答：

```sql
SELECT player_id, MIN(event_date) as first_login
FROM Activity
GROUP BY player_id;
```

这个 SQL 查询会找出每个玩家的第一次登录日期。它首先按照玩家的 id 对数据进行分组，然后使用 MIN 函数找到每个玩家的最早的登录日期。这个日期被选为该玩家的第一次登录日期。

