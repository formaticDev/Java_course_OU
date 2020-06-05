
/**
 * Class circle will calculate area, perimeter and radius of incircle
 * and excircle of given rectangle. Rectangle should be given as upper left
 * and right down angle points coordinates.
 * @author Anton Pluzharov
 * @version 14.03.2019
 */
import java.util.Scanner;
public class Circle
{
    public static void main (String[]args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("This programm calculates the areas "+
            "and the perimeters of the excircle and the incircle "+
            "of given rectangle ");
        System.out.print("Please enter the two coordinates of the "+
            "left-upper point of rectangle");
        int leftUpX=scan.nextInt();
        int leftUpY=scan.nextInt();
        //up to this point everything was copied from the question
        System.out.print("Please enter the two coordinates of the "+
            "right-down point of rectangle");
        int rightDownX=scan.nextInt();
        int rightDownY=scan.nextInt();
        //declaring radius of excircle as constant according to given formula
        final double EXCIRCLE_RADIUS=Math.sqrt((Math.pow((rightDownX-leftUpX),2.0)+
            Math.pow((leftUpY-rightDownY),2.0)))/2;
        //declaring radius of incircle as constant
        final double INCIRCLE_RADIUS=((double)(leftUpY-rightDownY))/2;
        //calculating and storing incircle area
        double IncircleArea=Math.PI*Math.pow(INCIRCLE_RADIUS,2.0);
        //calculating and storing incircle perimeter
        double IncirclePerimeter=2.0*Math.PI*INCIRCLE_RADIUS;
        //calculating and storing excircle area
        double ExcircleArea=Math.PI*Math.pow(EXCIRCLE_RADIUS,2.0);
        //calculating and storing excircle perimeter
        double ExcirclePerimeter=2.0*Math.PI*EXCIRCLE_RADIUS;
        //providing output of calculated data
        System.out.println("Incircle: radius = "+INCIRCLE_RADIUS+", area = "+
            IncircleArea+", perimeter = "+IncirclePerimeter);
        System.out.println("Excircle: radius = "+EXCIRCLE_RADIUS+", area = "+
            ExcircleArea+", perimeter = "+ExcirclePerimeter);
    }//end of method main
}//end of class circle
