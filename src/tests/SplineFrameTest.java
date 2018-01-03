package tests;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.kuka.generated.ioAccess.MediaFlangeIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
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
public class SplineFrameTest extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;
	@Inject 
	private MediaFlangeIOGroup mfTouch;
	
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
	}

	@Override
	public void run() {
		Spline splineMovement;
		int numFrames = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "How many frames?", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14") + 3;
		switch (numFrames) {
		case 3:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")));
			break;
		case 4:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")));
			break;
		case 5:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")),
									spl(getApplicationData().getFrame("/P5")));
			break;
		case 6:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")),
									spl(getApplicationData().getFrame("/P5")),
									spl(getApplicationData().getFrame("/P6")));
			break;
		case 7:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")),
									spl(getApplicationData().getFrame("/P5")),
									spl(getApplicationData().getFrame("/P6")),
									spl(getApplicationData().getFrame("/P7")));
			break;
		case 8:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")),
									spl(getApplicationData().getFrame("/P5")),
									spl(getApplicationData().getFrame("/P6")),
									spl(getApplicationData().getFrame("/P7")),
									spl(getApplicationData().getFrame("/P8")));
			break;
		case 9:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")),
									spl(getApplicationData().getFrame("/P5")),
									spl(getApplicationData().getFrame("/P6")),
									spl(getApplicationData().getFrame("/P7")),
									spl(getApplicationData().getFrame("/P8")),
									spl(getApplicationData().getFrame("/P9")));
			break;
		case 10:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")),
									spl(getApplicationData().getFrame("/P5")),
									spl(getApplicationData().getFrame("/P6")),
									spl(getApplicationData().getFrame("/P7")),
									spl(getApplicationData().getFrame("/P8")),
									spl(getApplicationData().getFrame("/P9")),
									spl(getApplicationData().getFrame("/P10")));
			break;
		case 11:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")),
									spl(getApplicationData().getFrame("/P5")),
									spl(getApplicationData().getFrame("/P6")),
									spl(getApplicationData().getFrame("/P7")),
									spl(getApplicationData().getFrame("/P8")),
									spl(getApplicationData().getFrame("/P9")),
									spl(getApplicationData().getFrame("/P10")),
									spl(getApplicationData().getFrame("/P11")));
			break;
		case 12:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")),
									spl(getApplicationData().getFrame("/P5")),
									spl(getApplicationData().getFrame("/P6")),
									spl(getApplicationData().getFrame("/P7")),
									spl(getApplicationData().getFrame("/P8")),
									spl(getApplicationData().getFrame("/P9")),
									spl(getApplicationData().getFrame("/P10")),
									spl(getApplicationData().getFrame("/P11")),
									spl(getApplicationData().getFrame("/P12")));
			break;
		case 13:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")),
									spl(getApplicationData().getFrame("/P5")),
									spl(getApplicationData().getFrame("/P6")),
									spl(getApplicationData().getFrame("/P7")),
									spl(getApplicationData().getFrame("/P8")),
									spl(getApplicationData().getFrame("/P9")),
									spl(getApplicationData().getFrame("/P10")),
									spl(getApplicationData().getFrame("/P11")),
									spl(getApplicationData().getFrame("/P12")),
									spl(getApplicationData().getFrame("/P13")));
			break;
		case 14:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")),
									spl(getApplicationData().getFrame("/P4")),
									spl(getApplicationData().getFrame("/P5")),
									spl(getApplicationData().getFrame("/P6")),
									spl(getApplicationData().getFrame("/P7")),
									spl(getApplicationData().getFrame("/P8")),
									spl(getApplicationData().getFrame("/P9")),
									spl(getApplicationData().getFrame("/P10")),
									spl(getApplicationData().getFrame("/P11")),
									spl(getApplicationData().getFrame("/P12")),
									spl(getApplicationData().getFrame("/P13")),
									spl(getApplicationData().getFrame("/P14")));
			break;
		default:
			splineMovement = new Spline(
									spl(getApplicationData().getFrame("/P1")),
									spl(getApplicationData().getFrame("/P2")),
									spl(getApplicationData().getFrame("/P3")));
			break;
		}
		
		
		lbr.move(ptp(getApplicationData().getFrame("/Front_upright")));
		lbr.move(splineMovement);
	}
}