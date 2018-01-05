package capdeployment.ant.messages;

import com.eibus.localization.IStringResource;
import com.eibus.tools.internal.MessageBundleGenerator;
import com.eibus.tools.internal.MessageText;

public class DeploymentMessages {
	private static int s_fieldIndexer = 0;

	private static IStringResource getMessage() {
		return MessageBundleGenerator.getMessage(DeploymentMessages.class,
				s_fieldIndexer++);
	}

	@MessageText("An unexpected error occurred: ''{0}''")
	public static final IStringResource UNEXPECTED_EXCEPTION = getMessage();

	@MessageText("No value found for property ''{0}''")
	public static final IStringResource NO_VALUE_FOR_PROPERTY = getMessage();

	@MessageText("No file exists ''{0}''")
	public static final IStringResource NO_FILE_EXISTS = getMessage();

	@MessageText("Error while reading the file ''{0}''")
	public static final IStringResource FILE_READING_ERROR = getMessage();

	@MessageText("Sheet doesn't exist ''{0}''")
	public static final IStringResource NO_SHEET = getMessage();

	@MessageText("No data in the sheet ''{0}'' ")
	public static final IStringResource NO_DATA = getMessage();

	@MessageText("Exception while deploying package ''{0}'' ")
	public static final IStringResource DEPLOY_PACKAGE_EXCEPTION = getMessage();

	@MessageText("Exception while attaching interfaces ''{0}'' ")
	public static final IStringResource ATTACH_INTERFACE_EXCEPTION = getMessage();

	@MessageText("Exception while updating JRE Path ''{0}'' ")
	public static final IStringResource UPDATE_JRE_EXCEPTION = getMessage();

	@MessageText("Exception while assigning role to user ''{0}'' ")
	public static final IStringResource ATTACH_ROLE_EXCEPTION = getMessage();

	@MessageText("Exception while performing action on the containers ''{0}'' ")
	public static final IStringResource CONTAINER_ACTION_EXCEPTION = getMessage();
}
