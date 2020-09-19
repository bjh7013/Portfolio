package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class JongEattach {
	public class Jonge {
		int num;
//		int 
	}
	static int[][] map;
	static boolean[][] b;
	static int[] jonge;
	static int[] dx = { 1, 0, 1 }; // 오른 아래 대각오른아래
	static int[] dy = { 0, 1, 1 };
	static Queue<Integer> q = new LinkedList<>();
	static int cnt=0;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		map = new int[10 + 2][10 + 2];
		b = new boolean[10 + 2][10 + 2];
		jonge = new int[5 + 2];
		for (int i = 1; i <= 5; i++) {
			jonge[i] = 5;
		}
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		dfs();

	}

	public static void dfs() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				cnt=0;
				if (map[i][j] == 1) {
					b[i][j] = true;
					for (int k = 0; k < 3; k++) {
						if (map[i + dx[k]][j + dy[k]] != 1) {
							break;
						}
						if(k==2) {
							for(int l =0; l<3; l++) {
								b[i + dx[k]][j + dy[k]]=true;
							}
							cnt++;
						}
					}
				}
			}
		}
	}
}
