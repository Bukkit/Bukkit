package org.bukkit.event.weather;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Handles all events fired in relation to weather
 */
public class WeatherListener implements Listener {

    public WeatherListener() {}

    public void onEvent(Event event) {}

    @EventHandler(Event.Type.WEATHER_CHANGE)
    public void onWeatherChange(WeatherChangeEvent event) {}

    @EventHandler(Event.Type.THUNDER_CHANGE)
    public void onThunderChange(ThunderChangeEvent event) {}

    @EventHandler(Event.Type.LIGHTNING_STRIKE)
    public void onLightningStrike(LightningStrikeEvent event) {}
}
