package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Atm {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] Pi = new int[N];
		for (int i = 0; i < N; i++) {
			Pi[i]=sc.nextInt();
		}
		Arrays.sort(Pi);
		int sum=0;
		int result=0;
		for(int i=0;i<N;i++) {
			sum+=Pi[i];
			result+=sum;
		}
		System.out.println(result);
	}
}