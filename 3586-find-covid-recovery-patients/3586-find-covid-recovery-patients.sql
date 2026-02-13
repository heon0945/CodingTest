WITH covid_patient_tb AS
(
    SELECT P.patient_id, P.patient_name, P.age, C.test_date, C.result
    FROM patients P
    JOIN covid_tests C
    ON P.patient_id = C.patient_id
),
first_positive AS
(
    SELECT patient_id,
           MIN(test_date) AS first_positive_date
    FROM covid_patient_tb
    WHERE result = 'Positive'
    GROUP BY patient_id
),
first_negative AS
(
    SELECT c.patient_id,
           MIN(c.test_date) AS first_negative_date
    FROM covid_patient_tb c
    JOIN first_positive fp
    ON c.patient_id = fp.patient_id
    AND c.test_date > fp.first_positive_date
    WHERE c.result = 'Negative'
    GROUP BY c.patient_id
)

SELECT p.patient_id,
       cp.patient_name,
       cp.age,
       DATEDIFF(fn.first_negative_date, p.first_positive_date) AS recovery_time
FROM first_positive p
JOIN first_negative fn
ON p.patient_id = fn.patient_id
JOIN patients cp
ON p.patient_id = cp.patient_id
ORDER BY recovery_time, cp.patient_name;