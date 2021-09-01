package de.continentale.zv.n_body_simulation.controller;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import de.continentale.zv.n_body_simulation.model.Planet;
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
public class SimulationsController implements Runnable
{
  SimulationsModel simulationsModel;
  SimulationsView simulationsView;
  ArrayList<Planet> planeten;
  Thread simulationsThread;
  int dt;

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
    this.dt = this.simulationsModel.getDt();

    this.simulationsThread = new Thread(this);
    this.simulationsThread.start();
  }

  void aktualisierePlaneten()
  {

    for (int i = 0; i <= this.simulationsModel.getPlaneten()
        .size() - 1; i++)
    {
      this.planeten = this.simulationsModel.getPlaneten();
      Vector2D kraft = berechneKraft(i);
      kraftAnwenden(kraft, i);
      aktualisierePosition(i);
    }
  }

  Vector2D berechneKraft(int aktuellerPlanet)
  {
    ArrayList<Vector2D> kraefte = new ArrayList<>();
    Vector2D kraftRichtung;
    Vector2D insgKraft = new Vector2D();
    double distanz;
    double distanzSq;
    double kraft;
    // final double G = 1;
    final double G = 6.6743E-11;

    for (int i = 0; i <= this.planeten.size() - 1; i++)
    {
      if (i == aktuellerPlanet)
      {
        continue;
      }

      kraftRichtung = this.planeten.get(i)
          .getPosition()
          .subtract(this.planeten.get(aktuellerPlanet)
              .getPosition());
      distanz = Vector2D.magnitude(kraftRichtung);
      distanzSq = distanz * distanz;
      kraft = G * this.planeten.get(aktuellerPlanet)
          .getMasse()
          * this.planeten.get(i)
              .getMasse()
          / distanzSq;
      kraefte.add(kraftRichtung.setMagnitude(kraft));
    }

    for (int i = 0; i <= kraefte.size() - 1; i++)
    {
      insgKraft = insgKraft.add(kraefte.get(i));
    }

    return insgKraft;
  }

  void kraftAnwenden(Vector2D insgKraft, int aktuellerPlanet)
  {
    Vector2D neuerImpuls = this.planeten.get(aktuellerPlanet)
        .getImpuls()
        .add(insgKraft.multiply(this.dt));
    this.simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .setImpuls(neuerImpuls);
  }

  void aktualisierePosition(int aktuellerPlanet)
  {
    Vector2D neueGeschwindigkeit = this.simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getImpuls()
        .multiply(1 / this.simulationsModel.getPlaneten()
            .get(aktuellerPlanet)
            .getMasse());
    this.simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .setGeschwindigkeit(neueGeschwindigkeit);

    Vector2D neuePosition = this.simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getPosition()
        .add(this.simulationsModel.getPlaneten()
            .get(aktuellerPlanet)
            .getGeschwindigkeit()
            .multiply(this.dt));
    this.simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .setPosition(neuePosition);
  }

  @SuppressWarnings("unused")
  void berechneCOM()
  {
    double insgMasse = 0;
    Vector2D position;
    double masse;
    double insgImpuls = 0;

    for (int i = 0; i <= this.simulationsModel.getPlaneten()
        .size() - 1; i++)
    {
      masse = this.simulationsModel.getPlaneten()
          .get(i)
          .getMasse();
      insgMasse += masse;
      position = this.simulationsModel.getPlaneten()
          .get(i)
          .getPosition();
      insgImpuls += Vector2D.magnitude(this.simulationsModel.getPlaneten()
          .get(i)
          .getImpuls());
      this.simulationsModel.setCOM(this.simulationsModel.getCOM()
          .add(position.multiply(masse)));
    }
    this.simulationsModel.setCOM(this.simulationsModel.getCOM()
        .multiply(1 / insgMasse));
    // System.out.println(this.simulationsModel.getCOM()
    // .toString());
    // System.out.println(insgImpuls);
  }

  @SuppressWarnings("unused")
  @Override
  public void run()
  {
    int t = 0;
    int tMax = 24 * dt * 31 * 6;

    while (true)
    {
      try
      {
        int frames = 60;
        int factor = 1000000;
        long timeout = 1000 / frames * factor;
        TimeUnit.NANOSECONDS.sleep(timeout);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

      aktualisierePlaneten();
      berechneCOM();
      simulationsView.repaint();
      t += this.dt;
    }
  }
}
