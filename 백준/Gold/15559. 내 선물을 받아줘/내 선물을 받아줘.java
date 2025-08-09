import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static char[][] map;
    static int[] parents;
    static boolean[][] visited;
    static Map<Character, Integer> direction; // N W E S 방향에 맞는 dx, dy 인덱스 저장
    static int dx[] = {-1, 0, 0, 1}; // N W E S
    static int dy[] = {0, -1, 1, 0};
    static int answer;

    public static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void make() {
        for (int i = 0; i < n * m; i++) {
            parents[i] = -1;
        }
    }

    public static int find(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        if (parents[rootA] < parents[rootB]) { // rootA 집합이 더 큼
            parents[rootA] += parents[rootB];
            parents[rootB] = rootA;
        } else {
            parents[rootB] += parents[rootA];
            parents[rootA] = rootB;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m];
        parents = new int[n * m];
        direction = new HashMap<>();

        direction.put('N', 0);
        direction.put('W', 1);
        direction.put('E', 2);
        direction.put('S', 3);

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        make();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        for (int i = 0; i < n * m; i++) {
            if (parents[i] < 0) answer++;
        }

        System.out.println(answer);
    }

    public static void bfs(int x, int y) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int dir = direction.get(map[cur.x][cur.y]);

            int nx = cur.x + dx[dir];
            int ny = cur.y + dy[dir];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            int curIdx = cur.x * m + cur.y;
            int nextIdx = nx * m + ny;

            union(curIdx, nextIdx); // 항상 합치기

            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
            }
        }
    }
}
