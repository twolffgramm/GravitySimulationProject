package de.continentale.zv.n_body_simulation.controller;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import de.continentale.zv.n_body_simulation.model.SimulationsModel;
import de.continentale.zv.n_body_simulation.model.Vector2D;

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

  /**
   * PositionsController Konstruktor.
   *
   * @param simulationsModel
   */
  public PositionsController(SimulationsModel simulationsModel)
  {
    this.simulationsModel = simulationsModel;
  }

  void aktualisierePlaneten()
  {

    for (int i = 0; i <= simulationsModel.getPlaneten()
        .size() - 1; i++)
    {
      Vector2D kraft = berechneKraft(i);
      kraftAnwenden(kraft, i);
      aktualisierePosition(i);
      speichereVorherigePosition(i);
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

    for (int i = 0; i <= simulationsModel.getPlaneten()
        .size() - 1; i++)
    {
      if (i == aktuellerPlanet)
      {
        continue;
      }

      kraftRichtung = simulationsModel.getPlaneten()
          .get(i)
          .getPosition()
          .subtract(simulationsModel.getPlaneten()
              .get(aktuellerPlanet)
              .getPosition());
      distanz = Vector2D.magnitude(kraftRichtung);
      distanzSq = distanz * distanz;
      kraft = G * simulationsModel.getPlaneten()
          .get(aktuellerPlanet)
          .getMasse()
          * simulationsModel.getPlaneten()
              .get(i)
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
    Vector2D neuerImpuls = simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getImpuls()
        .add(insgKraft.multiply(simulationsModel.getDt()));
    simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .setImpuls(neuerImpuls);
  }

  void aktualisierePosition(int aktuellerPlanet)
  {
    Vector2D neueGeschwindigkeit = simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getImpuls()
        .multiply(1 / simulationsModel.getPlaneten()
            .get(aktuellerPlanet)
            .getMasse());
    simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .setGeschwindigkeit(neueGeschwindigkeit);

    Vector2D neuePosition = simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getPosition()
        .add(simulationsModel.getPlaneten()
            .get(aktuellerPlanet)
            .getGeschwindigkeit()
            .multiply(simulationsModel.getDt()));
    simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .setPosition(neuePosition);
  }

  void speichereVorherigePosition(int aktuellerPlanet)
  {
    ArrayList<Vector2D> vorherigePositionen = this.simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getVorherigePositionen();
    if (vorherigePositionen.size() == 1000)
    {
      vorherigePositionen.remove(0);
    }
    Vector2D aktuellePosition = this.simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getPosition();
    vorherigePositionen.add(aktuellePosition);
    this.simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .setVorherigePositionen(vorherigePositionen);

    // if (aktuellerPlanet == 3)
    // {
    // System.out.println(vorherigePositionen.get(vorherigePositionen.size() - 1)
    // .toString() + " --> 1.");
    // System.out.println(this.simulationsModel.getPlaneten()
    // .get(aktuellerPlanet)
    // .getVorherigePositionen()
    // .get(this.simulationsModel.getPlaneten()
    // .get(aktuellerPlanet)
    // .getVorherigePositionen()
    // .size() - 1)
    // .toString() + " --> aus pos.Controller");
    // }
  }

  @SuppressWarnings("unused")
  void berechneCOM()
  {
    double insgMasse = 0;
    Vector2D position;
    double masse;
    double insgImpuls = 0;

    simulationsModel.setCOM(simulationsModel.getCOM()
        .multiply(0));

    for (int i = 0; i <= simulationsModel.getPlaneten()
        .size() - 1; i++)
    {
      masse = simulationsModel.getPlaneten()
          .get(i)
          .getMasse();
      insgMasse += masse;
      position = simulationsModel.getPlaneten()
          .get(i)
          .getPosition();
      insgImpuls += Vector2D.magnitude(simulationsModel.getPlaneten()
          .get(i)
          .getImpuls());
      simulationsModel.setCOM(simulationsModel.getCOM()
          .add(position.multiply(masse)));
    }

    simulationsModel.setCOM(simulationsModel.getCOM()
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
    int tMax = 24 * simulationsModel.getDt() * 31 * 6;

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

      aktualisierePlaneten();
      berechneCOM();

      t += simulationsModel.getDt();
    }
  }
}
