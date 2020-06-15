package com.ibm.microservices.AccountLoginService;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AccountLoginController {
	
	
	public static final long Transaction_JWT_TOKEN_VALIDITY = 1 * 60 * 60;
	public static final long Service_JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/getToken")
	public Map<String,String> generateToken(@RequestParam("grant_type") String grant_type,@RequestParam("apikey")  String apikey) throws Exception {
		
		Map<String, String> tokens = new HashMap<String, String>();
		
		String url = "https://iam.cloud.ibm.com/identity/token";
		        
        MultiValueMap<String,Object> uriVariables=new LinkedMultiValueMap<>();
        uriVariables.add("grant_type", grant_type);
        uriVariables.add("apikey", apikey);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Object> entity = new HttpEntity<>(uriVariables, headers);
        Object token = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
        System.out.println(token.toString());
    //    System.out.println(token.toString().indexOf(','+1));
    //    System.out.println(token.toString().substring(19, (token.toString().indexOf(',',token.toString().indexOf(','+1)))));
        
        String userToken = token.toString().substring(19, (token.toString().indexOf(',',token.toString().indexOf(','+1))));
        System.out.println(userToken);
        tokens.put("User_Token", userToken);
        
		Map<String, Object> claims = new HashMap<>();
		
		String transactionToken=jwtTokenUtil.doGenerateToken(claims,Transaction_JWT_TOKEN_VALIDITY);
		System.out.println(transactionToken);
		tokens.put("Transaction_Token", transactionToken);
		
		String serviceToken=jwtTokenUtil.doGenerateToken(claims,Service_JWT_TOKEN_VALIDITY);
		System.out.println(serviceToken);
		tokens.put("Service_Token", serviceToken);
		
		return tokens;
		
	}

}
