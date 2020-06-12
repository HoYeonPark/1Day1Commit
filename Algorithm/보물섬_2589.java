package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class 보물섬_2589 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		int[][][] dist = new int[N][M][4];
		Queue<pos> lqueue = new LinkedList<>();
		Queue<pos> queue = new LinkedList<>();
		String input;
		for(int i=0; i<N; i++) {
			input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j]=='L')
					lqueue.add(new pos(i, j, 0));
			}
		}
		
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		pos p;
		int max = 1;
		while(!lqueue.isEmpty()) {
			queue.add(lqueue.poll());
			boolean[][] visited = new boolean[N][M];
			while(!queue.isEmpty()) {
				p = queue.poll();
				
				if(visited[p.y][p.x])
					continue;
				
				visited[p.y][p.x] = true;
				
				if(p.dist > max)
					max = p.dist;
//				if(p.dir!=-1)
//					if(dist[p.y][p.x][p.dir] >= p.dist)
//						continue;
//					else
//						dist[p.y][p.x][p.dir] = p.dist;
				
				for(int k=0; k<4; k++) {
					int my = p.y + dy[k];
					int mx = p.x + dx[k];
					if(my < 0 || my >= N || mx < 0 || mx >= M || visited[my][mx])
						continue;
					if(map[my][mx]=='W')
						continue;
					
					queue.add(new pos(my, mx, p.dist+1));
				}
			}
		}
//		for(int k=0; k<4; k++) {
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//						System.out.print(dist[i][j][k] + " ");
//				}System.out.println();
//			}
//			System.out.println("=============");
//		}
		System.out.println(max);
	}
	static class pos{
		int y;
		int x;
		int dist;
		public pos(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
}
