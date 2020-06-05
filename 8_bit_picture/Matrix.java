/**
 * Class instances contains matrix with values from 0-255 in each cell.
 * Matrix is a black-white picture with colors from white (0) to black (255)
 *
 * @author Anton Pluzharov
 * @version 15.04.2019
 */
public class Matrix
{
    //declare instance variables
    private int[][] _array;
    private final int MAX_CELL_VALUE=255;
    /**
     * Constructor copies given array to instance variable.
     */
    public Matrix(int[][] array)
    {
        _array=new int[array.length][array[0].length];
        for (int i=0; i<_array.length;i++)
        {
            for (int j=0;j<_array[0].length;j++)
            {
                _array[i][j]=array[i][j];
            }
        }
    }
    /**
     * Constructor creates array in given sizes and fills it with 0 in every cell.
     */
    public Matrix(int size1, int size2)
    {
        _array=new int[size1][size2];
        for (int i=0; i<_array.length;i++)
        {
            for (int j=0; j<_array[i].length;j++)
            {
                _array[i][j]=0;
            }
        }
    }
    /**
     * Method prints array of instance as matrix. Overrides standard toString().
     * @return string String with matrix from array of instance.
     */
    public String toString()
    {
        String string = "";
        for (int i=0; i<_array.length;i++)
        {
            for (int j=0; j<_array[0].length;j++)
            {
                string+=_array[i][j];
                if(j+1!=_array[0].length)
                {
                    string+="\t";
                }
            }
            string+="\n";
        }
        return string;
    }
    /**
     * Method returns new matrix, which is negative to instance.
     * Assigns to each cell in new matrix 255 minus the same cell value in original matrix.
     * @return negative Matrix with negative values.
     */
    public Matrix makeNegative()
    {
        Matrix negative=new Matrix(this._array);
        for (int i=0; i<negative._array.length;i++)
        {
            for (int j=0; j<negative._array[0].length;j++)
            {
                negative._array[i][j]=255-negative._array[i][j];
            }
        }
        return negative;
    }
    /**
     * Method returns filtered image of instance.
     * Every cell in filtered image is average of the same cell in original image.
     * @return filteredImage Filtered image.
     */
    public Matrix imageFilterAverage()
    {
        Matrix filteredImage=new Matrix(_array.length, _array[0].length);
        for (int i=0;i<_array.length;i++)
        {
            for(int j=0; j<_array[0].length;j++)
            {
                int neighbourCount=1;
                int sum=_array[i][j];
                if (i>0)
                    {
                        sum+=_array[i-1][j];
                        neighbourCount++;
                    }
                if (j>0)
                    {
                        sum+=_array[i][j-1];
                        neighbourCount++;
                    }
                if ((i>0)&&(j>0))
                    {
                        sum+=_array[i-1][j-1];
                        neighbourCount++;
                    }
                if (j+1<_array[0].length)
                    {
                        sum+=_array[i][j+1];
                        neighbourCount++;
                    }
                if (i+1<_array.length)
                    {
                        sum+=_array[i+1][j];
                        neighbourCount++;
                    }
                if ((i+1<_array.length)&&(j+1<_array[0].length))
                    {
                        sum+=_array[i+1][j+1];
                        neighbourCount++;
                    }
                if ((i>0)&&(j+1<_array[0].length))
                    {
                        sum+=_array[i-1][j+1];
                        neighbourCount++;
                    }
                if ((j>0)&&(i+1<_array.length))
                    {
                        sum+=_array[i+1][j-1];
                        neighbourCount++;
                    }
                filteredImage._array[i][j]=sum/neighbourCount;
            }
        }
        return filteredImage;
    }
    /**
     * Method rotates instance matrix counter clockwise.
     * @return result Matrix of instance, rotated counter clockwise.
     */
    public Matrix rotateCounterClockwise()
    {
        Matrix result=new Matrix(this._array[0].length,this._array.length);
        for (int i=0;i<this._array.length;i++)
        {
            for (int j=0;j<this._array[0].length;j++)
            {
                result._array[result._array.length-(j+1)][i]=this._array[i][j];
            }
        }
        return result;
    }
    /**
     * Method rotates instance matrix clockwise.
     * @return result Matrix of instance, rotated clockwise.
     */    
    public Matrix rotateClockwise()
    {
        Matrix result=new Matrix(this._array[0].length,this._array.length);
        for (int i=0;i<this._array.length;i++)
        {
            for (int j=0;j<this._array[0].length;j++)
            {
                result._array[j][result._array[0].length-(i+1)]=this._array[i][j];
            }
        }
        return result;
    }
}
