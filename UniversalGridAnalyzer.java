import java.util.Scanner;
import java.util.Arrays;
public class UniversalGridAnalyzer {
    // here is the main method which is also the menu driven system
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // we create the array (grid) 
        System.out.print("enter the length of ur array (minimum 6)");
        int length = scanner.nextInt();
        // we make sure the user gives dimensions greater than 6x6 
        if(length < 6){
            System.out.println("error length less than 6");
        }
        System.out.print("enter the width of ur array (minimum 6)");
        int width = scanner.nextInt();
         if(width < 6){
            System.out.println("error width less than 6");
        }
        int[][] array = new int[length][width];

        for (int i = 0; i < length; i++){
            for (int j = 0; j < width ;j++){
                array[i][j] = (int) (Math.random() * 9) + 1;
            }
        }

        System.out.println("succesfully made ur array with width:" + width + ", and length:" + length);
        System.out.println("please choose the algorithim number you would like to choose. 1. Row & Column Aggregation 2. Global Max & Min with Position 3. Frequency Counter 4.Row & Column Comparison 5.Pattern Detection (Increasing Sequence) 6.Grid Transformation 7.Subgrid Processing 8.Boundary & Diagonal Processing 9.Validation Algorithm 0. Exit");
        int choice = scanner.nextInt();

        // we have a if statement to make sure the user selects a algorthim that is part of options
        if(choice < 0 || choice > 9){
            System.out.println("error not a algorithim");
        }

        // we have our menu system which involves a series of if statements to check which algorthim the user wants to run
        if(choice == 0){
            System.out.println("process exited");
        }
        if(choice == 1){
            RowColsum(array);
        } else if(choice == 2){
            maxMin(array);
        } else if(choice == 3){
            System.out.println("what is your target value?");
            int target = scanner.nextInt();
            System.out.println("what is your threshold?");
            int threshold = scanner.nextInt();
            count(array,target, threshold);
        } else if(choice == 4){
            compareRowsnCol(array);
        } else if(choice == 5){
            if(increasingRow(array)> -1){
                System.out.println("the row that is only increasing is: " + increasingRow(array));
            } else {
                System.out.println("there are no rows that are only increasing");
            }
        } else if(choice == 6){
            System.out.println("choose a transformation 1. rotate the row by one 2. swap two rows 3. reverese a column");
            int choicetransformation = scanner.nextInt();
            if(choicetransformation < 1 || choicetransformation>3){
                System.out.println("error not a transformation");
            }

            if (choicetransformation == 1){
            System.out.println("what row would u like to rotate");
            int row = scanner.nextInt();
            rotate(array, row);
            } else if(choicetransformation == 2 ){
            System.out.println("what row would u like to swap from");
            int row1 = scanner.nextInt();
            System.out.println("what the second row u would like to swap to");
            int row2 = scanner.nextInt();
            swap(array, row1, row2);
            } else if (choicetransformation ==3 ){
            System.out.println("what column would u like to reverse");
            int col = scanner.nextInt();
            reverse(array, col);
            }
        } else if(choice == 7){
            System.out.println("what is the starting row of ur subgrid");
            int rowStart = scanner.nextInt();
            System.out.println("what is the ending row of ur subgrid");
            int rowEnd = scanner.nextInt();
            System.out.println("what is the starting column of ur subgrid");
            int colStart = scanner.nextInt();
            System.out.println("what is the ending column of ur subgrid");
            int colEnd = scanner.nextInt();
            subgrid(array, rowStart, rowEnd, colStart, colEnd);
        } else if(choice == 8){
            
        } else if(choice == 9){
            if(duplicateChecker(array)){
                System.out.println("duplicates found");
            } else {
                System.out.println("no duplicates found");
            }
        }
    }

// here this algorthim finds the sum of each row and each column and stores the result in a 1d array
public static void RowColsum(int[][] array){
    int[] rowsum = new int[array.length];
    int[] colsum = new int[array[0].length];
    
    for(int i = 0; i < array.length; i++){
        for(int j = 0; j < array[i].length; j++){
            rowsum[i] += array[i][j];
        }
    }
    
    for(int k = 0; k < array[0].length; k++){
        for(int l = 0; l < array.length; l++){
            colsum[k] += array[l][k];
        }
    }
    System.out.println("the sum of all rows " + Arrays.toString(rowsum) + " the sum of all columns" + Arrays.toString(colsum));
}
// this one finds the highest value and the lowest value in the array and its position
public static void maxMin (int[][] array){
    int max = array[0][0];
    int min = array[0][0];
    int maxRowI = 0;
    int maxColI = 0;
    int minRowI = 0;
    int minColI = 0;
 
     for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array[i].length; j++) {
            
            if (array[i][j] > max) {
                max = array[i][j];
                maxRowI = i;
                maxColI = j;
            }
            if (array[i][j] < min) {
                min = array[i][j];
                minRowI = i;
                minColI = j;
            }
        }
    }
    System.out.println("the max value is: " + max + " at location " + maxRowI + "," + maxColI + " the min value is: " + min + " at location " + minRowI + "," + minColI);
}

