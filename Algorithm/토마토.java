package BaekJoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 토마토 {
	static class riping {
		int y;
		int x;
		int time;
		public riping(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int[][] tomato = new int[N][M];
		boolean[][] visit = new boolean[N][M];
		Queue<riping> queue = new LinkedList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tomato[i][j] = sc.nextInt();
				if(tomato[i][j]==1) {
					// 잘 익은 토마토들로부터 모든 것이 시작되지.. ㅎㅎ
					queue.add(new riping(i, j, 0));
					visit[i][j] = true;
				}
			}
		}
		boolean check = false;
		int cnt = 0;
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		// 큐가 빌때까지 Go!! 더이상 익힐 토마토가 없거나, 길이 없단 것이지! 캬~
		while(!queue.isEmpty()) {
			riping one = queue.poll();
//			System.out.println(one.x + "," + one.y);
			for(int i=0; i<4; i++) {
				int mx = one.x + dx[i];
				int my = one.y + dy[i];
				// 익은 토마토가 지금 이놈까지 익히는 시간이 최대 시간이야??
				if(cnt < one.time)
					cnt = one.time;
				// 범위 밖으로는 못나가~ 안내보내줄거야~
				if(mx < 0 || mx >= tomato[0].length || my < 0 || my >= tomato.length) 
					continue;
				if(!visit[my][mx] && tomato[my][mx]==0) {
					queue.add(new riping(my,mx, one.time+1));
					tomato[my][mx] = 1;
					visit[my][mx] = true;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
//				System.out.print(tomato[i][j] + " ");
				if(tomato[i][j]==0)
					check = true;
			}
//			System.out.println();
		}
		if(check)
			System.out.println(-1);
		else
			System.out.println(cnt);
	}
}
