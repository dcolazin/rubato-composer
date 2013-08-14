package org.rubato.rubettes.bigbang.view.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.TreeMap;

import org.rubato.rubettes.bigbang.controller.Controller;
import org.rubato.rubettes.bigbang.model.edits.AbstractOperationEdit;
import org.rubato.rubettes.bigbang.view.View;
import org.rubato.rubettes.bigbang.view.controller.mode.DisplayModeAdapter;
import org.rubato.rubettes.bigbang.view.model.DisplayObject;
import org.rubato.rubettes.bigbang.view.model.ZoomChange;
import org.rubato.rubettes.bigbang.view.model.tools.DisplayTool;
import org.rubato.rubettes.bigbang.view.model.tools.SelectionTool;

public class ViewController extends Controller {
	
	//modes
	public static final String DISPLAY_MODE = "setDisplayMode";
	
	//gui
	public static final String ADD_WINDOW = "addNewWindow";
	public static final String MAIN_OPTIONS_VISIBLE = "toggleMainOptionsVisible";
	public static final String VIEW_PARAMETERS_VISIBLE = "toggleViewParametersVisible";
	public static final String SHOW_WINDOW_PREFERENCES = "showWindowPreferences";
	
	//view manipulation
	public static final String VIEW_PARAMETERS = "ViewParameters";
	public static final String SELECTED_VIEW_PARAMETERS = "SelectedViewParameters";
	public static final String MANUAL_DENOTATOR_LIMITS = "setManualDenotatorLimits";
	public static final String PARAMETER_MIN_MAX = "setParameterMinAndMax";
	public static final String ZOOM_FACTORS = "changeZoomFactors";
	public static final String DISPLAY_POSITION = "changeDisplayPosition";
	public static final String SATELLITES_CONNECTED = "SatellitesConnected";
	public static final String LAYERS = "changeLayerState";
	public static final String TOGGLE_MOD_FILTER = "toggleModFilter";
	public static final String MOD_FILTER_VALUES = "changeModFilter";
	
	//general functionality
	public static final String UNDO = "undo";
	public static final String REDO = "redo";
	public static final String SELECT_OPERATION = "selectOperation";
	public static final String DESELECT_OPERATIONS = "deselectOperations";
	public static final String SELECT_COMPOSITION_STATE = "selectCompositionState";
	public static final String DESELECT_COMPOSITION_STATES = "deselectCompositionStates";
	public static final String INPUT_ACTIVE = "InputActive";
	
	//score manipulation - display
	public static final String FORM = "Form";
	public static final String DISPLAY_NOTES = "DisplayNotes";
	public static final String NOTE_SELECTION = "toggleNoteSelection";
	public static final String ANCHOR_NOTE_SELECTION = "toggleAnchorNoteSelection";
	public static final String SELECT_NOTES = "selectNotes";
	public static final String DISPLAY_TOOL = "setDisplayTool";
	public static final String CLEAR_DISPLAY_TOOL = "clearDisplayTool";
	
	//score manipulation - denotators
	public static final String STANDARD_DENOTATOR_VALUES = "setStandardDenotatorValue";
	public static final String ACTIVE_OBJECT = "setActiveObject";
	public static final String ACTIVE_COLIMIT_COORDINATE = "setActiveColimitCoordinate";
	public static final String MAX_SATELLITE_LEVEL = "setSatelliteLevels";
	public static final String ACTIVE_SATELLITE_LEVEL = "setActiveSatelliteLevel";
	public static final String ADD_OBJECT = "addObject";
	public static final String DELETE_OBJECTS = "deleteSelectedObjects";
	public static final String COPY_NOTES = "copySelectedNotesTo";
	public static final String COPY_NOTES_NEW = "copySelectedNotesToNewLayer";
	public static final String MOVE_NOTES = "moveSelectedNotesTo";
	public static final String MOVE_NOTES_NEW = "moveSelectedNotesToNewLayer";
	public static final String SATELLITE_NOTES = "addSelectedNotesAsSatellitesTo";
	public static final String FLATTEN_NOTES = "flattenSelectedNotes";
	public static final String MODULATOR_NOTES = "addSelectedNotesAsModulatorsTo";
	public static final String REMOVE_NOTES_FROM_CARRIER = "removeSelectedNotesFromCarrier";
	
