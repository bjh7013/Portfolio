package testProject;

/*
 * 백준 5430번 AC
 * 
 * SSAFY_구미_3기 백정훈 코드
 * Deque문제
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AC {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			String command = sc.next();
			int leng = sc.nextInt();
			String s = sc.next();
			String[] temps = new String[leng + 1];
			Deque<String> deque = new LinkedList<>();
			temps = s.replace("[", "").replace("]", "").split(",");
			for (int i = 0; i < leng; i++) {
				deque.add(temps[i]);
			}
			boolean check = false;
			boolean head = true;

			for (int i = 0; i < command.length(); i++) {
				if (command.charAt(i) == 'R') {
					head = !head;
				}
				if (command.charAt(i) == 'D') {
					if (deque.size() == 0) {
						check = true;
						break;
					}
					if (head) {
						deque.poll();
					} else {
						deque.pollLast();
					}
				}
			}
			if (check) {
				System.out.println("error");
			} else {
				if(head) {
					StringBuffer sb = new StringBuffer("[");
					int size = deque.size();
					for (int i = 0; i < size; i++) {
						sb.append(deque.getFirst());
						deque.poll();
						if (i != size - 1)
							sb.append(",");
					}
					sb.append("]");
					System.out.println(sb);
				}else {
					StringBuffer sb = new StringBuffer("[");
					int size = deque.size();
					for (int i = 0; i < size; i++) {
						sb.append(deque.getLast());
						deque.pollLast();
						if (i != size - 1)
							sb.append(",");
					}
					sb.append("]");
					System.out.println(sb);
				}
			}
		}
	}
}
