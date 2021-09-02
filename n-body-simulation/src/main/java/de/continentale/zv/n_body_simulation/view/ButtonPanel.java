package de.continentale.zv.n_body_simulation.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

import de.continentale.zv.n_body_simulation.controller.ButtonController;
import de.continentale.zv.n_body_simulation.model.SimulationsModel;

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
  private static final int DT_MIN = 0;
  private static final int DT_MAX = 100000;
  SimulationsModel simulationsModel;
  JButton szenarien;
  JButton start;
  JButton pause;
  JButton zuruecksetzen;
  JPanel platzhalter;
  JPanel platzhalter2;
  JSlider animationsGeschwindigkeit;
  JLabel animationsGeschwindigkeitLabel;

  /**
   * ButtonPanel Konstruktor.
   * 
   * @param simulationsModel
   *
   */
  public ButtonPanel(SimulationsModel simulationsModel)
  {
    this.simulationsModel = simulationsModel;
    this.setFocusable(true);
    this.setPreferredSize(FRAME_GROESSE);
    this.setBackground(Color.decode("#1F1F1F"));
    platzhalter = new JPanel();
    platzhalter.setPreferredSize(new Dimension(355, 50));
    platzhalter.setBackground(Color.decode("#1F1F1F"));
    platzhalter2 = new JPanel();
    platzhalter2.setPreferredSize(new Dimension(405, 50));
    platzhalter2.setBackground(Color.decode("#1F1F1F"));
    animationsGeschwindigkeit =
        new JSlider(JSlider.HORIZONTAL, DT_MIN, DT_MAX, this.simulationsModel.getDt());
    animationsGeschwindigkeit.setPreferredSize(new Dimension(300, 20));
    animationsGeschwindigkeit.setBackground(Color.decode("#1F1F1F"));
    animationsGeschwindigkeitLabel =
        new JLabel(this.simulationsModel.getAnimationsGeschwindigkeitString());
    animationsGeschwindigkeitLabel.setForeground(Color.WHITE);

    try
    {
      Image szenarienIcon = ImageIO.read(getClass().getResource("/BurgerMenu.png"));
      Image startIcon = ImageIO.read(getClass().getResource("/Play.png"));
      Image pauseIcon = ImageIO.read(getClass().getResource("/Pause.png"));
      Image zuruecksetzenIcon = ImageIO.read(getClass().getResource("/Reset.png"));
      szenarienIcon = szenarienIcon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      startIcon = startIcon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      pauseIcon = pauseIcon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      zuruecksetzenIcon = zuruecksetzenIcon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      szenarien = new JButton(new ImageIcon(szenarienIcon));
      start = new JButton(new ImageIcon(startIcon));
      pause = new JButton(new ImageIcon(pauseIcon));
      zuruecksetzen = new JButton(new ImageIcon(zuruecksetzenIcon));
    }
    catch (Exception ex)
    {
      System.out.println(ex);
    }

    szenarien.setPreferredSize(new Dimension(50, 50));
    start.setPreferredSize(new Dimension(50, 50));
    pause.setPreferredSize(new Dimension(50, 50));
    zuruecksetzen.setPreferredSize(new Dimension(50, 50));

    Border keinRand = BorderFactory.createEmptyBorder();
    szenarien.setBorder(keinRand);
    start.setBorder(keinRand);
    pause.setBorder(keinRand);
    zuruecksetzen.setBorder(keinRand);

    szenarien.setBackground(Color.decode("#131313"));
    start.setBackground(Color.decode("#131313"));
    pause.setBackground(Color.decode("#131313"));
    zuruecksetzen.setBackground(Color.decode("#131313"));

    szenarien.setActionCommand("szenarien");
    start.setActionCommand("start");
    pause.setActionCommand("pause");
    zuruecksetzen.setActionCommand("zuruecksetzen");

    this.add(szenarien);
    this.add(platzhalter);
    this.add(start);
    this.add(pause);
    this.add(zuruecksetzen);
    this.add(platzhalter2);
    this.platzhalter2.add(animationsGeschwindigkeit);
    this.platzhalter2.add(animationsGeschwindigkeitLabel);

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
      animationsGeschwindigkeit.addChangeListener(buttonController);
    }
  }

  /**
   * 
   */
  public void sliderZuruecksetzen()
  {
    this.animationsGeschwindigkeit.setValue(this.simulationsModel.getDt());
  }

  /**
   * 
   */
  public void updateSliderLabel()
  {
    animationsGeschwindigkeitLabel
        .setText(this.simulationsModel.getAnimationsGeschwindigkeitString());
  }

}
