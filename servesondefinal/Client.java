package servesondefinal;

import java.io.*;
import java.net.*;
import java.rmi.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Client
    extends JFrame implements Serializable {
private String nom;
//Mot de passe d'un client utilis� par celui ci pour se connceter au serveur
private String pwd;
//Variable bool�ene qui indique l'�tat du client connect� ou pas
private boolean etat = false;
//L'objet distant du serveur
transient static Contrat J;
transient private boolean disconnected=false;
 /***********************************Les variables de l'interface graphique**************************/
  GestionBouton gB=new GestionBouton();
  //tableau utilis� par le JList
  Vector noms=new Vector();
  Font policeNormale, policeGras, policeItalique, policeGrasItalique;
  ButtonGroup groupeBRadio;
  JTextField champ = new JTextField();
  JLabel send_L = new JLabel();
  JButton send = new JButton();
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu File = new JMenu();
  JMenuItem Disconnect = new JMenuItem();
  JRadioButton grasItalique = new JRadioButton();
  JRadioButton italique = new JRadioButton();
  JRadioButton gras = new JRadioButton();
  JRadioButton normal = new JRadioButton();
  JMenu Help = new JMenu();
  JMenuItem a_propos = new JMenuItem();
  JScrollPane scrollPane1 = new JScrollPane();
  JScrollPane scrollPane2 = new JScrollPane();
  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTextArea msgs = new JTextArea();
  JList destinataires = new JList();
//______________________________________________Constructeur de la classe client
  public Client(String nom,String mdp) {
    this.nom = nom;
    this.pwd = mdp;
    this.etat = true;
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  public Client(String nom,String pwd,int i){
    this.nom=nom;
    this.pwd = pwd;
    this.etat = false;
  }
  public Client(){

  }
  public String get_nom() {
    return nom;
  }

  public String get_pwd() {
    return pwd;
  }

  public boolean get_state() {
    return etat;
  }

  public void set_state(boolean connected) {
    etat = connected;
  }
  public void aff_Cli() {
     JOptionPane.showMessageDialog(null, this.toString());
   }
   public String toString() {
     String ch="Non connecté";
     if(etat)
       ch="Connecté";
      return "Nom du client : " + nom + "\nmot de passe du client : " + pwd+" "+ch;
    }
//_____________________________________________M�thodes utilis�es pour la mise � jour de l'interface graphique du client

//********************************Utilis� pour ajouter un client connect� au JList
    public void ajout(String nom){
      System.out.println("ajout de "+ nom+" à la liste des clients connectés");
      noms.add(nom);
      destinataires.setListData(noms);
      this.destinataires.repaint();
    }
//********************************Utilis�e pour supprimer un client qui a d�connect� du JList
    public  void supp(String nom){
      if(noms.contains(nom))
        noms.remove(noms.indexOf(nom));
       destinataires.setListData(noms);
       this.destinataires.repaint();
   }
   public void update(String msg){
     msgs.append(msg+"\n");
   }
public void initialiser(){
    for (int i = 0; i < noms.size(); i++)
      noms.clear();
    destinataires.setListData(noms);
    this.destinataires.repaint();
}
  private void jbInit() throws Exception {
    GestionBoutonRadio gestionnaire = new GestionBoutonRadio();
    //Cr�ation des relations logiques entre les JRadioButton
    groupeBRadio = new ButtonGroup();
    File.setBackground(new Color(233, 234, 229));
    File.setHorizontalTextPosition(SwingConstants.LEFT);
    File.setIcon(new ImageIcon("C:/Documents and Settings/MZT/jbproject/Chat/classes/chat/Images/new1.png"));
    Help.setText("Aide");
    Help.setBackground(new Color(233, 234, 229));
    Help.setIcon(new ImageIcon("C:/Documents and Settings/MZT/jbproject/Chat/classes/chat/Images/help.png"));
    a_propos.setBackground(new Color(233, 234, 229));
    a_propos.setText("A propos du logiciel...");
    Disconnect.addActionListener(new Client_Disconnect_actionAdapter(this));
    jScrollPane1.setBounds(new Rectangle(159, 39, 270, 196));
    jScrollPane2.setBounds(new Rectangle(246, 334, 187, 139));
    destinataires = new JList(noms);
    destinataires.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    destinataires.setToolTipText("La liste des clients connectés");
    destinataires.setVisibleRowCount(5);
    msgs.setEditable(false);
    msgs.setText("");
    msgs.setToolTipText("La liste des messages envoyés et reçues");
    this.getContentPane().setBackground(new Color(233, 234, 229));
    this.setLocale(java.util.Locale.getDefault());
    jMenuBar1.setBackground(new Color(233, 234, 229));
    italique.setBackground(new Color(233, 234, 229));
    grasItalique.setBackground(new Color(233, 234, 229));
    gras.setBackground(new Color(233, 234, 229));
    normal.setBackground(new Color(233, 234, 229));
    send.setBackground(new Color(239, 240, 234));
    Disconnect.setBackground(new Color(233, 234, 229));
    groupeBRadio.add(grasItalique);
    groupeBRadio.add(gras);
    groupeBRadio.add(italique);
    groupeBRadio.add(normal);
    //Cr�ation des objets de la police
    policeNormale = new Font("serif", Font.PLAIN, 14);
    policeGras = new Font("serif", Font.BOLD, 14);
    policeItalique = new Font("serif", Font.ITALIC, 14);
    policeGrasItalique = new Font("serif", Font.BOLD + Font.ITALIC, 14);
    grasItalique.addItemListener(gestionnaire);
    gras.addItemListener(gestionnaire);
    italique.addItemListener(gestionnaire);
    normal.addItemListener(gestionnaire);
    this.getContentPane().setLayout(null);
    champ.setText("");
    champ.setBounds(new Rectangle(158, 248, 271, 26));
    send.addActionListener(gB);
    send_L.setFont(new java.awt.Font("Dialog", 1, 14));
    send_L.setText("Envoyer � :");
    send_L.setBounds(new Rectangle(161, 362, 91, 30));
    send.setBounds(new Rectangle(36, 363, 114, 27));
    send.setText("Envoyer");
    send.setIcon(new ImageIcon("C:/Documents and Settings/MZT/jbproject/Chat/classes/chat/Images/send.gif"));
    this.setJMenuBar(jMenuBar1);
    //this.setTitle("JChat V1.0");
    File.setText("Fichier");
    Disconnect.setText("Déconnexion");
    grasItalique.setText("Gras/Italique");
    grasItalique.setBounds(new Rectangle(322, 282, 100, 21));
    italique.setText("Italique");
    italique.setBounds(new Rectangle(210, 282, 102, 21));
    gras.setText("Gras");
    gras.setBounds(new Rectangle(112, 282, 94, 21));
    normal.setText("Normal");
    normal.setBounds(new Rectangle(6, 282, 101, 21));
    this.getContentPane().add(champ, null);
    this.getContentPane().add(grasItalique, null);
    this.getContentPane().add(italique, null);
    this.getContentPane().add(normal, null);
    this.getContentPane().add(gras, null);
    this.getContentPane().add(scrollPane1);
    this.getContentPane().add(scrollPane2);
    this.getContentPane().add(send_L, null);
    this.getContentPane().add(send, null);
    this.getContentPane().add(jScrollPane1, null);
    jScrollPane1.getViewport().add(msgs, null);
    this.getContentPane().add(jScrollPane2, null);
    jMenuBar1.add(File);
    jMenuBar1.add(Help);
    File.add(Disconnect);
    Help.add(a_propos);
    jScrollPane2.getViewport().add(destinataires, null);
    this.setSize(new Dimension(457, 565));
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
//_________________________________Classe interne utilis�e pour la gestion des �venements engendr�s par les boutons radios
        private class GestionBoutonRadio
            implements ItemListener {
          public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == grasItalique)
              champ.setFont(policeGrasItalique);
            else
            if (e.getSource() == italique)
              champ.setFont(policeItalique);
            else
            if (e.getSource() == gras)
              champ.setFont(policeGras);
            else
            if (e.getSource() == normal) {
        champ.setFont(policeNormale);
      }
    }
            }
//_________________________________Pour la gestion de la fermeture de la fenetre
              protected void processWindowEvent(WindowEvent e) {
                if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                  if(User.srv_state){
                    try {
          J = (Contrat) Naming.lookup("rmi://" + User.srv_Adr + ":1099/JChat");
        }
        catch (RemoteException ex1) {
          System.out.println(ex1);
        }
        catch (MalformedURLException ex1) {
           System.out.println(ex1);
        }
        catch (NotBoundException ex1) {
           System.out.println(ex1);
        }
        try {
          J.disconnect(nom);
        }
        catch (RemoteException ex) {
        }
    }
    //__________________________________________Suppression de l'objet distant du client du registre RMI
   if(!disconnected) {
     try {
       System.out.println("Suppression de l'objet distant " + "//" +
                          User.adr + ":" + User.num_port +
                          "/" + nom);
       Naming.unbind("//" + User.adr + ":" + User.num_port +
                     "/" + nom);
     }
     catch (MalformedURLException ex2) {
       System.out.println(ex2);
     }
     catch (NotBoundException ex2) {
       System.out.println(ex2);
     }
     catch (RemoteException ex2) {
       System.out.println(ex2);
     }
   }
                  System.exit(0);
                }
              }
