package it.polito.tdp.poweroutages.model;


import java.time.temporal.ChronoUnit;
import java.util.Collections;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {

	private PowerOutageDAO podao;

	private List<PowerOutage> listaPO;
	private List<PowerOutage> best;
	private Integer maxPersone;

	private Integer totPersone;
	private double totOre;
	private double differenzaAnni;
	
	
	
	private Integer bestTotOre;

	public Model() {
		podao = new PowerOutageDAO();
		this.listaPO = new LinkedList<>();

	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutage> cerca(Integer anni, Integer ore, Nerc nerc) {

		this.listaPO = podao.listaPowerOutages(nerc);

		this.best = new LinkedList<>();  
		
		/*
		 * Se non metto l'inizializzazione rimane alla soluzione di prima oppure null,
		 * nel caso non ci fosse soluzione nel nuovo tentativo
		 * 
		*/
		
		this.maxPersone = 0;
		this.totOre = 0;
		

		this.totPersone = 0;

		this.bestTotOre=0;
	
		
		List<PowerOutage> parziale = new LinkedList<>();

		ricorsiva(anni, ore, 0, parziale);

		
		return best; //o è vuota o è piena

	}

	private void ricorsiva(Integer anni, Integer ore, Integer livello, List<PowerOutage> parziale) {

		
		
		
		//if (livello == this.listaPO.size()) {  cosa posso mettere come sostituto???
		if (this.totOre<ore) {
			
			if (this.totPersone > this.maxPersone) {
				
				this.maxPersone = this.totPersone;
				this.bestTotOre= (int) this.totOre;
				
				
				
				//!! NOOOO == > this.best = parziale;  ==> copio riferimento!!!!!
				
				Collections.sort(parziale, new ComparatorePerData());
				
				this.best = new LinkedList<>(parziale);
				
				return;
			}

			// quando sei arrivata alle ore, confronta

		}

		for (PowerOutage p : this.listaPO) {
				
			
			
			if (!parziale.contains(p)) { //se c'è già non aggiungooo!!!

 
				/*
				 * NON SERVE => LO FA GIà IL BACKTRACKING
				 * 
				 * if (livello == 0) {
				 *  this.totPersone = 0;
				 *	this.totOre = 0;
				 *	
				 *    	}*/
				
				
				if ((this.totOre + p.getOre()) <= ore) { // aggiungi

					if (parziale.size() > 0) {
						Collections.sort(parziale, new ComparatorePerData());

						PowerOutage primo = parziale.get(0);

						this.differenzaAnni = Math.abs(ChronoUnit.YEARS.between(primo.getDate_event_began(), p.getDate_event_began()));
						//=> mi da le unità
						
						
						if (this.differenzaAnni <= anni) {

							this.totOre += p.getOre();
							this.totPersone += p.getCustomers_affected();

							parziale.add(p);

							ricorsiva(anni, ore, livello + 1, parziale);

							this.totOre -= p.getOre();
							this.totPersone -= p.getCustomers_affected();
							parziale.remove(p); 
							
							// non posso fare parziale.size()-1 perchè l'ultimo inserito non è detto sia
							// l'ultimo della lista(ordinata)
						}

					} else {

						this.totOre += p.getOre();
						this.totPersone += p.getCustomers_affected();
						parziale.add(p);

						ricorsiva(anni, ore, livello + 1, parziale);

						this.totOre -= p.getOre();
						this.totPersone -= p.getCustomers_affected();
						parziale.remove(p);
					}
				}
			}
			
			
			
			
		}
		
		
		
		

	}

	 

	public Integer getMaxPersone() {
		return maxPersone;
	}

	public Integer getBestTotOre() {
		return bestTotOre;
	}

	
	
	

}
