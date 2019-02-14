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
		double BetaInfty_theta=0;
		for(Integer i=0;i<= v.size()-1;i++)
		{
			
				vector vectorMain=v.get(i);
				Double dmain=distance(vectorMain, theta);
				for(Integer j=0;j<i;j++)
				{
					vector vectorNotMain=v.get(j);
					Double dNotMain=distance(vectorNotMain, theta);
					//System.out.println(" Math.max(dmain, dNotMain) : "+Math.max(dmain, dNotMain)+ "<= distance(vectorMain, vectorNotMain) : " +distance(vectorMain, vectorNotMain));
					if(Math.abs(Math.pow(dmain,2) - Math.pow(dNotMain,2)) <= (Math.pow(distance(vectorMain, vectorNotMain),2)))
					{
						BetaInfty_theta=BetaInfty_theta+1;
					}
				}	
			 
		}
		BetaInfty_theta=BetaInfty_theta/((v.size())*(v.size()-1)/2); //Normalizing
		return BetaInfty_theta;
		
	}
	public static Double distance(vector v1, vector v2)
	{
		 return Math.sqrt(Math.pow(v1.getX()-v2.getX(), 2)+Math.pow(v1.getY()-v2.getY(), 2));
		
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

