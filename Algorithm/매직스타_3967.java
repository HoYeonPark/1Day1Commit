package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 매직스타_3967 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] star = new char[5][9];
		int[] num = new int[13];
		boolean[] check = new boolean[13];
		int idx = 1;
		int cnt = 0;
		for(int i=0; i<5; i++) {
			String input = br.readLine();
			for(int j=0; j<9; j++) {
				star[i][j] = input.charAt(j);
				if(star[i][j]=='X') 
					idx++;
				else if(star[i][j]!='.') {
					num[idx++] = star[i][j]-64;
					check[star[i][j]-64] = true;
					cnt++;
				}
			}
		}
		
		// 각 숫자당 2개의 줄에 더해진다.
	}
	static void magicstar(int[] num, boolean[] check, int[][] linesum, int cnt) {
		if(cnt==12) {
			
			
			return;
		}
		
		for(int i=1; i<13; i++) {
			if(num[i]==0) {
				for(int j=1; j<13; j++) {
					if(check[j])
						continue;
					num[i] = j;
					check[j] = true;
					
					magicstar(num, check, linesum, cnt+1);
					num[i] = 0;
					check[j] = false;
				}
			}
		}
	}
}	
