package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 톱니바퀴 {
	static LinkedList<Integer>[] list;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		list = new LinkedList[5];
		for (int i = 0; i < 5; i++) {
			list[i] = new LinkedList<>();
		}
		for (int i = 1; i < 5; i++) {
			String s = sc.next();
			for (int j = 0; j < 8; j++) {
				list[i].add((int) (s.charAt(j) - 48));
			}
		}
		// 0이 N극 1이 S극

		int K = sc.nextInt(); // 회전 횟수
		Queue<Wheel> q = new LinkedList<>();
		for (int i = 0; i < K; i++) {
			q.add(new Wheel(sc.nextInt(), sc.nextInt()));
		}
//		for (int k = 1; k < 5; k++) {
//			for (int j = 0; j < 8; j++) {
//				System.out.print(list[k].get(j) + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();

		while (!q.isEmpty()) {
			Wheel w = q.poll();
			boolean[] check = new boolean[5];
			check[w.num]=true;
			for (int i = w.num; i < 4; i++) {
				if (list[i].get(2) != list[i + 1].get(6)) {
					check[i] = true;
					check[i + 1] = true;
				} else {
					break;
				}
			}

			for (int i = w.num; i > 1; i--) {
				if (list[i].get(6) != list[i - 1].get(2)) {
					check[i] = true;
					check[i - 1] = true;
				}else {
					break;
				}
			}
			if (w.bang == 1) {
				list[w.num].addFirst(list[w.num].getLast());
				list[w.num].removeLast();
			} else {
				list[w.num].addLast(list[w.num].getFirst());
				list[w.num].removeFirst();
			}
			for (int i = w.num + 1; i < 5; i++) {
				if (check[i]) {
					if (w.num % 2 == i % 2) {
						if (w.bang == 1) {
							list[i].addFirst(list[i].getLast());
							list[i].removeLast();
						} else {
							list[i].addLast(list[i].getFirst());
							list[i].removeFirst();
						}
					}else {
						if (w.bang == -1) {
							list[i].addFirst(list[i].getLast());
							list[i].removeLast();
						} else {
							list[i].addLast(list[i].getFirst());
							list[i].removeFirst();
						}
					}
				} else {
					break;
				}
			}
			for (int i = w.num - 1; i > 0; i--) {
				if (check[i]) {
					if (w.num % 2 == i % 2) {
						if (w.bang == 1) {
							list[i].addFirst(list[i].getLast());
							list[i].removeLast();
						} else {
							list[i].addLast(list[i].getFirst());
							list[i].removeFirst();
						}
					}else {
						if (w.bang == -1) {
							list[i].addFirst(list[i].getLast());
							list[i].removeLast();
						} else {
							list[i].addLast(list[i].getFirst());
							list[i].removeFirst();
						}
					}
				} else {
					break;
				}
			}
		}

		int cnt = 0;
		for (int i = 1; i < 5; i++) {
			if (list[i].get(0) == 1) {
				cnt += Math.pow(2, (i - 1));
			}
		}
		System.out.println(cnt);
	}

//	static void dfs(int left, int right) {
//		if (left == 0 || right == 5)
//			return;
//		if()
//	}

	static class Wheel {
		int num, bang;

		public Wheel(int num, int bang) {
			super();
			this.num = num;
			this.bang = bang;
		}

		public Wheel() {
			super();
			// TODO Auto-generated constructor stub
		}

	}
}
