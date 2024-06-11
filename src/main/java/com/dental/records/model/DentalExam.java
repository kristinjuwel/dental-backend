package com.dental.records.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "dental_exam")
public class DentalExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long examId;
    
    @Column(name = "rank", length = 50)
    private String rank;
    
    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "middle_name", length = 30)
    private String middleName;

    @Column(name = "last_name", length = 30)
    private String lastName;
    
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "unit_assign", length = 50)
    private String unitAssign;
    
    @Column(name = "sponsor", length = 50)
    private String sponsor;
    
    @Column(name = "address", length = 120)
    private String address;

    @Column(name = "contact_number", length = 12)
    private String contactNumber;
    
    @Column(name = "picture")
    private byte[] picture;
    
    @Column(name = "region", length = 30)
    private String region;

    @Column(name = "purpose", length = 35)
    private String purpose;

    @Column(name = "initial_date")
    private Date purposeDate;
    
    @Column(name = "checkup_date")
    private Date checkupDate;
    
    @Column(name = "training_date")
    private Date trainingDate;
    
    @Column(name = "promotion_date")
    private Date promotionDate;

    @Column(name = "dent_image")
    private byte[] dentImage;
    
    @Column(name = "calculus", length = 50)
    private String calculus;

    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "complaint", length = 255)
    private String complaint;

    @Column(name = "med_hist", length = 255)
    private String medHist;

    @Column(name = "bp", length = 30)
    private String bp;

    @Column(name = "dentist_id")
    private Long dentistId;
    
    @Column(name = "dentist_sign")
    private byte[] dentSign;
    
    @Column(name = "patient_sign")
    private byte[] patSign;
    
    @Column(name = "chief_name", length = 255)
    private String chiefName;

    @Column(name = "chief_sign")
    private byte[] chiefSign;
    
    @Column(name = "completion_status")
    private String completionStatus;
    
    @ManyToOne
    @JoinColumn(name = "dentist_id", insertable = false, updatable = false)
    private Dentist dentist;
    
    public DentalExam() {
    	
    }

	

	public DentalExam(Long examId, String rank, String firstName, String middleName, String lastName, Date birthday,
			String unitAssign, String sponsor, String address, String contactNumber, byte[] picture, String region,
			String purpose, Date purposeDate, Date checkupDate, Date trainingDate, Date promotionDate, byte[] dentImage,
			String calculus, String remarks, String complaint, String medHist, String bp, Long dentistId,
			byte[] dentSign, byte[] patSign, String chiefName, byte[] chiefSign, String completionStatus,
			Dentist dentist) {
		super();
		this.examId = examId;
		this.rank = rank;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.unitAssign = unitAssign;
		this.sponsor = sponsor;
		this.address = address;
		this.contactNumber = contactNumber;
		this.picture = picture;
		this.region = region;
		this.purpose = purpose;
		this.purposeDate = purposeDate;
		this.checkupDate = checkupDate;
		this.trainingDate = trainingDate;
		this.promotionDate = promotionDate;
		this.dentImage = dentImage;
		this.calculus = calculus;
		this.remarks = remarks;
		this.complaint = complaint;
		this.medHist = medHist;
		this.bp = bp;
		this.dentistId = dentistId;
		this.dentSign = dentSign;
		this.patSign = patSign;
		this.chiefName = chiefName;
		this.chiefSign = chiefSign;
		this.completionStatus = completionStatus;
		this.dentist = dentist;
	}



	public Date getCheckupDate() {
		return checkupDate;
	}



	public void setCheckupDate(Date checkupDate) {
		this.checkupDate = checkupDate;
	}



	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUnitAssign() {
		return unitAssign;
	}

	public void setUnitAssign(String unitAssign) {
		this.unitAssign = unitAssign;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Date getPurposeDate() {
		return purposeDate;
	}

	public void setPurposeDate(Date purposeDate) {
		this.purposeDate = purposeDate;
	}

	public Date getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public Date getPromotionDate() {
		return promotionDate;
	}

	public void setPromotionDate(Date promotionDate) {
		this.promotionDate = promotionDate;
	}

	public byte[] getDentImage() {
		return dentImage;
	}

	public void setDentImage(byte[] dentImage) {
		this.dentImage = dentImage;
	}

	public String getCalculus() {
		return calculus;
	}

	public void setCalculus(String calculus) {
		this.calculus = calculus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public String getMedHist() {
		return medHist;
	}

	public void setMedHist(String medHist) {
		this.medHist = medHist;
	}

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public Long getDentistId() {
		return dentistId;
	}

	public void setDentistId(Long dentistId) {
		this.dentistId = dentistId;
	}

	public byte[] getDentSign() {
		return dentSign;
	}

	public void setDentSign(byte[] dentSign) {
		this.dentSign = dentSign;
	}

	public byte[] getPatSign() {
		return patSign;
	}

	public void setPatSign(byte[] patSign) {
		this.patSign = patSign;
	}

	public String getChiefName() {
		return chiefName;
	}

	public void setChiefName(String chiefName) {
		this.chiefName = chiefName;
	}

	public byte[] getChiefSign() {
		return chiefSign;
	}

	public void setChiefSign(byte[] chiefSign) {
		this.chiefSign = chiefSign;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public Dentist getDentist() {
		return dentist;
	}

	public void setDentist(Dentist dentist) {
		this.dentist = dentist;
	}
    
    
    
}