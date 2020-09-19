package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Commonjosang {
	static Stack<Integer> stack = new Stack<>();
	static Stack<Integer> stackone = new Stack<>();
	static Stack<Integer> stacktwo = new Stack<>();
	static int n, k;
	static int dp[][], w[], v[]; // dp배열과 무게, 가치배열
	static boolean[] b;
	static boolean bb;
	static int map[][];
	static int V;
	static int E;
	static int one;
	static int two;
	static int tempcnt;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= 1; tc++) {
			V = sc.nextInt(); // 정점
			E = sc.nextInt(); // 간선
			one = sc.nextInt();
			two = sc.nextInt();
			Tree tree = new Tree(1);
		}
	}

	public static class Tree {
		int data;
		Tree left;
		Tree right;

		public Tree(int data) {
			this.data = data;
			left = null;
			right = null;
		}
		
		public void rightSubtree(Tree child) {
			if(this.right!=null) {
				this.right=null;
			}
			this.right=child;
		}
		
		public void leftSubtree(Tree child) {
			if(this.left!=null) {
				this.left=null;
			}
			this.left=child;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Tree getLeft() {
			return left;
		}

		public void setLeft(Tree left) {
			this.left = left;
		}

		public Tree getRight() {
			return right;
		}

		public void setRight(Tree right) {
			this.right = right;
		}
	}

}