package remaster;

import beast.core.BEASTObject;
import beast.core.Function;

import java.util.*;

public class TrajectoryState {

    public Map<String, Double[]> occupancies = new HashMap<>();
    public Map<String, Double[]> initialOccupancies = new HashMap<>();
    public Map<String, Double[]> finalOccupancies = new HashMap<>();

    public Set<String> samplePopNames = new HashSet<>();

    public TrajectoryState(List<Function> popFuncs, List<Function> samplePopFuncs)  {

        for (Function popFunc : popFuncs)
            initPop(popFunc);

        for (Function popFunc : samplePopFuncs)
            samplePopNames.add(initPop(popFunc));

    }

    private String initPop(Function popFunc) {
        String popName = ((BEASTObject)popFunc).getID().intern();

        Double[] popArray = new Double[popFunc.getDimension()];
        for (int i=0; i<popArray.length; i++)
            popArray[i] = popFunc.getArrayValue(i);
        initialOccupancies.put(popName, popArray);
        occupancies.put(popName, new Double[popArray.length]);
        finalOccupancies.put(popName, new Double[popArray.length]);

        return popName;
    }

    public Set<String> getPopNames() {
        return occupancies.keySet();
    }

    public boolean hasPopNamed(String string) {
        return occupancies.containsKey(string);
    }

    public boolean hasSamplePopNamed(String string) {
        return samplePopNames.contains(string);
    }

    public Double[] get(String popName) {
        return occupancies.get(popName);
    }

    public double get(ReactElement el) {
        return occupancies.get(el.name)[el.idx];
    }

    public boolean isValid() {
        for (Double[] popValues : occupancies.values())
            for (double val : popValues)
                if (val < 0.0)
                    return false;

        return true;
    }

    public void setFinal() {
        for (String popName : occupancies.keySet())
            System.arraycopy(occupancies.get(popName), 0,
                    finalOccupancies.get(popName), 0,
                    occupancies.get(popName).length);
    }

    public void resetToInitial() {
        for (String popName : occupancies.keySet())
            System.arraycopy(initialOccupancies.get(popName), 0,
                    occupancies.get(popName), 0,
                    occupancies.get(popName).length);

    }

    public void resetToFinal() {
        for (String popName : occupancies.keySet())
            System.arraycopy(finalOccupancies.get(popName), 0,
                    occupancies.get(popName), 0,
                    occupancies.get(popName).length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String popName : occupancies.keySet()) {
            sb.append(":").append(popName).append("=");
            Double[] values  = occupancies.get(popName);
            for (int i=0; i<values.length; i++) {
                if (i>0)
                    sb.append(",");
                sb.append(values[i]);
            }
        }

        return sb.toString();
    }

}
