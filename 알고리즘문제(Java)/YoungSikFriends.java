package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class YoungSikFriends {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 사람 수
		int M = sc.nextInt(); // 공 받은 최대 수
		int L = sc.nextInt(); // 공 던지는 거리
		int cnt = 0;
		int balllocation=0;
		int[] ballcnt = new int[N]; // N번째가 공 받은 횟수
		ballcnt[0]++;
		int result=0;
		
		All : while (true) {
			for (int i = 0; i < N; i++) {
				if (ballcnt[i] == M)
					break All;
			}
			
			if((ballcnt[balllocation]%2)==1) {
				if((balllocation+L)>=N)
				{
					balllocation-=N;
				}
				balllocation+=L;
				ballcnt[balllocation]++;
				result++;
				continue;
			}
			if((ballcnt[balllocation]%2)==0) {
				if((balllocation-L)<0)
				{
					balllocation+=N;
				}
				balllocation-=L;
				ballcnt[balllocation]++;
				result++;
				continue;
			}

		}
		
		System.out.println(result);

	}
}