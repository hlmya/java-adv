package advjava.exam;

import java.util.Optional;
import java.util.function.Supplier;

public enum MoonPhase {
	PHASE0("New Moon"),
	PHASE1,PHASE2,PHASE3,PHASE4,PHASE5,PHASE6,PHASE7,PHASE8,PHASE9,PHASE10,
	PHASE11,PHASE12,PHASE13,
	PHASE14("Full Moon"),
	PHASE15,PHASE16,PHASE17,PHASE18,PHASE19,PHASE20,PHASE21,PHASE22,PHASE23,
	PHASE24,PHASE25,PHASE26,PHASE27;
	
	private String phaseName;
	
	private MoonPhase(){
		this.phaseName = "Phase " + ordinal();
	};
	
	private MoonPhase(String phaseName){
		this.phaseName = phaseName;
	}

	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	};
	
	@Override
	public String toString() {
		return this.phaseName;
	}
	
	public static Optional<MoonPhase> getPhase(String name) {
		for(MoonPhase phase: MoonPhase.values()) {
			if(name.equals(phase.getPhaseName())) {
				return Optional.of(phase);
			}
		}
		return Optional.empty();
	}
	
	public Supplier<MoonPhase> getSupplier(int[] steps) {
		MoonPhase thisPhase = this; // get current phase
		
		Supplier<MoonPhase> getPhase = new Supplier<MoonPhase>() {
			MoonPhase currentPhase = thisPhase;
			int stepIdx = 0;
			
			@Override
			public MoonPhase get() {
				MoonPhase retPhase = currentPhase; // get current phase and return
				currentPhase = getPhaseAfter(currentPhase,steps[stepIdx% steps.length]); // update current phase
				stepIdx++;//update stepIdx
				
				return retPhase;
			}
		};
		return getPhase;
	}
	
	private MoonPhase getPhaseAfter(MoonPhase current, int stepValue) {
		int lenPhases = MoonPhase.values().length;
		int currentIdx = current.ordinal(); //idx of current phase in enum
		//return phase with next stepvalue, and modulus as weekday
		return MoonPhase.values()[(currentIdx + stepValue)%lenPhases]; 
	}
	
}
