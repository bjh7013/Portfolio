package testProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class 좌표압축 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N + 1];
		List<Integer> list1 = new ArrayList<>();
		List<Press> list2 = new ArrayList<>();
		HashMap<Integer, Integer> hash = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());
			map[i] = value;
			list1.add(value);
		}
		Collections.sort(list1);

		int temp = 0;

		for (int i = 0; i < list1.size(); i++) {
			if (!hash.containsKey(list1.get(i))) {
				hash.put(list1.get(i), temp++);
			}
		}

		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < N; i++) {
			sb.append(hash.get(map[i])+" ");
		}
		System.out.println(sb);
	}

	static class Press {
		int value, num;

		public Press(int value, int num) {
			super();
			this.value = value;
			this.num = num;
		}

		public Press() {
			super();
			// TODO Auto-generated constructor stub
		}
	}
}
