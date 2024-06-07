import java.util.*;

public class Main{
    static Map<Integer, Map<Integer, Integer>> map;
    static int max = 0;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = scan.nextInt();

            int other = scan.nextInt();
            Map<Integer, Integer> node = new HashMap<>();
            while(other != -1){
                int dist = scan.nextInt();
                node.put(other, dist);
                other = scan.nextInt();
            }

            map.put(num, node);
        }
        dfs(0, 1, -1);

        System.out.println(max);
    }

    public static int dfs(int dist, int now, int prev){
        Map<Integer, Integer> connected = map.get(now);

        int maxLen = 0;
        for (int key : connected.keySet()) {
            if (key == prev) continue;

            int value = connected.get(key);

            int len = dfs(value, key, now);
            max = Math.max(maxLen + len, max);
            maxLen = Math.max(maxLen, len);
        }

        return maxLen + dist;
    }
}
