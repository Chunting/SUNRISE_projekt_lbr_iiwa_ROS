package tests;


import javax.inject.Inject;

import com.kuka.generated.ioAccess.MediaFlangeIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.motionModel.CartesianPTP;
import com.kuka.roboticsAPI.motionModel.LIN;

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
public class NeueApp extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;
	
	@Inject 
	private MediaFlangeIOGroup mfTouch;

	@Override
	public void initialize() {
		// initialize your application here
	}

	@Override
	public void run() {
		// your application execution starts here
		lbr.move(ptp(getApplicationData().getFrame("/Pos1_approach")));
		mfTouch.setLEDBlue(true);
		
		CartesianPTP bewegung = new CartesianPTP(getApplicationData().getFrame("/Pos1_grip"));
		bewegung.setJointVelocityRel(0.5);
		lbr.move(bewegung);
		mfTouch.setLEDBlue(false);
		
		LIN linearMovement = lin(getApplicationData().getFrame("/Pos2_grip"));
		linearMovement.setCartVelocity(30);
		lbr.move(linearMovement);
	}
}