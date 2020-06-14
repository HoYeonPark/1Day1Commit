package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스타트와링크_14889 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(map, new int[N/2], 0, 0);
		
		System.out.println(min);
	}
	static int min = Integer.MAX_VALUE;
	static void comb(int[][] map, int[] sel, int idx, int cnt) {
		if(cnt == sel.length) {
			int sum = 0;
			int sum2 = 0;
			
			for(int i=0; i<sel.length; i++) {
				for(int j=0; j<sel.length; j++) {
					sum += map[sel[i]][sel[j]];
				}
			}
			int[] sel2 = new int[sel.length];
			int idx1 = 0;
			int idx2 = 0;
			for(int i=0; i<map.length; i++) {
				if(i!=sel[idx1])
					sel2[idx2++] = i;
				else
					idx1++;
				if(idx2==sel.length)
					break;
				if(idx1==sel.length)
					idx1--;
			}
			for(int i=0; i<sel2.length; i++) {
				for(int j=0; j<sel2.length; j++) {
					sum2 += map[sel2[i]][sel2[j]];
				}
			}
			
			min = Math.min(Math.abs(sum-sum2), min);
			return;
		}
		if(idx == map.length)
			return;
		
		sel[cnt] = idx;
		comb(map, sel, idx+1, cnt+1);
		comb(map, sel, idx+1, cnt);
		
	}
}
