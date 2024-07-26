
import java.util.*;
import java.io.*;


class Solution
{
	public static void main(String args[]) throws Exception
	{
       String str;
				
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					str = br.readLine();
					for(int i = 0; i < str.length(); i++) {
						System.out.printf("%d ", str.charAt(i) - 'A' + 1);
					}
					
					
				}catch (IOException e){
					e.printStackTrace();
				}

    }
}