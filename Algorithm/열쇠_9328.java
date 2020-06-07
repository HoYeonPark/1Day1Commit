package BaekJoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 열쇠_9328 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		tc : for(int tc=1; tc<=T; tc++) {
			key = new HashSet<Character>();
			queue = new LinkedList<>();
			door = new HashMap<Character, ArrayList<pos>>();
			
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int docCnt = 0;
			map = new char[h][w];
			boolean[][] visited = new boolean[h][w];
			String input;
			for(int i=0; i<h; i++) {
				input = br.readLine();
				for(int j=0; j<w; j++) {
					map[i][j] = input.charAt(j);
					if(map[i][j]=='$')
						docCnt++;
				}
			}
			
			input = br.readLine();
			for(int i=0; i<input.length(); i++) {
				if(input.charAt(i)=='0')
					break;
				key.add(input.charAt(i));
			}
			
			// 가장자리에서 시작. 그러므로 시작점부터 찾는다..!
			for(int i=0; i<h; i++) {
				if(map[i][0]!='*') 
					setting(i, 0);
				if(map[i][w-1]!='*')
					setting(i, w-1);
			}
			for(int j=1; j<w-1; j++) {
				if(map[0][j]!='*') 
					setting(0, j);
				if(map[h-1][j]!='*') 
					setting(h-1, j);
			}
			
			int[] dy = {-1, 1, 0, 0};
			int[] dx = {0, 0, -1, 1};
			pos p;
			int pickCnt = 0;
			ArrayList<pos> list;
			int movecnt = 0;
			while(!queue.isEmpty()) {
				movecnt++;
				p = queue.poll();
				
				if(map[p.y][p.x]=='$') {
					map[p.y][p.x] = '.';
					pickCnt++;
					if(pickCnt==docCnt) {
						System.out.println(pickCnt);
						continue tc;
					}
				}
				
				for(int k=0; k<4; k++) {
					int my = p.y + dy[k];
					int mx = p.x + dx[k];
					if(my < 0 || my >= h || mx < 0 || mx >= w || visited[my][mx])
						continue;
					
					visited[my][mx] = true;
					
					if(map[my][mx]=='*')
						continue;
					
					// 열쇠네? 줍줍하고 마저 ㄱㄱ
					else if(map[my][mx] >= 97 && map[my][mx] <= 122) {
						key.add(map[my][mx]);
						queue.add(new pos(my, mx));
					}
					
					// 문이네?
					else if(map[my][mx] >= 65 && map[my][mx] <= 90) {
						if(!door.containsKey(map[my][mx])) 
							door.put(map[my][mx], new ArrayList<pos>());
						
						// 열쇠가 있으면 지금 들어가면 되지~
						if(key.contains(map[my][mx])) 
							queue.add(new pos(my, mx));
						// 없으면 나중에..
						else {
							list = door.get(map[my][mx]);
							if(!list.contains(map[my][mx]))
								list.add(new pos(my, mx));
						}
					}
					// 문서겠지..
					else
						queue.add(new pos(my, mx));
				}
			}
			boolean flag = true;
			int movecnt2 = 0;
			System.out.println("door " + door.size());
			System.out.println("key " + key.size());
			while(flag) {
				flag = false;
				for(int i=0; i<26; i++) {
					char c = (char)('A'+i);
					if(door.containsKey(c)) {
						queue.addAll(door.get(c));
						door.get(c).clear();
					}
				}
//				System.out.println("queue " + queue.size());
				// 아까 열쇠없어서 못들어갔던 문도 돌아봐야지!
				while(!queue.isEmpty()) {
					p = queue.poll();
					
					// 이래도 열쇠가 없어.....
					if(!key.contains((char)(map[p.y][p.x]+32))) {
						System.out.println("ㅎㅇ" + (char)(map[p.y][p.x]+32));
						continue;
					}
					
					if(map[p.y][p.x]=='$') {
						map[p.y][p.x] = '.';
						pickCnt++;
						if(pickCnt==docCnt) {
							System.out.println(pickCnt);
							continue tc;
						}
					}
					movecnt2++;
					
					for(int k=0; k<4; k++) {
						flag = true;
						int my = p.y + dy[k];
						int mx = p.x + dx[k];
						if(my < 0 || my >= h || mx < 0 || mx >= w || visited[my][mx])
							continue;
						
						visited[my][mx] = true;
						
						if(map[my][mx]=='*')
							continue;
						
						else if(map[my][mx] >= 97 && map[my][mx] <= 122) {
							key.add(map[my][mx]);
							queue.add(new pos(my, mx));
						}
						
						else if(map[my][mx] >= 65 && map[my][mx] <= 90) {
							if(!door.containsKey(map[my][mx])) 
								door.put(map[my][mx], new ArrayList<pos>());
							
							if(key.contains(map[my][mx])) 
								queue.add(new pos(my, mx));
							
							else {
								list = door.get(map[my][mx]);
								if(!list.contains(map[my][mx]))
									list.add(new pos(my, mx));
							}
						}
						else
							queue.add(new pos(my, mx));
					}
				}
			}
//			System.out.println("door " + door.size());
//			System.out.println("key " + key.size());
			System.out.println(movecnt + "," + movecnt2);
//			for(int i=0; i<h; i++) {
//				for(int j=0; j<w; j++) {
//					System.out.print(map[i][j] + " ");
//				}System.out.println();
//			}
			System.out.println(pickCnt);
		}
	}
	static char[][] map;
	static Set<Character> key = new HashSet<Character>();
	static Queue<pos> queue = new LinkedList<>();
	static Map<Character, ArrayList<pos>> door = new HashMap<Character, ArrayList<pos>>();
	static void setting(int y, int x) {
		// 입구다!
		if(map[y][x]=='.') 
			queue.add(new pos(y, x));
		else if(map[y][x]=='$')
			queue.add(new pos(y, x));
		// 열쇠네?
		else if(map[y][x] >= 97 && map[y][x] <= 122) {
			key.add(map[y][x]);
			queue.add(new pos(y, x));
		}
		// 문이네?
		else if(map[y][x] >= 65 && map[y][x] <= 90) {
			if(!door.containsKey(map[y][x])) 
				door.put(map[y][x], new ArrayList<pos>());
			door.get(map[y][x]).add(new pos(y, x));
		}
	}
	static class pos{
		int y;
		int x;
		public pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
