package javafaces;

//import Libraries
import java.io.Serializable;
import java.util.List;
/**
* 	FaceBundle class implements Serializable
*	@Shivi, Jack, Arjun, Rahul
*	@June 6, 2019
*/
public class FaceBundle implements Serializable{
	//variable and object instantiations
	private double[][] adjFaces;
	private List<String> imageFileNamesList;	
	private double[] averageFace;
	private double[][] eigenFaces;
	private double[] eigenValues;
	int imageWidth;
	int imageHeight;
	
	/**
	*	FaceBundle Constructor
	* 	@Param ArrayList<String> imglist, double Matrix adjFaces, double Array avgFace, double Matrix eigenFaces,
	*		double array evals, int w, int h
	*/
	public FaceBundle(List<String> imglist,double[][] adjFaces,double[] avgFace,double[][] eigenFaces,double[] evals,int w,int h){
		this.imageFileNamesList=imglist;
		this.adjFaces=adjFaces;		
		this.averageFace=avgFace;
		this.eigenFaces=eigenFaces;
		this.eigenValues=evals;
		this.imageWidth=w;
		this.imageHeight=h;
	}
	
	/**
	* 	getAdjustedFaces method double matrix
	*	@return adjFaces
	*/
	public double[][]getAdjustedFaces(){
		return adjFaces;
	}
	
	/**
	*	getEigenFaces method double matrix
	*	@return eigenFaces
	*/
	public double[][]getEigenFaces(){
		return eigenFaces;
	}
	
	/**
	*	getAvgFace method double array
	*	@return averageFace
	*/
	public double[] getAvgFace(){
		return averageFace;
	}
	
	/**
	*	getEigenValues method double array
	*	@return eigenValues
	*/
	public double[] getEigenValues(){
		return eigenValues;
	}
	
	/**
	*	getImageFileNamesList method ArrayList String
	*	return imageFIleNamesList
	*/
	public List<String> getImageFileNamesList(){
		return imageFileNamesList;
	}
	
	/**
	*	getImageWidth method int
	*	return imageWidth
	*/
	public int getImageWidth(){
		return imageWidth;
	}
	
	/**
	*	getImageHeigh method int
	*	return imageHeight
	*/
	public int getImageHeight(){
		return imageHeight;
	}
}
