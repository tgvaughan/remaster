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

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import remaster.AbstractReaction;
import remaster.ReactElement;

import java.util.*;

public abstract class CoalescentReactionBox {

    List<ReactElement> parents = new ArrayList<>();
    List<Multiset<ReactElement>> children = new ArrayList<>();

    public boolean generatesLeaves = false;

    public void processAndValidate(AbstractReaction reaction, Set<ReactElement> popElements) {

        Map<String, Integer> parentIDs = new HashMap<>();

        // Process products

        for (int i = 0; i < reaction.productList.size(); i++) {
            ReactElement el = reaction.productList.get(i);
            if (!popElements.contains(el))
                throw new IllegalArgumentException("Population '" + el.toString() + "' unknown.");

            parents.add(el);
            children.add(HashMultiset.create());
            String prodID = reaction.productIDList.get(i);
            if (prodID != null) {
                if (parentIDs.containsKey(prodID))
                    throw new IllegalArgumentException("Products cannot share an ID.");
                parentIDs.put(prodID, i);
            }
        }

        // Process reactants:

        for (int i = 0; i < reaction.reactantList.size(); i++) {
            ReactElement el = reaction.reactantList.get(i);
            if (!popElements.contains(el))
                throw new IllegalArgumentException("Population '" + el.toString() + "' unknown.");

            int parentIndex = 0;

            String reactID = reaction.reactantIDList.get(i);
            if (reactID != null) {
                if (!parentIDs.containsKey(reactID))
                    throw new IllegalArgumentException("The reaction ID '" + reactID +
                            "' is not associated with a product.");
                parentIndex = parentIDs.get(reactID);
            } else if (parents.stream().anyMatch(p -> p.name.equals(el.name))) {
                while (parentIndex < parents.size() && !parents.get(parentIndex).name.equals(el.name))
                    parentIndex += 1;
            }

            if (parentIndex < parents.size())
                children.get(parentIndex).add(el);
        }

        for (int i = 0; i < parents.size(); i++) {
            if (children.get(i).isEmpty()) {
                generatesLeaves = true;
                break;
            }
        }
    }
}
