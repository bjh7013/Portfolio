package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숫자게임 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			String N = sc.next();
			int cnt = 0;
			Queue<Number> q = new LinkedList<>();
			q.add(new Number(N, 0));
			boolean[][] check = new boolean[100000][300];// 숫자랑, 카운터
			while (!q.isEmpty()) {
				Number number = q.poll();
				if (check[Integer.parseInt(number.num)][number.cnt])
					continue;
				check[Integer.parseInt(number.num)][number.cnt]=true;
				cnt = Math.max(cnt, number.cnt);
				if (Integer.parseInt(number.num) < 10) {
					cnt = Math.max(cnt, number.cnt);
					continue;
				}
				int[] data = new int[number.num.length()];
				int size = number.num.length();
				for (int i = 0; i < size; i++) {
					data[i] = (number.num.charAt(i) - 48);
				}
				int result1 = 1;
//				for (int i = 0; i < size; i++) {
//					result1 *= data[i];
//				}
//				q.add(new Number(Integer.toString(result1), number.cnt + 1));

				String sub = "";
				int tempcnt = 1;
				for (int i = 0; i < size - 1; i++) { // 1
					sub = sub.concat(Integer.toString(data[i]));
					if (Integer.parseInt(sub) == 0) {
						break;
					}
//					System.out.println(sub);
					String sub2 = "";
					tempcnt *= Integer.parseInt(sub);
					for (int j = i + 1; j < size; j++) {// 2
						sub2 = sub2.concat(Integer.toString(data[j]));
						if (Integer.parseInt(sub2) == 0) {
							break;
						}
						String sub3 = "";
						tempcnt *= Integer.parseInt(sub2);
						for (int k = j + 1; k < size; k++) {// 3
							sub3 = sub3.concat(Integer.toString(data[k]));
							if (Integer.parseInt(sub3) == 0) {
								break;
							}
							String sub4 = "";
							tempcnt *= Integer.parseInt(sub3);
							for (int l = k + 1; l < size; l++) {// 4
								sub4 = sub4.concat(Integer.toString(data[l]));
								if (Integer.parseInt(sub4) == 0) {
									break;
								}
								String sub5 = "";
								tempcnt *= Integer.parseInt(sub4);
								for (int m = l + 1; m < size; m++) {// 5
									sub5 = sub5.concat(Integer.toString(data[m]));
									if (Integer.parseInt(sub5) == 0) {
										break;
									}
									tempcnt *= Integer.parseInt(sub5);
									q.add(new Number(Integer.toString(tempcnt), number.cnt + 1));
									tempcnt /= Integer.parseInt(sub5);
								}
								if (sub5.equals(""))
									q.add(new Number(Integer.toString(tempcnt), number.cnt + 1));
								tempcnt /= Integer.parseInt(sub4);
							}
							if (sub4.equals(""))
								q.add(new Number(Integer.toString(tempcnt), number.cnt + 1));
							tempcnt /= Integer.parseInt(sub3);
						}
						if (sub3.equals(""))
							q.add(new Number(Integer.toString(tempcnt), number.cnt + 1));
						if (Integer.parseInt(sub2) != 0)
							tempcnt /= Integer.parseInt(sub2);
					}
//					System.out.println(sub2);
					if (sub2.equals(""))
						q.add(new Number(Integer.toString(tempcnt), number.cnt + 1));
					tempcnt /= Integer.parseInt(sub);
//						tempcnt = 1;
					if (Integer.parseInt(sub2) % 10 == 0)
						q.add(new Number("0", number.cnt + 1));
				}

			}
//			System.out.println(data[0]+data[0]);
			System.out.println("#" + tc + " " + cnt);
		}
	}

	static class Number {
		String num;
		int cnt;

		public Number(String num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

	}
}
