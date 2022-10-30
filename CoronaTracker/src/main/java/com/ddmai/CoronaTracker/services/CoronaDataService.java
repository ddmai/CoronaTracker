package com.ddmai.CoronaTracker.services;

import com.ddmai.CoronaTracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CoronaDataService {

    private List<LocationStats> locationStats = new ArrayList<LocationStats>();

    @PostConstruct
    @Scheduled(cron = " * 1 * * * * ")
    public void fetchVirusData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String CORONA_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CORONA_DATA_URL)).build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
        StringReader reader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord record : records){
            LocationStats location = new LocationStats(record.get("Province/State"), record.get("Country/Region"), Integer.parseInt(record.get(record.size() - 1)));
            String[] arr = (String[]) record.stream().toArray();
            System.out.println(Arrays.toString(arr));
            location.setDiffFromPreviousDay(location.getLatestTotalCases() - Integer.parseInt(record.get(record.size() - 2)));

            locationStats.add(location);
        }

    }

    public List<LocationStats> getLocationStats() {
        return locationStats;
    }
}
