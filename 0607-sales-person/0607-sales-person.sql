SELECT name
FROM SalesPerson
WHERE name NOT IN 
(
    SELECT S.name
    FROM Orders O
    LEFT JOIN Company C
    ON O.com_id = C.com_id
    LEFT JOIN SalesPerson S
    ON O.sales_id = S.sales_id
    WHERE C.name = 'RED'
)