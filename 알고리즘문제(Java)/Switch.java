package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Switch {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		boolean[] bull = new boolean[s+1];
		int[] val = new int[s+1];
		int a;

		for (int i = 1; i < s+1; i++) {
			a = sc.nextInt();
			if (a == 1) {
				bull[i] = true;
			}
			if (a == 0) {
				bull[i] = false;
			}
		}
		int st = sc.nextInt();
		int[] sex = new int[st+1];
		int[] num = new int[st+1];

		for (int i = 1; i < st+1; i++) {
			sex[i] = sc.nextInt();
			num[i] = sc.nextInt();
			// System.out.println(sex[i] +" " + num[i]);
		}
		
		for (int i =1; i < st+1; i++) {
			if (sex[i] == 1) {
				for (int j = num[i]-1; j < s; j += num[i]) {
					if (bull[j] == true) {
						bull[j] = !bull[j];
						continue;
					}
					if (bull[i] == false) {
						bull[j] = !bull[j];
						continue;
					}
				}
				
			}
			if (sex[i] == 2) {
				bull[num[i]-1] = !bull[num[i]-1];
				for (int j = 1; j < s; j++) {
					if (((num[i]-1 + j) > (s - 1)) || (num[i]-1 - j) < 0) {
						break;
					}
					if (bull[num[i]-1 + j] == bull[num[i]-1 - j]) {
						bull[num[i]-1 + j] = !bull[num[i]-1 + j];
						bull[num[i]-1 - j] = !bull[num[i]-1 - j];
						continue;
					}
					break;
				}
			}
		}

		for (int i = 0; i < s; i++) {
			if(bull[i]==true) {
				val[i]=1;
			}
			if(bull[i]==false) {
				val[i]=0;
			}
			if ((i % 20 == 0) && (i != 0))
				System.out.println();
			System.out.print(val[i] + " ");
		}

	}
}
