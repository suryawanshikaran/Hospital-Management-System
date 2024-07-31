package com.HMSApp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HMSApp.entity.Patient;
import com.HMSApp.repository.PatientRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class PatientController {
	
	@Autowired
	 PatientRepository patientRepository;

	public PatientController(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}
	
	@PostMapping(path = "/patients")
	public Patient createPatient(@RequestBody Patient patient) {
		Patient p= patientRepository.save(patient);
		return p;
	}
	
	@GetMapping("/patients")
	public List<Patient> getAllPatient(){
		return patientRepository.findAll();
	}
	
	@GetMapping(path = "patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable long id) throws AttributeNotFoundException{
		Patient patient = patientRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("Patient not found with id :"+id));
		return ResponseEntity.ok(patient);
	}
	
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<Map<String, Boolean>>deletePatient(@PathVariable long id) throws AttributeNotFoundException{
		 Patient patient = patientRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("Patient Not Found With id"+id));
		 patientRepository.delete(patient);
		 Map<String, Boolean> response = new HashMap<String, Boolean>();
		 response.put("Deleted", Boolean.TRUE);
		 return ResponseEntity.ok(response);
	}
	 
	
	@PutMapping(path = "/patients/{id}")
	public ResponseEntity<Patient> updatePatientById(@PathVariable("id") long id, @RequestBody Patient patientDetails) throws AttributeNotFoundException{
		Patient patient = patientRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("Patient Not Found With id"+id));
		patient.setAge(patientDetails.getAge());
		patient.setName(patientDetails.getName());
		patient.setBlood(patientDetails.getBlood());
		patient.setDose(patientDetails.getDose());
		patient.setFees(patientDetails.getFees());
		patient.setPrescription(patientDetails.getPrescription());
		patient.setUrgency(patientDetails.getUrgency());
		
		
		Patient savedPatient = patientRepository.save(patient);
		return ResponseEntity.ok().body(savedPatient);
	}

}
