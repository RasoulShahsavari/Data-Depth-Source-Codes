import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
		System.out.println(depth(arrData,theta));
	}
	}
	
	public static double depth(ArrayList<vector> v, vector theta) {
		double Sph_theta=0;
		for(Integer i=0;i<= v.size()-1;i++)
		{
			
				vector vectorMain=v.get(i);
				//Double dmain=distance(vectorMain, theta);
				for(Integer j=0;j<i;j++)
				{
					vector vectorNotMain=v.get(j);
					//vector medianvector_ij=new vector();
					//medianvector_ij.setX((vectorMain.getX()+vectorNotMain.getX())/2);
					//medianvector_ij.setY((vectorMain.getY()+vectorNotMain.getY())/2);
					vector medianvector_ij=Mult(0.5,vectoradd(vectorMain,vectorNotMain)); // the constant parameter of Mult is double!!
					
					//Double dNotMain=distance(vectorNotMain, theta);
					//System.out.println(" Math.max(dmain, dNotMain) : "+Math.max(dmain, dNotMain)+ "<= distance(vectorMain, vectorNotMain) : " +distance(vectorMain, vectorNotMain));
					if(distance(medianvector_ij, theta)<= ((distance(vectorMain, vectorNotMain))/2))
					{
						Sph_theta=Sph_theta+1;
					}
				}	
			 
		}
		Sph_theta=Sph_theta/((v.size())*(v.size()-1)/2); //Normalizing
		return Sph_theta;
		
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

