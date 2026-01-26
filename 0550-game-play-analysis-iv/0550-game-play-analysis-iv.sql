	WITH first_login_tb AS
(SELECT player_id, device_id, event_date, MIN(event_date) OVER (PARTITION BY player_id) AS first_login_date
FROM Activity
)
 
SELECT ROUND(COUNT(DISTINCT player_id) / (SELECT COUNT(DISTINCT player_id) FROM Activity), 2) AS fraction
FROM first_login_tb
WHERE DATEDIFF(event_date, first_login_date) = 1