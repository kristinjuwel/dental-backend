package com.dental.records.model;

import jakarta.persistence.*;

@Entity
@Table(name = "unv_dentists")
public class UnverifiedDentist {

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
    private UnverifiedUser unvUser;
    
    public UnverifiedDentist() {
    	
    }
    
	public UnverifiedDentist(Dentist dentist) {
		this.dentistId = dentist.getDentistId();
		this.licenseNo = dentist.getLicenseNo();
		this.clinic = dentist.getClinic();
		this.clinicLoc = dentist.getClinicLoc();
	}

	public UnverifiedDentist(Long dentistId, String licenseNo, String clinic, String clinicLoc,
			UnverifiedUser unvUser) {
		super();
		this.dentistId = dentistId;
		this.licenseNo = licenseNo;
		this.clinic = clinic;
		this.clinicLoc = clinicLoc;
		this.unvUser = unvUser;
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

	public UnverifiedUser getUnvUser() {
		return unvUser;
	}

	public void setUnvUser(UnverifiedUser unvUser) {
		this.unvUser = unvUser;
	}
    
    
}
