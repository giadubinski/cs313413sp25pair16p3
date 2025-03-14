package edu.luc.etl.cs313.android.shapes.model;

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

    // TODO entirely your job (except onCircle)

    @Override
    public Location onCircle(final Circle c) {
        final int radius = c.getRadius();
        return new Location(-radius, -radius, new Rectangle(2 * radius, 2 * radius));
    }

    @Override
    public Location onFill(final Fill f) {

        return f.getShape().accept(this);
    }

    @Override
    public Location onGroup(final Group g) {

        int minX = Integer.MAX_VALUE;

        int maxX = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = 0;
        for (Shape s : g.getShapes()) {
        }
        return new Location(minX, minY, new Rectangle(maxX - minX, maxY - minY));
    }

    @Override
    public Location onLocation(final Location l) {

        Location loc = l.getShape().accept(this);
        return new Location(l.getX() + loc.getX(), l.getY() + loc.getY(), loc.getShape());
    }

    @Override
    public Location onRectangle(final Rectangle r) {

        return new Location(0, 0, new Rectangle(r.getWidth(), r.getHeight()));
    }

    @Override
    public Location onStrokeColor(final StrokeColor c) {

        return c.getShape().accept(this);
    }

    @Override
    public Location onOutline(final Outline o) {

        return o.getShape().accept(this);
    }

    @Override
    public Location onPolygon(final Polygon s) {

        return null;
    }
}
