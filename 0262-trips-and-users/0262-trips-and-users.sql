SELECT RC.Day, ROUND(IFNULL(CC.CancelCount, 0) / RC.RequestCount, 2) AS 'Cancellation Rate'
FROM
(SELECT request_at AS Day, COUNT(*) AS RequestCount
FROM Trips
WHERE client_id IN (SELECT users_id FROM Users WHERE banned = "No") AND
    driver_id IN (SELECT users_id FROM Users WHERE banned = "No") 
GROUP BY request_at
HAVING request_at IN ("2013-10-01", "2013-10-02", "2013-10-03")
) RC
LEFT JOIN
(SELECT request_at AS Day, IFNULL(COUNT(*), 0) AS CancelCount
FROM Trips
WHERE client_id IN (SELECT users_id FROM Users WHERE banned = "No") AND
    driver_id IN (SELECT users_id FROM Users WHERE banned = "No") AND
    status != "completed"
GROUP BY request_at
HAVING request_at IN ("2013-10-01", "2013-10-02", "2013-10-03")
) CC
ON RC.Day = CC.Day