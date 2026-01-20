SELECT D.name AS Department, E.name AS Employee, E.salary AS Salary
FROM
(
    SELECT id, name, salary, departmentId, DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS 'rank'
FROM Employee
) E
JOIN Department D
ON E.departmentId = D.id
WHERE E.rank IN (1, 2, 3)

