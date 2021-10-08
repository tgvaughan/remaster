package remaster.model;

import beast.core.BEASTObject;
import beast.core.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemState extends BEASTObject {

    public Input<List<Population>> populationsInput = new Input<>("population", "Population", new ArrayList<>());

    Map<String, Population> populations;

    @Override
    public void initAndValidate() {
        populations = new HashMap<>();
        for (Population population : populationsInput.get()) {
            population.initAndValidate();
            populations.put(population.name, population);
        }
    }
}
