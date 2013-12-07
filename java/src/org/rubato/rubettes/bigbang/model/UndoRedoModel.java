package org.rubato.rubettes.bigbang.model;

import java.util.List;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEditSupport;

import org.rubato.math.yoneda.Form;
import org.rubato.rubettes.bigbang.controller.BigBangController;
import org.rubato.rubettes.bigbang.controller.Controller;
import org.rubato.rubettes.bigbang.model.edits.AbstractOperationEdit;
import org.rubato.rubettes.bigbang.model.edits.AbstractTransformationEdit;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;

public class UndoRedoModel extends Model {
	
	private UndoManager undoManager;
	private UndoableEditSupport undoSupport;
	private BigBangTransformationGraph operations;
	private Integer insertionState;
	private List<AbstractOperationEdit> undoneOperations;
	private BigBangGraphAnimator animator;
	
	public UndoRedoModel(Controller controller) {
		controller.addModel(this);
		this.undoManager = new UndoManager();
		this.undoSupport = new UndoableEditSupport();
		this.undoSupport.addUndoableEditListener(new UndoAdaptor(this.undoManager));
		this.reset();
		this.firePropertyChange(BigBangController.UNDO, null, this.undoManager);
		this.firePropertyChange(BigBangController.GRAPH, null, this.operations);
	}
	
	//called in BigBangController
	public void setForm(@SuppressWarnings("unused") Form form) {
		this.reset();
	}
	
	public void newWindowAdded() {
		this.firePropertyChange(BigBangController.GRAPH, null, this.operations);
	}
	
	public void toggleGraphAnimation() {
		if (this.animator != null && this.animator.isAlive()) {
			this.animator.end();
		} else {
			if (this.animator == null) {
				this.animator = new BigBangGraphAnimator(this.operations, this);
			} else {
				double previousPosition = this.animator.getPositionInPercent();
				this.animator = new BigBangGraphAnimator(this.operations, this);
				if (previousPosition < 1) {
					this.animator.setPosition(previousPosition);
				}
			}
			this.animator.start();
		}
	}
	
	/**
	 * @param position between 0 and 1
	 */
	public void setGraphAnimationPosition(Double position) {
		if (this.animator == null || !this.animator.isAlive()) {
			this.animator = new BigBangGraphAnimator(this.operations, this);
		}
		this.animator.setPosition(position);
	}
	
	/**
	 * Splits the currently selected operation at the current position of the animator if it is splittable
	 */
	public void splitOperation() {
		this.operations.splitSelectedAndParallelOperations(this.animator.getPositionInSeconds());
		this.animator.setGraph(this.operations);
		this.firePropertyChange(BigBangController.GRAPH, null, this.operations);
	}
	
	public void undo() {
		this.undoneOperations.add(this.operations.removeLastOperation(true));
		this.firePropertyChange(BigBangController.UNDO, null, this.undoManager);
		this.firePropertyChange(BigBangController.GRAPH, null, this.operations);
	}
	
	public void redo() {
		this.operations.addOperation(this.undoneOperations.remove(this.undoneOperations.size()-1));
		this.firePropertyChange(BigBangController.REDO, null, this.undoManager);
		this.firePropertyChange(BigBangController.GRAPH, null, this.operations);
	}
	
	public void previewTransformation(AbstractTransformationEdit edit) {
		if (this.insertionState != null) {
			this.operations.previewInsertedTransformationAt(edit, this.insertionState);
		} else {
			this.operations.previewTransformationAtEnd(edit);
		}
	}
	
	public void postEdit(AbstractUndoableEdit edit) {
		//this.undoSupport.postEdit(edit);
		if (edit instanceof AbstractOperationEdit) {
			if (this.insertionState != null) {
				this.operations.insertOperation((AbstractOperationEdit)edit, this.insertionState);
				this.insertionState = null;
			} else {
				this.operations.addOperation((AbstractOperationEdit)edit);
			}
		}
		this.firePropertyChange(BigBangController.UNDO, null, this.undoManager);
		this.firePropertyChange(BigBangController.GRAPH, null, this.operations);
	}
	
	public AbstractOperationEdit getLastAddedEdit() {
		if (this.operations.getEdgeCount() > 0) {
			return this.operations.getLastAddedOperation();
		}
		return null;
	}
	
	public void modifiedOperation(Boolean inPreviewMode) {
		this.operations.updateComposition(inPreviewMode);
		this.firePropertyChange(BigBangController.GRAPH, null, this.operations);
	}
	
	public void modifyOperation(Integer operationIndex, Double ratio) {
		if (operationIndex >= 0 && this.operations.getEdgeCount() > operationIndex) {
			DijkstraShortestPath<Integer,AbstractOperationEdit> dijkstra = new DijkstraShortestPath<Integer,AbstractOperationEdit>(this.operations);
		    List<AbstractOperationEdit> shortestPath = dijkstra.getPath(0, this.operations.getLastState());
		    AbstractOperationEdit operation = shortestPath.get(operationIndex);
		    operation.modify(ratio);
			this.operations.updateComposition(true);
			this.firePropertyChange(BigBangController.MODIFY_OPERATION, null, operation);
		}
	}
	
	public void setOperationDurations(double duration) {
		this.operations.setDurations(duration);
	}
	
	public void insertOperation(Integer state) {
		this.insertionState = state;
	}
	
	public void removeOperation(AbstractOperationEdit operation) {
		this.operations.removeOperation(operation, true);
		this.firePropertyChange(BigBangController.GRAPH, null, this.operations);
	}
	
	public void selectCompositionState(Integer vertex) {
		this.operations.selectCompositionState(vertex, true);
		this.firePropertyChange(BigBangController.SELECT_COMPOSITION_STATE, null, vertex);
	}
	
	public void deselectCompositionStates() {
		this.operations.selectCompositionState(null, true);
		this.firePropertyChange(BigBangController.DESELECT_COMPOSITION_STATES, null, null);
	}
	
	public void selectOperation(AbstractOperationEdit edge) {
		this.operations.selectOperation(edge);
		this.firePropertyChange(BigBangController.SELECT_OPERATION, null, edge);
	}
	
	public void deselectOperations() {
		this.operations.selectOperation(null);
		this.firePropertyChange(BigBangController.DESELECT_OPERATIONS, null, null);
	}
	
	public void reset() {
		this.undoManager.discardAllEdits();
		this.operations = new BigBangTransformationGraph(this);
		this.firePropertyChange(BigBangController.GRAPH, null, this.operations);
	}
	
	public BigBangTransformationGraph getTransformationGraph() {
		return this.operations;
	}

}
