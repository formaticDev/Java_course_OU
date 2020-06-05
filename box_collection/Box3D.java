
/**
 * Class provides instances of 3D boxes and contains all their
 * parameters and methods.
 *
 * @author Anton Pluzharov
 * @version 23.03.2019
 */
public class Box3D
{
    //final variables for calculations
    final double DOUBLE_SCALAR=2.0, HALF_SCALAR=0.5;
    // instance variables
    private Point3D _base;//base point of box
    private int _length, _width, _height;//parameters of box

    /**
     * Default constructor creates box with base corner
     * (0,0,0) and all parameters equals 1.
     */
    public Box3D()
    {
        // initialise instance variables
        _base=new Point3D();
        _length=1;
        _width=1;
        _height=1;
    }
    /**
     * Constructor creates box with base as provided point and
     * width, length and hight as provided.
     * Each provided parameter which is not greater than 0 will be
     * set to 1.
     * @param p Point to set as base.
     * @param length Length of box.
     * @param width Width of box.
     * @param height Height of box.
     */
    public Box3D(Point3D p, int length, int width, int height)
    {
        _base=p;
        if(length<=0)
        {
            _length=1;
        }   else _length=length;
        if(width<=0)
        {
            _width=1;
        }   else _width=width;
        if(height<=0)
        {
            _height=1;
        }   else _height=height;
    }
    /**
     * Constructor will create copy of provided instance.
     * @param other The box to copy.
     */
    public Box3D (Box3D other)
    {
        _base=other._base;
        _length=other._length;
        _width=other._width;
        _height=other._height;
    }
    /**
     * Standard parameter getter.
     * @return Length of the box.
     */
    public int getLength()
    {
        return _length;
    }
    /**
     * Standard parameter getter.
     * @return Width of the box.
     */
    public int getWidth()
    {
        return _width;
    }
    /**
     * Standard parameter getter.
     * @return Height of the box.
     */
    public int getHeight()
    {
        return _height;
    }
    /**
     * Standard parameter getter.
     * @return Base point of the box.
     */
    public Point3D getBase()
    {
        return new Point3D(this._base);
    }
    /**
     * Standard parameter setter.
     * @param num Number to set length.
     */
    public void setLength(int num)
    {
        if (num>0)
        {
            _length=num;
        }
    }
    /**
     * Standard parameter setter.
     * @param num Number to set width.
     */
    public void setWidth(int num)
    {
        if (num>0)
        {
            _width=num;
        }
    }
    /**
     * Standard parameter setter.
     * @param num Number to set height.
     */
    public void setHeight(int num)
    {
        if (num>0)
        {
            _height=num;
        }
    }
    /**
     * Standard parameter setter.
     * @param p Number to set base point.
     */
    public void setBase(Point3D p)
    {
        _base= new Point3D(p);
    }
    /**
     * Override of standard toString function.
     * 
     * @return Returns string which displays content of instance according to template.
     */
    public String toString()
    {
        return "The base point is "+this._base.toString()+
            ", length = "+this._length+", width = "+this._width+
                ", height = "+this._height;
    }
    /**
     * Determinates if this and the other box equals.
     * @param other The box to compare with.
     * @return True if all the boxes parameters are equals,
     * false otherwise.
     */
    public boolean equals(Box3D other)
    {
        return (this._base.equals(other._base))&&
                (this._width==other._width)&&
                (this._length==other._length)&&
                (this._height==other._height);
    }
    /**
     * Creates new box, which is the old box moved by
     * three given values.
     * @param dx Value to move the box on X axis.
     * @param dy Value to move the box on Y axis.
     * @param dz Value to move the box on Z axis.
     * @return New Box3D instance wich is moved "this" box.
     */
    public Box3D move(double dx, double dy, double dz)
    {
        Point3D base_moved=new Point3D(this._base);
        base_moved.move(dx,dy,dz);
        return new Box3D(base_moved,this._length,this._width,this._height);
    }
    /**
     * Calculates up-right-back corner point coordinates of the box.
     * @return Point3D instance which have coordinates of up-right-back corner.
     */
    public Point3D getUpRightBackPoint()
    {
        Point3D base=this.getBase();
        Point3D upRightBackPoint=new Point3D(base.getX()-this._length,base.getY()+this._width,base.getZ()+this._height);
        return upRightBackPoint;
    }
    /**
     * Calculates coordinates of center of the box.
     * @return Point3D instance with coordinates of box center.
     */
    public Point3D getCenter()
    {
        Point3D center=this.getBase();
        center.move(-HALF_SCALAR*this.getLength(),this.getWidth()*HALF_SCALAR,this.getHeight()*HALF_SCALAR);
        return center;
    }
    /**
     * Calculates distance between centers of this and the other box.
     * @param other The box to calculate distance with.
     * @return The distance between centers of this and the other boxes center as real number.
     */
    public double distance(Box3D other)
    {
        return this.getCenter().distance(other.getCenter());
    }
    /**
     * Calculates the box volume.
     * @return Box volume as integer.
     */
    public int getVolume()
    {
        return (int)(this.getLength()*this.getHeight()*this.getWidth());
    }
    /**
     * Calculates surface area of the box.
     * @return Box surface area as integer.
     */
    public int getSurfaceArea()
    {
        return (int)((this.getHeight()*this.getWidth()+
                this.getHeight()*this.getLength()+
                this.getLength()*this.getWidth())*DOUBLE_SCALAR);
    }
    /**
     * Determinates if the box have larger volume than given box.
     * @param other The box to compare with.
     * @return True is this box have larger volume, false otherwise.
     */
    public boolean isLargerCapacity(Box3D other)
    {
            return this.getVolume()>other.getVolume();
    }
    /**
     * Determinates if the box can contain the other box.
     * @param other The other box to compare with.
     * @return True if the box can contain the other box, false otherwise.
     */
    public boolean contains(Box3D other)
    {
        return (this.getHeight()>other.getHeight())&&
                (this.getWidth()>other.getWidth())&&
                (this.getLength()>other.getLength());
    }
    /**
     * Determinates if the box is above the other given box.
     * @param other The other box to compare with.
     * @return True if the box is above the other box, false otherwise.
     */
    public boolean isAbove(Box3D other)
    {
        return this.getBase().isAbove(other.getUpRightBackPoint());
    }
}
