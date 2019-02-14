import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class twoD {
	public static ArrayList<String> arrTxtLines=new ArrayList<String>();
	public static ArrayList<vector> arrData=new ArrayList<vector>();
	public static ArrayList<vector> arrTest=new ArrayList<vector>();
	public static void main(String[] args) {

		dataFile();
		dataTest();
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter the value of beta greater than or equal to 1: ");
		double beta = reader.nextDouble(); // Scans the next token of the input as a double.
		//once finished
		reader.close();
		if (beta>=1) {
			for(vector vtest:arrTest)
			{
				vector theta=new vector();
				theta.setX(vtest.getX());
				theta.setY(vtest.getY());
				System.out.println(depth(arrData,theta,beta));
			}
		}
		else 
			System.out.println("The value of beta is invalid! It should be greater than or equal to 1 !!");
	}



	public static double depth(ArrayList<vector> v, vector theta, double beta) {
		double Beta_sk_Depth=0;
		for(Integer i=0;i<= v.size()-1;i++)
		{
			vector vectorMain=v.get(i);
			for(Integer j=0;j<i;j++)
			{
				vector vectorNotMain=v.get(j);
				vector centerMain=vectoradd(Mult(beta/2 , vectorminus(vectorNotMain,vectorMain)) , vectorMain);
				vector centerNotMain=vectoradd(Mult(beta/2 , vectorminus(vectorMain,vectorNotMain)) , vectorNotMain);

				Double dmain=distance(centerMain , theta);
				Double dNotMain=distance(centerNotMain , theta);
				//the radius of circles=d(centerMain,vectorMain)=d(centerNotMain,vectorNotMain)
				if(Math.max(dmain, dNotMain)<= (distance(centerNotMain, vectorNotMain)))
				{
					Beta_sk_Depth=Beta_sk_Depth+1;
				}
			}	

		}
		Beta_sk_Depth=Beta_sk_Depth/((v.size())*(v.size()-1)/2); //Normalizing
		return Beta_sk_Depth;

	}
	public static Double distance(vector v1, vector v2)
	{
		return Math.sqrt(Math.pow(v1.getX()-v2.getX(), 2)+Math.pow(v1.getY()-v2.getY(), 2));

	}

	public static vector vectoradd(vector v1, vector v2)
	{
		double cx = v1.getX()+v2.getX();
		double cy = v1.getY()+v2.getY();
		vector c = new vector();
		c.setX(cx);
		c.setY(cy);
		return c;
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

	public static vector Mult(double a, vector v)
	{
		double cx = a*v.getX();
		double cy = a*v.getY();
		vector c = new vector();
		c.setX(cx);
		c.setY(cy);
		return c;

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

