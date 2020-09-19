package testProject;

import java.util.Scanner;

public class Dice {
	static int[] di = { 1, 2, 3, 4, 5, 6 };
	static int[] result;
	static int cnt;
	static int value=0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String b;
		int N, M;
		N = sc.nextInt();
		M = sc.nextInt();
		result=new int[N];
		cnt=N;
		dice(N, M);
	}

	public static void dice(int n, int m) {
		
		if(n==0) {
			for(int i=0; i<cnt; i++) {
				value+=result[cnt-i-1];
			}
			if(value==m) {
			for(int i=0; i<cnt; i++) {
				System.out.print(result[cnt-i-1]+" ");
			}
			System.out.println();
			}
			value=0;
			return;
		}
		for (int i = 0; i < di.length; i++) {
			result[n-1] = di[i];
			
			dice(n-1,m);
		}
	}
}
