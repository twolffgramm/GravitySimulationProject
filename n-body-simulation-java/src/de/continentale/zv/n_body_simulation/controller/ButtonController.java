package de.continentale.zv.n_body_simulation.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JSpinner;
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
  boolean menuePanelSichtbarkeit;
  boolean einstellungsPanelSichtbarkeit;

  /**
   * ButtonController Konstruktor.
   * 
   * @param simulationsController .
   */
  public ButtonController(SimulationsController simulationsController)
  {
    this.simulationsController = simulationsController;
    gestartet = false;
    menuePanelSichtbarkeit = true;
    einstellungsPanelSichtbarkeit = true;
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
    switch (command)
    {
    	case "szenarien":
    		simulationsController.simulationsView.setMenuePanelSichtbarkeit(menuePanelSichtbarkeit);
    		menuePanelSichtbarkeit = !menuePanelSichtbarkeit;
    		break;
    		
    	case "start":
        if (gestartet == false)
        {
          simulationsController.positionsThread.start();
          simulationsController.repaintThread.start();
          gestartet = true;
        }
        else
        {
        	simulationsController.positionsThread.resume();
        	simulationsController.repaintThread.resume();
        }
        break;
      
    	case "pause":
        simulationsController.positionsThread.suspend();
        simulationsController.repaintThread.suspend();
    		break;
  		
    	case "zuruecksetzen":
        simulationsController.positionsThread.suspend();
        simulationsController.repaintThread.suspend();
        
  			simulationsController.simulationsModel.setPlanetAusgewaehlt(false);
        simulationsController.simulationsModel.modelZuruecksetzen();
        simulationsController.simulationsView.zuruecksetzen();
        simulationsController.simulationsView.repaint();
    		break;
    		
    	case "editor":
    		if (gestartet)
        {
          simulationsController.positionsThread.suspend();
          simulationsController.repaintThread.suspend();
        }
//        szenarioSetzen(0);
        simulationsController.simulationsView.setEinstellungsPanel(einstellungsPanelSichtbarkeit);
        einstellungsPanelSichtbarkeit = !einstellungsPanelSichtbarkeit;
    		break;
    		
    	case "Figure-Eight":
    		szenarioSetzen(1);
        setMenuePanelSichtbarkeit();
    		break;
    		
    	case "Sonnensystem":
    		szenarioSetzen(2);
        setMenuePanelSichtbarkeit();
    		break;
    		
    	case "Binary-Star System":
    		szenarioSetzen(3);
        setMenuePanelSichtbarkeit();
    		break;
    		
    	case "Chaos Dreieck":
    		szenarioSetzen(4);
        setMenuePanelSichtbarkeit();
    		break;
    		
    	case "listePlaneten":
  			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
      	String relativerPlanet = (String) comboBox.getSelectedItem();
      	if (relativerPlanet == null || relativerPlanet.equals(""))
      	{
      		simulationsController.simulationsModel.setRelativeAnzeige(false);
      	}
      	else
      	{
      		simulationsController.simulationsModel.setRelativeAnzeige(true);
      		simulationsController.simulationsModel.setRelativerPlanet(relativerPlanet);
      	}
    		simulationsController.simulationsView.repaint();
    		break;
    		
    	case "repulsion":
    		simulationsController.simulationsModel.setRepulsion();
    		break;
    		
  		default:
  			break;
    }
  }

	void szenarioSetzen(int szenario)
  {
    simulationsController.positionsThread.suspend();
    simulationsController.repaintThread.suspend();

    simulationsController.simulationsModel.setAktuellesSzenario(szenario);
    simulationsController.simulationsModel.modelZuruecksetzen();
    simulationsController.simulationsView.zuruecksetzen();
    simulationsController.simulationsView.repaint();

  }
  
  void setMenuePanelSichtbarkeit()
  {
    simulationsController.simulationsView.setMenuePanelSichtbarkeit(menuePanelSichtbarkeit);
    menuePanelSichtbarkeit = !menuePanelSichtbarkeit;
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
  	Object source = event.getSource();
  	JSlider slider = null;
  	JSpinner spinner = null;
  	String name = "";
  	
  	if (source instanceof JSlider)
  	{
  		slider = (JSlider) source;
  		name = slider.getName();
  	}
  	else if (source instanceof JSpinner)
  	{
  		spinner = (JSpinner) source;
  		name = spinner.getName();
  	}
  	
//    JSlider slider = (JSlider) event.getSource();
//    String name = slider.getName();
    switch (name)
    {
    	case "animationsGeschwindigkeit":
        int neueDt = slider.getValue();
        simulationsController.simulationsModel.setDt(neueDt);
        simulationsController.simulationsView.updateAnimationsgeschwindigkeitLabel();
    		break;
    		
    	case "minimalerAbstand":
        int minimalerAbstand = slider.getValue();
        simulationsController.simulationsModel.setMinimalerAbstand(minimalerAbstand);
    		break;
    		
    	case "masseSpinner":
    		double masse = ((double) spinner.getValue()) * 5.972E24;
    		simulationsController.simulationsModel.aendereMasseVonSpinner(masse);
    		break;
    		
    	case "xGeschwindigkeitSlider":
      	int wert = slider.getValue();
      	double geschwindigkeit = wertZuGeschwindigkeit(wert);
      	simulationsController.simulationsModel.aendereGeschwindigkeitVonSlider(geschwindigkeit,0);
      	simulationsController.simulationsView.updateEinstellungsLabel();
    		break;
    		
    	case "yGeschwindigkeitSlider":
      	wert = slider.getValue();
      	geschwindigkeit = wertZuGeschwindigkeit(wert);
      	simulationsController.simulationsModel.aendereGeschwindigkeitVonSlider(0,geschwindigkeit);
      	simulationsController.simulationsView.updateEinstellungsLabel();
    		break;
    		
    	default:
        int index = Integer.parseInt(name);
        double faktor = (double) slider.getValue() / 100;
        double neueMasse = 5.972E24 * faktor;
        simulationsController.simulationsModel.getPlaneten()
            .get(index)
            .setMasse(neueMasse);
//        simulationsController.simulationsView.updateSliderLabel();
    		break;
    }
  }
  
  // werte von -100 bis +100 mittels exponentialfunktionen nichtlinear auf geschwindigkeiten zwischen -10000 und +10000 für planeten mappen. 
  double wertZuGeschwindigkeit(int wert) {
  	double a = 0.03;
  	double geschwindigkeit;
  	if (wert >= 0) 
  	{
  		geschwindigkeit = (100 * wert) / (a * (100 - wert) + 1);
  	}
  	else
  	{
  		geschwindigkeit = (100 * wert) / (a * (wert + 100) + 1);
  	}
  	return geschwindigkeit;
  }
}