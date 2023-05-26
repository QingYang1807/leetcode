# 1571 

<span style="background-color: #57bb8a; color: #fff; padding: 4px 8px; border-radius: 4px;">简单</span>



[地址](https://leetcode.cn/problems/warehouse-manager/?envType=study-plan-v2&envId=sql-premium-50)

## SQL Schema
```sql
Create table If Not Exists Warehouse (name varchar(50), product_id int, units int)

Create table If Not Exists Products (product_id int, product_name varchar(50), Width int,Length int,Height int)
Truncate table Warehouse
insert into Warehouse (name, product_id, units) values ('LCHouse1', '1', '1')
insert into Warehouse (name, product_id, units) values ('LCHouse1', '2', '10')
insert into Warehouse (name, product_id, units) values ('LCHouse1', '3', '5')
insert into Warehouse (name, product_id, units) values ('LCHouse2', '1', '2')
insert into Warehouse (name, product_id, units) values ('LCHouse2', '2', '2')
insert into Warehouse (name, product_id, units) values ('LCHouse3', '4', '1')
Truncate table Products
insert into Products (product_id, product_name, Width, Length, Height) values ('1', 'LC-TV', '5', '50', '40')
insert into Products (product_id, product_name, Width, Length, Height) values ('2', 'LC-KeyChain', '5', '5', '5')
insert into Products (product_id, product_name, Width, Length, Height) values ('3', 'LC-Phone', '2', '10', '10')
insert into Products (product_id, product_name, Width, Length, Height) values ('4', 'LC-T-Shirt', '4', '10', '20')
```

表: `Warehouse`

```
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| name         | varchar |
| product_id   | int     |
| units        | int     |
+--------------+---------+
(name, product_id) 是该表主键.
该表的行包含了每个仓库的所有商品信息.
```

 

表: `Products`

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| product_name  | varchar |
| Width         | int     |
| Length        | int     |
| Height        | int     |
+---------------+---------+
product_id 是该表主键.
该表的行包含了每件商品以英尺为单位的尺寸(宽度, 长度和高度)信息.
```

 

写一个 SQL 查询来报告, 每个仓库的存货量是多少立方英尺.

返回结果没有顺序要求.

查询结果如下例所示.

 

**示例 1:**

```
输入：
Warehouse 表:
+------------+--------------+-------------+
| name       | product_id   | units       |
+------------+--------------+-------------+
| LCHouse1   | 1            | 1           |
| LCHouse1   | 2            | 10          |
| LCHouse1   | 3            | 5           |
| LCHouse2   | 1            | 2           |
| LCHouse2   | 2            | 2           |
| LCHouse3   | 4            | 1           |
+------------+--------------+-------------+
Products 表:
+------------+--------------+------------+----------+-----------+
| product_id | product_name | Width      | Length   | Height    |
+------------+--------------+------------+----------+-----------+
| 1          | LC-TV        | 5          | 50       | 40        |
| 2          | LC-KeyChain  | 5          | 5        | 5         |
| 3          | LC-Phone     | 2          | 10       | 10        |
| 4          | LC-T-Shirt   | 4          | 10       | 20        |
+------------+--------------+------------+----------+-----------+
输出：
+----------------+------------+
| WAREHOUSE_NAME | VOLUME     | 
+----------------+------------+
| LCHouse1       | 12250      | 
| LCHouse2       | 20250      |
| LCHouse3       | 800        |
+----------------+------------+
解释：
Id为1的商品(LC-TV)的存货量为 5x50x40 = 10000
Id为2的商品(LC-KeyChain)的存货量为 5x5x5 = 125 
Id为3的商品(LC-Phone)的存货量为 2x10x10 = 200
Id为4的商品(LC-T-Shirt)的存货量为 4x10x20 = 800
仓库LCHouse1: 1个单位的LC-TV + 10个单位的LC-KeyChain + 5个单位的LC-Phone.
          总存货量为: 1*10000 + 10*125  + 5*200 = 12250 立方英尺
仓库LCHouse2: 2个单位的LC-TV + 2个单位的LC-KeyChain.
          总存货量为: 2*10000 + 2*125 = 20250 立方英尺
仓库LCHouse3: 1个单位的LC-T-Shirt.
          总存货量为: 1*800 = 800 立方英尺.
```

思路：

- 首先，我们需要计算每个商品的体积，这可以通过将商品的宽度、长度和高度相乘来实现。
- 然后，我们需要计算每个仓库每个商品的存储量，这可以通过将商品的体积乘以商品在仓库中的单位数来实现。
- 最后，我们需要对每个仓库的所有商品的存储量进行汇总，这可以通过按仓库名称分组并对每个仓库的存储量进行求和来实现。

解答：

```sql
SELECT w.name AS WAREHOUSE_NAME, 
       SUM(w.units * p.Width * p.Length * p.Height) AS VOLUME
FROM Warehouse AS w
JOIN Products AS p ON w.product_id = p.product_id
GROUP BY w.name;
```

这个 SQL 查询首先通过内连接将仓库表和产品表连接在一起，连接的条件是仓库中的商品 id 与产品表中的商品 id 匹配。然后，它计算每个仓库每个商品的存储量，最后通过按仓库名称分组并对每个仓库的存储量进行求和，来获取每个仓库的总存储量。
