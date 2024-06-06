import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        int[][] rgb = new int[n][3];

        rgb[0][0] = scan.nextInt();
        rgb[0][1] = scan.nextInt();
        rgb[0][2] = scan.nextInt();

        for (int i = 1; i < n; i++) {
            int r = scan.nextInt();
            int g = scan.nextInt();
            int b = scan.nextInt();

            rgb[i][0] = Math.min(rgb[i-1][1], rgb[i-1][2]) + r;
            rgb[i][1] = Math.min(rgb[i-1][0], rgb[i-1][2]) + g;
            rgb[i][2] = Math.min(rgb[i-1][0], rgb[i-1][1]) + b;
        }

        System.out.println(Math.min(Math.min(rgb[n-1][0], rgb[n-1][1]), rgb[n-1][2]));
    }
}
