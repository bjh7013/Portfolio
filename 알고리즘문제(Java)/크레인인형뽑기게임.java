package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class 크레인인형뽑기게임 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int[][] board = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		int[] moves = new int[8];
		for (int i = 0; i < 8; i++) {
			moves[i] = sc.nextInt();
		}

//		Stack<Integer> stack = new Stack<>();
		LinkedList<Integer> list = new LinkedList<>();

		int count = 0;
		for (int i = 0; i < moves.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[j][moves[i] - 1] > 0) {
					list.add(board[j][moves[i] - 1]);
					board[j][moves[i] - 1] = 0;
					break;
				}
			}

			if (list.size() > 1) {
				All: while (true) {
					for (int j = 0; j < list.size() - 1; j++) {
						if (list.get(j) == list.get(j + 1)) {
							list.remove(j);
							list.remove(j);
							count += 2;
							continue All;
						}
					}
					break;
				}
			}
		}
		System.out.println(count);
	}
}
