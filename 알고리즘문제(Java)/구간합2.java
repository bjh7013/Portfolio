package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class 구간합2 {
	static HashMap<Long,Long> m = new HashMap<>();
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		m.clear();
		for(int i=1;i<17;i++) {
			long v=pow10(0L+i);
			m.put((v-1L),h(v-1L));
		}
		for (int tc = 1; tc <= T; tc++) {
			long A = sc.nextLong();
			long B = sc.nextLong();
			
		}
	}

	private static long pow10(long l) {
		// TODO Auto-generated method stub
		return (long)(Math.pow(10, l));
	}

	private static Long h(long l) {
		long len=len(l)+1L;
		// TODO Auto-generated method stub
		return ((45L)*(len)*(1L+l))/(10L);
	}

	private static long len(long l) {
		// TODO Auto-generated method stub
		return 0L+(l+"").length()-1;
	}
	private static long e(long n) {
		// TODO Auto-generated method stub
		return n*(n+1L)/2L;
	}	
	private static long g(long n) {
		if(n<=9) {
			return e(n);
		}else {
			long v=pow10(len(n));
			return (n/v)*(n%v+1L)+f(n%v);
		}
	}	
	public static long cal(long b, long a) {
		if(a<=1) {
			return f(b);
		}else if(a==b) {
			return f(b)-f(a-1);
		}else {
			return f(b)-f(a-1);
		}
		
	}

	private static long f(long n) {
		if(m.containsKey(n)) {
			return m.get(n);
		}else if(n<=9) {
			return e(n);
		}else {
			long v=pow10(len(n));
			m.put(n, f(n-1L-n%v)+g(n));
			return m.get(n);
		}
	}

}
