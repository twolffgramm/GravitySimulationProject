package de.continentale.zv.n_body_simulation.model;

/**
 * TODO Klasse kommentieren
 * 
 * @author Tim.Wolffgramm
 * @version $Revision:$<br/>
 *          $Date:$<br/>
 *          $Author:$
 */
public class Vector2D
{
  double x;
  double y;

  /**
   * Vector2D Konstruktor.
   *
   */
  public Vector2D()
  {
    this.x = 0;
    this.y = 0;
  }

  /**
   * Vector2D Konstruktor.
   *
   * @param x
   * @param y
   */
  public Vector2D(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString()
  {
    String vector = "(";
    vector += this.x + ",\n";
    vector += this.y + ")";
    return vector;
  }

  /**
   * @param u
   * @return .
   */
  public Vector2D add(Vector2D u)
  {
    return Vector2D.sum(this, u);
  }

  /**
   * @param v
   * @param u
   * @return .
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
   * @param u
   * @return .
   */
  public Vector2D subtract(Vector2D u)
  {
    return Vector2D.difference(this, u);
  }

  /**
   * @param v
   * @param u
   * @return .
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
   * @param scalar
   * @return .
   */
  public Vector2D multiply(double scalar)
  {
    return Vector2D.product(this, scalar);
    // this.x = this.x * scalar;
    // this.y = this.y * scalar;
  }

  /**
   * @param v
   * @param scalar
   * @return .
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
   * @param u
   * @return .
   */
  public double dot(Vector2D u)
  {
    return Vector2D.dotProduct(this, u);
  }

  /**
   * @param v
   * @param u
   * @return .
   */
  public static double dotProduct(Vector2D v, Vector2D u)
  {
    double sum = 0;
    sum += v.getX() * u.getX();
    sum += v.getY() * u.getY();
    return sum;
  }

  /**
   * @param u
   * @return .
   */
  public double cross(Vector2D u)
  {
    return Vector2D.crossProduct(this, u);
  }

  /**
   * @param v
   * @param u
   * @return .
   */
  public static double crossProduct(Vector2D v, Vector2D u)
  {
    double determinant = 0;
    determinant += v.getX() * u.getY();
    determinant -= u.getX() * v.getY();
    return determinant;
  }

  /**
   * @param v
   * @return .
   */
  public static double magnitude(Vector2D v)
  {
    return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
  }

  /**
   * @param scalar
   * @return .
   */
  public Vector2D setMagnitude(double scalar)
  {
    return Vector2D.setMagnitude(this, scalar);
  }

  /**
   * @param v
   * @param scalar
   * @return .
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
   * @param x
   */
  public void setX(double x)
  {
    this.x = x;
  }

  /**
   * @param y
   */
  public void setY(double y)
  {
    this.y = y;
  }

  /**
   * @param x
   * @param y
   */
  public void set(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  /**
   * @return .
   */
  public double getX()
  {
    return this.x;
  }

  /**
   * @return .
   */
  public double getY()
  {
    return this.y;
  }

  /**
   * @return Die Funktion gibt den Vector als double-Array mit x in der nullten und y in der ersten
   *         Position aus
   */
  public double[] get()
  {
    double[] vector = { this.x, this.y };
    return vector;
  }
}