	//score manipulation - transformations
	public static final String TRANSLATE_NOTES = "translateSelectedNotes";
	public static final String ROTATE_NOTES = "rotateSelectedNotes";
	public static final String SCALE_NOTES = "scaleSelectedNotes";
	public static final String REFLECT_NOTES = "reflectSelectedNotes";
	public static final String SHEAR_NOTES = "shearSelectedNotes";
	public static final String SHAPE_NOTES = "shapeSelectedNotes";
	public static final String AFFINE_TRANSFORM_NOTES = "affineTransformSelectedNotes";
	public static final String MODIFY_OPERATION = "modifyOperation";
	public static final String MODIFY_SELECTED_TRANSFORMATION = "modifySelectedTransformation";
	public static final String MODIFY_ROTATION = "modifyRotationAngle";
	
	//score manipulation - wallpaper
	public static final String START_WALLPAPER = "startWallpaper";
	public static final String ADD_WP_DIMENSION = "addWallpaperDimension";
	public static final String END_WALLPAPER = "endWallpaper";
	public static final String RANGE = "setRange";
	
	//score manipulation - alteration
	public static final String ALTERATION_COMPOSITION = "setAlterationComposition";
	
	//playback feature
	public static final String PLAY_MODE = "togglePlayMode";
	public static final String IS_LOOPING = "setIsLooping";
	public static final String TEMPO = "setTempo";
	public static final String PLAYBACK_POSITION = "setPlaybackPosition";
	public static final String FM_MODEL = "FMModel";
	public static final String WAVEFORM = "Waveform";
	public static final String PRESS_MIDI_KEY = "pressMidiKey";
	public static final String RELEASE_MIDI_KEY = "releaseMidiKey";
	
	
	public void changeDisplayMode(DisplayModeAdapter newMode) {
		this.callModelMethod(ViewController.DISPLAY_MODE, newMode);
	}
	
	public void addNewWindow() {
		this.callModelMethod(ViewController.ADD_WINDOW);
	}
	
	public void toggleMainOptionsVisible() {
		this.callModelMethod(ViewController.MAIN_OPTIONS_VISIBLE);
	}
	
	public void changeLayerState(int layerIndex) {
		this.callModelMethod(ViewController.LAYERS, layerIndex);
	}
	
	public void toggleModFilter() {
		this.callModelMethod(ViewController.TOGGLE_MOD_FILTER);
	}
	
	public void changeModFilter(int modLevel, int modNumber) {
		this.callModelMethod(ViewController.MOD_FILTER_VALUES, modLevel, modNumber);
	}
	
	public void toggleViewParametersVisible() {
		this.callModelMethod(ViewController.VIEW_PARAMETERS_VISIBLE);
	}
	
	public void changeViewParameters(int[] newViewParameters) {
		this.setModelProperty(ViewController.SELECTED_VIEW_PARAMETERS, newViewParameters);
	}
	
	public void setStandardDenotatorValue(int index, double value) {
		this.callModelMethod(ViewController.STANDARD_DENOTATOR_VALUES, index, value);
	}
	
	public void setActiveObject(int objectIndex) {
		this.callModelMethod(ViewController.ACTIVE_OBJECT, objectIndex);
	}
	
	public void setActiveColimitCoordinate(int colimitIndex, int coordinateIndex) {
		this.callModelMethod(ViewController.ACTIVE_COLIMIT_COORDINATE, colimitIndex, coordinateIndex);
	}
	
	public void setActiveSatelliteLevel(int satelliteLevel) {
		this.callModelMethod(ViewController.ACTIVE_SATELLITE_LEVEL, satelliteLevel);
	}
	
	public void showWindowPreferences() {
		this.callModelMethod(ViewController.SHOW_WINDOW_PREFERENCES);
	}
	
	public void changeDenotatorMinAndMax(int index, boolean manual, double min, double max) {
		this.callModelMethod(ViewController.MANUAL_DENOTATOR_LIMITS, index, manual, min, max);
	}
	
	public void changeParameterMinAndMax(int index, boolean relative, double min, double max, boolean cyclic) {
		this.callModelMethod(ViewController.PARAMETER_MIN_MAX, index, relative, min, max, cyclic);
	}
	
