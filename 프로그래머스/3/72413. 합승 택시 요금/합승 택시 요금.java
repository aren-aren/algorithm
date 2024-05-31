import java.util.*;

class Solution {
    List<List<Integer[]>> list;
    int n;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        this.n = n;
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] fare : fares) {
            list.get(fare[0]).add(new Integer[]{fare[1], fare[2]});
            list.get(fare[1]).add(new Integer[]{fare[0], fare[2]});
        }

        int[] startCosts = dijkstra(s);
        int[] aCosts = dijkstra(a);
        int[] bCosts = dijkstra(b);

        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, startCosts[i] + aCosts[i] + bCosts[i]);
        }

        return answer;
    }

    public int[] dijkstra(int start){
        int[] costs = new int[n + 1];
        boolean[] visit = new boolean[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        Arrays.fill(visit, false);
        costs[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
        pq.add(new int[]{start, 0});

        int visitCount = 0;
        while(!pq.isEmpty()){
            int[] arr = pq.poll();
            if(visit[arr[0]]) continue;
            visit[arr[0]] = true;
            visitCount++;

            List<Integer[]> connects = list.get(arr[0]);

            for (int i = 0; i < connects.size(); i++) {
                if(costs[connects.get(i)[0]] > arr[1] + connects.get(i)[1]){
                    costs[connects.get(i)[0]] = arr[1] + connects.get(i)[1];
                    pq.add(new int[]{connects.get(i)[0], costs[connects.get(i)[0]]});
                }
            }

            if(visitCount == n){
                break;
            }
        }

        return costs;
    }
}
