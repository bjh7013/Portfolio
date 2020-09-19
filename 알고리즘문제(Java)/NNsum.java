package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NNsum {
	static long[] val;
	static long[] result = new long[1000001];
	static long[] tempresult = new long[1000001];
	static long pow=0;
	static long factor=0;
	static long dis=1000000007;
	static int max=1;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		long start = System.currentTimeMillis();
		for (int tc = 1; tc <= T; tc++) {
			int temp=1;
			int num = sc.nextInt();
			val = new long[num+1];
			if(max>num) {
				temp=num;
			}
			if(max<=num) {
				temp=max;
			}
			
			for(int i=temp;i<=num;i++) {

				result[i]=(result[i-1]+nn(i))%1000000007;
			}
			System.out.println("#"+tc+" "+result[num]);
			
			if(num>max) {
				max=num;
			}
		}
		System.out.println(System.currentTimeMillis()-start);
	}
	
	
	
	static public long nn(int num) {
		pow=1;
		factor=num;
		while(num!=0) {
			if((num & 1) == 1) {
				pow*=factor;
			}
			num>>=1;
			factor*=factor;
			factor%=dis;
			pow%=dis;
		}
		return tempresult[num]=pow;
	}
}
