/**
 * Created by FuBaR on 8/12/2016.
 */
public class OutputNeuron extends Neuron {
    public OutputNeuron(int inputSize) {
        super(inputSize);
    }

    public double trainAndGetError(double[] inputs, double target) {
        double outputError = -1 * (target - getOutput(inputs)) * derivative(inputs);
        updateWeights(outputError);
        return outputError;
    }


    private void updateWeights(double error) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = weights[i] + delta(error, i) + momentum * previousDeltas[i];
            previousDeltas[i] = delta(error, i);
        }
    }
}
