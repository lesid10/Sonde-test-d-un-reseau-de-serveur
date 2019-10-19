package servesondefinal;

import java.rmi.*;
public interface Contrat extends Remote {
  //Fonction qui sera appell�e par le client pour se connecter au serveur de chat
  //ajout du client concern�e � la liste des clients connect�s du serveur
  boolean connect(String nom,String mdp,String adr,int num_port)throws RemoteException;
  //Fonction qui sera appell�e par le client inscrit au service de chat pour envoyer un message au serveur
  //Fonction qui ajoute une communication � la liste des communications du serveur
  void envoyer(Communication c)throws RemoteException;
  //Fonction renvoyant une liste contenant les messages recus d'un client c
  //recherche dans la liste des communication les messages dont la destination est le client c et renvoie de ces communications dans une liste
  //Fonction permettant d'�liminer un client du service du chat
  //supprimer le client c de la liste des clients connect�s
  void disconnect(String nom)throws RemoteException;
  int getnbrcon() throws RemoteException;
  String []list_con()throws RemoteException;
  int get_num_port() throws RemoteException;
}
