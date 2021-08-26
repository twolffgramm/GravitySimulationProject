package MoverAttractor;

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
  Mover mover;
  ArrayList<Mover> movers = new ArrayList<>();
  Attractor attractor;

  SimulationPanel()
  {
    newAttractor();
    newMover();
    this.setFocusable(true);
    this.setPreferredSize(SCREEN_SIZE);

    simulationThread = new Thread(this);
    simulationThread.start();
  }

  /**
   * 
   */
  public void newAttractor()
  {
    attractor = new Attractor(SIMULATION_WIDTH / 2, SIMULATION_HEIGHT / 2, 500);
  }

  /**
   * 
   */
  public void newMover()
  {
    random = new Random();
    // mover = new Mover(random.nextInt(SIMULATION_WIDTH), random.nextInt(SIMULATION_HEIGHT), 100);
    for (int i = 0; i <= 10; i++)
    {
      movers
          .add(new Mover(random.nextInt(SIMULATION_WIDTH), random.nextInt(SIMULATION_HEIGHT), 100));
    }
    // mover = new Mover(SIMULATION_WIDTH / 2, SIMULATION_HEIGHT / 2 + 300, 100);
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
    attractor.draw(g);
    for (int i = 0; i <= movers.size() - 1; i++)
    {
      movers.get(i)
          .drawLines(g);
      movers.get(i)
          .draw(g);
    }
    // mover.drawLines(g);
    // mover.draw(g);
    Toolkit.getDefaultToolkit()
        .sync();
  }

  /**
   * 
   */
  public void move()
  {
    attractor.move();
    for (int i = 0; i <= movers.size() - 1; i++)
    {
      movers.get(i)
          .move();
      attractor.attract(movers.get(i));
    }
    // mover.move();
    // attractor.attract(mover);
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
