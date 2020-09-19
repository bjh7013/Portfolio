package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Calculator1 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder("");
		StringBuilder sr = new StringBuilder("");
		Stack<Character> stack = new Stack<Character>();
		Stack<Integer> stacknum = new Stack<Integer>();
		int TC = 10;
		int N = 0;
		StringBuilder temp = new StringBuilder("");
		char a; // value
		int b; // num
		int result = 0;
		int cnt=0;

		for (int i = 0; i < TC; i++) {
			sb = new StringBuilder("");
			sr = new StringBuilder("");
			temp = new StringBuilder("");
			cnt=0;
			
			stack.clear();
			stacknum.clear();
			N = sc.nextInt();
			temp.append(sc.next());
			stacknum.push(0);
			stack.push((char) 0);

			for (int j = 0; j < N; j++) {
				stack.push(temp.charAt(j));
				b = temp.charAt(j);
				if((b=='(')||(b==')')) cnt++;
				for (int k = 0; k < N; k++) {
					if (b == ')') {
						if (stacknum.peek() == 3) {
							stack.pop();
							stack.pop();
							stacknum.pop();
							break;
						}
						stack.pop();
						sb.append(stack.pop());
						stack.push(temp.charAt(j));
						stacknum.pop();
						continue;
					}
					if (b == '(') {
						stacknum.push(3);
						break;
					}
					if ((b == '+') || (b == '-')) {
						if ((stacknum.peek() == 1) || (stacknum.peek() == 2)) {
							stack.pop();
							sb.append(stack.pop());
							stack.push(temp.charAt(j));
							stacknum.pop();
							continue;
						}

						stacknum.push(1);
						break;
					}
					if ((b == '*') || (b == '/')) {
						if ((stacknum.peek() == 2)) {
							stack.pop();
							sb.append(stack.pop());
							stack.push(temp.charAt(j));
							stacknum.pop();
							continue;
						}

						stacknum.push(2);
						break;
					}
					sb.append(temp.charAt(j));
					stack.pop();

					break;
				}
				if (j == (N - 1)) {
					while (!stack.isEmpty()) {
						sb.append(stack.pop());
						stacknum.clear();
					}
				}
			}

			// System.out.println(sb);

			for (int j = 0; j < N-cnt; j++) {

				if (sb.charAt(j) == '+') {
					result = stacknum.pop() + stacknum.pop();
					stacknum.push(result);
					//System.out.print(result + " ");
					continue;
				}
				if (sb.charAt(j) == '*') {
					result = stacknum.pop() * stacknum.pop();
					stacknum.push(result);
					continue;
				}
				stacknum.push((int) sb.charAt(j) - 48);
			}
			System.out.printf("#%d %d\n", (i + 1), result);

		}
	}
}
