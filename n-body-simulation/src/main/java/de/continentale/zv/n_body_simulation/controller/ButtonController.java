package de.continentale.zv.n_body_simulation.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class ButtonController implements ActionListener, MouseListener, MouseWheelListener,
    ChangeListener, MouseMotionListener
{
  SimulationsController simulationsController;
  boolean vorherGedrueckt;
  Point mausPunkt;

  /**
   * ButtonController Konstruktor.
   * 
   * @param simulationsController
   */
  public ButtonController(SimulationsController simulationsController)
  {
    this.simulationsController = simulationsController;
    vorherGedrueckt = false;
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @SuppressWarnings("deprecation")
  @Override
  public void actionPerformed(ActionEvent event)
  {
    String command = event.getActionCommand();
    if (command.equals("szenarien"))
    {

    }
    else if (command.equals("start"))
    {
      if (vorherGedrueckt == false)
      {
        simulationsController.positionsThread.start();
        simulationsController.repaintThread.start();
        vorherGedrueckt = true;
      }
      simulationsController.positionsThread.resume();
      simulationsController.repaintThread.resume();
    }
    else if (command.equals("pause"))
    {
      simulationsController.positionsThread.suspend();
      simulationsController.repaintThread.suspend();
    }
    else if (command.equals("zuruecksetzen"))
    {
      simulationsController.positionsThread.suspend();
      simulationsController.repaintThread.suspend();

      simulationsController.simulationsModel.modelZuruecksetzen();
      simulationsController.simulationsView.repaint();
      simulationsController.simulationsView.buttonPanel.sliderZuruecksetzen();
      simulationsController.simulationsView.simulationsPanel.zuruecksetzen();
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseClicked(MouseEvent event)
  {

  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent event)
  {
    mausPunkt = event.getPoint();
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
  public void mouseEntered(MouseEvent event)
  {
    event.getComponent()
        .setBackground(Color.decode("#0077d7"));
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent event)
  {
    event.getComponent()
        .setBackground(Color.decode("#131313"));
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
   */
  @Override
  public void mouseWheelMoved(MouseWheelEvent event)
  {
    int aenderung = event.getWheelRotation();
    simulationsController.simulationsModel.setZoomFaktor(aenderung);
    simulationsController.simulationsView.repaint();
  }

  /**
   * {@inheritDoc}
   * 
   * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
   */
  @Override
  public void stateChanged(ChangeEvent event)
  {
    JSlider source = (JSlider) event.getSource();
    int neueDt = source.getValue();
    simulationsController.simulationsModel.setDt(neueDt);
    simulationsController.simulationsView.buttonPanel.updateSliderLabel();
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseDragged(MouseEvent e)
  {
    int differenzX = e.getX() - mausPunkt.x;
    int differenzY = e.getY() - mausPunkt.y;
    Point differenz = new Point(differenzX, differenzY);
    simulationsController.simulationsView.simulationsPanel.updateUrsprung(differenz);
    simulationsController.simulationsView.repaint();
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent e)
  {

  }
}
