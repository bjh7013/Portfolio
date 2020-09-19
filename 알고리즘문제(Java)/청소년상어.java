package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 청소년상어 {
	static int[][] map;
	static int[][] bang;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		/*
		 * 1. 물고기 1~16이고 작은 숫자부터 움직임 2. 8방향이다. 3. 상어는 0,0에서 시작하고 방향은 먹은 물고기의 방향 4. 물고기는
		 * 해당 좌표가 범위밖이거나 상어가 있으면 못움직인다. 5. 비어있으면 그냥 움직인다. 6. 다른 물고기가 있으면 자리 교환한다. 7. 해당
		 * 방향으로 못가면 반시계 45도방향 계속 회전하면서 시도 8. 못움직이면 안움직인다. 9. 물고기 이동 종료 후, 상어가 이동한다.
		 * 
		 */
		map = new int[4][4];
		bang = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = sc.nextInt();
				bang[i][j] = sc.nextInt();
			}
		}
		
		
	}
}
