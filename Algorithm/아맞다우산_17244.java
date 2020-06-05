package Baekjoon5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아맞다우산_17244 {
	static pos[] item = new pos[7];
	static int[][] dist = new int[7][7];
	static int N, M, icnt = 1, min = Integer.MAX_VALUE;
	static class pos{
		int y;
		int x;
		int time;
		public pos(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}
	static void perm(int[] sel, boolean[] visited, int idx) {
		if(idx==icnt) {
			int sum = 0;
			int next = 0;
			for(int i=1; i<icnt; i++) {
				sum += dist[next][sel[i]];
				next = sel[i];
			}
			sum += dist[next][6];
			min = Math.min(min, sum);
			return;
		}
		
		for(int i=1; i<icnt; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sel[idx] = i;
				perm(sel, visited, idx+1);
				visited[i] = false;
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][M];
		boolean[][] visited = new boolean[N][M];
		
		String input;
		
		for(int i=0; i<N; i++) {
			input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j]=='X') 
					item[icnt++] = new pos(i, j, 0);
				else if(map[i][j]=='S')
					item[0] = new pos(i, j, 0);
				else if(map[i][j]=='E')
					item[6] = new pos(i, j, 0);
			}
		}
		
		Queue<pos> queue = new LinkedList<>();
		pos p;
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
//		int findcnt = 0;
		for(int i=0; i<icnt; i++) {
			queue.clear();
			queue.add(item[i]);
			for(int n=0; n<N; n++)
				Arrays.fill(visited[n], false);
			
			while(!queue.isEmpty()) {
				p = queue.poll();
				
				for(int k=0; k<4; k++) {
					int my = p.y + dy[k];
					int mx = p.x + dx[k];
					if(my < 0 || my >= N || mx < 0 || mx >= M || visited[my][mx])
						continue;
					
					visited[my][mx] = true;
					if(map[my][mx]=='#')
						continue;
					
					if(map[my][mx]=='.' || map[my][mx]=='S')
						queue.add(new pos(my, mx, p.time+1));
					
					else if(map[my][mx]=='X') {
//						findcnt++;
						for(int j=0; j<icnt; j++) {
							if(i==j)
								continue;
							if(item[j].y==my && item[j].x==mx) {
								dist[i][j] = p.time + 1;
								dist[j][i] = p.time + 1;
								break;
							}
						}
						queue.add(new pos(my, mx, p.time+1));
//						if(findcnt==icnt+1)
//							queue.clear();
					}
					else if(map[my][mx]=='E') {
//						findcnt++;
						dist[i][6] = p.time + 1;
						dist[6][i] = p.time + 1;
						queue.add(new pos(my, mx, p.time+1));
					}
				}
			}
//			for(int ii=0; ii<7; ii++) {
//				for(int jj=0; jj<7; jj++) {
//					System.out.print(dist[ii][jj] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("===========");
		}
		perm(new int[icnt+1], new boolean[icnt+1], 1);
		System.out.println(min);
	}

}
