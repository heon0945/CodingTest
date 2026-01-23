SELECT id, visit_date, people
FROM
(
    SELECT id, visit_date, people, COUNT(id) OVER (PARTITION BY id-rankId) AS groupCnt
    FROM
    (
        SELECT id, visit_date, people, RANK() OVER (ORDER BY id) AS rankId
        FROM
        (
            SELECT id, visit_date, people
            FROM Stadium
            WHERE people >= 100
        ) Rank_Stadium
    ) Group_Stadium
) Count_Stadium
WHERE groupCnt >= 3
ORDER BY visit_date