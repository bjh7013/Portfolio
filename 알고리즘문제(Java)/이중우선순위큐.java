package testProject;
/*
 * 백준 7662번 이중우선순위큐
 * 
 * SSAFY_구미_3기 백정훈 코드
 * 
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 이중우선순위큐 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			TreeMap<Long, Long> tree = new TreeMap<>();

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				long val = Long.parseLong(st.nextToken());
				if (s.equals("I")) {
					if (tree.containsKey(val)) {
						tree.put(val, tree.get(val) + 1);
					} else {
						tree.put(val, 1L);
					}
				} else {
					if (tree.isEmpty())
						continue;
					if (val == 1) {
						long tempval = tree.lastKey();
						if (tree.get(tempval) > 1) {
							tree.put(tempval, tree.get(tempval) - 1);
						} else {
							tree.pollLastEntry();
						}
					} else {
						long tempval = tree.firstKey();
						if (tree.get(tempval) > 1) {
							tree.put(tempval, tree.get(tempval) - 1);
						} else {
							tree.pollFirstEntry();
						}
					}
				}
			}
			if (tree.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				System.out.print(tree.lastEntry().getKey() + " ");
				System.out.println(tree.firstEntry().getKey());
			}
		}
	}
}
