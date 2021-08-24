package MoverAttractor;

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
  SimulationPanel simulationPanel;

  SimulationFrame()
  {
    simulationPanel = new SimulationPanel();
    this.add(simulationPanel);
    this.setTitle("Gravitational Attractor");
    this.setResizable(false);
    this.setBackground(Color.black);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }
}
