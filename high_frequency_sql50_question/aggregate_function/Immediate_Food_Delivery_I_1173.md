# 1173  即时食物配送 I

<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>


[地址](https://leetcode.cn/problems/immediate-food-delivery-i/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schema

```sql
Create table If Not Exists Delivery (delivery_id int, customer_id int, order_date date, customer_pref_delivery_date date)
Truncate table Delivery
insert into Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date) values ('1', '1', '2019-08-01', '2019-08-02')
insert into Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date) values ('2', '5', '2019-08-02', '2019-08-02')
insert into Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date) values ('3', '1', '2019-08-11', '2019-08-11')
insert into Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date) values ('4', '3', '2019-08-24', '2019-08-26')
insert into Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date) values ('5', '4', '2019-08-21', '2019-08-22')
insert into Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date) values ('6', '2', '2019-08-11', '2019-08-13')
```



配送表: `Delivery`

```
+-----------------------------+---------+
| Column Name                 | Type    |
+-----------------------------+---------+
| delivery_id                 | int     |
| customer_id                 | int     |
| order_date                  | date    |
| customer_pref_delivery_date | date    |
+-----------------------------+---------+
delivery_id 是表的主键。
该表保存着顾客的食物配送信息，顾客在某个日期下了订单，并指定了一个期望的配送日期（和下单日期相同或者在那之后）。
```

 如果顾客期望的配送日期和下单日期相同，则该订单称为 「即时订单」，否则称为「计划订单」。

写一条 SQL 查询语句<u>获取即时订单所占的百分比</u>， **保留两位小数。**

查询结果如下所示。

 

**示例 1:**

```
输入：
Delivery 表:
+-------------+-------------+------------+-----------------------------+
| delivery_id | customer_id | order_date | customer_pref_delivery_date |
+-------------+-------------+------------+-----------------------------+
| 1           | 1           | 2019-08-01 | 2019-08-02                  |
| 2           | 5           | 2019-08-02 | 2019-08-02                  |
| 3           | 1           | 2019-08-11 | 2019-08-11                  |
| 4           | 3           | 2019-08-24 | 2019-08-26                  |
| 5           | 4           | 2019-08-21 | 2019-08-22                  |
| 6           | 2           | 2019-08-11 | 2019-08-13                  |
+-------------+-------------+------------+-----------------------------+
输出：
+----------------------+
| immediate_percentage |
+----------------------+
| 33.33                |
+----------------------+
解释：2 和 3 号订单为即时订单，其他的为计划订单。
```

## 预备知识

本题使用到的 `MySQL` 函数的说明：

- `ROUND(x, d)`： 四舍五入保留 `x` 的 `d` 位小数。

## 方法一：直接计算

### **思路**

题目要求**获取即时订单所占的比例**，最直观的思路就是计算出即时订单的数量，再计算出总订单的数量。即时订单数量除以总订单数据就是我们要求的比例。

### **算法**

1. 计算即时订单数，即时订单的要求为**配送日期和下单日期相同**：

```mysql
select count(*) from Delivery where order_date = customer_pref_delivery_date
```

2. 计算总订单数：

```mysql
select count(*) from Delivery
```

3. 将两数相除，使用 `round` 函数保留 2 位小数，因为要计算的是所占比例，所以还需要乘 100。

### **代码**

Mysql

```mysql
select round (
    (select count(*) from Delivery where order_date = customer_pref_delivery_date) / 
    (select count(*) from Delivery) * 100,
    2
) as immediate_percentage
```

## 方法二： `sum` 和 `case when`

### **思路**

我们可以使用 `sum` 和 `case when` 计算出即时订单的数量。当满足条件的时候，使用 `case when` 将 `sum` 值加 1。

### **代码**

```mysql
select round (
    sum(case when order_date = customer_pref_delivery_date then 1 else 0 end) /
    count(*) * 100,
    2
) as immediate_percentage
from Delivery
```

## 方法三： `sum`

### **思路**

我们还可以直接使用 `sum`。当 `order_date = customer_pref_delivery_date` 为真时，`sum` 值加 1。

### **代码**

```mysql
select round (
    sum(order_date = customer_pref_delivery_date) /
    count(*) * 100,
    2
) as immediate_percentage
from Delivery
```
