package testProject;

/*
 * 백준 1927번 최소 힙
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 최소힙 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] map = new int[(N + 2) * 2];
		int index = 1;
		for (int i = 0; i < N; i++) {
			int val = sc.nextInt();
			if (val == 0) {
				if (index == 1) {
					System.out.println("0");
				} else {
					System.out.println(map[1]);
					int tempval = map[--index];
					map[1] = map[index];
					map[index] = 0;
					int tempidx = 1;
					while (true) {
						//이게 temp*2와 temp*2 +1 중에서 더 작은것을 먼저 해야함
						if (map[tempidx * 2] > map[tempidx * 2 + 1] && map[tempidx * 2 + 1]!=0) {
							if (tempval > map[tempidx * 2 + 1] && map[tempidx * 2 + 1] != 0) {
								map[tempidx] = map[tempidx * 2 + 1];
								map[tempidx * 2 + 1] = tempval;
								tempidx = tempidx * 2 + 1;
								continue;
							}
						} 
						else {
							if (tempval > map[tempidx * 2] && map[tempidx * 2] != 0) {
								map[tempidx] = map[tempidx * 2];
								map[tempidx * 2] = tempval;
								tempidx = tempidx * 2;
								continue;
							}
						}
						break;
					}
					map[tempidx] = tempval;
				}
			} else {
				int tempidx = index;
				if (tempidx == 1) {
					map[tempidx] = val;
					index++;
					continue;
				}
				while (true) {
					if (val < map[tempidx / 2]) {
						map[tempidx] = map[tempidx / 2];
						map[tempidx/2]= val;
						tempidx /= 2;
					} else {
						map[tempidx] = val;
						break;
					}
				}
				if (tempidx == 1) {
					map[tempidx] = val;
				}
				index++;
			}
		}
	}

}
