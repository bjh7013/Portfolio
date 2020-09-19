/**
 * 정올 IM-TOP문제
 */
package testProject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
 
public class Top {
 
    public static void main(String[] args) throws FileNotFoundException  {
    	System.setIn(new FileInputStream("test.txt"));
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder("");
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> stacknum = new Stack<Integer>();
        int N;
 
        N = sc.nextInt();
        int a = 0;
        int b = 0; // 스택넣기
        
        stack.push(sc.nextInt());
        stacknum.push(0);
        sb.append("0 ");
 
        for (int i = 1; i < N; i++) {
            a = stack.peek();
            b = stack.push(sc.nextInt());
            stacknum.push(i);
 
             
            if (a < b) {
                stack.pop();
                stacknum.pop();
                while (true) {
                    stack.pop();
                    stacknum.pop();
                    if (stack.isEmpty()) {
                    	stack.clear();
                    	stacknum.clear();
                        stack.push(b);
                        stacknum.push(i);
                        sb.append("0 ");
                        break;
                    }
                    if (stack.elementAt(0) < b) {
                    	stack.clear();
                    	stacknum.clear();
                    	stack.push(b);
                    	stacknum.push(i);
                    	sb.append("0 ");
                    	break;
                    }
                    if (stack.peek() < b)
                        continue;
 
                    if (stack.peek() > b) {
                        
                        sb.append(stacknum.peek()+1+" ");
                        stack.push(b);
                        stacknum.push(i);
                        break;
                    }
                    sb.append(stacknum.peek()+" ");
                        break;
 
                }
                continue;
            }
             
            sb.append(i+" ");
             
 
        }
        System.out.print(sb);
       
 
    }
 
}