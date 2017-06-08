import java.rmi.*;
import java.rmi.server.*;

public class IntegralComputeEngine  extends UnicastRemoteObject implements IntegralComputeEngineInterface 
{
	private final double ACCURACY = 100;
	
	protected IntegralComputeEngine() throws RemoteException 
	{super();}

	@Override
	public double computeIntegral(FunctionInterface function, double from, double to) throws RemoteException 
	{
		double stepValue = (to - from) / ACCURACY;  
		double stepLength = Math.abs(stepValue);
		double result = 0.0; 
		
        for(int i = 0; i < ACCURACY; i++)
        {result += stepLength * function.valueIn(from + stepValue*(i + 0.5));}
        
        return result;
	}
}
