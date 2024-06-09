package com.dental.records.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dentists")
public class Dentist {

    @Id
    @Column(name = "dentist_id")
    private Long dentistId;

    @Column(name = "license_no", nullable = false, length = 255)
    private String licenseNo;

    @Column(name = "clinic", length = 255)
    private String clinic;

    @Column(name = "clinic_loc", length = 255)
    private String clinicLoc;

    @OneToOne
    @JoinColumn(name = "dentist_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;
    
	public Dentist() {
	}
	
	public Dentist(UnverifiedDentist unvDentist) {
		this.dentistId = unvDentist.getDentistId();
		this.licenseNo = unvDentist.getLicenseNo();
		this.clinic = unvDentist.getClinic();
		this.clinicLoc = unvDentist.getClinicLoc();
	}
	
	public Dentist(Long dentistId, String licenseNo, String clinic, String clinicLoc, User user) {
		super();
		this.dentistId = dentistId;
		this.licenseNo = licenseNo;
		this.clinic = clinic;
		this.clinicLoc = clinicLoc;
		this.user = user;
	}

	public Long getDentistId() {
		return dentistId;
	}

	public void setDentistId(Long dentistId) {
		this.dentistId = dentistId;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getClinic() {
		return clinic;
	}

	public void setClinic(String clinic) {
		this.clinic = clinic;
	}

	public String getClinicLoc() {
		return clinicLoc;
	}

	public void setClinicLoc(String clinicLoc) {
		this.clinicLoc = clinicLoc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
