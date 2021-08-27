package de.continentale.zv.n_body_problem;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class SimulationFrame extends JFrame
{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  SimulationPanel simulationPanel;

  SimulationFrame()
  {
    simulationPanel = new SimulationPanel();
    this.add(simulationPanel);
    this.setTitle("Gravitational Attractor");
    // this.setResizable(false);
    this.setBackground(Color.BLACK);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }
}