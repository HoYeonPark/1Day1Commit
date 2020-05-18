package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 최소힙_1927 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}
		});
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int input = Integer.parseInt(br.readLine());
			if(input==0) {
				if(pq.isEmpty())
//					System.out.println(0);
					sb.append(0).append('\n');
				else
					sb.append(pq.poll()).append('\n');
//					System.out.println(pq.poll());
			}
			else
				pq.add(input);
		}
		System.out.println(sb.toString());
	}
}
