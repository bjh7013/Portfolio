package testProject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
public class Jongyo {
	private static int n, m;
	private static int people[];
	private static int cnt;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		people = new int[n + 1];
		makeSet(people);
		
		for (int i = 0; i < m; i++) {
			unionSet(sc.nextInt(), sc.nextInt());
		}
		
		for (int i = 1; i < people.length; i++) {
			if (people[i] != i) {
				continue;
			}
			cnt++;
		}
		System.out.println(cnt);
		sc.close();
	}

	// 기본 설정
	private static void makeSet(int[] people) {
		for (int i = 1; i < people.length; i++) {
			people[i] = i;
		}
	}

	private static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x == y) return;

		people[y] = x;
	}

	private static int findSet(int x) {
		
		if (x == people[x]) {
			return x;
		} 
		return people[x] = findSet(people[x]);
		
//		return x == people[x] ? x : (people[x] = findSet(people[x]));
	}
}



