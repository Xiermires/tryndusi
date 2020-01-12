package org.tryndusi.routing.sa;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.tryndusi.routing.sa.Annealing;
import org.tryndusi.routing.sa.Evaluator;
import org.tryndusi.routing.sa.Generator;
import org.tryndusi.routing.sa.Annealing.Result;

public class TestAnnealing {

    @Test
    public void testTravelSalesman() {
        final Generator<Point2D> generator = generator();

        final Annealing<Point2D> annealing = new Annealing<>(generator, evaluator(), 1e9, 1e-4);
        final Result<Point2D> result = annealing.simulate();
        System.out.println("Energy '" + result.energy + "'.");
        System.out.println("Solution '" + Arrays.asList(result.solution) + "'.");
    }

    // from http://www.math.uwaterloo.ca/tsp/world/countries.html
    // Djibouti - 38 Cities
    private Generator<Point2D> generator() {
        final List<Point2D> points = new ArrayList<>();
        points.add(new Point2D.Double(11003.611100, 42102.500000));
        points.add(new Point2D.Double(11108.611100, 42373.888900));
        points.add(new Point2D.Double(11133.333300, 42885.833300));
        points.add(new Point2D.Double(11155.833300, 42712.500000));
        points.add(new Point2D.Double(11183.333300, 42933.333300));
        points.add(new Point2D.Double(11297.500000, 42853.333300));
        points.add(new Point2D.Double(11310.277800, 42929.444400));
        points.add(new Point2D.Double(11416.666700, 42983.333300));
        points.add(new Point2D.Double(11423.888900, 43000.277800));
        points.add(new Point2D.Double(11438.333300, 42057.222200));
        points.add(new Point2D.Double(11461.111100, 43252.777800));
        points.add(new Point2D.Double(11485.555600, 43187.222200));
        points.add(new Point2D.Double(11503.055600, 42855.277800));
        points.add(new Point2D.Double(11511.388900, 42106.388900));
        points.add(new Point2D.Double(11522.222200, 42841.944400));
        points.add(new Point2D.Double(11569.444400, 43136.666700));
        points.add(new Point2D.Double(11583.333300, 43150.000000));
        points.add(new Point2D.Double(11595.000000, 43148.055600));
        points.add(new Point2D.Double(11600.000000, 43150.000000));
        points.add(new Point2D.Double(11690.555600, 42686.666700));
        points.add(new Point2D.Double(11715.833300, 41836.111100));
        points.add(new Point2D.Double(11751.111100, 42814.444400));
        points.add(new Point2D.Double(11770.277800, 42651.944400));
        points.add(new Point2D.Double(11785.277800, 42884.444400));
        points.add(new Point2D.Double(11822.777800, 42673.611100));
        points.add(new Point2D.Double(11846.944400, 42660.555600));
        points.add(new Point2D.Double(11963.055600, 43290.555600));
        points.add(new Point2D.Double(11973.055600, 43026.111100));
        points.add(new Point2D.Double(12058.333300, 42195.555600));
        points.add(new Point2D.Double(12149.444400, 42477.500000));
        points.add(new Point2D.Double(12286.944400, 43355.555600));
        points.add(new Point2D.Double(12300.000000, 42433.333300));
        points.add(new Point2D.Double(12355.833300, 43156.388900));
        points.add(new Point2D.Double(12363.333300, 43189.166700));
        points.add(new Point2D.Double(12372.777800, 42711.388900));
        points.add(new Point2D.Double(12386.666700, 43334.722200));
        points.add(new Point2D.Double(12421.666700, 42895.555600));
        points.add(new Point2D.Double(12645.000000, 42973.333300));
        return () -> points.toArray(new Point2D[0]);
    }

    private Evaluator<Point2D> evaluator() {
        return (candidate) -> {
            if (candidate.length == 1) {
                return -1;
            } else if (candidate.length < 2) {
                return Math.hypot(Math.abs(candidate[0].getX() - candidate[1].getX()),
                        Math.abs(candidate[0].getY() - candidate[1].getY()));
            } else {
                int i = 0;
                double evaluation = 0;
                for (; i < candidate.length - 1; i++) {
                    evaluation += //
                            Math.hypot(Math.abs(candidate[i].getX() - candidate[i + 1].getX()),
                                    Math.abs(candidate[i].getY() - candidate[i + 1].getY()));
                }
                evaluation += Math.hypot(Math.abs(candidate[i].getX() - candidate[0].getX()),
                        Math.abs(candidate[i].getY() - candidate[0].getY()));
                return evaluation;
            }
        };
    }
}
