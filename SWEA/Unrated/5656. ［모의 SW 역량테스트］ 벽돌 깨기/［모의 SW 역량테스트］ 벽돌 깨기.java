import java.io.*;
import java.util.*;
 
public class Solution {
     
    static int tc;
    static int n, w, h;
    static int map[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    static int output[];
    static int cnt;
    static int answer;
     
    static class Cell{
        int x, y, power;
 
        public Cell(int x, int y, int power) {
            super();
            this.x = x;
            this.y = y;
            this.power = power;
        }
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
         
        tc = sc.nextInt();
        for(int t = 1; t <= tc; t++) {
            sb.append("#").append(t).append(" ");
             
            answer = Integer.MAX_VALUE;
            cnt = 0;
            n = sc.nextInt();
            w = sc.nextInt();
            h = sc.nextInt();
            map = new int[h][w];
            output = new int[n];
             
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    map[i][j] = sc.nextInt();
                    if(map[i][j] != 0) cnt++; 
                }
            }
             
            //공격할 순서 정하기
            permu(0);
             
            sb.append(answer).append('\n');
        }
        System.out.println(sb);
    }
     
    static boolean OOB(int x, int y) {
        return x < 0 || x >= h || y < 0 || y >= w;
    }
     
    static int attack(int[][] tmp, int pos) {
         
        Queue<Cell> q = new ArrayDeque<>();
        boolean visit[][] = new boolean[h][w];
         
        int blockCnt = 0;
         
        //first attack point
        for(int i = 0; i < h; i++) {
            if(tmp[i][pos] != 0) {
                q.add(new Cell(i, pos, tmp[i][pos]));
                visit[i][pos] = true;
                break;
            }
        }
         
        while(!q.isEmpty()) {
            Cell cur = q.poll();
            tmp[cur.x][cur.y] = 0;
            blockCnt++;
             
            for(int i = 0; i < 4; i++) {
                for(int k = 1; k < cur.power; k++) {
                    int tx = cur.x + k * dx[i];
                    int ty = cur.y + k * dy[i];
                     
                    if(OOB(tx, ty)) continue;
                    if(visit[tx][ty]) continue;
                    if(tmp[tx][ty] == 0) continue;
                    q.add(new Cell(tx, ty, tmp[tx][ty]));
                    visit[tx][ty] = true;
                }
            }
        }
        //System.out.println(blockCnt);
        return blockCnt;
    }
     
    static void printMap(int[][] tmp) {
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                System.out.print(tmp[i][j] + " ");
            }
            System.out.println();
        }
    }
     
    static void gravity(int[][] tmp) {
        for(int j = 0; j < w; j++) {
            for(int i = h-2; i >= 0; i--) {
                if(tmp[i][j] == 0) continue;
                int pos = i;
                while(true) {
                    //다음 좌표 확인
                    pos = pos + 1;
                    if(pos >= h || tmp[pos][j] != 0) {
                        if(pos == i+1) break;
                        tmp[pos-1][j] = tmp[i][j];
                        tmp[i][j] = 0;
                        break;
                    }
                    else continue;
                }
            }
        }
         
    }
     
    static void gameStart() {
        int turn = 0;
        int tmp[][] = new int[h][w];
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        int blockCnt = 0;
         
        while(turn < n) {
             
            //attack
            blockCnt += attack(tmp, output[turn]);
 
            //gravity
            gravity(tmp);
             
            //printMap(tmp);
             
            turn++;
        }
         
        //calc answer
        answer = Math.min(answer, cnt - blockCnt);
    }
     
    static void permu(int order) {
        if(order == n) {
            //System.out.println(Arrays.toString(output));
             
            gameStart();
             
            return;
        }
         
        for(int i = 0; i < w; i++) {
            output[order] = i;
            permu(order + 1);
        }
    }
}