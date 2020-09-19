package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class 링크드리스트 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 수열의 길이 ex)5개 배열
			int M = sc.nextInt(); // 추가 횟수 ex)2번
			int L = sc.nextInt(); // 출력할 인덱스L ex)5번째
			LinkedList<Integer> list = new LinkedList<>(); // Linked리스트 생성

			for (int i = 0; i < N; i++) {
				list.add(sc.nextInt()); // N개의 인덱스에 숫자 주입중 ex)5개 1,2,3,4,5
			}
			for (int i = 0; i < M; i++) {//list.add(인덱스번호, 넣을값) ex) add(2,7);
				list.add(sc.nextInt(), sc.nextInt());
			}
//			for (int i = 0; i < list.size(); i++) { 
//				System.out.print(list.get(i) + " ");
//			}
			System.out.println(list.get(L));
		}
	}
}