	public void changeZoomFactors(ZoomChange zoomChange) {
		this.callModelMethod(ViewController.ZOOM_FACTORS, zoomChange);
	}
	
	public void setZoomFactors(double zoomFactor) {
		this.callModelMethod("setZoomFactors", zoomFactor, zoomFactor);
	}
	
	public void changeDisplayPosition(Dimension difference) {
		this.callModelMethod(ViewController.DISPLAY_POSITION, difference);
	}
	
	public void setDisplayPosition(Point center) {
		this.callModelMethod("setDisplayPosition", center);
	}
	
	public void toggleNoteSelection(Point location) {
		this.callModelMethod(ViewController.NOTE_SELECTION, location);
	}
	
	public void toggleAnchorNoteSelection(Point location) {
		this.callModelMethod(ViewController.ANCHOR_NOTE_SELECTION, location);
	}
	
	public void selectNotes(SelectionTool tool, boolean stillSelecting) {
		this.callModelMethod(ViewController.SELECT_NOTES, tool, stillSelecting);
	}
	
	public void changeDisplayTool(DisplayTool tool) {
		this.callModelMethod(ViewController.DISPLAY_TOOL, tool);
	}
	
	public void clearDisplayTool() {
		this.callModelMethod(ViewController.CLEAR_DISPLAY_TOOL);
	}
	
	public void modifyOperation(int operationNumber, int midiValue) {
		this.callModelMethod(ViewController.MODIFY_OPERATION, operationNumber, midiValue);
	}
	
	public void modifySelectedTransformation(Point2D.Double endingPoint, boolean inPreviewMode) {
		this.callModelMethod(ViewController.MODIFY_SELECTED_TRANSFORMATION, endingPoint, inPreviewMode);
	}
	
	public void modifySelectedTransformation(double[] newValues, boolean inPreviewMode) {
		this.callModelMethod(ViewController.MODIFY_SELECTED_TRANSFORMATION, newValues, inPreviewMode);
	}
	
	public void modifyRotationAngle(Double angle, boolean inPreviewMode) {
		this.callModelMethod(ViewController.MODIFY_ROTATION, angle, inPreviewMode);
	}
	
	public void translateSelectedNotes(Point2D.Double center, Point2D.Double endPoint, boolean copyAndTranslate, boolean previewMode) {
		this.callModelMethod(ViewController.TRANSLATE_NOTES, center, endPoint, copyAndTranslate, previewMode);
	}
	
	public void rotateSelectedNotes(Point2D.Double center, Point2D.Double endPoint, double angle, boolean copyAndTranslate, boolean previewMode) {
		this.callModelMethod(ViewController.ROTATE_NOTES, center, endPoint, angle, copyAndTranslate, previewMode);
	}
	
	public void scaleSelectedNotes(Point2D.Double center, Point2D.Double endPoint, double[] scaleFactors, boolean copyAndTranslate, boolean previewMode) {
		this.callModelMethod(ViewController.SCALE_NOTES, center, endPoint, scaleFactors, copyAndTranslate, previewMode);
	}
	
	public void reflectSelectedNotes(Point2D.Double center, Point2D.Double endPoint, double[] reflectionVector, boolean copyAndTranslate, boolean previewMode) {
		this.callModelMethod(ViewController.REFLECT_NOTES, center, endPoint, reflectionVector, copyAndTranslate, previewMode);
	}
	
	public void shearSelectedNotes(Point2D.Double center, Point2D.Double endPoint, double[] shearingFactors, boolean copyAndTranslate, boolean previewMode) {
		this.callModelMethod(ViewController.SHEAR_NOTES, center, endPoint, shearingFactors, copyAndTranslate, previewMode);
	}
	
	public void affineTransformSelectedNotes(Point2D.Double center, Point2D.Double endPoint, double[] shift, double angle, double[] scaleFactors, boolean copyAndTransform, boolean previewMode) {
		this.callModelMethod(ViewController.AFFINE_TRANSFORM_NOTES, center, endPoint, shift, angle, scaleFactors, copyAndTransform, previewMode);
	}
	
	public void shapeSelectedNotes(TreeMap<Integer,Integer> location, boolean copyAndTransform, boolean previewMode) {
		this.callModelMethod(ViewController.SHAPE_NOTES, location, copyAndTransform, previewMode);
	}
	
