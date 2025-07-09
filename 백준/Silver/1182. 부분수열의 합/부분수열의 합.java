import java.io.*;
import java.util.*;

public class Main {

    static int n, s;
    static int numbers[];
    static int ans = 0;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        s = sc.nextInt();

        numbers = new int[n];

        for(int i = 0; i < n; i++){
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);

        calculate(0, 0, 0);

        System.out.println(ans);
    }

    public static void calculate(int cur, int sum, int pick){

        if(cur == n) {
            if(sum == s && pick != 0){
                ans++;
            }
            return;
        }
        //포함하는 경우
        calculate(cur + 1, sum + numbers[cur], pick + 1);
        //포함 안하는 경우
        calculate(cur + 1, sum, pick);
    }
}
