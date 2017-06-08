import java.rmi.*;

public class IntegralClient
{
	public static void main(String[] args) 
	{
		try 
		{
			IntegralComputeEngineInterface integralCounter = (IntegralComputeEngineInterface) Naming.lookup("rmi://localhost/integral");
			
			double result = integralCounter.computeIntegral(new Function(), -10, 10);
			
			System.out.println("Result is: " + result);
		} 
		catch (Exception e) 
		{System.out.println("IntegralClient exception: " + e);}
	}
}
