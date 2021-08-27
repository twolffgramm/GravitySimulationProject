package de.continentale.zv.n_body_problem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class SimulationPanel extends JPanel implements Runnable
{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  static final int SIMULATION_WIDTH = 1500;
  static final int SIMULATION_HEIGHT = 1000;
  static final Dimension SCREEN_SIZE = new Dimension(SIMULATION_WIDTH, SIMULATION_HEIGHT);
  Thread simulationThread;
  Image image;
  Graphics graphics;
  Random random;
  ArrayList<Attractor> attractors = new ArrayList<>();
  Vector2D centerOfMass = new Vector2D();

  SimulationPanel()
  {
    newAttractors();
    this.setFocusable(true);
    this.setPreferredSize(SCREEN_SIZE);

    simulationThread = new Thread(this);
    simulationThread.start();
  }

  /**
   * 
   */
  public void newAttractors()
  {
    random = new Random();
    double[] positionX = { 415E6 + 291.001308E6, 415E6 - 291.001308E6, 415E6 };
    double[] positionY = { 300E6 + 72.926259E6, 300E6 - 72.926259E6, 300E6 };
    double factor = 1150;
    double[] velX = { 0.93240737 / 2 * factor, 0.93240737 / 2 * factor, -0.93240737 * factor };
    double[] velY = { -0.86473146 / 2 * factor, -0.86473146 / 2 * factor, 0.86473146 * factor };
    double[] mass = { 5.972E24, 5.972E24, 5.972E24 };

    for (int i = 0; i <= 2; i++)
    {
      // attractors
      // .add(new Attractor(random.nextInt(SIMULATION_WIDTH), random.nextInt(SIMULATION_HEIGHT),
      // 100));
      attractors.add(new Attractor(positionX[i], positionY[i], mass[i], velX[i], velY[i]));
    }
  }

  @Override
  public void paint(Graphics g)
  {
    image = createImage(getWidth(), getHeight());
    graphics = image.getGraphics();
    draw(graphics);
    g.drawImage(image, 0, 0, this);
  }

  /**
   * @param g
   */
  public void draw(Graphics g)
  {
    for (int i = 0; i <= attractors.size() - 1; i++)
    {
      attractors.get(i)
          .draw(g);
    }

    g.setColor(Color.RED);
    int r = 5;
    int x = (int) centerOfMass.getX() / 600000 - r / 2;
    int y = (int) centerOfMass.getY() / 600000 - r / 2;
    g.fillOval(x, y, r, r);

    Toolkit.getDefaultToolkit()
        .sync();
  }

  /**
   * 
   */
  public void move()
  {
    for (int i = 0; i <= attractors.size() - 1; i++)
    {
      attractors.get(i)
          .attract(attractors, i);
      attractors.get(i)
          .move();
    }

    double totalMass = 0;
    Vector2D position;
    double mass;
    Vector2D velocity;
    Vector2D momentum = new Vector2D();
    double totalMomentum;
    for (int i = 0; i <= attractors.size() - 1; i++)
    {
      totalMass += attractors.get(i).mass;
      position = attractors.get(i).position;
      velocity = attractors.get(i).velocity;
      mass = attractors.get(i).mass;
      momentum = momentum.add(velocity.multiply(mass));
      centerOfMass = centerOfMass.add(position.multiply(mass));
    }
    centerOfMass = centerOfMass.multiply(1 / totalMass);
    totalMomentum = Vector2D.magnitude(momentum);
    // System.out.println(totalMomentum);

  }

  @Override
  public void run()
  {
    int t = 0;
    int dt = 1;
    int tMax = 1000000000;

    while (t < tMax)
    {
      // try
      // {
      // TimeUnit.MILLISECONDS.sleep(1);
      // }
      // catch (InterruptedException e)
      // {
      // e.printStackTrace();
      // }

      move();
      repaint();
      t += dt;
      // System.out.println(t);
    }
  }
}