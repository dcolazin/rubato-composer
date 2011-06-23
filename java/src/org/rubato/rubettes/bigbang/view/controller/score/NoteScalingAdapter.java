package org.rubato.rubettes.bigbang.view.controller.score;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.event.MouseInputAdapter;

import org.rubato.rubettes.bigbang.view.controller.ViewController;
import org.rubato.rubettes.bigbang.view.model.tools.ScalingTool;

public class NoteScalingAdapter extends MouseInputAdapter {
	
	private final Dimension REFERENCE = new Dimension(100, 100);
	private ViewController controller;
	private Point2D.Double center;
	private ScalingTool scalingTool;
	
	public NoteScalingAdapter(ViewController controller) {
		this.controller = controller;
	}
	
	public void mousePressed(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1) {
			this.center = new Point2D.Double(event.getPoint().x, event.getPoint().y);
			this.scalingTool = new ScalingTool(this.center, this.REFERENCE);
			this.controller.changeDisplayTool(this.scalingTool);
		}
	}

	public void mouseDragged(MouseEvent event) {
		this.updateScalingTool(event);
	}

	public void mouseReleased(MouseEvent event) {
		this.scaleSelectedNotes(event);
	}
	
	private void updateScalingTool(MouseEvent event) {
		if (this.scalingTool != null) {
			double[] scaleFactors = this.scale(event, true);
			this.scalingTool.setScaleFactors(scaleFactors);
			this.controller.changeDisplayTool(this.scalingTool);
		}
	}
	
	private void scaleSelectedNotes(MouseEvent event) {
		if (this.scalingTool != null) {
			this.scale(event, false);
			this.scalingTool = null;
			this.controller.clearDisplayTool();
		}
	}
	
	private double[] scale(MouseEvent event, boolean inPreviewMode) {
		double[] scaleFactors = this.calculateScaleFactors(event);
		Point2D.Double currentEndPoint = new Point2D.Double(event.getPoint().x, event.getPoint().y);
		this.controller.scaleSelectedNotes(this.center, currentEndPoint, scaleFactors, event.isAltDown(), inPreviewMode);
		return scaleFactors;
	}
	
	private double[] calculateScaleFactors(MouseEvent event) {
		Point endPoint = event.getPoint();
		double xFactor = Math.abs(endPoint.x-this.center.x)*2/this.REFERENCE.getWidth();
		if (event.isShiftDown()) {
			return new double[]{xFactor, xFactor};
		}
		double yFactor = Math.abs(endPoint.y-this.center.y)*2/this.REFERENCE.getHeight();
		return new double[]{xFactor, yFactor};
	}

}
