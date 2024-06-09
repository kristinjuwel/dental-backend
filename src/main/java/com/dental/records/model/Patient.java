package com.dental.records.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "patient_type", nullable = false, length = 50)
    private String patientType;

    @Column(name = "rank", nullable = false, length = 50)
    private String rank;

    @Column(name = "unit_assign", nullable = false, length = 50)
    private String unitAssign;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;
    
    
	public Patient() {
		
	}

	public Patient(UnverifiedPatient unvPat) {
		this.patientId = unvPat.getPatientId();
		this.patientType =  unvPat.getPatientType();
		this.rank =  unvPat.getRank();
		this.unitAssign =  unvPat.getUnitAssign();
	}

	public Patient(Long patientId, String patientType, String rank, String unitAssign, User user) {
		super();
		this.patientId = patientId;
		this.patientType = patientType;
		this.rank = rank;
		this.unitAssign = unitAssign;
		this.user = user;
	}


	public Long getPatientId() {
		return patientId;
	}


	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}


	public String getPatientType() {
		return patientType;
	}


	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}


	public String getUnitAssign() {
		return unitAssign;
	}


	public void setUnitAssign(String unitAssign) {
		this.unitAssign = unitAssign;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
}