	public void addObject(Point2D.Double location) {
		this.callModelMethod(ViewController.ADD_OBJECT, location);
	}
	
	public void deleteSelectedObjects() {
		this.callModelMethod(ViewController.DELETE_OBJECTS);
	}
	
	public void copySelectedNotesTo(int layerIndex) {
		this.callModelMethod(ViewController.COPY_NOTES, layerIndex);
	}
	
	public void copySelectedNotesToNewLayer() {
		this.callModelMethod(ViewController.COPY_NOTES_NEW);
	}
	
	public void moveSelectedNotesTo(int layerIndex) {
		this.callModelMethod(ViewController.MOVE_NOTES, layerIndex);
	}
	
	public void moveSelectedNotesToNewLayer() {
		this.callModelMethod(ViewController.MOVE_NOTES_NEW);
	}
	
	public void addSelectedNotesAsSatellitesTo(DisplayObject parentNote, int powersetIndex) {
		this.callModelMethod(ViewController.SATELLITE_NOTES, parentNote, powersetIndex);
	}
	
	public void flattenSelectedNotes() {
		this.callModelMethod(ViewController.FLATTEN_NOTES);
	}
	
	public void addSelectedNotesAsModulatorsTo(DisplayObject parentNote) {
		this.callModelMethod(ViewController.MODULATOR_NOTES, parentNote);
	}
	
	public void removeSelectedNotesFromCarrier() {
		this.callModelMethod(ViewController.REMOVE_NOTES_FROM_CARRIER);
	}
	
	public void addWallpaperDimension() {
		this.callModelMethod(ViewController.ADD_WP_DIMENSION);
	}
	
	public void stopWallpaper() {
		this.callModelMethod(ViewController.END_WALLPAPER);
	}
	
	public void changeWallpaperRange(int dimension, boolean rangeTo, int value) {
		this.callModelMethod(ViewController.RANGE, dimension, rangeTo, value);
	}
	
	public void changeAlterationComposition(int index) {
		this.callModelMethod(ViewController.ALTERATION_COMPOSITION, index);
	}
	
	public void undo() {
		this.callModelMethod(ViewController.UNDO);
	}
	
	public void redo() {
		this.callModelMethod(ViewController.REDO);
	}
	
	public void selectOperation(AbstractOperationEdit operation) {
		this.callModelMethod(ViewController.SELECT_OPERATION, operation);
	}
	
	public void deselectOperations() {
		this.callModelMethod(ViewController.DESELECT_OPERATIONS);
	}
	
	public void selectCompositionState(Integer vertex) {
		this.callModelMethod(ViewController.SELECT_COMPOSITION_STATE, vertex);
	}
	
	public void deselectCompositionStates() {
		this.callModelMethod(ViewController.DESELECT_COMPOSITION_STATES);
	}
	
	public void changeInputActive(boolean inputActive) {
		this.setModelProperty(ViewController.INPUT_ACTIVE, inputActive);
	}
	
	public List<View> getViews() {
		return this.registeredViews;
	}
	
	public void togglePlayMode() {
		this.callModelMethod(ViewController.PLAY_MODE);
	}
	
	public void setIsLooping(boolean isLooping) {
		this.callModelMethod(ViewController.IS_LOOPING, isLooping);
	}
	
	public void setTempo(int tempo) {
		this.callModelMethod(ViewController.TEMPO, tempo);
	}
	
	public void setPlaybackPosition(Point2D.Double clickPosition) {
		this.callModelMethod(ViewController.PLAYBACK_POSITION, clickPosition);
	}
	
	public void changeFMModel(Object fmModel) {
		this.setModelProperty(ViewController.FM_MODEL, fmModel);
	}
	
	public void changeWaveform(Object waveform) {
		this.setModelProperty(ViewController.WAVEFORM, waveform);
	}
	
	public void pressMidiKey(int pitch, int velocity) {
		this.callModelMethod(ViewController.PRESS_MIDI_KEY, pitch, velocity);
	}
	
	public void releaseMidiKey(int pitch) {
		this.callModelMethod(ViewController.RELEASE_MIDI_KEY, pitch);
	}

}
