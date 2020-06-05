/**
 * The answer to exercise 14.
 *
 * @author Anton Pluzharov
 * @version 28.05.2019
 */
public class Ex14
{
    /**
     * Static method, returns max water volume which can be store
     * in tool defined by given array.
     * @param heights Array which defines tool to store water.
     * @return Amount of water units which given tool can store.
     */
    //Time comlexity: O(n), space complexity: O(n)
    public static int waterVolume (int [] heights)
    {
        final int ARR_LENGTH=heights.length;//length of given array stored in constant
        int capacity=0;//result
        int[] left=new int[ARR_LENGTH];//array with max height to the left of heights[i] including itself
        int[] right=new int[ARR_LENGTH];//array with max height to the right of heights[i] including itself
        //Start filling left array
        left[0]=heights[0];
        for(int i=1;i<ARR_LENGTH;i++)
        {
            left[i]=Math.max(left[i-1],heights[i]);
        }//end filling left array
        //Start filling right array
        right[ARR_LENGTH-1]=heights[ARR_LENGTH-1];
        for (int i=ARR_LENGTH-2;i>=0;i--)
        {
            right[i]=Math.max(right[i+1],heights[i]);
        }//end filling right array
        //Now calculate amount of water stored in each element
        //The amount will be: min(left[i],right[i]-heights[i])
        for (int i=0;i<ARR_LENGTH;i++)
        {
            capacity+=Math.min(left[i],right[i])-heights[i];
        }
        return capacity;
    }
    /**
     * Method returns length of the longest subsequence with even sum in given array.
     * Original time complexity: O(n^3), there is three nested "for" loops.
     * Original space complexity: O(n^2), defining variable "c" inside second nested "for"loop and variable "res" inside function "f" causes (n^2-n)=n^2.
     * Enhanced time complexity: O(n), for any array running over the array only twice.
     * Enhanced space complexity: O(1), defining same number of variables for any given array.
     * @param a Array with integers.
     * @return Length of the longest subsequence with even sum.
     */
    public static int what (int []a)
    {
        final int ARR_LENGTH=a.length; //constant with array length
        int result=0, firstOddCounter=0;//declaring result variable and first odd numbers counter
        for (int i=0; i<ARR_LENGTH; i++)
        {
            if(a[i]%2!=0)
            {
                firstOddCounter++;//counting odd numbers
            }
        }
        if(firstOddCounter%2==0)//in case there is even number of odd values sum of all the array is even
        return ARR_LENGTH;
        if((firstOddCounter==1)&&(ARR_LENGTH==1))//in case the array contains only one number and it is odd
        return 0;
        int secondOddCounter=firstOddCounter,secondResult=result;//declaring copy of result and odd number counter variables to check in both directions in the same time
        for(int i=0;i<ARR_LENGTH;i++)//check the length of sequense from the left and from the right
        {
            if(firstOddCounter!=0)//exit condition
            {
                if(a[i]%2!=0)
                {
                    if(firstOddCounter>1)//condition to count odd number
                    result++;
                    firstOddCounter-=1;
                }
                else result++;//count even number anyway
            }
            if(secondOddCounter!=0)//exit condition
            {
                if(a[ARR_LENGTH-i-1]%2!=0)
                {
                    if(secondOddCounter>1)//condition to count odd number
                    secondResult++;
                    secondOddCounter-=1;
                }
                else secondResult++;//count even number anyway
            }
            if ((secondOddCounter==0)&&(firstOddCounter==0))//exit condition
            break;
        }
        return Math.max(result,secondResult);//return the longest sequence
    }
    /**
     * Method prints all the solutions for equation x1+x2+x3=num, when 10>=x1,x2,x3>=0 and num is given number.
     * Method returns number of solutions.
     * If given number is less than 3 or more then 30 returning 0.
     * @param num Given solution of equation.
     * @return Number of solutions.
     */
    public static int solutions(int num)
    {
        int solutionsNumber=0;//declaring solutions counter
        if((num<3)||(num>30))//checking that input is correct
        return solutionsNumber;
        return solutions(num,1,1,1,solutionsNumber);//call overloaded private help function with starting values
    }
    /**
     * Private helping method, overloading "solutions" public function.
     * @param num Given parameter of equision.
     * @param x1 Value of x1.
     * @param x2 Value of x2.
     * @param x3 value of x3.
     * @param solutionsNumber Current solutions number.
     * @return Total solutions number.
     */
    private static int solutions(int num,int x1,int x2,int x3,int solutionsNumber)//helping private method, overloads publick one
    {
        if(x3>10)//reset x3 every time it is >10, increment x2
        {
            x3=1;
            x2++;
        }
        if(x2>10)//reset x2 every time it is >10, increment x1
        {
            x2=1;
            x1++;
        }
        if((x1+x2+x3==num)&&(x1<11)&&(x2<11)&&(x3<11))//if combination is a solution and x's are in range print and increase counter of solutions
        {
            System.out.println(x1+" + "+x2+" + "+x3);
            solutionsNumber++;
        }
        if((x1<=10))//exit condition, when x1 is >10 all combinations checked
        {
            return solutions(num,x1,x2,x3+1,solutionsNumber);
        }
        return solutionsNumber;//exit return
    }
    /**
     * Method determinates if there is any path in given array which sum is equal to given.
     * Method also assigns the found path to given array.
     * @param mat Matrix vith values of every step.
     * @param sum Sum of path to find and determinate existense.
     * @param path Array to write path to, should be provided as 0 matrix with same sizes with mat.
     */
    public static boolean findSum(int mat[][], int sum, int path[][]) //External method
    {
        return findSumAuxiliary(mat, sum, path, 0, 0);//calling for auxiliary method
    }
    /**
     * Private auxiliary method.
     * @param mat Matrix vith values of every step.
     * @param sum Sum of path to find and determinate existense.
     * @param path Array to write path to.
     * @param i X coordinate to start at.
     * @param j Y coordinate to start at.
     */
    private static boolean findSumAuxiliary (int[][] mat, int sum, int[][] path, int i, int j)//Looking for the beginning of the path 
    {
        if ((!(i >= 0 && i < mat.length && j >= 0 && j < mat[0].length))) //If we checked all the beginnings, return false
        {
            return false;
        }
        if (findSum(mat, sum, path, i, j)) //if solution found
        {
            return true;
        }
        return findSumAuxiliary (mat, sum, path, i, j + 1)|| findSumAuxiliary (mat, sum, path, i + 1, j);//start at next points
    }
    /**
     * Another Auxiliary method to find desired path, overloads findPath.
     * Method that searches the path from given coordinates.
     * @param mat Matrix vith values of every step.
     * @param sum Sum of path to find and determinate existense.
     * @param path Array to write path to.
     * @param i X coordinate to start at.
     * @param j Y coordinate to start at.
     */
    private static boolean findSum(int mat[][], int sum, int path[][], int i, int j)//Search path 
    {
        if (sum < mat[i][j]) 
        {
            return false;
        }
        path[i][j] = 1;
        if (sum == mat[i][j]) //If path found return true
        {
            return true;
        }
        boolean down=((i+1<mat.length)&&(path[i+1][j]==0));//conditions to move right
        boolean up=((i-1>=0)&&(path[i-1][j]==0));//conditions to move left
        boolean left=((j-1>=0)&&(path[i][j-1]==0));//conditions to move up
        boolean right=((j+1<mat.length)&&(path[i][j+1]==0));//conditions to move down
        // try go right
        if (right && findSum(mat, sum - mat[i][j], path, i, j + 1)) 
        {
            return true;
        }
        // try go down
        if (down && findSum(mat, sum - mat[i][j], path, i + 1, j)) 
        {
            return true;
        }
        // try go up
        if (up && findSum(mat, sum - mat[i][j], path, i - 1, j))
        {
            return true;
        }
        // try go left
        if (left && findSum(mat, sum - mat[i][j], path, i, j - 1))
        {
            return true;
        }
        // none of the above succeeded - there is no "good" path starting at (i, j).
        path[i][j] = 0; // undo
        return false;
    }
}
