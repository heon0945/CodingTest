SELECT S.student_id, S.student_name, S.subject_name,
    (SELECT COUNT(*) FROM Examinations E WHERE E.student_id = S.student_id AND E.subject_name = S.subject_name) AS attended_exams
FROM (SELECT * FROM Students CROSS JOIN Subjects) S
ORDER BY 1, 3
