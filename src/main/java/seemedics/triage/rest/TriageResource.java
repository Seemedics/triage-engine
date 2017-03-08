package seemedics.triage.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author victorp
 */

@Slf4j
@RestController
@RequestMapping("/v0/triage")
public class TriageResource {

    @RequestMapping("")
    @ResponseBody()
    String root() {
        log.debug("/v0/triage is called");
        return "This is the root triage end point";
    }

}
