package org.bukkit.event.weather;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

/**
 * Handles all events fired in relation to weather
 */
public class WeatherListener implements Listener {

    public WeatherListener() {
    }

    public void onEvent(Event event) {
        switch (event.getType()) {
            case WEATHER_CHANGE:
                this.onWeatherChange((WeatherChangeEvent) event);
                break;

            case THUNDER_CHANGE:
                this.onThunderChange((ThunderChangeEvent) event);
                break;

            case LIGHTNING_STRIKE:
                this.onLightningStrike((LightningStrikeEvent) event);
                break;
        }
    }

    public void onWeatherChange(WeatherChangeEvent event) {
    }

    public void onThunderChange(ThunderChangeEvent event) {
    }

    public void onLightningStrike(LightningStrikeEvent event) {
    }
}
