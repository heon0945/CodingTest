CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      SELECT DISTINCT salary
      FROM(
      SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS 'rank'
      FROM Employee
      ) E
      WHERE E.rank = N
  );
END