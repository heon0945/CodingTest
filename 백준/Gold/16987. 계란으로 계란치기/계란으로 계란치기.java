import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static Egg[] eggs;

    static int ans;



    static class Egg{
        int s, w;
        public Egg(int s, int w){
            this.s = s;
            this.w = w;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        eggs = new Egg[n];

        for(int i = 0; i < n; i++){
            eggs[i] = new Egg(sc.nextInt(), sc.nextInt());
        }

        dfs(0, 0); //현재 계란 위치, 깨진 계란 수

        System.out.println(ans);
    }

    public static void dfs(int pos, int cnt){

        //정답 갱신
        if(cnt > ans) ans = cnt;

        //마지막 계란이라면
        if(pos == n) return;

        //현재 계란이 깨진 상태라면
        if(eggs[pos].s <= 0) {
            dfs(pos + 1, cnt);
            return;
        }

        for(int i = 0; i < n; i++){
            if(i == pos) continue;

            if(eggs[i].s <= 0) continue;

            //계란 깨기

            int newcnt = cnt;

            eggs[pos].s -= eggs[i].w;
            eggs[i].s -= eggs[pos].w;

            //깨진 계란 개수 갱신
            if(eggs[pos].s <= 0) newcnt++;
            if(eggs[i].s <= 0) newcnt++;

            dfs(pos + 1, newcnt);

            eggs[pos].s += eggs[i].w;
            eggs[i].s += eggs[pos].w;
        }


    }
}
