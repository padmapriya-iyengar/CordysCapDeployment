package capdeployment.ant.exception;

import com.eibus.localization.IStringResource;
import com.eibus.localization.exception.LocalizableRuntimeException;

public class DeploymentException extends LocalizableRuntimeException  {
	private static final long serialVersionUID = 1787878978956587645L;

	public DeploymentException(IStringResource message, Object[] insertions) {
		super(message, insertions);
	}

	public DeploymentException(Throwable cause, IStringResource message,
			Object[] insertions) {
		super(cause, message, insertions);
	}

	public DeploymentException(Throwable cause) {
		super(cause);
	}
}
