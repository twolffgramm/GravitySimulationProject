package de.continentale.zv.n_body_simulation.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import de.continentale.zv.n_body_simulation.model.SimulationsModel;
import de.continentale.zv.n_body_simulation.model.Vector2D;
import de.continentale.zv.n_body_simulation.view.SimulationsView;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class PositionsController implements Runnable
{
  SimulationsModel simulationsModel;
  SimulationsView simulationsView;
  static final double G = 6.6743E-11;

  /**
   * PositionsController Konstruktor.
   *
   * @param simulationsModel .
   * @param simulationsView .
   */
  public PositionsController(SimulationsModel simulationsModel, SimulationsView simulationsView)
  {
    this.simulationsModel = simulationsModel;
    this.simulationsView = simulationsView;
  }

  @SuppressWarnings("unused")
  @Override
  public void run()
  {
    int t = 0;
    double tMax = 24 * simulationsModel.getDt() * 31 * 6;

    while (true)
    {
      try
      {
        int berechnungenProSekunde = 1000;
        int factor = 1000000;
        long timeout = 1000 / berechnungenProSekunde * factor;
        TimeUnit.NANOSECONDS.sleep(timeout);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

      simulationsModel.aktualisierePlaneten();
      simulationsModel.berechneCOM();
      simulationsModel.setPositionsThreadCounter();
      
      t += simulationsModel.getDt();
    }
  }
}