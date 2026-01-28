SELECT t.id AS id,
    CASE 
    WHEN t.p_id IS NULL THEN 'Root'
    WHEN pt.p_cnt IS NULL THEN 'Leaf'
    ELSE 'Inner'
    END AS 'type'
FROM
Tree t
LEFT JOIN
(
SELECT p_id, COUNT(*) AS p_cnt
FROM Tree
GROUP BY p_id
) pt
ON t.id = pt.p_id
