package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BlackJack {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 카드 갯수
		int M = sc.nextInt(); // 넘지 말아야 할 숫자
		int[] card = new int[N];
		for (int i = 0; i < N; i++) {
			card[i] = sc.nextInt();
		}
		jack(card, 3, 0, M);
		System.out.println(result);
	}

	static int sum = 0;
	static int result = 3000000;
	static int realcnt=0;

	static void jack(int[] card, int cnt, int num, int M) {
		if (cnt == 0)
			return;
		for (int i = num; i < card.length; i++) {
			sum += card[i];
			realcnt++;
//			System.out.print(sum+" ");
			if ((Math.abs(sum - M) < Math.abs(result - M)) && sum <= M && realcnt==3) {
				
				result = sum;
			}
			jack(card, cnt - 1, i + 1, M);
			realcnt--;
			sum -= card[i];
		}
	}
}