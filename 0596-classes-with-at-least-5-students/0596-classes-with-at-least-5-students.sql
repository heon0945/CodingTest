SELECT class
FROM (
SELECT class, COUNT(student) AS cnt
FROM Courses
GROUP BY class
) C
WHERE cnt >= 5
