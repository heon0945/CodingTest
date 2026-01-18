import java.util.*;
import java.io.*;

class Solution {
    int size;
    Set<Integer> primes;
    boolean[] visited;
    
    public int solution(String numbers) {
        size = numbers.length();
        primes = new HashSet<>();
        visited = new boolean[size];
        
        permutation(numbers, 0, "");
        
        return primes.size();
    }
    
    public void permutation(String numbers, int order, String str){
        if(order > 0){
            int n = Integer.parseInt(str);
            if(isPrime(n)) primes.add(n);
        }
        
        if(order == size) return;
        
        for(int i = 0; i < size; i++){
            if(visited[i]) continue;
            visited[i] = true;
            permutation(numbers, order + 1, str + numbers.charAt(i));
            visited[i] = false;
        }
    }
    
    public boolean isPrime(int n){
        if(n == 0 || n == 1) return false;
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0) return false;
        }
        return true;
    }
}