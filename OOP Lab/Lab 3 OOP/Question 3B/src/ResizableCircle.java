public class ResizableCircle extends Circle implements Resizable {

    public ResizableCircle(double radius) {
        super(radius);
    }

    @Override
    public String toString() {
        return "ResizableCircle[radius=" + radius + "]]";
    }

    @Override
    public void resize(int percent) {
        radius *= percent/100.0;
    }
    
}
