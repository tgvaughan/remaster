/*
 * Copyright (c) 2023-2026 ETH Zürich
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

package remaster;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReactionTest {

    @Test
    public void test() {

        Reaction reaction = new Reaction();
        reaction.initByName("value", "S[1] + I[0] -> I[0] + I[1]",
                "rate", "1 2 3",
                "changeTimes", "5 10");

        assertEquals(2, reaction.reactants.size());
        assertTrue(reaction.reactants.contains(new ReactElement("S", 1)));
        assertTrue(reaction.reactants.contains(new ReactElement("I", 0)));

        assertEquals(2, reaction.products.size());
        assertTrue(reaction.products.contains(new ReactElement("I", 0)));
        assertTrue(reaction.products.contains(new ReactElement("I", 1)));

        reaction.resetInterval();
        assertEquals(5.0, reaction.getIntervalEndTime(), 1e-14);
        assertEquals(1.0, reaction.getIntervalRate(), 1e-14);

        reaction.incrementInterval();
        assertEquals(10.0, reaction.getIntervalEndTime(), 1e-14);
        assertEquals(2.0, reaction.getIntervalRate(), 1e-14);

        reaction.incrementInterval();
        assertEquals(Double.POSITIVE_INFINITY, reaction.getIntervalEndTime(), 1e-14);
        assertEquals(3.0, reaction.getIntervalRate(), 1e-14);
    }
}
