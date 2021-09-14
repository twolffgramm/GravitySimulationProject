package de.continentale.zv.n_body_simulation.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
  JButton editor;
  JPanel platzhalterLinks;
  JPanel platzhalterRechts;
  JSlider animationsGeschwindigkeitSlider;
  JSlider minimalerAbstandsSlider;
  JLabel animationsGeschwindigkeitLabel;
  JCheckBox repulsion;

  /**
   * ButtonPanel Konstruktor.
   * 
   * @param simulationsModel .
   *
   */
  public ButtonPanel(SimulationsModel simulationsModel)
  {
    this.simulationsModel = simulationsModel;
    this.setFocusable(true);
    this.setPreferredSize(FRAME_GROESSE);
    this.setBackground(Color.decode("#1F1F1F"));

    platzhalterLinks = new JPanel();
    platzhalterLinks.setPreferredSize(new Dimension(295, 50));
    platzhalterLinks.setBackground(Color.decode("#1F1F1F"));

    platzhalterRechts = new JPanel();
    platzhalterRechts.setPreferredSize(new Dimension(405, 50));
    platzhalterRechts.setBackground(Color.decode("#1F1F1F"));

    animationsGeschwindigkeitSlider =
        new JSlider(JSlider.HORIZONTAL, DT_MIN, DT_MAX, (int) this.simulationsModel.getDt());
    animationsGeschwindigkeitSlider.setPreferredSize(new Dimension(300, 20));
    animationsGeschwindigkeitSlider.setBackground(Color.decode("#1F1F1F"));
    animationsGeschwindigkeitSlider.setName("animationsGeschwindigkeit");

    animationsGeschwindigkeitLabel =
        new JLabel(this.simulationsModel.getAnimationsGeschwindigkeitString());
    animationsGeschwindigkeitLabel.setForeground(Color.WHITE);

    minimalerAbstandsSlider = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
    minimalerAbstandsSlider.setPreferredSize(new Dimension(200, 20));
    minimalerAbstandsSlider.setBackground(Color.decode("#1F1F1F"));
    minimalerAbstandsSlider.setName("minimalerAbstand");

    repulsion = new JCheckBox("Repulsion");
    repulsion.setBackground(Color.decode("#1F1F1F"));
    repulsion.setForeground(Color.WHITE);

    try
    {
      Image szenarienIcon = ImageIO.read(getClass().getResource("/BurgerMenu.png"));
      Image startIcon = ImageIO.read(getClass().getResource("/Play.png"));
      Image pauseIcon = ImageIO.read(getClass().getResource("/Pause.png"));
      Image zuruecksetzenIcon = ImageIO.read(getClass().getResource("/Reset.png"));
      Image editorIcon = ImageIO.read(getClass().getResource("/Edit.png"));
      szenarienIcon = szenarienIcon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      startIcon = startIcon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      pauseIcon = pauseIcon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      zuruecksetzenIcon = zuruecksetzenIcon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      editorIcon = editorIcon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);

      szenarien = new JButton(new ImageIcon(szenarienIcon));
      start = new JButton(new ImageIcon(startIcon));
      pause = new JButton(new ImageIcon(pauseIcon));
      zuruecksetzen = new JButton(new ImageIcon(zuruecksetzenIcon));
      editor = new JButton(new ImageIcon(editorIcon));
    }
    catch (Exception ex)
    {
      System.out.println(ex);
    }

    Dimension buttonGroesse = new Dimension(50, 50);
    szenarien.setPreferredSize(buttonGroesse);
    start.setPreferredSize(buttonGroesse);
    pause.setPreferredSize(buttonGroesse);
    zuruecksetzen.setPreferredSize(buttonGroesse);
    editor.setPreferredSize(buttonGroesse);

    Border keinRand = BorderFactory.createEmptyBorder();
    szenarien.setBorder(keinRand);
    start.setBorder(keinRand);
    pause.setBorder(keinRand);
    zuruecksetzen.setBorder(keinRand);
    editor.setBorder(keinRand);

    szenarien.setBackground(Color.decode("#131313"));
    start.setBackground(Color.decode("#131313"));
    pause.setBackground(Color.decode("#131313"));
    zuruecksetzen.setBackground(Color.decode("#131313"));
    editor.setBackground(Color.decode("#131313"));

    szenarien.setOpaque(true);
    start.setOpaque(true);
    pause.setOpaque(true);
    zuruecksetzen.setOpaque(true);
    editor.setOpaque(true);

    szenarien.setActionCommand("szenarien");
    start.setActionCommand("start");
    pause.setActionCommand("pause");
    zuruecksetzen.setActionCommand("zuruecksetzen");
    editor.setActionCommand("editor");

    this.add(szenarien);
    this.add(editor);
    this.add(platzhalterLinks);
    this.add(start);
    this.add(pause);
    this.add(zuruecksetzen);
    this.add(platzhalterRechts);

    this.platzhalterRechts.add(animationsGeschwindigkeitSlider);
    this.platzhalterRechts.add(animationsGeschwindigkeitLabel);

    this.platzhalterLinks.add(repulsion);
    this.platzhalterLinks.add(minimalerAbstandsSlider);
  }

  /**
   * @param buttonController .
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
    animationsGeschwindigkeitSlider.addChangeListener(buttonController);
    minimalerAbstandsSlider.addChangeListener(buttonController);
  }

  /**
   * 
   */
  public void sliderZuruecksetzen()
  {
    this.animationsGeschwindigkeitSlider.setValue((int) this.simulationsModel.getDt());
  }

  /**
   * 
   */
  public void updateSliderLabel()
  {
    animationsGeschwindigkeitLabel
        .setText(this.simulationsModel.getAnimationsGeschwindigkeitString());
  }

  /**
   * @return .
   */
  public boolean getRepulsion()
  {
    return repulsion.isSelected();
  }

}