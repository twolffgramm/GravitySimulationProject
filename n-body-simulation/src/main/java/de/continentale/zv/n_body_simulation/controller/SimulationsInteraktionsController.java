package de.continentale.zv.n_body_simulation.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class SimulationsInteraktionsController
    implements MouseListener, MouseWheelListener, MouseMotionListener
{
  SimulationsController simulationsController;
  Point mausPunkt;
  Point prevDifferenz = new Point();

  /**
   * SimulationsInteraktionsController Konstruktor.
   *
   * @param simulationsController
   */
  public SimulationsInteraktionsController(SimulationsController simulationsController)
  {
    this.simulationsController = simulationsController;

  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseDragged(MouseEvent e)
  {
    int deltaX = e.getX() - mausPunkt.x;
    int deltaY = e.getY() - mausPunkt.y;
    Point delta = new Point(deltaX, deltaY);

    Point differenz = new Point();
    differenz.x = delta.x - prevDifferenz.x;
    differenz.y = delta.y - prevDifferenz.y;

    simulationsController.simulationsView.updateUrsprung(differenz);
    simulationsController.simulationsView.repaint();

    prevDifferenz = delta;
    System.out.println(e.getComponent()
        .toString());
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
   */
  @Override
  public void mouseWheelMoved(MouseWheelEvent e)
  {
    int aenderung = e.getWheelRotation();
    simulationsController.simulationsModel.setZoomFaktor(aenderung);
    simulationsController.simulationsModel.setRadius(aenderung);
    simulationsController.simulationsView.repaint();
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseClicked(MouseEvent e)
  {
    Point klick = e.getPoint();
    Point ursprung = simulationsController.simulationsView.simulationsPanel.ursprung;
    simulationsController.simulationsModel.planetHinzufuegen(klick, ursprung);
    simulationsController.simulationsView.repaint();

  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent e)
  {
    prevDifferenz.setLocation(0, 0);
    mausPunkt = e.getPoint();
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseReleased(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseEntered(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }
}
