package testProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MakePassward {
	static int N;
	static int M;
	static char[] ch;
	static char[] tempch;
	static boolean[] b;
	static int cnt = 0;
	static BufferedWriter bw;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		M = sc.nextInt();
		N = sc.nextInt();
		ch = new char[N + 1];
		tempch = new char[N + 1];
		cnt = 0;
		b = new boolean[N + 1];

		for (int i = 1; i < N + 1; i++) {
			ch[i] = sc.next().charAt(0);
		}
		Arrays.sort(ch);
		try {
			dfs(0);
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void dfs(int cnt) throws IOException {
		if (cnt == M) {
			int a = 0;
			int b = 0;
			for (int i = 0; i < M; i++) {
				if (tempch[i] == 'a' || tempch[i] == 'e' || tempch[i] == 'i' || tempch[i] == 'u' || tempch[i] == 'o') {
					a++;
					continue;
				}
				b++;
			}
			if (a >= 1 && b >= 2) {

				for (int i = 0; i < M; i++) {
					bw.write(tempch[i]);
				}
				bw.newLine();
			}
			return;
		}
		for (int i = 1; i < N + 1; i++) {
			if (b[i])
				continue;
			tempch[cnt] = ch[i];
			for (int j = 0; j <= i; j++) {
				b[j] = true;
			}
			dfs(cnt + 1);
			for (int j = 0; j <= i; j++) {
				b[j] = false;
			}
		}
	}
}
