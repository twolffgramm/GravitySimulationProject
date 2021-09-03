package de.continentale.zv.n_body_simulation.model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class SimulationsModel
{
  ArrayList<Planet> planeten = new ArrayList<>();
  Vector2D massenSchwerpunkt = new Vector2D();
  int dt;
  double zoomFaktor;
  int aktuellesSzenario;
  Szenario szenario;

  /**
   * SimulationsModel Konstruktor.
   * 
   * @param szenario
   * @param aktuellesSzenario
   */
  public SimulationsModel(Szenario szenario, int aktuellesSzenario)
  {
    for (int i = 0; i <= szenario.positionen.length - 1; i++)
    {
      this.planeten.add(new Planet(szenario.positionen[i], szenario.geschwindigkeiten[i],
          szenario.massen[i], szenario.radii[i], szenario.farben[i]));
    }
    this.dt = szenario.dt;
    this.zoomFaktor = szenario.zoomFaktor;
    this.aktuellesSzenario = aktuellesSzenario;
  }

  /**
   * 
   */
  public void modelZuruecksetzen()
  {
    planeten.clear();
    szenario = new Szenario(aktuellesSzenario);
    for (int i = 0; i <= szenario.positionen.length - 1; i++)
    {
      planeten.add(new Planet(szenario.positionen[i], szenario.geschwindigkeiten[i],
          szenario.massen[i], szenario.radii[i], szenario.farben[i]));
    }
    this.dt = szenario.dt;
    this.zoomFaktor = szenario.zoomFaktor;
  }

  /**
   * @return .
   */
  public ArrayList<Planet> getPlaneten()
  {
    return this.planeten;
  }

  /**
   * @return .
   */
  public Vector2D getCOM()
  {
    return this.massenSchwerpunkt;
  }

  /**
   * @param massenSchwerpunkt
   */
  public void setCOM(Vector2D massenSchwerpunkt)
  {
    this.massenSchwerpunkt = massenSchwerpunkt;
  }

  /**
   * @return .
   */
  public int getDt()
  {
    return this.dt;
  }

  /**
   * @return .
   */
  public double getZoomFaktor()
  {
    // TODO Auto-generated method stub
    return this.zoomFaktor;
  }

  /**
   * @param aenderung
   */
  public void setZoomFaktor(double aenderung)
  {
    aenderung = aenderung * this.zoomFaktor / 10;
    this.zoomFaktor -= aenderung;
  }

  /**
   * @param aenderung
   */
  public void setRadius(double aenderung)
  {
    for (int i = 0; i < planeten.size(); i++)
    {
      double radius = planeten.get(i)
          .getRadius();
      radius = radius + aenderung / 2;
      planeten.get(i)
          .setRadius(radius);
    }
  }

  /**
   * @param dt
   */
  public void setDt(int dt)
  {
    this.dt = dt;
  }

  /**
   * @return .
   */
  public String getAnimationsGeschwindigkeitString()
  {
    double berechnungenProSekunde = dt * 1000;

    if (berechnungenProSekunde < 60)
    {
      return String.format("%.4f", berechnungenProSekunde) + " Sekunden / Sekunde";
    }
    berechnungenProSekunde /= 60;
    if (berechnungenProSekunde < 60)
    {
      return String.format("%.4f", berechnungenProSekunde) + " Minuten / Sekunde";
    }
    berechnungenProSekunde /= 60;
    if (berechnungenProSekunde < 24)
    {
      return String.format("%.4f", berechnungenProSekunde) + " Stunden / Sekunde";
    }
    berechnungenProSekunde /= 24;
    if (berechnungenProSekunde < 365)
    {
      return String.format("%.4f", berechnungenProSekunde) + " Tage / Sekunde";
    }
    berechnungenProSekunde /= 365;
    if (berechnungenProSekunde < 100)
    {
      return String.format("%.4f", berechnungenProSekunde) + " Jahre / Sekunde";
    }
    berechnungenProSekunde /= 100;
    return String.format("%.4f", berechnungenProSekunde) + " Jahrhunderte / Sekunde";
  }

  /**
   * @param klick
   * @param ursprung
   */
  public void planetHinzufuegen(Point klick, Point ursprung)
  {
    this.planeten.add(new Planet(
        new Vector2D((klick.x - ursprung.x) * zoomFaktor, (klick.y - ursprung.y) * zoomFaktor),
        new Vector2D(), 5E24));
  }

  /**
   * @param szenario
   */
  public void setAktuellesSzenario(int szenario)
  {
    this.aktuellesSzenario = szenario;
  }
}
