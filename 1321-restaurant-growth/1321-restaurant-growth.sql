WITH date_tb AS
(
    SELECT visited_on, SUM(amount) AS date_amount
    FROM Customer
    GROUP BY visited_on
),
total_tb AS
(
    SELECT visited_on, SUM(date_amount) OVER (ORDER BY visited_on) AS total_amount
    FROM date_tb
),
sevenday_tb AS
(
    SELECT t1.visited_on, t1.total_amount - IFNULL(t2.total_amount, 0)  AS amount
    FROM total_tb t1
    LEFT JOIN total_tb t2
    ON DATEDIFF(t1.visited_on, t2.visited_on) = 7
    WHERE (SELECT MIN(visited_on) FROM total_tb) + 6 <= t1.visited_on 
)

SELECT visited_on, amount, ROUND(amount / 7, 2) AS average_amount
FROM sevenday_tb
ORDER BY visited_on