/**
 * Class contains Collection of 3DBoxes.
 *
 * @author Anton Pluzharov
 * @version 15.4.2019
 */

public class Collection
{
    // Constants
    private final int MAX_NUM_BOXES=100;
    // instance variables - boxes array and boxes counter
    private Box3D [] _boxes;
    private int _noOfBoxes;
    /**
     * Constructor for objects of class Collection
     */
    public Collection()
    {
        _boxes=new Box3D[MAX_NUM_BOXES];
        _noOfBoxes=0;
    }
    /**
     * Method adds Box to array.
     * @param base Base of added box.
     * @param length Length of added box.
     * @param width Width of added box.
     * @param height Height of added box.
     * @return True if box was insertet, false otherwise.
     */
    public boolean addBox(Point3D base, int length, int width, int height)
    {
        //creating new box and declaring variables
        Box3D newBox=new Box3D(base, length, width, height);
        int low =0, high=_noOfBoxes-1;
        int newBoxIndex=-1;//index to insert newBox
        int newBoxVolume=newBox.getVolume(); //newBox volume
        //check there is place in array
        if (MAX_NUM_BOXES<=_noOfBoxes)
        {
            return false;
        }
        //case _boxes is empty
        if (low>high)
        {
            _boxes[low]=newBox;
            _noOfBoxes++;
            return true;
        }
        //starting to find a place for the newBox with binary search
        while (high>=low)
        {
            int middle = (low+high)/2;
            int middleBoxVolume = _boxes[middle].getVolume();     
            if (_boxes[middle].getVolume()==newBoxVolume) //case when there is a box with same volume in array
                {
                    newBoxIndex = middle;
                    break;
                } else
                if (newBoxVolume>middleBoxVolume)  //if box in the middle is smaller looking right
                    {
                        low = middle+1;
                    } else
                if (newBoxVolume<middleBoxVolume) //if box in the middle is bigger looking left
                    {
                        high = middle-1;
                    }
            if (high<low) //case when we already found the index and there is no box with same volume
                {
                    if (newBoxVolume>middleBoxVolume)
                        {
                            for (int i=_noOfBoxes-1; i>middle; i--) //moving all boxes to the right of found box
                            {
                                _boxes[i+1]=_boxes[i];
                            }
                            _boxes[middle+1]=new Box3D(newBox); //insert new box to the right of found one
                            _noOfBoxes++;
                            return true;
                        } else
                        if (newBoxVolume<middleBoxVolume)
                            {
                                for (int i=_noOfBoxes-1; i>=middle; i--) //moving all boxes to the right of found box including
                                {
                                    _boxes[i+1]=_boxes[i];
                                }
                                _boxes[middle]=new Box3D(newBox); //insert new box to the left of found one
                                _noOfBoxes++;
                                return true;
                            }
                }
        } 
        if(newBoxIndex!=-1) //case found box with same volume
            {
               while ((newBoxIndex>0)&&(_boxes[newBoxIndex].getVolume()==_boxes[newBoxIndex-1].getVolume()))//case there are more boxes with same volume before the one found
               {
                   newBoxIndex--;
               }
               for (int i=_noOfBoxes-1; i>=newBoxIndex; i--) //moving all boxes to the right of found box index including
                                {
                                    _boxes[i+1]=_boxes[i];
                                }
               _boxes[newBoxIndex]=new Box3D(newBox);
               _noOfBoxes++;               
               return true;
            } else return false; //other cases
    }
    /**
     * Method returns copy of box with the most upper base corner.
     * @return highestBox The first box with highest base corner in array.
     */
    public Box3D mostUpperBaseCorner()
    {
        if (_noOfBoxes>0)//if _base is not empty
        {
            Box3D highestBox=new Box3D(_boxes[0]);//setting first box as highest one for the beginning
            for (int i=1; i<_noOfBoxes;i++)//boxes are sorted by volume and not by height, so going over all the array
            {
                if (_boxes[i].getBase().isAbove(highestBox.getBase()))//determinating if current box have higher base corner than the current max
                {
                    highestBox = new Box3D(_boxes[i]);
                }
            }
            return new Box3D(highestBox);//return box with max height of base corner
        }
    return null;
    }
    /**
     * Method returns the sum of surface areas of all boxes in array.
     * @return total The sum of surface area of all the boxes in array.
     */
    public double totalSurfaceArea()
    {
        double total=0.0;
        for (int i=0; i<_noOfBoxes;i++)
        {
            total+=_boxes[i].getSurfaceArea();
        }
        return total;
    }
    /**
     * Method return the longest distance between centers of any two boxes in array.
     * @return maxDistance The longest distance between two centers of any two boxes in array.
     */
    public double longestDistance()
    {
        double maxDistance = 0.0;
        for (int i=0; i<_noOfBoxes-1;i++) //first box to compare
        {
            for (int j=i+1; j<_noOfBoxes;j++) //second box to compare
            {
                //calculating distance between the centers
                double currentDistance=_boxes[i].getCenter().distance(_boxes[j].getCenter());
                if(maxDistance<currentDistance)
                {
                    maxDistance=currentDistance;
                }
            }
        }
        return maxDistance;
    }
    /**
     * Method returns number of boxes, which can contain the given box.
     * @param box Box to check.
     * @return total Total ammount of boxes which can contain given box.
     */
    public int howManyContains(Box3D box)
    {
        int counter=0; //counter of boxes which can contain
        for (int i=0; i<_noOfBoxes;i++)//check if every box can contein the given one
        {
            if (_boxes[i].contains(box)) counter++;
        }
        return counter;
    }
    /**
     * Method returns volume of smallest box in array which can contain every boxes in given range one by one. 
     * @param i Any index.
     * @param j Any index.
     * @return volume The smallest volume of box which can contain all boxeds in given range, if there is no such box-0.
     */
    public int volumeOfSmallestBox(int i, int j)
    {
        int volume=0;
        //validation of input
        if((i<0)||(j<0)||(i>=_noOfBoxes)||(j>=_noOfBoxes)||(Math.abs(i-j)>=_noOfBoxes)) return 0;
        if (i<=j) 
        {
            volume=assistVolumeOfSmallerBox(i,j);
        }
        else
        {
            volume=assistVolumeOfSmallerBox(j,i);
        }
        return volume;
    }
    /**
     * Assist method returns volume of smallest box in array which can contain every boxes in given range one by one.
     * @param i First range parameter.
     * @param j Second range parameter.
     * @return volume The smallest volume of box which can contain all boxes in given range, if there is no such box - 0.
     */
    private int assistVolumeOfSmallerBox(int i,int j)
    {
        int volume=0;
            for (int x=0; x<_noOfBoxes; x++)//index of box to check
            {
                if ((x<i)||(x>j))//check only out of given range since box cant contain itself\other box with similar parameters
                {
                    boolean status=true;
                    for (int y=i; y<=j; y++)//compare box x to box y(inside of range)
                    {
                        if (!_boxes[x].contains(_boxes[y]))//break if box x can't contain box from range
                        {
                            status=false;
                            break;
                        }
                    }
                    if ((status)&&(volume>_boxes[x].getVolume())) //case found box with smaller volume which can contain all the boxes in range
                    {
                        volume=_boxes[x].getVolume();
                    } else
                    {
                        if ((status)&&(volume==0))//case first matched box
                        {
                            volume=_boxes[x].getVolume();
                        }
                    }
                }
            }
            return volume;
    }
    /**
     * Method returns array with all boxes which exists in main array.
     * @return realBoxes All the boxes which exists in main array.
     */
    public Box3D[] getBoxes()
    {
        Box3D[] realBoxes=new Box3D[_noOfBoxes];
        for (int i=0; i<realBoxes.length;i++)
        {
            realBoxes[i]=new Box3D(_boxes[i]);
        }
        return realBoxes;
    }
    /**
     * Method returns number of existing boxes in array.
     * @return _noOfBoxes Number of boxes in array.
     */
    public int getNumOfBoxes()
    {
        return _noOfBoxes;
    }
    /**
     * Method returns string with description of all boxes in main array. Override of default toString().
     * @return description Human readible description of boxes in array.
     */
    public String toString()
    {
        String description= "";
        for (int i=0; i<_noOfBoxes;i++)
        {
            description+="Box no. "+(i+1)+": "+(_boxes[i].toString())+"\n";
        }
        return description;
    }
}