import java.util.*;

class Solution {

    class Puzzle{
        int [][][] shapes;
        boolean isUse;

        private int[][] rotate(int[][] shape){
            int[][] rotateShape = new int[shape[0].length][shape.length];

            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[0].length; j++) {
                    rotateShape[j][shape.length - i - 1] = shape[i][j];
                }
            }

            return rotateShape;
        }

        Puzzle(int[][] shape) {
            shapes = new int[4][][];
            shapes[0] = shape;
            isUse = false;
            for (int i = 1; i < 4; i++) {
                shapes[i] = rotate(shapes[i-1]);
            }
        }

        public int count(){
            int count = 0;

            for (int[] ints : shapes[0]) {
                for (int anInt : ints) {
                    count += anInt;
                }
            }

            return count;
        }

        @Override
        public boolean equals(Object o) {
            if(isUse) return false;

            int[][] puzzle = (int[][]) o;

            if(puzzle.length != shapes[0].length && puzzle.length != shapes[1].length) return false;

            for (int i = 0; i < 4; i++) {
                if(Objects.deepEquals(puzzle, shapes[i])) {
                    isUse = true;
                    return true;
                }
            }

            return false;
        }
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        List<Puzzle> list = new ArrayList<>();

        boolean[][] gbVisited = new boolean[game_board.length][game_board[0].length];
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[0].length; j++) {
                if( game_board[i][j] == 1 || gbVisited[i][j] ) continue;
                int[][] shape = bfs(i, j, game_board, gbVisited, 0);
                list.add(new Puzzle(shape));
            }
        }

        boolean[][] tVisited = new boolean[table.length][table[0].length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if( table[i][j] == 0 || tVisited[i][j] ) continue;
                int[][] shape = bfs(i, j, table, tVisited, 1);

                for (Puzzle puzzle : list) {
                    if(puzzle.equals(shape)){
                        answer += puzzle.count();
                        break;
                    }
                }
            }
        }

        return answer;
    }

    public int[][] bfs(int i, int j, int[][] field, boolean[][] visited, int target){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i,j});

        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};

        int startX = i;
        int startY = j;
        int endX = i;
        int endY = j;

        List<int[]> shape = new ArrayList<>();

        while(!q.isEmpty()){
            int[] point = q.poll();
            int x = point[0];
            int y = point[1];
            if(visited[x][y]) continue;
            visited[x][y] = true;

            startX = Math.min(startX, x);
            startY = Math.min(startY, y);
            endX = Math.max(endX, x);
            endY = Math.max(endY, y);

            shape.add(new int[]{x,y});

            for (int k = 0; k < 4; k++) {
                if(x + dx[k] < 0 || x + dx[k] >= field.length || y + dy[k] < 0 || y + dy[k] >= field[0].length) continue;
                if(field[x + dx[k]][y + dy[k]] == target) {
                    q.add(new int[]{x + dx[k], y + dy[k]});
                }
            }
        }

        int[][] result = new int[endX - startX + 1][endY - startY + 1];

        for (int[] p : shape) {
            result[p[0] - startX][p[1] - startY] = 1;
        }

        return result;
    }
}
