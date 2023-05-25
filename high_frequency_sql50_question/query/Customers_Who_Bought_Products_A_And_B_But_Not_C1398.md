# 1398 购买了产品 A 和产品 B 却没有购买产品 C 的顾客

<span style="background-color: #ffa500; color: #fff; padding: 4px 8px; border-radius: 4px;">中等</span>



[地址](https://leetcode.cn/problems/customers-who-bought-products-a-and-b-but-not-c/?envType=study-plan-v2&envId=sql-premium-50)



## SQL Schema

```sql
Create table If Not Exists Customers (customer_id int, customer_name varchar(30))
Create table If Not Exists Orders (order_id int, customer_id int, product_name varchar(30))
Truncate table Customers
insert into Customers (customer_id, customer_name) values ('1', 'Daniel')
insert into Customers (customer_id, customer_name) values ('2', 'Diana')
insert into Customers (customer_id, customer_name) values ('3', 'Elizabeth')
insert into Customers (customer_id, customer_name) values ('4', 'Jhon')
Truncate table Orders
insert into Orders (order_id, customer_id, product_name) values ('10', '1', 'A')
insert into Orders (order_id, customer_id, product_name) values ('20', '1', 'B')
insert into Orders (order_id, customer_id, product_name) values ('30', '1', 'D')
insert into Orders (order_id, customer_id, product_name) values ('40', '1', 'C')
insert into Orders (order_id, customer_id, product_name) values ('50', '2', 'A')
insert into Orders (order_id, customer_id, product_name) values ('60', '3', 'A')
insert into Orders (order_id, customer_id, product_name) values ('70', '3', 'B')
insert into Orders (order_id, customer_id, product_name) values ('80', '3', 'D')
insert into Orders (order_id, customer_id, product_name) values ('90', '4', 'C')
```

 `Customers` 表：

```
+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| customer_id         | int     |
| customer_name       | varchar |
+---------------------+---------+
customer_id 是这张表的主键。
customer_name 是顾客的名称。
```

 

`Orders` 表：

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| customer_id   | int     |
| product_name  | varchar |
+---------------+---------+
order_id 是这张表的主键。
customer_id 是购买了名为 "product_name" 产品顾客的id。
```

 

请你设计 SQL 查询来报告购买了产品 A 和产品 B 却没有购买产品 C 的顾客的 ID 和姓名（ `customer_id` 和 `customer_name` ），我们将基于此结果为他们推荐产品 C 。
您返回的查询结果需要按照 `customer_id` **排序**。

 

查询结果如下例所示。

```
Customers table:
+-------------+---------------+
| customer_id | customer_name |
+-------------+---------------+
| 1           | Daniel        |
| 2           | Diana         |
| 3           | Elizabeth     |
| 4           | Jhon          |
+-------------+---------------+

Orders table:
+------------+--------------+---------------+
| order_id   | customer_id  | product_name  |
+------------+--------------+---------------+
| 10         |     1        |     A         |
| 20         |     1        |     B         |
| 30         |     1        |     D         |
| 40         |     1        |     C         |
| 50         |     2        |     A         |
| 60         |     3        |     A         |
| 70         |     3        |     B         |
| 80         |     3        |     D         |
| 90         |     4        |     C         |
+------------+--------------+---------------+

Result table:
+-------------+---------------+
| customer_id | customer_name |
+-------------+---------------+
| 3           | Elizabeth     |
+-------------+---------------+
只有 customer_id 为 3 的顾客购买了产品 A 和产品 B ，却没有购买产品 C 。
```

## Code

### WHERE过滤，IN & NOT IN区分

```sql
SELECT
    customer_id, customer_name
FROM
    Customers
WHERE
    customer_id NOT IN (
        SELECT customer_id
        FROM Orders
        WHERE product_name = 'C'
    ) AND Customer_id IN (
        SELECT customer_id
        FROM Orders
        WHERE product_name = 'A'
    ) AND Customer_id IN (
        SELECT customer_id
        FROM Orders
        WHERE product_name = 'B'
    )
ORDER BY customer_id
```

### GROUP BY + HAVING 过滤

```sql
SELECT
    c.customer_id, c.customer_name
FROM
    Orders o LEFT JOIN Customers c ON o.customer_id = c.customer_id
GROUP BY c.customer_id
HAVING
    SUM(product_name = 'A') * SUM(product_name = 'B') > 0
    AND SUM(product_name = 'C') = 0
ORDER BY c.customer_id
```

