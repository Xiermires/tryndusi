package org.tryndusi.routing.sa;

import java.lang.reflect.Array;

import org.tryndusi.routing.xorshift.Xorshift64;

public class Annealing<T> {

    private final Xorshift64 rand = new Xorshift64();

    private final Generator<T> generator;
    private final Evaluator<T> evaluator;

    private double temperature;
    private final double coolingRate;

    public Annealing(Generator<T> generator, Evaluator<T> evaluator, double temp, double cr) {
        this.generator = generator;
        this.evaluator = evaluator;
        this.temperature = temp;
        this.coolingRate = cr;
    }

    @SuppressWarnings("unchecked")
    public Result<T> simulate() {
        final int[] pos = new int[2];
        final T[] candidate = generator.generate();
        final T[] best = (T[]) Array.newInstance(candidate[0].getClass(), candidate.length);
        double bestEnergy = evaluator.evaluate(candidate);

        double currentEnergy = Double.POSITIVE_INFINITY, newEnergy = bestEnergy;
        while (temperature > 1e-10) {
            currentEnergy = newEnergy;
            swap(pos, candidate);
            newEnergy = evaluator.evaluate(candidate);

            if (newEnergy < bestEnergy) {
                System.arraycopy(candidate, 0, best, 0, candidate.length);
                bestEnergy = newEnergy;
                continue;
            }

            final double acceptance = acceptance(currentEnergy, newEnergy, temperature);
            if (rand.nextDouble() > acceptance) {
                final T ref = candidate[pos[0]];
                candidate[pos[0]] = candidate[pos[1]];
                candidate[pos[1]] = ref;
                newEnergy = currentEnergy;
            } else {
                // keep the changes
            }
            temperature *= 1 - coolingRate;
        }
        return new Result<>(best, bestEnergy);
    }

    private void swap(int[] pos, T[] candidate) {
        pos[0] = (int) rand.nextDouble(0, candidate.length);
        pos[1] = (int) rand.nextDouble(0, candidate.length);

        final T ref = candidate[pos[0]];
        candidate[pos[0]] = candidate[pos[1]];
        candidate[pos[1]] = ref;
    }

    private double acceptance(double currentEnergy, double newEnergy, double temperature) {
        return Math.exp((currentEnergy - newEnergy) / temperature);
    }

    public static class Result<T> {

        public final T[] solution;
        public final double energy;

        public Result(T[] solution, double energy) {
            this.solution = solution;
            this.energy = energy;
        }
    }
}
