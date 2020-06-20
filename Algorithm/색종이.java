package BaekJoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 색종이 {
	static class colorpaper{
		int x;
		int y;
		public colorpaper(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] paper = new int[10][10];
		// 색종이는 1x1, 2x2, 3x3, 4x4, 5x5짜리로 5장씩!!!
		// 색종이 개수. 0번째 idx는 안쓸예정이야
		int[] count = {0, 5,5,5,5,5};
		Queue<colorpaper> queue = new LinkedList<>();
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				paper[i][j] = sc.nextInt();
				// 색종이로 덮어야될 영역이닷!! 큐에다 때려박아!!
				if(paper[i][j]==1) {
					queue.add(new colorpaper(i, j));
				}
			}
		}
		// 색종이를 덮을 영역 검사를 어떻게 할까..........
		// 1. 색종이의 수량은 한정되어있고
		// 2. 무조건 큰 색종이를 사용한다고 되는건 아님. 
		//    (더 작은 크기의 색종이를 붙이는게 효율적일 수도 있음.)
		// 3. 그러므로 난 비명을 지른다 으아ㅏㅏㅏㅏㅏㅏㅏㄱ
		while(!queue.isEmpty()) {
			
		}
		
	}

}
