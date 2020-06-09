package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 윌리암슨수액빨이딱따구리_17129 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		Queue<pos> queue = new LinkedList<>();
		
		String input;
		
		for(int i=0; i<n; i++) {
			input = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = (int)(input.charAt(j)-'0');
				if(map[i][j]==2)
					queue.add(new pos(i, j, 0));
			}
		}
		
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		boolean[][] visited = new boolean[n][m];
		pos pos;
		int dist = 0;
		
		while(!queue.isEmpty()) {
			pos = queue.poll();
			visited[pos.y][pos.x] = true;
			if(map[pos.y][pos.x] != 0 && map[pos.y][pos.x]!=2) {
				dist = pos.dist;
				break;
			}
			for(int k=0; k<4; k++) {
				int my = pos.y + dy[k];
				int mx = pos.x + dx[k];
				if(my < 0 || my >= n || mx < 0 || mx >= m || visited[my][mx])
					continue;
				
				if(map[my][mx]==1)
					continue;
				
				visited[my][mx] = true;
				queue.add(new pos(my, mx, pos.dist+1));
			}
		}
		
		if(dist==0)
			System.out.println("NIE");
		else {
			System.out.println("TAK");
			System.out.println(dist);
		}
	}
	static class pos{
		int y;
		int x;
		int dist;
		public pos(int y, int x, int dist) {
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
}
