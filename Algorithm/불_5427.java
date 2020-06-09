package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불_5427 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        tc : for(int tc=1; tc <= T; tc++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int C = Integer.parseInt(st.nextToken());
	        int R = Integer.parseInt(st.nextToken());
	        char[][] map = new char[R+2][C+2];
	        boolean[][] visited = new boolean[R+2][C+2];
	        int[][] tmemo = new int[R+2][C+2];
	        String input="";
	        Queue<pos> mypos = new LinkedList<>();
	        Queue<pos> firepos = new LinkedList<>();
	         
	        for(int i=1; i<=R; i++) {
	            st = new StringTokenizer(br.readLine());
	            input = st.nextToken();
	            for(int j=1; j<=C; j++) {
	                map[i][j] = input.charAt(j-1);
	                if(map[i][j]=='*')
	                    firepos.add(new pos(i, j, 0));
	                else if(map[i][j]=='@')
	                    mypos.add(new pos(i, j, 0));
	            }
	        }
	        for(int i=0; i<=R+1; i++)
	        	Arrays.fill(tmemo[i], Integer.MAX_VALUE);
	         
	        pos pos;
	        int[] dy = {-1, 1, 0, 0};
	        int[] dx = {0, 0, -1, 1};
	         
            // 불 먼저 붙여놓고
	        while(!firepos.isEmpty()) {
	        	pos = firepos.poll();

	        	int py = pos.y;
	        	int px = pos.x;
	        	tmemo[py][px] = pos.time;
	        	for(int k=0; k<4; k++) {
	        		int my = py + dy[k];
	        		int mx = px + dx[k];
	        		
	        		if(my <= 0 || my >= R+1 || mx <= 0 || mx >= C+1 || visited[my][mx])
	        			continue;
	        		
	        		visited[my][mx] = true;
	        		if(map[my][mx]=='.' || map[my][mx]=='@') 
	        			firepos.add(new pos(my, mx, pos.time+1));
	        	}
	        }
	        
	        for(int i=0; i<=R+1; i++)
	        	Arrays.fill(visited[i], false);
	        
//	        for(int i=0; i<R+2; i++) {
//	        	for(int j=0; j<C+2; j++) {
//	        		System.out.print(tmemo[i][j] + " ");
//	        	}	        	
//	        	System.out.println();
//	        }
            // 내가 이동해야지
            while(!mypos.isEmpty()) {
	            pos = mypos.poll();
	            int py = pos.y;
	            int px = pos.x;
	             
	             
	            for(int k=0; k<4; k++) {
	                int my = py + dy[k];
	                int mx = px + dx[k];
	                
	                if(visited[my][mx])
	                	continue;
	                 
	                if(my == 0 || my == R+1 || mx == 0 || mx == C+1) {
	                	sb.append(pos.time + 1).append('\n');
	                    continue tc;
	                }
	                visited[my][mx] = true;
	                 
	                if(map[my][mx]=='#')
	                    continue;
	                 
	                else if(tmemo[my][mx] <= pos.time+1)
	                    continue;
	                
	                else 
	                    mypos.add(new pos(my, mx, pos.time+1));
	            }
	        }
        	sb.append("IMPOSSIBLE").append('\n');
        }
        System.out.println(sb.toString());
    }
    static class pos{
        int y;
        int x;
        int time;
 
        public pos(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }
}