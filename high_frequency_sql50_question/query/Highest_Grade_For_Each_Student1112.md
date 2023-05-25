# 1112 每位学生的最高成绩

<span style="background-color: #ffa500; color: #fff; padding: 4px 8px; border-radius: 4px;">中等</span>



[地址](https://leetcode.cn/problems/highest-grade-for-each-student/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schema

```sql
Create table If Not Exists Enrollments (student_id int, course_id int, grade int)
Truncate table Enrollments
insert into Enrollments (student_id, course_id, grade) values ('2', '2', '95')
insert into Enrollments (student_id, course_id, grade) values ('2', '3', '95')
insert into Enrollments (student_id, course_id, grade) values ('1', '1', '90')
insert into Enrollments (student_id, course_id, grade) values ('1', '2', '99')
insert into Enrollments (student_id, course_id, grade) values ('3', '1', '80')
insert into Enrollments (student_id, course_id, grade) values ('3', '2', '75')
insert into Enrollments (student_id, course_id, grade) values ('3', '3', '82')
```

表：`Enrollments`

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| course_id     | int     |
| grade         | int     |
+---------------+---------+
(student_id, course_id) 是该表的主键。
```

 

编写一个 SQL 查询，查询每位学生获得的最高成绩和它所对应的科目，若科目成绩并列，取 `course_id` 最小的一门。查询结果需按 `student_id` 增序进行排序。

以 **任意顺序** 返回结果表。

查询结果格式如下所示。

 

**示例 1：**

```
输入：
Enrollments 表：
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 2          | 2         | 95    |
| 2          | 3         | 95    |
| 1          | 1         | 90    |
| 1          | 2         | 99    |
| 3          | 1         | 80    |
| 3          | 2         | 75    |
| 3          | 3         | 82    |
+------------+-----------+-------+
输出：
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 1          | 2         | 99    |
| 2          | 2         | 95    |
| 3          | 3         | 82    |
+------------+-----------+-------+
```

## 解题思路

1. rank() : 阶梯排序-前两个是并列的1，接下来就是第3名
2. dense_rank(): 连续排序-前两个是并列的1，接下来就是第2名
3. row_number(): 不会出现重复的排序

<窗口函数> over (partition by <分组列名> order by <排序列名>)

## 代码

```sql

select student_id, course_id, grade
from (
    select student_id, course_id, grade,
    rank() over (partition by student_id order by grade desc, course_id asc ) as ranking
    from Enrollments
) t1
where ranking = 1
order by student_id
```

