package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Alphabet {
	static int R;
	static int C;
	static char[][] map;
	static boolean[][] b;
	static boolean[] alpha = new boolean[100];
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static Stack<Character> stack = new Stack<>();
	static int size=0;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R + 2][C + 2];
		b = new boolean[R + 2][C + 2];
		String[] s = new String[R];

		for (int i = 0; i < R; i++) {
			s[i] = sc.next();
		}

		for (int i = 1; i < R + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				map[i][j] = s[i - 1].charAt(j - 1);
			}
		}

		dfs(1, 1);
		System.out.println(size);
	}

	static public void dfs(int i, int j) {
		if (alpha[(int) map[i][j]])
			return;
		if (map[i][j] < 'A')
			return;
		stack.push(map[i][j]);
		alpha[(int) map[i][j]] = true;
		for (int k = 0; k < 4; k++) {
			if ((map[i + dy[k]][j + dx[k]] >= 'A') && (!alpha[(int) map[i + dy[k]][j + dx[k]]])) {
//				stack.push(map[i + dy[k]][j + dx[k]]);
				dfs(i + dy[k], j + dx[k]);
			}
		}
		if(stack.size()>size){
			size=stack.size();
		}
		alpha[(int) map[i][j]] = false;
		stack.pop();
	}
}
