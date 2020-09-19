package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 주사위굴리기 {
	static int status;
	static int[] six;
	static int[][] map;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 세로
		int M = sc.nextInt(); // 가로

		Dice dice = new Dice(sc.nextInt(), sc.nextInt());
		int num = sc.nextInt();
		map = new int[N][M];
		six = new int[7];
		status = 1; // 현재 위는 1이다
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < num; i++) {
			int command = sc.nextInt();
			if (command == 1) { // 동
				if (dice.x == M - 1) {
					continue;
				} else {
					dice = new Dice(dice.y, dice.x + 1);
					dicestatus(dice, command);
				}
			} else if (command == 2) { // 서
				if (dice.x == 0) {
					continue;
				} else {
					dice = new Dice(dice.y, dice.x - 1);
					dicestatus(dice, command);
				}
			} else if (command == 3) { // 남
				if (dice.y == N - 1) {
					continue;
				} else {
					dice = new Dice(dice.y + 1, dice.x);
					dicestatus(dice, command);
				}
			} else { // 북
				if (dice.y == 0) {
					continue;
				} else {
					dice = new Dice(dice.y - 1, dice.x);
					dicestatus(dice, command);
				}
			}
		}

	}

	static void dicestatus(Dice dice, int command) {
		if (status == 1) {
			if (command == 1) { // 동쪽
				status = 4;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[3];
				} else {
					six[3] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 2) { // 서쪽
				status = 3;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[4];
				} else {
					six[4] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 3) { // 남쪽
				status = 2;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[5];
				} else {
					six[5] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 4) { // 북쪽
				status = 5;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[2];
				} else {
					six[2] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
		}

		if (status == 2) {
			if (command == 1) { // 동쪽
				status = 4;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[3];
				} else {
					six[3] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 2) { // 서쪽
				status = 3;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[4];
				} else {
					six[4] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 3) { // 남쪽
				status = 6;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[1];
				} else {
					six[1] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 4) { // 북쪽
				status = 1;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[6];
				} else {
					six[6] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
		}

		if (status == 5) {
			if (command == 1) { // 동쪽
				status = 4;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[3];
				} else {
					six[3] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 2) { // 서쪽
				status = 3;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[4];
				} else {
					six[4] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 3) { // 남쪽
				status = 1;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[6];
				} else {
					six[6] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 4) { // 북쪽
				status = 6;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[1];
				} else {
					six[1] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
		}
		
		if (status == 6) {
			if (command == 1) { // 동쪽
				status = 4;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[3];
				} else {
					six[3] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 2) { // 서쪽
				status = 3;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[4];
				} else {
					six[4] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 3) { // 남쪽
				status = 5;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[2];
				} else {
					six[2] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 4) { // 북쪽
				status = 2;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[5];
				} else {
					six[5] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
		}
		
		if (status == 3) {
			if (command == 1) { // 동쪽
				status = 1;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[4];
				} else {
					six[4] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 2) { // 서쪽
				status = 4;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[1];
				} else {
					six[1] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 3) { // 남쪽
				status = 2;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[5];
				} else {
					six[5] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 4) { // 북쪽
				status = 5;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[2];
				} else {
					six[2] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
		}
		
		if (status == 4) {
			if (command == 1) { // 동쪽
				status = 3;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[1];
				} else {
					six[1] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 2) { // 서쪽
				status = 1;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[3];
				} else {
					six[3] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 3) { // 남쪽
				status = 2;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[5];
				} else {
					six[5] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
			if (command == 4) { // 북쪽
				status = 5;
				System.out.println(six[status]);
				if (map[dice.y][dice.x] == 0) {
					map[dice.y][dice.x] = six[2];
				} else {
					six[2] = map[dice.y][dice.x];
					map[dice.y][dice.x] = 0;
				}
			}
		}
	}

	static class Dice {
		int y, x;

		public Dice() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Dice(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}
}
