WITH type_tb AS
(
    SELECT user_id, activity_type, MIN(activity_date) AS start_date, ROUND(SUM(activity_duration) / COUNT(activity_date), 2) AS avg_duration
    FROM UserActivity
    GROUP BY user_id, activity_type
),
trial_tb AS
(
    SELECT user_id, activity_type AS trial_type, start_date AS trial_date, avg_duration AS trial_avg_duration
    FROM type_tb
    WHERE activity_type = 'free_trial'
),
paid_tb AS
(
    SELECT user_id, activity_type AS paid_type, start_date AS paid_date, avg_duration AS paid_avg_duration
    FROM type_tb
    WHERE activity_type = 'paid'
)

SELECT T.user_id, trial_avg_duration, paid_avg_duration
FROM trial_tb T
JOIN paid_tb P
ON T.user_id = P.user_id
WHERE trial_date < paid_date