package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 하나로프림 {
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			//각 정점별로 인접리스트 참조변수
			long[][] adj = new long[N][N];
			 int[][] arr = new int[N][2];
	         for (int i = 0; i < N; i++)
	             arr[i][0] = sc.nextInt();
	         for (int i = 0; i < N; i++)
	             arr[i][1] = sc.nextInt();
	         double E = sc.nextDouble();
			//입력되어지는 간선 배열을 인접리스트에 저장
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					long c = distance(arr[i][0], arr[j][0], arr[i][1], arr[j][1]);
					adj[i][j] = c;
					adj[j][i] = c;
				}
			}
			boolean[] visited = new boolean[N];
			long[] dist = new long[N];
			Arrays.fill(dist, Long.MAX_VALUE);
			long result = 0, min = 0;
			dist[0] = 0;
			int index;
			//시작정점을 뺀 나머지 정점 수 만큼 반복
			for(int i = 0; i < N-1; i++) {
				min = Long.MAX_VALUE;
				index = -1;
				//아직 안고른 친구중에 젤 거리값이 작은 친구
				for(int j = 0; j < N; j++) {
					if( !visited[j] && dist[j] < min ) {
						min = dist[j];
						index = j;
					}
				}
				for(int j = 0; j <N; j++) {
					if( !visited[j] && adj[index][j]!=0 && dist[j] > adj[index][j] ) {
						dist[j] = adj[index][j];
					}
				}
				visited[index] = true;
			}
			for(int i = 0; i < N; i++)
				result += dist[i];
			System.out.println("#" + tc + " " + Math.round(result*E));
		}
	}
	static long distance(int x1, int x2, int y1, int y2) {
        long d = (long)((Math.pow(x1-x2,2) + Math.pow(y1-y2,2)));
        return d;
    }
}

























