/*
 * Copyright (c) 2023 Tim Vaughan
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

public class PunctualReactionTest {

    @Test
    public void test() {

        PunctualReaction reaction = new PunctualReaction();
        reaction.initByName("value", "I[0] -> sample",
                "n", "10 20",
                "times", "8 9 10");

        assertEquals(1, reaction.reactants.size());
        assertTrue(reaction.reactants.contains(new ReactElement("I", 0)));

        assertEquals(1, reaction.products.size());
        assertTrue(reaction.products.contains(new ReactElement("sample", 0)));

        reaction.resetInterval();
        assertEquals(8.0, reaction.getIntervalEndTime(), 1e-14);
        assertEquals(10, reaction.getNextN(), 1e-14);

        reaction.incrementInterval();
        assertEquals(9.0, reaction.getIntervalEndTime(), 1e-14);
        assertEquals(20, reaction.getNextN(), 1e-14);

        reaction.incrementInterval();
        assertEquals(10.0, reaction.getIntervalEndTime(), 1e-14);
        assertEquals(10, reaction.getNextN(), 1e-14);

        reaction.incrementInterval();
        assertEquals(Double.POSITIVE_INFINITY, reaction.getIntervalEndTime(), 1e-14);
    }
}
