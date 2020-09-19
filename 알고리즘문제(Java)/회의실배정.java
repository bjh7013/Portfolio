package testProject;

/*
 * 백준 1931번 회의실배정
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class 회의실배정 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		List<Room> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new Room(sc.nextInt(),sc.nextInt()));
		}
		
		Collections.sort(list, new Comparator<Room>() {
			@Override
			public int compare(Room o1, Room o2) {
				if(o1.end>o2.end) {
					return 1;
				}else if(o1.end<o2.end){
					return -1;					
				}else {
					if(o1.start>o2.start) {
						return 1;
					}else {
						return -1;
					}
				}
			}
		});
//		for (int i = 0; i < N; i++) {
//			System.out.print(list.get(i).start + " ");
//			System.out.println(list.get(i).end);
//		}
		int count=1;
		int temp=list.get(0).end;
		for (int i = 1; i < N; i++) {
			if(temp<=list.get(i).start) {
				count++;
				temp=list.get(i).end;
			}
		}
		System.out.println(count);
	}

	static class Room {
		int start, end;

		public Room() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Room(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}



	}
}
