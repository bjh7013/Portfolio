package testProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author BaekJeongHun
 *
 */
public class 스타일리쉬들여쓰기 {
	private static int[][] m;
	private static int[][] dap;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder("");
		for (int tc = 1; tc <= T; tc++) {

			int p = sc.nextInt(); // 마스터의 코드 줄 수
			int q = sc.nextInt(); // 나의 코드 줄 수

			m = new int[p][4]; // . 소 중 대
			for (int i = 0; i < p; i++) {
				// 한 줄을 입력받아서
				String line = sc.next();
				// 앞부분에 나온 .개수
				int index = 0;
				while (line.charAt(index) == '.') {
					index++;
				}
				m[i][0] = index;
				if (i > 0) {
					m[i][1] = m[i - 1][1];// 소괄호
					m[i][2] = m[i - 1][2];// 중괄호
					m[i][3] = m[i - 1][3];// 대괄호
				}
				for (int j = index; j < line.length(); j++) {
					switch (line.charAt(j)) {
					case '(':
						m[i][1]++;
						break;
					case ')':
						m[i][1]--;
						break;
					case '{':
						m[i][2]++;
						break;
					case '}':
						m[i][2]--;
						break;
					case '[':
						m[i][3]++;
						break;
					case ']':
						m[i][3]--;
						break;
					}
				}
			} // 마스터의 스타일리쉬 분석

			dap = new int[q][4]; // . 소 중 대
			for (int i = 0; i < q; i++) {
				// 한 줄을 입력받아서
				String line = sc.next();
				// 앞부분에 나온 .개수
				int index = 0;

				if (i > 0) {
					dap[i][1] = dap[i - 1][1];// 소괄호
					dap[i][2] = dap[i - 1][2];// 중괄호
					dap[i][3] = dap[i - 1][3];// 대괄호
				}
				for (int j = index; j < line.length(); j++) {
					switch (line.charAt(j)) {
					case '(':
						dap[i][1]++;
						break;
					case ')':
						dap[i][1]--;
						break;
					case '{':
						dap[i][2]++;
						break;
					case '}':
						dap[i][2]--;
						break;
					case '[':
						dap[i][3]++;
						break;
					case ']':
						dap[i][3]--;
						break;
					}
				}
			} // 내코드의 스타일리쉬 분석

			// dap[i][0] : 초기값-2.의 개수를 몇개
			for (int i = 0; i < q; i++) {
				dap[i][0] = -2;
			}
			for (int R = 1; R <= 20; R++) {
				for (int C = 1; C <= 20; C++) {
					for (int S = 1; S <= 20; S++) {
						if (check(R, C, S)) {
							cal(R, C, S);
						}
					}
				}
			}
			sb.append('#').append(tc).append(" 0"); // 첫번째 줄의 들여쓰기는 0
			for (int i = 1; i < dap.length; i++) {
				sb.append(' ').append(dap[i][0]);
			}
			sb.append('\n');

		}
		System.out.println(sb);
	}

	/**
	 * 내 코드에서 들여쓰기를 각 라인에 몇개씩 해야하는지 구해서 dap 배열에 저장
	 */
	private static void cal(int R, int C, int S) {
		for (int i = 1; i < dap.length; i++) {
			int x = dap[i - 1][1] * R + dap[i - 1][2] * C + dap[i - 1][3] * S;
			if (dap[i][0] == -2) {
				dap[i][0] = x;
			} else if (dap[i][0] != x) { // 기존값과 다른 들여쓰기 값이 생긴다면
				dap[i][0] = -1;
			}
		}

	}

	private static boolean check(int R, int C, int S) {
		for (int i = 1; i < m.length; i++) {
			if (m[i][0] != m[i - 1][1] * R + m[i - 1][2] * C + m[i - 1][3] * S) {
				return false;
			}
		}
		return true;
	}
}
