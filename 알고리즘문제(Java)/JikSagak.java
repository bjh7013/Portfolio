package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class JikSagak {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int result = 3;
		if (N == 1)
			result = 1;
		if (N == 2)
			result = 2;
		if (N == 3)
			result = 3;
		int cnt = 0;
		
		for (int i = 4; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				if (i % j == 0)
					cnt++;
			}
			result+=((int)Math.ceil(cnt/2.0));
			cnt=0;
		}
		System.out.println(result);
	}
}