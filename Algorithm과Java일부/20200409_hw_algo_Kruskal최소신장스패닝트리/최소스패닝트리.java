package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class 최소스패닝트리 {
	static int[] root;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		root = new int[V+1];
		long result=0;
		setRoot(V);
		List<Kruskal> list = new ArrayList<>();
		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int val = sc.nextInt();
			list.add(new Kruskal(a, b, val));
		}
		Collections.sort(list, new Comparator<Kruskal>() {

			@Override
			public int compare(Kruskal o1, Kruskal o2) {
				if (o1.val > o2.val) {
					return 1;
				} else if (o1.val < o2.val) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		int cnt=0;
		for (int i = 0; i < E; i++) {
			Kruskal k = list.get(i);
			int a=find(k.a);
			int b=find(k.b);
			if(a==b)
				continue;
			union(a,b);
			result+=k.val;
			cnt++;
			if(cnt==V)
				break;
		}
		System.out.println(result);

	}

	static class Kruskal {
		int a, b;
		long val;

		public Kruskal(int a, int b, long val) {
			super();
			this.a = a;
			this.b = b;
			this.val = val;
		}
	}

	static void setRoot(int n) {
		for (int i = 0; i < n+1; i++) {
			root[i] = i;
		}
	}

	static void union(int x, int y) {
		int getx = find(x);
		int gety = find(y);
		if(getx!=gety) {
			root[getx]=gety;
		}
	}
	static int find(int x) {
		if(x==root[x])
			return x;
		else
			return root[x]=find(root[x]);
	}
}
