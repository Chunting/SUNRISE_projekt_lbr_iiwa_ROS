package tests;


import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
import com.kuka.roboticsAPI.deviceModel.LBR;
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
public class TuetenGeben extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;

	@Override
	public void initialize() {
		// initialize your application here
	}

	@Override
	public void run() {
		while (true) {
			lbr.move(ptp(getApplicationData().getFrame("/TP2")));
			lbr.move(ptp(getApplicationData().getFrame("/TP3")));
			
			int again = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "Give Bag to peron?", "No", "Yes");
			if (again == 0) break;
			lbr.move(ptp(getApplicationData().getFrame("/TP2")));
			lbr.move(ptp(getApplicationData().getFrame("/TP4")));
			lbr.move(ptp(getApplicationData().getFrame("/TP1")));
			
			int back = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "Move back to you?", "No", "Yes");
			if (back == 0) break;
			lbr.move(ptp(getApplicationData().getFrame("/TP4")));
		}
	}
}