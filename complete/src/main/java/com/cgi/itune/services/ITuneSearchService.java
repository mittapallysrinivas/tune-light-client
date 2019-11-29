package com.cgi.itune.services;

import com.cgi.itune.ITuneRestClientApplication;
import com.cgi.itune.entities.ITuneResponse;
import com.cgi.itune.entities.ITunesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ITuneSearchService {

    private static final Logger log = LoggerFactory.getLogger(ITuneSearchService.class);
    @Autowired
    RestTemplate restTemplate;
    @Value("${itune.endpoint.search}")
    String searchEndpoint;
    @PostConstruct
    public void attachConvertor(){
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
    }


    public List<ITunesCollection> getTracksByArtistName(String name) throws UnsupportedEncodingException {
        ITuneResponse resp = restTemplate.getForObject(
                searchEndpoint+"?term="+ URLEncoder.encode(name.toLowerCase(),"utf-8"), ITuneResponse.class);

        return resp.getResults();
    }
}
