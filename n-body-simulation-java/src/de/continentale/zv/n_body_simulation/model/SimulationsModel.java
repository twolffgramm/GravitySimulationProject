package de.continentale.zv.n_body_simulation.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
public class SimulationsModel extends SimulationsEinstellungen
{
  /**
   * hält alle Planeten der aktuellen Simulation.
   */
  ArrayList<Planet> planeten = new ArrayList<>();
  static final double G = 6.6743E-11;

  /**
   * hält den Massenschwerpunkt der aktuellen Simulation
   */
  Vector2D massenSchwerpunkt = new Vector2D();

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
    super(szenario, aktuellesSzenario);
    
    for (int i = 0; i <= szenario.positionen.length - 1; i++)
    {
      planeten.add(new Planet(szenario.getPositionen()[i], szenario.getGeschwindigkeiten()[i],
          szenario.getMassen()[i], szenario.getRadii()[i], szenario.getFarben()[i]));
    }
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
  	if (relativeAnzeige) {
  		position = position.multiply(zoomFaktor).add(planeten.get(relativerPlanet).getPosition());
      planeten
      	.add(new Planet(position, geschwindigkeit.multiply(10), 5.972E24));
  	}
  	else
  	{
      planeten
      .add(new Planet(position.multiply(zoomFaktor), geschwindigkeit.multiply(10), 5.972E24));
  	}

  }
  
  public void aktualisierePlaneten()
  {
    int anzahlPlaneten = planeten.size();
    Vector2D[] kraefte = new Vector2D[anzahlPlaneten];

    for (int i = 0; i < anzahlPlaneten; i++)
    {
      kraefte[i] = berechneGravitationskraft(i);

      if (repulsion)
      {
        Vector2D repulsionskraft = berechneRepulsion(i);
        kraefte[i] = kraefte[i].add(repulsionskraft);
      }
    }

    for (int i = 0; i < anzahlPlaneten; i++)
    {
      kraftAnwenden(kraefte[i], i);
    }

    for (int i = 0; i < anzahlPlaneten; i++)
    {
      aktualisierePosition(i);
      speichereVorherigePosition(i);
    }
  }
  
  Vector2D berechneGravitationskraft(int aktuellerPlanet)
  {
    // Differenzvektor zum anderen Planeten
    Vector2D kraftRichtung;

    // Länge des Differenzvektors, bzw Quadrat der Länge
    double distanz;
    double distanzSq;

    // Gravitationskraft nach Newtonsches Gravitationsgesetz in N
    double kraft;

    // Gravitationskräfte zu allen anderen Planeten
    ArrayList<Vector2D> kraefte = new ArrayList<>();

    // Summe aller Gravitationskräfte für den aktuellen Planeten
    Vector2D insgKraft = new Vector2D();

    for (int i = 0; i < planeten.size(); i++)
    {
      if (i == aktuellerPlanet)
      {
        continue;
      }

      kraftRichtung = planeten.get(i)
          .getPosition()
          .subtract(planeten.get(aktuellerPlanet)
              .getPosition());
      distanz = Vector2D.magnitude(kraftRichtung);

      if (distanz < planeten.get(aktuellerPlanet)
          .getRadius() * zoomFaktor * minimalerAbstand)
      {
        continue;
      }

      distanzSq = distanz * distanz;
      kraft = G * planeten.get(aktuellerPlanet)
          .getMasse()
          * planeten.get(i)
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
  	//TODO: siehe handy-notizen
//    Point linksOben = simulationsView.getLinksOben();
  	Point linksOben = new Point();

    Map<String, Vector2D> abstaende = new HashMap<>();
    abstaende.put("abstandOben", new Vector2D(0, planeten.get(aktuellerPlanet)
        .getPosition()
        .getY() / zoomFaktor - linksOben.y));
    abstaende.put("abstandUnten", new Vector2D(0, planeten.get(aktuellerPlanet)
        .getPosition()
        .getY() / zoomFaktor - linksOben.y - 950));
    abstaende.put("abstandLinks", new Vector2D(planeten.get(aktuellerPlanet)
        .getPosition()
        .getX() / zoomFaktor - linksOben.x, 0));
    abstaende.put("abstandRechts", new Vector2D(planeten.get(aktuellerPlanet)
        .getPosition()
        .getX() / zoomFaktor - linksOben.x - 1000, 0));

    Object[] vectoren = abstaende.values()
        .toArray();
    double[] magnituden = new double[4];
    for (int i = 0; i < 4; i++)
    {
      magnituden[i] = Vector2D.magnitude((Vector2D) vectoren[i]);
    }
    Arrays.sort(magnituden);
    double min = magnituden[0];

    String geringsterAbstand = "";
    for (Entry<String, Vector2D> entry : abstaende.entrySet())
    {
      if (Vector2D.magnitude(entry.getValue()) == min)
      {
        geringsterAbstand = entry.getKey();
      }
    }

    Vector2D repulsionsRichtung = abstaende.get(geringsterAbstand);
    double distanz = min * zoomFaktor;
    double maxMasse = getMaxMasse();
    double repulsionsIntensitaet = G * planeten.get(aktuellerPlanet)
        .getMasse() * maxMasse / (distanz * distanz);
    Vector2D repulsionsKraft = repulsionsRichtung.setMagnitude(repulsionsIntensitaet);
    return repulsionsKraft;
  }
  
  void kraftAnwenden(Vector2D insgKraft, int aktuellerPlanet)
  {
    Vector2D neuerImpuls = planeten.get(aktuellerPlanet)
        .getImpuls()
        .add(insgKraft.multiply(dt));
    planeten.get(aktuellerPlanet)
        .setImpuls(neuerImpuls);
  }
  
  void aktualisierePosition(int aktuellerPlanet)
  {
    Vector2D neueGeschwindigkeit = planeten.get(aktuellerPlanet)
        .getImpuls()
        .multiply(1 / planeten.get(aktuellerPlanet)
            .getMasse());
    planeten.get(aktuellerPlanet)
        .setGeschwindigkeit(neueGeschwindigkeit);

    Vector2D neuePosition = planeten.get(aktuellerPlanet)
        .getPosition()
        .add(planeten.get(aktuellerPlanet)
            .getGeschwindigkeit()
            .multiply(dt));
    planeten.get(aktuellerPlanet)
        .setPosition(neuePosition);
  }
  
  void speichereVorherigePosition(int aktuellerPlanet)
  {
  	if (positionsThreadCounter % 2 != 0) {
  		return;
  	}
    ArrayList<Vector2D> vorherigePositionen = planeten.get(aktuellerPlanet)
        .getVorherigePositionen();
    Vector2D aktuellePosition;
    
    if (vorherigePositionen.size() == 500)
    {
      vorherigePositionen.remove(0);
    }
    
    if (relativeAnzeige)
    {
      aktuellePosition = planeten.get(aktuellerPlanet)
          .getPosition().subtract(planeten.get(relativerPlanet).getPosition());
    }
    else
    {
      aktuellePosition = planeten.get(aktuellerPlanet)
          .getPosition();
    }
    
    vorherigePositionen.add(aktuellePosition);
    planeten.get(aktuellerPlanet)
        .setVorherigePositionen(vorherigePositionen);
    positionsThreadCounter -= 2;
  }
  
  public void berechneCOM()
  {
    double insgMasse = 0;
    Vector2D position;
    double masse;
//    double insgImpuls = 0;

    massenSchwerpunkt.multiply(0);
    for (int i = 0; i < planeten.size(); i++)
    {
      masse = planeten.get(i)
          .getMasse();
      insgMasse += masse;
      position = planeten.get(i).getPosition();
//      insgImpuls += Vector2D.magnitude(planeten.get(i).getImpuls());
      massenSchwerpunkt.add(position.multiply(masse));
    }
    massenSchwerpunkt.multiply(1 / insgMasse);
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
    this.ausgewaehlterPlanet = 0;
  }

  public void aendereGeschwindigkeitVonSlider(double x, double y)
  {
  	if (!planetAusgewaehlt) 
  	{
  		return;
  	}
  	
  	Vector2D geschwindigkeit = planeten.get(ausgewaehlterPlanet).getGeschwindigkeit();
  	Vector2D impuls;
  	if (x != 0) 
  	{
  		geschwindigkeit.setX(x);
  		impuls = geschwindigkeit.multiply(planeten.get(ausgewaehlterPlanet).getMasse());
  		planeten.get(ausgewaehlterPlanet).setImpuls(impuls);
  	}
  	else if (y != 0) 
  	{
  		geschwindigkeit.setY(y);
  		impuls = geschwindigkeit.multiply(planeten.get(ausgewaehlterPlanet).getMasse());
  		planeten.get(ausgewaehlterPlanet).setImpuls(impuls);
  	}
  }
  
  public void aendereMasseVonSpinner(double masse)
  {
  	if (!planetAusgewaehlt)
  	{
  		return;
  	}
  	
  	planeten.get(ausgewaehlterPlanet).setMasse(masse);
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
}