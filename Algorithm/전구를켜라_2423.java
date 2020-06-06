package BaekJoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 전구를켜라_2423 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if((N%2==0 && M%2==1) || (N%2==1 && M%2==0)) {
			System.out.println("NO SOLUTION");
			return;
		}
		
		map = new char[N][M];
		visited = new int[N][M][2];
		String input;
		for(int i=0; i<N; i++) {
			input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				visited[i][j][0] = Integer.MAX_VALUE;
				visited[i][j][1] = Integer.MAX_VALUE;
			}
		}
		
		if(map[0][0]=='\\')
			move(0, 0, '\\', 0);
		else
			move(0, 0, '\\', 1);
		System.out.println(min);
	}
	static int N, M, min=Integer.MAX_VALUE;
	static char[][] map;
	static int[][][] visited;
	static void move(int y, int x, char curstatus, int changeCnt) {
		if(y==N-1 && x==M-1) {
			min = Math.min(min, changeCnt);
			return;
		}
		if(changeCnt >= min)
			return;
		
//		System.out.println(y + " " + x + " " + changeCnt);
		if(curstatus=='\\') {
			if(x+1 < M && changeCnt < visited[y][x+1][0]) {
				visited[y][x+1][0] = changeCnt;
				if(map[y][x+1]=='/')
					move(y, x+1, '/', changeCnt);
				else
					move(y, x+1, '/', changeCnt+1);
			}
			if(y+1 < N && changeCnt < visited[y+1][x][0]) {
				visited[y+1][x][0] = changeCnt;
				if(map[y+1][x]=='/')
					move(y+1, x, '/', changeCnt);
				else
					move(y+1, x, '/', changeCnt+1);
			}
			if(y+1 < N && x+1 < M && changeCnt < visited[y+1][x+1][0]) {
				visited[y+1][x+1][0] = changeCnt;
				if(map[y+1][x+1]=='\\')
					move(y+1, x+1, '\\', changeCnt);
				else
					move(y+1, x+1, '\\', changeCnt+1);
			}
			if(x-1 >= 0 && changeCnt < visited[y][x-1][0]) {
				visited[y][x-1][0] = changeCnt;
				if(map[y][x-1]=='/')
					move(y, x-1, '/', changeCnt);
				else
					move(y, x-1, '/', changeCnt+1);
			}
			if(y-1 >= 0 && changeCnt < visited[y-1][x][0]) {
				visited[y-1][x][0] = changeCnt;
				if(map[y-1][x]=='/')
					move(y-1, x, '/', changeCnt);
				else
					move(y-1, x, '/', changeCnt+1);
			}
			if(y-1 >= 0 && x-1 >= 0 && changeCnt < visited[y-1][x-1][0]) {
				visited[y-1][x-1][0] = changeCnt;
				if(map[y-1][x-1]=='\\')
					move(y-1, x-1, '\\', changeCnt);
				else
					move(y-1, x-1, '\\', changeCnt+1);
			}
		}
		
		else if(curstatus=='/') {
			if(x+1 < M && changeCnt < visited[y][x+1][1]) {
				visited[y][x+1][0] = changeCnt;
				if(map[y][x+1]=='\\')
					move(y, x+1, '\\', changeCnt);
				else
					move(y, x+1, '\\', changeCnt+1);
			}
			if(y+1 < N && changeCnt < visited[y+1][x][1]) {
				visited[y+1][x][0] = changeCnt;
				if(map[y+1][x]=='\\')
					move(y+1, x, '\\', changeCnt);
				else
					move(y+1, x, '\\', changeCnt+1);
			}
			if(y+1 < N && x-1 >= 0 && changeCnt < visited[y+1][x-1][1]) {
				visited[y+1][x-1][0] = changeCnt;
				if(map[y+1][x-1]=='/')
					move(y+1, x-1, '/', changeCnt);
				else
					move(y+1, x-1, '/', changeCnt+1);
			}
			if(x-1 >= 0 && changeCnt < visited[y][x-1][1]) {
				visited[y][x-1][0] = changeCnt;
				if(map[y][x-1]=='\\')
					move(y, x-1, '\\', changeCnt);
				else
					move(y, x-1, '\\', changeCnt+1);
			}
			if(y-1 >= 0 && changeCnt < visited[y-1][x][1]) {
				visited[y-1][x][0] = changeCnt;
				if(map[y-1][x]=='\\')
					move(y-1, x, '\\', changeCnt);
				else
					move(y-1, x, '\\', changeCnt+1);
			}
			if(y-1 >= 0 && x+1 < M && changeCnt < visited[y-1][x+1][1]) {
				visited[y-1][x+1][0] = changeCnt;
				if(map[y-1][x+1]=='/')
					move(y-1, x+1, '/', changeCnt);
				else
					move(y-1, x+1, '/', changeCnt+1);
			}
		}
	}
}
