package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BT {
	static int cnt = 0;
	static int N;
	static StringBuffer sb = new StringBuffer("");
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			int temp =1;
			sb = new StringBuffer("");
			N = sc.nextInt();
			int[] num = new int[N + 1];
			char[] a = new char[N + 1];
			for (int i = 1; i < N + 1; i++) {
				num[i] = sc.nextInt();
				a[i] = sc.nextLine().charAt(1);
			}
			System.out.print("#"+tc+" ");
			inOrder(a, 1);
			for(int i=0;i<sb.length()-1;i++) {
				if(sb.charAt(i)=='*'||sb.charAt(i)=='/'||sb.charAt(i)=='+'||sb.charAt(i)=='-') {
					if(sb.charAt(i+1)=='*'||sb.charAt(i+1)=='/'||sb.charAt(i+1)=='+'||sb.charAt(i+1)=='-') {
						temp=0;
						break;
					}
				}
			}
			System.out.print(temp);
			System.out.println();
		}
	}

	public static void inOrder(char[] array, int count) {
		if (count >= array.length) {
			return;
		}

		inOrder(array, 2 * count);
//		System.out.print(array[count]);
		sb.append(array[count]);
		inOrder(array, 2 * count + 1);
	}
}