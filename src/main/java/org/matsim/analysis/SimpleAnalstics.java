package org.matsim.analysis;

import org.matsim.core.events.EventsUtils;

public class SimpleAnalstics {

    public static void main(String[] args){
        var handler = new SimplePersonEventHandler();
        var manager = EventsUtils.createEventsManager();
        var linkVolumeHandler = new HourlyLinkvolumeHandler();
        int numberhours = 0;
        double numbervehicles = 0;

        manager.addHandler(linkVolumeHandler);
        manager.addHandler(handler);

        EventsUtils.readEvents(manager, "/Users/tim/IdeaProjects/matsim-serengeti-park-hodenhagen/scenarios/serengeti-park-v1.0/output/output-serengeti-park-v1.0-run1/serengeti-park-v1.0-run1.output_events.xml.gz");
    for (var hours: linkVolumeHandler.hourToVehicles.keySet()){
        System.out.printf("%d,%d\n", hours, linkVolumeHandler.hourToVehicles.get(hours));
        numberhours++;
        numbervehicles = numbervehicles + linkVolumeHandler.hourToVehicles.get(hours);
    }
    System.out.printf("Es sind insgesamt %.0f Autos in %d Stunden gefahren\nIm Schnitt sind es damit: %.4f", numbervehicles, numberhours, numbervehicles / numberhours);
    }

}
