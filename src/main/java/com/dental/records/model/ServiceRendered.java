package com.dental.records.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class ServiceRendered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "exam_id", nullable = false)
    private Integer examId;

    @Column(name = "date")
    private Date date;

    @Column(name = "diagnosis", length = 255)
    private String diagnosis;

    @Column(name = "treatment", length = 255)
    private String treatment;

    @Column(name = "remarks", length = 255)
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "exam_id", insertable = false, updatable = false)
    private DentalExam dentalExam;
    
    public ServiceRendered() {
    	
    }

	public ServiceRendered(Long serviceId, Integer examId, Date date, String diagnosis, String treatment, String remarks,
			DentalExam dentalExam) {
		super();
		this.serviceId = serviceId;
		this.examId = examId;
		this.date = date;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.remarks = remarks;
		this.dentalExam = dentalExam;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public DentalExam getDentalExam() {
		return dentalExam;
	}

	public void setDentalExam(DentalExam dentalExam) {
		this.dentalExam = dentalExam;
	}
    
    
}
