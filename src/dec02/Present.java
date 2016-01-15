package dec02;

public class Present {

    private int length = 0;
    private int width = 0;
    private int height = 0;
    private int maxDimension;

    public Present(String dimensions) {
        parseDimensions(dimensions);
        maxDimension = calculateMaxDimension();
    }

    private void parseDimensions(String dimensions) {
        String[] splitDimensions = dimensions.split("x");
        if(splitDimensions.length == 3) {
            try {
                length = Integer.parseInt(splitDimensions[0]);
                width = Integer.parseInt(splitDimensions[1]);
                height = Integer.parseInt(splitDimensions[2]);
            }
            catch(NumberFormatException e) {
                System.err.println("Unable to parse dimensions: " + dimensions);
            }
        }
    }

    public int calculateTotalWrappingPaper() {
        return calculateSurfaceArea() + calculateSlack();
    }

    private int calculateSurfaceArea() {
        return (2 * length * width) + (2 * width * height) + (2 * height * length);
    }

    private int calculateSlack() {
        return (length * width * height) / maxDimension;
    }

    public int calculateTotalRibbon() {
        return calculateSmallestPerimeter() + calculateCubicArea();
    }

    private int calculateSmallestPerimeter() {
        return 2 * (length + width + height - maxDimension);
    }

    private int calculateCubicArea() {
        return length * width * height;
    }

    private int calculateMaxDimension() {
        return Math.max(Math.max(length, width), height);
    }
}
