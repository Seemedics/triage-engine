package seemedics.triage.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author victorp
 */


@RestController
@RequestMapping("/v0/triage")
public class TriageResource {


    @RequestMapping("")
    @ResponseBody()
    String root() {
        return "This is the root triage end point";
    }

}
