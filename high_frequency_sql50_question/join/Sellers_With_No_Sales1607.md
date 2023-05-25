# 1607 没有卖出的卖家

<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>

[地址](https://leetcode.cn/problems/sellers-with-no-sales/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schema

```sql
Create table If Not Exists Customer (customer_id int, customer_name varchar(20))
Create table If Not Exists Orders (order_id int, sale_date date, order_cost int, customer_id int, seller_id int)

Create table If Not Exists Seller (seller_id int, seller_name varchar(20))

Truncate table Customer
insert into Customer (customer_id, customer_name) values ('101', 'Alice')
insert into Customer (customer_id, customer_name) values ('102', 'Bob')
insert into Customer (customer_id, customer_name) values ('103', 'Charlie')
Truncate table Orders
insert into Orders (order_id, sale_date, order_cost, customer_id, seller_id) values ('1', '2020-03-01', '1500', '101', '1')
insert into Orders (order_id, sale_date, order_cost, customer_id, seller_id) values ('2', '2020-05-25', '2400', '102', '2')
insert into Orders (order_id, sale_date, order_cost, customer_id, seller_id) values ('3', '2019-05-25', '800', '101', '3')
insert into Orders (order_id, sale_date, order_cost, customer_id, seller_id) values ('4', '2020-09-13', '1000', '103', '2')
insert into Orders (order_id, sale_date, order_cost, customer_id, seller_id) values ('5', '2019-02-11', '700', '101', '2')
Truncate table Seller
insert into Seller (seller_id, seller_name) values ('1', 'Daniel')
insert into Seller (seller_id, seller_name) values ('2', 'Elizabeth')
insert into Seller (seller_id, seller_name) values ('3', 'Frank')
```

表: `Customer`

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| customer_name | varchar |
+---------------+---------+
customer_id 是该表主键.
该表的每行包含网上商城的每一位顾客的信息.
```

 

表: `Orders`

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| sale_date     | date    |
| order_cost    | int     |
| customer_id   | int     |
| seller_id     | int     |
+---------------+---------+
order_id 是该表主键.
该表的每行包含网上商城的所有订单的信息.
sale_date 是顾客customer_id和卖家seller_id之间交易的日期.
```

 

表: `Seller`

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| seller_id     | int     |
| seller_name   | varchar |
+---------------+---------+
seller_id 是该表主键.
该表的每行包含每一位卖家的信息.
```

 

写一个SQL语句, 报告所有在2020年度没有任何卖出的卖家的名字.

返回结果按照 `seller_name` **升序排列**.

查询结果格式如下例所示.

 

**示例 1:**

```
输入：
Customer 表:
+--------------+---------------+
| customer_id  | customer_name |
+--------------+---------------+
| 101          | Alice         |
| 102          | Bob           |
| 103          | Charlie       |
+--------------+---------------+
Orders 表:
+-------------+------------+--------------+-------------+-------------+
| order_id    | sale_date  | order_cost   | customer_id | seller_id   |
+-------------+------------+--------------+-------------+-------------+
| 1           | 2020-03-01 | 1500         | 101         | 1           |
| 2           | 2020-05-25 | 2400         | 102         | 2           |
| 3           | 2019-05-25 | 800          | 101         | 3           |
| 4           | 2020-09-13 | 1000         | 103         | 2           |
| 5           | 2019-02-11 | 700          | 101         | 2           |
+-------------+------------+--------------+-------------+-------------+
Seller 表:
+-------------+-------------+
| seller_id   | seller_name |
+-------------+-------------+
| 1           | Daniel      |
| 2           | Elizabeth   |
| 3           | Frank       |
+-------------+-------------+
输出：
+-------------+
| seller_name |
+-------------+
| Frank       |
+-------------+
解释：
Daniel在2020年3月卖出1次.
Elizabeth在2020年卖出2次, 在2019年卖出1次.
Frank在2019年卖出1次, 在2020年没有卖出.
```

**思路**

这个问题可以通过联接`Seller`表和`Orders`表来解决。我们需要找出那些在2020年没有销售记录的卖家，这可以通过在日期字段上设置一个条件来实现。

首先，我们需要在`Orders`表中找出所有在2020年有销售记录的卖家。我们可以使用SQL的`YEAR`函数来获取销售日期的年份，并将其与2020比较。

然后，我们需要找出那些在`Seller`表中，但不在这些卖家列表中的卖家。这可以通过使用SQL的`NOT IN`语句来实现。

最后，我们需要将结果按照`seller_name`进行排序。



**解答**

下面是解决这个问题的SQL语句：

```sql
SELECT seller_name
FROM Seller
WHERE seller_id NOT IN (
    SELECT seller_id
    FROM Orders
    WHERE YEAR(sale_date) = 2020
)
ORDER BY seller_name;
```

这个查询的主要部分是子查询，它从`Orders`表中选择了在2020年有销售记录的所有卖家。然后，外部查询从`Seller`表中选择了那些不在这个列表中的卖家，并按照`seller_name`排序。

