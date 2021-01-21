/**
 * classifier.java
 *
 */

package Biocomputing;

import java.lang.Math;

abstract public class Classifier {
	
	protected double fitness;

	public abstract int classifyInstance(Instance ins);
	public abstract void printClassifier();
	public abstract int classifierClass();
	
	public double getFitness() {
		return fitness;
	}

	public double computeFitness(InstanceSet is) {
		Instance[] instances = is.getInstances();

		double totalInstances=instances.length;
		double numCorrectPos=0;
		double numCorrectNeg=0;
		double numMatched=0;
		int i;

		int classOfClassifier = classifierClass();
		for(i=0;i<instances.length;i++) {
			int pred = classifyInstance(instances[i]);
			if(pred!= -1) {
				numMatched++;
				if(instances[i].getClassValue()==pred) {
					numCorrectPos++;
				}
			} else {
				if(instances[i].getClassValue()!=classOfClassifier) {
					numCorrectNeg++;
				}
			}
		}

		double accuracy=(numCorrectPos+numCorrectNeg)/totalInstances;
		double error;
		if(numMatched>0) {
			error=numCorrectPos/numMatched;
		} else {
			error = 0;
		}

		fitness=accuracy*Math.pow(error,2);

		//System.out.println("Acc:"+accuracy+" Erorr:"+error+" fitness "+fitness);
		return fitness;
	}

	public void computeStats(InstanceSet is) {
		Instance[] instances = is.getInstancesOrig();

		double totalInstances=instances.length;
		double numCorrect=0;
		double numMatched=0;
		int i;

		for(i=0;i<instances.length;i++) {
			int pred = classifyInstance(instances[i]);
			if(pred!=-1) {
				numMatched++;
				if(instances[i].getClassValue()==pred) {
					numCorrect++;
				}
			}
		}

		double accuracy=numCorrect/numMatched*100;
		double coverage=numMatched/totalInstances*100;

		System.out.printf("Accuracy %.2f%%, coverage %.2f%%%n",accuracy,coverage);
	}
}
