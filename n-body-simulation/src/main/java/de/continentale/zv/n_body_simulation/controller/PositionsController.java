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

  /**
   * PositionsController Konstruktor.
   *
   * @param simulationsModel
   * @param simulationsView
   */
  public PositionsController(SimulationsModel simulationsModel, SimulationsView simulationsView)
  {
    this.simulationsModel = simulationsModel;
    this.simulationsView = simulationsView;
  }

  void aktualisierePlaneten()
  {

    for (int i = 0; i <= simulationsModel.getPlaneten()
        .size() - 1; i++)
    {
      Vector2D kraft = berechneKraft(i);
      if (simulationsView.getRepulsion())
      {
        Vector2D repulsion = berechneRepulsion(i);
        kraft = kraft.add(repulsion.multiply(1E22));
      }
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

  Vector2D berechneRepulsion(int aktuellerPlanet)
  {
    Point linksOben = simulationsView.getLinksOben();

    Map<String, Vector2D> abstaende = new HashMap<>();
    abstaende.put("abstandOben", new Vector2D(0, simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getPosition()
        .getY() / simulationsModel.getZoomFaktor() - linksOben.y));
    abstaende.put("abstandUnten", new Vector2D(0, simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getPosition()
        .getY() / simulationsModel.getZoomFaktor() - linksOben.y - 950));
    abstaende.put("abstandLinks", new Vector2D(simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getPosition()
        .getX() / simulationsModel.getZoomFaktor() - linksOben.x, 0));
    abstaende.put("abstandRechts", new Vector2D(simulationsModel.getPlaneten()
        .get(aktuellerPlanet)
        .getPosition()
        .getX() / simulationsModel.getZoomFaktor() - linksOben.x - 1000, 0));

    Object[] vectoren = abstaende.values()
        .toArray();
    double[] magnituden = new double[4];
    for (int i = 0; i < vectoren.length; i++)
    {
      magnituden[i] = Vector2D.magnitude((Vector2D) vectoren[i]);
    }
    Arrays.sort(magnituden);
    double min = magnituden[0];

    String which = "";
    for (Entry<String, Vector2D> entry : abstaende.entrySet())
    {
      if (Vector2D.magnitude(entry.getValue()) == min)
      {
        which = entry.getKey();
      }
    }

    Vector2D repulsionsRichtung = abstaende.get(which);
    return repulsionsRichtung;
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