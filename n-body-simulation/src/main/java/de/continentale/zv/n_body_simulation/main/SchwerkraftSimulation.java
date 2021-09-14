package de.continentale.zv.n_body_simulation.main;

import de.continentale.zv.n_body_simulation.controller.ButtonController;
import de.continentale.zv.n_body_simulation.controller.SimulationsController;
import de.continentale.zv.n_body_simulation.controller.InteraktionsController;
import de.continentale.zv.n_body_simulation.model.SimulationsModel;
import de.continentale.zv.n_body_simulation.model.Szenario;
import de.continentale.zv.n_body_simulation.view.SimulationsView;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class SchwerkraftSimulation
{

  /**
   * @param args .
   */
  public static void main(String[] args)
  {
    SimulationsModel simulationsModel = new SimulationsModel(new Szenario(1), 1);
    SimulationsView simulationsView = new SimulationsView(simulationsModel);
    SimulationsController simulationsController =
        new SimulationsController(simulationsModel, simulationsView);
    ButtonController buttonController = new ButtonController(simulationsController);
    InteraktionsController simulationsInteraktionsController =
        new InteraktionsController(simulationsController);

    simulationsView.registerListener(buttonController, simulationsInteraktionsController);
  }
}
