package testProject;

/*
 * 백준 1764번 듣보잡
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class 듣보잡 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int cnt = 0;
		Set<String> set = new HashSet<>();
		List<String> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			set.add(sc.next());
		}
		for (int i = 0; i < M; i++) {
			String s = sc.next();
			if (set.contains(s)) {
				list.add(s);
				cnt++;
			}
		}
		Collections.sort(list);

		System.out.println(cnt);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));		}
	}

}
