import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int s = scan.nextInt();

        int[] arr = new int[n];

        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        int begin = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();

            sum += arr[i];
            while(sum >= s){
                minLen = Math.min(minLen, i - begin + 1);
                sum -= arr[begin++];
            }
        }
        System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
    }
}
