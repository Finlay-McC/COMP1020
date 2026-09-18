public class Lab1Example1 {
    public static final int[] WIDTHS = {37, 2, 5, -22, 102, 8, 178361201, 12};
    public static final int[] HEIGHTS = {13, 3, 145, 8, -8, 0, 76};

    public static void main(String[] args) {
        int width, height, pos = 0;
        double areaRect, areaEllipse, diagonal, ratio;

        while (pos < HEIGHTS.length) {

            width = WIDTHS[pos];
            height = HEIGHTS[pos];
            pos++;
            System.out.printf("***\nUsing width = %d, height = %d.\n", width, height);

            areaRect = width * height;
            areaEllipse = width * height * Math.PI;
            diagonal = Math.sqrt(width * width + width * height);
            if (Double.isNaN(diagonal)) {
                System.out.println("ERROR");
            }
            if (height == 0) {
                System.out.println("ERROR");
                ratio = 0;
            } else {
                ratio = (double) width / (double) height;
            }

            System.out.printf("\nArea of a %d x %d rectangle = %.1f\n", width, height, areaRect);
            System.out.printf("Area of a %d x %d ellipse = %.1f\n", width, height, areaEllipse);
            System.out.printf("Diagonal of a %d x %d right triangle = %.1f\n", width, height, diagonal);
            System.out.printf("Ratio of %d over %d = %.1f\n", width, height, ratio);

        }
    }
}