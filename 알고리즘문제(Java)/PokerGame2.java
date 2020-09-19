package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PokerGame2 {
	static String[] mycard;
	static int[] suit;
	static int[] rank;
	static String ans;
	static String pairString;
	static String result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			mycard = new String[5];
			suit = new int[4];
			rank = new int[13];
			for (int i = 0; i < 5; i++) {
				mycard[i] = sc.next();
				char[] temp = mycard[i].toCharArray();
				makecard(temp);

			}
			ans = "High Card";
			pairString = "High Card";
			jokbo();
			System.out.printf("#%d %s\n", t, result);
		} // t
	}// main

	static void makecard(char[] temp) {
		switch (temp[0]) {
		case 'S':
			suit[0]++;
			break;
		case 'D':
			suit[1]++;
			break;
		case 'H':
			suit[2]++;
			break;
		case 'C':
			suit[3]++;
			break;
		}
		switch (temp[1]) {
		case 'A':
			rank[0]++;
			break;
		case '2':
			rank[1]++;
			break;
		case '3':
			rank[2]++;
			break;
		case '4':
			rank[3]++;
			break;
		case '5':
			rank[4]++;
			break;
		case '6':
			rank[5]++;
			break;
		case '7':
			rank[6]++;
			break;
		case '8':
			rank[7]++;
			break;
		case '9':
			rank[8]++;
			break;
		case 'T':
			rank[9]++;
			break;
		case 'J':
			rank[10]++;
			break;
		case 'Q':
			rank[11]++;
			break;
		case 'K':
			rank[12]++;
			break;
		}
	}

	static void jokbo() {
		countPair();
		isItFlush();
		isItStraight();

		if (ans == "Straight Flush")
			result = ans;
		else if (pairString == "Four of a Kind")
			result = pairString;
		else if (pairString == "Full House")
			result = pairString;
		else if (ans == "Flush")
			result = ans;
		else if (ans == "Straight")
			result = ans;
		else if (ans == "Three of a kind")     // 여기 ans 를 pairString으로 바꿔
			result = pairString;
		else if (pairString == "Two pair")
			result = pairString;
		else if (pairString == "One pair")
			result = pairString;
		else
			result = ans;
	}

	static void countPair() {
		int pair = 0;
		int triple = 0;
		int four = 0;
		for (int i = 0; i < rank.length; i++) {
			if (rank[i] == 2)
				pair++;
			else if (rank[i] == 3)
				triple++;
			else if (rank[i] == 4)
				four++;
		}

		if (four == 1)
			pairString = "Four of a Kind";
		else if (triple == 1 && pair == 1)
			pairString = "Full House";
		else if (triple == 1)
			pairString = "Three of a kind";
		else if (pair == 2)
			pairString = "Two pair";
		else if (pair == 1)
			pairString = "One pair";
//        System.out.println(pair+" "+triple+" "+four);
//        System.out.println(pairString);
	}

	static void isItStraight() {
		int start = 0;
		int count = 0;
		boolean flag = false;
		boolean isStraight = false;
		for (int i = 1; i < rank.length; i++) {
			if (rank[i] == 1 && !flag) {
				start = i;
				flag = true;
				count++;
			}
			if (rank[i - 1] == 1 && rank[i] == 1)
				count++;
		} // for
		if (count == 5)
			isStraight = true;
		else if (count == 4 && start == 9) {
			if (rank[0] == 1)
				isStraight = true;
		}
		if (isStraight && ans == "Flush")
			ans = "Straight Flush";
		else if (isStraight)
			ans = "Straight";
	}

	static void isItFlush() {
		for (int i = 0; i < suit.length; i++) {
			if (suit[i] == 5)
				ans = "Flush";
		}
	}
}