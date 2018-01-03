package tests;


import javax.inject.Inject;

import com.kuka.generated.ioAccess.MediaFlangeIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
import static com.kuka.roboticsAPI.motionModel.HRCMotions.handGuiding;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.AbstractFrame;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;

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
public class FourFramesAndLED extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;
	@Inject 
	private MediaFlangeIOGroup ledBlau;
	
//	AbstractFrame frameUnten = new Frame(-561.88, 554.05, 64.18, -111.59, 0.31, -177.22);
//	AbstractFrame frameLinks = new Frame(-462.40, -372.49, 199.23, -139.66, -20.18, 99.20);
//	AbstractFrame frameOben = new Frame(494.93, -490.71, 542.88, 42.44, -0.09, 3.00);
//	AbstractFrame frameRechts = new Frame(435.71, 487.20, 744.9, 33.14, 69.20, -92.09);
//	AbstractFrame frame1 = new Frame(607.32, 64.17, 645.33, -19.71, -18.32, -20.27);
//	AbstractFrame frame2 = new Frame(-200.47, -690.25, 214.26, -158.05, -39.77, -106.07);
//	AbstractFrame tFrameUnten = new ObjectFrame("fUnten", getApplicationData().getFrame("/WorldFrame"), frameUnten);
	
	@Override
	public void initialize() {
	}

	@Override
	public void run() {
		lbr.move(ptpHome());
		ledBlau.setLEDBlue(true);
		lbr.move(ptp(getApplicationData().getFrame("/P1")));
		ledBlau.setLEDBlue(false);
		lbr.move(ptpHome());
		ledBlau.setLEDBlue(true);
		lbr.move(ptp(getApplicationData().getFrame("/P2")));
		ledBlau.setLEDBlue(false);
		lbr.move(ptpHome());
		ledBlau.setLEDBlue(true);
		lbr.move(ptp(getApplicationData().getFrame("/P3")));
		ledBlau.setLEDBlue(false);
		lbr.move(ptpHome());
		ledBlau.setLEDBlue(true);
		lbr.move(ptp(getApplicationData().getFrame("/P4")));
		ledBlau.setLEDBlue(false);
		//lbr.setESMState("1");
		//lbr.setESMState("2");
		//lbr.move(handGuiding());  
		//lbr.setESMState("1");
		//ObjectFrame f1 = getApplicationData().getFrame("/P1");
		//getLogger().info("Frame1 ID:\n");
		//getLogger().info(frameUnten.getId().toString());
	}
}