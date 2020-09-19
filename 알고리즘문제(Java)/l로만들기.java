package testProject;

/*
 * 백준 1463번 1로만들기
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class l로만들기 {
	static boolean check;
	static int count;
	static int[] save;
	static int min;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		check = false;
		count = 987654321;
		save = new int[N + 1];
		min = 987654321;
		for (int i = 0; i < N + 1; i++) {
			save[i] = 987654321;
		}
		dfs(N, 0);
		System.out.println(save[N]);
	}

	static int dfs(int value, int cnt) {
		int temp = 0;
		if (value == 1) {
			return save[value] = 0;
		}
		if (save[value] != 987654321 && save[value]<cnt) {
			return save[value];
		}
		if (value % 3 == 0) {
			temp = dfs(value / 3, cnt + 1) + 1;
			if (save[value] > temp) {
				save[value] = temp;
			}
		}
		if (value % 2 == 0) {
			temp = dfs(value / 2, cnt + 1) + 1;
			if (save[value] > temp) {
				save[value] = temp;
			}
		}
		temp = dfs(value - 1, cnt + 1) + 1;
		if (save[value] > temp) {
			save[value] = temp;
		}
		return save[value];

	}
}
