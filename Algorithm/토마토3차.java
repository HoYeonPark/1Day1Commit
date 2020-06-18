package BaekJoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 토마토3차 {
	static class tomato{
		int h;
		int y;
		int x;
		int cnt;
		public tomato(int h, int y, int x, int cnt) {
			this.h = h;
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int H = sc.nextInt();
		int[][][] map = new int[H][N][M];
		Queue<tomato> queue = new LinkedList<>();
		
		int tomatoCnt = 0;
		for(int k=0; k<H; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					map[k][i][j] = sc.nextInt();
					if(map[k][i][j]==0)
						tomatoCnt++;
					else if(map[k][i][j]==1) 
						// 잘 익은 토마토로부터 모든 것이 시작된다.. 크하하하하
						queue.add(new tomato(k, i, j, 1));
				}
			}
		}
		int[] dh = { -1, 1, 0, 0, 0, 0};
		int[] dy = { 0, 0, -1, 1, 0, 0};
		int[] dx = { 0, 0, 0, 0, -1, 1};
		tomato tomato;
		int day = 0;
		while(!queue.isEmpty()) {
			if(tomatoCnt==0)
				break;
			tomato = queue.poll();
			if(day < tomato.cnt)
				day = tomato.cnt;
			for(int i=0; i<6; i++) {
				int mh = tomato.h + dh[i];
				int my = tomato.y + dy[i];
				int mx = tomato.x + dx[i];
				
				if(mh < 0 || mh >= map.length || my < 0 || my >= map[0].length
						|| mx < 0 || mx >= map[0][0].length) 
					continue;
				else if(map[mh][my][mx]==-1 || map[mh][my][mx]==1){
					continue;
				}
				else {
					map[mh][my][mx] = 1;
					tomatoCnt--;
					queue.add(new tomato(mh, my, mx, tomato.cnt+1));
				}
			}
		}
		if(tomatoCnt==0)
			System.out.println(day);
		else
			System.out.println(-1);
	}
}
