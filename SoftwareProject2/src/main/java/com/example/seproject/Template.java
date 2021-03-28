package com.example.seproject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class Template {

	@Autowired
    private DiscoveryClient discoveryClient;

	@RequestMapping("/console")
	public String firstPage() {
		return "console";
	}

	@PostMapping("/consolelogin/")
	public String consoleLogin(@RequestParam(name = "anumber") String accountNumber,Model model) {
		RestTemplate restTemplate = new RestTemplate();
		String accountServiceUrl = this.discoveryClient.getInstances("accounts-service").get(0).getUri().toString()
				+ "/accounts/" + accountNumber;
		try{
			String resp = restTemplate.getForObject(accountServiceUrl, String.class);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(resp);
			model.addAttribute("name", actualObj.get("owner"));
			return "menu";
		} catch(Exception e) {
			
		}
		return "error";
	}

	@RequestMapping("/clients/{applicationName}")
    public @ResponseBody String getClientsByApplicationName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName).get(0).getUri().toString();
    }
}