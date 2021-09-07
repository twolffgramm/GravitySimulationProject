package de.continentale.zv.n_body_simulation.model;

/**
 * Eine Instanz der Klasse {@code Szenario} hält je nach Angabe des geschwünschten Szenarios Arrays
 * an {@code Vector2D[]} Positionen, {@code Vector2D[]} Geschwindigkeiten, {@code double[]} Massen,
 * {@code String[]} Farben, sowie {@code double[]} Radii, die die Startkonditionen darstellen.
 * Zusätzlich enthält die Instanz einen {@code double} zoomFaktor und ein {@code int} dt -
 * delta-Zeit.
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class Szenario
{
  /**
   * Startpositionen der Planeten
   */
  Vector2D[] positionen;

  /**
   * Startgeschwindigkeiten der Planeten
   */
  Vector2D[] geschwindigkeiten;

  /**
   * Massen der Planeten
   */
  double[] massen;

  /**
   * Farben der Planeten
   */
  String[] farben;

  /**
   * Radii der Planeten
   */
  double[] radii;

  /**
   * zoomFaktor - wird benötigt um Szenarien mit kleinsten Abständen (wenige hundert Meter) aber
   * auch interstellaren Abständen (~E9 Meter) darzustellen. Wird zusätzlich bei Mausrad-Bewegung
   * verändert um Zoomen zu ermöglichen.
   */
  double zoomFaktor;

  /**
   * delta-Time - gibt an wie lange die Kräfte & Geschwindigkeiten pro Berechnung wirken sollen - in
   * Sekunden.
   */
  int dt;

  /**
   * Konstruiert eine
   *
   * @param szenarioNummer .
   */
  public Szenario(int szenarioNummer)
  {
    switch (szenarioNummer)
    {
      case 0:
        this.positionen = new Vector2D[0];
        this.geschwindigkeiten = new Vector2D[0];
        this.massen = new double[0];
        this.dt = 150;
        this.zoomFaktor = 750000;
        break;
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

      case 2: // Sonnensystem
        this.positionen = new Vector2D[] { new Vector2D(0, 0), new Vector2D(0, -69.8E9),
            new Vector2D(0, -108.9E9), new Vector2D(0, -152.1E9), new Vector2D(0, -249.2E9),
            new Vector2D(0, -816.6E9), new Vector2D(0, -1514.5E9), new Vector2D(0, -3003.6E9),
            new Vector2D(0, -4545.7E9), new Vector2D(0, -7304.3E9) };
        this.geschwindigkeiten = new Vector2D[] {
            new Vector2D(-(38860 * 0.330E24 + 34790 * 4.867E24 + 29290 * 5.972E24 + 21970 * 0.642E24
                + 12440 * 1898.190E24 + 9090 * 568.340E24 + 6490 * 86.813E24 + 5370 * 102.413E24
                + 3710 * 0.01303E24) / 1.989E30, 0), // extrem umständlich, aber nötig,
                                                     // um
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

      case 3: // Binary-Star System
        this.positionen =
            new Vector2D[] { new Vector2D(-100, 0), new Vector2D(100, 0), new Vector2D(0, 400) };
        this.geschwindigkeiten = new Vector2D[] { new Vector2D(0, 0.0003), new Vector2D(0, -0.0003),
            new Vector2D(0.0006, 0) };
        this.massen = new double[] { 1000000, 1000000, 100 };
        this.farben = new String[] { "#f3b02c", "#a0e160", "#ce2720" };
        this.radii = new double[] { 20, 20, 10 };
        this.dt = 350;
        this.zoomFaktor = 2;
        break;

      case 4: // Figure-Eight mit Erd-Massen und stellaren Abständen
        double[] position1 = { 100, 0 };
        double[] position2 = rotiere(-2.0944, position1);
        double[] position3 = rotiere(-2.0944, position2);
        this.positionen = new Vector2D[] { new Vector2D(position1[0], position1[1]),
            new Vector2D(position2[0], position2[1]), new Vector2D(position3[0], position3[1]) };
        double[] gesch1 = { 0, -0.0005 };
        double[] gesch2 = rotiere(-2.0944, gesch1);
        double[] gesch3 = rotiere(-2.0944, gesch2);
        this.geschwindigkeiten = new Vector2D[] { new Vector2D(gesch1[0], gesch1[1]),
            new Vector2D(gesch2[0], gesch2[1]), new Vector2D(gesch3[0], gesch3[1]) };
        this.massen = new double[] { 1000000, 1000000, 1000000 };
        this.farben = new String[] { "#f3b02c", "#a0e160", "#ce2720" };
        this.radii = new double[] { 20, 20, 20 };
        this.dt = 120;
        this.zoomFaktor = 0.5;
        break;
    }
  }

  double[] rotiere(double radianten, double[] punkt)
  {
    double x = Math.cos(radianten) * punkt[0] - Math.sin(radianten) * punkt[1];
    double y = Math.cos(radianten) * punkt[1] + Math.sin(radianten) * punkt[0];
    double[] rotierterPunkt = { x, y };
    return rotierterPunkt;
  }

  /**
   * @return .
   */
  public Vector2D[] getPositionen()
  {
    return this.positionen;
  }

  /**
   * @return .
   */
  public Vector2D[] getGeschwindigkeiten()
  {
    return this.geschwindigkeiten;
  }

  /**
   * @return .
   */
  public double[] getMassen()
  {
    return this.massen;
  }

  /**
   * @return .
   */
  public String[] getFarben()
  {
    return this.farben;
  }

  /**
   * @return .
   */
  public double[] getRadii()
  {
    return this.radii;
  }
}
