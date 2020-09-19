package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class 하나로복습 {
	static int[] root;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			List<Kruskal> list = new ArrayList<Kruskal>();
			int N = sc.nextInt();
			root = new int[N + 1];
			double[][] x = new double[N][3];
			double[][] y = new double[N][N];
			double val=0;
			for (int i = 0; i < N; i++) {
				x[i][0] = sc.nextDouble(); // x좌표
			}
			for (int i = 0; i < N; i++) {
				x[i][1] = sc.nextDouble(); // y좌표
			}
			double E = sc.nextDouble();
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					x[i][2] = ((x[i][0] - x[j][0]) * (x[i][0] - x[j][0]) + (x[i][1] - x[j][1]) * (x[i][1] - x[j][1]))
							* E;
					list.add(new Kruskal(i,j,x[i][2]));
				}
			}

			
			for (int i = 0; i < N; i++) {
				setMake(i);
			}
			Collections.sort(list,new Comparator<Kruskal>() {

				@Override
				public int compare(Kruskal o1, Kruskal o2) {
					if(o1.sum>o2.sum) {
						return 1;
					}else if(o1.sum<o2.sum){
						return -1;
					}else {
						return 0;
					}
				}
			}); // 클래스객체로 하기
			
			int cnt=0;
			for (int i = 0; i < list.size(); i++) {
				Kruskal k = list.get(i);
				int a = find(k.x);
				int b = find(k.y);
				if(a==b)
					continue;
				union(a,b);
				val+=k.sum;
					
				if(cnt==N)
					break;
			}
			list.clear();
			System.out.println("#"+tc+" "+ (long)Math.round(val));

		}
	}
	static class Kruskal {
		int x,y;
		double sum;

		public Kruskal(int x, int y, double sum) {
			super();
			this.x = x;
			this.y = y;
			this.sum = sum;
		}

		public Kruskal() {
			super();
			// TODO Auto-generated constructor stub
		}
	}
	static void setMake(int x) {
		root[x] = x;
	}

	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if (px != py) {
			root[px] = py;
		}
	}

	static int find(int x) {
		if (root[x] == x)
			return x;
		else {
			
			return root[x] = find(root[x]);
		}
	}
}
