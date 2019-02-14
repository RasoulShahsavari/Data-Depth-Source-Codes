import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
public class twoD {
	public ArrayList<String> arrTxtLines=new ArrayList<String>();
	public ArrayList<vector> arrData=new ArrayList<vector>();
	public ArrayList<vector> arrTest=new ArrayList<vector>();

	vector theta1=new vector();
	vector theta2=new vector();


	double dH_L_Sph=0;
	double dH_L_Si=0;
	double dH_L_Oj=0;
	double dH_Sph_Si=0;
	double dH_Sph_Oj=0;
	double dH_Si_Oj=0;


	double dH_L_Ran1=0;
	double dH_Sph_Ran1=0;
	double dH_Si_Ran1=0;
	double dH_Oj_Ran1=0;

	double dH_L_Ran2=0;
	double dH_Sph_Ran2=0;
	double dH_Si_Ran2=0;
	double dH_Oj_Ran2=0;

	double dH_L_Ran3=0;
	double dH_Sph_Ran3=0;
	double dH_Si_Ran3=0;
	double dH_Oj_Ran3=0;

	double dH_L_Ran4=0;
	double dH_Sph_Ran4=0;
	double dH_Si_Ran4=0;
	double dH_Oj_Ran4=0;

	double dH_Ran1_Ran2=0;
	double dH_Ran1_Ran3=0;
	double dH_Ran1_Ran4=0;
	double dH_Ran2_Ran3=0;
	double dH_Ran2_Ran4=0;
	double dH_Ran3_Ran4=0;

	double[] Ran1Arry ;
	double[] Ran2Arry ;
	double[] Ran3Arry ;
	double[] Ran4Arry ;

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		twoD run = new twoD();

		run.dataFile();
		run.dataTest();

		run.mainTask();

