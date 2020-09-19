package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class hambergur {
	static int totalK = 0;
	static int totalT = 0;
	static int result =0;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int N;
		int L;
		int[] Ti;
		int[] Ki;

		for (int i = 0; i < T; i++) {
			totalK=0;
			totalT=0;
			result=0;
			N = sc.nextInt();
			L = sc.nextInt();
			Ti = new int[N];
			Ki = new int[N];

			for (int j = 0; j < N; j++) {
				Ti[j] = sc.nextInt();
				Ki[j] = sc.nextInt();
			}
			System.out.println("#"+(i+1)+" "+ham(Ti, Ki, N, L,0));

		}
	}

	static private int ham(int[] T, int[] K, int n, int l,int temp) {
		int tempk=0;
		int tempt=0;
		for (int i = temp; i < n; i++) {
			totalK += K[i];
			totalT += T[i];
			if(totalK<=l) {
				//System.out.println(totalT+" " + totalK);
				if(result<totalT) result=totalT;
				tempk=totalK;
				tempt=totalT;
				ham(T, K, n, l,i+1);
				totalK=tempk-K[i];
				totalT=tempt-T[i];
				continue;
			}
			
			if(totalK>l) {
				totalK-=K[i];
				totalT-=T[i];
				continue;
			}
		}
		return result;
	}

}
