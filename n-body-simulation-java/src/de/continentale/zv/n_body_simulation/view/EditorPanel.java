package de.continentale.zv.n_body_simulation.view;

import java.awt.Point;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import de.continentale.zv.n_body_simulation.controller.ButtonController;
import de.continentale.zv.n_body_simulation.controller.InteraktionsController;
import de.continentale.zv.n_body_simulation.model.SimulationsModel;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class EditorPanel extends JPanel
{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  EditorEinstellungsPanel einstellungsPanel;
  SimulationsPanel simulationsPanel;
  ButtonPanel buttonPanel;

  /**
   * EditorPanel Konstruktor.
   *
   * @param simulationsModel .
   * @param breite .
   * @param hoehe .
   */
  public EditorPanel(SimulationsModel simulationsModel, int breite, int hoehe)
  {
    this.setFocusable(true);

    this.buttonPanel = new ButtonPanel(simulationsModel);
    this.simulationsPanel = new SimulationsPanel(simulationsModel, breite, hoehe);
    this.einstellungsPanel = new EditorEinstellungsPanel(simulationsModel);

    GroupLayout groupLayout = new GroupLayout(this);
    this.setLayout(groupLayout);

    // this.add(buttonPanel);
    // this.add(simulationsPanel);

    groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
        .addComponent(buttonPanel)
        .addComponent(einstellungsPanel)
        .addComponent(simulationsPanel));
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
        .addComponent(buttonPanel)
        .addComponent(einstellungsPanel)
        .addComponent(simulationsPanel));
    
//    einstellungsPanel.setVisible(false);

  }

  void paint()
  {
    simulationsPanel.repaint();
  }

  /**
   * 
   */
  public void zuruecksetzen()
  {
    buttonPanel.sliderZuruecksetzen();
    simulationsPanel.zuruecksetzen();
    einstellungsPanel.zuruecksetzen();
  }

  /**
   * @param differenz .
   */
  public void updateUrsrpung(Point differenz)
  {
    simulationsPanel.updateUrsprung(differenz);
  }

  /**
   * @param buttonController .
   * @param interaktionsController .
   */
  public void registerListener(ButtonController buttonController,
      InteraktionsController interaktionsController)
  {
    buttonPanel.registerListener(buttonController);
    einstellungsPanel.registerListener(buttonController);
    simulationsPanel.registerListener(interaktionsController);
  }

  /**
   * 
   */
  public void updateSliderLabel()
  {
    einstellungsPanel.updateSlider(0);
    buttonPanel.updateSliderLabel();
  }
  
  public void updateListePlaneten()
  {
  	buttonPanel.updateListePlaneten();
  }

  /**
   * 
   */
  public void erstelleSlider()
  {
//    einstellungsPanel.erstelleSlider();
  }

  /**
   * @param mausGedrueckt .
   * @param koordinatenMausGedrueckt .
   */
  public void setMausGedrueckt(boolean mausGedrueckt, Point koordinatenMausGedrueckt)
  {
    simulationsPanel.setMausGedrueckt(mausGedrueckt, koordinatenMausGedrueckt);
  }

  /**
   * @param geschwindigkeitsLinie .
   */
  public void setGeschwindigkeitsLinie(Point geschwindigkeitsLinie)
  {
    simulationsPanel.setGeschwindigkeitsLinie(geschwindigkeitsLinie);
  }

  /**
   * @return .
   */
  public Point getLinksOben()
  {
    return simulationsPanel.getLinksOben();
  }

//  /**
//   * @return .
//   */
//  public boolean getRepulsion()
//  {
//    return buttonPanel.getRepulsion();
//  }

  /**
   * @return .
   */
  public Point getUrsprung()
  {
    return simulationsPanel.getUrsprung();
  }

}