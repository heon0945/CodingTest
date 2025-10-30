import java.io.*;
import java.util.*;

// 1. X가 이긴 경우
    // 1-1. 게임 판이 가득차고 O가 완성되지 않은 경우
    // 1-2. O가 완성되지 않고 X가 완성 된 경우
// 2. O가 이긴 경우
    // 2-1. X가 완성되지 않고 O가 완성된 경우

public class Main {
    static char[][] board;

    static class Pair{
        int x, y;
        int cnt;
        public Pair(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String map = br.readLine();
            if(map.equals("end")) break;

            board = new char[3][3];

            int xCnt = 0;
            int oCnt = 0;

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    board[i][j] = map.charAt(i * 3 + j);
                    if(board[i][j] == 'X') xCnt++;
                    else if(board[i][j] == 'O') oCnt++;
                }
            }

            // X가 이긴 경우
            if(xCnt == oCnt+1){
                // 판이 가득차고 O가 완성되지 않은 경우
                if(xCnt+oCnt == 9 && !bingo('O'))
                    sb.append("valid");
                // O가 완성 되지 않고, X가 완성 된 경우
                else if(!bingo('O') && bingo('X'))
                    sb.append("valid");
                // 둘 다 아닌 경우는 X도 이기지 못함
                else
                    sb.append("invalid");
            }
            // O가 이긴 경우
            else if(xCnt == oCnt)
            {
                // X가 완성되지 않고, O가 완성된 경우
                if(!bingo('X') && bingo('O'))
                    sb.append("valid");
                // 그게 아니라면 먼저 두는 것은 X이기 때문에 이길 수 없음
                else
                    sb.append("invalid");
            }
            // 둘 다 완성되지 않은 경우
            else
                sb.append("invalid");

            sb.append('\n');

        }

        System.out.println(sb);
    }

    public static boolean bingo(char flag){

        for(int i = 0; i < 3; i++){
            if(board[i][0] == flag && board[i][1] == flag && board[i][2] == flag) return true;
            if(board[0][i] == flag && board[1][i] == flag && board[2][i] == flag) return true;
        }

        if(board[0][0] == flag && board[1][1] == flag && board[2][2] == flag) return true;
        if(board[0][2] == flag && board[1][1] == flag && board[2][0] == flag) return true;


        return false;
    }

}
