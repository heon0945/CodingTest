import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static List<Integer> primes;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        primes = new ArrayList<>();

        makePrime();

        int start = 0, end = 0; //투포인터 (두 개의 포인터 안에 들어온 소수들로 n을 만듦)
        int sum = 0; //연속된 소수로 만들어진 합계 (n이 되어야 함)
        int cnt = 0; //소수의 합 경우의 수

        while(true){
            if(sum >= n){
                if(sum == n) cnt++;
                sum -= primes.get(start++);
            }
            else if(end == primes.size()){
                break;
            }
            else{
                sum += primes.get(end++);
            }
        }

        System.out.println(cnt);

    }

    public static void makePrime(){
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for(int i = 2; i * i <= n; i++){
            if(isPrime[i]){
                //소수의 배수는 모두 소수가 아님
                for(int j = i * i; j <= n; j += i) isPrime[j] = false;
            }
        }

        for(int i = 1; i <= n; i++){
            if(isPrime[i]) primes.add(i);
        }
    }
}
