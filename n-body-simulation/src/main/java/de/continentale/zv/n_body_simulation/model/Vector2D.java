package de.continentale.zv.n_body_simulation.model;

/**
 * Eine Instanz der Klasse {@code Vector2D} hält einen 2-dimensionalen Vektor
 * ({@code double x, double y}). Die Klasse {@code Vector2D} enthält zusätzlich die lineare Algebra
 * für 2-dimensionale Vektoren in statisch- und nicht-statischer Variante.
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class Vector2D
{
  // x und y Komponente des Vektors
  double x;
  double y;

  /**
   * Konstruiert und Initialisiert einen Null-Vektor (0, 0).
   */
  public Vector2D()
  {
    this.x = 0;
    this.y = 0;
  }

  /**
   * Konstruiert einen {@code Vector2D} und initialisiert ihn mit der angegebenen x- und
   * y-Koordinate.
   *
   * @param x die x-Koordinate des neuen {@code Vector2D}
   * @param y die y-Koordinate des neuen {@code Vector2D}
   */
  public Vector2D(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  /**
   * Bildet einen String "(x, y)" aus dem {@code Vector2D} und gibt diesen aus.
   * 
   * @return eine String-Repräsentation eines {@code Vector2D}
   */
  @Override
  public String toString()
  {
    String vector = "(";
    vector += this.x + ",\n";
    vector += this.y + ")";
    return vector;
  }

  /**
   * Nicht-statische Vektor-Addition zum {@code Vector2D} auf dem die Methode aufgerufen wurde. Ruft
   * die statische Methode {@code Vector2D.sum(Vector2D v, Vector2D u)} mit dem aufgerufenen
   * {@code Vector2D v} und dem angebenen {@code Vector2D u} auf.
   * 
   * @param u der {@code Vector2D} der addiert werden soll.
   * @return einen {@code Vector2D} der die Vektor-Summe darstellt.
   */
  public Vector2D add(Vector2D u)
  {
    return Vector2D.sum(this, u);
  }

  /**
   * Statische Vektor-Addition der beiden {@code Vector2D v, Vector2D u}. Addiert {@code u} zu
   * {@code v}.
   * 
   * @param v {@code Vector2D} - Summand der Addition.
   * @param u {@code Vector2D} - Summand der Addition.
   * @return einen {@code Vector2D} der die Vektor-Summe darstellt.
   */
  public static Vector2D sum(Vector2D v, Vector2D u)
  {
    double sumX;
    double sumY;
    Vector2D sum;

    sumX = v.getX() + u.getX();
    sumY = v.getY() + u.getY();
    sum = new Vector2D(sumX, sumY);

    return sum;
  }

  /**
   * Nicht-statische Vektor-Subtraktion vom {@code Vector2D} auf dem die Methode aufgerufen wurde.
   * Ruft die statische Methode {@code Vector2D.difference(Vector2D v, Vector2D u)} mit dem
   * aufgerufenen {@code Vector2D v} und dem angebenen {@code Vector2D u} auf.
   * 
   * @param u der {@code Vector2D} der subtrahiert werden soll.
   * @return einen {@code Vector2D} der die Vektor-Differenz darstellt.
   */
  public Vector2D subtract(Vector2D u)
  {
    return Vector2D.difference(this, u);
  }

  /**
   * Statische Vektor-Subtraktion der beiden {@code Vector2D v, Vector2D u}. Subtrahiert {@code u}
   * von {@code v}.
   * 
   * @param v {@code Vector2D} - Minuend der Subtraktion.
   * @param u {@code Vector2D} - Subtrahend der Subtraktion.
   * @return einen {@code Vector2D} der die Vektor-Differenz darstellt.
   */
  public static Vector2D difference(Vector2D v, Vector2D u)
  {
    double differenceX;
    double differenceY;
    Vector2D difference;

    differenceX = v.getX() - u.getX();
    differenceY = v.getY() - u.getY();
    difference = new Vector2D(differenceX, differenceY);

    return difference;
  }

  /**
   * Nicht-statische Vektor-Multiplikation des {@code Vector2D} auf dem die Methode aufgerufen
   * wurde. Ruft die statische Methode {@code Vector2D.product(Vector2D v, double scalar)} mit dem
   * aufgerufenen {@code Vector2D v} und dem angebenen {@code double scalar} auf.
   * 
   * @param scalar der {@code double} mit dem multipliziert werden soll.
   * @return einen {@code Vector2D} der das Vektor-Produkt darstellt.
   */
  public Vector2D multiply(double scalar)
  {
    return Vector2D.product(this, scalar);
  }

  /**
   * Statische Vektor-Multiplikation des {@code Vector2D v} mit dem {@code double scalar}.
   * 
   * @param v {@code Vector2D} - Multiplikant der Multiplikation.
   * @param scalar - Multiplikator der Multiplikation.
   * @return einen {@code Vector2D} der das Vektor-Produkt darstellt.
   */
  public static Vector2D product(Vector2D v, double scalar)
  {
    double productX;
    double productY;
    Vector2D product;

    productX = v.getX() * scalar;
    productY = v.getY() * scalar;
    product = new Vector2D(productX, productY);

    return product;
  }

  /**
   * Nicht-statische Berechnung des Skalarprodukts des {@code Vector2D} auf dem die Methode
   * aufgerufen wurde mit dem angebenen {@code Vector2D u}. Ruft die statische Methode
   * {@code Vector2D.dotProduct(Vector2D v, Vector2D u)} mit dem aufgerufenen {@code Vector2D v} und
   * dem angebenen {@code Vector2D u} auf.
   * 
   * @param u der {@code Vector2D} mit dem das Skalarprodukt gebildet wird.
   * @return ein {@code double}, das Skalarprodukt.
   */
  public double dot(Vector2D u)
  {
    return Vector2D.dotProduct(this, u);
  }

  /**
   * Statische Berechnung des Skalarprodukts der angegebenen {@code Vector2D v} und
   * {@code Vector2D u}.
   * 
   * @param v {@code Vector2D} - Vektor mit dem das Skalarprodukt gebildet wird.
   * @param u {@code Vector2D} - Vektor mit dem das Skalarprodukt gebildet wird.
   * @return ein {@code double}, das Skalarprodukt.
   */
  public static double dotProduct(Vector2D v, Vector2D u)
  {
    double sum = 0;
    sum += v.getX() * u.getX();
    sum += v.getY() * u.getY();
    return sum;
  }

  /**
   * Nicht-statische Berechnung des Kreuzprodukts des {@code Vector2D} auf dem die Methode
   * aufgerufen wurde mit dem angebenen {@code Vector2D u}. Ruft die statische Methode
   * {@code Vector2D.drossProduct(Vector2D v, Vector2D u)} mit dem aufgerufenen {@code Vector2D v}
   * und dem angebenen {@code Vector2D u} auf.
   * <p>
   * Das Kreuzprodukt zweier 2-dimensional Vektoren gibt keinen Vektor, der orthogonal zu beiden
   * Vektoren steht (wie bei drei- oder mehrdimensionalen Vektoren) aus, sondern ein Skalar - die
   * Determinante der beiden Vektoren.
   * 
   * 
   * @param u der {@code Vector2D} mit dem das Kreuzprodukt gebildet wird.
   * @return ein {@code double}, das Kreuzprodukt (Determinante).
   */
  public double cross(Vector2D u)
  {
    return Vector2D.crossProduct(this, u);
  }

  /**
   * Statische Berechnung des Kreuzprodukts der angegebenen {@code Vector2D v} und
   * {@code Vector2D u}.
   * <p>
   * Das Kreuzprodukt zweier 2-dimensional Vektoren gibt keinen Vektor, der orthogonal zu beiden
   * Vektoren steht (wie bei drei- oder mehrdimensionalen Vektoren) aus, sondern ein Skalar - die
   * Determinante der beiden Vektoren.
   * 
   * @param v {@code Vector2D} - Vektor mit dem das Kreuzprodukt gebildet wird.
   * @param u {@code Vector2D} - Vektor mit dem das Kreuzprodukt gebildet wird.
   * @return ein {@code double}, das Kreuzprodukt (Determinante).
   */
  public static double crossProduct(Vector2D v, Vector2D u)
  {
    double determinant = 0;
    determinant += v.getX() * u.getY();
    determinant -= u.getX() * v.getY();
    return determinant;
  }

  /**
   * Statisch. Gibt die Magnitude - Länge - des angegebenen {@code Vektor2D v} aus. Die Berechnung
   * erfolgt mittels des Satz des Pythagoras.
   * 
   * @param v {@code Vektor2D} von dem die Länge berechnet werden soll.
   * @return ein {@code double}, die Länge von {@code v}.
   */
  public static double magnitude(Vector2D v)
  {
    return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
  }

  /**
   * Nicht-Statisches Setzen der Magnitude - Länge - {@code scalar} des {@code Vector2D} auf dem die
   * Methode aufgerufen wurde. Ruft die statische Methode
   * {@code Vector2D.setMagnitude(Vector2D v, double scalar)} mit dem aufgerufenen
   * {@code Vector2D v} und dem angebenen {@code double scalar} auf.
   * 
   * @param scalar {@code double} - Länge, auf die der Vektor skaliert werden soll.
   * @return einen {@code Vector2D} - der skalierte Vektor.
   */
  public Vector2D setMagnitude(double scalar)
  {
    return Vector2D.setMagnitude(this, scalar);
  }

  /**
   * Statisches Setzen der Magnitude - Länge - des {@code Vektor2D v} auf die angegebene Länge
   * {@code double scalar}.
   * 
   * @param v {@code Vektor2D} - Vektor der skaliert werden soll.
   * @param scalar {@code double} - Länge, auf die der Vektor skaliert werden soll.
   * @return einen {@code Vector2D} - der skalierte Vektor.
   */
  public static Vector2D setMagnitude(Vector2D v, double scalar)
  {
    double magnitude;
    double magnitudeFactor;
    Vector2D scaled;

    magnitude = Vector2D.magnitude(v);
    magnitudeFactor = scalar / magnitude;
    scaled = Vector2D.product(v, magnitudeFactor);

    return scaled;
  }

  /**
   * Setzt x-Koordinate auf {@code double x}.
   * 
   * @param x {@code double} - wird gesetzt.
   */
  public void setX(double x)
  {
    this.x = x;
  }

  /**
   * Setzt y-Koordinate auf {@code double y}.
   * 
   * @param y {@code double} - wird gesetzt.
   */
  public void setY(double y)
  {
    this.y = y;
  }

  /**
   * Setzt den {@code Vector2D} auf angegebene {@code double x, double y} - (x, y).
   * 
   * @param x {@code double} - wird gesetzt.
   * @param y {@code double} - wird gesetzt.
   */
  public void set(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  /**
   * Liefert x-Koordinate.
   * 
   * @return {@code double} x.
   */
  public double getX()
  {
    return this.x;
  }

  /**
   * Liefert y-Koordinate.
   * 
   * @return {@code double} y.
   */
  public double getY()
  {
    return this.y;
  }

  /**
   * Liefert x- und y-Koordinaten des {@code Vector2D} als {@code double[]}.
   * 
   * @return ein {@code double[]} mit der x-Koordinate an der nullten und der y-Koordinate an der
   *         ersten Stelle.
   */
  public double[] get()
  {
    double[] vector = { this.x, this.y };
    return vector;
  }
}