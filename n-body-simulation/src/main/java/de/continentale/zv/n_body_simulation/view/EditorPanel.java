package de.continentale.zv.n_body_simulation.view;

import java.awt.Point;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import de.continentale.zv.n_body_simulation.controller.ButtonController;
import de.continentale.zv.n_body_simulation.controller.SimulationsInteraktionsController;
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

  JPanel sliderPanel;
  SimulationsPanel simulationsPanel;
  ButtonPanel buttonPanel;

  /**
   * EditorPanel Konstruktor.
   *
   * @param simulationsModel
   * @param buttonPanel
   * @param simulationsPanel
   * @param breite
   * @param hoehe
   */
  public EditorPanel(SimulationsModel simulationsModel, int breite, int hoehe)
  {
    this.setFocusable(true);

    this.buttonPanel = new ButtonPanel(simulationsModel);
    this.simulationsPanel = new SimulationsPanel(simulationsModel, breite, hoehe);
    this.sliderPanel = new JPanel();

    GroupLayout groupLayout = new GroupLayout(this);
    this.setLayout(groupLayout);

    this.add(buttonPanel);
    this.add(simulationsPanel);

    groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
        .addComponent(buttonPanel)
        .addComponent(simulationsPanel));
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
        .addComponent(buttonPanel)
        .addComponent(simulationsPanel));
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
  }

  /**
   * @param differenz
   */
  public void updateUrsrpung(Point differenz)
  {
    simulationsPanel.updateUrsprung(differenz);
  }

  /**
   * @param buttonController
   * @param simulationsInteraktionsController
   */
  public void registerListener(ButtonController buttonController,
      SimulationsInteraktionsController simulationsInteraktionsController)
  {
    buttonPanel.registerListener(buttonController);
    simulationsPanel.registerListener(simulationsInteraktionsController);
  }

}
