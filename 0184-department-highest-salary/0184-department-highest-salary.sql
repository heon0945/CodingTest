SELECT Department, Employee, Salary
FROM
(
SELECT E.name AS Employee, D.name AS Department, E.salary AS Salary, RANK() OVER (PARTITION BY D.id ORDER BY salary DESC) AS 'rank'
FROM Employee E
LEFT JOIN Department D
ON E.departmentId = D.id
) R
WHERE R.rank = 1