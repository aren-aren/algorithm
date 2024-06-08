import java.util.*;

public class Main{
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();

        for (int i = 0; i < t; i++) {
            int m = scan.nextInt();
            int n = scan.nextInt();

            int[][] field = new int[n][m];
            visited = new boolean[n][m];

            int k = scan.nextInt();

            List<int[]> list = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                field[y][x] = 1;
                list.add(new int[]{y,x});
            }

            int count = 0;
            for (int[] node : list) {
                if(visited[node[0]][node[1]]) continue;

                bfs(field, node[0], node[1]);
                count++;
            }

            System.out.println(count);
        }
    }

    private static void bfs(int[][] field, int y, int x) {
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y,x});

        while(!q.isEmpty()){
            int[] point = q.poll();
            if(visited[point[0]][point[1]]) continue;

            visited[point[0]][point[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nx = point[1] + dx[i];
                int ny = point[0] + dy[i];

                if(nx < 0 || nx >= field[0].length || ny < 0 || ny >= field.length) continue;
                if(field[ny][nx] == 0) continue;

                q.add(new int[]{ny, nx});
            }
        }
    }
}
