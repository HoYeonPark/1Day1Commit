package BaekJoon;

import java.util.Scanner;

public class 연구소 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		installWall(map, 0, 0, 0);
		System.out.println(max);
	}
	static int max = 0;
	public static void installWall(int[][] map, int y, int x, int wallCnt) {
		int n = map.length;
		int m = map[0].length;
		// 벽을 다 세웠으면 안전지역을 세야지!!
		// 근데 어떻게 세지? 바이러스 먼저 퍼뜨리고? 아니면 그냥 탐색하다 바이러스만나면 아웃?
		// 먼저 퍼뜨리고 세야지~
		if(wallCnt==3) {
			boolean[][] visit = new boolean[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(!visit[i][j] && map[i][j]==2) {
						spreadVirus(map, visit, j, i);
					}
				}
			}
			int cnt = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j]==0)
						cnt++;
				}
			}
			if(max < cnt) 
				max = cnt;
		}
		// 그게 아니라면 벽부터 세워!!
		else {
			
			// 벽을 안 세우고 가볼까??
			if(y == m-1) {
				// 맵의 끝인가비? 그럼 여기까지.
				if(x == n-1) {}
				else
					installWall(map, 0, x+1, wallCnt);
			}
			else 
				installWall(map, y+1, x, wallCnt);
			
			// 일단 벽을 세우면 맵이 달라지니까 나만 쓸거 하나 만들고..
			int [][] mymap = new int[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					mymap[i][j] = map[i][j];
				}
			}

			// 벽 세울 수 있으면 벽을 세우고도 가야지!!
			if(mymap[x][y]==0) {
				mymap[x][y] = 1;
				installWall(mymap, y, x, wallCnt+1);
			}
		}
	}
	public static void spreadVirus(int[][] map, boolean[][] visit, int y, int x) {
		if(y < 0 || y >= map[0].length)
			return;
		else if(x < 0 || x >= map.length)
			return;
		else if(visit[x][y])
			return;
		
		if(map[x][y]==0)
			map[x][y] = 2;
		else if(map[x][y]==1)
			return;

		visit[x][y] = true;
		
		spreadVirus(map, visit, y-1, x);
		spreadVirus(map, visit, y+1, x);
		spreadVirus(map, visit, y, x-1);
		spreadVirus(map, visit, y, x+1);
	}
}
