package remaster.reactionboxes;

import beast.base.util.Randomizer;
import org.junit.Assert;
import org.junit.Test;
import remaster.Lineage;
import remaster.ReactElement;
import remaster.Reaction;

import java.util.*;

public class ContinuousCoalescentReactionBoxTest {

    private static final double CURRENT_TIME = 0.0;
    private static final double INTERVAL_END = 1.0;
    private static final double PER_PAIR_RATE = 0.05;
    private static final int DRAW_COUNT = 20000;

    private ContinuousCoalescentReactionBox createTwoLineageBox() {
        Reaction reaction = new Reaction();
        reaction.initByName("value", "2pop -> pop",
                "rate", PER_PAIR_RATE + " " + PER_PAIR_RATE,
                "changeTimes", Double.toString(INTERVAL_END));

        Set<ReactElement> popElements = new HashSet<>();
        popElements.add(new ReactElement("pop", 0, true));

        return new ContinuousCoalescentReactionBox(reaction, popElements);
    }

    private Map<ReactElement, List<Lineage>> createTwoLineageState() {
        ReactElement popEl = new ReactElement("pop", 0, true);
        List<Lineage> lineageList = new ArrayList<>();
        lineageList.add(new Lineage(popEl, CURRENT_TIME));
        lineageList.add(new Lineage(popEl, CURRENT_TIME));

        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();
        lineages.put(popEl, lineageList);

        return lineages;
    }

    private static double legacySampler(double u) {
        double t = CURRENT_TIME;
        double propensity = PER_PAIR_RATE;
        double dt = -Math.log(u) / propensity;
        double intervalEnd = INTERVAL_END;

        while (t + dt > intervalEnd) {
            u -= 1.0 - Math.exp(-(intervalEnd - t) * propensity);
            t = intervalEnd;
            intervalEnd = Double.POSITIVE_INFINITY;
            dt = -Math.log(u) / propensity;
        }

        return t + dt;
    }

    @Test
    public void fixedSamplerProducesFiniteTimesAndExpectedMean() {
        Randomizer.setSeed(53);

        ContinuousCoalescentReactionBox box = createTwoLineageBox();
        Map<ReactElement, List<Lineage>> lineages = createTwoLineageState();

        double sum = 0.0;
        for (int i = 0; i < DRAW_COUNT; i++) {
            double t = box.getNextReactionTime(CURRENT_TIME, lineages);
            Assert.assertTrue("reaction time must be finite", Double.isFinite(t));
            Assert.assertTrue("reaction time must be >= current time", t >= CURRENT_TIME);
            sum += t - CURRENT_TIME;
        }

        double sampleMean = sum / DRAW_COUNT;
        double expectedMean = 1.0 / PER_PAIR_RATE;
        Assert.assertEquals(expectedMean, sampleMean, 0.7);
    }

    @Test
    public void legacyFormulaCanProduceNaNUnderIntervalCrossing() {
        Randomizer.setSeed(53);

        int nanCount = 0;
        for (int i = 0; i < DRAW_COUNT; i++) {
            double t = legacySampler(Randomizer.nextDouble());
            if (Double.isNaN(t))
                nanCount += 1;
        }

        Assert.assertTrue("legacy interval update should produce NaN draws", nanCount > 0);
    }
}
