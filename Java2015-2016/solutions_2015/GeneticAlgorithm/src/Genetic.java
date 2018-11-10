import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Genetic {
	static Random rndGen = new Random();

	static int sekritKoud = 351;

	public static void main(String[] args) {
		int populationCount = 3;
		int itetationCount = 500;
		int mutationProbabilty = 100; // out of 1000000
		int crossoverCount = 0;
		int removeWorstCount = 1;

		Supplier<Entity> createEntity = () -> new Entity(rndGen.nextInt(1000));
		Comparator<Entity> fitness = (e1, e2) -> Math.abs(e2.genome - sekritKoud) - Math.abs(e1.genome - sekritKoud);
		Function<Entity, Entity> mutatorFun = e -> new Entity((e.genome + rndGen.nextInt(100)) % 1000);
		BiFunction<Entity, Entity, SimpleEntry<Entity, Entity>> crossover = (e1, e2) -> {
			int lower1 = e1.genome % 100;
			int lower2 = e2.genome % 100;
			int upper1 = e1.genome / 100;
			int upper2 = e1.genome / 100;

			Entity new1 = new Entity(upper1 * 100 + lower2);
			Entity new2 = new Entity(upper2 * 100 + lower1);
			return new SimpleEntry<Entity, Entity>(new1, new2);
		};

		Entity best = geneticAlgorithm(populationCount, createEntity, itetationCount, mutationProbabilty,
				crossoverCount, fitness, removeWorstCount, mutatorFun, crossover);

		System.out.println(best.genome);
	}

	static Entity geneticAlgorithm(int populationCount, Supplier<Entity> createEntity, int itetationCount,
			int mutationProbabilty, int crossoverCount, Comparator<Entity> fitness, int removeWorstCount,
			Function<Entity, Entity> mutatorFun,
			BiFunction<Entity, Entity, AbstractMap.SimpleEntry<Entity, Entity>> crossover) {

		Function<Entity, Entity> mutator = e -> {
			boolean shouldMutate = rndGen.nextInt(1000000) < mutationProbabilty;
			return shouldMutate ? mutatorFun.apply(e) : e;
		};

		List<Entity> population = createPopulation(populationCount, createEntity);
		for (int i = 0; i < itetationCount; i++) {
			List<Entity> newPopulation = population.stream().map(mutator).collect(Collectors.toList());
			doCrossover(crossoverCount, crossover, newPopulation);

			// throw away worst, retain best fitness
			population = newPopulation.stream().sorted((e1, e2) -> -fitness.compare(e1, e2)).limit(removeWorstCount)
					.collect(Collectors.toList());

			// note: this is usually not part of the genetic algorithm code
			// because the goal is not explicitly known
			if (population.get(0).genome == sekritKoud) {
				System.out.println("Found optimum at iteration#" + i);
				return population.get(0);
			}

			// refill population
			List<Entity> newEntities = createPopulation(removeWorstCount, createEntity);
			population.addAll(newEntities);
		}

		System.out.println("Didn't find optimum, returning best...");

		return population.stream().max(fitness).get();
	}

	private static void doCrossover(int crossoverCount,
			BiFunction<Entity, Entity, AbstractMap.SimpleEntry<Entity, Entity>> crossover, List<Entity> population) {
		// crossover
		for (int j = 0; j < crossoverCount; j++) {
			int idx1 = getRandomEntityIdx(population);
			int idx2 = getOtherRandomEntityIdx(population, idx1);

			Entity entity1 = population.get(idx1);
			Entity entity2 = population.get(idx2);
			AbstractMap.SimpleEntry<Entity, Entity> crossed = crossover.apply(entity1, entity2);

			population.set(idx1, entity1);
			population.set(idx2, entity1);
		}
	}

	private static int getOtherRandomEntityIdx(List<Entity> population, int idx1) {
		int idx2 = getRandomEntityIdx(population);
		while (idx2 == idx1) {
			idx2 = getRandomEntityIdx(population);
		}
		return idx2;
	}

	private static int getRandomEntityIdx(List<Entity> population) {
		return rndGen.nextInt(population.size());
	}

	private static List<Entity> createPopulation(int populationCount, Supplier<Entity> createEntity) {
		List<Entity> population = new ArrayList<>();
		for (int i = 0; i < populationCount; i++) {
			Entity newEntity = createEntity.get();
			population.add(newEntity);
		}
		return population;
	}
}
