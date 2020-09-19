package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class 비밀번호찾기 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 저장된 사이트 수
		int M = sc.nextInt(); // 비밀번호를 찾으려는 사이트 수
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			map.put(sc.next(), sc.next());
		}
		for (int i = 0; i < M; i++) {
			System.out.println(map.get(sc.next()));
		}
	}
}
