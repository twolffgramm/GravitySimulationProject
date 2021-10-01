package de.continentale.zv.n_body_simulation.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Eine Instanz der Klasse {@code Planet} ist die kleinste Einheit des Models für die Simulation.
 * Sie hält den aktuellen {@code Vector2D Position}, {@code Vector2D Geschwindigkeit},
 * {@code Vector2D Beschleunigung}, {@code Vector2D Impuls}, {@code double Masse},
 * {@code double Radius} & {@code String Farbe}, welche für die Berechnung der Kräfte, sowie für das
 * Zeichnen des Planeten nötig sind. Zusätzlich hält jeder [{@code Planet} eine
 * {@code ArrayList<Vector2D> vorherigePositionen}, in welcher die vorherigen Positionen des
 * Planeten gespeichert werden können. Dies ermöglicht das Zeichnen eines "Trails" hinter dem
 * Planeten.
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class Planet
{
  Vector2D position;
  Vector2D geschwindigkeit;
  Vector2D beschleunigung;
  Vector2D impuls;
  double masse;
  double radius;
  String farbe;
  // Liste der vorherigen Positionen als Vectoren. Benötigt für die Darstellung eines Trails für
  // jeden Planeten.
  ArrayList<Vector2D> vorherigePositionen;

  // genutzt für die zufällige Zuordunung einer Farbe beim dynamischen Erstellen eines Planeten
  Random random;

  /**
   * Konstruiert einen {@code Planet} und initialierst diesen nur mit Angabe eines
   * {@code Vector2D position}, der die Startposition beschreibt, eines
   * {@code Vector2D geschwindigkeit}, der die Startgeschwindigkeit beschreibt und des
   * {@code double masse}, der die Masse beschreibt.
   * <p>
   * Der {@code Vector2D impuls} wird berechnet. Bei Konstruierung auf diesem Weg wird der Radius
   * auf 20 Pixel festgelegt und eine zufällige Farbe zugeordnet.
   * <p>
   * Die {@code ArrayList<Vector2D> vorherigePositionen} wird instanziiert und die Startposition
   * hinzugefügt.
   *
   * @param position {@code Vector2D} - die Startposition
   * @param geschwindigkeit {@code Vector2D} - die Startgeschwindigkeit
   * @param masse {@code double} - die Masse
   */
  public Planet(Vector2D position, Vector2D geschwindigkeit, double masse)
  {
    this.position = position;
    this.geschwindigkeit = geschwindigkeit;
    this.masse = masse;
    this.impuls = this.geschwindigkeit.multiply(this.masse);
    this.radius = 20;
    random = new Random();
    int randomInt = random.nextInt(16777215);
    this.farbe = "#" + Integer.toHexString(randomInt);
    vorherigePositionen = new ArrayList<>();
    vorherigePositionen.add(this.position);
  }

  /**
   * Konstruiert einen {@code Planet} und initialierst diesen mit Angabe eines
   * {@code Vector2D position}, der die Startposition beschreibt, eines
   * {@code Vector2D geschwindigkeit}, der die Startgeschwindigkeit beschreibt, einem
   * {@code double masse}, der die Masse beschreibt, einem {@code double radius}, der den Radius
   * beschreibt, sowie einem {@code String farbe}, der die Farbe beschreibt.
   * <p>
   * Der {@code Vector2D impuls} wird berechnet. Die {@code ArrayList<Vector2D> vorherigePositionen}
   * wird instanziiert und die Startposition hinzugefügt.
   *
   * @param position {@code Vector2D} - die Startposition
   * @param geschwindigkeit {@code Vector2D} - die Startgeschwindigkeit
   * @param masse {@code double} - die Masse
   * @param radius {@code double} - der Radius
   * @param farbe {@code String} - die Farbe
   */
  public Planet(Vector2D position, Vector2D geschwindigkeit, double masse, double radius,
      String farbe)
  {
    this.position = position;
    this.geschwindigkeit = geschwindigkeit;
    this.masse = masse;
    this.impuls = this.geschwindigkeit.multiply(this.masse);
    this.radius = radius;
    this.farbe = farbe;
    vorherigePositionen = new ArrayList<>();
    vorherigePositionen.add(this.position);
  }

  /**
   * Liefert die aktuelle Position.
   * 
   * @return einen {@code Vector2D}, die aktuelle Position.
   */
  public Vector2D getPosition()
  {
    return this.position;
  }

  /**
   * Liefert die aktuelle Geschwindigkeit.
   * 
   * @return einen {@code Vector2D}, die aktuelle Geschwindigkeit.
   */
  public Vector2D getGeschwindigkeit()
  {
    return this.geschwindigkeit;
  }

  /**
   * Liefert die aktuelle Beschleunigung.
   * 
   * @return einen {@code Vector2D}, die aktuelle Beschleunigung.
   */
  public Vector2D getBeschleunigung()
  {
    return this.beschleunigung;
  }

  /**
   * Liefert den aktuellen Impuls.
   * 
   * @return einen {@code Vector2D}, den aktuellen Impuls.
   */
  public Vector2D getImpuls()
  {
    return this.impuls;
  }

  /**
   * Liefert die aktuelle Masse.
   * 
   * @return ein {@code double}, die aktuelle Masse.
   */
  public double getMasse()
  {
    return this.masse;
  }

  /**
   * Liefert den aktuellen Radius.
   * 
   * @return ein {@code double}, den aktuellen Radius.
   */
  public double getRadius()
  {
    return this.radius;
  }

  /**
   * Liefert die Farbe.
   * 
   * @return ein {@code String}, die Farbe als Hex-String mit führendem "#".
   */
  public String getFarbe()
  {
    return this.farbe;
  }

  /**
   * Liefert die aktuellen vorherigen Positionen.
   * 
   * @return eine {@code ArrayList<Vector2D>}, die vorherigen Positionen.
   */
  public ArrayList<Vector2D> getVorherigePositionen()
  {
    return vorherigePositionen;
  }

  /**
   * Setzt die Position.
   * 
   * @param position {@code Vector2D} - die neue Position
   */
  public void setPosition(Vector2D position)
  {
    this.position = position;
  }

  /**
   * Setzt die Geschwindigkeit.
   * 
   * @param geschwindigkeit {@code Vector2D} - die neue Geschwindigkeit
   */
  public void setGeschwindigkeit(Vector2D geschwindigkeit)
  {
    this.geschwindigkeit = geschwindigkeit;
  }

  /**
   * Setzt die Beschleunigung.
   * 
   * @param beschleunigung {@code Vector2D} - die neue Beschleunigung
   */
  public void setBeschleunigung(Vector2D beschleunigung)
  {
    this.beschleunigung = beschleunigung;
  }

  /**
   * Setzt den Impuls
   * 
   * @param impuls {@code Vector2D} - der neue Impuls
   */
  public void setImpuls(Vector2D impuls)
  {
    this.impuls = impuls;
  }

  /**
   * Setzt die Masse
   * 
   * @param masse {@code double} - die neue Masse
   */
  public void setMasse(double masse)
  {
    this.masse = masse;
  }

  /**
   * Setzt den Radius
   * 
   * @param radius {@code double} - der neue Radius in Pixel
   */
  public void setRadius(double radius)
  {
    this.radius = radius;
  }

  /**
   * Setzt die vorherigen Positionen. Wird zum aktualisieren dieser Variable benutzt.
   * 
   * @param vorherigePositionen {@code ArrayList<Vector2D>} - die neuen vorherigen Positionen
   */
  public void setVorherigePositionen(ArrayList<Vector2D> vorherigePositionen)
  {
    this.vorherigePositionen = vorherigePositionen;
  }

}
