/*
 * Copyright (c) 2023 ETH Zurich
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

import java.util.Objects;

/**
 * Class for reactants and products of reaction
 */
public class ReactElement {
    public String name;
    public int idx;
    public boolean isScalar;

    public ReactElement(String name, int idx, boolean isScalar) {
        this.name = name.intern();
        this.idx = idx;
        this.isScalar = isScalar;
    }

    public ReactElement(String name, int idx) {
        this(name, idx, false);
    }

    @Override
    public String toString() {
        return isScalar ? name : name + "{" + idx + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactElement that = (ReactElement) o;
        return idx == that.idx && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, idx);
    }
}
