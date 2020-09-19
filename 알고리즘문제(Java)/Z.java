package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 백준 1074번 Z
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
public class Z {
	static int N;
	static int R;
	static int C;
	static int count = 0;
	static boolean check = true;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		R = sc.nextInt()+1;
		C = sc.nextInt()+1;
		dfs(1,1,(int)(Math.pow(2, N)),(int)(Math.pow(2, N)),0);
		System.out.println(count);
	}
	static void dfs(int RowF,int ColF,int RowL,int ColL,int cnt) {
		if(!(RowF<=R && RowL>=R && ColF<=C && ColL>=C)&& check) {
			double tempvalue = Math.sqrt((RowL-RowF)+1);
			count+=((RowL-RowF)+1)*((RowL-RowF)+1);
			return;
		}
//		System.out.println("행"+RowF +" " +RowL);
//		System.out.println("열"+ColF +" " +ColL);
//		System.out.println(count);
		if((RowL-RowF)==1 && (ColL-ColF)==1) {
			if(RowF==R && ColF==C) {
				check=false;
				return;
			}else if(RowF==R && ColL==C) {
				count+=1;
				check=false;
				return;
			}else if(RowL==R && ColF==C) {
				count+=2;
				check=false;
				return;
			}else if(RowL==R && ColL==C) {
				count+=3;
				check=false;
				return;
			}else {
				check=false;
				return;				
			}
		}
		if(check) {dfs(RowF,ColF,(RowL+RowF-1)/2,(ColL+ColF-1)/2,cnt);} // 왼쪽위
		if(check) {dfs(RowF,(ColF+ColL-1)/2+1,(RowL+RowF-1)/2,ColL,cnt);} // 오른쪽위
		if(check) {dfs((RowF+RowL-1)/2+1,ColF,RowL,(ColL+ColF-1)/2,cnt);} // 왼쪽아래
		if(check) {dfs((RowF+RowL-1)/2+1,(ColF+ColL-1)/2+1,RowL,ColL,cnt);} // 오른쪽아래
	}
}
