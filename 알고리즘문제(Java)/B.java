package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class B {
	static int cnt = 0;
	static int N;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		BST bst = new BST();

		for (int tc = 1; tc <= 1; tc++) {
			N = sc.nextInt();
			int num = 0;
			String a = "";
			for (int i = 1; i <= N; i++) {
				num = sc.nextInt();
				a = sc.nextLine();
				bst.insert(num);
			}
		}
		bst.inOrder(bst.root);
	}

	static class BST {
		Node root;

		boolean search(int data) {
			if (root == null)
				return false;

			Node temp = root;

			while (temp != null) {
				if (temp.data == data)
					return true;
				else if (temp.data > data) {
					if (temp.left == null)
						return false;

					temp = temp.left;
				} else {
					if (temp.right == null)
						return false;

					temp = temp.right;
				}
			}

			return false;
		}

		void inOrder(Node node) {
			if (node == null)
				return;

			inOrder(node.left);
			System.out.print(node.data + " ");
			inOrder(node.right);
		}

		void insert(int data) {
			if (root == null) {
				root = new Node(data);
				return;
			}

			Node temp = root;
			cnt = 0;
			while (temp != null) {
				if (temp.data < data) {
					{

						if (temp.left == null) {
							temp.left = new Node(data);
							return;
						} else if (temp.right == null) {
							temp.right = new Node(data);
							return;
						}

						if ((data / 2 == temp.data) && (data % 2 == 0)) {
							temp = temp.left;
						}

						if ((data / 2 == temp.data) && (data % 2 == 1)) {
							temp = temp.right;
						}
					}
				}
			}

		}
	}

	static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", left=" + left + ", right=" + right + "]";
		}
	}

}