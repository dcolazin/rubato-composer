package org.rubato.rubettes.bigbang.view.subview;

import java.awt.Point;
import java.util.List;

import org.rubato.rubettes.bigbang.view.model.DisplayObject;
import org.rubato.rubettes.bigbang.view.model.ViewParameter;
import org.rubato.rubettes.bigbang.view.model.ViewParameters;
import org.rubato.rubettes.bigbang.view.model.tools.DisplayTool;

public class DisplayContents {
	
	//finally merge with display!!!!
	protected ViewParameters viewParameters;
	private int[] selectedViewParameters;
	protected DisplayObjectList notes;
	private DisplayAxes axes;
	private DisplayTool tool;
	protected int currentWidth, currentHeight;
	protected double xZoomFactor, yZoomFactor;
	protected int xPosition, yPosition;
	private boolean satellitesConnected;
	
	public DisplayContents() {
		this.axes = new DisplayAxes(this);
	}
	
	public void setNotes(DisplayObjectList notes) {
		for (DisplayObject currentNote : notes) {
			currentNote.setDisplay(this);
		}
		this.notes = notes;
		this.updateNoteBounds();
	}
	
	public DisplayObjectList getNotes() {
		return this.notes;
	}
	
	public void setTool(DisplayTool tool) {
		this.tool = tool;
		if (this.tool != null) {
			this.tool.setDisplay(this);
		}
	}
	
	public void setViewParameters(ViewParameters viewParameters) {
		this.viewParameters = viewParameters;
	}
	
	public void setSelectedViewParameters(int[] selectedViewParameters) {
		this.selectedViewParameters = selectedViewParameters;
		this.updateNoteBounds();
	}
	
	public void setZoomFactors(double[] zoomFactors) {
		this.xZoomFactor = zoomFactors[0];
		this.yZoomFactor = zoomFactors[1];
		this.updateNoteBounds();
	}
	
	public void setPosition(Point position) {
		this.xPosition = position.x;
		this.yPosition = position.y;
		this.updateNoteBounds();
	}
	
	protected void updateNoteBounds() {
		if (this.notes != null) {
			this.notes.updateBounds(this.xZoomFactor, this.yZoomFactor, this.xPosition, this.yPosition);
		}
	}
	
	public void setCurrentSize(int currentWidth, int currentHeight) {
		this.currentWidth = currentWidth;
		this.currentHeight = currentHeight;
	}
	
	public int getCurrentWidth() {
		return this.currentWidth;
	}
	
	public int getCurrentHeight() {
		return this.currentHeight;
	}
	
	public double getXZoomFactor() {
		return this.xZoomFactor;
	}
	
	public double getYZoomFactor() {
		return this.yZoomFactor;
	}
	
	public int getXPosition() {
		return this.xPosition;
	}
	
	public int getYPosition() {
		return this.yPosition;
	}
	
	public double getMinVisibleX() {
		double value = (0-this.xPosition)/this.xZoomFactor;
		//double value = this.xPosition;//this.xZoomFactor;
		return this.viewParameters.get(0).translateDisplayValue(value);
	}
	
	public double getMaxVisibleX() {
		double value = (this.currentWidth-this.xPosition)/this.xZoomFactor;
		return this.viewParameters.get(0).translateDisplayValue(value);
	}
	
	public double getMinVisibleY() {
		double value = (this.currentHeight-this.yPosition)/this.yZoomFactor;
		return this.viewParameters.get(1).translateDisplayValue(value);
	}
	
	public double getMaxVisibleY() {
		double value = (0-this.yPosition)/this.yZoomFactor;
		return this.viewParameters.get(1).translateDisplayValue(value);
	}
	
	public double translateXDenotatorValue(double value) {
		value = this.viewParameters.get(0).translateDenotatorValue(value);
		return value*this.xZoomFactor+this.xPosition;
	}
	
	public double translateYDenotatorValue(double value) {
		value = this.viewParameters.get(1).translateDenotatorValue(value);
		return value*this.yZoomFactor+this.yPosition;
	}
	
	public double getMaxY() {
		int selectedYParameter = this.selectedViewParameters[1];
		double[] maxValues = this.viewParameters.getMaxValues();
		if (selectedYParameter >= 0 && maxValues != null) {
			return maxValues[selectedYParameter];
		}
		return 0;
	}
	
	public ViewParameter getViewParameter(int i) {
		return this.viewParameters.get(i);
	}
	
	public int getSelectedViewParameter(int i) {
		return this.selectedViewParameters[i];
	}
	
	public void setSatellitesConnected(boolean satellitesConnected) {
		this.satellitesConnected = satellitesConnected;
	}
	
	public boolean satellitesConnected() {
		return this.satellitesConnected;
	}
	
	public double translateValue(List<Double> denotatorValues, int i) {
		int v = this.selectedViewParameters[i];
		if (v > -1 && v < denotatorValues.size()) {
			//System.out.println(v+" "+this.viewParameters.get(i).translateDenotatorValue(denotatorValues[v]));
			return this.viewParameters.get(i).translateDenotatorValue(denotatorValues.get(v));
		}
		return this.viewParameters.get(i).getDefaultValue();
	}
	
	public void paint(AbstractPainter painter, int width, int height) {
		this.setCurrentSize(width, height);
		this.paintAxes(painter);
		this.paintNotes(painter);
		this.paintTool(painter);
	}
	
	public void paintNotes(AbstractPainter painter) {
		this.notes.paint(painter);
	}
	
	public void paintAxes(AbstractPainter painter) {
		this.axes.paint(painter);
	}
	
	public void paintTool(AbstractPainter painter) {
		if (this.tool != null) {
			this.tool.paint(painter);
		}
	}

}