// this method count the number of times a target value is found and the number of values greater than a threshold set by the user
public static void count(int[][] array, int target, int threshold){

    int countt = 0;
    int countThreshold = 0;

    for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array[i].length; j++) {
            if (array[i][j] == target) {
                countt++;
            }
            if (array[i][j] > threshold){
                countThreshold++;
            }
        }
    }

System.out.println("the number of times target appears is " + countt + " the number of values greater than you threshold are " + countThreshold);
}
// here we find the row with the highest sum and the column with the lowest sum in the array
public static void compareRowsnCol(int[][] array){

    int maxRowS = Integer.MIN_VALUE;
    int minColS = Integer.MAX_VALUE;
    int rowIndex = -1;
    int colIndex = -1;

    for (int i = 0; i < array.length; i++) {
        int sumrow = 0;
        for (int j = 0; j < array[i].length; j++) {
            sumrow += array[i][j];
        }
        if (sumrow > maxRowS) {
            maxRowS = sumrow;
            rowIndex = i;
        }
    }
  
    for (int k = 0; k < array[0].length; k++) {
        int sumcol = 0;
        for (int l = 0; l < array.length; l++) {
            sumcol += array[l][k];
        }

        if (sumcol < minColS) {
            minColS = sumcol;
            colIndex = k;
        }
    }

System.out.println("the row with the highest sum: " + rowIndex + " the column with the lowest sum: " + colIndex);
}

// here we check if there is a row where the values in it are strictly increasing 
public static int increasingRow(int[][] array){
    for(int i = 0; i < array.length; i++){
        boolean increasing = true;
        for (int j = 0; j < array[i].length - 1; j++){
           
            if(array[i][j + 1] <= array[i][j]){
                increasing = false;
            }
        }
        if (increasing){
        return i; }
    } 
        return -1;
    }
    // this transformation rotates a specific row right by 1
public static void rotate (int[][] array, int row){
    int last = array[row][array[row].length - 1];
    
    // we shift eveyrthing over to the right 
    for(int i = array[row].length - 1; i > 0; i--){
        array[row][i] = array[row][i - 1];
    }
    array[row][0] = last;
  
    System.out.println("you have succesfully rotated the row " + row);
}
// this one swaps two entire rows
public static void swap(int[][] array, int row1, int row2){
    for(int i = 0; i < array[row1].length; i++){
        int temp = array[row1][i];
        array[row1][i] = array[row2][i];
        array[row2][i] = temp;
    }

    System.out.println("you have succesfully swapped the rows " + row1 + " with " + row2);
}
// this transformation reverses the order of numbers in a column
public static void reverse (int[][] array, int col){
    int topindex = 0;
    int bottomindex = array.length - 1; 
    // we loop halfway through because we swap from both sides moving to the middle of the column
    for (int i = topindex; i < array.length/2; i++){
        int temp = array[topindex][col];
        array[topindex][col] = array[bottomindex][col];
        array[bottomindex][col] = temp;
        
        topindex++;
        bottomindex--;
    }

    System.out.println( "you have reveresed the column " + col);
}
// here we proccess a subgrid which is specified by the user and we will find the sum and largest value in this subgrid
public static void subgrid (int[][] array, int rowStart, int rowEnd, int colStart, int colEnd){ 
    int sum = 0;
    int max = array[rowStart][colStart];

    // we loop through the subgrid starting from the points they specify and ending at the end points they specify 
    for (int i = rowStart; i<= rowEnd; i++){
        for (int j = colStart; j <= colEnd; j++){
            sum+= array[i][j];
            if (array[i][j] > max){
                max = array[i][j];
            }
        }
    }

    System.out.println("the sum of the subgrid is: " + sum + " the greatest value in the subgrid is:" + max);
}
// this algorithim prints the boundary elements, the main diagonal and the secondary diagonal
public static void boundaryAndDiagonals (int[][] array){
    int row = array.length; 
    int col = array[0].length;

    System.out.println("boundary elements:");
    for (int i = 0; i < row; i++){
        for (int j = 0; j < col; j++){
            // we use if statements to check if its on the edge of the array/grid
            if (i==0 || i==row - 1 || j==0 || j==col - 1){
                System.out.print(array[i][j] + " ");
            } 
            else {
                System.out.println(" ");
            }
        }
        System.out.println();
    }

    System.out.println("main diagonal:");
    for (int k = 0; k < row; k++){
        System.out.print(array[k][k] + " ");
    }
        System.out.println();

    System.out.print("secondary diagonal:");
    int currentrow = 0;
    int currentcol = col - 1;

    for (int l = 0; l < row; l++){
        // we print current position and update row so it moves down and column left since we are moving diagonally
        System.out.print(array[currentrow][currentcol] + " ");
        currentrow++;
        currentcol--;
    }
    System.out.println(" ");
}

// here we check if any row has the same number repeated atleast twice
public static boolean duplicateChecker(int[][] array){
    for(int i = 0; i < array.length; i++){
        for (int j = 0; j < array[i].length; j++){
            for (int k = j + 1; k < array[i].length; k++){
                if (array[i][j] == array[i][k]){
                    return true;
                }
            } 
        }
    }
        return false;
    }
}