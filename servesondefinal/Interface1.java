package servesondefinal;

import java.rmi.*;

public interface Interface1 extends Remote {
  void newcliConnexion(String nom)throws RemoteException;
  void clidisconnect(String nom)throws RemoteException;
  void updatemsgs(Communication com)throws RemoteException;
  void close_notification()throws RemoteException;
  void start_notification()throws RemoteException;
}
