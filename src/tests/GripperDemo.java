package tests;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.kuka.generated.ioAccess.MediaFlangeIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.motionModel.CartesianPTP;
import com.kuka.roboticsAPI.motionModel.Spline;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

/**
 * Implementation of a robot application.
 * <p>
 * The application provides a {@link RoboticsAPITask#initialize()} and a 
 * {@link RoboticsAPITask#run()} method, which will be called successively in 
 * the application lifecycle. The application will terminate automatically after 
 * the {@link RoboticsAPITask#run()} method has finished or after stopping the 
 * task. The {@link RoboticsAPITask#dispose()} method will be called, even if an 
 * exception is thrown during initialization or run. 
 * <p>
 * <b>It is imperative to call <code>super.dispose()</code> when overriding the 
 * {@link RoboticsAPITask#dispose()} method.</b> 
 * 
 * @see UseRoboticsAPIContext
 * @see #initialize()
 * @see #run()
 * @see #dispose()
 */
public class GripperDemo extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;
	@Inject 
	private MediaFlangeIOGroup mfTouch;
	
	private Spline splineMovement;
	private long gripperDelay = 1000;
	
	/**
	 * Opens a simple gripper connected to output X3 on the media flange touch. To consider the time the gripper needs to open/close,
	 * a delay can be specified that the program will halt for it to open/close. Optionally the blue LED of the media flange can be
	 * turned on whenever the gripper closes/X3 output pin is set to +24V.
	 * @param open				If 'true', the gripper closes (output X3 is set to +24V).
	 * @param delayMillisecs	The delay that the software will halt after opening the gripper.
	 * @param useLED			Flag, if LED value is to be set when the gripper closes.
	 */
	public void setGripperOpen(boolean open, long delayMillisecs, boolean useLED) {
		mfTouch.setOutputX3Pin1(open);
		if (useLED) mfTouch.setLEDBlue(open);
		try {
			TimeUnit.MILLISECONDS.sleep(delayMillisecs);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void initialize() {
		lbr = getContext().getDeviceFromType(LBR.class);
		
		splineMovement = new Spline(
								spl(getApplicationData().getFrame("/SP1")),
								spl(getApplicationData().getFrame("/SP2")),
								spl(getApplicationData().getFrame("/SP3")),
								spl(getApplicationData().getFrame("/SP4")),
								spl(getApplicationData().getFrame("/SP5")),
								spl(getApplicationData().getFrame("/SP6")),
								spl(getApplicationData().getFrame("/SP7")),
								spl(getApplicationData().getFrame("/SP8")),
								spl(getApplicationData().getFrame("/SP9")),
								spl(getApplicationData().getFrame("/SP10")),
								spl(getApplicationData().getFrame("/SP11")),
								spl(getApplicationData().getFrame("/SP12")),
								spl(getApplicationData().getFrame("/SP13")),
								spl(getApplicationData().getFrame("/SP14")),
								spl(getApplicationData().getFrame("/SP15")),
								spl(getApplicationData().getFrame("/SP16")),
								spl(getApplicationData().getFrame("/Front_upright")));
	}

	@Override
	public void run() {
		int doWiggle = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "Do you want the robot to do some stuff between the two gripping positions?", "No, just pick and place.", "Yes", "Yes, please! (16 splinable frames must be available!)", "Yes, please, and go quick!");
		setGripperOpen(false, 0, true);
		//lbr.move(ptpHome());
		
		getLogger().info("Approaching first position.");
		lbr.move(ptp(getApplicationData().getFrame("/Pos1_approach")));
		lbr.move(ptp(getApplicationData().getFrame("/Pos1_grip")));
		getLogger().info("Closing Gripper.");
		setGripperOpen(true, gripperDelay, true);
		getLogger().info("Lifting piece.");
		lbr.move(ptp(getApplicationData().getFrame("/Pos1_lift")));
		
		if (doWiggle == 1) {
			getLogger().info("Doing some moves!");
			lbr.move(ptp(getApplicationData().getFrame("/Front_upright")));
			lbr.move(ptp(getApplicationData().getFrame("/Front_upsideDown")));
			lbr.move(ptp(getApplicationData().getFrame("/Rear_upsideDown")));
			lbr.move(ptp(getApplicationData().getFrame("/Front2_upright")));
		}
		else if (doWiggle == 2) {
			getLogger().info("Here comes the spline!");
			lbr.move(ptp(getApplicationData().getFrame("/Front_upright")));
			splineMovement.setJointJerkRel(0.05);
			splineMovement.setJointAccelerationRel(0.05);
			lbr.move(splineMovement);
		}
		else if (doWiggle == 3) {
			lbr.move(ptp(getApplicationData().getFrame("/Front_upright")));
			getLogger().info("Spline with high acceleration and jerk!");
			lbr.move(splineMovement);
		}
		
		getLogger().info("Approaching target position.");
		lbr.move(ptp(getApplicationData().getFrame("/Pos2_lift")));
		lbr.move(ptp(getApplicationData().getFrame("/Pos2_grip")));
		getLogger().info("Opening Gripper");
		setGripperOpen(false, gripperDelay, true);
		lbr.move(ptp(getApplicationData().getFrame("/Pos2_approach")));
		
		int driveBack = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "Do you want the robot to put it back?", "No", "Yes");

		if (driveBack > 0) {
			lbr.move(ptp(getApplicationData().getFrame("/Pos2_grip")));
			setGripperOpen(true, gripperDelay, true);
			lbr.move(ptp(getApplicationData().getFrame("/Pos2_lift")));
			
			if (doWiggle > 0) {
				double radius = 300;
				ObjectFrame startFrame = getApplicationData().getFrame("/CircStart");
				Frame rightFrame = (new Frame(startFrame)).setY(-radius).setX(radius);
				Frame bottomFrame = (new Frame(startFrame)).setY(-2*radius);
				Frame leftFrame = (new Frame(startFrame)).setY(-radius).setX(-radius);
				
				lbr.move(ptp(startFrame));
				Spline circleSpline = new Spline(
											spl(rightFrame),
											spl(bottomFrame),
											spl(leftFrame),
											spl(startFrame),
											spl(rightFrame),
											spl(bottomFrame),
											spl(leftFrame),
											spl(startFrame),
											spl(getApplicationData().getFrame("/Pos1_lift")));
				lbr.move(circleSpline);
			}
			else lbr.move(ptp(getApplicationData().getFrame("/Pos1_lift")));
			
			lbr.move(ptp(getApplicationData().getFrame("/Pos1_grip")));
			getLogger().info("Opening Gripper");
			setGripperOpen(false, gripperDelay, true);
			lbr.move(ptp(getApplicationData().getFrame("/Pos1_approach")));
		}
		
		getLogger().info("Done!");
	}
}