package de.continentale.zv.n_body_simulation.model;

import java.util.ArrayList;

/**
 * TODO Klasse kommentieren
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
  ArrayList<Vector2D> vorherigePositionen;

  /**
   * Planet Konstruktor.
   *
   * @param position
   * @param geschwindigkeit
   * @param masse
   */
  public Planet(Vector2D position, Vector2D geschwindigkeit, double masse)
  {
    this.position = position;
    this.geschwindigkeit = geschwindigkeit;
    this.masse = masse;
    this.impuls = this.geschwindigkeit.multiply(this.masse);
    this.radius = 20;
    this.farbe = "#FFFFFF";
    vorherigePositionen = new ArrayList<>();
    vorherigePositionen.add(this.position);
  }

  /**
   * Planet Konstruktor.
   *
   * @param position
   * @param geschwindigkeit
   * @param masse
   * @param radius
   * @param farbe
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
   * @return .
   */
  public Vector2D getPosition()
  {
    return this.position;
  }

  /**
   * @return .
   */
  public Vector2D getGeschwindigkeit()
  {
    return this.geschwindigkeit;
  }

  /**
   * @return .
   */
  public Vector2D getBeschleunigung()
  {
    return this.beschleunigung;
  }

  /**
   * @return .
   */
  public Vector2D getImpuls()
  {
    return this.impuls;
  }

  /**
   * @return .
   */
  public double getMasse()
  {
    return this.masse;
  }

  /**
   * @return .
   */
  public double getRadius()
  {
    return this.radius;
  }

  /**
   * @return .
   */
  public String getFarbe()
  {
    return this.farbe;
  }

  /**
   * @return .
   */
  public ArrayList<Vector2D> getVorherigePositionen()
  {
    return vorherigePositionen;
  }

  /**
   * @param position
   */
  public void setPosition(Vector2D position)
  {
    this.position = position;
  }

  /**
   * @param geschwindigkeit
   */
  public void setGeschwindigkeit(Vector2D geschwindigkeit)
  {
    this.geschwindigkeit = geschwindigkeit;
  }

  /**
   * @param beschleunigung
   */
  public void setBeschleunigung(Vector2D beschleunigung)
  {
    this.beschleunigung = beschleunigung;
  }

  /**
   * @param impuls
   */
  public void setImpuls(Vector2D impuls)
  {
    this.impuls = impuls;
  }

  /**
   * @param masse
   */
  public void setMasse(double masse)
  {
    this.masse = masse;
  }

  /**
   * @param radius
   */
  public void setRadius(double radius)
  {
    this.radius = radius;
  }

  /**
   * @param vorherigePositionen
   */
  public void setVorherigePositionen(ArrayList<Vector2D> vorherigePositionen)
  {
    this.vorherigePositionen = vorherigePositionen;
  }

}
