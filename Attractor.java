package MoverAttractor;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

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
  Vector2D position;
  Vector2D velocity;
  Vector2D acceleration;
  double mass;
  double radius;
  Random random;

  Attractor(double x, double y, double m, double vX, double vY)
  {
  	random = new Random();
    this.position = new Vector2D(x, y);
    this.velocity = new Vector2D(vX, vY);
    this.acceleration = new Vector2D();
    this.mass = m;
    this.radius = Math.sqrt(this.mass) * 2;
  }

  /**
   * @param mover
   */
  public void attract(ArrayList<Attractor> attractors, int iterator)
  {
		ArrayList<Vector2D> forces = new ArrayList<>();
		Vector2D force = new Vector2D();
    final double G = 20;
    
  	for (int i = 0; i <= attractors.size()-1; i++) {
  		if (i == iterator) {
  			continue;
  		}
//      forces.add(this.position.subtract(attractors.get(i).position));
  		forces.add(attractors.get(i).position.subtract(this.position));
      double distance = Vector2D.magnitude(forces.get(forces.size()-1));
      double distanceSq = distance * distance;
      double strength = G * (this.mass * attractors.get(i).mass) / distanceSq;
      forces.set(forces.size()-1, forces.get(forces.size()-1).setMagnitude(strength));
  	}
  	
  	for (int i = 0; i <= forces.size()-1; i++) {
  		force = force.add(forces.get(i));
  	}
  	
  	this.applyForce(force);
  }

  /**
   * @param force
   */
  public void applyForce(Vector2D force)
  {
    force = force.multiply(1 / this.mass);
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
    g.setColor(Color.WHITE);
    int r = (int) this.radius;
    int x = (int) this.position.getX() - r / 2;
    int y = (int) this.position.getY() - r / 2;
    g.fillOval(x, y, r, r);
  }
}