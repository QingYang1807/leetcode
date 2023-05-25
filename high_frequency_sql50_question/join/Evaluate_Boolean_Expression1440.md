# 1440 计算布尔表达式的值

<span style="background-color: #ffa500; color: #fff; padding: 4px 8px; border-radius: 4px;">中等</span>



[地址](https://leetcode.cn/problems/evaluate-boolean-expression/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schema

```sql
Create Table If Not Exists Variables (name varchar(3), value int)
Create Table If Not Exists Expressions (left_operand varchar(3), operator ENUM('>', '<', '='), right_operand varchar(3))
Truncate table Variables
insert into Variables (name, value) values ('x', '66')
insert into Variables (name, value) values ('y', '77')
Truncate table Expressions
insert into Expressions (left_operand, operator, right_operand) values ('x', '>', 'y')
insert into Expressions (left_operand, operator, right_operand) values ('x', '<', 'y')
insert into Expressions (left_operand, operator, right_operand) values ('x', '=', 'y')
insert into Expressions (left_operand, operator, right_operand) values ('y', '>', 'x')
insert into Expressions (left_operand, operator, right_operand) values ('y', '<', 'x')
insert into Expressions (left_operand, operator, right_operand) values ('x', '=', 'x')
```

表 `Variables`:

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| name          | varchar |
| value         | int     |
+---------------+---------+
name 是该表主键.
该表包含了存储的变量及其对应的值.
```

 

表 `Expressions`:

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| left_operand  | varchar |
| operator      | enum    |
| right_operand | varchar |
+---------------+---------+
(left_operand, operator, right_operand) 是该表主键.
该表包含了需要计算的布尔表达式.
operator 是枚举类型, 取值于('<', '>', '=')
left_operand 和 right_operand 的值保证存在于 Variables 表单中.
```

 

写一个 SQL 查询, 以计算表 `Expressions` 中的布尔表达式.

返回的结果表没有顺序要求.

查询结果格式如下例所示.

```
Variables 表:
+------+-------+
| name | value |
+------+-------+
| x    | 66    |
| y    | 77    |
+------+-------+

Expressions 表:
+--------------+----------+---------------+
| left_operand | operator | right_operand |
+--------------+----------+---------------+
| x            | >        | y             |
| x            | <        | y             |
| x            | =        | y             |
| y            | >        | x             |
| y            | <        | x             |
| x            | =        | x             |
+--------------+----------+---------------+

Result 表:
+--------------+----------+---------------+-------+
| left_operand | operator | right_operand | value |
+--------------+----------+---------------+-------+
| x            | >        | y             | false |
| x            | <        | y             | true  |
| x            | =        | y             | false |
| y            | >        | x             | true  |
| y            | <        | x             | false |
| x            | =        | x             | true  |
+--------------+----------+---------------+-------+
如上所示, 你需要通过使用 Variables 表来找到 Expressions 表中的每一个布尔表达式的值.
```



**思路**

这是一个关于如何在SQL中执行动态比较操作的问题。通常，我们不能直接在查询中使用列的值作为比较操作符，因为SQL不支持这样的动态比较操作。然而，我们可以通过使用CASE语句来模拟这种行为。

在本题中，我们需要在`Expressions`表中对每个表达式进行求值，而表达式的操作数和操作符都是存储在表中的。操作数的值存储在`Variables`表中，操作符存储在`Expressions`表中。我们需要首先将`Expressions`表和`Variables`表进行合适的连接，以获取操作数的值，然后使用CASE语句根据操作符来进行比较。

**解答**

以下是解决这个问题的SQL查询：
```sql
SELECT e.left_operand, e.operator, e.right_operand,
    CASE e.operator
        WHEN '>' THEN CASE WHEN v1.value > v2.value THEN 'true' ELSE 'false' END
        WHEN '<' THEN CASE WHEN v1.value < v2.value THEN 'true' ELSE 'false' END
        WHEN '=' THEN CASE WHEN v1.value = v2.value THEN 'true' ELSE 'false' END
    END AS value
FROM Expressions e
JOIN Variables v1 ON e.left_operand = v1.name
JOIN Variables v2 ON e.right_operand = v2.name
```

或

```sql
select e.*,
    case
        when operator = '=' and v1.value = v2.value then 'true'
        when operator = '>' and v1.value > v2.value then 'true'
        when operator = '<' and v1.value < v2.value then 'true'
        else 'false'
    end value
from Expressions e
left join Variables v1
on e.left_operand = v1.name
left join Variables v2
on e.right_operand = v2.name
```

这个查询首先将`Expressions`表和`Variables`表连接两次，一次是用于获取左操作数的值，一次是用于获取右操作数的值。然后，我们对每个表达式进行求值，使用CASE语句根据操作符进行比较。每个比较都有两种可能的结果：'true' 或 'false'，我们使用另一个CASE语句来确定应该返回哪个结果。

