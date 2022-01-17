package lab_5.part_1;

import java.applet.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Main extends Frame
{
	private final java.util.List<Line> linesBuffer = new ArrayList<>();

	public static void main(String[] args)
	{
		Main task = new Main();
		task.setSize(1280, 720);
		task.setVisible(true);
		task.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent we) {System.exit(0);}});
	}

	public Main()
	{
		linesBuffer.add(new Line());
		linesBuffer.add(new Line());
		linesBuffer.add(new Line(200, 300, 600, 400));
		linesBuffer.add(new Line(150, 250, 700, 100));
		linesBuffer.add(new Line(150, 250, 750, 400));
		linesBuffer.add(new Line(300, 250, 700, 210));
		linesBuffer.add(new Line(160, 180, 530, 700));
		linesBuffer.add(new Line(900, 200, 1100, 700));
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);

		findIntersectLines(linesBuffer);
		for (Line line : linesBuffer)
		{
			if (line.get_isIntersect()) g.setColor(Color.red);
			else g.setColor(Color.blue);
			g.drawLine(line.get_x1(), line.get_y1(), line.get_x2(), line.get_y2());
		}
	}

	private static void findIntersectLines(java.util.List<Line> linesBuffer)
	{
		int linesCount = linesBuffer.size();
		for (int i = 0; i < linesCount - 1; ++i)
			if (!(linesBuffer.get(i).get_isIntersect()))
				for (int j = i + 1; j < linesCount; ++j )
					checkIntersect(linesBuffer.get(i), linesBuffer.get(j));
	}

	private static void checkIntersect(Line line1, Line line2)
	{
		int x1 = line1.get_x1(); int y1 = line1.get_y1(); int x2 = line1.get_x2(); int y2 = line1.get_y2();
		int x3 = line2.get_x1(); int y3 = line2.get_y1(); int x4 = line2.get_x2(); int y4 = line2.get_y2();

		if (java.awt.geom.Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4)) { line1.set_isIntersect(true); line2.set_isIntersect(true); }

		/*
		float Ua, Ub, numerator_a, numerator_b, denominator;

		denominator = (y4 - y3) * (x1 - x2) - (x4 - x3) * (y1 - y2);
		if (denominator == 0f)
			if ((x1 * y2 - x2 * y1) * (x4 - x3) - (x3 * y4 - x4 * y3) * (x2 - x1) == 0 && (x1 * y2 - x2 * y1) * (y4 - y3) - (x3 * y4 - x4 * y3) * (y2 - y1) == 0)
				{ line1.set_isIntersect(true); line2.set_isIntersect(true); }
		else
			{
				numerator_a = (x4 - x2) * (y4 - y3) - (x4 - x3) * (y4 - y2);
				numerator_b = (x1 - x2) * (y4 - y2) - (x4 - x2) * (y1 - y2);
				Ua = numerator_a / denominator;
				Ub = numerator_b / denominator;
				if (Ua >= 0 && Ua <= 1 && Ub >= 0 && Ub <= 1) { line1.set_isIntersect(true); line2.set_isIntersect(true); }
			}
		*/

		/*
		if (y2 - y1 != 0)
		{
			float q = (float)((x2 - x1) / (y1 - y2));
			float sn = (x3 - x4) + (y3 - y4) * q;
			if (sn != 0f) { line1.set_isIntersect(true); line2.set_isIntersect(true); }
		}
		else
			if (y3 - y4 != 0) { line1.set_isIntersect(true); line2.set_isIntersect(true); }
		*/

		/*
		int a1 = y2 - y1;
		int b1 = x1 - x2;

		int a2 = y4 - y3;
		int b2 = x3 - x4;

		if (a1 * b2 - a2 * b1 != 0) { line1.set_isIntersect(true); line2.set_isIntersect(true); }
		*/

		/*
		float k1 = 0f, k2 = 0f;

		if (x1 >= x2) { int tmp = x1; x1 = x2; x2 = tmp; tmp = y1; y1 = y2; y2 = tmp; }
		if (x3 >= x4) { int tmp = x3; x3 = x4; x4 = tmp; tmp = y3; y3 = y4; y4 = tmp; }

		if (y1 != y2) k1 = (float)(y2 - y1) / (x2 - x1);
		if (y3 != y4) k1 = (float)(y4 - y3) / (x4 - x3);

		if (k1 != k2)
		{
			boolean condition_1 = x1 <= x4 && x4 <= x2;
			boolean condition_2 = x1 <= x3 && x3 <= x2;

			if (condition_1)
				if (!condition_2) { line1.set_isIntersect(true); line2.set_isIntersect(true); }
			else if (condition_2) { line1.set_isIntersect(true); line2.set_isIntersect(true); }
		}
		*/

		/*
		int tmp = x2 * y1 - x1 * y2;
		int a1 = (y2 - y1) * x3 + (x1 - x2) * y3 + tmp;
		int a2 = (y2 - y1) * x4 + (x1 - x2) * y4 + tmp;

		if (a1 < 0)
			isIntersect = a2 >= 0;
		else if (a1 > 0)
			isIntersect = a2 <= 0;
		else isIntersect = false;

		if (isIntersect) { line1.set_isIntersect(true); line2.set_isIntersect(true); }
		*/
	}
}