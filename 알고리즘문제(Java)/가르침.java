package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class 가르침 {
	public static char charSet[]; // 후보군 배열
	public static boolean visit[]; // 백트래킹 방문 여부 체크
	public static int N, K, wordSetSize, max = 0, candidateSize;
	public static String wordSet[]; // 단어저장소

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int wordSize, preCount = 0;
		N = sc.nextInt();
		K = sc.nextInt();
		wordSet = new String[N]; // 추후 탐색을 위한 단어저장소
		// 후보군 해시셋(중복방지 보장)
		HashSet<Character> candidate = new HashSet<>();
		int resultcnt = 0;
		int w = 0;
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			s = s.replaceAll("[antic]", "");
			if (s.length() == 0)
				resultcnt++;
			else {
				wordSet[w++] = s;
				for (int j = 0; j < s.length(); j++) {
					candidate.add(s.charAt(j));
				}
			}
		}

		if (K < 5)
			resultcnt = 0;
		else {
			K -= 5;
			wordSetSize = w;
			candidateSize = candidate.size();
			charSet = new char[candidateSize];
			visit = new boolean[candidateSize];
			int i = 0;
			for (char ch : candidate) {
				charSet[i++] = ch;
			}
			if (candidateSize < K)
				K = candidateSize;
			dfs(new HashSet<>(), 0, 0);
		}
		System.out.println(max+resultcnt);

	}

	private static void dfs(HashSet<Character> picked, int idx, int pick) {
		if (pick == K) {
			boolean imp;
			int wordSize = 0;
			int count = 0;
			for (int i = 0; i < wordSetSize; i++) {
				imp = false;
				wordSize = wordSet[i].length();
				for (int j = 0; j < wordSize; j++)
					if (!picked.contains(wordSet[i].charAt(j))) {
						imp = true;
						break;
					}
				if (!imp)
					count++;
			}
			max = Math.max(max, count);
		}
		for (int i = idx; i < candidateSize; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			picked.add(charSet[i]);
			dfs(picked, i, pick + 1);
			picked.remove(charSet[i]);
			visit[i] = false;
		}
	}
}
