package seemedics.model.triage.examples;

import seemedics.model.Fact;
import seemedics.model.dialog.PredefAnswer;
import seemedics.model.dialog.Question;
import seemedics.model.metadata.MedSymptomDescriptor;
import seemedics.model.triage.ConditionalFlow;
import seemedics.model.triage.TriageOutcome;
import seemedics.model.triage.TriageProtocol;
import seemedics.model.triage.Urgency;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author victorp
 */
public class SoreThroatProtocotData {

    private static String BODY_TEMP_SYM_DESC_ID = "sym-body-temperature";
    private static String SORE_THROAT_SYM_DESC_ID = "sym-sore-throat";
    private static String THROAT_WHITE_PATCHES_DESC_ID = "sym-throat-white-patches";




    public static Map<String, MedSymptomDescriptor> symptomsDescriptors(){
        Map<String, MedSymptomDescriptor> allSymptoms = new HashMap<>();


        MedSymptomDescriptor bodyTemperature = MedSymptomDescriptor.builder()
                .id(BODY_TEMP_SYM_DESC_ID)
                .name("Body Temperature")
                .build();

        MedSymptomDescriptor soreThroat = MedSymptomDescriptor.builder()
                .id(SORE_THROAT_SYM_DESC_ID)
                .name("Sore Throat")
                .build();

        MedSymptomDescriptor throatWhitePatches = MedSymptomDescriptor.builder()
                .id(THROAT_WHITE_PATCHES_DESC_ID)
                .name("White Patches on Throat")
                .build();

        allSymptoms.put(bodyTemperature.getId(),bodyTemperature);
        allSymptoms.put(soreThroat.getId(),soreThroat);
        allSymptoms.put(throatWhitePatches.getId(),throatWhitePatches);


        return allSymptoms;
    }


    public static Fact initialFact(){
        return Fact.builder()
                .id("fact-sore-throat")
                .ref(SORE_THROAT_SYM_DESC_ID)
                .name("Sore Throat")
                .status(Fact.Status.EXISTS)
                .build();
    }

