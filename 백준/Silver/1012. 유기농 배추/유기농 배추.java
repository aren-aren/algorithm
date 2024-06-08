import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();

        for (int i = 0; i < t; i++) {
            int m = scan.nextInt();
            int n = scan.nextInt();

            int[][] field = new int[n][m];
            int k = scan.nextInt();

            for (int j = 0; j < k; j++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                field[y][x] = 1;
            }

            int count = 0;
            for (int y = 0; y < field.length ; y++) {
                for (int x = 0; x < field[0].length; x++) {
                    if (field[y][x] == 0) continue;
                    bfs(field, y, x);
                    count++;
                }
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

            for (int i = 0; i < 4; i++) {
                int nx = point[1] + dx[i];
                int ny = point[0] + dy[i];

                if(nx < 0 || nx >= field[0].length || ny < 0 || ny >= field.length) continue;
                if(field[ny][nx] == 0) continue;

                field[ny][nx] = 0;
                q.add(new int[]{ny, nx});
            }
        }
    }
}
