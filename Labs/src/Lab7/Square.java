package Lab7;
public class Square {
    int[][] sq;
    Square(int size) {
        sq = new int[size][size];
    }

    static int magicConstant(int[][] square) {
        int squareSize = square.length;
        return (squareSize * (squareSize * squareSize + 1))/2;
    }

    void fill(int[] arr){
        int index = 0;
        for (int r = 0; r < sq.length; r++) {
            for (int c = 0; c < sq.length; c++) {
                sq[r][c] = arr[index];
                index++;
            }
        }
    }

    boolean isMagic() {
        int magic = magicConstant(sq);
        if (checkColumns() == sq.length && checkRows() == sq.length && checkDiagonals() == 2 && checkValues() == 0) {
            return true;
        } else {
            return false;
        }
    }

    int checkColumns() {
        int magic = magicConstant(sq);
        int count = 0;
        int sum;
        for (int c = 0; c < sq.length; c++) {
            sum = 0;
            for (int r = 0; r < sq.length; r++) {
                sum += sq[r][c];
            }
            if (sum == magic) {
                count++;
            }
        }
        return count;
    }

    int checkRows() {
        int magic = magicConstant(sq);
        int count = 0;
        int sum;
        for (int r = 0; r < sq.length; r++) {
            sum = 0;
            for (int c = 0; c < sq.length; c++) {
                sum += sq[r][c];
            }
            if (sum == magic) {
                count++;
            }
        }
        return count;
    }

    int checkDiagonals() {
        int magic = magicConstant(sq);
        int count = 0;
        int sum1 = 0;
        int sum2 = 0;
        for (int r = 0; r < sq.length; r++) {
            sum1 += sq[r][r];
            sum2 += sq[r][sq.length-1-r];
        }
        if (sum1 == magic && sum2 == magic) {
            return 2;
        } else if (sum1 == magic || sum2 == magic) {
            return 1;
        } else {
            return 0;
        }
    }

    int checkValues() {
        int[] found = new int[sq.length*sq.length];
        int max = sq.length*sq.length;
        int value;
        int wrong = 0;
        for (int r = 0; r < sq.length; r++) {
            for (int c = 0; c < sq.length; c++) {
                value = sq[r][c];
                if (value > max || value < 1){
                    wrong++;
                } else {
                    boolean foundValue = false;
                    for (int alreadyFound : found) {
                        if (alreadyFound == sq[r][c]) {
                            foundValue = true;
                        }
                    }
                    if (foundValue) {
                        wrong++;
                    } else {
                        found[value-1] = value;
                    }
                }
            }
        }
        return wrong;
    }

    public String toString() {
        String result = "";
        for (int r = 0; r < sq.length; r++) {
            result += "[";
            for (int c = 0; c < sq.length; c++) {
                result += String.format("%3d ", sq[r][c]);
            }
            result += "]\n";
        }
        return result;
    }
}
