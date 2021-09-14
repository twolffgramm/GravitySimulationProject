package de.continentale.zv.n_body_simulation.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Das Simulations-Model hält den gesamten aktuellen Stand der Simulation - eine
 * {@code ArrayList<Planet> planeten} & einen {@code Vector2D massenSchwerpunkt}, sowie weitere
 * Informationen für die Berechnung und Anzeige - den {@code int dt}, den {@code double zoomFaktor}
 * & den {@code int minimalerAbstand}.
 * <p>
 * Zusätzlich wird das aktuelle Szenario als {@code int aktuellesSzenario} gehalten.
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class SimulationsModel
{
  /**
   * hält alle Planeten der aktuellen Simulation.
   */
  ArrayList<Planet> planeten = new ArrayList<>();

  /**
   * hält den Massenschwerpunkt der aktuellen Simulation
   */
  Vector2D massenSchwerpunkt = new Vector2D();

  /**
   * beschreibt ein vielfaches (int) des Radius eines Planeten. Ist die Distanz zu einem anderen
   * Planeten geringer als dieses Vielfaches, wird die Gravitationskraft zu diesem nicht
   * berücksichtigt - siehe PositionsController.berechneGravitationskraft()
   */
  int minimalerAbstand;

  double dt;
  double zoomFaktor;

  // benötigt, um das SimulationsModel zurückzusetzen auf das aktuelle Szenario
  int aktuellesSzenario;
  Szenario szenario;

  /**
   * Konstruiert ein {@code SimulationsModel} und initialisiert dieses anhand eines
   * {@code Szenario szenario} und einem {@code int aktuellesSzenario}. Anhand des Szenarios werden
   * neue {@code Planet}-Objekte instanziiert und in {@code ArrayList<Planet> planeten} gespeichert,
   * sowie die delta-Zeit - dt - und der zoomFaktor übernommen.
   * <p>
   * der minimale Abstand zwischen zwei Planeten wird zu Beginn auf 0 festgelegt.
   * 
   * @param szenario {@code Szenario} - das Szenario mit dem das SimulationsModel gefüllt wird.
   * @param aktuellesSzenario {@code int} - die Nummer des aktuellen Szenarios.
   */
  public SimulationsModel(Szenario szenario, int aktuellesSzenario)
  {
    for (int i = 0; i <= szenario.positionen.length - 1; i++)
    {
      this.planeten.add(new Planet(szenario.getPositionen()[i], szenario.getGeschwindigkeiten()[i],
          szenario.getMassen()[i], szenario.getRadii()[i], szenario.getFarben()[i]));
    }
    this.dt = szenario.getDt();
    this.zoomFaktor = szenario.getZoomFaktor();
    this.minimalerAbstand = 0;
    this.aktuellesSzenario = aktuellesSzenario;
    this.szenario = new Szenario(aktuellesSzenario);
  }

  /**
   * Fügt zu dem bestehenden Model einen Planeten unter Angabe eines {@code Vector2D position} &
   * {@code Vector2D geschwindigkeit}.
   * <p>
   * Der {@code Vector2D position} beschreibt die Position im externen Koordinatensystem - Pixel im
   * Frame. Transformation in das Koordinatensystem des Models findet üder eine Multiplikation mit
   * dem aktuellen zoomFaktor statt. Der {@code Vector2D geschwindigkeit} wird mit einem Faktor von
   * 10 multipliziert um eine sinnvolle Interaktion mit der Maus zu gewährleisten.
   * <p>
   * Ein dynamisch hinzugefügter Planet hat die festgelegte Masse einer Erdmasse (5.972E24).
   * 
   * @param position {@code Vector2D} - Position des neuen Planeten im Koordinatensystem des JFrames
   *          (Pixel)
   * @param geschwindigkeit {@code Vector2D} - Geschwindigkeit des neuen Planeten. Wird
   *          verzehnfacht.
   */
  public void planetHinzufuegen(Vector2D position, Vector2D geschwindigkeit)
  {
    this.planeten
        .add(new Planet(position.multiply(zoomFaktor), geschwindigkeit.multiply(10), 5.972E24));
  }

  /**
   * Setzt das aktuelle Model zurück.
   * <p>
   * Löscht alle vorhanden Planeten und erstellt neue Planeten nach dem Preset des aktuellen
   * Szenarios
   */
  public void modelZuruecksetzen()
  {
    planeten.clear();
    szenario = new Szenario(aktuellesSzenario);
    for (int i = 0; i <= szenario.positionen.length - 1; i++)
    {
      planeten.add(new Planet(szenario.getPositionen()[i], szenario.getGeschwindigkeiten()[i],
          szenario.getMassen()[i], szenario.getRadii()[i], szenario.getFarben()[i]));
    }
    this.dt = szenario.getDt();
    this.zoomFaktor = szenario.getZoomFaktor();
  }

  /**
   * Liefert alle Planeten in dem aktuellen Model.
   * 
   * @return eine {@code ArrayList<Planet>}, alle aktuellen Planeten des Models.
   */
  public ArrayList<Planet> getPlaneten()
  {
    return this.planeten;
  }

  /**
   * Liefert den Massenschwerpunkt des aktuellen Systems.
   * 
   * @return ein {@code Vector2D}, der den aktuellen Massenschwerpunkt des Systems als Vektor
   *         darstellt.
   */
  public Vector2D getCOM()
  {
    return this.massenSchwerpunkt;
  }

  /**
   * Liefert den aktuellen diskreten Zeitsprung (delta-Zeit, dt) für die Simulation.
   * 
   * @return ein {@code double}, die delta-Zeit, dt.
   */
  public double getDt()
  {
    return this.dt;
  }

  /**
   * Liefert den aktuellen Zoomfaktor für die Darstellung der aktuellen Simulation.
   * 
   * @return ein {@code double}, den aktuellen Zoomfaktor.
   */
  public double getZoomFaktor()
  {
    return this.zoomFaktor;
  }

  /**
   * Liefert die maximale Masse aller Planeten im aktuellen System.
   * 
   * @return ein {@code double}, die maximale Masse.
   */
  public double getMaxMasse()
  {
    // (1) Array mit allen Massen erstellen. (2) Array sortieren. (3) letzten Eintrag im Array
    // ausgeben.
    // Wenn noch keine Planeten vorhanden (nur im Editor-Modus), setze maximale Masse auf eine
    // Erdmasse (~5E24).
    double[] massen = new double[planeten.size()];
    for (int i = 0; i < planeten.size(); i++)
    {
      massen[i] = planeten.get(i)
          .getMasse();
    }
    if (massen.length == 0)
    {
      massen[0] = 5E24;
    }
    Arrays.sort(massen);
    return massen[massen.length - 1];
  }

  /**
   * Liefert die Angabe der Simulationszeit pro Echtzeitsekunde (z.B. 1.598 Stunden / Sekunde) als
   * String für das SliderLabel des ButtonPanels.
   * 
   * @return ein {@code String}, die aktuelle Simulationszeit pro Echtzeitsekunde.
   */
  public String getAnimationsGeschwindigkeitString()
  {
    // ergibt sich aus dem PositionsController (Thread läuft 1000-mal pro Sekunde) und des aktuellen
    // diskreten Zeitsprung pro Berechnung.
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
   * Liefert den aktuellen minimalen Abstand zwischen Planeten, der für die Berechnung der
   * Positionen notwendig ist. Angabe als Ganzzahl-faktor des Radius.
   * 
   * @return ein {@code int}, der aktuelle Faktor.
   */
  public int getMinimalerAbstand()
  {
    return this.minimalerAbstand;
  }

  /**
   * Setzt den Massenschwerpunkt des aktuellen Systems.
   * 
   * @param massenSchwerpunkt {@code Vector2D}, der neue Massenschwerpunkt des Systems.
   * 
   */
  public void setCOM(Vector2D massenSchwerpunkt)
  {
    this.massenSchwerpunkt = massenSchwerpunkt;
  }

  /**
   * Setzt den diskreten Zeitsprung (delta-Zeit, dt) für die Simulation.
   * 
   * @param dt {@code int}, die neue delta-Zeit, dt.
   */
  public void setDt(int dt)
  {
    this.dt = dt;
  }

  /**
   * Setzt den Zoomfaktor für die aktuelle Darstellung der Simulation.
   * <p>
   * Das Setzen erfolgt mittels der Angabe einer "Änderung" die Verrechnet wird. Dies ist notwendig,
   * um die Mausradbewegung zu Verrechnen. die Änderung pro Mausradbewegung (+1 || -1) führt zu
   * einem 10%-igen Zoom.
   * 
   * @param aenderung {@code double}, die Mausradbewegung, +1 || -1.
   */
  public void setZoomFaktor(double aenderung)
  {
    aenderung = aenderung * this.zoomFaktor / 10;
    this.zoomFaktor += aenderung;
  }

  /**
   * Setzt die neuen Radii der Planeten.
   * <p>
   * Hier wird ebenfalls die Mausradbewegung (+1 || -1) verarbeitet. Die Änderung führt zu Änderung
   * des Radius von +0.5 oder -0.5 Pixel.
   * 
   * @param aenderung {@code double}, die Mausradbewegung, +1 || -1.
   */
  public void setRadius(double aenderung)
  {
    for (int i = 0; i < planeten.size(); i++)
    {
      double radius = planeten.get(i)
          .getRadius();
      radius = radius - aenderung / 2;
      planeten.get(i)
          .setRadius(radius);
    }
  }

  /**
   * Setzt die Kennung des neuen Szenarios des Models. Hiermit kann über die Buttons im Menü das
   * Szenario gewechselt werden.
   * 
   * @param szenario {@code int}, die Kennung des neuen Szenarios.
   */
  public void setAktuellesSzenario(int szenario)
  {
    this.aktuellesSzenario = szenario;
  }

  /**
   * Setzt den neuen aktuellen minimalen Abstand zwischen Planeten, der für die Berechnung der
   * Positionen notwendig ist. Angabe als Ganzzahl-faktor des Radius.
   * 
   * @param minimalerAbstand {@code int}, der neue Ganzzahl-faktor.
   */
  public void setMinimalerAbstand(int minimalerAbstand)
  {
    this.minimalerAbstand = minimalerAbstand;
  }

}