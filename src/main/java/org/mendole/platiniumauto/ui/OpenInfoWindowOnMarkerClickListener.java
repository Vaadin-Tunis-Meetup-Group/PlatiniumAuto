package org.mendole.platiniumauto.ui;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;

/**
 * Listener that opens info window when a marker is clicked.
 */
public class OpenInfoWindowOnMarkerClickListener implements MarkerClickListener {

    private static final long serialVersionUID = 646386541641L;

    private final GoogleMap map;
    private final GoogleMapMarker marker;
    private final GoogleMapInfoWindow window;
    private MapView view;

    public OpenInfoWindowOnMarkerClickListener(GoogleMap map,
            GoogleMapMarker marker, GoogleMapInfoWindow window, MapView view) {
        this.map = map;
        this.marker = marker;
        this.window = window;
        this.view = view;
    }

    @Override
    public void markerClicked(GoogleMapMarker clickedMarker) {
		// if (clickedMarker.equals(marker)) {
		// view.editProduct(new Product());
		// }
    }

}
