import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static long ans;
    static int numbers[];


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        numbers = new int[n];

        for(int i = 0; i < n; i++){
            numbers[i] = sc.nextInt();
        }

        for(int bit = 0; bit <= 20; bit++){

            long cnt0 = 0;
            long cnt1 = 0;

            for(int i = 0; i < n; i++){
                if((numbers[i] & (1 << bit)) == 0) cnt0++;
                else cnt1++;
            }

            ans += cnt0 * cnt1 * (1L << bit);

        }

        System.out.println(ans);

    }
}