import java.io.*;
import java.util.*;

public class Main {
	

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
 
        int n = scan.nextInt();
        int m = scan.nextInt();
        int answer = Integer.MAX_VALUE;
        
        int[] nums = new int[n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
 
        int left = 0;
        int right = max - min;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(canDvideByMid(mid, m, nums)) {
            	answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
	
	public static boolean canDvideByMid(int mid, int m, int[] nums) {  
        int countSet = 1;
        
        int min = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
            if((max - min) > mid) {
                min = nums[i];
                max = nums[i];
                countSet++;
                if(countSet > m) return false;
            }
        }
        return true;
    }
}