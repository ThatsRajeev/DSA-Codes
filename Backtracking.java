import java.util.*;

public class Backtracking {
    public static List<String> saveBoard(String output[][]) {
        List<String> myList = new ArrayList<>();
        String str = "";

        for(int i=0; i<output.length; i++) {
            str = "";
            for(int j=0; j<output[0].length; j++) {
                if(output[i][j]=="Q") {
                    str+=("Q");
                } else {
                    str+=(".");
                }
            }
            myList.add(str);
            str = "";
        }
        return myList;
    }
    public static boolean isSafe(int nrows, int row, int col, String output[][]) {

        //horizontal check
        for(int i=0; i<nrows; i++) {
            if(output[row][i].equals("Q")) {
                return false;
            }
        }

        //vertical check
        for(int i=0; i<nrows; i++) {
            if(output[i][col].equals("Q")) {
                return false;
            }
        }

        //upper left check
        int j = col;
        for(int i=row; i>=0 && j>=0; i--, j--) {
            if(output[i][j].equals("Q")) {
                return false;
            }
        }

        //lower right check
        j = col;
        for(int i=row; i<nrows && j<nrows; i++, j++) {
            if(output[i][j].equals("Q")) {
                return false;
            }
        }

        //upper right check
        j = col;
        for(int i=row; i>=0 && j<nrows; i--, j++) {
            if(output[i][j].equals("Q")) {
                return false;
            }
        }

        //lower left check
        j = col;
        for(int i=row; i<nrows && j>=0; i++, j--) {
            if(output[i][j].equals("Q")) {
                return false;
            }
        }
        return true;
    }

    public static void helper(int n, int row, String output[][], List<List<String>> finalList) {
        if(row==n) {
            finalList.add(saveBoard(output));
            return;
        }
        for(int i=0; i<n; i++) {
            if (isSafe(n, row, i, output)) {
                output[row][i] = "Q";
                helper(n, row+1, output, finalList);
                output[row][i] = "";
            }
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> finalList = new ArrayList<>();
        String[][] output = new String[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                output[i][j] = "";
            }
        }
        helper(n, 0, output, finalList);
        return finalList;
    }

    public static void main(String args[]) {
        int n = 4;
        System.out.println(solveNQueens(n));

    }
}
