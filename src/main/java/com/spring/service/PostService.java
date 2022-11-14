package com.spring.service;


import com.spring.dto.PostDTO;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.List;

@Service
public class PostService {

    public static String BASE_POST_URL= "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    private RestTemplate restTemplate;

    public List<PostDTO> posts(){
        ResponseEntity<List> posts =  restTemplate.getForEntity(BASE_POST_URL, List.class);
        return posts.getBody();
    }

    public PostDTO post(long id){
        ResponseEntity<PostDTO> post = restTemplate.getForEntity(BASE_POST_URL+"/"+id, PostDTO.class);
        return post.getBody();
    }

    public PostDTO addPost(PostDTO postDTO){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("accept", "application/json");
        httpHeaders.add("accept-language", "en");
        HttpEntity<PostDTO> request =new HttpEntity<>(postDTO, httpHeaders);
//        ResponseEntity<PostDTO> post = restTemplate.postForEntity(BASE_POST_URL, request, PostDTO.class);
        ResponseEntity<PostDTO> post = restTemplate.exchange(BASE_POST_URL, HttpMethod.POST, request, PostDTO.class);
        return post.getBody();
    }

    public void updatePost(PostDTO postDTO){
        HttpEntity<PostDTO> request = new HttpEntity<>(postDTO);
//        ResponseEntity<PostDTO> post = restTemplate.exchange(BASE_POST_URL, HttpMethod.PUT, request, PostDTO.class);
        restTemplate.put(BASE_POST_URL, request);
//        return post.getBody();
    }
}
