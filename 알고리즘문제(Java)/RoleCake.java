package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class RoleCake {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();
		int N = sc.nextInt();
		int[] Pi = new int[N];
		int[] Ki = new int[N];
		int temp = -1;
		boolean[] b = new boolean[L];
		int[] num = new int[N];
		int result=0;
		int tempi = -1;
		int sol = 0;

		for (int i = 0; i < N; i++) {
			Pi[i] = sc.nextInt();
			Ki[i] = sc.nextInt();
			if (Ki[i] - Pi[i] > temp) {
				temp = Ki[i] - Pi[i];
				result=i+1;
			}
			for (int j = Pi[i] - 1; j <= Ki[i] - 1; j++) {
				if (b[j])
					continue;
				b[j] = true;
				num[i]++;
			}
			if (num[i] > tempi) {
				tempi = num[i];
				sol = i;
			}
		}

		System.out.println(result);
		System.out.println(sol + 1);

	}
}