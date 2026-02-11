WITH date_tb AS
(
    SELECT student_id, subject, MIN(exam_date) AS first_time, MAX(exam_date) AS last_time
    FROM Scores
    GROUP BY student_id, subject
),
minmax_tb AS
(
    SELECT student_id, subject, first_time, last_time
    FROM date_tb
    WHERE first_time != last_time
),
score_tb AS
(
    SELECT student_id, subject, 
        (SELECT score FROM Scores WHERE M.student_id = student_id AND M.subject = subject AND   exam_date = M.first_time) AS first_score,
        (SELECT score FROM Scores WHERE M.student_id = student_id AND M.subject = subject AND exam_date = M.last_time) AS latest_score
    FROM minmax_tb M
)

SELECT * 
FROM score_tb
WHERE first_score < latest_score
ORDER BY student_id, subject