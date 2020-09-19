package testProject;

/*
 * 백준 1620번 나는야 포켓몬 마스터 이다솜
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 나는야포켓몬마스터이다솜 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		String[] book = new String[N + 2];
		Map<String, String> map1 = new HashMap<>();

		for (int i = 1; i < N + 1; i++) {
			String s = sc.next();
			map1.put(s, String.valueOf(i));
			map1.put(String.valueOf(i), s);
		}
		for (int i = 0; i < M; i++) {
			System.out.println(map1.get(sc.next()));
		}
	}
}
