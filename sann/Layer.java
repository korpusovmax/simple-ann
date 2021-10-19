package sann;

public class Layer {
	//layer type (default/recurrent)
	public String type;
	public double[] layer, errors;
	
	public Layer(int count) {
		this.type = "default";
		this.layer = new double[count];
		this.errors = new double[count];
		
		for(int i = 0; i < 0; i++) {
			this.layer[i] = 0;
		} 
	}
	public Layer(int count, String type) {
		this.type = type;
		this.layer = new double[count];
		this.errors = new double[count];
		
		for(int i = 0; i < 0; i++) {
			this.layer[i] = 0;
		}
	} 
	
	//setup new neurons values
	public void setNeurons(double[] n) {
		this.layer = n;
	} 
	
	//get first neuron value
	public double getFirst() {
		return this.layer[0];
	}
	//and last
	public double getLast() {
		return this.layer[this.layer.length-1];
	} 
	//get id of the most active neuron 
	public int getOneHot() {
		int ret = 0;
		
		for(int i = 1; i < this.layer.length; i++) {
			if (this.layer[i] > this.layer[ret]) {
				ret = i;
			} 
		}
		return ret;
	} 
} 
