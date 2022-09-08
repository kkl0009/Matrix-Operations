
class LinearAlgebra {
    private static class Matrix {
        private double[][] matrix;
        private int rows;
        private int cols;
        
        public Matrix(double[][] matrix) {
            this.matrix = matrix;
            
            rows = matrix.length;
            cols = matrix[0].length;
        }
        
        public Matrix(int rows, int cols) {
            matrix = new double[rows][cols];
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    matrix[i][j] = 0;
                }
            }
            
            this.rows = rows;
            this.cols = cols;
        }
        
        public boolean setRow(int row_num, double[] row) {
            if(row_num < 0 || row_num >= rows) {
                System.out.println("ERROR: Row number must be between 0 and " + (rows - 1));
                return false;
            }
            if(row.length != cols) {
                System.out.println("ERROR: Input row must contain " + cols + "values");
                return false;
            }
            
            matrix[row_num] = row;
            return true;
        }
        
        public boolean setCol(int col_num, double[] col) {
            if(col_num < 0 || col_num >= cols) {
                System.out.println("ERROR: Column number must be between 0 and " + (cols - 1));
                return false;
            }
            if(col.length != rows) {
                System.out.println("ERROR: Input column must contain " + rows + " values");
                return false;
            }
            
            for(int i = 0; i < rows; i++) {
                matrix[i][col_num] = col[i];
            }
            return true;
        }
        
        public boolean setValue(int row_num, int col_num, double value) {
            if(row_num < 0 || row_num >= rows) {
                System.out.println("ERROR: Row number must be between 0 and " + (rows - 1));
                return false;
            }
            if(col_num < 0 || col_num >= cols) {
                System.out.println("ERROR: Column number must be between 0 and " + (cols - 1));
                return false;
            }
            
            matrix[row_num][col_num] = value;
            return true;
        }
        
        public void replaceMatrix(double[][] matrix) {
            this.matrix = matrix;
            rows = matrix.length;
            cols = matrix[0].length;
        }
        
        public void replaceMatrix(Matrix new_matrix) {
            double[][] new_array = new_matrix.asArray();
            matrix = new_array;
            rows = new_array.length;
            cols = new_array[0].length;
        }
        
        public Matrix getTranspose() {
            double[][] transpose = new double[cols][rows];
            
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    transpose[j][i] = matrix[i][j];
                }
            }
            
            return new Matrix(transpose);
        }
        
        public void setAsTranspose() {
            Matrix transpose = getTranspose();
            replaceMatrix(transpose);
        }
        
        public Matrix getHorizontalFlip() {
            double[][] horizontal = new double[rows][cols];
            
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    horizontal[i][(cols - 1) - j] = matrix[i][j];
                }
            }
            
            return new Matrix(horizontal);
        }
        
        public void setAsHorizontalFlip() {
            Matrix horizontal = getHorizontalFlip();
            replaceMatrix(horizontal);
        }
        
        public Matrix getVerticalFlip() {
            double[][] vertical = new double[rows][cols];
            
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    vertical[(rows - 1) - i][j] = matrix[i][j];
                }
            }
            
            return new Matrix(vertical);
        }
        
        public void setAsVerticalFlip() {
            Matrix vertical = getVerticalFlip();
            replaceMatrix(vertical);
        }
        
        // public Matrix getGaussianElim() {
        //     double[][] copy = new double[rows][cols];
        //     for(int i = 0; i < rows; i++) {
        //         for(int j = 0; j < cols; j++) {
        //             copy[i][j] = matrix[i][j];
        //         }
        //     }
            
        //     for(int j = 0; j < cols - 1; j++) {
        //         for(int i = 0; i < rows; i++) {
        //             //Reduce pivot point to 1
        //             double reduce = copy[i][j];
        //             for(int k = 0; k < cols; k++) {
        //                 copy[i][k] /= reduce;
        //             }
                    
        //             // Matrix debug = new Matrix(copy);
        //             // System.out.println(debug);
                    
        //             //Subtract remaining rows from pivot point
        //             for(int k = 0; k < )
        //         }
        //     }
        //     return null;
        // }
        
        public double[][] asArray() {
            return matrix;
        }
        
        public int getRows() {
            return rows;
        }
        
        public int getCols() {
            return cols;
        }
        
        public String toString() {
            String toPrint = "\n";
            for(int i = 0; i < rows; i++) {
                toPrint += "| ";
                for(int j = 0; j < cols; j++) {
                    toPrint += matrix[i][j] + " ";
                }
                toPrint += "|\n";
            }
            toPrint += "\n";
            return toPrint;
        }
    }
    
    public static void main(String[] args) {
        double[][] matrix = new double[][] {{3, 1, 2, 4}, {8, 4, 6, 3}, {2, 5, 2, 7}, {8, 4, 2, 6}};
        Matrix test = new Matrix(matrix);
        System.out.println(test);
    }
}