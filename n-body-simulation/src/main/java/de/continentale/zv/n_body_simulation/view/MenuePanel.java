package de.continentale.zv.n_body_simulation.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import de.continentale.zv.n_body_simulation.controller.ButtonController;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class MenuePanel extends JPanel
{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  private static final int BREITE = 1000;
  private static final int HOEHE = 1000;
  private static final Dimension FRAME_GROESSE = new Dimension(BREITE, HOEHE);
  JButton szenario1;
  JButton szenario2;
  JButton szenario3;
  JButton szenario4;

  /**
   * MenuePanel Konstruktor.
   *
   */
  public MenuePanel()
  {
    this.setFocusable(true);
    this.setMaximumSize(FRAME_GROESSE);
    this.setBackground(Color.decode("#000000"));
    szenario1 = new JButton("Figure-Eight");
    szenario2 = new JButton("Sonnensystem");
    szenario3 = new JButton("Binary-Star System");
    szenario4 = new JButton("Chaos Dreieck");

    Dimension buttonGroesse = new Dimension(125, 100);
    szenario1.setPreferredSize(buttonGroesse);
    szenario2.setPreferredSize(buttonGroesse);
    szenario3.setPreferredSize(buttonGroesse);
    szenario4.setPreferredSize(buttonGroesse);

    Border keinRand = BorderFactory.createEmptyBorder();
    szenario1.setBorder(keinRand);
    szenario2.setBorder(keinRand);
    szenario3.setBorder(keinRand);
    szenario4.setBorder(keinRand);

    szenario1.setBackground(Color.decode("#131313"));
    szenario2.setBackground(Color.decode("#131313"));
    szenario3.setBackground(Color.decode("#131313"));
    szenario4.setBackground(Color.decode("#131313"));
    szenario1.setForeground(Color.WHITE);
    szenario2.setForeground(Color.WHITE);
    szenario3.setForeground(Color.WHITE);
    szenario4.setForeground(Color.WHITE);

    szenario1.setActionCommand("Figure-Eight");
    szenario2.setActionCommand("Sonnensystem");
    szenario3.setActionCommand("Binary-Star System");
    szenario4.setActionCommand("Chaos Dreieck");

    this.add(szenario1);
    this.add(szenario2);
    this.add(szenario3);
    this.add(szenario4);
  }

  /**
   * @param buttonController
   */
  public void registerListener(ButtonController buttonController)
  {
    Component[] components = this.getComponents();
    for (Component component : components)
    {
      if (component instanceof JButton)
      {
        JButton button = (JButton) component;
        button.addActionListener(buttonController);
        button.addMouseListener(buttonController);
      }
    }
  }
}
