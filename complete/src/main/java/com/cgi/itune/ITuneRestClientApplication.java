package com.cgi.itune;

import com.cgi.itune.config.AppConfig;
import com.cgi.itune.entities.ITuneResponse;
import com.cgi.itune.entities.ITunesCollection;
import com.cgi.itune.services.ITuneSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ITuneRestClientApplication {

	private static final Logger log = LoggerFactory.getLogger(ITuneRestClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ITuneRestClientApplication.class, args);
	}
	@Autowired
	ITuneSearchService iTuneSearchService;
	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {
			String artisName="Jack Johnson";
			if(null!=args && args.length>0){
				artisName=args[0];
			}
			List<ITunesCollection> collections=iTuneSearchService.getTracksByArtistName(artisName);

			StringBuilder sb= new StringBuilder();
			if(null!=collections) {
				sb.append("\n Showing Tracks for ").append(artisName).append(":\n");
				for (ITunesCollection collection : collections) {
					sb.append(collection.getCollectionName()).append(", ");
					sb.append(collection.getTrackName()).append(", ");
					sb.append(collection.getPrimaryGenreName()).append(", ").append("\n");
				}
			}else{
				sb.append("\n No Results found for ").append(artisName);
			}
			log.info(sb.toString());
		};
	}
}
