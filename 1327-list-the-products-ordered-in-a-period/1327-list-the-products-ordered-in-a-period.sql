SELECT P.product_name, SUM(O.unit) AS unit
FROM (SELECT * FROM Orders WHERE order_date >= '2020-02-01' AND order_date < '2020-03-01') O
JOIN Products P
ON O.product_id = P.product_id 
GROUP BY P.product_id
HAVING SUM(O.unit) >= 100 