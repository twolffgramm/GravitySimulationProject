package de.continentale.zv.n_body_simulation.view;

import java.awt.Color;
import java.awt.Container;

import javax.swing.GroupLayout;
import javax.swing.JFrame;

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

  /**
   * SimulationsView Konstruktor.
   * 
   * @param simulationsModel
   *
   */
  public SimulationsView(SimulationsModel simulationsModel)
  {
    this.simulationsModel = simulationsModel;
    buttonPanel = new ButtonPanel();
    simulationsPanel = new SimulationsPanel(this.simulationsModel);

    Container contentPane = this.getContentPane();
    GroupLayout layout = new GroupLayout(contentPane);
    contentPane.setLayout(layout);

    contentPane.add(buttonPanel);
    contentPane.add(simulationsPanel);

    layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(buttonPanel)
        .addComponent(simulationsPanel));
    layout.setHorizontalGroup(layout.createParallelGroup()
        .addComponent(buttonPanel)
        .addComponent(simulationsPanel));

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
    this.simulationsPanel.repaint();
  }
}
