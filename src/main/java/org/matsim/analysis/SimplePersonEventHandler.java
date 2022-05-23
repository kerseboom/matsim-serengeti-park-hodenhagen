package org.matsim.analysis;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.population.Person;

import java.util.HashMap;
import java.util.Map;


public class SimplePersonEventHandler implements PersonArrivalEventHandler, PersonDepartureEventHandler {

    private final Map<Id<Person>, Double> personToDepartureTime = new HashMap<>();
    @Override
    public void handleEvent(PersonDepartureEvent personDepartureEvent) {
        var departureTime  = personDepartureEvent.getTime();
        var personID = personDepartureEvent.getPersonId();
        personToDepartureTime.put(personID, departureTime);

    }

    @Override
    public void handleEvent(PersonArrivalEvent personArrivalEvent) {
        var arrivalTime  = personArrivalEvent.getTime();
        var departureTime = personToDepartureTime.get(personArrivalEvent.getPersonId());
        var travelTime = arrivalTime - departureTime;
        System.out.println("Person: "+personArrivalEvent.getPersonId()+ "travelled: "+ travelTime+"s.");
    }
}
