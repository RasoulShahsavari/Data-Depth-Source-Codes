import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
public class twoD {
	public static ArrayList<String> arrTxtLines=new ArrayList<String>();
	public static ArrayList<vector> arrData=new ArrayList<vector>();
	public static ArrayList<vector> arrTest=new ArrayList<vector>();
	public static void main(String[] args) {

		dataFile();
		dataTest();

		for(vector vtest:arrTest)
		{
			vector theta=new vector();
			theta.setX(vtest.getX());
			theta.setY(vtest.getY());
			System.out.println(depth_tu(arrData,theta));
		}
	}
	public static double depth_tu(ArrayList<vector> v, vector theta) {
		double Tuk_theta=10;
		int nt = 0;//number of trivial points W.R.T O=(0,0)
		int n = v.size();
		ArrayList<vector> translated =new ArrayList<vector>(n);
		translated = Translate(v,theta);

		for(Integer i=0;i<n;i++) 
		{
			if (istrivial(translated.get(i)))
				nt=nt+1;	
		}
		//System.out.println("nt==============="+nt);

		double[] ntv = new double[2*(n-nt)];//n-nt instead of n??   //an array for storing projected non 
		Integer j=0;                                                // trivial points (and their reflections)
		for (Integer i=0;i<n;i++)                                   //on the boundary of C(0,1)
		{
			ntv[j]=0;
			vector u = translated.get(i);
			if (!istrivial(u))
			{
				ntv[j]=Math.atan2(u.getY(),u.getX());
				ntv[j+(n-nt)]=Math.atan2(u.getY(),u.getX())+Math.PI;
				j=j+1;	
			}
		}

		Arrays.sort(ntv);

		int k = ntv.length;
		k= removeDuplicates(ntv, k);
		double[] slope = new double[k];// check the dimensions !!!
		for (Integer i=0; i<k;i++)
		{
			slope[i]=ntv[i];
		}


		for (Integer i=0;i<slope.length-1;i++)//check the dimensions!!!!
		{
			// to compute the depth of a data-point query w.r.t a data set 
			// double left_counter =nt-1;
			//double right_counter=nt-1;

			// to compute the depth of a non-data-point query w.r.t a data set 
			double left_counter =nt; // they should be double to avoid zero when normalizing
			double right_counter=nt; // the normalization factor in 2/n

			for(Integer ii =0;ii<n;ii++)
			{
				vector datapoint=translated.get(ii);
				if (!istrivial(datapoint)) {
					double point_place = datapoint.getY()-(Math.tan((slope[i]+slope[i+1])/2))*datapoint.getX();
					if (point_place < 0) {
						left_counter=left_counter+1;
					}
					else if (point_place > 0) {
						right_counter=right_counter+1;
					}
				}
			}
			//System.out.println("left="+left_counter+"right="+right_counter);
			Tuk_theta= Math.min(Tuk_theta , (2*Math.min(left_counter, right_counter))/n); //Normalizing at the same time!
		}                                                                                 

		return Tuk_theta;

	}


	public static ArrayList<vector> Translate (ArrayList<vector> a , vector b)
	{
		int n = a.size();
		ArrayList<vector> tra= new ArrayList<vector>(n);
		for (Integer i=0;i<n;i++) {
			vector tr = vectorminus(a.get(i),b);
			tra.add(i,tr);
		}
		return tra;
	}

	public static vector vectorminus(vector v1, vector v2)
	{
		double cx = v1.getX()-v2.getX();
		double cy = v1.getY()-v2.getY();
		vector c = new vector();
		c.setX(cx);
		c.setY(cy);
		return c;
	}

	public static Double length(vector v)
	{
		return Math.sqrt(Math.pow(v.getX(), 2)+Math.pow(v.getY(), 2));

	}

	public static boolean istrivial(vector v)
	{
		double epsilon=1e-8;
		if (length(v)< epsilon) 
			return true;

		else
			return false;
	}

	// Function to remove duplicate elements
	// This function returns new size of modified
	// array.
	public static int removeDuplicates(double arr[], int n)
	{
		// Return, if array is empty
		// or contains a single element
		if (n==0 || n==1)
			return n;

		double [] temp = new double[n];

		// Start traversing elements
		int j = 0;
		for (int i=0; i<n-1; i++)
			// If current element is not equal
			// to next element then store that
			// current element
			if (arr[i] != arr[i+1])
				temp[j++] = arr[i];

		// Store the last element as whether
		// it is unique or repeated, it hasn't
		// stored previously
		temp[j++] = arr[n-1]; 

		// Modify original array
		for (int i=0; i<j; i++)
			arr[i] = temp[i];

		return j;
	}


	public static void dataFile() {
		File file = new File("C:/Dataset.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;

		try {
			fis = new FileInputStream(file);

			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			// dis.available() returns 0 if the file does not have more lines.
			while (dis.available() != 0) {
				String[] temp=dis.readLine().split(",");
				vector vtest=new vector();
				vtest.setX((Double.parseDouble(temp[0]) ));
				vtest.setY(Double.parseDouble(temp[1]));
				arrData.add(vtest);
			}

			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void dataTest() {
		File file = new File("C:/Theta.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;

		try {
			fis = new FileInputStream(file);

			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			// dis.available() returns 0 if the file does not have more lines.
			while (dis.available() != 0) {
				String[] temp=dis.readLine().split(",");
				vector vtest=new vector();
				vtest.setX((Double.parseDouble(temp[0]) ));
				vtest.setY(Double.parseDouble(temp[1]));
				arrTest.add(vtest);
			}

			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

