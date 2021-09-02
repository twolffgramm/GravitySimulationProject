package de.continentale.zv.n_body_simulation.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.GeneralPath;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.continentale.zv.n_body_simulation.controller.ButtonController;
import de.continentale.zv.n_body_simulation.model.SimulationsModel;
import de.continentale.zv.n_body_simulation.model.Vector2D;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class SimulationsPanel extends JPanel
{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  private static final int BREITE = 1000;
  private static final int HOEHE = 1000;
  private static final Dimension FRAME_GROESSE = new Dimension(BREITE, HOEHE);
  Image image;
  Graphics graphics;
  SimulationsModel simulationsModel;
  Image hintergrund;
  /** ursprung */
  public Point ursprung = new Point(BREITE / 2, HOEHE / 2);

  /**
   * SimulationsPanel Konstruktor.
   * 
   * @param simulationsModel
   *
   */
  public SimulationsPanel(SimulationsModel simulationsModel)
  {
    this.simulationsModel = simulationsModel;
    this.setFocusable(true);
    this.setPreferredSize(FRAME_GROESSE);

    try
    {
      hintergrund = ImageIO.read(getClass().getResource("/Background.jpg"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  void registerListener(ButtonController buttonController)
  {
    this.addMouseWheelListener(buttonController);
    this.addMouseListener(buttonController);
    this.addMouseMotionListener(buttonController);
  }

  @Override
  public void paint(Graphics g)
  {
    image = createImage(getWidth(), getHeight());
    graphics = image.getGraphics();
    graphics.translate(ursprung.x, ursprung.y);
    draw(graphics);
    g.drawImage(image, 0, 0, this);
  }

  /**
   * @param g
   */
  public void draw(Graphics g)
  {
    g.drawImage(hintergrund, -BREITE / 2, -HOEHE / 2, null);
    // g.drawImage(hintergrund, 0, 0, null);
    for (int i = 0; i <= simulationsModel.getPlaneten()
        .size() - 1; i++)
    {
      g.setColor(Color.decode(simulationsModel.getPlaneten()
          .get(i)
          .getFarbe()));
      int r = (int) simulationsModel.getPlaneten()
          .get(i)
          .getRadius();
      int x = (int) Math.round(simulationsModel.getPlaneten()
          .get(i)
          .getPosition()
          .getX() / simulationsModel.getZoomFaktor() - r / 2);
      int y = (int) Math.round(simulationsModel.getPlaneten()
          .get(i)
          .getPosition()
          .getY() / simulationsModel.getZoomFaktor() - r / 2);
      g.fillOval(x, y, r, r);

      Toolkit.getDefaultToolkit()
          .sync();
    }

    g.setColor(Color.RED);
    int r = 5;
    int x = (int) Math.round(simulationsModel.getCOM()
        .getX() / simulationsModel.getZoomFaktor() - r / 2);
    int y = (int) Math.round(simulationsModel.getCOM()
        .getY() / simulationsModel.getZoomFaktor() - r / 2);
    g.fillOval(x, y, r, r);

    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(2.0f));

    for (int i = 0; i <= simulationsModel.getPlaneten()
        .size() - 1; i++)
    {
      g2.setPaint(Color.decode(simulationsModel.getPlaneten()
          .get(i)
          .getFarbe()));
      ArrayList<Vector2D> vorherigePositionen = this.simulationsModel.getPlaneten()
          .get(i)
          .getVorherigePositionen();
      GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, vorherigePositionen.size() - 1);
      // if (i == 3)
      // {
      // System.out.println(vorherigePositionen.get(vorherigePositionen.size() - 1)
      // .toString() + " --> aus Panel");
      // }
      path.moveTo(vorherigePositionen.get(0)
          .getX() / simulationsModel.getZoomFaktor(),
          vorherigePositionen.get(0)
              .getY() / simulationsModel.getZoomFaktor());
      for (int j = 0; j < vorherigePositionen.size() - 1; j++)
      {
        path.lineTo(vorherigePositionen.get(j)
            .getX() / simulationsModel.getZoomFaktor(),
            vorherigePositionen.get(j)
                .getY() / simulationsModel.getZoomFaktor());
      }
      g2.draw(path);
    }
  }

  /**
   * @param differenz
   */
  public void updateUrsprung(Point differenz)
  {
    ursprung.setLocation(ursprung.x + differenz.x, ursprung.y + differenz.y);
  }

  /**
   * 
   */
  public void zuruecksetzen()
  {
    ursprung.setLocation(BREITE / 2, HOEHE / 2);
  }
}
