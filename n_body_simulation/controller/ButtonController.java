package de.continentale.zv.n_body_simulation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class ButtonController implements ActionListener
{
  SimulationsController simulationsController;
  boolean vorherGedrueckt;

  /**
   * ButtonController Konstruktor.
   * 
   * @param simulationsController
   */
  public ButtonController(SimulationsController simulationsController)
  {
    this.simulationsController = simulationsController;
    this.vorherGedrueckt = false;
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @SuppressWarnings("deprecation")
  @Override
  public void actionPerformed(ActionEvent event)
  {
    String command = event.getActionCommand();
    if (command.equals("szenarien"))
    {

    }
    else if (command.equals("start"))
    {
      if (vorherGedrueckt == false)
      {
        simulationsController.positionsController.positionsThread.start();
        simulationsController.repaintController.repaintThread.start();
        vorherGedrueckt = true;
      }
      simulationsController.positionsController.positionsThread.resume();
      simulationsController.repaintController.repaintThread.resume();
    }
    else if (command.equals("pause"))
    {
      simulationsController.positionsController.positionsThread.suspend();
      simulationsController.repaintController.repaintThread.suspend();
    }
    else if (command.equals("zuruecksetzen"))
    {

    }
  }
}
