package tests;


import javax.inject.Inject;

import com.kuka.generated.ioAccess.MediaFlangeIOGroup;
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
public class EndsetupUebergabe extends RoboticsAPIApplication {
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
		lbr.move(ptp(getApplicationData().getFrame("/Lev_start")));
		mfTouch.setSwitchOffX3Voltage(false);
		int init = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "Startbedingung: Wo soll Probe levitieren?", "Links", "Rechts");
		if(init==0){
			mfTouch.setOutputX3Pin1(true);
		}
		if(init==1){
			mfTouch.setOutputX3Pin11(true);
		}
		
		while (true) {
		int grip = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "Aus welchem Levitator greifen?", "Links", "Rechts","Genug davon!");
		
		if(grip==0)
		{
			lbr.move(ptp(getApplicationData().getFrame("/LevLAO45")));
			lbr.move(lin(getApplicationData().getFrame("/LevLA45")));
			lbr.move(lin(getApplicationData().getFrame("/LevL45")));
			mfTouch.setOutputX3Pin2(true);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			}
			mfTouch.setOutputX3Pin11(false);
			lbr.move(lin(getApplicationData().getFrame("/LevLA45")));
			lbr.move(lin(getApplicationData().getFrame("/LevLAO45")));
			lbr.move(ptp(getApplicationData().getFrame("/Lev_start")));
		}
		
		if(grip==1)
		{
			lbr.move(ptp(getApplicationData().getFrame("/LevRAO45")));
			lbr.move(lin(getApplicationData().getFrame("/LevRA45")));
			lbr.move(lin(getApplicationData().getFrame("/LevR45")));
			mfTouch.setOutputX3Pin2(true);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			}
			mfTouch.setOutputX3Pin1(false);
			lbr.move(lin(getApplicationData().getFrame("/LevRA45")));
			lbr.move(lin(getApplicationData().getFrame("/LevRAO45")));
			lbr.move(ptp(getApplicationData().getFrame("/Lev_start")));
		}
		
		if(grip==2) break;
		
		int put = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "In welchen Levitator uebergeben?", "Links", "Rechts","Genug davon!");
		if(put==0){
			lbr.move(ptp(getApplicationData().getFrame("/LevLAO45")));
			lbr.move(lin(getApplicationData().getFrame("/LevLA45")));
			lbr.move(lin(getApplicationData().getFrame("/LevL45")));
			mfTouch.setOutputX3Pin11(true);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			}
			mfTouch.setOutputX3Pin2(false);
			lbr.move(lin(getApplicationData().getFrame("/LevLA45")));
			lbr.move(lin(getApplicationData().getFrame("/LevLAO45")));
			lbr.move(ptp(getApplicationData().getFrame("/Lev_start")));
			}
		
		if(put==1){
			lbr.move(ptp(getApplicationData().getFrame("/LevRAO45")));
			lbr.move(lin(getApplicationData().getFrame("/LevRA45")));
			lbr.move(lin(getApplicationData().getFrame("/LevR45")));
			mfTouch.setOutputX3Pin1(true);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			}
			mfTouch.setOutputX3Pin2(false);
			lbr.move(lin(getApplicationData().getFrame("/LevRA45")));
			lbr.move(lin(getApplicationData().getFrame("/LevRAO45")));
			lbr.move(ptp(getApplicationData().getFrame("/Lev_start")));
			}
		
		if(put==2)break;
	}
}
}

		