package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 스위치켜고끄기 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 스위치 수
		int[] map = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			map[i] = sc.nextInt();
		}
		int M = sc.nextInt(); // 학생 수
		Queue<Student> q = new LinkedList<>();
		for (int i = 1; i < M + 1; i++) {
			q.add(new Student(sc.nextInt(), sc.nextInt())); // 각 학생의 성별 남:1 여:2, 스위치 갯수
		}
//		System.out.println(q.size());
		for (int j = 1; j < M + 1; j++) {
			Student stu = q.poll();

			if (stu.sex == 1) {
				for (int i = stu.num; i < N + 1; i += stu.num) {
					if (map[i] == 0) {
						map[i] = 1;
					} else {
						map[i] = 0;
					}
				}
			} else {
				if (map[stu.num] == 0) {
					map[stu.num] = 1;
				} else {
					map[stu.num] = 0;
				}
				for (int i = 1; stu.num + i < N + 1 && stu.num - i > 0; i++) {
					if (map[stu.num + i] == 1 && map[stu.num - i] == 1) {
						map[stu.num + i] = 0;
						map[stu.num - i] = 0;
					} else if (map[stu.num + i] == 0 && map[stu.num - i] == 0) {
						map[stu.num + i] = 1;
						map[stu.num - i] = 1;
					} else {
						break;
					}
				}
			}
		}

		for (int i = 1; i < N + 1; i++) {
			if (i != 1 && i % 20 == 1)
				System.out.println();
			System.out.print(map[i] + " ");
		}

	}

	static class Student {
		int sex, num;

		public Student(int sex, int num) {
			super();
			this.sex = sex;
			this.num = num;
		}
	}
}
