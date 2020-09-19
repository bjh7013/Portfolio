package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NongJak {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();
		char[][] map;
		String[] value;
		int mid;
		

		for (int tc = 1; tc <= TC; tc++) {
			int result=0;
			int size = sc.nextInt();
			mid = size / 2;

			map = new char[size + 2][size + 2];
			value = new String[size + 2];
			for (int i = 0; i < size; i++) {
				value[i] = sc.next();
			}

			for (int i = 0; i < size; i++) {
				map[i] = value[i].toCharArray();
			}

//			for (int i = 0; i < size; i++) {
//				for (int j = 0; j < size; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			
			for (int i = 0; i < mid; i++) {
				for (int j = mid-i; j < mid+1+i; j++) {
					result+=map[i][j]-48;
				}
			}
			int low=0;
			for (int i = mid; i < size; i++) {
				for (int j = low; j < size-low; j++) {
					result+=map[i][j]-48;
				}
				low++;
			}
			
			System.out.println("#"+tc+" "+result);

		}
	}
}
