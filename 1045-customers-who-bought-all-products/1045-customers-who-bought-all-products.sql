SELECT customer_id
FROM
(
SELECT customer_id, COUNT(DISTINCT product_key) AS cnt
FROM Customer
GROUP BY customer_id
) C
WHERE cnt = (SELECT COUNT(DISTINCT product_key) FROM Product)