
    
    /**
     * Class contains constructors of new insances of points in three-dimensional space,
     * and all their parameters and functions.
     *
     * @author Anton Pluzharov
     * @version 23.03.2019
     */
    import java.lang.Math;
    public class Point3D
    {
        // instance variables
        private double _x, _y, _z; //storage for three coordinates of each point
    
        /**
         * Create Point3D instance with coordinates (0,0,0).
         */
    public Point3D()
    {
     // initialise instance variables
     _x=0;
     _y=0;
     _z=0;
    }
    /**
     * Creates new Point3D instance with provided parameters.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     */
    public Point3D(double x, double y, double z)
    {
        // initialise instance variables
        _x=x;
        _y=y;
        _z=z;
    }
    /**
     * Copy coordinates from provided instance to new Point3D instance.
     * 
     * @param point A point to copy a coordinates from.
     */
    public Point3D(Point3D other)
    {
        // initialise instance variables
        _x=other._x;
        _y=other._y;
        _z=other._z;
    }
    /**
     * Method returns an X coordinate of point.
     *
     * @return    The X coordinate of point.
     */
    public double getX()
    {
        return this._x;
    }
    /**
     * Method returns an Y coordinate of point.
     *
     * @return    The Y coordinate of point.
     */
    public double getY()
    {
        return this._y;
    }
    /**
     * Method returns an Z coordinate of point.
     *
     * @return    The Z coordinate of point.
     */
    public double getZ()
    {
        return this._z;
    }
    /**
     * Sets the X coordinate of instance to given value.
     * 
     * @param num Desired value of instances X coordinate.
     */
    public void setX(double num)
    {
        this._x=num;
    }
    /**
     * Sets the Y coordinate of instance to given value.
     * 
     * @param num Desired value of instances Y coordinate.
     */
    public void setY(double num)
    {
        this._y=num;
    }
    /**
     * Sets the Z coordinate of instance to given value.
     * 
     * @param num Desired value of instances Z coordinate.
     */
    public void setZ(double num)
    {
        this._z=num;
    }
    /**
     * Return the content of Point3D instance as math-representation string.
     * 
     * @return String with mathematical representation of points coordinates.
     */
    @Override
    public String toString()
    {
        return "("+this._x+","+this._y+","+this._z+")";
    }
    /**
     * Determinates if the provided point has same coordinates as the
     * point on which the method was run.
     * 
     * @param other The point to compare with.
     */
    public boolean equals(Point3D other)
    {
      if ((this._x==other._x)&&(this._y==other._y)&&(this._z==other._z))
        return true;
      else
        return false;
    }
    /**
     * Determinates if the point is above other point up to Z coordinates.
     * @param other Other point to compare with.
     * @return True if this point is above the other, otherwise false.
     */
    public boolean isAbove(Point3D other)
    {
        if (this._z>other._z)
        {
            return true;
        }
        return false;
    }
    /**
     * Determinates if the point is under other point up to Z coordinates.
     * @param other Other point to compare with.
     * @return True if this point is under the other, otherwise false.
     */
    public boolean isUnder(Point3D other)
    {
        return other.isAbove(this);
    }
    /**
     * Determinates if the point is to the left of other point
     * according to their Y coordinates.
     * @param other Other point to compare with.
     * @return True if this point is to the left to the other, otherwise false.
     */
    public boolean isLeft(Point3D other)
    {
        if (this._y<other._y)
        {
            return true;
        }
        return false;
    }/**
     * Determinates if the point is to the right of other point
     * according to their Y coordinates.
     * @param other Other point to compare with.
     * @return True if this point is to the right of the other, otherwise false.
     */
    public boolean isRight(Point3D other)
    {
        return other.isLeft(this);
    }
    /**
     * Determinates if the point is behind other point up to X coordinates.
     * @param other Other point to compare with.
     * @return True if this point is behind the other, otherwise false.
     */
    public boolean isBehind(Point3D other)
    {
        if (this._x<other._x)
        {
            return true;
        }
        return false;
    }
    /**
     * Determinates if the point is in front of other point up to X coordinates.
     * @param other Other point to compare with.
     * @return True if this point is above the other, otherwise false.
     */
    public boolean isInFrontOf(Point3D other)
    {
        return other.isBehind(this);
    }
    /**
     * Moves the point from (x,y,z) to (x+dx,y+dy,z+dz). 
     * @param dx Value of move on X axis.
     * @param dy Value of move on Y axis.
     * @param dz Value of move on Z axis.
     */
    public void move(double dx, double dy, double dz)
    {
        this._x+=dx;
        this._y+=dy;
        this._z+=dz;
    }
    /**
     * Calculates distance between this and the other points.
     * @param p Point to calculate distance with.
     * @return Distance between this and the other points.
     */
    public double distance(Point3D p)
    {
        return Math.sqrt(Math.pow(this._x-p._x,2)+Math.pow(this._y-p._y,2)+Math.pow(this._z-p._z,2));
    }
}