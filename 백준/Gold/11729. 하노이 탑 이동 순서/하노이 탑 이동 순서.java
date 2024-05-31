import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        System.out.println((1<<n) -1);
        StringBuilder sb = new StringBuilder();
        move(1,3,n, sb);
        System.out.println(sb);
    }

    public static void move(int from, int to, int n, StringBuilder sb){
        if(n == 0) return;

        int other = 6 - from - to;

        move(from, other, n-1, sb);
        sb.append(from).append(" ").append(to).append("\n");
        move(other, to, n-1, sb);
    }
}
