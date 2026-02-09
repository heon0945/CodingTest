WITH gain_tb AS
(
    SELECT stock_name, SUM(price) AS gain
    FROM Stocks
    WHERE operation='Sell'
    GROUP BY stock_name
),
loss_tb AS
(
    SELECT stock_name, SUM(price) AS loss
    FROM Stocks
    WHERE operation='Buy'
    GROUP BY stock_name
)

SELECT g.stock_name, g.gain - l.loss AS capital_gain_loss
FROM gain_tb g
JOIN loss_tb l
ON g.stock_name = l.stock_name