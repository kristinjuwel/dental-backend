package com.dental.records.model;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "referral")
public class Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "referral_id")
    private Long referralId;
    
    @Column(name = "from_dent")
    private String fromDent;
    
    @Column(name = "to_dent")
    private String toDent;
    
    @Column(name = "exam_id")
    private Long examId;
    
    @Column(name = "patient_name")
    private String patientName;
    
    @Column(name = "patient_age")
    private Integer patientAge;
    
    @Column(name = "patient_sex")
    private String patientSex;
    
    @Column(name = "patient_address")
    private String patientAddress;
    
    @Column(name = "treatment_date")
    private Date currentDate;
    
    @Column(name = "purpose")
    private String purpose;
    
    @Column(name = "treatment")
    private String treatment;
    
    @Column(name = "dental_cert")
    private String dentalCert;
    
    @Column(name = "dentist_id")
    private Long dentistId;
    
    @Column(name = "dentist_name")
    private String dentistName;
    
    @Column(name = "dentist_license")
    private String dentistLicense;
    
    @Column(name = "completion_status")
    private String completionStatus;

    @Column(name = "drawing_one")
    private byte[] drawingOne;

    @Column(name = "drawing_two")
    private byte[] drawingTwo;

    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id", insertable = false, updatable = false)
    private DentalExam dentalExam;

    @ManyToOne
    @JoinColumn(name = "dentist_id", referencedColumnName = "dentist_id", insertable = false, updatable = false)
    private Dentist dentist;
    
    public Referral() {
    	
    }

	public Referral(Long referralId, String fromDent, String toDent, Long examId, String patientName,
			Integer patientAge, String patientSex, String patientAddress, Date currentDate, String purpose,
			String treatment, String dentalCert, Long dentistId, String dentistName, String dentistLicense,
			String completionStatus, byte[] drawingOne, byte[] drawingTwo, DentalExam dentalExam, Dentist dentist) {
		super();
		this.referralId = referralId;
		this.fromDent = fromDent;
		this.toDent = toDent;
		this.examId = examId;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientSex = patientSex;
		this.patientAddress = patientAddress;
		this.currentDate = currentDate;
		this.purpose = purpose;
		this.treatment = treatment;
		this.dentalCert = dentalCert;
		this.dentistId = dentistId;
		this.dentistName = dentistName;
		this.dentistLicense = dentistLicense;
		this.completionStatus = completionStatus;
		this.drawingOne = drawingOne;
		this.drawingTwo = drawingTwo;
		this.dentalExam = dentalExam;
		this.dentist = dentist;
	}

	public Long getReferralId() {
		return referralId;
	}

	public void setReferralId(Long referralId) {
		this.referralId = referralId;
	}

	public String getFromDent() {
		return fromDent;
	}

	public void setFromDent(String fromDent) {
		this.fromDent = fromDent;
	}

	public String getToDent() {
		return toDent;
	}

	public void setToDent(String toDent) {
		this.toDent = toDent;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
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

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getDentalCert() {
		return dentalCert;
	}

	public void setDentalCert(String dentalCert) {
		this.dentalCert = dentalCert;
	}

	public Long getDentistId() {
		return dentistId;
	}

	public void setDentistId(Long dentistId) {
		this.dentistId = dentistId;
	}

	public String getDentistName() {
		return dentistName;
	}

	public void setDentistName(String dentistName) {
		this.dentistName = dentistName;
	}

	public String getDentistLicense() {
		return dentistLicense;
	}

	public void setDentistLicense(String dentistLicense) {
		this.dentistLicense = dentistLicense;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public byte[] getDrawingOne() {
		return drawingOne;
	}

	public void setDrawingOne(byte[] drawingOne) {
		this.drawingOne = drawingOne;
	}

	public byte[] getDrawingTwo() {
		return drawingTwo;
	}

	public void setDrawingTwo(byte[] drawingTwo) {
		this.drawingTwo = drawingTwo;
	}

	public DentalExam getDentalExam() {
		return dentalExam;
	}

	public void setDentalExam(DentalExam dentalExam) {
		this.dentalExam = dentalExam;
	}

	public Dentist getDentist() {
		return dentist;
	}

	public void setDentist(Dentist dentist) {
		this.dentist = dentist;
	}

	
    
   
}