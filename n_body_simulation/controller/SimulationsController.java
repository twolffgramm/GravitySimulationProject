package de.continentale.zv.n_body_simulation.controller;

import de.continentale.zv.n_body_simulation.model.SimulationsModel;
import de.continentale.zv.n_body_simulation.view.SimulationsView;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class SimulationsController
{
  SimulationsModel simulationsModel;
  SimulationsView simulationsView;
  RepaintController repaintController;
  PositionsController positionsController;

  /**
   * SimulationsController Konstruktor.
   *
   * @param simulationsModel
   * @param simulationsView
   */
  public SimulationsController(SimulationsModel simulationsModel, SimulationsView simulationsView)
  {
    this.simulationsModel = simulationsModel;
    this.simulationsView = simulationsView;
    repaintController = new RepaintController(simulationsView);
    positionsController = new PositionsController(simulationsModel);
  }
}
