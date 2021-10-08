package remaster.model;

import beast.core.BEASTObject;
import beast.core.Function;
import beast.core.Input;

public class Population extends BEASTObject {

    public Input<String> nameInput = new Input<>("name", "Population name", Input.Validate.REQUIRED);
    public Input<Function> initialValuesInput = new Input<>("initialValues", "Initial values", Input.Validate.REQUIRED);

    public String name;
    public double[] values;

    @Override
    public void initAndValidate() {
        name = nameInput.get();
        values = initialValuesInput.get().getDoubleValues();
    }
}
