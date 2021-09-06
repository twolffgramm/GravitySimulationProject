package de.continentale.zv.n_body_simulation.controller;

import java.util.concurrent.TimeUnit;

import de.continentale.zv.n_body_simulation.view.SimulationsView;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class RepaintController implements Runnable
{
  SimulationsView simulationsView;

  /**
   * RepaintController Konstruktor.
   * 
   * @param simulationsView
   *
   */
  public RepaintController(SimulationsView simulationsView)
  {
    this.simulationsView = simulationsView;
  }

  @Override
  public void run()
  {
    // int t = 0;
    // int tMax = 24 * dt * 31 * 6;

    while (true)
    {
      try
      {
        int framesProSekunde = 60;
        int factor = 1000000;
        long timeout = 1000 / framesProSekunde * factor;
        TimeUnit.NANOSECONDS.sleep(timeout);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

      // System.out.println("repainted");
      simulationsView.repaint();
    }
  }
}