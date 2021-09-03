package de.continentale.zv.n_body_simulation.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import de.continentale.zv.n_body_simulation.controller.ButtonController;
import de.continentale.zv.n_body_simulation.controller.SimulationsInteraktionsController;
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
  /** simulationsPanel */
  public SimulationsPanel simulationsPanel;
  MenuePanel menuePanel;
  EditorPanel editorPanel;
  SimulationsModel simulationsModel;
  JPanel hauptPanel;
  boolean isEditor;

  /**
   * SimulationsView Konstruktor.
   * 
   * @param simulationsModel
   *
   */
  public SimulationsView(SimulationsModel simulationsModel)
  {
    this.isEditor = false;
    this.simulationsModel = simulationsModel;
    buttonPanel = new ButtonPanel(this.simulationsModel);
    simulationsPanel = new SimulationsPanel(this.simulationsModel, 1000, 1000);

    editorPanel = new EditorPanel(this.simulationsModel, 800, 1000);

    menuePanel = new MenuePanel();
    menuePanel.setAlignmentY(0.47f);
    hauptPanel = new JPanel();

    GroupLayout groupLayout = new GroupLayout(hauptPanel);
    hauptPanel.setLayout(groupLayout);

    hauptPanel.add(buttonPanel);
    hauptPanel.add(simulationsPanel);

    groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
        .addComponent(buttonPanel)
        .addComponent(simulationsPanel));
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
        .addComponent(buttonPanel)
        .addComponent(simulationsPanel));

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
    menuePanel.setVisible(false);
  }

  void paint()
  {
    if (isEditor)
    {
      editorPanel.repaint();
    }
    else
    {
      simulationsPanel.repaint();
    }
  }

  /**
   * @param buttonController
   * @param simulationsInteraktionsController
   */
  public void registerListener(ButtonController buttonController,
      SimulationsInteraktionsController simulationsInteraktionsController)
  {
    buttonPanel.registerListener(buttonController);
    menuePanel.registerListener(buttonController);
    simulationsPanel.registerListener(simulationsInteraktionsController);
    editorPanel.registerListener(buttonController, simulationsInteraktionsController);
  }

  /**
   * @param sichtbar
   */
  public void setMenuePanelSichtbarkeit(boolean sichtbar)
  {
    menuePanel.setVisible(sichtbar);
  }

  /**
   * 
   */
  public void zuruecksetzen()
  {
    if (isEditor == false)
    {
      buttonPanel.sliderZuruecksetzen();
      simulationsPanel.zuruecksetzen();
    }
    else
    {
      editorPanel.zuruecksetzen();
    }
  }

  /**
   * @param differenz
   */
  public void updateUrsprung(Point differenz)
  {
    if (isEditor == false)
    {
      simulationsPanel.updateUrsprung(differenz);
    }
    else
    {
      editorPanel.updateUrsrpung(differenz);
    }
  }

  /**
   * 
   */
  public void updateSlider()
  {
    buttonPanel.updateSliderLabel();
  }

  /**
   * @param isEditor
   */
  public void wechsleModus()
  {
    if (isEditor == false)
    {
      this.add(editorPanel);
      hauptPanel.setVisible(false);
      editorPanel.setVisible(true);

    }
    else
    {
      hauptPanel.setVisible(true);
      editorPanel.setVisible(false);
    }
    isEditor = !isEditor;
  }

  /**
   * @return .
   */
  public boolean getIsEditor()
  {
    return this.isEditor;
  }
}
