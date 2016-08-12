import java.util.ArrayList;
import java.util.List;

/**
 * Created by FuBaR on 8/12/2016.
 */
public class Layer {
    List<Neuron> neurons;
    private double bias = -1;
    private double biasWeight;

    public Layer(int layerSize, int inputSize) {
        neurons = new ArrayList<>();
        biasWeight = 1;
        for (int i = 0; i < layerSize; i++) {
            Neuron newNeuron = new Neuron(inputSize);
            neurons.add(newNeuron);
        }
    }

    public double[] getLayerOutput(double[] inputs) {
        double[] outputs = new double[neurons.size() + 1];
        int i;
        for (i = 0; i < neurons.size(); i++) {
            outputs[i] = neurons.get(i).getOutput(inputs);
        }
        outputs[i + 1] = bias * biasWeight;
        return outputs;
    }
}
