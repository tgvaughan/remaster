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

package remaster.reactionboxes;

import beast.base.util.Randomizer;
import remaster.Lineage;
import remaster.LineageFactory;
import remaster.PunctualReaction;
import remaster.ReactElement;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PunctualCoalescentReactionBox extends CoalescentReactionBox {

    PunctualReaction reaction;

    public PunctualCoalescentReactionBox(PunctualReaction reaction, Set<ReactElement> popElements) {
        this.reaction = reaction;
        processAndValidate(reaction, popElements);

        if (reaction.isPReaction())
            throw new IllegalArgumentException("Probabilistic punctual " +
                    "reactions not supported for coalescent simulations.");
    }

    public double getReactionTime() {
        return reaction.getIntervalEndTime();
    }

    public void applyReaction(Map<ReactElement, List<Lineage>> lineages,
                              LineageFactory lineageFactory) {

        double reactionTime = reaction.getIntervalEndTime();

        for (int count=0; count<reaction.getNextN(); count++) {
            for (int i = 0; i < children.size(); i++) {
                ReactElement parentEl = parents.get(i);

                Lineage parent = children.get(i).isEmpty()
                        ? lineageFactory.createSample(parentEl, reactionTime)
                        : lineageFactory.createInternal(parentEl, reactionTime);

                for (ReactElement childEl : children.get(i)) {
                    List<Lineage> elementLineages = lineages.get(childEl);
                    parent.addChild(elementLineages.remove(Randomizer.nextInt(elementLineages.size())));
                }

                lineages.get(parentEl).add(parent);
            }
        }

        reaction.incrementInterval();
    }
}
