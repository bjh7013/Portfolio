package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class 길찾기게임 {
	static List<Road> list;
	static List<Road>[] list2;
	static int[] dep;
	static boolean[] check;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int[][] map = new int[9][2];// x,y
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 2; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		list = new ArrayList<>();
		list2 = new ArrayList[10001];
		dep = new int[10001];
		check = new boolean[10001];
		
		for (int i = 0; i < 10001; i++) {
			list2[i] = new ArrayList<>();
		}
		for (int i = 0; i < map.length; i++) {
			list.add(new Road(map[i][0], map[i][1], i + 1));
		}
		Collections.sort(list, new Comparator<Road>() {
			@Override
			public int compare(Road o1, Road o2) {
				if (o1.y < o2.y) {
					return 1;
				} else if (o1.y > o2.y) {
					return -1;
				} else {
					if (o1.x < o2.x) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		});
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).x + " " + list.get(i).y);
		}
		int tempcnt = 0;
		int depth = 1;

		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).y != list.get(i + 1).y) {
				depth++;
			}
		}
		int finaldepth = depth;
		int deptcnt = 1;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).y != list.get(i + 1).y) {
				dep[depth] = deptcnt;
				depth--;
				deptcnt = 1;
			} else {
				deptcnt++;
			}
		}
		depth = finaldepth;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).y != list.get(tempcnt).y) {
				list2[depth].add(list.get(i));
			} else {

			}
		}
		dfs1(list.get(0).y, 1, list.get(0).x);
	}

	static void dfs1(int val, int index, int parents) {
		int cnt = 0;
		check[list.get(index)]
		for (int i = index; i > list.size(); i++) {
			if (val > list.get(i).y) {
				
				dfs1(list.get(i).y, i + dep[list.get(i).y] - cnt, list.get(i).x);
				cnt++;
			}
			if (list.get(index).y > list.get(i).y) {
				break;
			}
		}
	}

	static class Road {
		int x, y, index;

		public Road() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Road(int x, int y, int index) {
			super();
			this.x = x;
			this.y = y;
			this.index = index;
		}

	}
}
