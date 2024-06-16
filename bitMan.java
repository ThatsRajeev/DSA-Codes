import java.util.*;

public class bitMan {

    public static String decimalToBinary(int num) {
        StringBuilder binary = new StringBuilder();

        if(num==0) {
            binary.append("0");
        }
        else{
            int n=num;
            while(n!=0) {
                int remainder = n % 2;
                n = n/2;
                binary.insert(0, remainder);
            }
        }
        return binary.toString();
    }
    public static int binaryToDecimal(int num) {
        String n=Integer.toString(num);
        int k =0;
        for(int i=n.length()-1; i>=0; i--) {
            k+= Character.getNumericValue(n.charAt(i))*Math.pow(2,i);
        }
        return k;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        int out = binaryToDecimal(n);
        System.out.println(out);
        // System.out.print("Enter the bit position to toggle: ");
        // int pos = sc.nextInt()-1;

        // boolean k= (n>0) && ((n)&(n-1))==0;
        // if(k) {
        //     System.out.println("The number is a power of 2");
        // } else {
        //     System.out.println("The number is not a power of 2");
        // }

        // int mask = 1<<pos;
        // int nen=0;
        // if ((mask & n)==0) {
        //     nen = n | mask;
        // } else {
        //     nen = ~mask & n;
        // }
        // System.out.println("The modified number is: "+ nen);

            // int bits = Integer.SIZE - Integer.numberOfLeadingZeros(n);
            // int k=0;
            // for(int i=0; i<bits; i++) {
            //     int mask = 1<<i;
            //     if((mask & n)!=0) {
            //         k=k+1;
            //     }
            // }
            // System.out.println("The number of 1's in the binary representation is: "+k);

                sc.close();
    }
}
