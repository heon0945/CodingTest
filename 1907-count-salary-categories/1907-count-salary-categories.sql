WITH cat_tb AS (
    SELECT 'Low Salary' AS category
    UNION ALL
    SELECT 'Average Salary' AS category
    UNION ALL
    SELECT 'High Salary' AS category
),
acc_tb AS (
    SELECT *, CASE
        WHEN income < 20000 THEN 'Low Salary'
        WHEN income >= 20000 AND income <= 50000 THEN 'Average Salary'
        ELSE 'High Salary'
    END AS category
    FROM Accounts 
)

SELECT C.category, COUNT(A.category) AS accounts_count
FROM cat_tb C
LEFT JOIN acc_tb A
ON C.category = A.category
GROUP BY C.category
