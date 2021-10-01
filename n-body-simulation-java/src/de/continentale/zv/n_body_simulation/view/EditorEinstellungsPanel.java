package de.continentale.zv.n_body_simulation.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import de.continentale.zv.n_body_simulation.controller.ButtonController;
import de.continentale.zv.n_body_simulation.model.SimulationsModel;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class EditorEinstellungsPanel extends JPanel
{
  SimulationsModel simulationsModel;
  ButtonController buttonController;
  JSlider masseSlider;
  JSlider xGeschwindigkeitSlider;
  JSlider yGeschwindigkeitSlider;
  JLabel masseLabel;
  JLabel xGeschwindigkeitLabel;
  JLabel yGeschwindigkeitLabel;
  JSpinner masseSpinner;
  SpinnerModel masseSpinnerModel;

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  EditorEinstellungsPanel(SimulationsModel simulationsModel)
  {
    this.simulationsModel = simulationsModel;
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(1000, 60));
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.setBackground(Color.decode("#1F1F1F"));
    
    masseSpinnerModel = new SpinnerNumberModel(0,0,350000,0.001);
    masseSpinner = new JSpinner(masseSpinnerModel);
    masseSpinner.setPreferredSize(new Dimension(1,30));
    masseSpinner.setName("masseSpinner");
    this.add(masseSpinner);
//    masseSlider = erstelleSlider(masseSlider,0,100000,0,"masseSlider");
//    masseLabel = erstelleLabel(masseLabel);
    xGeschwindigkeitSlider = erstelleSlider(xGeschwindigkeitSlider,-100,100,0,"xGeschwindigkeitSlider");
    xGeschwindigkeitLabel = erstelleLabel(xGeschwindigkeitLabel);
    yGeschwindigkeitSlider = erstelleSlider(yGeschwindigkeitSlider,-100,100,0,"yGeschwindigkeitSlider");
    yGeschwindigkeitLabel = erstelleLabel(yGeschwindigkeitLabel);
    registerListener(buttonController);
    updateSliderLabel();
  }
  
  void updateSlider(int aktPlanet) 
  {
//  	// update Slider-Wert für die Masse. Wert als Faktor einer Erdmasse.
//  	double masse = simulationsModel.getPlaneten().get(aktPlanet).getMasse();
//  	double faktor = masse / 5.972E24;
//  	masseSlider.setValue((int) (faktor * 10000));
  	
  	double masse = simulationsModel.getPlaneten().get(aktPlanet).getMasse();
  	double faktor = masse / 5.972E24;
  	masseSpinner.setValue(faktor);
  	
  	// update Slider-Wert für die x-Geschwindigkeit.
  	double xGeschwindigkeit = simulationsModel.getPlaneten().get(aktPlanet).getGeschwindigkeit().getX();
  	int wert = geschwindigkeitZuWert(xGeschwindigkeit);
  	xGeschwindigkeitSlider.setValue(wert);
  	
  	// update Slider-Wert für die y-Geschwindigkeit.
  	double yGeschwindigkeit = simulationsModel.getPlaneten().get(aktPlanet).getGeschwindigkeit().getY();
  	wert = geschwindigkeitZuWert(yGeschwindigkeit);
  	yGeschwindigkeitSlider.setValue(wert);
  	
  	updateSliderLabel();
  }
  
  // geschwindigkeiten von -10000 bis +10000 mittels exponentialfunktionen nichtlinear auf werte zwischen -100 und 100 für die slider mappen. 
  int geschwindigkeitZuWert(double geschwindigkeit) {
  	double a = 0.03;
  	int wert;
  	if (geschwindigkeit >= 0) 
  	{
      wert = (int) ((100 * a * geschwindigkeit + geschwindigkeit) / (100 + a * geschwindigkeit));
  	}
  	else 
  	{
      wert = (int) ((100 * a * geschwindigkeit + geschwindigkeit) / (100 - a * geschwindigkeit));
  	}
  	return wert;
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

  /**
   * 
   */
  public void updateSliderLabel()
  {
//  	double faktor = (double) masseSlider.getValue() / 10000;
//  	System.out.println(faktor);
//  	masseLabel.setText(String.format("%.2f", faktor) + " Erdmassen");
  	
  	int xWert = xGeschwindigkeitSlider.getValue();
  	double xGeschwindigkeit = wertZuGeschwindigkeit(xWert);
  	xGeschwindigkeitLabel.setText("x: " + String.format("%.2f", xGeschwindigkeit / 1000) + "km/s");
  	
  	int yWert = yGeschwindigkeitSlider.getValue();
  	double yGeschwindigkeit = wertZuGeschwindigkeit(yWert);
  	yGeschwindigkeitLabel.setText("y: " + String.format("%.2f", yGeschwindigkeit / 1000) + "km/s");
  }

  /**
   * 
   */
  public JSlider erstelleSlider(JSlider aktSlider, int min, int max, int wert, String name)
  {
  	aktSlider = new JSlider(min,max,wert);
  	aktSlider.setExtent(10);
  	aktSlider.setPreferredSize(new Dimension(100, 30));
  	aktSlider.setBackground(Color.decode("#111111"));
  	aktSlider.setOpaque(true);
  	aktSlider.setName(name);
    this.add(aktSlider);
    return aktSlider;
  }
  
  public JLabel erstelleLabel(JLabel sliderLabel)
  {
  	sliderLabel = new JLabel();
  	sliderLabel.setBackground(Color.decode("#1F1F1F"));
  	sliderLabel.setForeground(Color.WHITE);
  	this.add(sliderLabel);
  	return sliderLabel;
  }

  /**
   * @param buttonController .
   */
  public void registerListener(ButtonController buttonController)
  {
    this.buttonController = buttonController;
    Component[] components = this.getComponents();
    for (Component component : components)
    {
      if (component instanceof JSlider)
      {
        JSlider slider = (JSlider) component;
        slider.addChangeListener(buttonController);
      }
      if (component instanceof JSpinner)
      {
        JSpinner spinner = (JSpinner) component;
        spinner.addChangeListener(buttonController);
      }
    }
    
  }

  /**
   * 
   */
  public void zuruecksetzen()
  {
//    masseSlider.setValue(0);
  	masseSpinner.setValue((double) 0);
    xGeschwindigkeitSlider.setValue(0);
    yGeschwindigkeitSlider.setValue(0);
    updateSliderLabel();
  }
}