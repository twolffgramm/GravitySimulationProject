package de.continentale.zv.n_body_simulation.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import de.continentale.zv.n_body_simulation.model.SimulationsModel;

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
  double zoomFaktor;

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
    this.setBackground(Color.BLACK);
    this.zoomFaktor = simulationsModel.getZoomFaktor();
  }

  @Override
  public void paint(Graphics g)
  {
    image = createImage(getWidth(), getHeight());
    graphics = image.getGraphics();
    graphics.translate(500, 500);
    draw(graphics);
    g.drawImage(image, 0, 0, this);
  }

  /**
   * @param g
   */
  public void draw(Graphics g)
  {
    for (int i = 0; i <= this.simulationsModel.getPlaneten()
        .size() - 1; i++)
    {
      g.setColor(Color.WHITE);
      int r = (int) this.simulationsModel.getPlaneten()
          .get(i)
          .getRadius();
      int x = (int) Math.round(this.simulationsModel.getPlaneten()
          .get(i)
          .getPosition()
          .getX() / zoomFaktor - r / 2);
      int y = (int) Math.round(this.simulationsModel.getPlaneten()
          .get(i)
          .getPosition()
          .getY() / zoomFaktor - r / 2);
      g.fillOval(x, y, r, r);

      Toolkit.getDefaultToolkit()
          .sync();
    }

    g.setColor(Color.RED);
    int r = 5;
    int x = (int) Math.round(this.simulationsModel.getCOM()
        .getX() / zoomFaktor - r / 2);
    int y = (int) Math.round(this.simulationsModel.getCOM()
        .getY() / zoomFaktor - r / 2);
    g.fillOval(x, y, r, r);
  }
}
