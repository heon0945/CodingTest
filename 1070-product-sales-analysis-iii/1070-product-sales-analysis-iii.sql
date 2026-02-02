WITH first_tb AS (
    SELECT product_id, MIN(year) AS year
    FROM Sales
    GROUP BY product_id
)

SELECT product_id, year AS first_year, quantity, price
FROM Sales s
WHERE (product_id, year) IN (SELECT product_id, year FROM first_tb)

