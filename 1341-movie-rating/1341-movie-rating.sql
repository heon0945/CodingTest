WITH fav_movie AS 
(
    SELECT MV.title, AVG(rating) AS average
    FROM MovieRating M
    JOIN Movies MV
    ON M.movie_id = MV.movie_id
    WHERE YEAR(created_at) = 2020 AND MONTH(created_at) = 2
    GROUP BY M.movie_id
    ORDER BY average DESC, title ASC LIMIT 1
),
fav_user AS
(
   SELECT U.name, COUNT(*) AS results
    FROM MovieRating M
    JOIN Users U
    ON M.user_id = U.user_id
    GROUP BY M.user_id
    ORDER BY results DESC, name ASC LIMIT 1 
)

SELECT name AS results
FROM fav_user

UNION ALL

SELECT title AS results
FROM fav_movie