package it.polito.tdp.poweroutages.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PowerOutage {

	private Integer id;
	private Integer customers_affected;
	
	
	
	private LocalDateTime date_event_began;
	private LocalDateTime date_event_finished;
	//private Duration differenza;
	
	private double ore;
	
	
	public PowerOutage(Integer id, Integer customers_affected,  LocalDateTime date_event_began,
			LocalDateTime date_eventi_finished) {
		super();
		this.id = id;
		this.customers_affected = customers_affected;
		
		this.date_event_began = date_event_began;
		this.date_event_finished = date_eventi_finished;
		
		
		/*differenza = Duration.between(this.date_event_began, this.date_event_finished);

		this.ore = differenza.toHours();
		
		this.anni = Period.from(differenza).getYears();
		
		
		controllo siano uguali a quello sotto ---------------------_???????
		*/
		
		this.ore= ChronoUnit.HOURS.between(this.date_event_began, this.date_event_finished);
		
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public double getOre() {
		return ore;
	}


	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}


	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}


	
	
	public Integer getCustomers_affected() {
		return customers_affected;
	}


	@Override
	public String toString() {
		
		String s= String.format("%d  %s  %s  %s  %d \n", this.date_event_began.getYear(), DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(date_event_began), 
				DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(date_event_finished), this.ore, this.customers_affected);
		
		
		
		return s;
	}
	
	
	
	
	
}
