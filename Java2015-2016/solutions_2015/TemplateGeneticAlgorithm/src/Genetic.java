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

	static <T> T geneticAlgorithm(int populationCount, Supplier<T> createEntity, int itetationCount,
			int mutationProbabilty, int crossoverCount, Comparator<T> fitness, int removeWorstCount,
			Function<T, T> mutatorFun, BiFunction<T, T, AbstractMap.SimpleEntry<T, T>> crossover) {

		Function<T, T> mutator = e -> {
			boolean shouldMutate = rndGen.nextInt(1000000) < mutationProbabilty;
			return shouldMutate ? mutatorFun.apply(e) : e;
		};

		List<T> population = createPopulation(populationCount, createEntity);
		for (int i = 0; i < itetationCount; i++) {
			List<T> newPopulation = population.stream().map(mutator).collect(Collectors.toList());
			doCrossover(crossoverCount, crossover, newPopulation);

			// throw away worst, retain best fitness
			population = newPopulation.stream().sorted((e1, e2) -> -fitness.compare(e1, e2)).limit(removeWorstCount)
					.collect(Collectors.toList());

			// refill population
			List<T> newEntities = createPopulation(removeWorstCount, createEntity);
			population.addAll(newEntities);
		}

		return population.stream().max(fitness).get();
	}

	private static <T> void doCrossover(int crossoverCount, BiFunction<T, T, AbstractMap.SimpleEntry<T, T>> crossover,
			List<T> population) {
		// crossover
		for (int j = 0; j < crossoverCount; j++) {
			int idx1 = getRandomEntityIdx(population);
			int idx2 = getOtherRandomEntityIdx(population, idx1);

			T entity1 = population.get(idx1);
			T entity2 = population.get(idx2);
			AbstractMap.SimpleEntry<T, T> crossed = crossover.apply(entity1, entity2);

			population.set(idx1, entity1);
			population.set(idx2, entity1);
		}
	}

	private static <T> int getOtherRandomEntityIdx(List<T> population, int idx1) {
		int idx2 = getRandomEntityIdx(population);
		while (idx2 == idx1) {
			idx2 = getRandomEntityIdx(population);
		}
		return idx2;
	}

	private static <T> int getRandomEntityIdx(List<T> population) {
		return rndGen.nextInt(population.size());
	}

	private static <T> List<T> createPopulation(int populationCount, Supplier<T> createEntity) {
		List<T> population = new ArrayList<>();
		for (int i = 0; i < populationCount; i++) {
			T newEntity = createEntity.get();
			population.add(newEntity);
		}
		return population;
	}
}
