package com.dental.records.controller;



import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.DentalExam;
import com.dental.records.service.DentalExamService;

@RestController
@CrossOrigin(origins = "https://dental-health-record.vercel.app")
@RequestMapping("/dental")
public class DentalExamController {

    private final DentalExamService dentalExamService;
    
    
    @Autowired
    public DentalExamController(DentalExamService dentalExamService) {
		super();
		this.dentalExamService = dentalExamService;
	}
    
    @GetMapping
    public List<DentalExam> getAllExams() {
        return dentalExamService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DentalExam> getExamById(@PathVariable Long id) {
        Optional<DentalExam> dentalExam = dentalExamService.findById(id);
        return dentalExam.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public List<DentalExam> getExamsByDentistId(@RequestParam Long dentistId) {
        return dentalExamService.findByDentistId(dentistId);
    }
    
    @GetMapping("/fullname")
    public List<DentalExam> getExamsByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        return dentalExamService.findByFullName(firstName, lastName);
    }

    @GetMapping("/status/{completionStatus}")
    public List<DentalExam> getExamsByCompletionStatus(@PathVariable String completionStatus) {
        return dentalExamService.findByCompletionStatus(completionStatus);
    }
    
    @CrossOrigin(origins = "https://dental-health-record.vercel.app")
    @PostMapping
    public DentalExam addExam(@RequestPart("dentalExam") DentalExam dentalExam,
                              @RequestPart("patImage") MultipartFile patImage,
                              @RequestPart("dentImage") MultipartFile dentImage,
                              @RequestPart("patSign") MultipartFile patSign) throws IOException {
        return dentalExamService.save(dentalExam, patImage, dentImage, patSign);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentalExam> updateExam(@PathVariable Long id, @RequestBody DentalExam updatedExam) {
        DentalExam updated = dentalExamService.update(id, updatedExam);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        dentalExamService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}