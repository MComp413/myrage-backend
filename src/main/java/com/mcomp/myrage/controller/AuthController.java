package com.mcomp.myrage.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


//@RestController
//@RequestMapping("/auth")
public class AuthController {

  @Value("${CGLOUD_CLIENT_ID}")
  private String clientId;



  @GetMapping("/oauth2")
  public ResponseEntity<String> getOAuthUrl() {
    Collection<String> scopes = List.of();
    String redirectUri = "";
    GoogleAuthorizationCodeRequestUrl authUrl =
        new GoogleAuthorizationCodeRequestUrl(clientId, redirectUri, scopes);
    var responseHeaders = new HttpHeaders();
    responseHeaders.set("Location", authUrl.toString());
    return new ResponseEntity<>(responseHeaders, HttpStatus.FOUND);
  }

  @GetMapping("/oauth2/success")
  public ResponseEntity<String> handleOAuthSuccess() {
  var responseHeaders = new HttpHeaders();
    return new ResponseEntity<>(responseHeaders, HttpStatus.FOUND);
  }


}
