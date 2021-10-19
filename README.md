# SANN
Simple Artificial Neural Network java library  
WIP

### Creating neural network
#### Variant 1
```java
//create new net (input, hidden, default len, output)
Net net = new Net(3, 2, 2, 1);

//initialize weights and fill weights randomly
net.setup();
```

#### Variant 2 (same structure)
```java
//create net
Net net = new Net();
//add layers (layers, neurons in layers)
net.addLayers(1, 3);
net.addLayers(2, 2);
net.addLayers(1, 1);

//initialize weights and fill weights randomly
net.setup();
```

### Training
```java
double[][] input = {{1.0d, 0.0d, 1.0d}, {0.0d, 1.0d, 0.0d}, {0.0d, 0.0d, 1.0d}, {1.0d, 0.0d, 1.0d}};

double[][] output = {{1.0d}, {0.0d}, {0.0d}, {1.0d}};
//setting up target values for training
net.setTrainingData(input, output);
//start training (k, iterations)
net.train(0.2, 10000);
```

### Listeners
```java
net.setOnTrainingListener(new Net.OnTrainingListener() {
	@Override
	public void onTraining(int iteration, double error) {
		//doingSomething();
	}
	@Override
	public void onTrainingFinished(int iteration, double error) {
		//doingSomething();
	}
});
```
## Implementation
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
	implementation 'com.github.y9neon:simple-ann:$version' // use version from releases or use '-SNAPSHOT'
}
```
