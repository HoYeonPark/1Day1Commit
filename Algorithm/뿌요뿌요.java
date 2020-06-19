package BaekJoon;

import java.util.Scanner;

public class 뿌요뿌요 {
	static class block{
		int y; 
		int x;
		public block(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[][] map = new char[12][6];
		
		for(int i=0; i<12; i++) {
			String str = sc.next();
			for(int j=0; j<6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		// 터질 블록 있니??
		for(int i=0; i<12; i++) {
			for(int j=0; j<6; j++) {
				if(map[i][j]!='.') {
					block[] block = new block[4];
					block[0] = new block(i, j);
					checkPang(map, block, i, j, 1);
				}
			}
		}
	}
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {-1, 1, 0, 0};
	static void checkPang(char[][] map, block[] block, int y, int x, int blockCnt) {
		
		for(int i=0; i<4; i++) {
			int my = y + dy[i];
			int mx = x + dx[i];
			
			if(my < 0 || my >= map.length || mx < 0 || mx >= map[0].length) 
				continue;
			
			if(map[my][mx]==map[y][x]) {
				// 블록이 터질때가 됐나..
				if(blockCnt >= 4) {
					
				}
				block[blockCnt] = new block(my, mx);
				checkPang(map, block, my, mx, blockCnt+1);
			}
		}
	}
}
