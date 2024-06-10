package com.reports.ms.reportsms.services;

import com.reports.ms.reportsms.Config.RestTemplateConfig;
import com.reports.ms.reportsms.models.RepairDetailModel;
import com.reports.ms.reportsms.repositories.ReportOneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportOneService {

    /* repository layer methods */

    @Autowired
    ReportOneRepository reportOneRepository;

    @Autowired
    RestTemplate restTemplate;



    /* Aux methods */

    /*--------------------------------------------------------------------------------------------------------
     * getAllRepairDetails: Method to retrieve all repair detail entities from the repair detail microservice.
     *
     * @return - List of all repair detail entities.
     --------------------------------------------------------------------------------------------------------*/
    public List<RepairDetailModel> getAllRepairDetails() {
        ResponseEntity<RepairDetailModel[]> responseEntity = restTemplate.exchange(
                "http://repair-ms/ms/repairdetail",
                HttpMethod.GET,
                null,
                RepairDetailModel[].class
        );

        return Arrays.asList(responseEntity.getBody());
    }


    /* POST OPERATIONS */

    /* REPORT NOT READY */
    public Boolean createReport(LocalDate month){
        /* first, we get all repairs */
        List<RepairDetailModel> repairs = getAllRepairDetails();

        /* we add all the repairs present in the month */
        List<RepairDetailModel> repairsForMonth = new ArrayList<>();
        for (RepairDetailModel repair : repairs) {
            LocalDate repairDate = repair.getRepairDate();
            if (repairDate.getMonth() == month.getMonth()) {
                repairsForMonth.add(repair);
            }
        }
        return null;
    }
}
