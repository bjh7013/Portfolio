package virus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CoronaStreamTest {
	static int count = 0;

	public static void main(String[] args) {
		List<Corona> list = new ArrayList<Corona>();
		List<Integer> list2 = new ArrayList<>();
		Random r = new Random();
		int cnt = 0;
		for (int i = 0; i < 10000; i++) {
			String name = "Corona" + i;
			int level = r.nextInt(50);
			String spreadSpeed = "S" + r.nextInt(10);
			list.add(new Corona(name, level, spreadSpeed));
		}
		for (int i = 0; i < 10000; i++) {
			list2.add(list.get(i).level);
		}
		Stream<Integer> stream = list2.stream();

		Stream<Integer> streams = stream.filter( n ->  {return n>30;});
		print(streams);
		System.out.println("level이 30을 넘는 객체의 갯수 : "+count);
	}

	public static void print(Stream<?> stream) {
		stream.forEach(a -> count++);
	}

	static class Corona {
		String name;
		int level;
		String spreadSpeed;

		public Corona(String name, int level, String spreadSpeed) {
			this.name = name;
			this.level = level;
			this.spreadSpeed = spreadSpeed;
		}

		public Corona() {

		}

		public void go(int n) {
			if (this.level > n) {
				count++;
			}
		}
	}
}
