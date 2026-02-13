WITH date_order AS 
(
    SELECT E.employee_id, E.name, P.rating, RANK() OVER (PARTITION BY E.employee_id ORDER BY review_date DESC) AS ranking
    FROM employees E
    JOIN performance_reviews P
    ON E.employee_id = P.employee_id
),
LAST3 AS
(
    SELECT employee_id, name, 
    MAX(CASE WHEN ranking = 1 THEN rating END) AS rating_latest,
    MAX(CASE WHEN ranking = 2 THEN rating END) AS rating_second,
    MAX(CASE WHEN ranking = 3 THEN rating END) AS rating_old
    FROM date_order
    WHERE ranking <= 3
    GROUP BY employee_id
)

SELECT employee_id, name, rating_latest - rating_old AS improvement_score
FROM last3
WHERE rating_old IS NOT NULL
AND rating_second IS NOT NULL
AND rating_latest IS NOT NULL
AND rating_latest > rating_second
AND rating_second > rating_old
ORDER BY improvement_score DESC, name