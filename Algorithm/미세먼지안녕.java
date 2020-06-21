package BaekJoon;

import java.util.Scanner;

public class 미세먼지안녕 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		int T = sc.nextInt();
		
		int[][] map = new int[R][C];
		int cleaner = 0;
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = sc.nextInt();
				// 청정기는 밑을 기준으로 삼지 뭐
				if(map[i][j]==-1)
					cleaner = i;
			}
		}
		int[] dy = {0, 0, -1, 1};
		int[] dx = {-1, 1, 0, 0};
		// T 시간만큼 돌고
		for(int t=0; t<T; t++) {
			int[][] calc = new int[R][C];
			// 미세먼지를 먼저 퍼뜨린 다음,
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					// 청정기이거나 미세먼지가 없으면 pass~~@@
					if(map[i][j]==-1 || map[i][j]==0)
						continue;
					
					int sum = 0;
					for(int k=0; k<4; k++) {
						int my = i + dy[k];
						int mx = j + dx[k];
						
						if(my < 0 || my >= R || mx < 0 || mx >= C || map[my][mx]==-1)
							continue;
						// 확산시켜볼까?
						calc[my][mx] += map[i][j]/5;
						sum += map[i][j]/5;
//						if(i==0 && j==C-1)
//							System.out.println(sum + "," + calc[i][j]);
//						if(i==1 && j==C-1)
//							System.out.println(sum + "," + calc[i][j]);
					}
					calc[i][j] -= sum;
				}
			}
			// 확산!! 
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					map[i][j] += calc[i][j];
				}
			}
			// 이제 돌리고~ 돌리고~~
			// 위부터 돌릴까? 반시계 방향이야~~!!
			// →→
			int tmp = map[cleaner-1][C-1];
			for(int j=C-1; j>1; j--) {
				map[cleaner-1][j] = map[cleaner-1][j-1];
			}
			map[cleaner-1][1] = 0;
			
			// ↑↑
			int tmp2 = map[0][C-1];
			for(int i=0; i<cleaner-1; i++) {
				map[i][C-1] = map[i+1][C-1];
			}
			map[cleaner-2][C-1] = tmp;
			
			// ←←
			tmp = map[0][0];
			for(int j=0; j<C-2; j++) {
				map[0][j] = map[0][j+1];
			}
			map[0][C-2] = tmp2;
			
			// ↓↓
			for(int i=cleaner-2; i>1; i--) {
				map[i][0] = map[i-1][0];
			}
			map[1][0] = tmp;
			
			// 아이고 힘들다 ㅠㅠ 이제 아래 돌려야지..! 시계 방향이야..!!! ㅠㅠ
			// →→
			tmp = map[cleaner][C-1];
			for(int j=C-1; j>1; j--) {
				map[cleaner][j] = map[cleaner][j-1];
			}
			map[cleaner][1] = 0;
			
			// ↓↓
			tmp2 = map[R-1][C-1];
			for(int i=R-1; i>cleaner+1; i--) {
				map[i][C-1] = map[i-1][C-1];
			}
			map[cleaner+1][C-1] = tmp;
			
			// ←←
			tmp = map[R-1][0];
			for(int j=0; j<C-2; j++) {
				map[R-1][j] = map[R-1][j+1];
			}
			map[R-1][C-2] = tmp2;
			
			// ↑↑
			for(int i=cleaner+1; i<R-1; i++) {
				map[i][0] = map[i+1][0];
			}
			map[R-2][0] = tmp;
		}
		
		int sum = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sum += map[i][j];
			}
		}
		sum += 2;
		System.out.println(sum);
	}
}
