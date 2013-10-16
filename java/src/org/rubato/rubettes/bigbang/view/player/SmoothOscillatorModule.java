package org.rubato.rubettes.bigbang.view.player;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Add;
import com.jsyn.unitgen.Multiply;
import com.jsyn.unitgen.UnitBinaryOperator;

public class SmoothOscillatorModule {
	
	private UnitBinaryOperator operator;
	private UnitOutputPort thingInInputA;
	private UnitOutputPort thingInInputB;
	private UnitInputPort thingInOutput;
	private int type;
	
	public SmoothOscillatorModule(UnitBinaryOperator operator, UnitOutputPort newOutput) { //int type) {
		this.operator = operator;
		this.thingInInputB = newOutput;
		this.thingInInputB.connect(this.operator.inputB);
		this.type = type;
	}
	
	public void insertBetween(SmoothOscillatorModule module) {
		this.insertBetween(module.getThingInInputA(), module.getThingInOutput());
	}
	
	public void insertBetween(UnitOutputPort out, UnitInputPort in) {
		out.disconnect(in);
		out.connect(this.operator.inputA);
		this.operator.output.connect(in);
		this.thingInInputA = out;
		this.thingInOutput = in;
	}
	
	public void remove() {
		this.disconnectOperator();
		this.thingInInputA.connect(this.thingInOutput);
		this.thingInInputA = null;
		this.thingInOutput = null;
	}
	
	private void disconnectOperator() {
		if (this.thingInOutput != null) {
			this.operator.output.disconnect(this.thingInOutput);
		}
		this.thingInInputA.disconnect(this.operator.inputA);
		this.thingInInputB.disconnect(this.operator.inputB);
	}
	
	private void replaceOperatorUnit(UnitBinaryOperator newOperator) {
		this.disconnectOperator();
		this.thingInInputA.connect(newOperator.inputA);
		this.thingInInputB.connect(newOperator.inputB);
		if (this.thingInOutput != null) {
			this.thingInOutput.connect(newOperator.output);
		}
		this.operator = newOperator;
	}
	
	public UnitInputPort getThingInOutput() {
		return this.thingInOutput;
	}
	
	public UnitOutputPort getThingInInputA() {
		return this.thingInInputA;
	}
	
	public UnitBinaryOperator getOperatorUnit() {
		return this.operator;
	}
	
	public void setType(int modulatorType) {
		if ((modulatorType == JSynObject.FREQUENCY_MODULATION || modulatorType == JSynObject.ADDITIVE)
				&& !(this.operator instanceof Add)) {
			this.replaceOperatorUnit(new Add());
		} else if (modulatorType == JSynObject.RING_MODULATION && !(this.operator instanceof Multiply)) {
			this.replaceOperatorUnit(new Multiply());
		}
	}
	
	public int getType() {
		return this.type;
	}

}
