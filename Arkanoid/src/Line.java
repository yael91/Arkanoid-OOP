/**
     * Line : create Line with two points given.
     */
    public class Line {
        /**
         * private Point start.
         */
        private Point start;
        /**
         * private Point end.
         */
        private Point end;

        /**
         * constructor : restores start and end for the Line.
         *
         * @param start1 point number.
         * @param end1   point number.
         */
        public Line(Point start1, Point end1) {
            this.start = start1;
            this.end = end1;
        }

        /**
         * Line : creates new line.
         *
         * @param x1 : point number.
         * @param y1 : point number.
         * @param x2 : point number.
         * @param y2 : point number.
         */
        public Line(double x1, double y1, double x2, double y2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        }

        /**
         * length :  Return the length of the line.
         *
         * @return double number.
         */
        public double length() {
            return this.start.distance(this.end);
        }

        /**
         * middle : Returns the middle point of the line.
         *
         * @return Point value.
         */
        public Point middle() {
            double midX = (this.start.getX() + this.end.getX()) / 2;
            double midY = (this.start.getY() + this.end.getY()) / 2;
            return new Point(midX, midY);
        }

        /**
         * start : Returns the start point of the line.
         *
         * @return Point value.
         */
        public Point start() {
            return this.start;
        }

        /**
         * end : Returns the end point of the line.
         *
         * @return Point value.
         */
        public Point end() {
            return this.end;
        }

        /**
         * isIntersecting :  Returns true if the lines intersect, false otherwise.
         *
         * @param other : line number.
         * @return Boolean value.
         */
        public boolean isIntersecting(Line other) {
            //calculate the incline in case one of the incline is 0.
            double side = (this.end.getY() - this.start.getY())
                    * (other.end().getX() - other.start().getX());
            double side2 = (other.end().getY() - other.start().getY())
                    * (this.end.getX() - this.start.getX());
            //if the lines are parallel.
            if (side == side2) {
                if (other.start().equals(this.end) || other.end().equals(this.start)) {
                    return true;
                }
                if (other.start().equals(this.end) || other.end().equals(this.start)) {
                    return true;
                }
                return false;
            }
            //validation.
            if (this.intersectionWith(other) == null) {
                return false;
            }
            return true;
        }

        /**
         * calculateIncline :  calculates incline.
         *
         * @param other : line number.
         * @return Boolean value.
         */
        public double calculateIncline(Line other) {
            return (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
        }

        /**
         * intersectionWith :  Returns the intersection point if the lines intersect
         * and null otherwise.
         *
         * @param other :line number.
         * @return Point value.
         */
        public Point intersectionWith(Line other) {
            Point intersection = this.intersectPoint(other);
            if (intersection == null) {
                return null;
            }
            return intersection;
        }

        /**
         * intersectPoint :  calculate intersection point.
         *
         * @param other : line number.
         * @return Point value.
         */
        public Point intersectPoint(Line other) {
            double x1 = this.start.getX();
            double y1 = this.start.getY();

            double x2 = this.end.getX();
            double y2 = this.end.getY();

            double x3 = other.start.getX();
            double y3 = other.start.getY();

            double x4 = other.end.getX();
            double y4 = other.end.getY();

            double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
            // no intersection
            if (d == 0) {
                return null;
            }

            double x = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
            double y = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);
            x /= d;
            y /= d;

            Point intersection = new Point(x, y);
            if (2 * intersection.distance(this.middle()) > this.length()) {
                return null;
            }
            if (2 * intersection.distance(other.middle()) > other.length()) {
                return null;
            }
            return intersection;
        }

        /**
         * rangeLine :  calculate intersection point.
         *
         * @param other         line number.
         * @param intersectionX double number.
         * @param intersectionY double number.
         * @return boolean value.
         */
        public boolean rangeLine(Line other, double intersectionX, double intersectionY) {
            //the min or max value.
            double maxThis = Math.max(this.start.getX(), this.end.getX());
            double minThis = Math.min(this.start.getX(), this.end.getX());
            double maxOther = Math.max(other.start.getX(), other.end.getX());
            double minOther = Math.min(other.start.getX(), other.end.getX());
            double maxThisY = Math.max(this.start.getY(), this.end.getY());
            double minThisY = Math.min(this.start.getY(), this.end.getY());
            double maxOtherY = Math.max(other.start.getY(), other.end.getY());
            double minOtherY = Math.min(other.start.getY(), other.end.getY());
            // check the range (validate).
            double eps = 0.0007;
            if (((intersectionX <= maxThis + eps) && (intersectionX >= minThis - eps))
                    && ((intersectionX <= maxOther + eps) && (intersectionX >= minOther - eps)
                    && (intersectionY <= maxThisY + eps) && (intersectionY >= minThisY - eps))
                    && ((intersectionY <= maxOtherY + eps) && (intersectionY >= minOtherY - eps))) {
                return true;
            }
            return false;
        }

        /**
         * equals -- return true is the lines are equal, false otherwise.
         *
         * @param other : line number.
         * @return boolean value.
         */
        public boolean equals(Line other) {
            // check if the Points are the same.
            if ((other.start().equals(this.start))
                    && (other.end().equals(this.end))
                    || (other.start().equals(this.end)
                    && (other.end().equals(this.start)))) {
                return true;
            }
            return false;
        }

        /**
         * closestIntersectionToStartOfLine :f this line does not intersect with the rectangle, return null
         * Otherwise, return the closest intersection point to the start of the line.
         *
         * @param rect : Rectangle.
         * @return closest Point.
         */
        public Point closestIntersectionToStartOfLine(Rectangle rect) {
            Line[] lines = rect.createLinesRectangled();
            Point closest = null;
            for (int i = 0; i < lines.length; ++i) {
                Point inter = this.intersectionWith(lines[i]);
                if (inter == null) {
                    continue;
                }
                if (closest == null) {
                    closest = inter;
                    continue;
                }
                if (this.start.distance(inter) < this.start.distance(closest)) {
                    closest = inter;
                }
            }
            return closest;

        }
    }


