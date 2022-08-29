import java.util.Scanner;

public class MatrixOps
{
     public static void main(String []args)
     {
         Scanner input = new Scanner(System.in);
         
         System.out.println("How many columns does your system have?");
         int cols = input.nextInt();
         
         System.out.println("How many row does your system have?");
         int rows = input.nextInt();
         
         double[][] testMatrix = new double[rows][cols];
         double[][] otherTestMatrix = new double[rows][cols];
         
         for(int i = 0; i < rows; i++)
         {
             System.out.println("Input " + cols + " values for each equation " + i + ":");
             for(int j = 0; j < cols; j++)
             {
                 testMatrix[i][j] = input.nextDouble();
                 otherTestMatrix[i][j] = testMatrix[i][j];
             }
         }
         System.out.println("\nOriginal Matrix:");
        
        for(int i = 0; i < testMatrix.length; i++)
        {
            for(int j = 0; j < testMatrix[i].length; j++)
            {
                System.out.print(testMatrix[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\nRow Reduced Matrix:");
        
        double[][] newMatrix = rowReduce(testMatrix);
        for(int i = 0; i < newMatrix.length; i++)
        {
            for(int j = 0; j < newMatrix[i].length; j++)
            {
                System.out.print(newMatrix[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.print("\nLinear dependency of the system: ");
        if(linIndependent(testMatrix))
            System.out.print("Independent");
        else
            System.out.print("Dependent");
        
        System.out.println("\nDeterminant of the system: " + determinant(otherTestMatrix));
     }
     
     public static double[][] rowReduce(double[][] matrix)
     {
        boolean consistent, independent;
        boolean[] pivots = new boolean[matrix[0].length];
        for(int i = 0; i < matrix[0].length; i++)
            pivots[i] = false;
        
        int pivotCount = 0;
         
         int rowPos = 0;
         for(int k = 0; k < matrix[0].length; k++)
         {
            int position = -1;
            for(int i = rowPos; i < matrix.length; i++)
            {
                if(matrix[i][k] != 0)
                {
                    position = i;
                    break;
                }
            }
         
            if(position == -1)
            {
                //Remember to do more stuff here
                independent = false;
                System.out.println("The system is linearly dependent, this is not currently supported by the program");
                
                continue;
            }
            else if(position != rowPos)
            {
                double[] temp = matrix[rowPos];
                matrix[rowPos] = matrix[position];
                matrix[position] = temp;
             
                //System.out.println("Swapped row " + rowPos + " with row " + position);
            }
            
            if(position != -1)
            {
                pivots[k] = true;
                pivotCount++;
            }
         
            if(matrix[rowPos][k] != 1)
            {
                double factor = matrix[rowPos][k];
                for(int i = k; i < matrix[rowPos].length; i++)
                    matrix[rowPos][i] /= factor;
            
                //System.out.println("Divided each element in row " + rowPos + " by " + factor);
            }
         
            double factors[] = new double[matrix.length];
         
            for(int i = rowPos; i < matrix.length; i++)
            {
                factors[i] = -matrix[i][k];
            }
         
            for(int i = rowPos + 1; i < matrix.length; i++)
            {
                for(int j = k; j < matrix[i].length; j++)
                {
                    matrix[i][j] += factors[i] * matrix[rowPos][j];
                }
            }
            
            if(rowPos + 1 >= matrix.length)
                break;
            else
                rowPos++;
         }
         
         int pivPos = 0;
         int rowCounter = 0;
         double[] newFactors = new double[matrix.length];
         for(int i = 0; i < pivotCount; i++)
         {
             for(int j = pivPos; j < pivots.length; j++)
             {
                 if(pivots[j])
                 {
                     pivPos = j;
                     break;
                 }
             }
             for(int j = rowCounter - 1; j >= 0; j--)
             {
                 newFactors[j] = -matrix[j][i]; 
             }
             
             for(int k = rowCounter - 1; k >= 0; k--)
             {
                 for(int l = i; l < matrix[0].length; l++)
                 {
                     matrix[k][l] += newFactors[k] * matrix[rowCounter][l];
                 }
             }
             
             if(rowCounter + 1 >= matrix.length)
                break;
             else
                rowCounter++;
         }
         
         return matrix;
     }
     
     public static boolean linIndependent(double[][] matrix)
     {
         return determinant(matrix) != 0;
     }
     
     public static double determinant(double[][] matrix)
     {
         int size = matrix.length;
         if(matrix[0].length != size)
         {
             System.out.print("ERROR: Determinants are only defined on (n x n) - matrices");
         }
         
         if(size == 2)
             return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
         else if(size < 2)
             return matrix[0][0];
         
         double det = 0.0;
         
         for(int i = 0; i < size; i++)
         {
             double[][] subMatrix = new double[size-1][size-1];
             int track = 0;
             
             for(int j = 0; j < size; j++)
             {
                 if(i == j)
                    continue;
                 
                 for(int k = 1; k < size; k++)
                 {
                     subMatrix[k - 1][track] = matrix[k][j];
                 }
                 track++;
             }
            
             if(i % 2 == 0)
                det += (matrix[0][i] * determinant(subMatrix));
             else
                det -= (matrix[0][i] * determinant(subMatrix)); 
             //System.out.println(det);
         }
         
         return det;
     }
}