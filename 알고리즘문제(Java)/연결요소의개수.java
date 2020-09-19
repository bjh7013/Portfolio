package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class 연결요소의개수 {
	static int[] root;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 정점
		int M = sc.nextInt(); // 간선
		if (M == 0) {
			System.out.println(N);
		} else {
			int cnt = 1;
			root = new int[N + 1];
			List<Connect> list = new ArrayList<>();
			HashSet<Integer> set = new HashSet<>();
			for (int i = 0; i < N + 1; i++) {
				root[i] = i;
			}
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				list.add(new Connect(a, b));
			}
//			Collections.sort(list, new Comparator<Connect>() {
//
//				@Override
//				public int compare(Connect o1, Connect o2) {
//					if (o1.one < o2.one)
//						return -1;
//					else
//						return 1;
//				}
//			});
			for (int i = 0; i < M; i++) {
				Connect c = list.get(i);
				if (root[c.one] != root[c.two]) {
					union(c.one, c.two);
				}
			}
//			Arrays.sort(root);
			for (int i = 1; i < root.length; i++) {
				set.add(find(i));
			}
			System.out.println(set.size());
//			System.out.println(root[N]);
//			System.out.println(cnt);
		}
	}

	static void union(int x, int y) {
		int xx = find(x);
		int yy = find(y);
		if (xx != yy) {
			if (xx > yy) {
				root[xx] = yy;
			} else {
				root[yy] = xx;
			}
		}
	}

	static int find(int x) {
		if (root[x] == x) {
			return x;
		} else {
			return root[x] = find(root[x]);
		}
	}

	static class Connect {
		int one, two;

		public Connect() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Connect(int one, int two) {
			super();
			this.one = one;
			this.two = two;
		}

	}
}
