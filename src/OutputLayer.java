/**
 * Created by FuBaR on 8/12/2016.
 */
public class OutputLayer {
    OutputNeuron outputNeuron;

    public OutputLayer(int inputSize) {
        outputNeuron = new OutputNeuron(inputSize);
    }

    public double train(double[] inputs, double target) {
        return outputNeuron.trainAndGetError(inputs, target);
    }

    public double getLayerOutput(double[] inputs) {
        double output = outputNeuron.getOutput(inputs);
        return output;
    }
}
