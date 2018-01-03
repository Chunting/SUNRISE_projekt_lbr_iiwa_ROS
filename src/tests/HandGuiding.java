package tests;

import javax.inject.Inject;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;

import static com.kuka.roboticsAPI.motionModel.HRCMotions.*;
import com.kuka.generated.ioAccess.*;

public class HandGuiding extends RoboticsAPIApplication {

	
	private LBR lbr;
	@Inject 
	private MediaFlangeIOGroup ledBlau;
	
	final static double radiusOfCircMove=120;
	final static int nullSpaceAngle = 80;

	final static double offsetAxis2And4=Math.toRadians(20);
	final static double offsetAxis4And6=Math.toRadians(-40);
	double[] loopCenterPosition= new double[]{
			0, offsetAxis2And4, 0, offsetAxis2And4 +offsetAxis4And6 -Math.toRadians(90), 0, offsetAxis4And6,Math.toRadians(90)};



	public void initialize() {		
		lbr = getContext().getDeviceFromType(LBR.class);
	}

	public void run() {	
		//ledBlau.setLEDBlue(true);
		//lbr.setESMState("1");
		//lbr.move(ptp(getApplicationData().getFrame("/P1")));
		//lbr.setESMState("2");
		//lbr.move(handGuiding());
		//ledBlau.setLEDBlue(false);
		//lbr.setESMState("1");
		//ledBlau.setLEDBlue(true);
		//lbr.move(ptp(getApplicationData().getFrame("/P2")));
	}

}