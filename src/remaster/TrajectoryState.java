package remaster;

import beast.core.BEASTObject;
import beast.core.Function;

import java.util.*;

public class TrajectoryState {

    public double[] occupancies, initialOccupancies, finalOccupancies;

    public Map<String, Integer> popIndices = new HashMap<>();
    public Map<String, Integer> popDims = new HashMap<>();

    public Set<String> samplePopNames = new HashSet<>();

    public TrajectoryState(List<Function> popFuncs, List<Function> samplePopFuncs)  {


        List<Function> allPops = new ArrayList<>(popFuncs);
        allPops.addAll(samplePopFuncs);

        int nextIdx = 0;

        for (Function popFunc : allPops) {
            String popName = ((BEASTObject)popFunc).getID().intern();
            popIndices.put(popName, nextIdx);
            popDims.put(popName, popFunc.getDimension());
            nextIdx += popFunc.getDimension();
        }

        occupancies = new double[nextIdx];
        initialOccupancies = new double[nextIdx];
        finalOccupancies = new double[nextIdx];

        for (Function popFunc : allPops) {
            String popName = ((BEASTObject)popFunc).getID().intern();
            System.arraycopy(popFunc.getDoubleValues(), 0, initialOccupancies,
                    popIndices.get(popName), popFunc.getDimension());
        }

        System.arraycopy(initialOccupancies, 0, occupancies, 0,
                initialOccupancies.length);

        for (Function popFunc : samplePopFuncs) {
            String popName = ((BEASTObject) popFunc).getID().intern();
            samplePopNames.add(popName);
        }
    }

    public Set<String> getPopNames() {
        return popIndices.keySet();
    }

    public boolean hasPopNamed(String string) {
        return popIndices.containsKey(string);
    }

    public boolean hasSamplePopNamed(String string) {
        return samplePopNames.contains(string);
    }

    public double get(String popName, int idx) {
        return occupancies[popIndices.get(popName) + idx];
    }

    public double get(ReactElement el) {
        return occupancies[popIndices.get(el.name) + el.idx];
    }

    public int getOffset(ReactElement el) {
        return popIndices.get(el.name) + el.idx;
    }

    public Double[] getArray(String popName) {
        Double[] res = new Double[popDims.get(popName)];

        int offset = popIndices.get(popName);
        for (int i=0; i<res.length; i++)
            res[i] = occupancies[offset+i];

        return res;
    }

    public int getTotalSubpopCount() {
        return occupancies.length;
    }

    public void increment(String popName, int idx, double amount) {
        occupancies[popIndices.get(popName)+idx] += amount;
    }

    public boolean isValid() {
        for (Double val : occupancies) {
            if (val < 0.0)
                return false;
        }

        return true;
    }

    public void setFinal() {
        System.arraycopy(occupancies, 0, finalOccupancies, 0,
                occupancies.length);
    }

    public void resetToInitial() {
        System.arraycopy(initialOccupancies, 0, occupancies, 0,
                occupancies.length);
    }

    public void resetToFinal() {
        System.arraycopy(finalOccupancies, 0, occupancies, 0,
                occupancies.length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String popName : popIndices.keySet()) {
            sb.append(":").append(popName).append("=");
            int idx  = popIndices.get(popName);
            for (int i=idx; i<idx+popDims.get(popName); i++) {
                if (i>0)
                    sb.append(",");
                sb.append(occupancies[i]);
            }
        }

        return sb.toString();
    }

}
