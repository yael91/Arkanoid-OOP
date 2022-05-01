 /**
     * Rectangle : create Rectangle with upperleft point width and height.
     */
    public class Rectangle {
        //declarations.
        private Point upperLeft;
        private double width;
        private double height;

        /**
         * constructor : Create a new rectangle with location and width/height.
         *
         * @param upperLeft1 : point object.
         * @param width1     : double number.
         * @param height1    : double number.
         */
        public Rectangle(Point upperLeft1, double width1, double height1) {
            this.upperLeft = upperLeft1;
            this.width = width1;
            this.height = height1;
        }

        /**
         * setHeight : sets the game enviroment..
         *
         * @param height1 : height (double) of the rectangle.
         */
        public void setHeight(double height1) {
            this.height = height1;
        }

        /**
         * createLinesRectangled : creates Lines of Rectangled.
         *
         * @return arrayLinesRectangle: array of lines.
         */
        public Line[] createLinesRectangled() {
            Line lineLeft1 = new Line(this.upperLeft, new Point(this.upperLeft.getX(), this.upperLeft.getY() + height));
            Line lineUp1 = new Line(this.upperLeft, new Point(this.upperLeft.getX() + width, this.upperLeft.getY()));
            Line lineDown1 = new Line(new Point(this.upperLeft.getX(), this.upperLeft.getY() + height),
                    new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height));
            Line lineRight1 = new Line(new Point(this.upperLeft.getX() + width, this.upperLeft.getY()),
                    new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height));
            Line[] arrayLinesRectangle = new Line[]{lineLeft1, lineRight1, lineUp1, lineDown1};
            return arrayLinesRectangle;
        }

        /**
         * intersectionPoints :Return a (possibly empty) List of intersection points.
         * with the specified line
         *
         * @param line : Line object.
         * @return ArrayList : java.util.List of lines.
         */
        public java.util.List intersectionPoints(Line line) {
            java.util.List<Point> arrayList = new java.util.ArrayList<Point>();
            //creates array lines and check if lines intersecting.
            Line[] arrayLines = createLinesRectangled();
            for (int j = 0; j < arrayLines.length; j++) {
                if (line.isIntersecting(arrayLines[j])) {
                    arrayList.add(line.intersectionWith(arrayLines[j]));
                }
            }
            return arrayList;
        }

        /**
         * getWidth : excess Return the width of the rectangle.
         *
         * @return width: double value.
         */
        public double getWidth() {
            return this.width;
        }

        /**
         * getHeight : excess Return the height of the rectangle.
         *
         * @return height: double value.
         */
        public double getHeight() {
            return this.height;
        }

        /**
         * getUpperLeft : excess Return the upper-left point of the rectangle.
         *
         * @return upperLeft: Point object.
         */
        public Point getUpperLeft() {
            return this.upperLeft;
        }

        /**
         * setUpperLeft  set the upper-left point of the rectangle.
         *
         * @param point this is point.
         */
        public void setUpperLeft(Point point) {

            this.upperLeft = point;
        }

        /**
         * inside :Return true if the point is on the rectangle.
         *
         * @param p : Point object.
         * @return boolean value.
         */
        public boolean inside(Point p) {
            if (p.getX() > upperLeft.getX() && p.getX() < upperLeft.getX() + width && p.getY()
                    > upperLeft.getY() && p.getY() < upperLeft.getY() + height) {
                return true;
            }
            return false;
        }


}


