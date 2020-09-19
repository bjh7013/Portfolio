package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class StartandLink {
	static int N;
	static int[][] map;
	static int[] tempmap;
	static int[] tempmapr;
	static boolean[] b;
	static Stack<Integer> stack = new Stack<Integer>();
	static LinkedList<Integer> list = new LinkedList<Integer>();
	static int tempi = 0;
	static int tempj = 0;
	static int temp = 1000000;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];
		tempmap = new int[N];
		b = new boolean[N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		com(0);

		System.out.println(temp);
	}

	public static void com(int num) { // num=1
		if (num == N / 2) {
			val(b);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (b[i])
				continue;
			b[i] = true;
			com(num + 1);
			b[i] = false;

		}
	}

	public static void val(boolean[] b) {
		int iindex = 0;
		int jindex = 0;
		int iresult = 0;
		int jresult = 0;
		int[] tempi = new int[N / 2];
		int[] tempj = new int[N / 2];
		for (int i = 0; i < N; i++) {
			if (b[i]) {
				tempi[iindex++] = i;
			} else {
				tempj[jindex++] = i;
			}
		}
		for (int i = 0; i < N / 2 - 1; i++) {
			for (int j = i+1; j < N / 2; j++) {
				iresult+= map[tempi[i]][tempi[j]] + map[tempi[j]][tempi[i]];
			}
		}
		for (int i = 0; i < N / 2 - 1; i++) {
			for (int j = i+1; j < N / 2; j++) {
				jresult+= map[tempj[i]][tempj[j]] + map[tempj[j]][tempj[i]];
			}
		}
//		System.out.println(temp);
		if (Math.abs(iresult - jresult) < temp) {
			temp = Math.abs(iresult - jresult);
		}

	}

}
