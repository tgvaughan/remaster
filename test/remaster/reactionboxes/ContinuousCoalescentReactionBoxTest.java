/*
 * Copyright (c) 2026 ETH Zürich
 *
 * This file is part of remaster.
 *
 * remaster is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * remaster is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with remaster. If not, see <https://www.gnu.org/licenses/>.
 */

package remaster.reactionboxes;

import beast.base.util.DiscreteStatistics;
import beast.base.util.Randomizer;
import org.junit.Assert;
import org.junit.Test;
import remaster.Lineage;
import remaster.LineageFactory;
import remaster.ReactElement;
import remaster.Reaction;

import java.util.*;
import java.util.stream.IntStream;

public class ContinuousCoalescentReactionBoxTest {

    @Test
    public void testFixedRate() {
        Randomizer.setSeed(53);

        ReactElement X = new ReactElement("X", 0, true);
        ReactElement Y = new ReactElement("Y", 0, true);

        Set<ReactElement> popElements = new HashSet<>();
        popElements.add(X);
        popElements.add(Y);

        Reaction migration = new Reaction();
        migration.initByName("value", "X -> Y",
                "rate", "2.0");

        ContinuousCoalescentReactionBox reactionBox =
                new ContinuousCoalescentReactionBox(migration, popElements);

        LineageFactory lineageFactory = new LineageFactory();

        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();
        lineages.put(X, new ArrayList<>());

        lineages.get(X).add(lineageFactory.createSample(X, null, 0.0));
        lineages.get(X).add(lineageFactory.createSample(X, null, 0.0));

        double[] nextReactionTimes = IntStream.range(0, 100000)
                .mapToDouble(i -> {
                    return reactionBox.getNextReactionTime(0, lineages);
                })
                .toArray();

        Assert.assertEquals(0.25,DiscreteStatistics.mean(nextReactionTimes), 1e-3);
    }

    @Test
    public void testRateShift() {
        Randomizer.setSeed(42);

        ReactElement X = new ReactElement("X", 0, true);
        ReactElement Y = new ReactElement("Y", 0, true);

        Set<ReactElement> popElements = new HashSet<>();
        popElements.add(X);
        popElements.add(Y);

        Reaction migration = new Reaction();
        migration.initByName("value", "X -> Y",
                "rate", "0.1 0.2",
                "changeTimes", "1.0");

        ContinuousCoalescentReactionBox reactionBox =
                new ContinuousCoalescentReactionBox(migration, popElements);

        LineageFactory lineageFactory = new LineageFactory();

        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();
        lineages.put(X, new ArrayList<>());

        lineages.get(X).add(lineageFactory.createSample(X, null, 0.0));
        lineages.get(X).add(lineageFactory.createSample(X, null, 0.0));

        double[] nextReactionTimes = IntStream.range(0, 1000000)
                .mapToDouble(i -> {
                    return reactionBox.getNextReactionTime(0, lineages);
                })
                .toArray();

        double lambda1 = 0.1*2;
        double lambda2 = 0.2*2;
        double T = 1.0;
        double trueMean = 1/lambda1 + Math.exp(-lambda1*T)*(1/lambda2 - 1/lambda1);

        Assert.assertEquals(trueMean, DiscreteStatistics.mean(nextReactionTimes), 1e-3);
    }
}
