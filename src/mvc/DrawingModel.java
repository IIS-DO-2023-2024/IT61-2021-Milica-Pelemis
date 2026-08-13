package mvc;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class DrawingModel {

    private List<Shape> shapes = new ArrayList<Shape>();

    public List<Shape> getShapes() {
        return shapes;
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public void add(int index, Shape shape) {
        shapes.add(index, shape);
    }

    public void remove(Shape shape) {
        shapes.remove(shape);
    }

    public void remove(int index) {
        shapes.remove(index);
    }

    public Shape get(int index) {
        return shapes.get(index);
    }

    public void set(int index, Shape shape) {
        shapes.set(index, shape);
    }

    public int size() {
        return shapes.size();
    }

    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    public int indexOf(Shape shape) {
        return shapes.indexOf(shape);
    }
}