package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 최대힙 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		StringBuffer sb = new StringBuffer("");

		for (int i = 0; i < N; i++) {
			int temp = sc.nextInt();
			if (temp == 0) {
				if (pq.isEmpty()) {
					System.out.println("0");
				} else {
					System.out.println(pq.poll());
				}
			} else {
				pq.add(temp);
			}
		}
	}
}
