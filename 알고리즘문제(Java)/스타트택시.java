package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 스타트택시 {
	static int[] dx = { 0, -1, 1, 0 };
	static int[] dy = { -1, 0, 0, 1 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // NxN의 영역
		int M = sc.nextInt(); // 손님 수
		int Oil = sc.nextInt();
		int result = -1;
		int total = 0;
		int[][] map = new int[N + 2][N + 2];
		boolean[] check = new boolean[M + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int taxiR = sc.nextInt();
		int taxiC = sc.nextInt();

		Queue<Taxi> q = new LinkedList<>();
		q.add(new Taxi(taxiR, taxiC, 0, Oil));
		List<Person> list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			list.add(new Person(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}
		// 여기서부터 시작
		while (true) {
			boolean search = false;
			boolean[][] visit = new boolean[N + 2][N + 2];
			Queue<Loc> loc = new LinkedList<>();
			int mindist = Integer.MAX_VALUE;
			while (!q.isEmpty()) {
				Taxi taxi = q.poll();
				for (int i = 0; i < M; i++) { // 해당 거리에 check로 운송한 곳 빼고 찾기
					if (check[i])
						continue;
					if (taxi.y == list.get(i).sy && taxi.x == list.get(i).sx && taxi.dist <= mindist) {
						if (!search)
							mindist = taxi.dist;
						loc.add(new Loc(taxi.y, taxi.x, i, taxi.oil));
						search = true;
					}
				}
				if (search) // 발견했으면 더 이상 add 안 함
					continue;
				for (int k = 0; k < 4; k++) { // 4방향 탐색
					if (taxi.y + dy[k] < 1 || taxi.y + dy[k] > N || taxi.x + dx[k] < 1 || taxi.x + dx[k] > N)
						continue;
					if (map[taxi.y + dy[k]][taxi.x + dx[k]] == 1)
						continue;
					if (visit[taxi.y + dy[k]][taxi.x + dx[k]])
						continue;
					if (taxi.oil < 1)
						continue;
					visit[taxi.y + dy[k]][taxi.x + dx[k]] = true;
					q.add(new Taxi(taxi.y + dy[k], taxi.x + dx[k], taxi.dist + 1, taxi.oil - 1)); // 조건 충족되면 일단 넣음
				}
			}
			int miny = Integer.MAX_VALUE;
			int minx = Integer.MAX_VALUE;
			int minoil = Integer.MAX_VALUE;
			int number = -1;

			if (loc.isEmpty()) {
//				System.out.println(total);
				break;
			}
			while (!loc.isEmpty()) {
				Loc temp = loc.poll();

				if (temp.y <= miny) {
					if (temp.y < miny) {
						miny = temp.y;
						minx = temp.x;
						number = temp.num;
						minoil = temp.oil;
						continue;
					}
					if (temp.y == miny) {
						if (temp.x < minx) {
							miny = temp.y;
							minx = temp.x;
							number = temp.num;
							minoil = temp.oil;
						}
					}
				}
			}
//			System.out.println(number + "번째");
//			System.out.println(miny + " " + minx);
			check[number] = true;
			Queue<Taxi> q2 = new LinkedList<>(); // 도착까지 간다
			q2.add(new Taxi(miny, minx, 0, minoil));
			boolean[][] visit2 = new boolean[N + 2][N + 2];

			while (!q2.isEmpty()) {
				Taxi taxi = q2.poll();
				if (taxi.y == list.get(number).ey && taxi.x == list.get(number).ex) {
					q.add(new Taxi(taxi.y, taxi.x, 0, taxi.oil + taxi.dist * 2));
					break;
				}
				for (int k = 0; k < 4; k++) { // 4방향 탐색
					if (taxi.y + dy[k] < 1 || taxi.y + dy[k] > N || taxi.x + dx[k] < 1 || taxi.x + dx[k] > N)
						continue;
					if (map[taxi.y + dy[k]][taxi.x + dx[k]] == 1)
						continue;
					if (visit2[taxi.y + dy[k]][taxi.x + dx[k]])
						continue;
					if (taxi.oil < 1)
						continue;
					visit2[taxi.y + dy[k]][taxi.x + dx[k]] = true;
					q2.add(new Taxi(taxi.y + dy[k], taxi.x + dx[k], taxi.dist + 1, taxi.oil - 1)); // 조건 충족되면 일단 넣음
				}
			}
			
//			System.out.println(q.peek().y + " " + q.peek().x);
			total++;
			if (total == M) {
				result = q.poll().oil;
				break;
			}
		}
		System.out.println(result);
		// 여기가 끝
	}

	static class Taxi {
		int y, x, dist, oil;

		public Taxi() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Taxi(int y, int x, int dist, int oil) {
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
			this.oil = oil;
		}

	}

	static class Person {
		int sy, sx, ey, ex;

		public Person() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Person(int sy, int sx, int ey, int ex) {
			super();
			this.sy = sy;
			this.sx = sx;
			this.ey = ey;
			this.ex = ex;
		}

	}

	static class Loc {
		int y, x, num, oil;

		public Loc(int y, int x, int num, int oil) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
			this.oil = oil;
		}

		public Loc() {
			super();
			// TODO Auto-generated constructor stub
		}
	}
}
