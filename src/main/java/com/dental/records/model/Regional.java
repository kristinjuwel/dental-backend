package com.dental.records.model;

import jakarta.persistence.*;

@Entity
@Table(name = "regional")
public class Regional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regional_id")
    private Long regionalId;

    @Column(name = "services_id")
    private Long servicesId;
    
    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "report_month")
    private String reportMonth;

    @Column(name = "report_year")
    private String reportYear;

    @Column(name = "unif_total")
    private Long unifTotal;

    @Column(name = "unif_consultation")
    private Long unifConsultation;

    @Column(name = "unif_oral_prophylaxis")
    private Long unifOralProphylaxis;

    @Column(name = "unif_dental_sealant")
    private Long unifDentalSealant;

    @Column(name = "unif_fluoride_application")
    private Long unifFluorideApplication;

    @Column(name = "unif_restoration")
    private Long unifRestoration;

    @Column(name = "unif_extraction")
    private Long unifExtraction;

    @Column(name = "unif_prosthodontics")
    private Long unifProsthodontics;

    @Column(name = "unif_other_procedures")
    private Long unifOtherProcedures;

    @Column(name = "nonunif_total")
    private Long nonunifTotal;

    @Column(name = "nonunif_consultation")
    private Long nonunifConsultation;

    @Column(name = "nonunif_oral_prophylaxis")
    private Long nonunifOralProphylaxis;

    @Column(name = "nonunif_dental_sealant")
    private Long nonunifDentalSealant;

    @Column(name = "nonunif_fluoride_application")
    private Long nonunifFluorideApplication;

    @Column(name = "nonunif_restoration")
    private Long nonunifRestoration;

    @Column(name = "nonunif_extraction")
    private Long nonunifExtraction;

    @Column(name = "nonunif_prosthodontics")
    private Long nonunifProsthodontics;

    @Column(name = "nonunif_other_procedures")
    private Long nonunifOtherProcedures;

    @Column(name = "dpd_total")
    private Long dpdTotal;

    @Column(name = "dpd_consultation")
    private Long dpdConsultation;

    @Column(name = "dpd_oral_prophylaxis")
    private Long dpdOralProphylaxis;

    @Column(name = "dpd_dental_sealant")
    private Long dpdDentalSealant;

    @Column(name = "dpd_fluoride_application")
    private Long dpdFluorideApplication;

    @Column(name = "dpd_restoration")
    private Long dpdRestoration;

    @Column(name = "dpd_extraction")
    private Long dpdExtraction;

    @Column(name = "dpd_prosthodontics")
    private Long dpdProsthodontics;

    @Column(name = "dpd_other_procedures")
    private Long dpdOtherProcedures;

    @Column(name = "cvl_total")
    private Long cvlTotal;

    @Column(name = "cvl_consultation")
    private Long cvlConsultation;

    @Column(name = "cvl_oral_prophylaxis")
    private Long cvlOralProphylaxis;

    @Column(name = "cvl_dental_sealant")
    private Long cvlDentalSealant;

    @Column(name = "cvl_fluoride_application")
    private Long cvlFluorideApplication;

    @Column(name = "cvl_restoration")
    private Long cvlRestoration;

    @Column(name = "cvl_extraction")
    private Long cvlExtraction;

    @Column(name = "cvl_prosthodontics")
    private Long cvlProsthodontics;

    @Column(name = "cvl_other_procedures")
    private Long cvlOtherProcedures;

    @Column(name = "rtr_total")
    private Long rtrTotal;

    @Column(name = "rtr_consultation")
    private Long rtrConsultation;

    @Column(name = "rtr_oral_prophylaxis")
    private Long rtrOralProphylaxis;

    @Column(name = "rtr_dental_sealant")
    private Long rtrDentalSealant;

    @Column(name = "rtr_fluoride_application")
    private Long rtrFluorideApplication;

    @Column(name = "rtr_restoration")
    private Long rtrRestoration;

    @Column(name = "rtr_extraction")
    private Long rtrExtraction;

    @Column(name = "rtr_prosthodontics")
    private Long rtrProsthodontics;

    @Column(name = "rtr_other_procedures")
    private Long rtrOtherProcedures;

    @Column(name = "fo1_examined")
    private Long fo1Examined;

    @Column(name = "up_promotion")
    private Long upPromotion;

    @Column(name = "up_training")
    private Long upTraining;

    @Column(name = "nup_promotion")
    private Long nupPromotion;
    
    @Column(name = "grand_total")
    private Long grandTotal;
    
    @Column(name = "staff_sign")
    private byte[] staffSign;
    
    @Column(name = "chief_sign")
    private byte[] chiefSign;
    
    @Column(name = "staff_name")
    private String staffName;
    
    @Column(name = "chief_name")
    private String chiefName;
    
    @OneToOne
    @JoinColumn(name = "services_id", referencedColumnName = "service_id", insertable = false, updatable = false)
    ServiceRendered serviceRendered;
    
    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id", insertable = false, updatable = false)
    DentalExam dentalExam;
    
    public Regional() {
    	
    }

   

	public Regional(Long regionalId, Long servicesId, Long examId, String regionName, String reportMonth,
			String reportYear, Long unifTotal, Long unifConsultation, Long unifOralProphylaxis, Long unifDentalSealant,
			Long unifFluorideApplication, Long unifRestoration, Long unifExtraction, Long unifProsthodontics,
			Long unifOtherProcedures, Long nonunifTotal, Long nonunifConsultation, Long nonunifOralProphylaxis,
			Long nonunifDentalSealant, Long nonunifFluorideApplication, Long nonunifRestoration, Long nonunifExtraction,
			Long nonunifProsthodontics, Long nonunifOtherProcedures, Long dpdTotal, Long dpdConsultation,
			Long dpdOralProphylaxis, Long dpdDentalSealant, Long dpdFluorideApplication, Long dpdRestoration,
			Long dpdExtraction, Long dpdProsthodontics, Long dpdOtherProcedures, Long cvlTotal, Long cvlConsultation,
			Long cvlOralProphylaxis, Long cvlDentalSealant, Long cvlFluorideApplication, Long cvlRestoration,
			Long cvlExtraction, Long cvlProsthodontics, Long cvlOtherProcedures, Long rtrTotal, Long rtrConsultation,
			Long rtrOralProphylaxis, Long rtrDentalSealant, Long rtrFluorideApplication, Long rtrRestoration,
			Long rtrExtraction, Long rtrProsthodontics, Long rtrOtherProcedures, Long fo1Examined, Long upPromotion,
			Long upTraining, Long nupPromotion, Long grandTotal, byte[] staffSign, byte[] chiefSign, String staffName,
			String chiefName, ServiceRendered serviceRendered, DentalExam dentalExam) {
		super();
		this.regionalId = regionalId;
		this.servicesId = servicesId;
		this.examId = examId;
		this.regionName = regionName;
		this.reportMonth = reportMonth;
		this.reportYear = reportYear;
		this.unifTotal = unifTotal;
		this.unifConsultation = unifConsultation;
		this.unifOralProphylaxis = unifOralProphylaxis;
		this.unifDentalSealant = unifDentalSealant;
		this.unifFluorideApplication = unifFluorideApplication;
		this.unifRestoration = unifRestoration;
		this.unifExtraction = unifExtraction;
		this.unifProsthodontics = unifProsthodontics;
		this.unifOtherProcedures = unifOtherProcedures;
		this.nonunifTotal = nonunifTotal;
		this.nonunifConsultation = nonunifConsultation;
		this.nonunifOralProphylaxis = nonunifOralProphylaxis;
		this.nonunifDentalSealant = nonunifDentalSealant;
		this.nonunifFluorideApplication = nonunifFluorideApplication;
		this.nonunifRestoration = nonunifRestoration;
		this.nonunifExtraction = nonunifExtraction;
		this.nonunifProsthodontics = nonunifProsthodontics;
		this.nonunifOtherProcedures = nonunifOtherProcedures;
		this.dpdTotal = dpdTotal;
		this.dpdConsultation = dpdConsultation;
		this.dpdOralProphylaxis = dpdOralProphylaxis;
		this.dpdDentalSealant = dpdDentalSealant;
		this.dpdFluorideApplication = dpdFluorideApplication;
		this.dpdRestoration = dpdRestoration;
		this.dpdExtraction = dpdExtraction;
		this.dpdProsthodontics = dpdProsthodontics;
		this.dpdOtherProcedures = dpdOtherProcedures;
		this.cvlTotal = cvlTotal;
		this.cvlConsultation = cvlConsultation;
		this.cvlOralProphylaxis = cvlOralProphylaxis;
		this.cvlDentalSealant = cvlDentalSealant;
		this.cvlFluorideApplication = cvlFluorideApplication;
		this.cvlRestoration = cvlRestoration;
		this.cvlExtraction = cvlExtraction;
		this.cvlProsthodontics = cvlProsthodontics;
		this.cvlOtherProcedures = cvlOtherProcedures;
		this.rtrTotal = rtrTotal;
		this.rtrConsultation = rtrConsultation;
		this.rtrOralProphylaxis = rtrOralProphylaxis;
		this.rtrDentalSealant = rtrDentalSealant;
		this.rtrFluorideApplication = rtrFluorideApplication;
		this.rtrRestoration = rtrRestoration;
		this.rtrExtraction = rtrExtraction;
		this.rtrProsthodontics = rtrProsthodontics;
		this.rtrOtherProcedures = rtrOtherProcedures;
		this.fo1Examined = fo1Examined;
		this.upPromotion = upPromotion;
		this.upTraining = upTraining;
		this.nupPromotion = nupPromotion;
		this.grandTotal = grandTotal;
		this.staffSign = staffSign;
		this.chiefSign = chiefSign;
		this.staffName = staffName;
		this.chiefName = chiefName;
		this.serviceRendered = serviceRendered;
		this.dentalExam = dentalExam;
	}



	public Long getExamId() {
		return examId;
	}



	public void setExamId(Long examId) {
		this.examId = examId;
	}



	public DentalExam getDentalExam() {
		return dentalExam;
	}



	public void setDentalExam(DentalExam dentalExam) {
		this.dentalExam = dentalExam;
	}



	public Long getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(Long regionalId) {
		this.regionalId = regionalId;
	}

	public Long getServicesId() {
		return servicesId;
	}

	public void setServicesId(Long servicesId) {
		this.servicesId = servicesId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getReportMonth() {
		return reportMonth;
	}

	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}

	public String getReportYear() {
		return reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	public Long getUnifTotal() {
		return unifTotal;
	}

	public void setUnifTotal(Long unifTotal) {
		this.unifTotal = unifTotal;
	}

	public Long getUnifConsultation() {
		return unifConsultation;
	}

	public void setUnifConsultation(Long unifConsultation) {
		this.unifConsultation = unifConsultation;
	}

	public Long getUnifOralProphylaxis() {
		return unifOralProphylaxis;
	}

	public void setUnifOralProphylaxis(Long unifOralProphylaxis) {
		this.unifOralProphylaxis = unifOralProphylaxis;
	}

	public Long getUnifDentalSealant() {
		return unifDentalSealant;
	}

	public void setUnifDentalSealant(Long unifDentalSealant) {
		this.unifDentalSealant = unifDentalSealant;
	}

	public Long getUnifFluorideApplication() {
		return unifFluorideApplication;
	}

	public void setUnifFluorideApplication(Long unifFluorideApplication) {
		this.unifFluorideApplication = unifFluorideApplication;
	}

	public Long getUnifRestoration() {
		return unifRestoration;
	}

	public void setUnifRestoration(Long unifRestoration) {
		this.unifRestoration = unifRestoration;
	}

	public Long getUnifExtraction() {
		return unifExtraction;
	}

	public void setUnifExtraction(Long unifExtraction) {
		this.unifExtraction = unifExtraction;
	}

	public Long getUnifProsthodontics() {
		return unifProsthodontics;
	}

	public void setUnifProsthodontics(Long unifProsthodontics) {
		this.unifProsthodontics = unifProsthodontics;
	}

	public Long getUnifOtherProcedures() {
		return unifOtherProcedures;
	}

	public void setUnifOtherProcedures(Long unifOtherProcedures) {
		this.unifOtherProcedures = unifOtherProcedures;
	}

	public Long getNonunifTotal() {
		return nonunifTotal;
	}

	public void setNonunifTotal(Long nonunifTotal) {
		this.nonunifTotal = nonunifTotal;
	}

	public Long getNonunifConsultation() {
		return nonunifConsultation;
	}

	public void setNonunifConsultation(Long nonunifConsultation) {
		this.nonunifConsultation = nonunifConsultation;
	}

	public Long getNonunifOralProphylaxis() {
		return nonunifOralProphylaxis;
	}

	public void setNonunifOralProphylaxis(Long nonunifOralProphylaxis) {
		this.nonunifOralProphylaxis = nonunifOralProphylaxis;
	}

	public Long getNonunifDentalSealant() {
		return nonunifDentalSealant;
	}

	public void setNonunifDentalSealant(Long nonunifDentalSealant) {
		this.nonunifDentalSealant = nonunifDentalSealant;
	}

	public Long getNonunifFluorideApplication() {
		return nonunifFluorideApplication;
	}

	public void setNonunifFluorideApplication(Long nonunifFluorideApplication) {
		this.nonunifFluorideApplication = nonunifFluorideApplication;
	}

	public Long getNonunifRestoration() {
		return nonunifRestoration;
	}

	public void setNonunifRestoration(Long nonunifRestoration) {
		this.nonunifRestoration = nonunifRestoration;
	}

	public Long getNonunifExtraction() {
		return nonunifExtraction;
	}

	public void setNonunifExtraction(Long nonunifExtraction) {
		this.nonunifExtraction = nonunifExtraction;
	}

	public Long getNonunifProsthodontics() {
		return nonunifProsthodontics;
	}

	public void setNonunifProsthodontics(Long nonunifProsthodontics) {
		this.nonunifProsthodontics = nonunifProsthodontics;
	}

	public Long getNonunifOtherProcedures() {
		return nonunifOtherProcedures;
	}

	public void setNonunifOtherProcedures(Long nonunifOtherProcedures) {
		this.nonunifOtherProcedures = nonunifOtherProcedures;
	}

	public Long getDpdTotal() {
		return dpdTotal;
	}

	public void setDpdTotal(Long dpdTotal) {
		this.dpdTotal = dpdTotal;
	}

	public Long getDpdConsultation() {
		return dpdConsultation;
	}

	public void setDpdConsultation(Long dpdConsultation) {
		this.dpdConsultation = dpdConsultation;
	}

	public Long getDpdOralProphylaxis() {
		return dpdOralProphylaxis;
	}

	public void setDpdOralProphylaxis(Long dpdOralProphylaxis) {
		this.dpdOralProphylaxis = dpdOralProphylaxis;
	}

	public Long getDpdDentalSealant() {
		return dpdDentalSealant;
	}

	public void setDpdDentalSealant(Long dpdDentalSealant) {
		this.dpdDentalSealant = dpdDentalSealant;
	}

	public Long getDpdFluorideApplication() {
		return dpdFluorideApplication;
	}

	public void setDpdFluorideApplication(Long dpdFluorideApplication) {
		this.dpdFluorideApplication = dpdFluorideApplication;
	}

	public Long getDpdRestoration() {
		return dpdRestoration;
	}

	public void setDpdRestoration(Long dpdRestoration) {
		this.dpdRestoration = dpdRestoration;
	}

	public Long getDpdExtraction() {
		return dpdExtraction;
	}

	public void setDpdExtraction(Long dpdExtraction) {
		this.dpdExtraction = dpdExtraction;
	}

	public Long getDpdProsthodontics() {
		return dpdProsthodontics;
	}

	public void setDpdProsthodontics(Long dpdProsthodontics) {
		this.dpdProsthodontics = dpdProsthodontics;
	}

	public Long getDpdOtherProcedures() {
		return dpdOtherProcedures;
	}

	public void setDpdOtherProcedures(Long dpdOtherProcedures) {
		this.dpdOtherProcedures = dpdOtherProcedures;
	}

	public Long getCvlTotal() {
		return cvlTotal;
	}

	public void setCvlTotal(Long cvlTotal) {
		this.cvlTotal = cvlTotal;
	}

	public Long getCvlConsultation() {
		return cvlConsultation;
	}

	public void setCvlConsultation(Long cvlConsultation) {
		this.cvlConsultation = cvlConsultation;
	}

	public Long getCvlOralProphylaxis() {
		return cvlOralProphylaxis;
	}

	public void setCvlOralProphylaxis(Long cvlOralProphylaxis) {
		this.cvlOralProphylaxis = cvlOralProphylaxis;
	}

	public Long getCvlDentalSealant() {
		return cvlDentalSealant;
	}

	public void setCvlDentalSealant(Long cvlDentalSealant) {
		this.cvlDentalSealant = cvlDentalSealant;
	}

	public Long getCvlFluorideApplication() {
		return cvlFluorideApplication;
	}

	public void setCvlFluorideApplication(Long cvlFluorideApplication) {
		this.cvlFluorideApplication = cvlFluorideApplication;
	}

	public Long getCvlRestoration() {
		return cvlRestoration;
	}

	public void setCvlRestoration(Long cvlRestoration) {
		this.cvlRestoration = cvlRestoration;
	}

	public Long getCvlExtraction() {
		return cvlExtraction;
	}

	public void setCvlExtraction(Long cvlExtraction) {
		this.cvlExtraction = cvlExtraction;
	}

	public Long getCvlProsthodontics() {
		return cvlProsthodontics;
	}

	public void setCvlProsthodontics(Long cvlProsthodontics) {
		this.cvlProsthodontics = cvlProsthodontics;
	}

	public Long getCvlOtherProcedures() {
		return cvlOtherProcedures;
	}

	public void setCvlOtherProcedures(Long cvlOtherProcedures) {
		this.cvlOtherProcedures = cvlOtherProcedures;
	}

	public Long getRtrTotal() {
		return rtrTotal;
	}

	public void setRtrTotal(Long rtrTotal) {
		this.rtrTotal = rtrTotal;
	}

	public Long getRtrConsultation() {
		return rtrConsultation;
	}

	public void setRtrConsultation(Long rtrConsultation) {
		this.rtrConsultation = rtrConsultation;
	}

	public Long getRtrOralProphylaxis() {
		return rtrOralProphylaxis;
	}

	public void setRtrOralProphylaxis(Long rtrOralProphylaxis) {
		this.rtrOralProphylaxis = rtrOralProphylaxis;
	}

	public Long getRtrDentalSealant() {
		return rtrDentalSealant;
	}

	public void setRtrDentalSealant(Long rtrDentalSealant) {
		this.rtrDentalSealant = rtrDentalSealant;
	}

	public Long getRtrFluorideApplication() {
		return rtrFluorideApplication;
	}

	public void setRtrFluorideApplication(Long rtrFluorideApplication) {
		this.rtrFluorideApplication = rtrFluorideApplication;
	}

	public Long getRtrRestoration() {
		return rtrRestoration;
	}

	public void setRtrRestoration(Long rtrRestoration) {
		this.rtrRestoration = rtrRestoration;
	}

	public Long getRtrExtraction() {
		return rtrExtraction;
	}

	public void setRtrExtraction(Long rtrExtraction) {
		this.rtrExtraction = rtrExtraction;
	}

	public Long getRtrProsthodontics() {
		return rtrProsthodontics;
	}

	public void setRtrProsthodontics(Long rtrProsthodontics) {
		this.rtrProsthodontics = rtrProsthodontics;
	}

	public Long getRtrOtherProcedures() {
		return rtrOtherProcedures;
	}

	public void setRtrOtherProcedures(Long rtrOtherProcedures) {
		this.rtrOtherProcedures = rtrOtherProcedures;
	}

	public Long getFo1Examined() {
		return fo1Examined;
	}

	public void setFo1Examined(Long fo1Examined) {
		this.fo1Examined = fo1Examined;
	}

	public Long getUpPromotion() {
		return upPromotion;
	}

	public void setUpPromotion(Long upPromotion) {
		this.upPromotion = upPromotion;
	}

	public Long getUpTraining() {
		return upTraining;
	}

	public void setUpTraining(Long upTraining) {
		this.upTraining = upTraining;
	}

	public Long getNupPromotion() {
		return nupPromotion;
	}

	public void setNupPromotion(Long nupPromotion) {
		this.nupPromotion = nupPromotion;
	}

	public Long getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Long grandTotal) {
		this.grandTotal = grandTotal;
	}

	public byte[] getStaffSign() {
		return staffSign;
	}

	public void setStaffSign(byte[] staffSign) {
		this.staffSign = staffSign;
	}

	public byte[] getChiefSign() {
		return chiefSign;
	}

	public void setChiefSign(byte[] chiefSign) {
		this.chiefSign = chiefSign;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getChiefName() {
		return chiefName;
	}

	public void setChiefName(String chiefName) {
		this.chiefName = chiefName;
	}

	public ServiceRendered getServiceRendered() {
		return serviceRendered;
	}

	public void setServiceRendered(ServiceRendered serviceRendered) {
		this.serviceRendered = serviceRendered;
	}
    
    
}
