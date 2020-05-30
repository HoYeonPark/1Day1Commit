package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 용감한용사진수_14718 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 힘, 민, 지, 합
		int[][] stat = new int[N][4];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			stat[i][0] = Integer.parseInt(st.nextToken());
			stat[i][1] = Integer.parseInt(st.nextToken());
			stat[i][2] = Integer.parseInt(st.nextToken());
			stat[i][4] = stat[i][0]+stat[i][1]+stat[i][2];
		}
		
		Arrays.sort(stat, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[4]-o2[4];
			}
		});
		
		int[] pre = new int[3];
		int[] max = new int[3];
		
		for(int i=0; i<K; i++) {
			for(int k=0; k<3; k++)
				if(max[k] <= stat[i][k]) {
					pre[k] = max[k];
					max[k] = stat[i][k];
				}
		}
		
		int[] type = new int[3];
		int idx = -1;
		for(int i=K; i<N; i++) {
			for(int k=0; k<3; k++) 
				type[k] = max[k] - stat[i][k];
			
			if(type[0]+type[1]+type[2] >= 0 ) {
				if(max[0] < type[0]) {
					
				}
			}
		}
		
		System.out.println(max[0]+max[1]+max[2]);
	}

}
