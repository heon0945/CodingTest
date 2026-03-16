SELECT U.name, IFNULL(SUM(R.distance), 0) AS travelled_distance
FROM Rides R
RIGHT JOIN Users U 
ON R.user_id = U.id
GROUP BY U.id
ORDER BY 2 DESC, 1 ASC