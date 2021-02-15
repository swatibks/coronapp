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

import com.swati.coronapp.models.USAStats;

@Service
public class USACoronaTrackerService {
	
	private static String VIRUS_TRACKING_SOURCE_CONFIRMED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
			
	private	List<USAStats> finalstats = new ArrayList<>();
	
	public List<USAStats> getfinalstats() {
        return finalstats;
    }
	
	@PostConstruct
	@Scheduled(cron = "* 1 * * * *")
	public void fetchSourceData() throws IOException, InterruptedException {
		
		List<USAStats> currentStats = new ArrayList<>();
		
		HttpClient httpclient = HttpClient.newHttpClient();
		HttpRequest httprequest = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_TRACKING_SOURCE_CONFIRMED))
				.build();
		HttpResponse<String> httpresponse = httpclient.send(httprequest, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvBodyReader = new StringReader(httpresponse.body());		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		for (CSVRecord record : records) {	
			USAStats usaStats = new USAStats();
			usaStats.setProvinceState(record.get("Combined_Key"));
			Integer totalCases = Integer.parseInt(record.get(record.size()-1));
			Integer previousDayCases = Integer.parseInt(record.get(record.size()-2));
			usaStats.setTotalCases(totalCases);
			usaStats.setDiffFromLastDay(totalCases-previousDayCases);
			currentStats.add(usaStats);
		}
		
		this.finalstats = currentStats;
	}
}
