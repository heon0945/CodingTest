import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] numbers;
    static int[] twoSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        numbers = new int[n];

        for(int i = 0; i < n; i++){
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        twoSum = new int[numbers.length * (numbers.length + 1) / 2];
        for(int i = 0; i < numbers.length; i++){
            for(int j = i; j < numbers.length; j++){
                twoSum[cnt] = numbers[i] + numbers[j];
                cnt++;
            }
        }

        Arrays.sort(twoSum);

        int answer = -1;
        for(int i = 0; i < numbers.length; i++){
            for(int j = 0; j < numbers.length; j++){
                if(binarySearch(numbers[i] - numbers[j])){
                    answer = Math.max(answer, numbers[i]);
                }
            }
        }

        System.out.println(answer);


    }

    static boolean binarySearch(int value){
        int left = 0;
        int right = twoSum.length - 1;

        while(left <= right){

            int mid = (left + right) / 2;

            if(twoSum[mid] > value){
                right = mid - 1;
            }
            else if(twoSum[mid] < value){
                left = mid + 1;
            }
            else{
                return true;
            }
        }
        return false;
    }

}
