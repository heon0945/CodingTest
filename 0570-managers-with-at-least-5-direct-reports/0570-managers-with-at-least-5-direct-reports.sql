SELECT manager_name AS name
FROM (
SELECT DISTINCT manager_id, manager_name
FROM
(
SELECT e1.id, e1.name, e2.id AS manager_id, e2.name AS manager_name, COUNT(*) OVER (PARTITION BY e2.id) AS manager_cnt
FROM Employee e1
JOIN Employee e2
ON e1.managerId = e2.id
) mng_tb1
WHERE manager_cnt >= 5
) mng_tb2