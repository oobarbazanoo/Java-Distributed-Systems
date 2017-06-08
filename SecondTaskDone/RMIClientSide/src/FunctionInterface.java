import java.rmi.*;

public interface FunctionInterface extends Remote
{
	public double valueIn(double x) throws RemoteException;
}
