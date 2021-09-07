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
  SimulationsPanel simulationsPanel;
  MenuePanel menuePanel;
  EditorPanel editorPanel;
  SimulationsModel simulationsModel;
  JPanel hauptPanel;
  boolean isEditor;

  /**
   * SimulationsView Konstruktor.
   * 
   * @param simulationsModel .
   *
   */
  public SimulationsView(SimulationsModel simulationsModel)
  {
    this.isEditor = false;
    this.simulationsModel = simulationsModel;

    buttonPanel = new ButtonPanel(this.simulationsModel);
    simulationsPanel = new SimulationsPanel(this.simulationsModel, 1000, 1000);
    editorPanel = new EditorPanel(this.simulationsModel, 1000, 950);
    menuePanel = new MenuePanel();
    menuePanel.setAlignmentY(0.47f);
    menuePanel.setVisible(false);

    hauptPanel = new JPanel();
    GroupLayout groupLayout = new GroupLayout(hauptPanel);
    groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
        .addComponent(buttonPanel)
        .addComponent(simulationsPanel));
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
        .addComponent(buttonPanel)
        .addComponent(simulationsPanel));
    hauptPanel.setLayout(groupLayout);

    Container contentPane = this.getContentPane();
    OverlayLayout overlayLayout = new OverlayLayout(contentPane);
    contentPane.setLayout(overlayLayout);

    this.add(menuePanel);
    this.add(hauptPanel);
    this.add(editorPanel);

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
   * @param buttonController .
   * @param simulationsInteraktionsController .
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
   * @param differenz .
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
  public void updateSliderLabel()
  {
    buttonPanel.updateSliderLabel();
    if (isEditor)
    {
      editorPanel.updateSliderLabel();
    }
  }

  /**
   * 
   */
  public void erstelleSlider()
  {
    editorPanel.erstelleSlider();
  }

  /**
   */
  public void wechsleModus()
  {
    if (isEditor == false)
    {

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
   * @param mausGedrueckt .
   * @param koordinatenMausGedrueckt .
   */
  public void setMausGedrueckt(boolean mausGedrueckt, Point koordinatenMausGedrueckt)
  {
    if (isEditor == false)
    {
      simulationsPanel.setMausGedrueckt(mausGedrueckt, koordinatenMausGedrueckt);
    }
    else
    {
      editorPanel.setMausGedrueckt(mausGedrueckt, koordinatenMausGedrueckt);
    }
  }

  /**
   * @param geschwindigkeitsLinie .
   */
  public void setGeschwindigkeitsLinie(Point geschwindigkeitsLinie)
  {
    if (isEditor == false)
    {
      simulationsPanel.setGeschwindigkeitsLinie(geschwindigkeitsLinie);
    }
    else
    {
      editorPanel.setGeschwindigkeitsLinie(geschwindigkeitsLinie);
    }
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
  public boolean getIsEditor()
  {
    return this.isEditor;
  }

  /**
   * @return .
   */
  public Point getUrsprung()
  {
    if (isEditor == false)
    {
      return simulationsPanel.getUrsprung();
    }
    else
    {
      return editorPanel.getUrsprung();
    }
  }

  /**
   * @return .
   */
  public Point getLinksOben()
  {
    return editorPanel.getLinksOben();
  }

  /**
   * @return .
   */
  public boolean getRepulsion()
  {
    if (isEditor == false)
    {
      return buttonPanel.getRepulsion();
    }
    else
    {
      return editorPanel.getRepulsion();
    }
  }
}