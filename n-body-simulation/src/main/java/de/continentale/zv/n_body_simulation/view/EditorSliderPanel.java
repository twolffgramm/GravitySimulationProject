package de.continentale.zv.n_body_simulation.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

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
public class EditorSliderPanel extends JPanel
{
  SimulationsModel simulationsModel;
  ButtonController buttonController;
  Graphics g;
  Image hintergrund;
  JSlider[] sliders;
  JLabel[] sliderLabels;
  int anzahlPlaneten;

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  EditorSliderPanel(SimulationsModel simulationsModel)
  {
    this.simulationsModel = simulationsModel;
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(1000, 60));
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.setBackground(Color.decode("#1F1F1F"));
  }

  /**
   * 
   */
  public void updateSliderLabel()
  {
    for (int i = 0; i < sliders.length; i++)
    {
      double aktuelleMasse = simulationsModel.getPlaneten()
          .get(i)
          .getMasse();
      double aktuellerFaktor = aktuelleMasse / 5.972E24;
      sliders[i].setValue((int) (aktuellerFaktor * 100));
      sliderLabels[i].setText(String.format("%.2f", aktuellerFaktor) + " Erdmassen");
    }
  }

  /**
   * 
   */
  public void erstelleSlider()
  {
    this.removeAll();
    anzahlPlaneten = simulationsModel.getPlaneten()
        .size();
    sliders = new JSlider[anzahlPlaneten];
    sliderLabels = new JLabel[anzahlPlaneten];

    for (int i = 0; i < anzahlPlaneten; i++)
    {
      sliders[i] = new JSlider(0, 500, 100);
      sliders[i].setPreferredSize(new Dimension(100, 30));
      sliders[i].setBackground(Color.decode("#111111"));
      sliders[i].setOpaque(true);
      sliders[i].setName(String.valueOf(i));
      sliderLabels[i] = new JLabel();
      sliderLabels[i].setBackground(Color.decode("#1F1F1F"));
      sliderLabels[i].setForeground(Color.WHITE);
      this.add(sliders[i]);
      this.add(sliderLabels[i]);
      this.revalidate();
      this.repaint();
    }
    registerListener(buttonController);
    updateSliderLabel();
  }

  /**
   * @param buttonController
   */
  public void registerListener(ButtonController buttonController)
  {
    this.buttonController = buttonController;
    Component[] components = this.getComponents();
    for (Component component : components)
    {
      if (component instanceof JSlider)
      {
        JSlider slider = (JSlider) component;
        slider.addChangeListener(buttonController);
      }
    }
  }

  /**
   * 
   */
  public void zuruecksetzen()
  {
    erstelleSlider();
  }
}