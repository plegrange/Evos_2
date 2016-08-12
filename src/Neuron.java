/**
 * Created by FuBaR on 8/12/2016.
 */
public class Neuron {
    private double[] inputs;
    private double[] weights;

    public Neuron(int inputSize) {
        initializeNeuron(inputSize);
    }

    private void initializeNeuron(int inputSize) {
        inputs = new double[inputSize];
        initializeWeights(inputSize);
    }

    private void initializeWeights(int inputSize) {
        weights = new double[inputSize];
        for (int i = 0; i < inputSize; i++) {
            weights[i] = 1;
        }
    }

    public double getOutput(double[] inputs) {
        double net = 0;
        for (int i = 0; i < inputs.length; i++) {
            net += inputs[i] * weights[i];
        }
        return net;
    }
}
