package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 별자리 {
	static int[] dx = { 0, 0, -1, 1, -1, 1, - 1, 1 };// 상하좌우 왼위 오른위 왼아래 오른아래
	static int[] dy = { -1, 1, 0, 0, -1, -1, 1, 1 }; //dx,dy는 8방향 탐색
	static boolean[][] check; //방문체크배열
	static int result = 0; //결과 값 저장

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt")); //input텍스트의 파일 내용 읽음
		Scanner sc = new Scanner(System.in); //입력 스캐너
		int T = sc.nextInt(); //테스트케이스 입력

		for (int tc = 1; tc <= T; tc++) { // 테스트 케이스
			int[][] map = new int[12][12]; // 10x10의 1~10으로 하기 위한 2차원 배열
			check = new boolean[12][12]; //방문제크또한 같게
			result = 0; //결과값 0로 리셋
			for (int i = 1; i < 11; i++) { //2차원배열값 입력
				for (int j = 1; j < 11; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			Queue<Star> q = new LinkedList<>(); // Star클래스를 Queue로 받음
			for (int i = 1; i < 11; i++) { //방문체크하지 않은 각각의 첫 별자리의 좌표를 받음
				for (int j = 1; j < 11; j++) {
					if (check[i][j]) //방문했으면 컨티뉴
						continue;
					if (map[i][j] == 1) {
						result++; //별자리의 갯수 추가
						q.add(new Star(i, j));
						while (!q.isEmpty()) { //연결된 모든 별 체크
							Star star = q.poll(); // Star 클래스 객체의 데이트 주입
							if (check[star.y][star.x]) //방문한 것이면 컨티뉴
								continue;
							check[star.y][star.x] = true; //방문체크!
							for (int k = 0; k < 8; k++) { // 8방향 탐색
								if (star.y + dy[k] > 10 || star.y + dy[k] < 1 || star.x + dx[k] > 10
										|| star.x + dx[k] < 1) //사용가능범위 입력
									continue;
								if (check[star.y + dy[k]][star.x + dx[k]]) //방문했으면 컨티뉴
									continue;
								if (map[star.y + dy[k]][star.x + dx[k]] == 1) { //방문하지않고 값이 1이면 Queue에 추가
									q.add(new Star(star.y + dy[k], star.x + dx[k]));
								}

							}
						}
					}
				}
			}
			System.out.println("#" + tc + " " + result);
		}
	}

	static class Star {
		int y, x;

		public Star(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
