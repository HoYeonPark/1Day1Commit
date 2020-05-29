package Baekjoon5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 회사에있는사람_7785 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		TreeSet<String> nameList = new TreeSet<String>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String status = st.nextToken();
			
			if(status.equals("enter"))
				nameList.add(name);
			else if(status.equals("leave"))
				nameList.remove(name);
		}
		
		Iterator<String> iter = nameList.descendingIterator();
		while(iter.hasNext())
			System.out.println(iter.next());
	}
}
