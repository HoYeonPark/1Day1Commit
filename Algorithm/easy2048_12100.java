package Baekjoon5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class easy2048_12100 {
	static int N = 0;
	static int max = 0;
	
	public static void up(int cnt, int[][] map) {
		for(int j=0; j<N; j++) {
			for(int i=0; i<N-1; i++) {
				if(map[i][j]!=0) {
					for(int k=i+1; k<N; k++) {
						if(map[k][j]==0)
							continue;
						// 값이 있을 경우
						else {
							// 합쳐질 경우
							if(map[k][j]==map[i][j]) {
								map[i][j] *= 2;
								map[k][j] = 0;
							}
							else {
								if(k!=i+1) {
									map[i+1][j] = map[k][j];
									map[k][j] = 0;
								}
							}
							break;
						}
					}
				}
				else {
					for(int k=i+1; k<N; k++) {
						if(map[k][j]!=0) {
							map[i][j] = map[k][j];
							map[k][j] = 0;
							i--;
							break;
						}
					}
				}
			}
		}
		if(cnt <= 5)
			go(cnt, map);
		else
			maxNum(map);
		
	}
	public static void down(int cnt, int[][] map) {
		for(int j=N-1; j>=0; j--) {
			for(int i=N-1; i>=1; i--) {
				if(map[i][j]!=0) {
					for(int k=i-1; k>=0; k--) {
						if(map[k][j]==0)
							continue;
						// 값이 있을 경우
						else {
							// 합쳐질 경우
							if(map[k][j]==map[i][j]) {
								map[i][j] *= 2;
								map[k][j] = 0;
							}
							else {
								if(k!=i-1) {
									map[i-1][j] = map[k][j];
									map[k][j] = 0;
								}
							}
							break;
						}
					}
				}
				else {
					for(int k=i-1; k>=0; k--) {
						if(map[k][j]!=0) {
							map[i][j] = map[k][j];
							map[k][j] = 0;
							i++;
							break;
						}
					}
				}
			}
		}
		if(cnt <= 5)
			go(cnt, map);
		else
			maxNum(map);
	}
	public static void left(int cnt, int[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N-1; j++) {
				if(map[i][j]!=0) {
					for(int k=j+1; k<N; k++) {
						if(map[i][k]==0)
							continue;
						// 값이 있을 경우
						else {
							// 합쳐질 경우
							if(map[i][k]==map[i][j]) {
								map[i][j] *= 2;
								map[i][k] = 0;
							}
							else {
								if(k!=j+1) {
									map[i][j+1] = map[i][k];
									map[i][k] = 0;
								}
							}
							break;
						}
					}
				}
				else {
					for(int k=j+1; k<N; k++) {
						if(map[i][k]!=0) {
							map[i][j] = map[i][k];
							map[i][k] = 0;
							j--;
							break;
						}
					}
				}
			}
		}
		if(cnt <= 5)
			go(cnt, map);
		else
			maxNum(map);
	}
	public static void right(int cnt, int[][] map) {
		for(int i=N-1; i>=0; i--) {
			for(int j=N-1; j>=1; j--) {
				if(map[i][j]!=0) {
					for(int k=j-1; k>=0; k--) {
						if(map[i][k]==0)
							continue;
						// 값이 있을 경우
						else {
							// 합쳐질 경우
							if(map[i][k]==map[i][j]) {
								map[i][j] *= 2;
								map[i][k] = 0;
							}
							else {
								if(k!=j-1) {
									map[i][j-1] = map[i][k];
									map[i][k] = 0;
								}
							}
							break;
						}
					}
				}
				else {
					for(int k=j-1; k>=0; k--) {
						if(map[i][k]!=0) {
							map[i][j] = map[i][k];
							map[i][k] = 0;
							j++;
							break;
						}
					}
				}
			}
		}
		if(cnt <= 5)
			go(cnt, map);
		else
			maxNum(map);
	}
	
	public static void go(int cnt, int[][] map) {
		int[][] tmpMap = new int[N][N];
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("=======");

		cnt++;
		mapclone(map, tmpMap);
		up(cnt, tmpMap);
		mapclone(map, tmpMap);
		down(cnt, tmpMap);
		mapclone(map, tmpMap);
		left(cnt, tmpMap);
		mapclone(map, tmpMap);
		right(cnt, tmpMap);
	}
	
	public static void mapclone(int[][] map, int[][] tmpMap) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
	}
	
	public static void maxNum(int[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		go(1, map);
		System.out.println(max);
	}
}
