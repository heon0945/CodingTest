import java.io.*;
import java.util.*;

public class Main {

    static int n, m ,k;
    static char[][] map;
    static Map<String, Integer> cnt;
    static int[] dx = {-1, 1, 0, 0, -1, 1, 1, -1}; // 8방향: 상하좌우 + 대각선
    static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};

    static String[] strs;
    static int max = 0;

    public static class Cell{
        int x, y;
        String str;
        public Cell(int x, int y, String str){
            this.x = x;
            this.y = y;
            this.str = str;
        }
    }

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // n, m, k 입력
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        cnt = new HashMap<>();
        strs = new String[k];
        for (int i = 0; i < k; i++) {
            strs[i] = br.readLine();
            cnt.put(strs[i], 0);
            max = Math.max(max, strs[i].length());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bfs(i, j);
            }
        }


        for (String s : strs) {
            sb.append(cnt.get(s)).append('\n');
        }
        System.out.print(sb);

    }

    static void bfs(int sx, int sy) {
        Queue<Cell> queue = new ArrayDeque<>();

        queue.add(new Cell(sx, sy, Character.toString(map[sx][sy])));

        while (!queue.isEmpty()) {
            Cell cur = queue.poll();

            // 현재 만든 문자열이 좋아하는 문자열 중 하나라면 카운트 증가
            if (cnt.containsKey(cur.str)) {
                cnt.put(cur.str, cnt.get(cur.str) + 1);
            }

            // 문자열 길이가 이미 최대이면, 더 이상 확장하지 않음
            if (cur.str.length() >= max) continue;

            // 8방향으로 다음 위치 계산
            for (int dir = 0; dir < 8; dir++) {
                int curx = cur.x + dx[dir];
                int cury = cur.y + dy[dir];

                // 보드 경계 넘어가면 반대편으로 순환
                if (curx < 0) curx = n - 1;
                else if (curx >= n) curx = 0;
                if (cury < 0) cury = m - 1;
                else if (cury >= m) cury = 0;

                // 새로운 문자열 추가하고 큐에 넣기
                String nstr = cur.str + map[curx][cury];
                queue.add(new Cell(curx, cury, nstr));
            }
        }

    }
}