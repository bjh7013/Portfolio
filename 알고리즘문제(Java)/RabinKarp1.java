package testProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;

public class RabinKarp1 {
	static int cnt = 0;

	static void rabinfindPattern(String text, String pattern) {
		/*
		 * q a prime number p hash value for pattern t hash value for text d is the
		 * number of unique characters in input alphabet
		 */
		int d = 128;
		int q = 107;
		int end = text.length();
		int m = pattern.length();
		int t = 0, p = 0;
		int h = 1;
		int i, j;
		int start = 0;
		// 1.패턴의 해시값 계산
		for (i = 0; i < m - 1; i++)
			h = (h * d) % q;
		// 2.text에서 패턴의 길이 만큼 잘라서 해시 값 계산
		for (i = 0; i < m; i++) {
			p = (d * p + pattern.charAt(i)) % q;
			t = (d * t + text.charAt(i)) % q;
		}
		// 3.
		for (i = 0; i < end - m; i++) {
			if (p == t) {
				// if the hash value matches match them character by character
				for (j = 0; j < m; j++)
					if (text.charAt(j + i) != pattern.charAt(j))
						break;
				if (j == m && i >= start) {
					cnt++;
					return;
				}
			}
			if (i < end - m) {
				t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;
				if (t < 0)
					t = t + q;
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		String s1 = sc.nextLine();
		String s2 = sc.nextLine();
		rabinfindPattern(s1, s2);
		if (cnt == 0) {
			System.out.println("0");
		} else {
			System.out.println("1");
		}
	}
}