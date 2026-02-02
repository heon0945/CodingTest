WITH order_19 AS (
SELECT buyer_id, COUNT(*) AS orders_in_2019
FROM Orders
WHERE YEAR(order_date) = 2019
GROUP BY buyer_id
)

SELECT U.user_id AS buyer_id, U.join_date, IFNULL(O.orders_in_2019, 0) AS orders_in_2019
FROM Users U
LEFT JOIN order_19 O
ON U.user_id = O.buyer_id
