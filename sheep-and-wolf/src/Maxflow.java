import java.lang.*;
import java.util.Collections;
import java.util.LinkedList;

class MaxFlow {
    int V; // Number of vertices in graph

    MaxFlow(int V) {
        this.V = V;
    }

    /**
     * Returns true if there is a path from source 's' to
     * sink 't' in residual graph. Also fills path[] to
     * store the path
     */
    private boolean bfs(int[][] residualGraph, int s, int t, int[] path) {
        // Create a visited array and mark all vertices as
        // not visited
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue
                = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        path[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v]
                        && residualGraph[u][v] > 0) {
                    // If we find a connection to the sink
                    // node, then there is no point in BFS
                    // anymore We just have to set its path
                    // and can return true
                    if (v == t) {
                        path[v] = u;
                        return true;
                    }
                    queue.add(v);
                    path[v] = u;
                    visited[v] = true;
                }
            }
        }

        // We didn't reach sink in BFS starting from source,
        // so return false
        return false;
    }

    /**
     * Returns two disjoint path from s to t in the given
     * graph
     */
    public LinkedList<Integer>[] fordFulkerson(int[][] graph, int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual
        // graph with given capacities in the original graph
        // as residual capacities in residual graph

        // Residual graph where residualGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If residualGraph[i][j] is 0, then there is
        // not)
        int[][] residualGraph = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                residualGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int[] path = new int[V];

        LinkedList<Integer>[] pathList = new LinkedList[2];

        int max_flow = 0, i = 0;

        while (bfs(residualGraph, s, t, path)) {
            // Find minimum residual capacity of the edges
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = path[v]) {
                u = path[v];
                path_flow
                        = Math.min(path_flow, residualGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            LinkedList<Integer> foundedPath = new LinkedList<>();

            for (v = t; v != s; v = path[v]) {
                u = path[v];
                foundedPath.add(u);
                residualGraph[u][v] -= path_flow;
                residualGraph[v][u] += path_flow;
            }

            Collections.reverse(foundedPath);

            pathList[i] = foundedPath;

            // Add path flow to overall flow
            max_flow += path_flow;

            if(max_flow == 2) {
                return pathList;
            }
            i++;
        }

        return null;
    }
}
