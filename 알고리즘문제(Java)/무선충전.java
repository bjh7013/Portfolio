package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 무선충전 {
	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };
	static int result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = 10;
			result = 0;
			int[][] map = new int[N + 1][N + 1];
			int M = sc.nextInt(); // 이동시간
			int A = sc.nextInt(); // BC 갯수
			int[] userA = new int[M + 1];
			int[] userB = new int[M + 1];
			AP[] battery = new AP[A + 1];
			for (int i = 0; i < M; i++) {
				userA[i] = sc.nextInt();
			}
			for (int i = 0; i < M; i++) {
				userB[i] = sc.nextInt();
			}
			Queue<User> qA = new LinkedList<>();
			Queue<User> qB = new LinkedList<>();
			qA.add(new User(1, 1, 0));
			qB.add(new User(10, 10, 0));
			for (int i = 1; i < A + 1; i++) {
				battery[i] = new AP();
				battery[i].x = sc.nextInt();
				battery[i].y = sc.nextInt();
				battery[i].arrange = sc.nextInt();
				battery[i].power = sc.nextInt();
			}

			while (!qA.isEmpty()) {
				User usera = qA.poll();
				User userb = qB.poll();
				if (usera.cnt == M+1)
					break;
				PriorityQueue<Charge> a = new PriorityQueue<>();
				PriorityQueue<Charge> b = new PriorityQueue<>();
				for (int i = 1; i < A + 1; i++) {
					int distA = Math.abs(battery[i].x - usera.x) + Math.abs(battery[i].y - usera.y);
					int distB = Math.abs(battery[i].x - userb.x) + Math.abs(battery[i].y - userb.y);
					if (distA <= battery[i].arrange) {
						a.add(new Charge(i, battery[i].power));
					}
					if (distB <= battery[i].arrange) {
						b.add(new Charge(i, battery[i].power));
					}
				}
				if (a.size() == 0 && b.size() == 0) {
					qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]], usera.cnt + 1));
					qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]], usera.cnt + 1));
					continue;
				}
				if (a.size() == 0 && b.size() != 0) {
					result += b.poll().val;
					qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]], usera.cnt + 1));
					qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]], usera.cnt + 1));
					continue;
				}
				if (a.size() != 0 && b.size() == 0) {
					result += a.poll().val;
					qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]], usera.cnt + 1));
					qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]], usera.cnt + 1));
					continue;
				}
				if (a.size() != 0 && b.size() != 0) {
					if (a.size() == 1 && b.size() == 1) {
						Charge acharge = a.poll();
						Charge bcharge = b.poll();
						if (acharge.index == bcharge.index) {
							result += acharge.val;
							qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]],
									usera.cnt + 1));
							qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]],
									usera.cnt + 1));
							continue;
						}
						if (acharge.index != bcharge.index) {
							result += acharge.val;
							result += bcharge.val;
							qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]],
									usera.cnt + 1));
							qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]],
									usera.cnt + 1));
							continue;
						}
					}
					if (a.size() > 1 && b.size() == 1) {
						Charge acharge = a.poll();
						Charge bcharge = b.poll();
						if (acharge.index == bcharge.index) {
							result += bcharge.val;
							acharge = a.poll();
							result += acharge.val;
							qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]],
									usera.cnt + 1));
							qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]],
									usera.cnt + 1));
							continue;
						}
						if (acharge.index != bcharge.index) {
							result += acharge.val;
							result += bcharge.val;
							qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]],
									usera.cnt + 1));
							qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]],
									usera.cnt + 1));
							continue;
						}
					}
					if (a.size() == 1 && b.size() > 1) {
						Charge acharge = a.poll();
						Charge bcharge = b.poll();
						if (acharge.index == bcharge.index) {
							result += acharge.val;
							result += b.poll().val;
							qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]],
									usera.cnt + 1));
							qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]],
									usera.cnt + 1));
							continue;
						}
						if (acharge.index != bcharge.index) {
							result += acharge.val;
							result += bcharge.val;
							qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]],
									usera.cnt + 1));
							qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]],
									usera.cnt + 1));
							continue;
						}
					}
					if (a.size() > 1 && b.size() > 1) {
						Charge acharge = a.poll();
						Charge bcharge = b.poll();
						if (acharge.index == bcharge.index) {
							result += acharge.val;
							acharge = a.poll();
							bcharge = b.poll();
							result += Math.max(acharge.val, bcharge.val);
							qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]],
									usera.cnt + 1));
							qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]],
									usera.cnt + 1));
							continue;
						}
						if (acharge.index != bcharge.index) {
							result += acharge.val;
							result += bcharge.val;
							qA.add(new User(usera.y + dy[userA[usera.cnt]], usera.x + dx[userA[usera.cnt]],
									usera.cnt + 1));
							qB.add(new User(userb.y + dy[userB[usera.cnt]], userb.x + dx[userB[usera.cnt]],
									usera.cnt + 1));
							continue;
						}
					}
				}
			}
			System.out.println("#"+tc+" "+result);

		}

	}

	static class Charge implements Comparable<Charge> {
		int index, val;

		public Charge(int index, int val) {
			super();
			this.index = index;
			this.val = val;
		}

		@Override
		public int compareTo(Charge o) {

			return Integer.compare(o.val, this.val);
		}
	}

	static class User {
		int y, x, cnt;

		public User(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}

	static class AP {
		int x, y, arrange, power;

		public AP() {
			super();
			// TODO Auto-generated constructor stub
		}

		public AP(int x, int y, int arrange, int power) {
			super();
			this.x = x;
			this.y = y;
			this.arrange = arrange;
			this.power = power;
		}

	}
}
