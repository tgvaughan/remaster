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

import beast.base.evolution.tree.Node;
import org.junit.platform.console.shadow.picocli.CommandLine;

/**
 * A class of objects representing "typed" lineages.
 * Additionally, the constructor records the type in the metaDataString field,
 * for logging purposes.
 */
public class Lineage extends Node {
    public ReactElement reactElement;
    public ReactElement sampElement;

    public Lineage(ReactElement reactElement, double time) {
        this(reactElement, null, time);
    }

    public Lineage(ReactElement reactElement, ReactElement sampElement, double time) {
        super();
        this.reactElement = reactElement;
        this.sampElement = sampElement;
        this.height = time;

        metaData.put("type", reactElement);
        if (sampElement != null)
            metaData.put("samp", sampElement);

        metaDataString = "type=\"" + reactElement + "\","
                + (sampElement != null ? "samp=\"" + sampElement + "\"," : "")
                + "time=" + time;
    }
}
