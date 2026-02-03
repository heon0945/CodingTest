WITH price_tb AS 
(
    SELECT product_id, MAX(change_date) AS recent_date
    FROM Products
    WHERE change_date <= '2019-08-16'
    GROUP BY product_id
),
recent_tb AS
(
    SELECT product_id, new_price, change_date
    FROM Products
    WHERE (product_id, change_date) IN (SELECT product_id, recent_date FROM price_tb)
)

SELECT DISTINCT(P.product_id), IFNULL(R.new_price, 10) AS price
FROM Products P
LEFT JOIN recent_tb R
ON P.product_id = R.product_id

