SELECT MAX(num) as num
FROM
(
    SELECT num, COUNT(*) AS cnt
    FROM MyNumbers
    GROUP BY num
) N
WHERE cnt = 1