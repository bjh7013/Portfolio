package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 보물상자비밀번호 {
	static int N;
	static int K;
	static long[] val;
	static String s;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
//		각 회전에 따라 각 변의 길이 값을 넣는다. 4로 나눔
//		회전을 시키고 반복한다
//		중복을 시키지 않는다.
//		sort한다.
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 글자수
			K = sc.nextInt(); // 회전수
			s = sc.next();
			val = new long[5];
			List<Character> list = new LinkedList<>();
			PriorityQueue<Long> pq = new PriorityQueue<>();
			for (int i = 1; i < N + 1; i++) {
				list.add(s.charAt(i - 1));
			}
			int cnt = 0;
			while (cnt != N + 1) {
				val = new long[5];
				for (int i = 0; i < N; i++) {
					if (list.get(i) < 65) {
						val[i / (N / 4) + 1] += (list.get(i) - 48) * Math.pow(16, (((N / 4) - (i % (N / 4))) - 1));
					} else {
						val[i / (N / 4) + 1] += (list.get(i) - 55) * Math.pow(16, (((N / 4) - (i % (N / 4))) - 1));
					}
				}
				for (int i = 1; i < 5; i++) {
					if (pq.contains(val[i]))
						continue;
					pq.add(val[i]);
				}
				list.add(0, list.get(N - 1));
				list.remove(N);
				cnt++;
			}
			int size = pq.size();
			for (int i = 0; i < size; i++) {
				long tempnum = pq.poll();
				if (i == (size - K))
					System.out.println("#" + tc + " " + tempnum);
			}
		}
	}
}
