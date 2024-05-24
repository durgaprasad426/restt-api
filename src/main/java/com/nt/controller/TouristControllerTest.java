package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.repo.Tourist;
import com.nt.service.ITouristService;

@RestController
@RequestMapping("/tourist")
public class TouristControllerTest {

	@Autowired
	private ITouristService service;
	

	@PostMapping("/register")
	//@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String>enrollTourist(@RequestBody Tourist tourist){
		try {
	String msg=	service.registerTourist(tourist);
	return new ResponseEntity<String>(msg,HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<String>("problem in tourist enrollment",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
	@GetMapping("/findall")
	public ResponseEntity<?>getAllStudents(){
		try {
	List<Tourist>listofTourist=	service.fetchAllTouists();
	return new ResponseEntity<List<Tourist>>(listofTourist,HttpStatus.OK);
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("problem in fetching tourist",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
	@GetMapping("/find/{id}")
	public ResponseEntity<?>findByTouristId(@PathVariable Integer id){
		try {
	Tourist tourist=	  service.fetchTouristById(id);
	return new ResponseEntity<Tourist>(tourist,HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}                         
}
	@PutMapping("/modify")
	public ResponseEntity<String>modifyTouristDetails(@RequestBody Tourist tourist){
		try {
		   String msg=   service.updateTouristDetails(tourist);
		   return new ResponseEntity<String>(msg,HttpStatus.OK);
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
	@GetMapping("/findbyName/{name}")
	public ResponseEntity<?>displayALLTouristNames(@PathVariable String name){
		try {
		 List<Tourist>tourist= service.findAllTouristByName(name);
		 return new ResponseEntity<List<Tourist>>(tourist,HttpStatus.OK);
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
	}
}
	@PatchMapping("/pupdate/{id}/{percentage}")
	public ResponseEntity<?>modifyToursitBudgetDetailsById(@PathVariable Integer id,@PathVariable double percentage){
		try {
		String msg=  service.updateTouristBudgetById(id, percentage);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}catch (Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>deleteByTouristId(@PathVariable Integer id){
		try {
		 String msg=  service.removeTouristById(id);
		 return new ResponseEntity<String>(msg,HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
	@DeleteMapping("/delete/{start}/{end}")
	public ResponseEntity<?>deleteByTouristId(@PathVariable double start,@PathVariable double end){
		try {
		 String msg=  service.removeTouristBudgetRange(start, end);
		 return new ResponseEntity<String>(msg,HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
}
