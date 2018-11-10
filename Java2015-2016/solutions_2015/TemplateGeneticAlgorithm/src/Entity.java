import java.util.function.Function;

public class Entity {
	int genome;

	public Entity(int genome) {
		this.genome = genome;
	}

	void mutate(Function<Integer, Integer> mutatorFun) {
		genome = mutatorFun.apply(genome);
	}
}
