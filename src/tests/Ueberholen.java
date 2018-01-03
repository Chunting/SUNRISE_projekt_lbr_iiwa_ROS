package tests;


import javax.inject.Inject;

import com.kuka.generated.ioAccess.MediaFlangeIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;

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
public class Ueberholen extends RoboticsAPIApplication {
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
		lbr.move(ptp(getApplicationData().getFrame("/P16")));
		mfTouch.setSwitchOffX3Voltage(true);
		lbr.move(lin(getApplicationData().getFrame("/P17"))
				.setCartAcceleration(20000.0));
		mfTouch.setSwitchOffX3Voltage(false);
	}
}