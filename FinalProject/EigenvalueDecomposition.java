package javafaces;

/**
*   EigenValueDecomposition class
*   @Shivi, Jack, Arjun, Rahul
*   @June 6, 2019
*/
public class EigenvalueDecomposition extends cern.colt.matrix.linalg.EigenvalueDecomposition{
	/**
	*	EigenValueDecomposition Constructor call super
	*	@Param dmat Matrix2D
	*/
	public EigenvalueDecomposition(Matrix2D dmat){
		super(dmat);
	}
	/**
	*	getEigenValues method
	*	@return array of doubles for EigenValues
	*/
	public double[]getEigenValues(){
		return diag(getD().toArray());
	}
	/**
	*	getEigenVectors method
	*	@return matrix of doubles for EigenVectors
	*/
	public double[][] getEigenVectors(){
		return getV().toArray();
	}
	/**
	*	diag method, given a matrix of doubles, returns the diagonal
	*		double values in an array
	*	@Param m Matrix of doubles
	*	@return d Array of doubles
	*/
	private double[] diag(double[][] m) {
	    //Creates double Array with size m.length
	    double[] d = new double[m.length];
	    for (int i = 0; i< m.length; i++)
	      //double Array gets the value of the corresponding row and column from the matrix
	      d[i] = m[i][i];
	    return d;
	}
	
}
