import java.util.*;

class Solution {
    int[][] map;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[101][101]; //2배 스케일링하면 1~100 좌표 가능
        
        drawEdge(rectangle);
        
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }
    
    public void drawEdge(int[][] rectangle){
        for(int[] r : rectangle){
            int sx = r[0] * 2;
            int sy = r[1] * 2;
            int ex = r[2] * 2;
            int ey = r[3] * 2;
            
            for(int i = sy; i <= ey; i++){
                for(int j = sx; j <= ex; j++){
                    
                    if(i == sy || i == ey || j == sx || j == ex){
                        if(map[i][j] != 2) map[i][j] = 1;
                    }
                    else{
                        map[i][j] = 2;
                    }
                }
            }
            
        }
        
    }
    
    public int bfs(int sx, int sy, int ex, int ey){
        boolean[][] visited = new boolean[101][101];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        
        q.add(new int[]{sy, sx, 0});
        visited[sy][sx] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
                
            if(cur[0] == ey && cur[1] == ex) return cur[2];
            
            for(int i = 0; i < 4; i++){
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                
                if(ny < 0 || ny > 100 || nx < 0 || nx > 100) continue;
                if(visited[ny][nx]) continue;
                if(map[ny][nx] != 1) continue;
                
                q.add(new int[]{ny, nx, cur[2] + 1});
                visited[ny][nx] = true;
            }  
        }
        
        return 100000;
        
    }
}