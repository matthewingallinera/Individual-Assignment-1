package com.example.Assignment1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class APIController {

    /**
     * I just put this here as another way to test that the project works/
     *
     * @return hello statement
     */
    @GetMapping("/hello")
    public String hello(){
        return "Hello, I am Matthew Ingallinera.";
    }

    /**
     * Gets lyrics from a song using the lyrics.ovh api (in this case, the lyrics to John Mayer's Wildfire
     *
     * @return The lyrics to the John Mayer song Wildfire.
     */
    @GetMapping("/lyrics")
    public Object getLyrics(){
        try {
            String url = "https://api.lyrics.ovh/v1/John&Mayer/Wildfire";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonListResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonListResponse);

            //This is similar to your /fruit example because the api im using only responds with a single json object
            Lyrics lyric = new Lyrics(root.get("lyrics").asText());
            return lyric;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(APIController.class.getName()).log(Level.SEVERE,null,ex);
            return "error in /lyrics";
        }
    }


}

