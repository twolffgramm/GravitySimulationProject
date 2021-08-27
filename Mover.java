package de.continentale.zv.n_body_problem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */

public class Mover extends Rectangle
{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  Vector2D position;
  Vector2D velocity;
  Vector2D acceleration;
  Vector2D bla;
  double mass;
  double radius;

  @SuppressWarnings("unused")
  Mover(double x, double y, double m)
  {
    Random random = new Random();
    this.position = new Vector2D(x, y);
    // this.vel = new Vector2D(random.nextDouble(), random.nextDouble());
    // this.vel = this.vel.multiply(10);
    this.velocity = new Vector2D(2, 0);
    this.acceleration = new Vector2D();
    this.mass = m;
    this.radius = Math.sqrt(this.mass) * 2;
  }

  /**
   * @param force
   */
  public void applyForce(Vector2D force)
  {
    force = force.multiply(1 / this.mass);
    this.bla = force;
    this.acceleration = this.acceleration.add(force);
  }

  /**
   * 
   */
  public void move()
  {
    this.velocity = this.velocity.add(this.acceleration);
    this.position = this.position.add(this.velocity);
    this.acceleration.set(0, 0);
  }

  /**
   * @param g
   */
  public void draw(Graphics g)
  {
    g.setColor(Color.GRAY);
    int r = (int) this.radius;
    int x = (int) this.position.getX() - r / 2;
    int y = (int) this.position.getY() - r / 2;
    g.fillOval(x, y, r, r);
  }

  void drawLines(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;
    int x1 = (int) this.position.getX();
    int y1 = (int) this.position.getY();
    double x2 = this.bla.getX() * 100;
    double y2 = this.bla.getY() * 100;
    int x3 = (int) x2;
    int y3 = (int) y2;
    x3 = x1 + x3;
    y3 = y1 + y3;
    g2d.drawLine(x3, y3, x1, y1);
  }
}