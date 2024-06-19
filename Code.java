import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Code {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine().trim());

        while (t--> 0) {
            String[] query = br.readLine().trim().split(" ");
            int n = Integer.parseInt(query[0]);
            int c = Integer.parseInt(query[1]);

            String[] query2 = br.readLine().trim().split(" ");
            int arr[] = new int[n];
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(query2[i]);
            }
            arr[0] += c;

            int maxi = 0;
            int idx = 0;
            for(int i=n-1; i>=0; i--) {
                if(arr[i] >= maxi) {
                    maxi = arr[i];
                    idx = i;
                }
            }
            long prefixSum = 0;
            int ans[] = new int[n];
            ans[idx] = 0;

            for(int i=idx+1; i<n; i++) {
                ans[i] = i;
            }

            for(int i=0; i<idx; i++) {
                prefixSum += arr[i];
                if(prefixSum >= maxi) {
                    ans[i] = i;
                } else {
                    ans[i] = i+1;
                }
            }

            for(int i=0; i<n; i++) {
                bw.write(ans[i] + " ");
            }

            bw.write("\n");
        }
        
        bw.flush();
        br.close();
        bw.close();
    }
}
