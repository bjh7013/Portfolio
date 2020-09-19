package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Pass {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		Queue<Integer> q = new LinkedList<Integer>();
		int a;
		int number;
		for (int i = 0; i < 10; i++) {
			number = sc.nextInt();

			for (int j = 0; j < 8; j++) {
				
				q.offer(sc.nextInt());
			}
			All : while (true) {
				for (int j = 1; j < 6; j++) {
					if ((q.peek() - j) <= 0) {
						q.offer(0);
						q.poll();
						break All;
					}
					q.offer(q.poll() - j);
				}
				
			}
			System.out.print("#"+number+ " ");
			for (int j = 0; j < 8; j++) {
				System.out.print(q.poll() + " ");
			}
			q.clear();
			System.out.println();
			
		}
	}
}