		//long endTime = System.currentTimeMillis(); 
		//double duration = (double)(endTime - startTime);
		//System.out.println("Overall time: "+ duration);
	}


	private void mainTask() {
		Ran1Arry = new double [arrTest.size()];
		Ran2Arry = new double [arrTest.size()];
		Ran3Arry = new double [arrTest.size()];
		Ran4Arry = new double [arrTest.size()];

		long startTime = System.currentTimeMillis();

		for(int i=0; i<arrTest.size();i++)
		{
			Ran1Arry[i] = Math.random();
			Ran2Arry[i] = Math.random();
			Ran3Arry[i] = Math.random();
			Ran4Arry[i] = Math.random();


		}

		for(int i=0; i<arrTest.size();i++)
		{
			vector vtest1=arrTest.get(i);
			task(vtest1, i);

		}

		System.out.println("Hamming Distance (Lens,Spherical)="+ dH_L_Sph/((arrTest.size()*arrTest.size())-arrTest.size()));	
		System.out.println("Hamming Distance (Lens,Simplicial)="+ dH_L_Si/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Spherical,Simplicial)="+ dH_Sph_Si/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Lens,Oja)="+ dH_L_Oj/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Spherical,Oja)="+ dH_Sph_Oj/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Simplicial,Oja)="+ dH_Si_Oj/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println();
		System.out.println("Hamming Distance (Lens,RandomRank1)="+ dH_L_Ran1/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Spherical,RandomRank1)="+ dH_Sph_Ran1/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Simplicial,RandomRank1)="+ dH_Si_Ran1/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Oja,RandomRank1)="+ dH_Oj_Ran1/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println();
		System.out.println("Hamming Distance (Lens,RandomRank2)="+ dH_L_Ran2/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Spherical,RandomRank2)="+ dH_Sph_Ran2/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Simplicial,RandomRank2)="+ dH_Si_Ran2/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Oja,RandomRank2)="+ dH_Oj_Ran2/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println();
		System.out.println("Hamming Distance (Lens,RandomRank3)="+ dH_L_Ran3/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Spherical,RandomRank3)="+ dH_Sph_Ran3/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Simplicial,RandomRank3)="+ dH_Si_Ran3/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Oja,RandomRank3)="+ dH_Oj_Ran3/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println();
		System.out.println("Hamming Distance (Lens,RandomRank4)="+ dH_L_Ran4/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Spherical,RandomRank4)="+ dH_Sph_Ran4/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Simplicial,RandomRank4)="+ dH_Si_Ran4/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (Oja,RandomRank4)="+ dH_Oj_Ran4/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println();
		System.out.println("Hamming Distance (RandomRank1,RandomRank2)="+ dH_Ran1_Ran2/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (RandomRank1,RandomRank3)="+ dH_Ran1_Ran3/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (RandomRank1,RandomRank4)="+ dH_Ran1_Ran4/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (RandomRank2,RandomRank3)="+ dH_Ran2_Ran3/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (RandomRank2,RandomRank4)="+ dH_Ran2_Ran4/((arrTest.size()*arrTest.size())-arrTest.size()));
		System.out.println("Hamming Distance (RandomRank3,RandomRank4)="+ dH_Ran3_Ran4/((arrTest.size()*arrTest.size())-arrTest.size()));



		long endTime = System.currentTimeMillis(); 
		double duration = (double)(endTime - startTime);
		System.out.println();
		System.out.println("The Running time for a set of " +arrTest.size()+" points is "+ duration/1000+" seconds");


	}


	private void task(vector vtest1,int i) {
		long startTime = System.currentTimeMillis();

		double depth_le_1 = depth_le(arrData,theta1);
		double depth_le_2 = depth_le(arrData,theta2);

		double depth_si_1 = depth_si(arrData,theta1);
		double depth_si_2 = depth_si(arrData,theta2);

		double depth_sph_1 = depth_sph(arrData,theta1);
		double depth_sph_2 = depth_sph(arrData,theta2);

		double depth_oj_1 = depth_oj(arrData,theta1);
		double depth_oj_2 = depth_oj(arrData,theta2);

		for (int j=0; j<arrTest.size();j++){

			vector vtest2=arrTest.get(j);
			theta1.setX(vtest1.getX());                                                          
			theta1.setY(vtest1.getY());    
			theta2.setX(vtest2.getX());
			theta2.setY(vtest2.getY());

			// dH(D1,D2)

			if ((depth_le_1<=depth_le_2)&&(depth_si_1>depth_si_2)
					|| ((depth_le_1>depth_le_2)&&(depth_si_1<=depth_si_2))){
				dH_L_Si=dH_L_Si+1;
			}

			if ((depth_le_1<=depth_le_2)&&(depth_sph_1>depth_sph_2)
					|| ((depth_le_1>depth_le_2)&&(depth_sph_1<=depth_sph_2))){
				dH_L_Sph=dH_L_Sph+1;
			}

			if ((depth_sph_1<=depth_sph_2)&&(depth_si_1>depth_si_2)
					|| ((depth_sph_1>depth_sph_2)&&(depth_si_1<=depth_si_2))){
				dH_Sph_Si=dH_Sph_Si+1;
			}

			if ((depth_le_1<=depth_le_2)&&(depth_oj_1>depth_oj_2)
					|| ((depth_le_1>depth_le_2)&&(depth_oj_1<=depth_oj_2))){
				dH_L_Oj=dH_L_Oj+1;
			}		

			if ((depth_sph_1<=depth_sph_2)&&(depth_oj_1>depth_oj_2)
					|| ((depth_sph_1>depth_sph_2)&&(depth_oj_1<=depth_oj_2))){
				dH_Sph_Oj=dH_Sph_Oj+1;
			}

			if ((depth_oj_1<=depth_oj_2)&&(depth_si_1>depth_si_2)
					|| ((depth_oj_1>depth_oj_2)&&(depth_si_1<=depth_si_2))){
				dH_Si_Oj=dH_Si_Oj+1;
			}


			//dH(D1, Ran1)

			if ((depth_le_1<=depth_le_2)&&Ran1Arry[i]>Ran1Arry[j]
					|| ((depth_le_1>depth_le_2)&&Ran1Arry[i]<=Ran1Arry[j])){
				dH_L_Ran1=dH_L_Ran1+1;
			}

			if ((depth_sph_1<=depth_sph_2)&&Ran1Arry[i]>Ran1Arry[j]
					|| ((depth_sph_1>depth_sph_2)&&Ran1Arry[i]<=Ran1Arry[j])){
				dH_Sph_Ran1=dH_Sph_Ran1+1;
			}

			if ((depth_si_1<=depth_si_2)&&Ran1Arry[i]>Ran1Arry[j]
					|| ((depth_si_1>depth_si_2)&&Ran1Arry[i]<=Ran1Arry[j])){
				dH_Si_Ran1=dH_Si_Ran1+1;
			}

			if ((depth_oj_1<=depth_oj_2)&&Ran1Arry[i]>Ran1Arry[j]
					|| ((depth_oj_1>depth_oj_2)&&Ran1Arry[i]<=Ran1Arry[j])){
				dH_Oj_Ran1=dH_Oj_Ran1+1;
			}

			//dH(D1,Ran2)						

			if ((depth_le_1<=depth_le_2)&&Ran2Arry[i]>Ran2Arry[j]
					|| ((depth_le_1>depth_le_2)&&Ran2Arry[i]<=Ran2Arry[j])){
				dH_L_Ran2=dH_L_Ran2+1;
			}

			if ((depth_sph_1<=depth_sph_2)&&Ran2Arry[i]>Ran2Arry[j]
					|| ((depth_sph_1>depth_sph_2)&&Ran2Arry[i]<=Ran2Arry[j])){
				dH_Sph_Ran2=dH_Sph_Ran2+1;
			}

			if ((depth_si_1<=depth_si_2)&&Ran2Arry[i]>Ran2Arry[j]
					|| ((depth_si_1>depth_si_2)&&Ran2Arry[i]<=Ran2Arry[j])){
				dH_Si_Ran2=dH_Si_Ran2+1;
			}

			if ((depth_oj_1<=depth_oj_2)&&Ran2Arry[i]>Ran2Arry[j]
					|| ((depth_oj_1>depth_oj_2)&&Ran2Arry[i]<=Ran2Arry[j])){
				dH_Oj_Ran2=dH_Oj_Ran2+1;
			}

			//dH(D1,Ran3)	

			if ((depth_le_1<=depth_le_2)&&Ran3Arry[i]>Ran3Arry[j]
					|| ((depth_le_1>depth_le_2)&&Ran3Arry[i]<=Ran3Arry[j])){
				dH_L_Ran3=dH_L_Ran3+1;
			}

			if ((depth_sph_1<=depth_sph_2)&&Ran3Arry[i]>Ran3Arry[j]
					|| ((depth_sph_1>depth_sph_2)&&Ran3Arry[i]<=Ran3Arry[j])){
				dH_Sph_Ran3=dH_Sph_Ran3+1;
			}

			if ((depth_si_1<=depth_si_2)&&Ran3Arry[i]>Ran3Arry[j]
					|| ((depth_si_1>depth_si_2)&&Ran3Arry[i]<=Ran3Arry[j])){
				dH_Si_Ran3=dH_Si_Ran3+1;
			}

			if ((depth_oj_1<=depth_oj_2)&&Ran3Arry[i]>Ran3Arry[j]
					|| ((depth_oj_1>depth_oj_2)&&Ran3Arry[i]<=Ran3Arry[j])){
				dH_Oj_Ran3=dH_Oj_Ran3+1;
			}

			//dH(D1,Ran4)	

			if ((depth_le_1<=depth_le_2)&&Ran4Arry[i]>Ran4Arry[j]
					|| ((depth_le_1>depth_le_2)&&Ran4Arry[i]<=Ran4Arry[j])){
				dH_L_Ran4=dH_L_Ran4+1;
			}

			if ((depth_sph_1<=depth_sph_2)&&Ran4Arry[i]>Ran4Arry[j]
					|| ((depth_sph_1>depth_sph_2)&&Ran4Arry[i]<=Ran4Arry[j])){
				dH_Sph_Ran4=dH_Sph_Ran4+1;
			}

			if ((depth_si_1<=depth_si_2)&&Ran4Arry[i]>Ran4Arry[j]
					|| ((depth_si_1>depth_si_2)&&Ran4Arry[i]<=Ran4Arry[j])){
				dH_Si_Ran4=dH_Si_Ran4+1;
			}

			if ((depth_oj_1<=depth_oj_2)&&Ran4Arry[i]>Ran4Arry[j]
					|| ((depth_oj_1>depth_oj_2)&&Ran4Arry[i]<=Ran4Arry[j])){
				dH_Oj_Ran4=dH_Oj_Ran4+1;
			}


			//dH(Ran1,Ran2)
			//dH(Ran1,Ran3)
			//dH(Ran1,Ran4)
			//dH(Ran2,Ran3)
			//dH(Ran2,Ran4)
			//dH(Ran3,Ran4)

			if (Ran2Arry[i]<=Ran2Arry[j]&&Ran1Arry[i]>Ran1Arry[j]
					|| (Ran1Arry[i]<=Ran1Arry[j]&&Ran2Arry[i]>Ran2Arry[j])){
				dH_Ran1_Ran2=dH_Ran1_Ran2+1;
			}

			if (Ran3Arry[i]<=Ran3Arry[j]&&Ran1Arry[i]>Ran1Arry[j]
					|| (Ran1Arry[i]<=Ran1Arry[j]&&Ran3Arry[i]>Ran3Arry[j])){
				dH_Ran1_Ran3=dH_Ran1_Ran3+1;
			}

			if (Ran4Arry[i]<=Ran4Arry[j]&&Ran1Arry[i]>Ran1Arry[j]
					|| (Ran1Arry[i]<=Ran1Arry[j]&&Ran4Arry[i]>Ran4Arry[j])){
				dH_Ran1_Ran4=dH_Ran1_Ran4+1;
			}

			if (Ran2Arry[i]<=Ran2Arry[j]&&Ran3Arry[i]>Ran3Arry[j]
					|| (Ran3Arry[i]<=Ran3Arry[j]&&Ran2Arry[i]>Ran2Arry[j])){
				dH_Ran2_Ran3=dH_Ran2_Ran3+1;
			}


			if (Ran2Arry[i]<=Ran2Arry[j]&&Ran4Arry[i]>Ran4Arry[j]
					|| (Ran4Arry[i]<=Ran4Arry[j]&&Ran2Arry[i]>Ran2Arry[j])){
				dH_Ran2_Ran4=dH_Ran2_Ran4+1;
			}

			if (Ran3Arry[i]<=Ran3Arry[j]&&Ran4Arry[i]>Ran4Arry[j]
					|| (Ran4Arry[i]<=Ran4Arry[j]&&Ran3Arry[i]>Ran3Arry[j])){
				dH_Ran3_Ran4=dH_Ran3_Ran4+1;
			}
		}
		//long endTime = System.currentTimeMillis(); 
		//double duration = (double)(endTime - startTime);
		//System.out.println("Each Round: "+ duration);
		//startTime = endTime;
	}



	public double depth_le(ArrayList<vector> v, vector theta) {
		double LD_theta=0;
		for(int i=0; i < v.size() ;i++)
		{

			vector vectorMain=v.get(i);
			Double dmain=distance(vectorMain, theta);
			for(int j=0;j<i;j++)
			{
				vector vectorNotMain=v.get(j);
				Double dNotMain=distance(vectorNotMain, theta);
				//System.out.println(" Math.max(dmain, dNotMain) : "+Math.max(dmain, dNotMain)+ "<= distance(vectorMain, vectorNotMain) : " +distance(vectorMain, vectorNotMain));
				if(Math.max(dmain, dNotMain)<= (distance(vectorMain, vectorNotMain)))
				{
					LD_theta=LD_theta+1;
				}
			}
		}
		LD_theta=LD_theta/((v.size())*(v.size()-1)/2); //Normalizing
		return LD_theta;
	}


	public double depth_si(ArrayList<vector> v, vector theta) {
		double Simplicial_D_theta=0;
		for(int k=0;k< v.size();k++)
		{
			vector v1=v.get(k);
			for(int l=0;l<k;l++)
			{
				vector v2=v.get(l);
				for (int kk=0;kk<l;kk++)
				{
					vector v3=v.get(kk);
					double alpha = ((v2.getY() - v3.getY())*(theta.getX() - v3.getX()) + (v3.getX() - v2.getX())*(theta.getY() - v3.getY())) /
							((v2.getY() - v3.getY())*(v1.getX() - v3.getX()) + (v3.getX() - v2.getX())*(v1.getY() - v3.getY()));
					double beta = ((v3.getY() - v1.getY())*(theta.getX() - v3.getX()) + (v1.getX() - v3.getX())*(theta.getY() - v3.getY())) /
							((v2.getY() - v3.getY())*(v1.getX() - v3.getX()) + (v3.getX() - v2.getX())*(v1.getY() - v3.getY()));
					double gamma = 1 - alpha - beta;
					//System.out.println(alpha);
					//system.out.println(beta);
					//System.out.println(gamma);
					if (alpha>=0 && beta>=0 && gamma>=0)
					{
						Simplicial_D_theta=Simplicial_D_theta+1;
					}
				}
			}			
		}
		//Normalizing the depth
		double vsize=v.size();		
		Simplicial_D_theta=Simplicial_D_theta/((vsize*(vsize-1)*(vsize-2))/6);	    
		//Simplicial_D_theta=Simplicial_D_theta/(((v.size())*(v.size()-1)*(v.size()-2))/6); // in this form I got size overflow and multiplication of three positive number were negative !
		return Simplicial_D_theta;

	}

	public double depth_sph(ArrayList<vector> v, vector theta) {
		double Sph_D_theta=0;
		for(int i=0;i<= v.size()-1;i++)
		{

			vector vectorMain=v.get(i);
			//Double dmain=distance(vectorMain, theta);
			for(int j=0;j<i;j++)
			{
				vector vectorNotMain=v.get(j);
				vector medianvector_ij=new vector();
				medianvector_ij.setX((vectorMain.getX()+vectorNotMain.getX())/2);
				medianvector_ij.setY((vectorMain.getY()+vectorNotMain.getY())/2);

				//Double dNotMain=distance(vectorNotMain, theta);
				//System.out.println(" Math.max(dmain, dNotMain) : "+Math.max(dmain, dNotMain)+ "<= distance(vectorMain, vectorNotMain) : " +distance(vectorMain, vectorNotMain));
				if(distance(medianvector_ij, theta)<= ((distance(vectorMain, vectorNotMain))/2))
				{
					Sph_D_theta=Sph_D_theta+1;
				}
			}	

		}
		Sph_D_theta=Sph_D_theta/((v.size())*(v.size()-1)/2); //Normalizing
		return Sph_D_theta;

	}

	public double depth_oj(ArrayList<vector> v, vector theta) {
		double OjaD_theta=0;
		for(int i=0;i<= v.size()-1;i++)
		{

			vector v1=v.get(i);
			for(int j=0;j<i;j++)
			{
				vector v2=v.get(j);
				double area=0.5*Math.abs(((v2.getX()*theta.getY())-(theta.getX()*v2.getY()))-((v1.getX()*theta.getY())-(theta.getX()*v1.getY()))+((v2.getY()*v1.getX())-(v2.getX()*v1.getY())));
				//System.out.println(area);		
				OjaD_theta=OjaD_theta+area;
			}	

		}

		OjaD_theta=1/OjaD_theta; // inverse the oja depth to normalized it and now the bigger oja depth means 
		return OjaD_theta;       // the more central. (we did that to be able to compare different data depths)
	}

	public Double distance(vector v1, vector v2)
	{
		return Math.sqrt(Math.pow(v1.getX()-v2.getX(), 2)+Math.pow(v1.getY()-v2.getY(), 2));

	}
	public void dataFile() {
		File file = new File("C:/Users/user/Desktop/Randomly generated data_matlab_2/Dataset.txt");
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


	public  void dataTest() {
		File file = new File("C:/Users/user/Desktop/Randomly generated data_matlab_2/Theta.txt");
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
