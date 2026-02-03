WITH first_order_tb AS
(
    SELECT customer_id, MIN(order_date) AS first_order_date
    FROM Delivery
    GROUP BY customer_id
),
status_tb AS
(
    SELECT customer_id, order_date, customer_pref_delivery_date, CASE
        WHEN order_date = customer_pref_delivery_date THEN 'immediate'
        ELSE 'scheduled'
    END AS status
    FROM Delivery
    WHERE (customer_id, order_date) IN (SELECT customer_id, first_order_date FROM first_order_tb)
)

SELECT ROUND(COUNT(*) / (SELECT COUNT(*) FROM status_tb) * 100, 2) AS immediate_percentage
FROM status_tb
WHERE status = 'immediate'