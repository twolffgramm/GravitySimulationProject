package de.continentale.zv.n_body_simulation.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

import de.continentale.zv.n_body_simulation.model.Vector2D;

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
  Point koordinatenMausGedrueckt;
  Point koordinatenMausGeloest;
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
  	if (SwingUtilities.isLeftMouseButton(e)) {
      int deltaX = e.getX() - koordinatenMausGedrueckt.x;
      int deltaY = e.getY() - koordinatenMausGedrueckt.y;
      Point delta = new Point(deltaX, deltaY);
  
      Point differenz = new Point();
      differenz.x = delta.x - prevDifferenz.x;
      differenz.y = delta.y - prevDifferenz.y;
  
      simulationsController.simulationsView.updateUrsprung(differenz);
      simulationsController.simulationsView.repaint();
  
      prevDifferenz = delta;
  	}
  	
  	if (SwingUtilities.isRightMouseButton(e)) {
      int deltaX = koordinatenMausGedrueckt.x - e.getX();
      int deltaY = koordinatenMausGedrueckt.y - e.getY();
      Point delta = new Point(deltaX, deltaY);
      simulationsController.simulationsView.setGeschwindigkeitsLinie(delta);
      simulationsController.simulationsView.repaint();
  	}
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
    koordinatenMausGedrueckt = e.getPoint();
    Point ursprung = simulationsController.simulationsView.getUrsprung();
    Point koordinatenMausGedruecktTransformiert = new Point(koordinatenMausGedrueckt.x - ursprung.x, koordinatenMausGedrueckt.y - ursprung.y);
  	simulationsController.simulationsView.setMausGedrueckt(true, koordinatenMausGedruecktTransformiert);
    simulationsController.simulationsView.repaint();
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseReleased(MouseEvent e)
  {
  	if (SwingUtilities.isRightMouseButton(e)) {
    koordinatenMausGeloest = e.getPoint();
    Point ursprung = simulationsController.simulationsView.getUrsprung();
    Vector2D positionNeuerPlanet = new Vector2D(koordinatenMausGedrueckt.x - ursprung.x, koordinatenMausGedrueckt.y - ursprung.y);
    Vector2D geschwindigkeitNeuerPlanet = new Vector2D(koordinatenMausGedrueckt.x - koordinatenMausGeloest.x, koordinatenMausGedrueckt.y - koordinatenMausGeloest.y);
    simulationsController.simulationsModel.planetHinzufuegen(positionNeuerPlanet,geschwindigkeitNeuerPlanet);
  	simulationsController.simulationsView.setMausGedrueckt(false, koordinatenMausGedrueckt);
    simulationsController.simulationsView.setGeschwindigkeitsLinie(new Point());
    simulationsController.simulationsView.repaint();
    if (simulationsController.simulationsView.getIsEditor())
    {
      simulationsController.simulationsView.erstelleSlider();

    }
  	}
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