//_________________________________Classe interne utilis�e pour la gestion des �venements engendr�s par le bouton Envoyer
 private class GestionBouton implements ActionListener{
    public void actionPerformed(ActionEvent e) {
       if (destinataires.getSelectedIndex() != -1) {
         try {
           J = (Contrat) Naming.lookup("rmi://" + User.srv_Adr + ":1099/JChat");
         }
         catch (RemoteException ex1) {
           System.out.println(ex1);
         }
         catch (MalformedURLException ex1) {
           System.out.println(ex1);
         }
         catch (NotBoundException ex1) {
           System.out.println(ex1);
         }
           Communication com = new Communication(nom,
                                                 (String) noms.get(
               destinataires.
               getSelectedIndex()),
                                                 champ.getText());
           try {
             J.envoyer(com);
             msgs.append(com.toString() + "\n");
           }
           catch (RemoteException ex) {
           }
       }
     }
    }
  synchronized void Disconnect_actionPerformed(ActionEvent e) {
//__________________________________________Suppression de l'objet distant du client du registre RMI
if(User.srv_state){
      try {
             J = (Contrat) Naming.lookup("rmi://" + User.srv_Adr + ":1099/JChat");
           }
           catch (RemoteException ex1) {
             System.out.println(ex1);
           }
           catch (MalformedURLException ex1) {
              System.out.println(ex1);
           }
           catch (NotBoundException ex1) {
              System.out.println(ex1);
           }
         try {
           J.disconnect(nom);
         }
         catch (RemoteException ex) {
         }
         try {
        Naming.unbind( "//"+User.adr+":"+User.num_port + "/" + nom);
     }
     catch (MalformedURLException ex) {
     }
     catch (NotBoundException ex) {
     }
     catch (RemoteException ex) {
     }
     initialiser();
     disconnected=true;
       }
       else
         JOptionPane.showMessageDialog(null,"Le serveur est injoignable pour le moment");
    }
     }
class Client_Disconnect_actionAdapter implements java.awt.event.ActionListener {
  Client adaptee;

  Client_Disconnect_actionAdapter(Client adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Disconnect_actionPerformed(e);
  }

}

