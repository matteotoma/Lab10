package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		ARRIVO_GRUPPO_CLIENTI, TAVOLO_LIBERATO
	}
	
	private LocalTime time;
	private EventType type;
	private int numPersone;
	private Duration durata;
	private double tolleranza;
	private Tavolo tavolo;
	
	public Event(LocalTime time, EventType type, Tavolo tavolo) {
		super();
		this.time = time;
		this.type = type;
		this.tavolo = tavolo;
		this.numPersone = (int)(Math.random()*10 + 1);
		this.durata = Duration.ofMinutes(60 + (int) (Math.random() * ((int) (120 - 60))));
		this.tolleranza = Math.random();
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int compareTo(Event o) {
		return this.time.compareTo(o.time);
	}

	public int getNumPersone() {
		return numPersone;
	}

	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}

	public Duration getDurata() {
		return durata;
	}

	public void setDurata(Duration durata) {
		this.durata = durata;
	}

	public double getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

}
