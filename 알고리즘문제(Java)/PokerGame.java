package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PokerGame {
	static int type = 0;
	static int[] suit;
	static int[] rank;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			String[] s = new String[5];
			rank = new int[15]; // 숫자
			suit = new int[5];// D,C,H,S = 1,2,3,4
			type = 0;
			for (int i = 0; i < s.length; i++) {
				s[i] = sc.next();
				if (s[i].charAt(1) == 'A') {
					rank[1]++;
					rank[14]++;
				} else if (s[i].charAt(1) == 'T') {
					rank[10]++;
				} else if (s[i].charAt(1) == 'J') {
					rank[11]++;
				} else if (s[i].charAt(1) == 'Q') {
					rank[12]++;
				} else if (s[i].charAt(1) == 'K') {
					rank[13]++;
				} else {
					rank[s[i].charAt(1) - 48]++;
				}

				if (s[i].charAt(0) == 'D') {
					suit[1]++;
				} else if (s[i].charAt(0) == 'C') {
					suit[2]++;
				} else if (s[i].charAt(0) == 'H') {
					suit[3]++;
				} else {
					suit[4]++;
				}
			}
			int sol = check();
			System.out.print("#"+tc+" ");
			if (sol == 1) {
				System.out.println("Straight Flush");
			} else if (sol == 2) {
				System.out.println("Four of a Kind");
			} else if (sol == 3) {
				System.out.println("Full House");
			} else if (sol == 4) {
				System.out.println("Flush");
			} else if (sol == 5) {
				System.out.println("Straight");
			} else if (sol == 6) {
				System.out.println("Three of a kind");
			} else if (sol == 7) {
				System.out.println("Two pair");
			} else if (sol == 8) {
				System.out.println("One pair");
			} else {
				System.out.println("High card");
			}

//			1. Straight Flush : 모두 동일한 슈트에 랭크(값)가 모두 연속적이다(여기서는 로얄 플러쉬를 포함한다. 로얄 플러쉬는 모두 동일한 슈트에 T, J, Q, K, A를 갖는다).
//			2. Four of a Kind : 5장 중 4개의 랭크(값)가 모두 같다.
//			3. Full House : 3장의 동일한 랭크(값)와 다른 랭크(값)의 동일한 2장으로 구성된다.
//			4. Flush : 5장이 모두 동일한 슈트다.
//			5. Straight : 다른 슈트가 섞여있으며 랭크(값)가 모두 연속적이다.
//			6. Three of a kind : 동일한 랭크(값)가 3장이다(1,2,3,3,3).
//			7. Two pair : 동일한 랭크(값) 2장씩 두개의 랭크다(2,6,6,3,3).
//			8. One pair : 동일한 랭크가 2장이다(2,4,5,5,7).
//			9. High card : 1~8번에 해당하지 않는다.

		}
	}

	static int check() {
		for (int i = 1; i < 5; i++) { // 1번확인
			if (suit[i] == 5) {
				for (int j = 1; j < 11; j++) {
					for (int k = j; k < j + 5; k++) {
						if (rank[k] == 0)
							break;
						if (k == j + 4)
							return 1;
					}
				}
			}
		}
		for (int i = 1; i < 14; i++) { // 2번확인
			if (rank[i] == 4)
				return 2;
		}
		for (int i = 1; i < 14; i++) { // 3번 확인
			if (rank[i] == 3) {
				for (int j = 1; j < 14; j++) {
					if (rank[j] == 2)
						return 3;
				}
			}
		}
		for (int i = 0; i < 5; i++) { // 4번 확인
			if (suit[i] == 5)
				return 4;
		}
		for (int j = 1; j < 11; j++) { // 5번 확인
			for (int k = j; k < j + 5; k++) {
				if (rank[k] == 0)
					break;
				if (k == j + 4)
					return 5;
			}
		}
		for (int i = 1; i < 14; i++) { // 6번확인
			if (rank[i] == 3)
				return 6;
		}

		boolean[] b = new boolean[15];
		for (int i = 1; i < 14; i++) { // 7번확인
			if (rank[i] == 2) {
				b[i] = true;
				for (int j = 1; j < 14; j++) {
					if (b[j])
						continue;
					if (rank[j] == 2)
						return 7;
				}
				b[i] = false;
			}
		}

		for (int i = 1; i < 14; i++) { // 8번 확인
			if (rank[i] == 2)
				return 8;
		}
		return 9;
	}
}
