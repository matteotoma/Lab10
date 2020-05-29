package it.polito.tdp.bar.model;

public class Model {
	
	private Simulator simulatore;
		
	public void simulazione() {
		this.simulatore = new Simulator();
		this.simulatore.init();
		this.simulatore.run();
	}
	
	public String risultato() {
		return "Arrivati " +this.simulatore.getClienti()+ " clienti, " 
				+this.simulatore.getSoddisfatti()+ " soddisfatti e " 
				+this.simulatore.getInsoddisfatti()+ " insoddisfatti\n";
	}

}
