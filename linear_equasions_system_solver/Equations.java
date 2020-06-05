
/**
 * This class analyses given system of 2 linear equations.
 *
 * @author Anton Pluzharov
 * @version 14.03.2019
 */
import java.util.Scanner;
public class Equations
{
   public static void main(String[]args)
   {
       Scanner scan = new Scanner(System.in); //declaring scanner instance
       final double ROUND_COEFFICIENT=1000;
       System.out.println("This programm solves a system of 2 linear equations");
       System.out.println("Enter the coefficients a11 a12 a21 a22 b1 b2");
       //prompt user input
       int a11=scan.nextInt();
       int a12=scan.nextInt();
       int a21=scan.nextInt();
       int a22=scan.nextInt();
       int b1=scan.nextInt();
       int b2=scan.nextInt();
       //printing provided system
       System.out.println("Eq1: "+a11+"*x1+"+a12+"*x2="+b1);
       System.out.println("Eq2: "+a21+"*x1+"+a22+"*x2="+b2);
       
       //analysing and solution part
       if (((a11*a22)-(a12*a21))!=0)//term of single solution
       {
           //declaring and calculation of x1 and x2 according to provided formula
           double x1 = (double)(((b1*a22)-(b2*a12))/(double)((a11*a22)-(a12*a21)));
           double x2 = (double)(((b2*a11)-(b1*a21))/(double)((a11*a22)-(a12*a21)));
           //round results to 3 digits after dot
           x1=(double)Math.round(x1*ROUND_COEFFICIENT)/ROUND_COEFFICIENT;
           x2=(double)Math.round(x2*ROUND_COEFFICIENT)/ROUND_COEFFICIENT;
           //print results
           System.out.print("Single solution: ("+x1+","+x2+")");
       }//end of if
       else //if there is no single solution
       {
           boolean haveMultipleSolutions = (((b2*a11)-(b1*a21))==0)&&(((b1*a22)-(b2*a12))==0);//two first multiple solutions term
           boolean haveNoSolutions = (((b1!=0)&&((a11==0)&&(a12==0)))||((b2!=0)&&((a21==0)&&(a22==0))));//no sollutions term
           if (haveNoSolutions)
           {
               System.out.println("No solution");
           }
           else if (haveMultipleSolutions)
           {
               System.out.println("Many solutions");
           }
       }//end of else
       //end of analysing solutions part
   }//end of main
}//end of class Equations
