package BaekJoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 포도주시식_2156 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] wine = new int[n];
		int[][] dp = new int[n][3];
		
		for(int i=0; i<n; i++)
			wine[i] = Integer.parseInt(br.readLine());
		
		dp[0][1] = wine[0];
		for(int i=1; i<n; i++) {
			// 안먹어!
			dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
			// 드디어 한잔 먹어!
			dp[i][1] = dp[i-1][0] + wine[i];
			// 마신김에 두잔 먹어!
			dp[i][2] = dp[i-1][1] + wine[i];
		}
		
		System.out.println(Math.max(dp[n-1][2], Math.max(dp[n-1][0], dp[n-1][1])));
	}
}
