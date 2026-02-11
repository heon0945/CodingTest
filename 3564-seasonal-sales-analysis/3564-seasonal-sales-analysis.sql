WITH season_tb AS
(
    SELECT 
    CASE WHEN MONTH(sale_date) IN (12, 1, 2) THEN 'Winter'
    WHEN MONTH(sale_date) IN (3, 4, 5) THEN 'Spring'
    WHEN MONTH(sale_date) IN (6, 7, 8) THEN 'Summer'
    ELSE 'Fall'
    END AS season,
    category, SUM(quantity) AS total_quantity, ROUND(SUM(quantity * price), 2) AS total_revenue
    FROM sales S
    JOIN products P
    ON S.product_id = P.product_id
    GROUP BY season, category
),
rank_tb AS
(
    SELECT *, RANK() OVER (PARTITION BY season ORDER BY total_quantity DESC, total_revenue DESC, category) AS ranking
    FROM season_tb
)

SELECT season, category, total_quantity, total_revenue
FROM rank_tb
WHERE ranking=1
