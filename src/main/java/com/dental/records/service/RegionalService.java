package com.dental.records.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.time.ZoneId;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.DentalExam;
import com.dental.records.model.Regional;
import com.dental.records.model.ServiceRendered;
import com.dental.records.repository.DentalExamRepository;
import com.dental.records.repository.RegionalRepository;
import com.dental.records.repository.ServiceRenderedRepository;

@Service
public class RegionalService {
	private final RegionalRepository regionalRepo;
	private final ServiceRenderedRepository renderedRepo;
	private final DentalExamRepository examRepo;
	
	@Autowired
	public RegionalService(RegionalRepository regionalRepo, ServiceRenderedRepository renderedRepo,
			DentalExamRepository examRepo) {
		super();
		this.regionalRepo = regionalRepo;
		this.renderedRepo = renderedRepo;
		this.examRepo = examRepo;
	}
	
    String categorizeRank(String rank) {
        if (rank == null) return "unknown";

        // Uniformed ranks
        List<String> uniformedRanks = List.of(
            "Fire Director", "Fire Chief Superintendent", "Fire Senior Superintendent",
            "Fire Superintendent", "Fire Chief Inspector", "Fire Senior Inspector",
            "Fire Inspector", "Senior Fire Officer 4", "Senior Fire Officer 3",
            "Senior Fire Officer 2", "Senior Fire Officer 1", "Fire Officer 3",
            "Fire Officer 2", "Fire Officer 1"
        );

        if (uniformedRanks.contains(rank)) return "uniformed";

        // Non-uniformed ranks
        List<String> nonUniformedRanks = List.of(
            "Non-Uniformed", "Clerk", "Others"
        );

        if (nonUniformedRanks.contains(rank)) return "non-uniformed";

        // Other specific ranks
        switch (rank) {
            case "Dependent":
                return "dependent";
            case "Retiree":
                return "retiree";
            case "Civilian":
                return "civilian";
            default:
                return "non-uniformed"; // Assuming any other rank defaults to non-uniformed
        }
    }
    
