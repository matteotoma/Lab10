package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
	
	// CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;
	
	// PARAMETRI DI SIMULAZIONE
	private Duration intEventi;
	
	// STATO DEL SISTEMA
	private List<Tavolo> tavoli;
	
	// OUTPUT DA CALCOLARE
	private int clienti;
	private int insoddisfatti;
	private int soddisfatti;
	
	private void setTavoli() {
		this.tavoli = new ArrayList<>();

		aggiungiTavolo(2, 10);
		aggiungiTavolo(4, 8);
		aggiungiTavolo(4, 6);
		aggiungiTavolo(5, 4);

		// ordina i tavoli dal più piccolo al più grande, così facilito le ricerche
		Collections.sort(this.tavoli, new Comparator<Tavolo>() {
			public int compare(Tavolo o1, Tavolo o2) {
				return -(o1.getnPosti() - o1.getnPosti());
			}
		});
	}
	
	private void aggiungiTavolo(int num, int nPosti) {
		for (int i = 0; i < num; i++) {
			Tavolo t = new Tavolo(nPosti, false);
			this.tavoli.add(t);
		}
	}

	public int getClienti() {
		return clienti;
	}

	public int getInsoddisfatti() {
		return insoddisfatti;
	}
	
	public int getSoddisfatti() {
		return soddisfatti;
	}
	
	public void init() {
		setTavoli();
		this.clienti = this.insoddisfatti = this.soddisfatti = 0;
		this.queue = new PriorityQueue<>();
		
		LocalDateTime oraArrivoCliente = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 00));
		for(int i=0; i<2000; i++) {
			Event e = new Event(oraArrivoCliente.toLocalTime(), EventType.ARRIVO_GRUPPO_CLIENTI, null);
			this.queue.add(e);
			this.intEventi = Duration.of((int)((Math.random()*10)+1), ChronoUnit.MINUTES);
			oraArrivoCliente = oraArrivoCliente.plus(intEventi);
		}
	}
	
	public void run() {		
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		switch(e.getType()) {
		case ARRIVO_GRUPPO_CLIENTI:
			this.clienti += e.getNumPersone();
			Tavolo trovato = null;
			
			for(Tavolo t: tavoli) {
					if(!t.isOccupato() && e.getNumPersone() <= t.getnPosti()) {
						this.soddisfatti += e.getNumPersone();
						trovato = t;
						trovato.setOccupato(true);
						Event nuovo = new Event(e.getTime().plus(e.getDurata()), EventType.TAVOLO_LIBERATO, trovato);
						this.queue.add(nuovo);
						break;
					}
			}
			
			if(trovato == null) {
				if(e.getTolleranza() <= Math.random())
					this.insoddisfatti += e.getNumPersone();
				else {
					// servizio al bancone
					this.soddisfatti += e.getNumPersone();
				}
			}
			break;
		case TAVOLO_LIBERATO:
			e.getTavolo().setOccupato(false);
			break;
		}
	}
	
}
