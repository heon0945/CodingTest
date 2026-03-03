SELECT x, y, z, 
CASE 
WHEN x + y > z && x + z > y && y + z > x THEN 'Yes'
ELSE 'No' 
END AS triangle
FROM Triangle