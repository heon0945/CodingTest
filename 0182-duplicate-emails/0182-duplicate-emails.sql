WITH email_cnt AS
(
SELECT email, COUNT(*) AS cnt
FROM Person
GROUP BY email
)
SELECT email AS Email
FROM email_cnt
WHERE cnt >= 2