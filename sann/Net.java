
/*java Simple Artificial Neural Network library
by @maxcrocus (telegram)
suggestions and support are welcome*/

package sann;

import sann.Layer;
import sann.Weights;
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
			this.layers.add(new Layer(len));
		}
	}
	
	public void addRecurrentLayers(int count, int len) {
		for (int i = 0; i < count; i++) {
			this.layers.add(new Layer(len, "recurrent"));
		}
	}
	//weights
	public void setup() {
		this.weights = new ArrayList<>();
		for(int i = 0; i < this.layers.size()-1; i++) {
			this.weights.add(new Weights(this.layers.get(i), this.layers.get(i+1)));
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
		Layer ret = this.layers.get(this.layers.size()-1);
		for(int i = 1; i < this.weights.size()+1; i++) {
			ret = this.forward(i);
		}
		return ret;
	}
	//back propogation for all layers, finding errors and setup new weights
	public void backPropogation(double[] target, double k) {
		this.weights.get(this.weights.size()-1).fixError(target);
		
		for(int i = this.weights.size()-1; i > 0; i--) {
			this.weights.get(i).findError();
		}
		for(int i = this.weights.size()-1; i > - 1; i--) {
			this.weights.get(i).backward(k);
		}
	} 
	
	//training
	public void setTrainingData(double[][] inputData, double[][] outputData) {
		this.trainingInput = inputData;
		this.trainingOutput = outputData;
	} 
	
	public void train(double k, int iterations) {
		int iteration = 0;
		while (iteration < iterations) {
			this.layers.get(0).layer = this.trainingInput[iteration%this.trainingInput.length];
			
			this.forwardAll();
			
			this.backPropogation(this.trainingOutput[iteration%this.trainingOutput.length], k);
			
			if (null != this.trainingListener) { 
				this.trainingListener.onTraining(iteration, 0.0d);
			}
			iteration++;
		}
		
		if (null != this.trainingListener) { 
				this.trainingListener.onTrainingFinished(iteration, 0.0d);
		}
	}
	//listeners
	private OnTrainingListener trainingListener;
	
	public void setOnTrainingListener(OnTrainingListener listener) {
		this.trainingListener = listener;
	}
	
	public interface OnTrainingListener {
		void onTraining(int iteration, double error);
		void onTrainingFinished(int iteration, double error);
	}
}
/*java Simple Artificial Neural Network library
by @maxcrocus (telegram)
suggestions and support are welcome*/

package sann;

import sann.Layer;
import sann.Weights;
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
			this.layers.add(new Layer(len));
		}
	}
	
	public void addRecurrentLayers(int count, int len) {
		for (int i = 0; i < count; i++) {
			this.layers.add(new Layer(len, "recurrent"));
		}
	}
	//weights
	public void setup() {
		this.weights = new ArrayList<>();
		for(int i = 0; i < this.layers.size()-1; i++) {
			this.weights.add(new Weights(this.layers.get(i), this.layers.get(i+1)));
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
		Layer ret = this.layers.get(this.layers.size()-1);
		for(int i = 1; i < this.weights.size()+1; i++) {
			ret = this.forward(i);
		}
		return ret;
	}
	//back propogation for all layers, finding errors and setup new weights
	public void backPropogation(double[] target, double k) {
		this.weights.get(this.weights.size()-1).fixError(target);
		
		for(int i = this.weights.size()-1; i > 0; i--) {
			this.weights.get(i).findError();
		}
		for(int i = this.weights.size()-1; i > - 1; i--) {
			this.weights.get(i).backward(k);
		}
	} 
	
	//training
	public void setTrainingData(double[][] inputData, double[][] outputData) {
		this.trainingInput = inputData;
		this.trainingOutput = outputData;
	} 
	
	public void train(double k, int iterations) {
		int iteration = 0;
		while (iteration < iterations) {
			this.layers.get(0).layer = this.trainingInput[iteration%this.trainingInput.length];
			
			this.forwardAll();
			
			this.backPropogation(this.trainingOutput[iteration%this.trainingOutput.length], k);
			
			if (null != this.trainingListener) { 
				this.trainingListener.onTraining(iteration, this.layers.get(this.layers.size()-1).getError(this.trainingOutput[iteration%this.trainingOutput.length]));
			}
			iteration++;
		}
		
		if (null != this.trainingListener) { 
				this.trainingListener.onTrainingFinished(iteration, this.layers.get(this.layers.size()-1).getError(this.trainingOutput[iteration%this.trainingOutput.length]));
		}
	}
	//listeners
	private OnTrainingListener trainingListener;
	
	public void setOnTrainingListener(OnTrainingListener listener) {
		this.trainingListener = listener;
	}
	
	public interface OnTrainingListener {
		void onTraining(int iteration, double error);
		void onTrainingFinished(int iteration, double error);
	}
}
