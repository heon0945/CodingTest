WITH product_tb AS
(
    SELECT PP.user_id, PP.product_id, PP.quantity, PF.category, PF.price
    FROM ProductPurchases PP
    JOIN ProductInfo PF
    ON PP.product_id = PF.product_id
)

SELECT p1.product_id AS product1_id, p2.product_id AS product2_id, p1.category AS product1_category, p2.category AS product2_category, COUNT(DISTINCT p1.user_id) AS customer_count
FROM product_tb p1
JOIN product_tb p2
ON p1.user_id = p2.user_id AND p1.product_id < p2.product_id
GROUP BY 1, 2
HAVING COUNT(DISTINCT p1.user_id) >= 3
ORDER BY 5 DESC, 1, 2
