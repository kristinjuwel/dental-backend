package com.dental.records.controller;

import java.util.List;
import java.util.Optional;

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

import com.dental.records.model.ServiceRendered;
import com.dental.records.service.ServiceRenderedService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/services")
public class ServiceRenderedController {
	private ServiceRenderedService renderedService;

	@Autowired
	public ServiceRenderedController(ServiceRenderedService renderedService) {
		super();
		this.renderedService = renderedService;
	}
	
	@GetMapping
    public List<ServiceRendered> getAllServices() {
        return renderedService.findAll();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<ServiceRendered> getServiceById(@PathVariable Long id) {
        Optional<ServiceRendered> service = renderedService.findById(id);
        return service.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@GetMapping("/exam/{examId}")
    public List<ServiceRendered> getServicecByExamId(@PathVariable Long examId) {
        return renderedService.findByExam(examId);
    }
	
	@PostMapping
    public ServiceRendered createService(@RequestBody ServiceRendered service) {
        return renderedService.save(service);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<String> updateService(@PathVariable Long id, @RequestBody ServiceRendered serviceDetails) {
        try {
        	renderedService.update(id, serviceDetails);
            return ResponseEntity.ok("Update Successful");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        renderedService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
