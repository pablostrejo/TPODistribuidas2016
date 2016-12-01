package rmi;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MainServer {

	public static void main(String[] args) {		
		RemoteInterface remoteObject;
		try {
			LocateRegistry.createRegistry(1099);
			
			remoteObject = new RemoteObject();
			Naming.rebind("//localhost/TPODistribuidas", remoteObject);
			System.out.println("Se hizo el bind del Remote Object");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}