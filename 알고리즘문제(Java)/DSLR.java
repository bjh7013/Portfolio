package testProject;
/*
 * 백준 9019번 DSLR
 * 
 * SSAFY_구미_3기 백정훈 코드
 * 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DSLR {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int val = sc.nextInt();
			int target = sc.nextInt();
			String sol = "";
			Queue<Cal> q = new LinkedList<>();
			boolean[] check = new boolean[10000];
			q.add(new Cal(val / 1000, (val % 1000) / 100, (val % 100) / 10, val % 10, val, ""));
			while (!q.isEmpty()) {
				Cal cal = q.poll();
				if (cal.total == target) {
					sol = cal.command;
					break;
				}
				if (check[cal.total])
					continue;
				check[cal.total] = true;
				for (int i = 0; i < 4; i++) {
					if (i == 0) { // D
						int temptotal = cal.total * 2;
						q.add(new Cal((temptotal % 10000) / 1000, (temptotal % 1000) / 100, (temptotal % 100) / 10,
								temptotal % 10, temptotal % 10000, cal.command.concat("D")));
					}
					if (i == 1) {// S
						if (cal.total == 0) {
							q.add(new Cal(9, 9, 9, 9, 9999, cal.command.concat("S")));
						} else {
							int temptotal = cal.total - 1;
							q.add(new Cal((temptotal % 10000) / 1000, (temptotal % 1000) / 100, (temptotal % 100) / 10,
									temptotal % 10, temptotal % 10000, cal.command.concat("S")));
						}
					}
					if (i == 2) { // L
						int temptotal = cal.x2 * 1000 + cal.x3 * 100 + cal.x4 * 10 + cal.x1;
						q.add(new Cal(cal.x2, cal.x3, cal.x4, cal.x1, temptotal, cal.command.concat("L")));
					}
					if (i == 3) {// R
						int temptotal = cal.x4 * 1000 + cal.x1 * 100 + cal.x2 * 10 + cal.x3;
						q.add(new Cal(cal.x4, cal.x1, cal.x2, cal.x3, temptotal, cal.command.concat("R")));
					}
				}
			}
			System.out.println(sol);
		}
	}

	static class Cal {
		int x1, x2, x3, x4, total;
		String command;

		public Cal() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Cal(int x1, int x2, int x3, int x4, int total, String command) {
			super();
			this.x1 = x1;
			this.x2 = x2;
			this.x3 = x3;
			this.x4 = x4;
			this.total = total;
			this.command = command;
		}

	}
}
