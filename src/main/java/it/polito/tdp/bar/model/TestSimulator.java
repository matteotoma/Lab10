package it.polito.tdp.bar.model;

public class TestSimulator {

	public static void main(String[] args) {
		Simulator s = new Simulator();
		
		s.init();
		s.run();
		
		int clienti = s.getClienti();
		int sodd = s.getSoddisfatti();
		int insodd = s.getInsoddisfatti();
		
		System.out.format("Arrivati %d clienti, %d soddisfatti e %d insoddisfatti\n", clienti, sodd, insodd);

	}

}
