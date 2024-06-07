import java.util.*;

public class Main{
    static class Node{
        int num;
        int dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    static List<Node>[] list;
    static int max = 0;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            int num = scan.nextInt();

            int other = scan.nextInt();
            List<Node> nodes = new ArrayList<>();
            while(other != -1){
                int dist = scan.nextInt();
                nodes.add(new Node(other-1, dist));
                other = scan.nextInt();
            }

            list[num-1] = nodes;
        }
        dfs(0, 0, -1);

        System.out.println(max);
    }

    public static int dfs(int dist, int now, int prev){
        List<Node> connected = list[now];

        int maxLen = 0;
        for (Node node : connected) {
            if (node.num == prev) continue;

            int len = dfs(node.dist, node.num, now);
            max = Math.max(maxLen + len, max);
            maxLen = Math.max(maxLen, len);
        }
        return maxLen + dist;
    }
}
