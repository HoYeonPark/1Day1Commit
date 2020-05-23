package Baekjoon5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 카드구매하기_11052 {
	static int max = 0;
	static int[] memocost;
	static int[] memocnt;
	public static void buy(int[] cardcost, int curcost, int cardcnt, int N) {
		if(cardcnt==N) {
			max = Math.max(max, curcost);
			return;
		}
		
		for(int i=0; i<N; i++) {
			int cnt = cardcnt + i;
			int cost = curcost + cardcost[i];
			if(cnt >= N)
				break;
			
			if(memocost[cnt] >= cost && memocnt[cnt] <= cnt)
				continue;
			
			if(memocost[cnt] < cost) {
				memocost[cnt] = cost;
				memocnt[cnt] = cnt;
			}
			buy(cardcost, cost, cnt+1, N);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] cardcost = new int[N];
		memocost = new int[N];
		memocnt = new int[N];
		Arrays.fill(memocnt, N+1);
		
		for(int i=0; i<N; i++) {
			cardcost[i] = Integer.parseInt(st.nextToken());
		}
		buy(cardcost, 0, 0, N);
		System.out.println(max);
	}
}
