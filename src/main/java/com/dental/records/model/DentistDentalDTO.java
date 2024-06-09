package com.dental.records.model;

public class DentistDentalDTO {
	private Dentist dentist;
	private DentalExam dentalExam;
	
	public DentistDentalDTO(Dentist dentist, DentalExam dentalExam) {
		super();
		this.dentist = dentist;
		this.dentalExam = dentalExam;
	}
	public Dentist getDentist() {
		return dentist;
	}
	public void setDentist(Dentist dentist) {
		this.dentist = dentist;
	}
	public DentalExam getDentalExam() {
		return dentalExam;
	}
	public void setDentalExam(DentalExam dentalExam) {
		this.dentalExam = dentalExam;
	}
	
	
}
