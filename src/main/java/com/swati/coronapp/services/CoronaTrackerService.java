package com.swati.coronapp.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.swati.coronapp.models.GlobalStats;

@Service
public class CoronaTrackerService {
	
	private static String VIRUS_TRACKING_SOURCE = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private	List<GlobalStats> finalstats = new ArrayList<>();
	
	public List<GlobalStats> getfinalstats() {
        return finalstats;
    }
	
	@PostConstruct
	@Scheduled(cron = "* 1 * * * *")
	public void fetchSourceData() throws IOException, InterruptedException {
		
		List<GlobalStats> currentStats = new ArrayList<>();
		
		HttpClient httpclient = HttpClient.newHttpClient();
		HttpRequest httprequest = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_TRACKING_SOURCE))
				.build();
		HttpResponse<String> httpresponse = httpclient.send(httprequest, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvBodyReader = new StringReader(httpresponse.body());		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		for (CSVRecord record : records) {	
			GlobalStats globalstats = new GlobalStats();
			globalstats.setState(record.get("Province/State"));
			globalstats.setCountry(record.get("Country/Region"));
			Integer totalCases = Integer.parseInt(record.get(record.size()-1));
			Integer previousDayCases = Integer.parseInt(record.get(record.size()-2));
			globalstats.setTotalCases(totalCases);
			globalstats.setDiffFromLastDay(totalCases-previousDayCases);
			currentStats.add(globalstats);
		}
		
		this.finalstats = currentStats;
	}

}
