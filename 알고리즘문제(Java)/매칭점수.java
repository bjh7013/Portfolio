package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class 매칭점수 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		String word = sc.nextLine().toLowerCase();
		String[] saves = new String[2];
		for (int i = 0; i < 2; i++) {
			saves[i] = sc.nextLine().toLowerCase();
		}
		String[] s = saves; // 1~N번째 html
		double[] score = new double[s.length];
		double[] pagescore = new double[s.length];
		int[] linkcnt = new int[s.length];
		HashMap<String, Integer> map = new HashMap<>();

		for (int i = 0; i < s.length; i++) {
			String[] url = s[i].split("content="); // 해당 페이지의 url구하기
			for (int j = 1; j < url.length; j++) { // 해당 페이지 url 구하기
				String[] realurl = url[j].split("/>");
				System.out.println(realurl[0]);
				map.put(realurl[0], i);
			}
		}

		for (int i = 0; i < s.length; i++) { // 각 페이지의 외부링크 수
			String[] urlcnt = s[i].split("<a href=\"");
			int cntlink = 0;
			for (int j = 1; j < urlcnt.length; j++) {
				cntlink++;
			}
			linkcnt[i] = cntlink;
		}

		for (int i = 0; i < s.length; i++) {
			String[] content = s[i].split(word);
			int cnt = 0;
			for (int j = 1; j < content.length; j++) { // 해당 단어 중복 수 구하기
				if (content[j].equals("") || (content[j].charAt(0) >= 'a' && content[j].charAt(0) <= 'z'))
					continue;
				if (content[j - 1].equals("") || (content[j - 1].charAt(content[j - 1].length() - 1) >= 'a'
						&& content[j - 1].charAt(content[j - 1].length() - 1) <= 'z') && (content[j - 1].charAt(content[j - 1].length() - 2) !='\\')) //여기 함
					continue;
				cnt++;
			}
			pagescore[i] = cnt;
		}
		for (int i = 0; i < s.length; i++) {
			String[] urlcnt = s[i].split("<a href=");
			for (int j = 1; j < urlcnt.length; j++) {
				String[] urls = urlcnt[j].split(">");
				System.out.println(urls[0]);
				if (map.containsKey(urls[0]))
					score[map.get(urls[0])] += (pagescore[i] / linkcnt[i]);
			}
			score[i] += pagescore[i];
		}

		double temp = -1.0;
		int sol = -1;
		for (int i = 0; i < s.length; i++) {
			if (score[i] > temp) {
				temp = score[i];
				sol = i;
			}
		}
		System.out.println(sol);
	}
}
