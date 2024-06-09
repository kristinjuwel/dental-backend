package com.dental.records.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "unv_patients")
public class UnverifiedPatient {

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
    private UnverifiedUser unvUser;
    
	public UnverifiedPatient() {
	}
	
	public UnverifiedPatient(Patient patient) {
		this.patientId = patient.getPatientId();
		this.patientType = patient.getPatientType();
		this.rank = patient.getRank();
		this.unitAssign = patient.getUnitAssign();
	}
	
	public UnverifiedPatient(Long patientId, String patientType, String rank, String unitAssign,
			UnverifiedUser unvUser) {
		super();
		this.patientId = patientId;
		this.patientType = patientType;
		this.rank = rank;
		this.unitAssign = unitAssign;
		this.unvUser = unvUser;
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

	public UnverifiedUser getUnvUser() {
		return unvUser;
	}

	public void setUnvUser(UnverifiedUser unvUser) {
		this.unvUser = unvUser;
	}
	
	
}