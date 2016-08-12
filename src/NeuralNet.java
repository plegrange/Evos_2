/**
 * Created by FuBaR on 8/12/2016.
 */
public class NeuralNet {
    private Layer hiddenLayer, outputLayer;

    private int inputSize, outputSize, hiddenLayerSize;

    public NeuralNet(int inputSize, int outputSize, int hiddenLayerSize) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        hiddenLayer = new Layer(hiddenLayerSize, inputSize);
        outputLayer = new Layer(outputSize, hiddenLayerSize + 1);
    }

    public double[] getOutput(double[] inputs) {
        //double[] output = new double[outputSize];
        double[] hiddenOutput = hiddenLayer.getLayerOutput(inputs);
        double[] output = outputLayer.getLayerOutput(hiddenOutput);
        return output;
    }
}
