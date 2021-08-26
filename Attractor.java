package MoverAttractor;

import java.awt.Color;
import java.awt.Graphics;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class Attractor
{
  Vector2D pos;
  Vector2D vel;
  Vector2D acc;
  double mass;
  double radius;

  Attractor(double x, double y, double m)
  {
    this.pos = new Vector2D(x, y);
    this.vel = new Vector2D(0, 0);
    this.acc = new Vector2D();
    this.mass = m;
    this.radius = Math.sqrt(this.mass) * 2;
  }

  /**
   * @param mover
   */
  public void attract(Mover mover)
  {
    Vector2D force;
    force = this.pos.subtract(mover.pos);
    double distance = Vector2D.magnitude(force);
    double distanceSq = distance * distance;
    final double G = 5;
    double strength = G * (this.mass * mover.mass) / distanceSq;
    force = force.setMagnitude(strength);
    mover.applyForce(force);
  }

  /**
   * 
   */
  public void move()
  {
    this.pos = this.pos.add(this.vel);
  }

  /**
   * @param g
   */
  public void draw(Graphics g)
  {
    g.setColor(Color.WHITE);
    int r = (int) this.radius;
    int x = (int) this.pos.getX() - r / 2;
    int y = (int) this.pos.getY() - r / 2;
    g.fillOval(x, y, r, r);
  }
}
