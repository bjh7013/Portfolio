package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 화섭이의미생물배양 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			Queue<BFS> q = new LinkedList<>();
			int start = sc.nextInt();
			int end = sc.nextInt();
			int sum = sc.nextInt();
			int mul = sc.nextInt();
			boolean[] b = new boolean[end+1];
			int result=-1;
			q.add(new BFS(start,0));

			while (!q.isEmpty()) {
				BFS val = q.poll();
				if(val.num==end) {
					result=val.cnt;
					break;
				}
				for (int i = 0; i < 2; i++) {
					if (i == 1) {
						if (val.num + sum > end)
							continue;
						if(b[(int)val.num+sum])
							continue;
						q.add(new BFS(val.num+sum,val.cnt+1));
						b[(int)val.num + sum] = true;
					}
					if (i == 0) {
						if (val.num * mul > end)
							continue;
						if(b[(int)val.num*mul])
							continue;
						q.add(new BFS(val.num*mul,val.cnt+1));
						b[(int)val.num * mul] = true;
					}
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}
	static class BFS{
		long num;
		int cnt;

		public BFS(long num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}
	}
}
