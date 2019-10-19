package servesondefinal;


import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Fenetre
    extends JPanel{
    private String image = "C:/Documents and Settings/MZT/jbproject/Chat/classes/chat/Images/cadre.png";
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(Toolkit.getDefaultToolkit().getImage(image),0,0,this);
    }
    public Fenetre(){
      super();
    }
}
