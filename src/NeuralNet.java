/**
 * Created by FuBaR on 8/12/2016.
 */
public class NeuralNet {
    private HiddenLayer hiddenLayer;
    private OutputLayer outputLayer;

    private int inputSize, outputSize, hiddenLayerSize;

    public NeuralNet(int inputSize, int outputSize, int hiddenLayerSize) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        hiddenLayer = new HiddenLayer(hiddenLayerSize, inputSize);
        outputLayer = new OutputLayer(hiddenLayerSize + 1);
    }

    public double getOutput(double[] inputs) {
        double[] hiddenLayerOutput = hiddenLayer.getLayerOutput(inputs);
        return outputLayer.getLayerOutput(hiddenLayerOutput);
    }

    public void trainNeuralNetwork(double[] inputs, double target) {
        double[] hiddenLayerOutput = hiddenLayer.getLayerOutput(inputs);
        double outputError = outputLayer.train(hiddenLayerOutput, target);
        hiddenLayer.train(inputs, outputError);
    }

}
