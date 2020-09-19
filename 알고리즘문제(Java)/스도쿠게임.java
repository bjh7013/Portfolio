package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 스도쿠게임 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테스트케이스 수

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 스도쿠 입력 횟수
			int[][] map = new int[10][10]; // 9x9배열에 스도쿠 값 입력!!
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			Queue<Integer> q1 = new LinkedList<>(); // 행 저장큐
			Queue<Integer> q2 = new LinkedList<>(); // 열 저장큐
			Queue<Integer> q3 = new LinkedList<>(); // 숫자 저장큐
			for (int i = 0; i < N; i++) { // 행,열,숫자 저장
				int row = sc.nextInt();
				int col = sc.nextInt();
				int val = sc.nextInt();
				q1.add(row);
				q2.add(col);
				q3.add(val);
			}
			int cnt = 0; // 몇 번까지 가능한 지 카운트
			All: while (!q1.isEmpty()) { // 게임 수행 가능할 때 까지 하기!!
				int r = q1.poll();
				int c = q2.poll();
				int v = q3.poll();

				for (int i = r; i < r + 1; i++) { // 해당 게임의 숫자의 행에 같은 숫자 있는 지 확인 행=r 열=0~8
					for (int j = 0; j < 9; j++) {
						if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
							break All;
					}
				}

				for (int i = 0; i < 9; i++) {
					for (int j = c; j < c + 1; j++) { // 해당 게임의 숫자의 열에 같은 숫자 있는 지 확인 행=0~8 열=c
						if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
							break All;
					}
				}

				if (r / 3 == 0) {
					if (c / 3 == 0) {
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) { // 해당 게임의 숫자의 열에 같은 숫자 있는 지 확인 행=0~8 열=c
								if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
									break All;
							}
						}
					} else if (c / 3 == 1) {
						for (int i = 0; i < 3; i++) {
							for (int j = 3; j < 6; j++) { // 해당 게임의 숫자의 열에 같은 숫자 있는 지 확인 행=0~8 열=c
								if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
									break All;
							}
						}
					}
					if (c / 3 == 2) {
						for (int i = 0; i < 3; i++) {
							for (int j = 6; j < 9; j++) { // 해당 게임의 숫자의 열에 같은 숫자 있는 지 확인 행=0~8 열=c
								if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
									break All;
							}
						}
					}
				} else if (r / 3 == 1) {
					if (c / 3 == 0) {
						for (int i = 3; i < 6; i++) {
							for (int j = 0; j < 3; j++) { // 해당 게임의 숫자의 열에 같은 숫자 있는 지 확인 행=0~8 열=c
								if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
									break All;
							}
						}
					} else if (c / 3 == 1) {
						for (int i = 3; i < 6; i++) {
							for (int j = 3; j < 6; j++) { // 해당 게임의 숫자의 열에 같은 숫자 있는 지 확인 행=0~8 열=c
								if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
									break All;
							}
						}
					}
					if (c / 3 == 2) {
						for (int i = 3; i < 6; i++) {
							for (int j = 6; j < 9; j++) { // 해당 게임의 숫자의 열에 같은 숫자 있는 지 확인 행=0~8 열=c
								if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
									break All;
							}
						}
					}
				} else if (r / 3 == 2) {
					if (c / 3 == 0) {
						for (int i = 6; i < 9; i++) {
							for (int j = 0; j < 3; j++) { // 해당 게임의 숫자의 열에 같은 숫자 있는 지 확인 행=0~8 열=c
								if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
									break All;
							}
						}
					} else if (c / 3 == 1) {
						for (int i = 6; i < 9; i++) {
							for (int j = 3; j < 6; j++) { // 해당 게임의 숫자의 열에 같은 숫자 있는 지 확인 행=0~8 열=c
								if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
									break All;
							}
						}
					}
					if (c / 3 == 2) {
						for (int i = 6; i < 9; i++) {
							for (int j = 6; j < 9; j++) { // 해당 게임의 숫자의 열에 같은 숫자 있는 지 확인 행=0~8 열=c
								if (map[i][j] == v) // 만약에 겹치는게 있으면 while문을 끝낸다!!
									break All;
							}
						}
					}
				}
				cnt++; // 겹치는게 없어서 성공적으로 게임 수행 완료!!
			}

			System.out.println("#" + tc + " " + cnt); // 결과
		}

	}
}
