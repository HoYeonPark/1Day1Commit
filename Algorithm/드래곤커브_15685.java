package Baekjoon5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 드래곤커브_15685 {
	static ArrayList<pos> dragon = new ArrayList<>();
	
	static class pos{
		int x;
		int y;
		int dir;
		public pos(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "pos [x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}
	}
	
	// 90도 회전
	public static void rotate90deg(pos curp, int idx) {
		pos prep = dragon.get(idx);
//		System.out.println(idx);
//		System.out.println("prep :" + prep);
//		System.out.println("curp :" + curp);
		int nextdir = -1;
		
		switch(prep.dir) {
		// right
		case 0:
			curp.dir = 1;
			nextdir = 1;
			break;
		// up
		case 1:
			curp.dir = 2;
			nextdir = 2;
			break;
		// left
		case 2:
			curp.dir = 3;
			nextdir = 3;
			break;
		// down
		case 3:
			curp.dir = 0;
			nextdir = 0;
			break;
		default:
			break;
		}
		
		switch(curp.dir) {
		// right
		case 0:
			curp = new pos(curp.x+1, curp.y, nextdir);
			break;
		// up
		case 1:
			curp = new pos(curp.x, curp.y-1, nextdir);
			break;
		// left
		case 2:
			curp = new pos(curp.x-1, curp.y, nextdir);
			break;
		// down
		case 3:
			curp = new pos(curp.x, curp.y+1, nextdir);
			break;
		default:
			break;
		}
//		System.out.println("next :" + curp);
//		System.out.println("=========");
		dragon.add(curp);
		
		if(idx > 0)
			rotate90deg(curp, idx-1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		boolean[][] map = new boolean[101][101];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// x, y 좌표, 방향, 세대
		int x = 0, y = 0, d = 0, g = 0;
		
		// 맵에 드래곤 커브 표시하기.
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			pos curp = new pos(x, y, d);
			dragon.clear();
			dragon.add(curp);
			
			// 시작점
			switch(curp.dir) {
			// right
			case 0:
				dragon.add(new pos(curp.x+1, curp.y, 1));
				break;
			// up
			case 1:
				dragon.add(new pos(curp.x, curp.y-1, 2));
				break;
			// left
			case 2:
				dragon.add(new pos(curp.x-1, curp.y, 3));
				break;
			// down
			case 3:
				dragon.add(new pos(curp.x, curp.y+1, 0));
				break;
			default:
				break;
			}
			
			if(g > 0) {
				// 2의 g 제곱만큼 드래곤 커브 그리기.
				for(int k=0; k<g; k++) {
					int idx = dragon.size() - 1;
					curp = dragon.get(idx);
					rotate90deg(curp, idx-1);
				}
			}
			
			for( pos p : dragon) {
//				System.out.println(p.x + ", " + p.y);
				map[p.x][p.y] = true;
			}
		}
		
		int answer = 0;
		int[][] check = new int[101][101];
		// 전 맵을 돌면서 정사각형의 드래곤 커브 찾기.
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j])
					check[i][j] = 1;
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) 
					answer++;
			}
		}
		System.out.println(answer);
		
//		for(int i=0; i<101; i++) {
//			for(int j=0; j<101; j++) {
//				System.out.print(check[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
}
