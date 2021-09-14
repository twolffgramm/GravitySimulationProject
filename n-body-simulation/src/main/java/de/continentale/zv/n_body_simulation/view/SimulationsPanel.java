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

import de.continentale.zv.n_body_simulation.controller.InteraktionsController;
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

  private int breite;
  private int hoehe;
  Image image;
  Graphics graphics;
  SimulationsModel simulationsModel;
  Image hintergrund;
  Point ursprung;
  Point linksOben;
  Dimension frameGroesse;
  boolean mausGedrueckt;
  Point koordinatenMausGedrueckt;
  Point geschwindigkeitsLinie = new Point();
  boolean fixiereCOM = true;
  // Vector2D prevCOM;
  // Vector2D difCOM;

  /**
   * SimulationsPanel Konstruktor.
   * 
   * @param simulationsModel .
   * @param breite .
   * @param hoehe .
   *
   */
  public SimulationsPanel(SimulationsModel simulationsModel, int breite, int hoehe)
  {
    this.breite = breite;
    this.hoehe = hoehe;
    frameGroesse = new Dimension(breite, hoehe);
    ursprung = new Point(breite / 2, hoehe / 2);
    linksOben = new Point(-breite / 2, -hoehe / 2);
    this.simulationsModel = simulationsModel;
    this.setFocusable(true);
    this.setPreferredSize(frameGroesse);
    // prevCOM = this.simulationsModel.getCOM();
    // difCOM = new Vector2D();

    try
    {
      hintergrund = ImageIO.read(getClass().getResource("/Background.jpg"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  void registerListener(InteraktionsController interaktionsController)
  {
    this.addMouseWheelListener(interaktionsController);
    this.addMouseListener(interaktionsController);
    this.addMouseMotionListener(interaktionsController);
  }

  @Override
  public void paint(Graphics g)
  {
    image = createImage(getWidth(), getHeight());
    graphics = image.getGraphics();

    // if (fixiereCOM)
    // {
    // System.out.println("am doing it");
    // Vector2D com = simulationsModel.getCOM();
    // difCOM = difCOM.add(Vector2D.difference(prevCOM, com)
    // .multiply(1 / simulationsModel.getZoomFaktor()));
    // Point differenz = new Point();
    // if ((int) difCOM.getX() >= 1 && (int) difCOM.getY() >= 1)
    // {
    // differenz.setLocation(difCOM.getX(), difCOM.getY());
    // updateUrsprung(differenz);
    // difCOM.set(0, 0);
    // }
    // else if ((int) difCOM.getX() >= 1)
    // {
    // differenz.setLocation(difCOM.getX(), 0);
    // updateUrsprung(differenz);
    // difCOM.setX(0);
    // }
    // else if ((int) difCOM.getY() >= 1)
    // {
    // differenz.setLocation(0, difCOM.getY());
    // updateUrsprung(differenz);
    // difCOM.setY(0);
    // }
    // prevCOM = com;
    // }

    graphics.translate(ursprung.x, ursprung.y);
    draw(graphics);
    g.drawImage(image, 0, 0, this);
  }

  /**
   * @param g .
   */
  public void draw(Graphics g)
  {

    g.drawImage(hintergrund, linksOben.x - 200, linksOben.y, null);
    for (int i = 0; i <= simulationsModel.getPlaneten()
        .size() - 1; i++)
    {
      g.setColor(Color.decode(simulationsModel.getPlaneten()
          .get(i)
          .getFarbe()));
      int r = (int) Math.round(simulationsModel.getPlaneten()
          .get(i)
          .getRadius());
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

      path.moveTo(vorherigePositionen.get(0)
          .getX() / simulationsModel.getZoomFaktor(),
          vorherigePositionen.get(0)
              .getY() / simulationsModel.getZoomFaktor());
      for (int j = 0; j < vorherigePositionen.size() - 1; j += 1)
      {
        path.lineTo(vorherigePositionen.get(j)
            .getX() / simulationsModel.getZoomFaktor(),
            vorherigePositionen.get(j)
                .getY() / simulationsModel.getZoomFaktor());
      }
      g2.draw(path);

      if (mausGedrueckt)
      {
        g.setColor(Color.LIGHT_GRAY);
        r = 20;
        x = koordinatenMausGedrueckt.x - r / 2;
        y = koordinatenMausGedrueckt.y - r / 2;
        g.fillOval(x, y, r, r);

        x = koordinatenMausGedrueckt.x;
        y = koordinatenMausGedrueckt.y;
        g.drawLine(x, y, x + geschwindigkeitsLinie.x, y + geschwindigkeitsLinie.y);
      }
    }
  }

  /**
   * @param differenz .
   */
  public void updateUrsprung(Point differenz)
  {
    ursprung.setLocation(ursprung.x + differenz.x, ursprung.y + differenz.y);
    linksOben.setLocation(linksOben.x - differenz.x, linksOben.y - differenz.y);
  }

  /**
   * 
   */
  public void zuruecksetzen()
  {
    ursprung.setLocation(breite / 2, hoehe / 2);
    linksOben.setLocation(-breite / 2, -hoehe / 2);
    // prevCOM = this.simulationsModel.getCOM();
    // difCOM = new Vector2D();
  }

  /**
   * @param mausGedrueckt .
   * @param koordinatenMausGedrueckt .
   */
  public void setMausGedrueckt(boolean mausGedrueckt, Point koordinatenMausGedrueckt)
  {
    this.mausGedrueckt = mausGedrueckt;
    this.koordinatenMausGedrueckt = koordinatenMausGedrueckt;
  }

  /**
   * @param geschwindigkeitsLinie .
   */
  public void setGeschwindigkeitsLinie(Point geschwindigkeitsLinie)
  {
    this.geschwindigkeitsLinie.setLocation(geschwindigkeitsLinie.x / 4,
        geschwindigkeitsLinie.y / 4);
  }

  /**
   * @return .
   */
  public Point getLinksOben()
  {
    return this.linksOben;
  }

  /**
   * @return .
   */
  public Point getUrsprung()
  {
    return this.ursprung;
  }

}