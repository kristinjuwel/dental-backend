package com.dental.records.model;

import java.sql.Date;

import jakarta.persistence.*;


@Entity 
@Table(name = "consent")
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consent_id")
    private Long consentId;
    
    @Column(name = "exam_id")
    private Long examId;
    
    @Column(name = "treatment_date")
    private Date treatDate;
    
    @Column(name = "treatment")
    private String treatment;
    
    @Column(name = "patient_name")
    private String patientName;
    
    @Column(name = "patient_age")
    private Integer patientAge;
    
    @Column(name = "patient_sign")
    private byte[] patientSign;
    
    @Column(name = "dentist_sign")
    private byte[] dentistSign;
    
    @Column(name = "completion_status")
    private String completionStatus;
    

    @ManyToOne
    @JoinColumn(name = "exam_id", insertable = false, updatable = false)
    private DentalExam dentalexam;
    
    public Consent() {
    	
    }

	public Consent(Long consentId, Long examId, Date treatDate, String treatment, String patientName,
			Integer patientAge, byte[] patientSign, byte[] dentistSign, String completionStatus,
			DentalExam dentalexam) {
		super();
		this.consentId = consentId;
		this.examId = examId;
		this.treatDate = treatDate;
		this.treatment = treatment;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientSign = patientSign;
		this.dentistSign = dentistSign;
		this.completionStatus = completionStatus;
		this.dentalexam = dentalexam;
	}

	public Long getConsentId() {
		return consentId;
	}

	public void setConsentId(Long consentId) {
		this.consentId = consentId;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Date getTreatDate() {
		return treatDate;
	}

	public void setTreatDate(Date treatDate) {
		this.treatDate = treatDate;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}

	public byte[] getPatientSign() {
		return patientSign;
	}

	public void setPatientSign(byte[] patientSign) {
		this.patientSign = patientSign;
	}

	public byte[] getDentistSign() {
		return dentistSign;
	}

	public void setDentistSign(byte[] dentistSign) {
		this.dentistSign = dentistSign;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public DentalExam getDentalexam() {
		return dentalexam;
	}

	public void setDentalexam(DentalExam dentalexam) {
		this.dentalexam = dentalexam;
	}

	
    
}