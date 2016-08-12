import java.util.ArrayList;
import java.util.List;

/**
 * Created by FuBaR on 8/12/2016.
 */
public class Layer {
    List<Neuron> neurons;
    private int bias;

    public Layer(int layerSize, int inputSize) {
        neurons = new ArrayList<>();
        for (int i = 0; i < layerSize; i++) {
            Neuron newNeuron = new Neuron(inputSize);
            neurons.add(newNeuron);
        }
    }
}
