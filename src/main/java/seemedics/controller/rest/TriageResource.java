package seemedics.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seemedics.service.triage.engine.TriageNextIn;
import seemedics.service.triage.engine.TriageStartIn;
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
    TriageResult start(@RequestBody TriageStartIn triageStartIn) {
        log.info("triage.start v0 is called with the following input: {}", triageStartIn);
        return triageEngine.start(triageStartIn);
    }


    @PostMapping(path = "/next")
    TriageResult next(@RequestBody TriageNextIn triageNextIn) {
        log.info("triage.next v0 is called with the following input: {}", triageNextIn);
        return triageEngine.next(triageNextIn);
    }

}
