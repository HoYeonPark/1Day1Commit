package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 테트로미노_14500_최적화 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int cnt, tmp, min, my, mx;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = true;
				dfs(map, visited, i, j, 1, map[i][j]);
				visited[i][j] = false;
				
				// ㅗ 검사하기
				cnt = 0;
				tmp = map[i][j];
				min = Integer.MAX_VALUE;
				for(int k=0; k<4; k++) {
					my = i + dy[k];
					mx = j + dx[k];
					if(my < 0 || my >= N || mx < 0 || mx >= M )
						continue;
					tmp += map[my][mx];
					cnt++;
					min = Math.min(min, map[my][mx]);
				}
				if(cnt==4) 
					tmp -= min;
				max = Math.max(max, tmp);
			}
		}
		
		System.out.println(max);
	}
	static int max = -1;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	static int N;
	static int M;
	static void dfs(int[][] map, boolean[][] visited, int y, int x, int cnt, int sum) {
		
		if(cnt==4) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int k=0; k<4; k++) {
			int my = y + dy[k];
			int mx = x + dx[k];
			
			if(my < 0 || my >= N || mx < 0 || mx >= M || visited[my][mx])
				continue;
			
			visited[my][mx] = true;
			dfs(map, visited, my, mx, cnt+1, sum+map[my][mx]);
			visited[my][mx] = false;
		}
	}
}
