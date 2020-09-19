package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Teleport {
	static int me;
	static int you;
	static int cnt;
	static Queue<Data> q = new LinkedList<>();
	static int[] b = new int[100001];
	static int[] num = new int[100001];

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		me = sc.nextInt();
		you = sc.nextInt();

		bfs();
		System.out.println(cnt);
		System.out.println(num[cnt]);
	}

	public static void bfs() {
		cnt = 0;
		q.offer(new Data(me, cnt));
		Data d;
		b[me]=1;
		while (!q.isEmpty()) {
			d = q.poll();
			if (d.my == you) {
				cnt=d.cnt;
				num[d.cnt]+=1;
				continue;
			}
			int[] temp = { d.my + 1, d.my - 1, d.my * 2 };
			for (int i = 0; i < 3; i++) {
				if ((temp[i] > 100000) || (temp[i] < 0)) {
					continue;
				}
				if(b[temp[i]]!=0 && b[temp[i]]!=d.cnt+1) {
					continue;
				}
				b[temp[i]]=d.cnt+1;
				q.offer(new Data(temp[i],d.cnt+1));
			}

		}

	}

	static class Data {
		int my;
		int cnt;

		public Data(int my, int cnt) {
			super();
			this.my = my;
			this.cnt = cnt;
		}

	}
}
