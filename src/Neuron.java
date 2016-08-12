/**
 * Created by FuBaR on 8/12/2016.
 */
public class Neuron {
    double[] inputs;
    double[] weights;
    double learningRate = 1;

    public Neuron(int inputSize) {
        initializeNeuron(inputSize);
    }

    private void initializeNeuron(int inputSize) {
        //inputs = new double[inputSize];
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
        this.inputs = inputs;
        for (int i = 0; i < inputs.length; i++) {
            net += inputs[i] * weights[i];
        }
        return sigmoidFunction(net);
    }

    private double net(double[] inputs) {
        double net = 0;
        this.inputs = inputs;
        for (int i = 0; i < inputs.length; i++) {
            net += inputs[i] * weights[i];
        }
        return net;
    }

    private double sigmoidFunction(double net) {
        double sigmoid = 1 / (1 + Math.exp(-1 * (net - inputs[inputs.length - 1])));
        return sigmoid;
    }

    public double derivative(double[] inputs) {
        double net = net(inputs);
        return sigmoidFunction(net) * (1 - sigmoidFunction(net));
    }

    public void train(double[] inputs, double outputError) {
        this.inputs = inputs;
        double error = 0;
        for (int i = 0; i < weights.length; i++) {
            error += outputError * weights[i] * derivative(inputs);
        }
        for (int i = 0; i < weights.length; i++) {
            weights[i] = weights[i] - learningRate * outputError * inputs[i];
        }
    }
}
