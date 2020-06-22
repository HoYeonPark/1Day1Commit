package BaekJoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 아기상어 {
	static int N;
	static int[][] map;
	// y좌표랑, x좌표랑, 자기 크기랑, 먹은 물고기들!
	static int[] baby = new int[4];
	static int smallfish;
	static int count[] = new int[7];
	static int idx = 2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		smallfish = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==0)
					continue;
				if(map[i][j]==9) {
					// y
					baby[0] = i;
					// x
					baby[1] = j;
				}
				else if(map[i][j] < 2)
					smallfish++;
				else if(map[i][j] >= 2)
					count[map[i][j]]++;
			}
		}
		baby[2] = 2;
		baby[3] = 0;
		if(smallfish==0)
			System.out.println(0);
		else {
			hunting(baby[0], baby[1]);
			System.out.println(time);
		}
	}
	static int time = 0;
	static void hunting(int y, int x) {
		if(smallfish == 0)
			return;
		// 1. 크기가 작은 놈만 먹을 수 있고
		// 2. 크기가 큰 놈이 있으면 못 지나가고
		// 3. 아무튼 상어녀석이 먹을 물고기는 콕! 집어서 정해져 있음 ㅇㅇ.
		int[] dy = {0, 0, -1, 1};
		int[] dx = {-1, 1, 0, 0};
		boolean[][] visited = new boolean[N][N];
		
		// 맵 탐색할 큐..
		Queue<pos> queue = new LinkedList<>();
		// 손 닿는 먹이 리스트
		ArrayList<pos> list = new ArrayList<>();
		queue.add(new pos(y, x, 0));
		pos pos;
		int dist = Integer.MAX_VALUE;
		
		while(!queue.isEmpty()) {
			pos = queue.poll();
			if(dist < pos.dist)
				continue;
			visited[pos.y][pos.x] = true;
			for(int k=0; k<4; k++) {
				int my = pos.y + dy[k];
				int mx = pos.x + dx[k];
				if(my < 0 || my >= N || mx < 0 || mx >= N || visited[my][mx])
					continue;
				
				if(map[my][mx] > baby[2]) {
					visited[my][mx] = true;
					continue;
				}
				
				if(map[my][mx]==0 || map[my][mx] == baby[2]) {
					visited[my][mx] = true;
					queue.add(new pos(my, mx, pos.dist+1));
					continue;
				}
				// 근데 동시에 도착하는 경우.. 를 처리해야하니 저장할거 하나 더 만들래..
				if(map[my][mx] < baby[2]) {
					visited[my][mx] = true;
					list.add(new pos(my, mx, pos.dist+1));
					dist = pos.dist+1;
					continue;
				}
			}
		}
		// 먹을 물고기가 없거나, 물고기가 닿을 수 없는 곳에 있으면 엄마 불러야지..
		if(list.isEmpty())
			return;
		else {
			Collections.sort(list, new Comparator<pos>() {
				@Override
				public int compare(pos o1, pos o2) {
					if(o1.dist==o2.dist) {
						if(o1.y == o2.y)
							return o1.x - o2.x;
						else
							return o1.y - o2.y;
					}
					else
						return o1.dist - o2.dist;
				}
			});
			pos = list.get(0);
			time += pos.dist;
			map[pos.y][pos.x] = 9;
			map[baby[0]][baby[1]] = 0;
			baby[0] = pos.y;
			baby[1] = pos.x;
			baby[3]++;
			if(baby[2]==baby[3]) {
				baby[2]++;
				baby[3] = 0;
				if(idx < 7)
					smallfish += count[idx++];
			}
			smallfish--;
			hunting(baby[0], baby[1]);
		}
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
