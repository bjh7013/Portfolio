package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 구간합 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			long A = sc.nextLong();
			long B = sc.nextLong();
			long[] ans = new long[10];
			long point=1;
			while(A<=B) {
				while(B%10!=9 && A<=B) {
					cal(B,ans,point);
					B--;
				}
				if(B<A) {
					break;
				}
				while(A%10!=0&&A<=B) {
					cal(A,ans,point);
					A++;
				}
				A/=10;
				B/=10;
				for(int i=0;i<10;i++) {
					ans[i]+=(B-A+1)*point;
				}
				point*=10;
			}
			long sum=0;
			for(int i=0;i<10;i++) {
				sum+=(ans[i]*i);
			}
			
			System.out.println("#" + tc + " " + sum);

		}
	}
	private static void cal(long x, long[] ans, long point) {
		while(x>0) {
			String s=String.valueOf(x);
			int xx = s.charAt(s.length()-1)-'0'; //49-48
			ans[xx]+=point;
			x/=10;
		}
	}
}
