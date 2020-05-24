package com.ssm.mf.jvm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JVMController {

    @RequestMapping("/jvisualVMTestBTrace")
    public String jvisualVMTestBTrace(){

        return " hello";
    }

}
