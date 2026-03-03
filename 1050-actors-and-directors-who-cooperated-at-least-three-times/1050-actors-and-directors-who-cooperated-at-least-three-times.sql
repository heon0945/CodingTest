SELECT actor_id, director_id
FROM
(
    SELECT actor_id, director_id, COUNT(timestamp) AS cnt
    FROM ActorDirector
    GROUP BY actor_id, director_id
) A
WHERE cnt >= 3