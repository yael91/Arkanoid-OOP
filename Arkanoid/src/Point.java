/**
 * Point : creates Point with x and y values.
 */
public class Point {

    /**
     * private double x.
     */
    private double x;
    /**
     * private double y.
     */
    private double y;

    /**
     * constructor : restores x and y for the point.
     *
     * @param x : double number.
     * @param y : double number.
     */
    public Point(double x, double y) {

        this.x = x;
        this.y = y;
    }

    /**
     * distance : return the distance of this point to the other point.
     *
     * @param other : point number.
     * @return : double number.
     */
    public double distance(Point other) {
        double dist = Math.sqrt(Math.pow(other.getX() - this.x, 2)
                + Math.pow(other.getY() - this.y, 2));
        return dist;
    }

    /**
     * equals : return true is the points are equal, false otherwise.
     *
     * @param other : point number.
     * @return : true/false.
     */
    public boolean equals(Point other) {
        //validation.
        if (other == null) {
            return false;
        }
        // if the values are equals return true.
        if ((other.getX()) == (this.x) && (other.getY()) == (this.y)) {
            return true;
        }
        return false;
    }

    /**
     * getX : Return the x value of this point.
     *
     * @return : x
     */
    public double getX() {
        return this.x;
    }

    /**
     * getX : Return the y value of this point.
     *
     * @return : y
     */
    public double getY() {
        return this.y;
    }

}


