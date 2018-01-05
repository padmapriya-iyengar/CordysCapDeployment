package capdeployment.ant.tasks;

import capdeployment.ant.handler.ReadExcel;
import capdeployment.ant.settings.DeploymentSettings;

import com.cordys.deployment.ant.tasks.BaseTask;

public class DeploymentTask extends BaseTask<DeploymentSettings, ReadExcel> {

	public DeploymentTask() {
		super(new DeploymentSettings());
		// TODO Auto-generated constructor stub
	}

	public void setFilePath(String filePath) {
		((DeploymentSettings) getSettings()).setFilePath(filePath);
	}

	@Override
	public ReadExcel createHandler(String paramString,
			DeploymentSettings paramSettingsT) {
		// TODO Auto-generated method stub
		return new ReadExcel("cn=cordys,cn=DevInst2,o=alahli.com",
				(DeploymentSettings) getSettings());
	}

}
