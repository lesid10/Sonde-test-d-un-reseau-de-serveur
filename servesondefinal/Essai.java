package servesondefinal;

import java.awt.*;
import javax.swing.*;


public class Essai extends JFrame {
  static JProgressBar jProgressBar1 = new JProgressBar();
  public Essai() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setTitle("Connexion en cours...");
    this.getContentPane().setLayout(null);
    jProgressBar1.setBounds(new Rectangle(66, 40, 217, 22));
    this.getContentPane().setBackground(new Color(239, 240, 234));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setFont(new java.awt.Font("Dialog", 0, 12));
    this.getContentPane().add(jProgressBar1, null);
    this.setSize(new Dimension(352, 130));
  }
}
