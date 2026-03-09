import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static int n;
    static int cnt;
    static int[] fav;
    static boolean[] visited;
    static boolean[] afterTeam; //false : 팀 검사 전, true : 팀 검사 후(팀인지, 아닌지 판단 후)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            cnt = 0;

            fav = new int[n+1];
            visited = new boolean[n+1];
            afterTeam = new boolean[n+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                fav[i] = Integer.parseInt(st.nextToken());

                //혼자서 팀을 이루는 학생
                if(i == fav[i]) {
                    cnt++;
                    afterTeam[i] = true;
                }
            }

            //팀 사이클 판단
            for(int i = 1; i <= n; i++){
                if(afterTeam[i]) continue;
                makeTeam(i);
            }

            sb.append(n - cnt).append('\n');
        }

        System.out.println(sb);

    }

    public static void makeTeam(int cur){
        visited[cur] = true;

        //다음 학생
        int next = fav[cur];

        //다음 학생이 아직 방문 전 (방문을 안했다는 뜻 : 팀도 결정 전)
        if(!visited[next]) makeTeam(next);
        else{
            //방문은 했으나 팀은 아직 결정 전 (현재 판단 중에 있음) -> 다시 돌아왔으므로 사이클
            if(!afterTeam[next]) {
                cnt++;
                //현재 사이클 안에 포함된 학생 모두 정답 포함
                int iter = next;
                while(iter != cur){
                    cnt++;
                    iter = fav[iter];
                }
            }
        }

        //다음 학생이 팀이 이미 정해진 경우 (팀이 될 수 없다고 판단)
        afterTeam[cur] = true;

    }
}