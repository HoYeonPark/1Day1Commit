package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 자동차경주대회_2651 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		limit = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		dist = new int[N+2];
		time = new int[N+1];
		minarr = new int[N+1];
		memo = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N+1; i++)
			dist[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			time[i] = Integer.parseInt(st.nextToken());
		
		run(new int[N], 1, 0, 0, dist[1]);

		sb.append(min).append('\n');
		sb.append(minCnt).append('\n');
		
		for(int i=0; i<minCnt; i++)
			sb.append(minarr[i]).append(" ");
		
		System.out.println(sb.toString());
	}
	static int N, limit;
	static int min = Integer.MAX_VALUE;
	static int minCnt = 0;
	static int[] minarr, memo;
	static int[] dist, time;
	static void run(int[] pick, int idx, int cnt, int mytime, int curdist) {
		if(idx > N) {
			if(min > mytime) {
				min = mytime;
				minCnt = cnt;
				for(int i=0; i<cnt; i++)
					minarr[i] = pick[i];	
			}
//			System.out.println(Arrays.toString(pick));
			return;
		}
		if(min <= mytime)
			return;
		
		if(memo[idx]!=0 && memo[idx] <= mytime)
			return;
		
		
		if(curdist + dist[idx+1] <= limit) {
			memo[idx] = mytime;
			run(pick, idx+1, cnt, mytime, curdist+dist[idx+1]);
		}
		
		pick[cnt] = idx;
		memo[idx] = mytime+time[idx];
		run(pick, idx+1, cnt+1, mytime+time[idx], dist[idx+1]);
	}
}
