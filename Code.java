import java.util.Scanner;

public class Code {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // Number of test cases

        while (t-- > 0) {
            int n = sc.nextInt(); // Number of buns
            int a = sc.nextInt(); // Regular price of a bun
            int b = sc.nextInt(); // Price of the first modified bun

            int maxK = Math.min(n, b);
            long maxProfit = 0;

            // Function to calculate profit for given k
            for (int k : new int[]{0, maxK, maxK / 2}) {
                if (k > maxK) continue;
                long modifiedProfit = (long) k * b - (long) k * (k + 1) / 2;
                long regularProfit = (long) (n - k) * a;
                maxProfit = Math.max(maxProfit, modifiedProfit + regularProfit);
            }

            System.out.println(maxProfit);
        }
        sc.close();
    }
}
