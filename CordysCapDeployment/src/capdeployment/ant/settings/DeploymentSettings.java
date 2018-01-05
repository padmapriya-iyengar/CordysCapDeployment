package capdeployment.ant.settings;

import capdeployment.ant.exception.DeploymentException;
import capdeployment.ant.messages.DeploymentMessages;

import com.cordys.deployment.ant.settings.StandardSettings;

public class DeploymentSettings  extends StandardSettings {
	public String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public boolean isValid() {
		if (null == filePath || ("").equals(filePath))
			throw new DeploymentException(
					DeploymentMessages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Service Container" });
		return true;
	}

}
