import java.rmi.*;

public class IntegralServer
{
	public static void main (String[] argv) 
	{
		 try 
		 {
			 Naming.rebind("rmi://localhost/integral", new IntegralComputeEngine());
			 
			 System.out.println("Integral Server is ready.");
		 }
		 catch (Exception e)
		 {System.out.println("Integral Server failed: " + e);}
	}
}
