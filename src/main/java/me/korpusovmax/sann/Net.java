
/*java Simple Artificial Neural Network library

by @maxcrocus (telegram)

suggestions and support are welcome*/

package sann;

import me.korpusovmax.sann.Layer;

import me.korpusovmax.sann.Weights;

import java.util.ArrayList;

public class Net {

	public ArrayList<Layer> layers;

	public ArrayList<Weights> weights;

	public double[][] trainingInput, trainingOutput;

	

	public Net(int inputLength, int hiddenLayers, int defaultLength, int outputLength) {

		this.layers = new ArrayList<>();

		this.layers.add(new Layer(inputLength));

		

		for (int i = 0; i < hiddenLayers; i++) {

			this.layers.add(new Layer(defaultLength));

		}

		

		this.layers.add(new Layer(outputLength));

	}

	

	public Net() {

		this.layers = new ArrayList<>();

	}

	

	//setup

	public void addLayers(int count, int len) {

		for (int i = 0; i < count; i++) {

			layers.add(new Layer(len));

		}

	}

	

	public void addRecurrentLayers(int count, int len) {

		for (int i = 0; i < count; i++) {

			layers.add(new Layer(len, "recurrent"));

		}

	}

	//weights

	public void setup() {

		weights = new ArrayList<>();

		for(int i = 0; i < layers.size()-1; i++) {

			weights.add(new Weights(layers.get(i), layers.get(i+1)));

		} 

	}

	

	//feed forward from one layer to other

	public Layer forward(int layerId) {

		int id = layerId-1; //previous layer

		Weights weights = this.weights.get(id);

		

		return weights.forward();

	}

	//one by one layers feed forward

	public Layer forwardAll() {

		Layer ret = layers.get(layers.size()-1);

		for(int i = 1; i < weights.size()+1; i++) {

			ret = forward(i);

		}

		return ret;

	}

	//back propogation for all layers, finding errors and setup new weights

	public void backPropogation(double[] target, double k) {

		weights.get(weights.size()-1).fixError(target);

		

		for(int i = weights.size()-1; i > 0; i--) {

			weights.get(i).findError();

		}

		for(int i = weights.size()-1; i > - 1; i--) {

			weights.get(i).backward(k);

		}

	} 

	

	//training

	public void setTrainingData(double[][] inputData, double[][] outputData) {

		trainingInput = inputData;

		trainingOutput = outputData;

	} 

	

	public void train(double k, int iterations) {

		int iteration = 0;

		while (iteration < iterations) {

			layers.get(0).layer = trainingInput[iteration%trainingInput.length];

			

			forwardAll();

			

			backPropogation(trainingOutput[iteration%trainingOutput.length], k);

			

			if (trainingListener != null) { 

				trainingListener.onTraining(iteration, layers.get(layers.size()-1).getError(trainingOutput[iteration%trainingOutput.length]));

			}

			iteration++;

		}

		

		if (trainingListener != null) { 

				trainingListener.onTrainingFinished(iteration, layers.get(layers.size()-1).getError(trainingOutput[iteration%trainingOutput.length]));

		}

	}

	//listeners

	private OnTrainingListener trainingListener;

	

	public void setOnTrainingListener(OnTrainingListener listener) {

		trainingListener = listener;

	}

	

	public interface OnTrainingListener {

		void onTraining(int iteration, double error);

		void onTrainingFinished(int iteration, double error);

	}

}
