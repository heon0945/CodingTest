SELECT name AS Customers
FROM Customers
WHERE id NOT IN (
SELECT DISTINCT C.id
FROM Customers C
RIGHT JOIN Orders O
ON C.id = O.customerId )