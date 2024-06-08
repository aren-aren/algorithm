import java.util.*;

public class Main{

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        int[] fib = new int[43];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int k = scan.nextInt();
            if(k == 0) sb.append(1).append(" ").append(0).append("\n");
            else sb.append(fib[k-1]).append(" ").append(fib[k]).append("\n");
        }

        System.out.print(sb);
    }
}
