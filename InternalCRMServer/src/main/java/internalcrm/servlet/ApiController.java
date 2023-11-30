package internalcrm.servlet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
 
    @RequestMapping("/model/leads")
    public void getLead(@RequestParam(value = "lowAnnualRevenue")long lowAnnualRevenue, @RequestParam(value = "highAnnualRevenue")long highAnnualRevenue,
                        @RequestParam(value = "state")String state) {
        System.out.println(lowAnnualRevenue + "" + highAnnualRevenue + state);
    }

    @RequestMapping("/model/lead")
    public void getLead2() {
        System.out.println("model/lead");
    }
}