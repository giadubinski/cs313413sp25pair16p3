package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

    // TODO entirely your job (except onCircle)

    private final Canvas canvas;

    private final Paint paint;

    public Draw(final Canvas canvas, final Paint paint) {
        this.canvas = canvas;
        this.paint = paint;
        paint.setStyle(Style.STROKE);
    }

    @Override
    public Void onCircle(final Circle c) {
        canvas.drawCircle(0, 0, c.getRadius(), paint);
        return null;
    }

    @Override
    public Void onStrokeColor(final StrokeColor c) {

        int firstColor = paint.getColor();

        paint.setColor(c.getColor());
        paint.setStyle(Style.FILL_AND_STROKE);
        c.getShape().accept(this);
        paint.setColor(firstColor);
        paint.setStyle(Style.STROKE);

        return null;
    }

    @Override
    public Void onFill(final Fill f) {

        Paint.Style firstStyle = paint.getStyle();
        paint.setStyle(Paint.Style.FILL);
        f.getShape().accept(this);
        paint.setStyle(firstStyle);

        return null;
    }

    @Override
    public Void onGroup(final Group g) {

        for(Shape shape : g.getShapes()) {

            if(shape instanceof Location) {
                shape.accept(this);
            }
            else {
                shape.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void onLocation(final Location l) {

        canvas.save();
        canvas.translate(l.getX(), l.getY());
        l.getShape().accept(this);
        canvas.restore();

        return null;
    }

    @Override
    public Void onRectangle(final Rectangle r) {

        canvas.drawRect(0, 0, r.getWidth(), r.getHeight(), paint);

        return null;
    }

    @Override
    public Void onOutline(Outline o) {

        Paint.Style firstStyle = paint.getStyle();
        paint.setStyle(Paint.Style.STROKE);
        o.getShape().accept(this);
        paint.setStyle(firstStyle);

        return null;
    }

    @Override
    public Void onPolygon(final Polygon s) {

        final float[] pts = null;

        canvas.drawLines(pts, paint);

        return null;
    }
}
