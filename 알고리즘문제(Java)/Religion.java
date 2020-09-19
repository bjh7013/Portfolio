package testProject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
public class Religion {
    static int[] root;
    static int[] roottemp;
    static int templ = 0;
    static int iRoot;
    static int jRoot;
 
    public static void main(String[] args) throws FileNotFoundException  {
    	System.setIn(new FileInputStream("test.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        root = new int[n + 1];
 
        int temp = 0;
        int[] tempi = new int[m + 2];
        int[] tempj = new int[m + 2];
        roottemp = new int[n + 1];
 
        for (int i = 1; i < m + 1; i++) {
        	union(sc.nextInt(),sc.nextInt());
        }
 
        int cnt = 0;
        for (int i = 1; i < n + 1; i++) {
            if (root[i] == 0)
                cnt++;
        }
        System.out.println(cnt);
    }
 
    static int find(int n) {
        if (root[n] == 0) {
            return n;
        }
 
        return root[n]=find(root[n]);
    }

 
    private static void union(int i, int j) {
        iRoot = find(i);
        jRoot = find(j);
        if (iRoot != jRoot) {
            root[jRoot] = iRoot;
        }
    }
 
}