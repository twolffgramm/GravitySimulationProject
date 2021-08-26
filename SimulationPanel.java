package MoverAttractor;

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

  static final int SIMULATION_WIDTH = 1000;
  static final int SIMULATION_HEIGHT = 1000;
  static final Dimension SCREEN_SIZE = new Dimension(SIMULATION_WIDTH, SIMULATION_HEIGHT);
  Thread simulationThread;
  Image image;
  Graphics graphics;
  Random random;
  ArrayList<Attractor> attractors = new ArrayList<>();
  ArrayList<Attractor> attractorsWithout = new ArrayList<>();
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
  	int[] positionX = {SIMULATION_WIDTH / 2, SIMULATION_WIDTH / 2};
  	int[] positionY = {SIMULATION_HEIGHT / 2 - 100, SIMULATION_HEIGHT / 2 +100};
  	int[] velX = {-2,2};
  	int[] velY = {0,0};
  	
    for (int i = 0; i <= 1; i++)
    {
//      attractors
//          .add(new Attractor(random.nextInt(SIMULATION_WIDTH), random.nextInt(SIMULATION_HEIGHT), 100));
    	attractors
    				.add(new Attractor(positionX[i], positionY[i], 100, velX[i], velY[i]));
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
    	attractors.get(i).draw(g);
    }
    
    g.setColor(Color.RED);
    int r = 5;
    int x = (int) centerOfMass.getX() - r / 2;
    int y = (int) centerOfMass.getY() - r / 2;
    g.fillOval(x, y, r, r);
    
    Toolkit.getDefaultToolkit()
        .sync();
  }

  /**
   * 
   */
  public void move()
  {
  	for (int i = 0; i <= attractors.size() - 1; i++) {
  		attractors.get(i).attract(attractors,i);
  		attractors.get(i).move();
  	}
  	
  	double totalMass = 0;
  	Vector2D position;
  	double mass;
  	Vector2D velocity;
  	Vector2D momentum = new Vector2D();
  	double totalMomentum;
  	for (int i = 0; i <= attractors.size() - 1; i++) {
  		totalMass += attractors.get(i).mass;
  		position = attractors.get(i).position;
  		velocity = attractors.get(i).velocity;
  		mass = attractors.get(i).mass;
  		momentum = momentum.add(velocity.multiply(mass));
  		centerOfMass = centerOfMass.add(position.multiply(mass));
  	}
  	centerOfMass = centerOfMass.multiply(1 / totalMass);
  	totalMomentum = Vector2D.magnitude(momentum);
  	System.out.println(totalMomentum);
  	
  	
  }

  @Override
  public void run()
  {
    long lastTime = System.nanoTime();
    double amountOfTicks = 60;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    while (true)
    {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      if (delta >= 1)
      {
        move();
        repaint();
        delta--;
      }
    }
  }
}