WITH base AS (
    SELECT
        log_id,
        ip,
        status_code,

        -- 옥텟 개수 계산
        LENGTH(ip) - LENGTH(REPLACE(ip, '.', '')) + 1 AS octet_cnt,

        -- 각 옥텟을 컬럼으로 분리
        SUBSTRING_INDEX(ip, '.', 1)                           AS o1,
        SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 2), '.', -1) AS o2,
        SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 3), '.', -1) AS o3,
        SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 4), '.', -1) AS o4
    FROM logs
)
SELECT
    ip,
    COUNT(*) AS invalid_count
FROM (
    SELECT
        log_id,
        ip,
        CASE
            WHEN octet_cnt != 4 THEN 1

            WHEN (o1 LIKE '0%' AND o1 != '0')
              OR (o2 LIKE '0%' AND o2 != '0')
              OR (o3 LIKE '0%' AND o3 != '0')
              OR (o4 LIKE '0%' AND o4 != '0') THEN 1

            WHEN CAST(o1 AS UNSIGNED) > 255
              OR CAST(o2 AS UNSIGNED) > 255
              OR CAST(o3 AS UNSIGNED) > 255
              OR CAST(o4 AS UNSIGNED) > 255 THEN 1

            ELSE 0
        END AS is_invalid
    FROM base
) t
WHERE is_invalid = 1
GROUP BY ip
ORDER BY invalid_count DESC, ip DESC