    public static TriageProtocol protocol() {


        /**
         * Predef facts
         */
        Fact factNoWhitePatches = Fact.builder()
                .id("fact-no-white-patches-in-throat")
                .ref(THROAT_WHITE_PATCHES_DESC_ID)
                .name("No White Patches inside Throat")
                .status(Fact.Status.NOT_EXISTS)
                .build();

        Fact factWhitePatches = Fact.builder()
                .id("fact-no-white-patches-in-throat")
                .ref(THROAT_WHITE_PATCHES_DESC_ID)
                .name("No White Patches inside Throat")
                .status(Fact.Status.EXISTS)
                .build();

        Fact factFeverAbove40 = Fact.builder()
                .id("fact-fever-40-42")
                .ref(BODY_TEMP_SYM_DESC_ID)
                .name("Body Temperature 40-42")
                .status(Fact.Status.EXISTS)
                .value("temperature", "40-42")
                .build();


        Fact factFeverAbove39 = Fact.builder()
                .id("fact-fever-39-40")
                .ref(BODY_TEMP_SYM_DESC_ID)
                .name("Body Temperature 39-40")
                .status(Fact.Status.EXISTS)
                .value("temperature", "39-40")
                .build();

        Fact factFeverAbove37 = Fact.builder()
                .id("fact-fever-37-39")
                .ref(BODY_TEMP_SYM_DESC_ID)
                .name("Body Temperature 37-39")
                .status(Fact.Status.EXISTS)
                .value("temperature", "37-39")
                .build();

        Fact factNoFever = Fact.builder()
                .id("fact-fever-36-37")
                .ref(BODY_TEMP_SYM_DESC_ID)
                .name("Body Temperature 36-37")
                .status(Fact.Status.EXISTS)
                .value("temperature", "36-37")
                .build();

        PredefAnswer answFeverAbove37C = PredefAnswer.builder()
                .id("ans-fever=37-39")
                .name("Fever 37-39 C")
                .text("37-39 C")
                .build();

        PredefAnswer answFeverAbove39C = PredefAnswer.builder()
                .id("ans-fever=39-40")
                .name("Fever 39-40 C")
                .text("39-40 C")
                .build();


        PredefAnswer answFeverAbove40C = PredefAnswer.builder()
                .id("ans-fever=40-42")
                .name("Fever 40-42 C")
                .text("40-42 C")
                .build();

        PredefAnswer answNoFever = PredefAnswer.builder()
                .id("ans-no-fever")
                .name("No fever (36.6 C) C")
                .text("I have no fever")
                .build();

        Question doYouHaveFever = Question.builder()
                .id("q-do-u-have-fever")
                .name("q-do-u-have-fever")
                .text("Do you have fever?")
                .choice(answFeverAbove37C)
                .choice(answFeverAbove39C)
                .choice(answFeverAbove40C)
                .choice(answNoFever)
                .build();


        PredefAnswer answYesWhitePatches = PredefAnswer.builder()
                .id("ans-yes-white-patches")
                .name("ans-yes-white-patches")
                .text("White patches")
                .imageUrlOpt(Optional.of("http://www.foodpyramid.com/wp-content/uploads/2013/07/white-spots-on-back-of-throat-1.jpg"))
                .build();


        PredefAnswer answNoWhitePatches = PredefAnswer.builder()
                .id("ans-no-white-patches")
                .name("ans-no-white-patches")
                .text("White patches")
                .imageUrlOpt(Optional.of("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTApo2EAy6exxQPG18lo2-eibmgPWiUWQAurlsGl3xaVbS0bjwuwQ"))
                .build();


        Question doYouSeeWhitePatches = Question.builder()
                .id("q-do-u-see-white-patches")
                .name("q-do-u-see-white-patches")
                .text("Do you see white patches around the throat?")
                .choice(answYesWhitePatches)
                .choice(answNoWhitePatches)
                .build();


        Urgency callEmergency = Urgency.builder()
                .id("urg-10")
                .name("Urgency 10")
                .level(10)
                .interpretation("Call emergency now.")
                .build();

        Urgency docWithin24h = Urgency.builder()
                .id("urg-20")
                .name("Urgency 20")
                .level(20)
                .interpretation("I recommend you to see a doctor within 24 hours.")
                .build();

        Urgency pharmacist = Urgency.builder()
                .id("urg-30")
                .name("Urgency 30")
                .level(30)
                .interpretation("At this point I recommend you to get recommendation for a pharmacist.")
                .build();

        TriageOutcome flowForFeverAbove40 = TriageOutcome.builder()
                .id("flow-sore-throat-fever-40-42")
                .name("Sore Throat with Fever 40-42")
                .urgency(callEmergency)
                .build();

        TriageOutcome flowForWhitePatches = TriageOutcome.builder()
                .id("flow-sore-throat-with-white-patches")
                .name("Sore Throat with White Patches")
                .urgency(docWithin24h)
                .build();

        TriageOutcome flowForNoWhitePatches = TriageOutcome.builder()
                .id("flow-sore-throat-no-white-patches")
                .name("Sore Throat without White Patches")
                .urgency(pharmacist)
                .build();

        ConditionalFlow flowForFever36to40 = ConditionalFlow.builder()
                .id("flow-sore-throat-fever-36-40")
                .name("Sore Throat with Fever 36-40")
                .question(doYouSeeWhitePatches)
                .subFlow(answYesWhitePatches.getId(), flowForWhitePatches)
                .subFlow(answNoWhitePatches.getId(), flowForNoWhitePatches)
                .build();

        ConditionalFlow mainFlow = ConditionalFlow.builder()
                .id("flow-sore-throat")
                .name("Sore Throat")
                .question(doYouHaveFever)
                .subFlow(answFeverAbove40C.getId(), flowForFeverAbove40)
                .subFlow(answFeverAbove37C.getId(), flowForFever36to40)
                .subFlow(answFeverAbove39C.getId(), flowForFever36to40)
                .subFlow(answNoFever.getId(), flowForFever36to40)
                .build();


        TriageProtocol soreThroatProtocol = TriageProtocol.builder()
                .id("tri-prot-sore-throat")
                .name("Sore Throat")
                .initialSymptomDescId(SORE_THROAT_SYM_DESC_ID)
                .flow(mainFlow)
                .subFlow(mainFlow.getId(), mainFlow)
                .subFlow(flowForFever36to40.getId(), flowForFever36to40)
                .subFlow(flowForFeverAbove40.getId(),flowForFeverAbove40)
                .subFlow(flowForWhitePatches.getId(),flowForWhitePatches)
                .subFlow(flowForNoWhitePatches.getId(),flowForNoWhitePatches)
                .ans2fact(answNoFever.getId(), factNoFever)
                .ans2fact(answFeverAbove40C.getId(), factFeverAbove40)
                .ans2fact(answFeverAbove37C.getId(), factFeverAbove37)
                .ans2fact(answFeverAbove39C.getId(), factFeverAbove39)
                .ans2fact(answYesWhitePatches.getId(),factWhitePatches)
                .ans2fact(answNoWhitePatches.getId(), factNoWhitePatches)
                .build();

        return soreThroatProtocol;
    }


}
