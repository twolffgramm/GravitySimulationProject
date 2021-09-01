package de.continentale.zv.n_body_simulation.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class ButtonPanel extends JPanel
{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  private static final int BREITE = 1000;
  private static final int HOEHE = 60;
  private static final Dimension FRAME_GROESSE = new Dimension(BREITE, HOEHE);
  JButton szenarien;
  JButton start;
  JButton pause;
  JButton zuruecksetzen;

  /**
   * ButtonPanel Konstruktor.
   *
   */
  public ButtonPanel()
  {
    this.setFocusable(true);
    this.setPreferredSize(FRAME_GROESSE);
    this.setBackground(Color.decode("#282828"));

    this.szenarien = new JButton("Menü");
    this.start = new JButton("Start");
    this.pause = new JButton("Pause");
    this.zuruecksetzen = new JButton("Zurück");

    this.add(this.szenarien);
    this.add(this.start);
    this.add(this.pause);
    this.add(this.zuruecksetzen);
  }
}
