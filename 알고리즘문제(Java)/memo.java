package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class memo {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int[] a= new int[4];

		for (int i = 0; i < 4; i++) {
			a[i] = sc.nextInt();
		}
		Arrays.sort(a);
		for (int i = 0; i < 4; i++) {
			System.out.println(a[i]);
		}

	}
}