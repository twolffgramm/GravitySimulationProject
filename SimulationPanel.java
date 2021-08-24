package MoverAttractor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
  static final int SIMULATION_WIDTH = 1000;
  static final int SIMULATION_HEIGHT = 1000;
  static final Dimension SCREEN_SIZE = new Dimension(SIMULATION_WIDTH, SIMULATION_HEIGHT);
  Thread simulationThread;
  Image image;
  Graphics graphics;
  Random random;
  mover mover;
  attractor attractor;

  SimulationPanel()
  {
    newAttractor();
    newMover();
    this.setFocusable(true);
    this.setPreferredSize(SCREEN_SIZE);

    simulationThread = new Thread(this);
    simulationThread.start();
  }

  public void newAttractor()
  {
    attractor = new attractor(SIMULATION_WIDTH, SIMULATION_HEIGHT, 100);
  }

  public void newMover()
  {
    random = new Random();
    mover = new mover(random.nextInt(SIMULATION_WIDTH), random.nextInt(SIMULATION_HEIGHT), 100);
  }

  public void paint(Graphics g)
  {
    image = createImage(getWidth(), getHeight());
    graphics = image.getGraphics();
    draw(graphics);
    g.drawImage(image, 0, 0, this);
  }

  public void draw(Graphics g)
  {
    attractor.draw(g);
    mover.draw(g);
    Toolkit.getDefaultToolkit()
        .sync();
  }

  public void move()
  {
    mover.move();
  }

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
