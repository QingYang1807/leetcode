# 1407 排名靠前的旅行者

<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>

[link](https://leetcode.cn/problems/top-travellers/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schema
```sql
Create Table If Not Exists Users (id int, name varchar(30))
Create Table If Not Exists Rides (id int, user_id int, distance int)
Truncate table Users
insert into Users (id, name) values ('1', 'Alice')
insert into Users (id, name) values ('2', 'Bob')
insert into Users (id, name) values ('3', 'Alex')
insert into Users (id, name) values ('4', 'Donald')
insert into Users (id, name) values ('7', 'Lee')
insert into Users (id, name) values ('13', 'Jonathan')
insert into Users (id, name) values ('19', 'Elvis')
Truncate table Rides
insert into Rides (id, user_id, distance) values ('1', '1', '120')
insert into Rides (id, user_id, distance) values ('2', '2', '317')
insert into Rides (id, user_id, distance) values ('3', '3', '222')
insert into Rides (id, user_id, distance) values ('4', '7', '100')
insert into Rides (id, user_id, distance) values ('5', '13', '312')
insert into Rides (id, user_id, distance) values ('6', '19', '50')
insert into Rides (id, user_id, distance) values ('7', '7', '120')
insert into Rides (id, user_id, distance) values ('8', '19', '400')
insert into Rides (id, user_id, distance) values ('9', '7', '230')
```

表：`Users`

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
id 是该表单主键。
name 是用户名字。
```

 

表：`Rides`

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| user_id       | int     |
| distance      | int     |
+---------------+---------+
id 是该表单主键。
user_id 是本次行程的用户的 id, 而该用户此次行程距离为 distance 。
```

 

写一段 SQL , 报告每个用户的旅行距离。

返回的结果表单，以 `travelled_distance` **降序排列** ，如果有两个或者更多的用户旅行了相同的距离, 那么再以 `name` **升序排列** 。

查询结果格式如下例所示。

```
Users 表：
+------+-----------+
| id   | name      |
+------+-----------+
| 1    | Alice     |
| 2    | Bob       |
| 3    | Alex      |
| 4    | Donald    |
| 7    | Lee       |
| 13   | Jonathan  |
| 19   | Elvis     |
+------+-----------+

Rides 表：
+------+----------+----------+
| id   | user_id  | distance |
+------+----------+----------+
| 1    | 1        | 120      |
| 2    | 2        | 317      |
| 3    | 3        | 222      |
| 4    | 7        | 100      |
| 5    | 13       | 312      |
| 6    | 19       | 50       |
| 7    | 7        | 120      |
| 8    | 19       | 400      |
| 9    | 7        | 230      |
+------+----------+----------+

Result 表：
+----------+--------------------+
| name     | travelled_distance |
+----------+--------------------+
| Elvis    | 450                |
| Lee      | 450                |
| Bob      | 317                |
| Jonathan | 312                |
| Alex     | 222                |
| Alice    | 120                |
| Donald   | 0                  |
+----------+--------------------+
Elvis 和 Lee 旅行了 450 英里，Elvis 是排名靠前的旅行者，因为他的名字在字母表上的排序比 Lee 更小。
Bob, Jonathan, Alex 和 Alice 只有一次行程，我们只按此次行程的全部距离对他们排序。
Donald 没有任何行程, 他的旅行距离为 0。
```

**思路**

这个问题可以通过联接`Users`表和`Rides`表来解决。我们需要找出每个用户的总旅行距离。可以使用SQL的`SUM`函数来计算每个用户的总旅行距离。需要注意的是，有些用户可能没有旅行记录，对于这种情况，我们需要将他们的旅行距离设置为0，可以使用SQL的`COALESCE`函数来实现。

首先，我们需要联接`Users`表和`Rides`表，联接条件是`user_id`字段。

然后，我们需要按用户分组，并计算每个用户的总旅行距离。我们可以使用`GROUP BY`语句和`SUM`函数来实现。

最后，我们需要将结果按旅行距离降序排序，如果旅行距离相同，则按用户名升序排序。我们可以使用`ORDER BY`语句来实现。



**解答**

下面是解决这个问题的SQL语句：

```sql
SELECT u.name, COALESCE(SUM(r.distance), 0) as travelled_distance
FROM Users u
LEFT JOIN Rides r
ON u.id = r.user_id
GROUP BY u.id, u.name
ORDER BY travelled_distance DESC, u.name ASC;
```

这个查询的主要部分是`LEFT JOIN`，它将`Users`表和`Rides`表联接在一起。然后，我们使用`GROUP BY`语句和`SUM`函数计算每个用户的总旅行距离。`COALESCE`函数确保了如果用户没有旅行记录，则他的旅行距离将被设置为0。最后，我们使用`ORDER BY`语句将结果按旅行距离降序排序，如果旅行距离相同，则按用户名升序排序。