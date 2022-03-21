package remaster;

import java.util.Objects;

/**
 * Class for reactants and products of reaction
 */
public class ReactElement {
    String name;
    int idx;

    public ReactElement(String name, int idx) {
        this.name = name.intern();
        this.idx = idx;
    }

    @Override
    public String toString() {
        return "ReactElement{" +
                "name='" + name + '\'' +
                ", idx=" + idx +
                '}';
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
