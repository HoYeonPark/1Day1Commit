package BaekJoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 낚시왕_17143 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		// 상어 수
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		shark[] shark = new shark[M+1];
		Set<shark> life = new HashSet<>();
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			if(d==1 || d==2) 
				s %= 2*R - 2;
			else if(d==3 || d==4) 
				s %= 2*C - 2;
			
			shark[i] = new shark(r, c, s, d, z, i);
			map[r][c] = i;
			life.add(shark[i]);
		}
		Queue<shark> queue = new LinkedList<>();
		shark sk;
		int sum = 0;
		// 낚시왕 ㄱㄱㅆ
		for(int j=0; j<C; j++) {
			for(int i=0; i<R; i++) {
				if(map[i][j]!=0) {
					int num = map[i][j];
					sum += shark[num].size;
					life.remove(shark[num]);
					break;
				}
			}
			if(j==C-1)
				break;
			// 상어가 움직여야되니 0으로 초기화
			for(int k=0; k<R; k++)
				Arrays.fill(map[k], 0);
			
			// 상어들아, 도망쳐!!
			queue.addAll(life);
			while(!queue.isEmpty()) {
				sk = queue.poll();
				int my = sk.y;
				int mx = sk.x;
				
				for(int s=0; s < sk.speed; s++) {
					my += dy[sk.dir];
					mx += dx[sk.dir];
					if(my < 0 ) {
						my = 1;
						sk.dir = 2;
					}
					else if(my >= R ) {
						my = R-2;
						sk.dir = 1;
					}
					else if(mx < 0 ) {
						mx = 1;
						sk.dir = 3;
					}
					else if(mx >= C ) {
						mx = C-2;
						sk.dir = 4;
					}
				}
				sk.y = my;
				sk.x = mx;
				if(map[my][mx]==0) {
					map[my][mx] = sk.idx;
				}
				// 딴 녀석이 있으면.
				else {
					int idx = map[my][mx];
					if(shark[idx].size > sk.size) {
						life.remove(sk);
					}
					else {
						map[my][mx] = sk.idx;
						life.remove(shark[idx]);
					}
				}
//				System.out.println(sk.idx + " " + sk.speed + " " + my + " " + mx);
			}
		}
		System.out.println(sum);
	}
	// 위, 아래, 오른쪽, 왼쪽
	static int[] dy = {1234, -1, 1, 0, 0};
	static int[] dx = {1234, 0, 0, 1, -1};
	static class shark{
		int y;
		int x;
		int speed;
		int dir;
		int size;
		int idx;
		public shark(int y, int x, int speed, int dir, int size, int idx) {
			super();
			this.y = y;
			this.x = x;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
			this.idx = idx;
		}
	}
}
