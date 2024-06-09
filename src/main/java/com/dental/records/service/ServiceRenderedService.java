package com.dental.records.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dental.records.repository.ServiceRenderedRepository;
import com.dental.records.model.ServiceRendered;

@Service
public class ServiceRenderedService {
	private final ServiceRenderedRepository serviceRepository;
	
	@Autowired
	public ServiceRenderedService(ServiceRenderedRepository serviceRepository) {
		super();
		this.serviceRepository = serviceRepository;
	}
	
	public List<ServiceRendered> findAll() {
        return serviceRepository.findAll();
    }
	
	public List<ServiceRendered> findByExam(Long examId){
		return serviceRepository.findByExamId(examId);
	}
	
	public Optional<ServiceRendered> findById(Long id) {
        return Optional.ofNullable(serviceRepository.findByServiceId(id));
    }
	
	public ServiceRendered save(ServiceRendered service) {
        return serviceRepository.save(service);
    }
	
	 public ServiceRendered update(Long id, ServiceRendered serviceDetails) {
	        Optional<ServiceRendered> serviceOptional = Optional.ofNullable(serviceRepository.findByServiceId(id));
	        if (serviceOptional.isPresent()) {
	        	ServiceRendered serviceToUpdate = serviceOptional.get();
	            serviceToUpdate.setDate(serviceDetails.getDate());
	            serviceToUpdate.setDiagnosis(serviceDetails.getDiagnosis());
	            serviceToUpdate.setTreatment(serviceDetails.getTreatment());
	            serviceToUpdate.setRemarks(serviceDetails.getRemarks());
	            return serviceRepository.save(serviceToUpdate);
	        } else {
	            throw new RuntimeException("Service not found with id " + id);
	        }
	    }

	
	public String deleteById(Long id) {
        serviceRepository.deleteById(id);
        return "Successfully deleted";
    }
	
}
