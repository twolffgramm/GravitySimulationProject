package de.continentale.zv.n_body_simulation.model;

public class SimulationsEinstellungen 
{
	/**
   * beschreibt ein vielfaches (int) des Radius eines Planeten. Ist die Distanz zu einem anderen
   * Planeten geringer als dieses Vielfaches, wird die Gravitationskraft zu diesem nicht
   * berücksichtigt - siehe PositionsController.berechneGravitationskraft()
   */
	int minimalerAbstand;
	
	boolean relativeAnzeige;
	int relativerPlanet;
	double dt;
	double zoomFaktor;
	int positionsThreadCounter;
	int ausgewaehlterPlanet;
	boolean planetAusgewaehlt;
	
	boolean repulsion;
	
//benötigt, um das SimulationsModel zurückzusetzen auf das aktuelle Szenario
	int aktuellesSzenario;
	
	public SimulationsEinstellungen(Szenario szenario, int aktuellesSzenario) 
	{
		this.dt = szenario.getDt();
		this.zoomFaktor = szenario.getZoomFaktor();
		this.minimalerAbstand = 0;
		this.aktuellesSzenario = aktuellesSzenario;
		this.positionsThreadCounter = 0;
		this.ausgewaehlterPlanet = 0;
	}
	
  /**
   * Liefert den aktuellen diskreten Zeitsprung (delta-Zeit, dt) für die Simulation.
   * 
   * @return ein {@code double}, die delta-Zeit, dt.
   */
  public double getDt()
  {
    return this.dt;
  }
  
  /**
   * Setzt den diskreten Zeitsprung (delta-Zeit, dt) für die Simulation.
   * 
   * @param dt {@code int}, die neue delta-Zeit, dt.
   */
  public void setDt(int dt)
  {
    this.dt = dt;
  }
  
  /**
   * Liefert den aktuellen Zoomfaktor für die Darstellung der aktuellen Simulation.
   * 
   * @return ein {@code double}, den aktuellen Zoomfaktor.
   */
  public double getZoomFaktor()
  {
    return this.zoomFaktor;
  }
  
  /**
   * Setzt den Zoomfaktor für die aktuelle Darstellung der Simulation.
   * <p>
   * Das Setzen erfolgt mittels der Angabe einer "Änderung" die Verrechnet wird. Dies ist notwendig,
   * um die Mausradbewegung zu Verrechnen. die Änderung pro Mausradbewegung (+1 || -1) führt zu
   * einem 10%-igen Zoom.
   * 
   * @param aenderung {@code double}, die Mausradbewegung, +1 || -1.
   */
  public void setZoomFaktor(double aenderung)
  {
    aenderung = aenderung * this.zoomFaktor / 10;
    this.zoomFaktor += aenderung;
  }
  
  /**
   * Liefert den aktuellen minimalen Abstand zwischen Planeten, der für die Berechnung der
   * Positionen notwendig ist. Angabe als Ganzzahl-faktor des Radius.
   * 
   * @return ein {@code int}, der aktuelle Faktor.
   */
  public int getMinimalerAbstand()
  {
    return this.minimalerAbstand;
  }
  
  /**
   * Setzt den neuen aktuellen minimalen Abstand zwischen Planeten, der für die Berechnung der
   * Positionen notwendig ist. Angabe als Ganzzahl-faktor des Radius.
   * 
   * @param minimalerAbstand {@code int}, der neue Ganzzahl-faktor.
   */
  public void setMinimalerAbstand(int minimalerAbstand)
  {
    this.minimalerAbstand = minimalerAbstand;
  }
  
  public int getRelativerPlanet()
  {
    return this.relativerPlanet;
  }
  
	public void setRelativerPlanet(String relativerPlanet) {
		this.relativerPlanet = Integer.parseInt(relativerPlanet);
	}
	
  public boolean getRelativeAnzeige()
  {
    return this.relativeAnzeige;
  }
  
  public void setRelativeAnzeige(boolean relativeAnzeige)
  {
  	this.relativeAnzeige = relativeAnzeige;
  }
  
  /**
   * Setzt die Kennung des neuen Szenarios des Models. Hiermit kann über die Buttons im Menü das
   * Szenario gewechselt werden.
   * 
   * @param szenario {@code int}, die Kennung des neuen Szenarios.
   */
  public void setAktuellesSzenario(int szenario)
  {
    this.aktuellesSzenario = szenario;
  }
  
  public boolean getRepulsion()
  {
  	return this.repulsion;
  }
  
  public void setRepulsion() 
  {
  	repulsion = !repulsion;
  }
	
  public void setPositionsThreadCounter()
  {
  	positionsThreadCounter++;
  }
  
  public int getAusgewaehlterPlanet()
  {
  	return this.ausgewaehlterPlanet;
  }
  
  public void setAusgewaehlterPlanet(int ausgewaehlterPlanet)
  {
  	this.ausgewaehlterPlanet = ausgewaehlterPlanet;
  }
  
  public boolean getPlanetAusgewaehlt()
  {
  	return this.planetAusgewaehlt;
  }
  
  public void setPlanetAusgewaehlt(boolean planetAusgeswaehlt)
  {
  	this.planetAusgewaehlt = planetAusgeswaehlt;
  }
}
