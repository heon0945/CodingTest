WITH user_tb AS
(
    SELECT user_id
    FROM Signups

    UNION

    SELECT user_id
    FROM Confirmations
)

SELECT U.user_id, CASE
    WHEN COUNT(C.action) = 0 THEN 0
    ELSE ROUND(IFNULL(SUM(C.action='confirmed'), 0) / COUNT(C.action), 2)
    END AS confirmation_rate
FROM user_tb U
LEFT JOIN Confirmations C
ON U.user_id = C.user_id
GROUP BY U.user_id