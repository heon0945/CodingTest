import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int n, k;
    static int[] strength;
    static boolean[] robots;
    static int stage;
    static int cnt;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();
        strength = new int[2 * n];
        robots = new boolean[2 * n];

        for(int i = 0; i < 2 * n; i++){
            strength[i] = sc.nextInt();
        }

        while(true){
            stage++;

            //1. 벨트 회전
            rotating();
            //2. 로봇 이동
            moving();
            //3. 로봇 올리기
            newRobot();
            //4. 종료 판단
            if(isEnd()) break;
        }

        System.out.println(stage);
    }

    static void rotating() {
        int tmpS = strength[2 * n - 1];
        boolean tmpR = robots[2 * n - 1];

        for(int i = 2 * n - 2; i >= 0; i--){
            strength[i + 1] = strength[i];
            robots[i + 1] = robots[i];
        }
        strength[0] = tmpS;
        robots[0] = tmpR;

        //이동 후 로봇 내리기
        robots[n-1] = false;
    }

    static void moving() {
        for(int i = 2 * n - 1; i >= 0; i--){
            if(robots[i] && !robots[i+1] && strength[i+1] > 0){
                robots[i+1] = true; robots[i] = false; // 이동
                strength[i+1]--;
                if(strength[i+1] <= 0) cnt++;
                robots[n-1] = false;
            }
        }
    }

    static void newRobot(){
        if(!robots[0] && strength[0] > 0){
            robots[0] = true;
            strength[0]--;
            if(strength[0] <= 0) cnt++;
        }
    }

    static boolean isEnd(){
        return cnt >= k;
    }
}