	public Long autoGenerate(String regionPlace, String repMonth, String repYear) {
		Regional regionForm = new Regional();

	    // Parse the repMonth and repYear to create a YearMonth instance
	    int month = Integer.parseInt(repMonth);
	    int year = Integer.parseInt(repYear);
	    YearMonth yearMonth = YearMonth.of(year, month);

	    // Get the start and end dates of the month
	    LocalDate startDate = yearMonth.atDay(1);
	    LocalDate endDate = yearMonth.atEndOfMonth();

	    // Convert LocalDate to Date
	    Date start = (Date) Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    Date end = (Date) Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<DentalExam> examForm = examRepo.findByUnitAssignAndCheckupDateBetween(regionPlace, start, end);
		
		long unifOralProphylaxis = 0, unifDentalSealant = 0, unifFluorideApplication = 0, unifRestoration = 0, unifExtraction = 0, unifProsthodontics = 0, unifOtherProcedures = 0;
	    long nonUnifOralProphylaxis = 0, nonUnifDentalSealant = 0, nonUnifFluorideApplication = 0, nonUnifRestoration = 0, nonUnifExtraction = 0, nonUnifProsthodontics = 0, nonUnifOtherProcedures = 0;
	    long depOralProphylaxis = 0, depDentalSealant = 0, depFluorideApplication = 0, depRestoration = 0, depExtraction = 0, depProsthodontics = 0, depOtherProcedures = 0;
	    long retOralProphylaxis = 0, retDentalSealant = 0, retFluorideApplication = 0, retRestoration = 0, retExtraction = 0, retProsthodontics = 0, retOtherProcedures = 0;
	    long civOralProphylaxis = 0, civDentalSealant = 0, civFluorideApplication = 0, civRestoration = 0, civExtraction = 0, civProsthodontics = 0, civOtherProcedures = 0;



	    // For each DentalExam, fetch the corresponding ServiceRendered records and count treatments
	    for (DentalExam exam : examForm) {
	        List<ServiceRendered> renderedForm = renderedRepo.findByExamId(exam.getExamId());
	        String categorizedRank = categorizeRank(exam.getRank());
	            // Assuming rank is in DentalExam
	        for (ServiceRendered service : renderedForm) {
	            String treatment = service.getTreatment();
	            switch (treatment) {
	                case "oral prophylaxis":
	                    switch (categorizedRank) {
	                        case "uniformed":
	                            unifOralProphylaxis++;
	                            break;
	                        case "non-uniformed":
	                            nonUnifOralProphylaxis++;
	                            break;
	                        case "dependent":
	                            depOralProphylaxis++;
	                            break;
	                        case "retiree":
	                            retOralProphylaxis++;
	                            break;
	                        case "civilian":
	                            civOralProphylaxis++;
	                            break;
	                    }
	                    break;
	                case "dental sealant":
	                    switch (categorizedRank) {
	                        case "uniformed":
	                            unifDentalSealant++;
	                            break;
	                        case "non-uniformed":
	                            nonUnifDentalSealant++;
	                            break;
	                        case "dependent":
	                            depDentalSealant++;
	                            break;
	                        case "retiree":
	                            retDentalSealant++;
	                            break;
	                        case "civilian":
	                            civDentalSealant++;
	                            break;
	                    }
	                    break;
	                case "fluoride application":
	                    switch (categorizedRank) {
	                        case "uniformed":
	                            unifFluorideApplication++;
	                            break;
	                        case "non-uniformed":
	                            nonUnifFluorideApplication++;
	                            break;
	                        case "dependent":
	                            depFluorideApplication++;
	                            break;
	                        case "retiree":
	                            retFluorideApplication++;
	                            break;
	                        case "civilian":
	                            civFluorideApplication++;
	                            break;
	                    }
	                    break;
	                case "restoration":
	                    switch (categorizedRank) {
	                        case "uniformed":
	                            unifRestoration++;
	                            break;
	                        case "non-uniformed":
	                            nonUnifRestoration++;
	                            break;
	                        case "dependent":
	                            depRestoration++;
	                            break;
	                        case "retiree":
	                            retRestoration++;
	                            break;
	                        case "civilian":
	                            civRestoration++;
	                            break;
	                    }
	                    break;
	                case "extraction":
	                    switch (categorizedRank) {
	                        case "uniformed":
	                            unifExtraction++;
	                            break;
	                        case "non-uniformed":
	                            nonUnifExtraction++;
	                            break;
	                        case "dependent":
	                            depExtraction++;
	                            break;
	                        case "retiree":
	                            retExtraction++;
	                            break;
	                        case "civilian":
	                            civExtraction++;
	                            break;
	                    }
	                    break;
	                case "prosthodontics":
	                    switch (categorizedRank) {
	                        case "uniformed":
	                            unifProsthodontics++;
	                            break;
	                        case "non-uniformed":
	                            nonUnifProsthodontics++;
	                            break;
	                        case "dependent":
	                            depProsthodontics++;
	                            break;
	                        case "retiree":
	                            retProsthodontics++;
	                            break;
	                        case "civilian":
	                            civProsthodontics++;
	                            break;
	                    }
	                    break;
	                default:
	                    switch (categorizedRank) {
	                        case "uniformed":
	                            unifOtherProcedures++;
	                            break;
	                        case "non-uniformed":
	                            nonUnifOtherProcedures++;
	                            break;
	                        case "dependent":
	                            depOtherProcedures++;
	                            break;
	                        case "retiree":
	                            retOtherProcedures++;
	                            break;
	                        case "civilian":
	                            civOtherProcedures++;
	                            break;
	                    }
	                    break;
	            }
	        }
	    }
	    Long unifTotal = unifOralProphylaxis+unifDentalSealant+unifFluorideApplication+unifRestoration+unifExtraction+unifProsthodontics+unifOtherProcedures;
	    Long nonUnifTotal = nonUnifOralProphylaxis+nonUnifDentalSealant+nonUnifFluorideApplication+nonUnifRestoration+nonUnifExtraction+nonUnifProsthodontics+nonUnifOtherProcedures;
	    Long dpdTotal = depOralProphylaxis+depDentalSealant+depFluorideApplication+depRestoration+depExtraction+depProsthodontics+depOtherProcedures;
	    Long rtrTotal = retOralProphylaxis+retDentalSealant+retFluorideApplication+retRestoration+retExtraction+retProsthodontics+retOralProphylaxis;
	    Long cvlTotal = civOralProphylaxis+civDentalSealant+civFluorideApplication+civRestoration+civExtraction+civProsthodontics+civOralProphylaxis;
	    Long grandTotal = unifTotal+nonUnifTotal+dpdTotal+rtrTotal+cvlTotal;
	    		
	    regionForm.setRegionName(regionPlace);	
	    // Set the counted values to regionForm
	    regionForm.setUnifOralProphylaxis(unifOralProphylaxis);
	    regionForm.setUnifDentalSealant(unifDentalSealant);
	    regionForm.setUnifFluorideApplication(unifFluorideApplication);
	    regionForm.setUnifRestoration(unifRestoration);
	    regionForm.setUnifExtraction(unifExtraction);
	    regionForm.setUnifProsthodontics(unifProsthodontics);
	    regionForm.setUnifOtherProcedures(unifOtherProcedures);
	    regionForm.setUnifTotal(unifTotal);

	    regionForm.setNonunifOralProphylaxis(nonUnifOralProphylaxis);
	    regionForm.setNonunifDentalSealant(nonUnifDentalSealant);
	    regionForm.setNonunifFluorideApplication(nonUnifFluorideApplication);
	    regionForm.setNonunifRestoration(nonUnifRestoration);
	    regionForm.setNonunifExtraction(nonUnifExtraction);
	    regionForm.setNonunifProsthodontics(nonUnifProsthodontics);
	    regionForm.setNonunifOtherProcedures(nonUnifOtherProcedures);
	    regionForm.setNonunifTotal(nonUnifTotal);


	    regionForm.setDpdOralProphylaxis(depOralProphylaxis);
	    regionForm.setDpdDentalSealant(depDentalSealant);
	    regionForm.setDpdFluorideApplication(depFluorideApplication);
	    regionForm.setDpdRestoration(depRestoration);
	    regionForm.setDpdExtraction(depExtraction);
	    regionForm.setDpdProsthodontics(depProsthodontics);
	    regionForm.setDpdOtherProcedures(depOtherProcedures);
	    regionForm.setDpdTotal(dpdTotal);
	    
	    regionForm.setRtrOralProphylaxis(retOralProphylaxis);
	    regionForm.setRtrDentalSealant(retDentalSealant);
	    regionForm.setRtrFluorideApplication(retFluorideApplication);
	    regionForm.setRtrRestoration(retRestoration);
	    regionForm.setRtrExtraction(retExtraction);
	    regionForm.setRtrProsthodontics(retProsthodontics);
	    regionForm.setRtrOtherProcedures(retOtherProcedures);
	    regionForm.setRtrTotal(rtrTotal);

	    regionForm.setCvlOralProphylaxis(civOralProphylaxis);
	    regionForm.setCvlDentalSealant(civDentalSealant);
	    regionForm.setCvlFluorideApplication(civFluorideApplication);
	    regionForm.setCvlRestoration(civRestoration);
	    regionForm.setCvlExtraction(civExtraction);
	    regionForm.setCvlProsthodontics(civProsthodontics);
	    regionForm.setCvlOtherProcedures(civOtherProcedures);
	    regionForm.setCvlTotal(cvlTotal);
	    
	    
	    regionForm.setGrandTotal(grandTotal);
	    // Save the regional record to the repository (assuming you have a regionalRepository)
	    regionalRepo.save(regionForm);

		
		return regionForm.getGrandTotal();
	}
	
	public void addEntry(Regional regional) {
		regionalRepo.save(regional);
	}
	
	public Regional save(Regional regional, MultipartFile staffSign, MultipartFile chiefSign) throws IOException {
    	byte[] staffSignBytes = staffSign.getBytes();
    	byte[] chiefSignBytes = chiefSign.getBytes();
    	regional.setStaffSign(staffSignBytes);
    	regional.setChiefSign(chiefSignBytes);
    	return regionalRepo.save(regional);
    }

	public Regional getRegionalsByRegionNameAndReportMonthAndReportYear(String regionName, String reportMonth, String reportYear) {
        return regionalRepo.findByRegionNameAndReportMonthAndReportYear(regionName, reportMonth, reportYear);
    }
}
