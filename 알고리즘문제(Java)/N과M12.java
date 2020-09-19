package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/*
 * 백준 15666번 N과M
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
public class N과M12 {
	static int N;
	static int K;
	static PriorityQueue<Integer> pq;
	static List<Integer> list;
	static Stack<Integer> stack;
	static boolean[] check;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		pq = new PriorityQueue();
		list = new ArrayList();
		stack = new Stack();
		check = new boolean[10];
		for (int i = 0; i < N; i++) {
			int temp = sc.nextInt();
			if (!pq.contains(temp)) {
				pq.add(temp);
			}
		}
		int size = pq.size();
		for (int i = 0; i < size; i++) {
			list.add(pq.poll());
		}

		dfs(0);

	}

	static void dfs(int cnt) {
		if (cnt == K) {
			for (int i = 0; i < K; i++) {
				System.out.print(stack.get(i) + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			if (!stack.isEmpty() && stack.get(stack.size()-1)<=list.get(i)) {
				stack.push(list.get(i));
				dfs(cnt + 1);
				stack.pop();
			}else if(stack.isEmpty()) {
				stack.push(list.get(i));
				dfs(cnt + 1);
				stack.pop();
			}
		}
	}
}
