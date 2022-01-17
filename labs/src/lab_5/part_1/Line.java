package lab_5.part_1;


public class Line
{
    private boolean _isIntersect = false;
    private final int _x1, _y1, _x2, _y2;

    public int get_x1() { return _x1; }
    public int get_y1() { return _y1; }
    public int get_x2() { return _x2; }
    public int get_y2() { return _y2; }

    public boolean get_isIntersect() { return _isIntersect; }
    public void set_isIntersect(boolean value) { _isIntersect = value; }

    public Line() { _x1 = 500; _y1 = 50; _x2 = 10; _y2 = 100; }
    public Line(int x1, int y1, int x2, int y2) { _x1 = x1; _y1 = y1; _x2 = x2; _y2 = y2; }
}
