package org.rubato.rubettes.bigbang.view.model;

import java.util.ArrayList;
import java.util.List;

import org.rubato.rubettes.bigbang.model.Model;
import org.rubato.rubettes.bigbang.view.controller.ViewController;

public class ViewParameters extends Model {
	
	public static final int X = 0;
	public static final int Y = 1;
	public static final int SATURATION = 2;
	public static final int WIDTH = 3;
	public static final int HUE = 4;
	public static final int HEIGHT = 5;
	public static final int VIEW_PARAMETER_COUNT = 6;
	
	private ArrayList<ViewParameter> parameters;
	private int[] selectedViewParameters;
	private List<Double> minValues, maxValues;
	
	public ViewParameters(ViewController controller, boolean invertYAxis) {
		controller.addModel(this);
		this.minValues = new ArrayList<Double>();
		this.maxValues = new ArrayList<Double>();
		this.initParameters(invertYAxis);
		this.setSelectedViewParameters(new int[]{0,1,2,3,4,-1});
	}
	
	private void initParameters(boolean invertYAxis) {
		this.parameters = new ArrayList<ViewParameter>();
		this.parameters.add(new ViewParameter("x-axis", false, 0, false)); //x
		this.parameters.add(new ViewParameter("y-axis", invertYAxis, 0, false)); //y
		ViewParameter opacity = new ViewParameter("opacity", false, 1, 0, 1, false);
		opacity.setMinAndMaxGoalValues(false, 0, 127, false);
		this.parameters.add(new ViewParameter("opacity", false, 1, 0.35, 1, false)); //saturation
		this.parameters.add(new ViewParameter("width", false, 1, false)); //width
		this.parameters.add(new ViewParameter("color", false, 0.0833, 0.0833, 1.0833, true)); //hue
		this.parameters.add(new ViewParameter("height", false, 1, 1, 10, false)); //height
		this.firePropertyChange(ViewController.VIEW_PARAMETERS, null, this);
	}
	
	public int size() {
		return this.parameters.size();
	}
	
	public ViewParameter get(int index) {
		return this.parameters.get(index);
	}
	
	public void setSelectedXYViewParameters(int[] newSelections) {
		this.selectedViewParameters[0] = newSelections[0];
		this.selectedViewParameters[1] = newSelections[1];
		this.updateMinAndMaxValues();
		this.firePropertyChange(ViewController.SELECTED_VIEW_PARAMETERS, null, this.selectedViewParameters);
	}
	
	public void setSelectedViewParameters(int[] newSelections) {
		this.selectedViewParameters = newSelections;
		this.updateMinAndMaxValues();
		this.firePropertyChange(ViewController.SELECTED_VIEW_PARAMETERS, null, this.selectedViewParameters);
	}
	
	public int getSelected(int index) {
		return this.selectedViewParameters[index];
	}
	
	public void setDenotatorMinAndMaxValues(List<Double> minValues, List<Double> maxValues) {
		this.minValues = minValues;
		this.maxValues = maxValues;
		this.updateMinAndMaxValues();
	}
	
	public void setManualDenotatorLimits(Integer index, Boolean manual, Double minValue, Double maxValue) {
		ViewParameter parameter = this.parameters.get(index);
		parameter.setManualDenotatorLimits(manual, minValue, maxValue);
		this.firePropertyChange(ViewController.VIEW_PARAMETERS, null, this);
	}
	
	public void setParameterMinAndMax(Integer index, Boolean relative, Double minValue, Double maxValue, Boolean cyclic) {
		this.parameters.get(index).setMinAndMaxGoalValues(relative, minValue, maxValue, cyclic);
		this.firePropertyChange(ViewController.VIEW_PARAMETERS, null, this);
	}
	
	public double[] getMaxValues() {
		return this.getMaxValues();
	}
	
	private void updateMinAndMaxValues() {
		for (int i = 0; i < this.parameters.size(); i++) {
			int d = this.selectedViewParameters[i];
			//System.out.println(this.minValues[1]+" "+this.maxValues[1]);
			if (d > -1 && this.minValues.size() > d && this.maxValues.size() > d) {
				this.parameters.get(i).setDenotatorLimitsIfNotManual(this.minValues.get(d), this.maxValues.get(d));
			}
		}
		this.firePropertyChange(ViewController.VIEW_PARAMETERS, null, this);
	}

}
