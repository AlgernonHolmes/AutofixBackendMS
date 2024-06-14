package com.repair.ms.repairms.controller;


import com.repair.ms.repairms.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms/repair")
@CrossOrigin("*")
public class RepairController {

    /* API endpoints */

    /* Service layer methods */

    @Autowired
    RepairService repairService;



}
