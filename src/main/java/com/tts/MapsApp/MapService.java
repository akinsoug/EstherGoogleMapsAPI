package com.tts.MapsApp;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {
	@Value("${api_key}")
	private String apiKey;

	public void addCoordinates(Location location) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getCity() + ","
				+ location.getState() + "&key=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();
		GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);
		Location coordinates = response.getResults().get(0).getGeometry().getLocation();
		location.setLat(coordinates.getLat());
		location.setLng(coordinates.getLng());
	}

	public Location randomCordinate() {
		Location location = new Location();
		Random rand = new Random();
		//generate a random number between -180 and 180
		Double longitude = (rand.nextDouble() * 360) - 180;
		Double latitude = (rand.nextDouble() * 180) - 90;
		
		location.setLat(String.valueOf(latitude));
		location.setLng(String.valueOf(longitude));
		
//		return randomCordinatees();
		return location;
	}
	

}
