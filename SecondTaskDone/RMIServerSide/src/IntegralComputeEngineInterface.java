import java.rmi.*;

public interface IntegralComputeEngineInterface extends Remote 
{
	public double computeIntegral(FunctionInterface function, double from, double to) throws RemoteException;
}
