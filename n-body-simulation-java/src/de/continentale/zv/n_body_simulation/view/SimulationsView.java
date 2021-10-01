package de.continentale.zv.n_body_simulation.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import de.continentale.zv.n_body_simulation.controller.ButtonController;
import de.continentale.zv.n_body_simulation.controller.InteraktionsController;
import de.continentale.zv.n_body_simulation.model.SimulationsModel;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class SimulationsView extends JFrame
{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  ButtonPanel buttonPanel;
  SimulationsPanel simulationsPanel;
  MenuePanel menuePanel;
  SimulationsModel simulationsModel;
  EditorEinstellungsPanel einstellungsPanel;
  JPanel hauptPanel;

  /**
   * SimulationsView Konstruktor.
   * 
   * @param simulationsModel .
   *
   */
  public SimulationsView(SimulationsModel simulationsModel)
  {
    this.simulationsModel = simulationsModel;

    buttonPanel = new ButtonPanel(this.simulationsModel);
    simulationsPanel = new SimulationsPanel(this.simulationsModel, 1000, 900);
    einstellungsPanel = new EditorEinstellungsPanel(this.simulationsModel);
    menuePanel = new MenuePanel();
    menuePanel.setAlignmentY(0.47f);
    menuePanel.setVisible(false);

    hauptPanel = new JPanel();
    GroupLayout groupLayout = new GroupLayout(hauptPanel);
    groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
        .addComponent(buttonPanel)
        .addComponent(einstellungsPanel)
        .addComponent(simulationsPanel));
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
        .addComponent(buttonPanel)
        .addComponent(einstellungsPanel)
        .addComponent(simulationsPanel));
    hauptPanel.setLayout(groupLayout);
    
    einstellungsPanel.setVisible(false);

    Container contentPane = this.getContentPane();
    OverlayLayout overlayLayout = new OverlayLayout(contentPane);
    contentPane.setLayout(overlayLayout);

    this.add(menuePanel);
    this.add(hauptPanel);

    this.setTitle("Gravitations Simulation");
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(Color.decode("#464646"));
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }

  void paint()
  {
      simulationsPanel.repaint();
  }

  /**
   * @param buttonController .
   * @param interaktionsController .
   */
  public void registerListener(ButtonController buttonController,
      InteraktionsController interaktionsController)
  {
    buttonPanel.registerListener(buttonController);
    menuePanel.registerListener(buttonController);
    einstellungsPanel.registerListener(buttonController);
    simulationsPanel.registerListener(interaktionsController);
  }

  /**
   * 
   */
  public void zuruecksetzen()
  {
  	updateListePlaneten();
    buttonPanel.sliderZuruecksetzen();
    simulationsPanel.zuruecksetzen();
    einstellungsPanel.zuruecksetzen();
  }

  /**
   * @param differenz .
   */
  public void updateUrsprung(Point differenz)
  {
      simulationsPanel.updateUrsprung(differenz);
  }

  /**
   * 
   */
  public void updateEinstellungsSlider(int ausgewaehlterPlanet)
  {
    einstellungsPanel.updateSlider(ausgewaehlterPlanet);
  }
  
  public void updateEinstellungsLabel()
  {
  	einstellungsPanel.updateSliderLabel();
  }
  
  public void updateAnimationsgeschwindigkeitLabel()
  {
    buttonPanel.updateSliderLabel();
  }
  
  public void updateListePlaneten()
  {
  		buttonPanel.updateListePlaneten();
  }

  /**
   * 
   */
  public void erstelleSlider()
  {
//    editorPanel.erstelleSlider();
  }

  /**
   * @param mausGedrueckt .
   * @param koordinatenMausGedrueckt .
   */
  public void setMausGedrueckt(boolean mausGedrueckt, Point koordinatenMausGedrueckt)
  {
      simulationsPanel.setMausGedrueckt(mausGedrueckt, koordinatenMausGedrueckt);
  }

  /**
   * @param geschwindigkeitsLinie .
   */
  public void setGeschwindigkeitsLinie(Point geschwindigkeitsLinie)
  {
      simulationsPanel.setGeschwindigkeitsLinie(geschwindigkeitsLinie);
  }

  /**
   * @param sichtbar .
   */
  public void setMenuePanelSichtbarkeit(boolean sichtbar)
  {
    menuePanel.setVisible(sichtbar);
  }

  /**
   * @return .
   */
  public Point getUrsprung()
  {
      return simulationsPanel.getUrsprung();
  }

  /**
   * @return .
   */
  public Point getLinksOben()
  {
  	return simulationsPanel.getLinksOben();
  }
  
  public void setEinstellungsPanel(boolean sichtbarkeit) {
  	einstellungsPanel.setVisible(sichtbarkeit);
  }
}