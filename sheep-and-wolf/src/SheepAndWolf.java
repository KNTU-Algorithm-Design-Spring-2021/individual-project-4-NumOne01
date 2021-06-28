import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class SheepAndWolf {
    public static void main(String[] args) {
        System.out.print("Enter number of vertices in graph : ");

        Scanner in = new Scanner(System.in);

        int n = in.nextInt(), s, t;
        int[][] adj = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(adj[i], 0);
        }

        System.out.print("Enter number of edges in graph : ");

        int m = in.nextInt();

        System.out.println("Enter edges :");

        for (int i = 0; i < m; i++) {
            adj[in.nextInt()][in.nextInt()] = 1;
        }

        System.out.println("Enter source node");
        s = in.nextInt();
        System.out.println("Enter target node");
        t = in.nextInt();

        MaxFlow maxFlow = new MaxFlow(n);
        LinkedList<Integer>[] paths = maxFlow.fordFulkerson(adj, s, t);
        if (paths != null) {
            System.out.println("Safe paths are : ");
            printPath(paths[0], t);
            printPath(paths[1], t);
        } else {
            System.out.println("There is no safe path !");
        }
    }

    private static void printPath(LinkedList<Integer> path, int target) {
        for (int vertex : path) {
            System.out.print(vertex + " ");
        }
        System.out.println(target);
    }
}
