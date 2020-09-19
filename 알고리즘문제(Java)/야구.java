package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 야구 {
	static int N;
	static int ttcnt;
	static boolean[] check;
	static int[][] map;
	static int[] order;
	static int[] base;
	static int[] baseball;
	static int out;
	static int score;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][10];
		check = new boolean[10];
		order = new int[10];
		base = new int[4];
		baseball = new int[10];
		out = 0;
		score = 0;
		ttcnt=0;
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < 9 + 1; i++) {
				map[k][i] = sc.nextInt();
			}
		}
		order[1] = 1;
		comb(2);
		System.out.println(score);
	}

	static void comb(int cnt) {
		if (cnt == 10) {
			int tempcnt = 1;
			for (int i = 2; i < 10; i++) {
//				System.out.print(order[i] + " ");
				baseball[tempcnt++] = order[i];
//				System.out.print(baseball[tempcnt-1]);
				if (i == 4) {
					baseball[tempcnt++] = order[1];
//					System.out.print(baseball[tempcnt-1]);
//					System.out.print(order[1] + " ");
				}
			}
//			System.out.println();
			int a = game();
//			if (a == 107) {
//				for(int i=1;i<10;i++) {
//					System.out.print(baseball[i]+" ");
//				}
//				System.out.println();
//			}
			score = Math.max(score, a);
			return;
		}
		for (int i = 2; i < 10; i++) {
			if (check[i])
				continue;
			order[cnt] = i;
			check[i] = true;
			comb(cnt + 1);
			check[i] = false;
		}
	}

	static int game() {
		ttcnt++;
		int inning = 1;
		int tempscore = 0;
		All :while (inning <= N) {
			for (int i = 1; i < 10; i++) {
//				if(tempscore>103) {
//					System.out.print(out+" ");
//					System.out.print(map[inning][baseball[i]]+" ");
//				}
				if (map[inning][baseball[i]] == 1) {
					base[0] = base[3];
					base[3] = base[2];
					base[2] = base[1];
					base[1] = 1;
					if (base[0] == 1) {
						tempscore++;
						base[0] = 0;
					}
				} else if (map[inning][baseball[i]] == 2) {
					base[0] = base[3] + base[2];
					base[3] = base[1];
					base[2] = 1;
					base[1]=0;
					if (base[0] >= 1) {
						tempscore = tempscore + base[0];
						base[0] = 0;
					}
				} else if (map[inning][baseball[i]] == 3) {
					base[0] = base[3] + base[2] + base[1];
					base[3] = 1;
					base[2]=0;
					base[1]=0;
					if (base[0] >= 1) {
						tempscore = tempscore + base[0];
						base[0] = 0;
					}
				} else if (map[inning][baseball[i]] == 4) {
					base[0] = base[3] + base[2] + base[1] + 1;
					base[3]=0;
					base[2]=0;
					base[1]=0;
					if (base[0] >= 1) {
						tempscore = tempscore + base[0];
						base[0] = 0;
					}
				} else if (map[inning][baseball[i]] == 0) {
					out++;
//					if(tempscore>103) {
//						System.out.print(inning+" ");
//						System.out.print(tempscore+" ");
//					}
				}
				if (out == 3) {
					out = 0;
					inning++;
					base[3] = 0;
					base[2] = 0;
					base[1] = 0;
					base[0] = 0;
				}
//				if(tempscore>103) {
//					System.out.print(inning+" ");
//					System.out.println(ttcnt);
//				}
				if (i == 9) {
					i -= 9;
				}
				if (inning > N) {
					base[3] = 0;
					base[2] = 0;
					base[1] = 0;
					out = 0;
					break All;
				}
			}
		}
		return tempscore;
	}
}
