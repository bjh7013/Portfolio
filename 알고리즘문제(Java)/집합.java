package testProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 집합 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int value = 0;
		int sol = 0;
		StringTokenizer st;
//		TreeSet<Integer> tree = new TreeSet();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if (!(s.equals("all") || s.equals("empty"))) {
				value = Integer.parseInt(st.nextToken());
			}
			if (s.equals("add")) {
//				tree.add(value);
				sol |= (1 << (value - 1));
			} else if (s.equals("remove")) {
//				tree.remove(value);
				sol &= (~(1 << (value - 1)));
			} else if (s.equals("check")) {
//				if (tree.contains(value)) {
//					sb.append(1);
//					sb.append(System.getProperty("line.separator"));
//				} else {
//					sb.append(0);
//					sb.append(System.getProperty("line.separator"));
//				}
				if ((sol & (1 << (value - 1))) > 0) {
					sb.append(1).append('\n');
				} else {
					sb.append(0).append('\n');
				}
			} else if (s.equals("toggle")) {
//				if (tree.contains(value)) {
//					tree.remove(value);
//				} else {
//					tree.add(value);
//				}
				
//				if ((sol & (1 << (value - 1))) > 0) {
//					sol &= (~(1 << (value - 1)));
//				} else {	
//					sol |= (1 << (value - 1));
//				}
				sol ^= (1<<value-1);
			} else if (s.equals("all")) {
//				for (int j = 0; j < 20; j++) {
//					if (!tree.contains(j + 1)) {
//						tree.add(j + 1);
//					}
//				}
				sol = (1 << 20) - 1;
			} else if (s.equals("empty")) {
//				tree.clear();
				sol = 0;
			}
		}
		System.out.print(sb);
	}
}
