package de.continentale.zv.n_body_simulation.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
public class ButtonController implements ActionListener, MouseListener, ChangeListener
{
  SimulationsController simulationsController;
  boolean gestartet;
  boolean sichtbarkeit;

  /**
   * ButtonController Konstruktor.
   * 
   * @param simulationsController .
   */
  public ButtonController(SimulationsController simulationsController)
  {
    this.simulationsController = simulationsController;
    gestartet = false;
    sichtbarkeit = true;
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
      simulationsController.simulationsView.setMenuePanelSichtbarkeit(sichtbarkeit);
      sichtbarkeit = !sichtbarkeit;
    }
    else if (command.equals("start"))
    {
      if (gestartet == false)
      {
        simulationsController.positionsThread.start();
        simulationsController.repaintThread.start();
        gestartet = true;
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

      simulationsController.simulationsView.zuruecksetzen();
      simulationsController.simulationsModel.modelZuruecksetzen();
      simulationsController.simulationsView.repaint();

    }
    else if (command.equals("editor"))
    {
      if (gestartet)
      {
        simulationsController.positionsThread.suspend();
        simulationsController.repaintThread.suspend();
      }
      szenarioSetzen(0);
      simulationsController.simulationsView.wechsleModus();
      simulationsController.simulationsView.erstelleSlider();
    }
    else if (command.equals("Figure-Eight"))
    {
      szenarioSetzen(1);
      simulationsController.simulationsView.setMenuePanelSichtbarkeit(sichtbarkeit);
      sichtbarkeit = !sichtbarkeit;
      if (simulationsController.simulationsView.getIsEditor())
      {
        simulationsController.simulationsView.wechsleModus();
      }
    }
    else if (command.equals("Sonnensystem"))
    {
      szenarioSetzen(2);
      simulationsController.simulationsView.setMenuePanelSichtbarkeit(sichtbarkeit);
      sichtbarkeit = !sichtbarkeit;
      if (simulationsController.simulationsView.getIsEditor())
      {
        simulationsController.simulationsView.wechsleModus();
      }
    }
    else if (command.equals("Binary-Star System"))
    {
      szenarioSetzen(3);
      simulationsController.simulationsView.setMenuePanelSichtbarkeit(sichtbarkeit);
      sichtbarkeit = !sichtbarkeit;
      if (simulationsController.simulationsView.getIsEditor())
      {
        simulationsController.simulationsView.wechsleModus();
      }
    }
    else if (command.equals("Chaos Dreieck"))
    {
      szenarioSetzen(4);
      simulationsController.simulationsView.setMenuePanelSichtbarkeit(sichtbarkeit);
      sichtbarkeit = !sichtbarkeit;
      if (simulationsController.simulationsView.getIsEditor())
      {
        simulationsController.simulationsView.wechsleModus();
      }
    }
  }

  @SuppressWarnings("deprecation")
  void szenarioSetzen(int szenario)
  {
    simulationsController.positionsThread.suspend();
    simulationsController.repaintThread.suspend();

    simulationsController.simulationsModel.setAktuellesSzenario(szenario);
    simulationsController.simulationsModel.modelZuruecksetzen();
    simulationsController.simulationsView.zuruecksetzen();
    simulationsController.simulationsView.repaint();

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
   * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
   */
  @Override
  public void stateChanged(ChangeEvent event)
  {
    JSlider slider = (JSlider) event.getSource();
    String name = slider.getName();
    if (name.equals("animationsGeschwindigkeit"))
    {
      int neueDt = slider.getValue();
      simulationsController.simulationsModel.setDt(neueDt);
      simulationsController.simulationsView.updateSliderLabel();
    }
    else if (name.equals("minimalerAbstand"))
    {
      int minimalerAbstand = slider.getValue();
      simulationsController.simulationsModel.setMinimalerAbstand(minimalerAbstand);
    }
    else
    {
      int index = Integer.parseInt(name);
      double faktor = (double) slider.getValue() / 100;
      double neueMasse = 5.972E24 * faktor;
      simulationsController.simulationsModel.getPlaneten()
          .get(index)
          .setMasse(neueMasse);
      simulationsController.simulationsView.updateSliderLabel();
    }
  }
}