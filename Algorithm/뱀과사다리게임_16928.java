package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 뱀과사다리게임_16928 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			lad.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			snake.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		dp = new int[101];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		go(1, 0);
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[100]);
	}
	static int[] dp;
	static Map<Integer, Integer> lad = new HashMap<Integer, Integer>();
	static Map<Integer, Integer> snake = new HashMap<Integer, Integer>();
	static void go(int idx, int cnt) {
		if(idx > 100)
			return;
		if(idx==100) {
			dp[100] = Math.min(dp[100], cnt);
			return;
		}
		
		if(dp[idx] <= cnt)
			return;
		
		dp[idx] = cnt;
		if(lad.get(idx)!=null)
			go(lad.get(idx), cnt);
		else if(snake.get(idx)!=null)
			go(snake.get(idx), cnt);
		else {
			go(idx+1, cnt+1);
			go(idx+2, cnt+1);
			go(idx+3, cnt+1);
			go(idx+4, cnt+1);
			go(idx+5, cnt+1);
			go(idx+6, cnt+1);
		}
	}
}
