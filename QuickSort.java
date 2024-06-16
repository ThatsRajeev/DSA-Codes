public class QuickSort {
    public static int partition (int arr[], int low, int high) {
        int pivot = arr[high];
        int k = low - 1;

        for(int i=low; i<high; i++) {
            if(arr[i]< pivot) {
                k++;
                int temp = arr[k];
                arr[k] = arr[i];
                arr[i] = temp;
            }
        }
        k++;
        int temp = arr[k];
        arr[k] = pivot;
        arr[high] = temp;
        return k;
    }
    public static void rec(int arr[], int low, int high) {
        if(low < high) {
            int pidx = partition(arr, low, high);

            rec(arr, low, pidx-1);
            rec(arr, pidx+1, high);
        }
    }
    public static void main(String args[]) {
        int arr[] = {6, 3, 9, 5, 2, 8};
        rec(arr, 0 , arr.length-1);

        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
