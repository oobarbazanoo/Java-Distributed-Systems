import java.rmi.RemoteException;
import java.rmi.server.*;

public class Function extends UnicastRemoteObject implements FunctionInterface
{
	protected Function() throws RemoteException
	{super();}

	@Override
	public double valueIn(double x) throws RemoteException
	{return Math.sin(x)*Math.sin(x) + Math.cos(x)*Math.cos(x);}
	//-3-x : -6
	//Math.sin(x) : 0
	//2 : 4
	//1-x : 2
}
