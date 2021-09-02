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

  Planet(Vector2D position, Vector2D geschwindigkeit, double masse)
  {
    this.position = position;
    this.geschwindigkeit = geschwindigkeit;
    this.masse = masse;
    this.impuls = this.geschwindigkeit.multiply(this.masse);
    // this.radius = Math.sqrt(this.mass) * 2;
    this.radius = 20;
    vorherigePositionen = new ArrayList<>();
    vorherigePositionen.add(this.position);
  }

  Planet(Vector2D position, Vector2D geschwindigkeit, double masse, double radius, String farbe)
  {
    this.position = position;
    this.geschwindigkeit = geschwindigkeit;
    this.masse = masse;
    this.impuls = this.geschwindigkeit.multiply(this.masse);
    // this.radius = Math.sqrt(this.mass) * 2;
    this.radius = radius;
    this.farbe = farbe;
    vorherigePositionen = new ArrayList<>();
    vorherigePositionen.add(this.position);
  }
  // Planet(double positionX, double positionY, double geschwindigkeitX, double geschwindigkeitY,
  // double masse)
  // {
  // random = new Random();
  // this.position = new Vector2D(positionX, positionY);
  // this.geschwindigkeit = new Vector2D(geschwindigkeitX, geschwindigkeitY);
  // this.beschleunigung = new Vector2D();
  // this.masse = masse;
  // this.impuls = this.geschwindigkeit.multiply(this.masse);
  // // this.radius = Math.sqrt(this.mass) * 2;
  // this.radius = 20;
  // }

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
   * @param vorherigePositionen
   */
  public void setVorherigePositionen(ArrayList<Vector2D> vorherigePositionen)
  {
    this.vorherigePositionen = vorherigePositionen;
  }

}
