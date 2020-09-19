package testProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 염라대왕의이름정렬2 {
	static int N;
	static String[] s;
	static PriorityQueue<Strings> q;
	static HashSet<String> set;

	public static void main(String[] args) throws NumberFormatException, IOException  {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		Scanner sc = new Scanner(System.in);
//		int T = sc.nextInt();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
//			StringBuffer sb = new StringBuffer();
			N = Integer.parseInt(br.readLine());
			s = new String[N + 1];
			q = new PriorityQueue<>();
			set = new HashSet<>();
			int maxlen = 0;
			String a;
			for (int i = 1; i < N + 1; i++) {
				a = br.readLine();
				if (!set.contains(a)) {
					set.add(a);
					q.offer(new Strings(a));
				}
			}

			maxlen = q.size();
//			System.out.println("#" + tc);
			sb.append("#"+tc+"\n");
			for (int i = 1; i < maxlen + 1; i++) {
				if (q.isEmpty())
					break;
				sb.append(q.poll().s+"\n");
			}
		}
		System.out.println(sb);
	}

	static class Strings implements Comparable<Strings> {
		String s;

		public Strings(String s) {
			super();
			this.s = s;
		}

		@Override
		public int compareTo(Strings o) {
			if (this.s.length() < o.s.length())
				return -1;
			if (this.s.length() > o.s.length())
				return 1;
			for (int i = 0; i < o.s.length(); i++) {
				if (this.s.charAt(i) < o.s.charAt(i))
					return -1;
				if (this.s.charAt(i) > o.s.charAt(i))
					return 1;
			}
			return 0;
		}

	}
}
