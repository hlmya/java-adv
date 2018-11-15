package advjava.exam;

import java.util.Optional;
import java.util.function.Supplier;

public enum MoonPhase {
	PHASE0("New Moon"),
	PHASE1,
	PHASE2,
	PHASE3,
	PHASE4,
	PHASE5,
	PHASE6,
	PHASE7,
	PHASE8,
	PHASE9,
	PHASE10,
	PHASE11,
	PHASE12,
	PHASE13,
	PHASE14("Full Moon"),
	PHASE15,
	PHASE16,
	PHASE17,
	PHASE18,
	PHASE19,
	PHASE20,
	PHASE21,
	PHASE22,
	PHASE23,
	PHASE24,
	PHASE25,
	PHASE26,
	PHASE27;

	private String phaseName;

	private MoonPhase() {
		setPhaseName("Phase " + ordinal());
	}

	private MoonPhase(String phaseName) {
		setPhaseName(phaseName);
	}

	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	@Override
	public String toString() {
		return phaseName;
	}

	public static Optional<MoonPhase> getPhase(String name) {
		for (MoonPhase phase : MoonPhase.values()) {
			if (phase.getPhaseName().equals(name)) {
				return Optional.of(phase);
			}
		}

		return Optional.empty();
	}

	public Supplier<MoonPhase> getSupplier(int[] steps) {
		MoonPhase thisPhase = this;
		return new Supplier<MoonPhase>() {
			MoonPhase currentPhase = thisPhase;
			// Indexes "steps". This element in "steps" shows us how many steps to take from the current phase.
			int stepsIdx = 0;

			@Override
			public MoonPhase get() {
				MoonPhase retval = currentPhase;
				currentPhase = currentPhase.getPhaseAfter(steps[stepsIdx]);
				stepsIdx = moduloStep(stepsIdx, 1, steps.length);
				return retval;
			}
		};
	}

	/**
	 * Utility function.
	 * 
	 * @param stepCount
	 *            The number of steps to take.
	 * @return The {@link MoonPhase} that is {@code stepCount} steps after the
	 *         current {@link MoonPhase}.
	 */
	private MoonPhase getPhaseAfter(int stepCount) {
		int maxMoonIdx = MoonPhase.values().length;
		int newOrdinal = moduloStep(ordinal(), stepCount, maxMoonIdx);
		return MoonPhase.values()[newOrdinal];
	}

	/**
	 * Utility function. Takes {@code stepCount} steps starting from
	 * {@code start}, and makes sure that we don't end up at {@code endIdx} or higher.
	 */
	private int moduloStep(int start, int stepCount, int endIdx) {
		return (start + stepCount) % endIdx;
	}
}
