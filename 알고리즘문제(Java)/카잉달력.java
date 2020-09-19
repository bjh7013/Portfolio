package testProject;

import java.io.BufferedReader;
/*
 * 백준 6064번 카잉 달력
 * 
 * SSAFY_구미_3기 백정훈 코드
 * 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 카잉달력 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("test.txt"));
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			s=br.readLine();
			st = new StringTokenizer(s);
			int M = Integer.parseInt(st.nextToken());// M은 x의 최대
			int N = Integer.parseInt(st.nextToken());// N의 y의 최대
			int x = Integer.parseInt(st.nextToken());// x
			int y = Integer.parseInt(st.nextToken());// y
			int xtemp = 0;
			int ytemp = 0;
			int count = 0;
			boolean sol = false;
			boolean[] check = new boolean[N + M + 1];
			if (M < N) {
				count = x; 
				xtemp = x;
				ytemp = x;
				while (true) {
					if (check[ytemp]) { // y값이 재방문시
						break;
					}
					if (ytemp == y) {// y값이 맞을 때
						sol = true;
						break;
					}
					check[ytemp] = true;
					ytemp += (M - N);
					count += M;
					if (ytemp < 1) {
						ytemp += N;
						continue;
					}
					if (ytemp > N) {
						ytemp -= N;
						continue;
					}
				}
			} else {
				count = y;
				xtemp = y;
				ytemp = y;
				while (true) {
//					System.out.println(xtemp);
					if (check[xtemp]) { // x값이 재방문시
						break;
					}
					if (xtemp == x) {// x값이 맞을 때
						sol = true;
						break;
					}
					check[xtemp] = true;
					xtemp += (N - M);
					count += N;
					if (xtemp > M) {
						xtemp -= M;
						continue;
					}
					if (xtemp < 1) {
						xtemp += M;
						continue;
					}
				}
			}
			if(sol) {
				System.out.println(count);				
			}else {
				System.out.println(-1);
			}
		}
	}
}
