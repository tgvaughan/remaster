package remaster;

import beast.core.BEASTObject;
import beast.core.Function;

import java.util.*;

public class TrajectoryState {

    public Map<String, Double[]> occupancies = new HashMap<>();
    public Map<String, Double[]> initialOccupancies = new HashMap<>();

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

        return popName;
    }

    public Set<String> getPopNames() {
        return occupancies.keySet();
    }

    public boolean hasPopNamed(String string) {
        return occupancies.containsKey(string);
    }

    public Set<String> getSamplePopNames() {
        return samplePopNames;
    }

    public boolean hasSamplePopNamed(String string) {
        return samplePopNames.contains(string);
    }

    public Double[] get(String popName) {
        return occupancies.get(popName);
    }


    public void reset() {
        for (String popName : occupancies.keySet())
            System.arraycopy(initialOccupancies.get(popName),
                    0, occupancies.get(popName),
                    0, occupancies.get(popName).length);

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
