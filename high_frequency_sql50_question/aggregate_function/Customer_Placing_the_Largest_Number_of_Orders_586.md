# 586 订单最多的客户

<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>


[地址](https://leetcode.cn/problems/customer-placing-the-largest-number-of-orders/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schame
```sql
Create table If Not Exists orders (order_number int, customer_number int)
Truncate table orders
insert into orders (order_number, customer_number) values ('1', '1')
insert into orders (order_number, customer_number) values ('2', '2')
insert into orders (order_number, customer_number) values ('3', '3')
insert into orders (order_number, customer_number) values ('4', '3')
```

表: `Orders`

```
+-----------------+----------+
| Column Name     | Type     |
+-----------------+----------+
| order_number    | int      |
| customer_number | int      |
+-----------------+----------+
Order_number是该表的主键。
此表包含关于订单ID和客户ID的信息。
```

 

编写一个SQL查询，为下了 **最多订单** 的客户查找 `customer_number` 。

测试用例生成后， **恰好有一个客户** 比任何其他客户下了更多的订单。

查询结果格式如下所示。

 

**示例 1:**

```
输入: 
Orders 表:
+--------------+-----------------+
| order_number | customer_number |
+--------------+-----------------+
| 1            | 1               |
| 2            | 2               |
| 3            | 3               |
| 4            | 3               |
+--------------+-----------------+
输出: 
+-----------------+
| customer_number |
+-----------------+
| 3               |
+-----------------+
解释: 
customer_number 为 '3' 的顾客有两个订单，比顾客 '1' 或者 '2' 都要多，因为他们只有一个订单。
所以结果是该顾客的 customer_number ，也就是 3 。
```

 

**进阶：** 如果有多位顾客订单数并列最多，你能找到他们所有的 `customer_number` 吗？



## 方法：使用 LIMIT [Accepted]

### 算法

首先，我们使用 GROUP BY 选择 customer_number 和相应的订单数目。

```sql
SELECT
    customer_number, COUNT(*)
FROM
    orders
GROUP BY customer_number
```

| customer_number | COUNT(*) |
| --------------- | -------- |
| 1               | 1        |
| 2               | 1        |
| 3               | 2        |



将它们按照订单数目降序排序之后，第一条记录的 customer_number 就是答案。

| customer_number | COUNT(*) |
| --------------- | -------- |
| 3               | 2        |

在 MySQL 中， LIMIT 语句可以被用来限制 SELECT 语句的返回行数。它需要传入 1 个或 2 个非负整数参数，第一个参数 offset 表示跳过前面多少行后开始取数据，第二个参数表示最多返回多少行的数据。默认 offset 为 0（不是 1）。

LIMIT 语句也可以只使用一个参数，这个参数的含义是从结果的第一行开始返回的行数。所以 LIMIT 1 会返回第一行的记录。

### MySQL

```sql
SELECT
    customer_number
FROM
    orders
GROUP BY customer_number
ORDER BY COUNT(*) DESC
LIMIT 1;
```



若订单最多的顾客不止一位，则利用having的子查询查找哪些顾客的订单数量是最大的

```sql
select customer_number
from orders
group by customer_number
having count(*) = (
    select count(*)
    from orders
    group by customer_number
    order by count(*) desc
    limit 0,1
)
```



其中，以下代码的意思是：

```sql
having count(*) = (
    select count(*)
    from orders
    group by customer_number
    order by count(*) desc
    limit 0,1
)
```

由于假设结果不止一个，所以就要选出所有和最大的订单数量相同的顾客
