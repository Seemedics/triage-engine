package seemedics.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seemedics.model.Facts;
import seemedics.service.triage.engine.TriageEngine;
import seemedics.service.triage.engine.TriageResult;

/**
 * @author victorp
 */

@Slf4j
@RestController
@RequestMapping("/v0/triage")
public class TriageResource {

    @Autowired
    TriageEngine triageEngine;


    @GetMapping()
    String root() {
        log.info("Triage root v0 is called");
        return "This is the root triage end point";
    }


    @PostMapping(path = "/start")
    TriageResult start(@RequestBody Facts facts) {
        log.info("triage.start v0 is called with the following facts: {}", facts);
        return triageEngine.start(facts.facts);
    }

}
