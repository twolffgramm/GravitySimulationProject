package de.continentale.zv.n_body_simulation.model;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class Szenario
{
  /** positionen */
  public Vector2D[] positionen;
  /** geschwindigkeiten */
  public Vector2D[] geschwindigkeiten;
  /** massen */
  public double[] massen;
  /** farben */
  public String[] farben;
  /** radii */
  public double[] radii;
  /** zoomFaktor */
  public double zoomFaktor;
  /** dt */
  public int dt;

  /**
   * Szenario Konstruktor.
   *
   * @param szenarioNummer
   */
  public Szenario(int szenarioNummer)
  {
    switch (szenarioNummer)
    {
      case 1: // Figure-Eight mit Erd-Massen und stellaren Abständen
        this.positionen = new Vector2D[] { new Vector2D(291.001308E6, 72.926259E6),
            new Vector2D(-291.001308E6, -72.926259E6), new Vector2D(0, 0) };
        double factor = 1150;
        this.geschwindigkeiten =
            new Vector2D[] { new Vector2D(0.93240737 / 2 * factor, -0.86473146 / 2 * factor),
                new Vector2D(0.93240737 / 2 * factor, -0.86473146 / 2 * factor),
                new Vector2D(-0.93240737 * factor, 0.86473146 * factor) };
        this.massen = new double[] { 5.972E24, 5.972E24, 5.972E24 };
        this.farben = new String[] { "#f3b02c", "#a0e160", "#ce2720" };
        this.radii = new double[] { 20, 20, 20 };
        this.dt = 250;
        this.zoomFaktor = 750000;
        break;

      case 2: // Figure-Eight mit Massen = 1 und kleinen Abständen
        this.positionen = new Vector2D[] { new Vector2D(0.97000436, -0.24308753),
            new Vector2D(-0.97000436, 0.24308753), new Vector2D(0, 0) };
        this.geschwindigkeiten = new Vector2D[] { new Vector2D(0.93240737 / 2, 0.86473146 / 2),
            new Vector2D(0.93240737 / 2, 0.86473146 / 2), new Vector2D(-0.93240737, -0.86473146) };
        this.massen = new double[] { 1, 1, 1 };
        this.dt = 1;
        this.zoomFaktor = 0.125;
        break;

      case 3: // Sonnensystem
        this.positionen = new Vector2D[] { new Vector2D(0, 0), new Vector2D(0, -69.8E9),
            new Vector2D(0, -108.9E9), new Vector2D(0, -152.1E9), new Vector2D(0, -249.2E9),
            new Vector2D(0, -816.6E9), new Vector2D(0, -1514.5E9), new Vector2D(0, -3003.6E9),
            new Vector2D(0, -4545.7E9), new Vector2D(0, -7304.3E9) };
        this.geschwindigkeiten = new Vector2D[] {
            new Vector2D(-(38860 * 0.330E24 + 34790 * 4.867E24 + 29290 * 5.972E24 + 21970 * 0.642E24
                + 12440 * 1898.190E24 + 9090 * 568.340E24 + 6490 * 86.813E24 + 5370 * 102.413E24
                + 3710 * 0.01303E24) / 1.989E30, 0), // extrem umständlich, aber nötig, um
            // Impulserhaltung zu garantieren (Sonne hat
            // entgegengesetzte Geschwindigkeit damit
            // Impuls gleich ist)
            new Vector2D(38860, 0), new Vector2D(34790, 0), new Vector2D(29290, 0),
            new Vector2D(21970, 0), new Vector2D(12440, 0), new Vector2D(9090, 0),
            new Vector2D(6490, 0), new Vector2D(5370, 0), new Vector2D(3710, 0) };
        this.massen = new double[] { 1.989E30, 0.330E24, 4.867E24, 5.972E24, 0.642E24, 1898.190E24,
            568.340E24, 86.813E24, 102.413E24, 0.01303E24 };
        this.farben = new String[] { "#f6e340", "#d0baa4", "#f3b02c", "#a0e160", "#ce2720",
            "#914c3f", "#c1a27b", "#74c5c7", "#273173", "#e6b891" };
        this.radii = new double[] { 35, 5, 12, 13, 7, 25, 20, 18, 18, 4 };
        this.dt = 31536;
        this.zoomFaktor = 2000000000;
        break;

    }
  }
}
