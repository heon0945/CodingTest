import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static boolean[][] visited;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};

    public static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];

        for(int i = 0; i < 12; i++){
            String str = br.readLine();
            for(int j = 0; j < 6; j++){
                map[i][j] = str.charAt(j);
            }
        }

        int cnt = 0;

        while(true){
            visited = new boolean[12][6];
            boolean pop = false;

            for(int i = 0; i < 12; i++){
                for(int j = 0; j < 6; j++){
                    if(map[i][j] != '.' && !visited[i][j]){
                        if(bfs(i, j, map[i][j])) pop = true;
                    }
                }
            }

            if(!pop) break;
            cnt++;

            //하강
            for(int j = 0; j < 6; j++){
                Stack<Character> stack = new Stack<>();
                for(int i = 0; i < 12; i++){
                    if(map[i][j] != '.'){
                        stack.push(map[i][j]);
                    }
                }
                for(int i = 11; i >= 0; i--){
                    if(stack.isEmpty()){
                        map[i][j] = '.';
                    }
                    else{
                        map[i][j] = stack.pop();
                    }
                }
            }

        }

        System.out.println(cnt);
    }

    public static boolean bfs(int x, int y, char pivot){

        List<Node> list = new ArrayList<>();
        Queue<Node> q = new ArrayDeque<>();

        visited[x][y] = true;
        list.add(new Node(x, y));
        q.add(new Node(x, y));

        while(!q.isEmpty()){

            Node cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] != pivot) continue;
                q.add(new Node(nx, ny));
                list.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }


        if(list.size() < 4) return false;

        for(int i = 0; i < list.size(); i++){
            Node cur = list.get(i);
            map[cur.x][cur.y] = '.';
        }
        return true;
    }
}