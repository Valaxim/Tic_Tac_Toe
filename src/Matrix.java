public class Matrix <T> {

    private final T[][] matrix;
    private final int rows;
    private final int cols;

    @SuppressWarnings("unchecked")
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = (T[][]) new Object[rows][cols];
    }

    public T get(int row, int col) {
        return this.matrix[row][col];
    }

    public T set(T value, int row, int col) {
        T v = this.matrix[row][col];
        this.matrix[row][col] = value;
        return v;
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }
}
