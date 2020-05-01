package it.polito.tdp.poweroutages.model;

import java.util.Comparator;

public class ComparatorePerData implements Comparator<PowerOutage> {

	@Override
	public int compare(PowerOutage p1, PowerOutage p2) {
		
		if(p1.getDate_event_began().compareTo(p2.getDate_event_began())==0) {
			
			return p1.getDate_event_finished().compareTo(p2.getDate_event_finished());
	
		}
		
		
		return p1.getDate_event_began().compareTo(p2.getDate_event_began());
	}

}
