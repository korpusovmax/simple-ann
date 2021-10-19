package sann;

import sann.Layer;
import java.util.Random;

public class Weights {
	//left layer and right layer
	public Layer x, y;
	public double[][] weights;
	
	public Weights(Layer x, Layer y) {
		this.x = x;
		this.y = y;
		this.weights = new double[x.layer.length][y.layer.length];
		
		//setup random values
		Random r = new Random();
		for(int i = 0; i < x.layer.length; i++){
			for(int j = 0; j < y.layer.length; j++){
				this.weights[i][j] = -0.5 + r.nextDouble() * (0.5 - -0.5);
			} 
		} 
	}
	//feed forward
	public Layer forward() {
		Layer left = this.x;
		Layer right = this.y;
		
		for(int y = 0; y < right.layer.length; y++) {
			for(int x = 0; x < left.layer.length; x++) {
				right.layer[y] = right.layer[y] + left.layer[x] * this.weights[x][y];
			}
			right.layer[y] = 1/( 1 + Math.exp( -1 * right.layer[y]));
		}
		return right;
	}
	//fix output layer error
	public void fixError(double[] target) {
		for(int i = 0; i < target.length; i++) {
			this.y.errors[i] = target[i] - this.y.layer[i];
		} 
	} 
	//finding error for other layers
	public void findError() {
		Layer left = this.x;
		Layer right = this.y;
		for(int x = 0; x < this.weights.length; x++) {
			left.errors[x] = 0;
			for(int y = 0; y < this.weights[0].length; y++) {
				left.errors[x] = left.errors[x] + this.weights[x][y] * right.errors[y];
			} 
		} 
	}
	//backward weights update
	public void backward(double k) {
		Layer left = this.x;
		Layer right = this.y;
		for(int y = 0; y < this.weights[0].length; y++) {
			for(int x = 0; x < this.weights.length; x++) {
				this.weights[x][y] = this.weights[x][y] + k * right.errors[y] * left.layer[x] * right.layer[y] * (1 - right.layer[y]);
			} 
		} 
	} 
}
