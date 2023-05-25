# 1212 查询球队积分

<span style="background-color: #ffa500; color: #fff; padding: 4px 8px; border-radius: 4px;">中等</span>

[地址](https://leetcode.cn/problems/team-scores-in-football-tournament/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schema
```sql
Create table If Not Exists Teams (team_id int, team_name varchar(30))
Create table If Not Exists Matches (match_id int, host_team int, guest_team int, host_goals int, guest_goals int)
Truncate table Teams
insert into Teams (team_id, team_name) values ('10', 'Leetcode FC')
insert into Teams (team_id, team_name) values ('20', 'NewYork FC')
insert into Teams (team_id, team_name) values ('30', 'Atlanta FC')
insert into Teams (team_id, team_name) values ('40', 'Chicago FC')
insert into Teams (team_id, team_name) values ('50', 'Toronto FC')
Truncate table Matches
insert into Matches (match_id, host_team, guest_team, host_goals, guest_goals) values ('1', '10', '20', '3', '0')
insert into Matches (match_id, host_team, guest_team, host_goals, guest_goals) values ('2', '30', '10', '2', '2')
insert into Matches (match_id, host_team, guest_team, host_goals, guest_goals) values ('3', '10', '50', '5', '1')
insert into Matches (match_id, host_team, guest_team, host_goals, guest_goals) values ('4', '20', '30', '1', '0')
insert into Matches (match_id, host_team, guest_team, host_goals, guest_goals) values ('5', '50', '30', '1', '0')
```

表: `Teams`

```
+---------------+----------+
| Column Name   | Type     |
+---------------+----------+
| team_id       | int      |
| team_name     | varchar  |
+---------------+----------+
此表的主键是 team_id。
表中的每一行都代表一支独立足球队。
```

 

表: `Matches`

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| match_id      | int     |
| host_team     | int     |
| guest_team    | int     | 
| host_goals    | int     |
| guest_goals   | int     |
+---------------+---------+
此表的主键是 match_id。
表中的每一行都代表一场已结束的比赛。
比赛的主客队分别由它们自己的 id 表示，他们的进球由 host_goals 和 guest_goals 分别表示。
```

 

您希望在所有比赛之后计算所有球队的比分。积分奖励方式如下:

- 如果球队赢了比赛(即比对手进更多的球)，就得 **3** 分。
- 如果双方打成平手(即，与对方得分相同)，则得 **1** 分。
- 如果球队输掉了比赛(例如，比对手少进球)，就 **不得分** 。

写出一条SQL语句以查询每个队的 `team_id`，`team_name` 和 `num_points`。

返回的结果根据 `num_points` **降序排序**，如果有两队积分相同，那么这两队按 `team_id` **升序排序**。

查询结果格式如下。

 

**示例 1:**

```
输入：
Teams table:
+-----------+--------------+
| team_id   | team_name    |
+-----------+--------------+
| 10        | Leetcode FC  |
| 20        | NewYork FC   |
| 30        | Atlanta FC   |
| 40        | Chicago FC   |
| 50        | Toronto FC   |
+-----------+--------------+
Matches table:
+------------+--------------+---------------+-------------+--------------+
| match_id   | host_team    | guest_team    | host_goals  | guest_goals  |
+------------+--------------+---------------+-------------+--------------+
| 1          | 10           | 20            | 3           | 0            |
| 2          | 30           | 10            | 2           | 2            |
| 3          | 10           | 50            | 5           | 1            |
| 4          | 20           | 30            | 1           | 0            |
| 5          | 50           | 30            | 1           | 0            |
+------------+--------------+---------------+-------------+--------------+
输出：
+------------+--------------+---------------+
| team_id    | team_name    | num_points    |
+------------+--------------+---------------+
| 10         | Leetcode FC  | 7             |
| 20         | NewYork FC   | 3             |
| 50         | Toronto FC   | 3             |
| 30         | Atlanta FC   | 1             |
| 40         | Chicago FC   | 0             |
+------------+--------------+---------------+
```

**思路**

这个问题可以通过几步来解决：

1. 首先，我们需要计算每个队伍作为主队或客队的比赛积分。积分规则是：如果球队赢了比赛，就得3分；如果双方打成平手，就得1分；如果球队输掉了比赛，就不得分。
2. 计算出每个队伍作为主队或客队的比赛积分后，我们需要将这些积分合并到一起，得到每个队伍的总积分。
3. 最后，我们根据积分对队伍进行排序。

**解答**

以下是解决这个问题的SQL查询：

```sql
SELECT t.team_id, t.team_name,
    COALESCE(sum(
        CASE 
            WHEN (m.host_team = t.team_id AND m.host_goals > m.guest_goals) OR (m.guest_team = t.team_id AND m.guest_goals > m.host_goals) THEN 3
            WHEN m.host_goals = m.guest_goals THEN 1
            ELSE 0
        END), 0) AS num_points
FROM Teams t
LEFT JOIN Matches m
ON t.team_id = m.host_team OR t.team_id = m.guest_team
GROUP BY t.team_id, t.team_name
ORDER BY num_points DESC, t.team_id ASC
```

这个查询首先将Teams表和Matches表进行左连接，然后使用CASE语句根据比赛结果计算每场比赛的积分。如果队伍赢了比赛，就得3分；如果双方打成平手，就得1分；如果队伍输掉了比赛，就不得分。然后，我们将每个队伍的所有积分加起来，得到每个队伍的总积分。最后，我们根据积分对队伍进行排序，积分相同的队伍按照team_id升序排序。



或

```sql
select team_id,team_name,
sum(ifnull(
    case
    when team_id=host_team and host_goals>guest_goals then 3
    when team_id=host_team and host_goals=guest_goals then 1
    when team_id=guest_team and host_goals<guest_goals then 3
    when team_id=guest_team and host_goals=guest_goals then 1
end,0)) as 'num_points'
from Teams ,Matches group by team_id order by num_points desc,team_id ;
```


这个SQL语句的主要目的是按规则计算每个队伍的得分，并按得分排序。

首先，我们分析主要部分：

`ifnull(case when ... then ... end, 0) as 'num_points'`这是一种使用条件表达式计算每场比赛得分的方法。该语句先判断是主队还是客队，然后根据规则计算得分。如果比赛信息中没有相关队伍的信息，使用ifnull函数将得分设置为0。

其中，

- `team_id=host_team and host_goals>guest_goals`表示主队赢得了比赛，得分为3。
- `team_id=host_team and host_goals=guest_goals`表示主队在比赛中打成平手，得分为1。
- `team_id=guest_team and host_goals<guest_goals`表示客队赢得了比赛，得分为3。
- `team_id=guest_team and host_goals=guest_goals`表示客队在比赛中打成平手，得分为1。

`sum()`是一个聚合函数，用于计算同一队伍在所有比赛中的总得分。

`from Teams ,Matches` 这是一个简单的笛卡尔积，将Teams表中的每一行与Matches表中的每一行进行组合，得到所有可能的组合。

`group by team_id`这个语句的目的是按队伍id将结果分组，这样我们可以计算每个队伍的总得分。

最后，`order by num_points desc,team_id`用于将结果按得分降序排序，得分相同的情况下，按队伍id升序排序。

综上，这个SQL查询先对Teams和Matches表做笛卡尔积，然后根据比赛结果计算得分，最后按照得分和队伍id对结果进行排序。