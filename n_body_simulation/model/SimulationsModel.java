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
public class SimulationsModel
{
  ArrayList<Planet> planeten = new ArrayList<>();
  Vector2D massenSchwerpunkt = new Vector2D();
  int dt;
  double zoomFaktor;

  /**
   * SimulationsModel Konstruktor.
   * 
   * @param szenario
   */
  public SimulationsModel(Szenario szenario)
  {
    for (int i = 0; i <= szenario.positionen.length - 1; i++)
    {
      this.planeten.add(
          new Planet(szenario.positionen[i], szenario.geschwindigkeiten[i], szenario.massen[i]));
    }
    this.dt = szenario.dt;
    this.zoomFaktor = szenario.zoomFaktor;
  }

  // public SimulationsModel(double[] positionX, double[] positionY, double[] geschwindigkeitX,
  // double[] geschwindigkeitY, double[] masse)
  // {
  // for (int i = 0; i <= positionX.length - 1; i++)
  // {
  // this.planeten.add(new Planet(positionX[i], positionY[i], geschwindigkeitX[i],
  // geschwindigkeitY[i], masse[i]));
  // }
  // }

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
}
