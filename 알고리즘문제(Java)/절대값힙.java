package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 절대값힙 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		PriorityQueue<Integer> pqplus = new PriorityQueue<>();
		PriorityQueue<Integer> pqminus = new PriorityQueue<>(Collections.reverseOrder());
		int N = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			int temp = sc.nextInt();
			if (temp != 0) {
				if (temp < 0) {
					pqminus.add(temp);
				} else {
					pqplus.add(temp);
				}
			} else {
				if (pqminus.isEmpty() && pqplus.isEmpty()) {
					System.out.println("0");
				} else if (pqminus.isEmpty()) {
					System.out.println(pqplus.poll());
				} else if (pqplus.isEmpty()) {
					System.out.println(pqminus.poll());
				} else if (Math.abs(pqminus.peek()) <= Math.abs(pqplus.peek())) {
					System.out.println(pqminus.poll());
				} else {
					System.out.println(pqplus.poll());
				}
			}
		}
	}
}
