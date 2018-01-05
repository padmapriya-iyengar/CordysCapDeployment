package capdeployment.ant.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import capdeployment.ant.exception.DeploymentException;
import capdeployment.ant.messages.DeploymentMessages;

import com.cordys.deployment.ant.tasks.BaseTask;

public class ExecuteTask {
	public static String buildFilePath = "E:\\OpenText\\Process Platform\\defaultInst\\antScripts\\CordysUtilities\\build.xml";

	public static boolean deployPackage(ArrayList<String> packageDetails) {
		boolean deploymentStatus = false;
		int counter = 0;
		int packageCount = 0;
		ArrayList<String> properties = null;
		try {
			packageCount = packageDetails.size();
			properties = new ArrayList<String>();

			for (counter = 0; counter < packageCount; counter++) {
				properties.add("name-"
						+ packageDetails.get(counter).split("$$")[0]);
				properties.add("server-"
						+ packageDetails.get(counter).split("$$")[1]);
				executeAntTask(buildFilePath, "deployPackageUsingFile",
						properties);
			}
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while deploying the packages");
			throw new DeploymentException(
					DeploymentMessages.DEPLOY_PACKAGE_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		return deploymentStatus;

	}

	public static boolean attachInterface(ArrayList<String> interfaceDetails) {
		boolean deploymentStatus = false;
		int counter = 0;
		int interfaceCount = 0;
		ArrayList<String> properties = null;
		try {
			interfaceCount = interfaceDetails.size();
			properties = new ArrayList<String>();

			for (counter = 0; counter < interfaceCount; counter++) {
				properties.add("ServiceGroup-"
						+ interfaceDetails.get(counter).split("$$")[0]);
				properties.add("Organization-"
						+ interfaceDetails.get(counter).split("$$")[1]);
				properties.add("UpdateValue-"
						+ interfaceDetails.get(counter).split("$$")[2]);
				if (null != interfaceDetails.get(counter).split("$$")[3]
						&& !("").equals(interfaceDetails.get(counter).split(
								"$$")[3])
						&& interfaceDetails.get(counter).split("$$")[3]
								.equalsIgnoreCase("DELETE"))
					executeAntTask(buildFilePath, "GroupConfigDelete",
							properties);
				else
					executeAntTask(buildFilePath, "GroupConfigAdd", properties);
			}
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while attaching interfaces");
			throw new DeploymentException(
					DeploymentMessages.ATTACH_INTERFACE_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		return deploymentStatus;

	}

	public static boolean updateJRE(ArrayList<String> jreDetails) {
		boolean deploymentStatus = false;
		int counter = 0;
		int jreCount = 0;
		ArrayList<String> properties = null;
		try {
			jreCount = jreDetails.size();
			properties = new ArrayList<String>();

			for (counter = 0; counter < jreCount; counter++) {
				properties.add("ServiceGroup-"
						+ jreDetails.get(counter).split("$$")[0]);
				properties.add("ServiceContainer-"
						+ jreDetails.get(counter).split("$$")[1]);
				properties.add("Organization-"
						+ jreDetails.get(counter).split("$$")[2]);
				properties.add("UpdateValue-"
						+ jreDetails.get(counter).split("$$")[3]);
				executeAntTask(buildFilePath, "ContainerConfigUpdate",
						properties);
			}
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while updating JRE details");
			throw new DeploymentException(
					DeploymentMessages.UPDATE_JRE_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		return deploymentStatus;

	}

	public static boolean assignRole(ArrayList<String> roleDetails) {
		boolean deploymentStatus = false;
		int counter = 0;
		int roleCount = 0;
		ArrayList<String> properties = null;
		try {
			roleCount = roleDetails.size();
			properties = new ArrayList<String>();

			for (counter = 0; counter < roleCount; counter++) {
				properties.add("User-"
						+ roleDetails.get(counter).split("$$")[0]);
				properties.add("RoleName-"
						+ roleDetails.get(counter).split("$$")[1]);
				properties.add("Organization-"
						+ roleDetails.get(counter).split("$$")[2]);
				properties.add("RolePackage-"
						+ roleDetails.get(counter).split("$$")[3]);
				executeAntTask(buildFilePath, "RoleAssignment", properties);
			}
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while assigning roles to user");
			throw new DeploymentException(
					DeploymentMessages.ATTACH_ROLE_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		return deploymentStatus;

	}

	public static boolean restartContainer(ArrayList<String> containerDetails) {
		boolean deploymentStatus = false;
		int counter = 0;
		int containerCount = 0;
		ArrayList<String> properties = null;
		try {
			containerCount = containerDetails.size();
			properties = new ArrayList<String>();

			for (counter = 0; counter < containerCount; counter++) {
				properties.add("ServiceGroup-"
						+ containerDetails.get(counter).split("$$")[0]);
				properties.add("ServiceContainer-"
						+ containerDetails.get(counter).split("$$")[1]);
				properties.add("Organization-"
						+ containerDetails.get(counter).split("$$")[2]);
				properties.add("Action-"
						+ containerDetails.get(counter).split("$$")[3]);
				executeAntTask(buildFilePath, "ContainerAction", properties);
			}
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while acting on the containers");
			throw new DeploymentException(
					DeploymentMessages.CONTAINER_ACTION_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		return deploymentStatus;
	}

	public static boolean executeAntTask(String buildXmlFileFullPath) {
		return executeAntTask(buildXmlFileFullPath, null, null);
	}

	public static boolean executeAntTask(String buildXmlFileFullPath,
			String target, ArrayList<String> propertyValue) {
		boolean success = false;
		DefaultLogger consoleLogger = null;
		Project project = null;
		File buildFile = null;
		BaseTask.printMessage("Before executing the task");
		try {
			consoleLogger = getConsoleLogger();
			project = new Project();
			buildFile = new File(buildXmlFileFullPath);
			project.setUserProperty("ant.file", buildFile.getAbsolutePath());
			for (int propertyCount = 0; propertyCount < propertyValue.size(); propertyCount++) {
				project.setProperty(
						propertyValue.get(propertyCount).split("-")[0],
						propertyValue.get(propertyCount).split("-")[1]);
			}
			BaseTask.printMessage("Service group is:"+project.getProperty("ServiceGroup"));
			BaseTask.printMessage("Service container is:"+project.getProperty("ServiceContainer"));
			BaseTask.printMessage("Organization is:"+project.getProperty("Organization"));
			BaseTask.printMessage("Action is:"+project.getProperty("Action"));
			project.addBuildListener(consoleLogger);
			project.fireBuildStarted();
			project.init();
			ProjectHelper projectHelper = ProjectHelper.getProjectHelper();
			project.addReference("ant.projectHelper", projectHelper);
			projectHelper.parse(project, buildFile);
			String targetToExecute = (target != null && target.trim().length() > 0) ? target
					.trim() : project.getDefaultTarget();
			project.executeTarget(targetToExecute);
			project.fireBuildFinished(null);
			success = true;
		} catch (BuildException buildException) {
			project.fireBuildFinished(buildException);
			throw new RuntimeException(
					"!!! Exception occured while executing the ANT script !!!",
					buildException);
		}
		BaseTask.printMessage("After executing the task");
		return success;
	}

	private static DefaultLogger getConsoleLogger() {
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);

		return consoleLogger;
	}

	public static boolean capDeployment(
			Map<String, ArrayList<String>> packageDetails) {
		//boolean status = false;
		boolean deploymentResult = false;
		try {
			BaseTask.printMessage("Before executing all the tasks");
			deploymentResult = restartContainer(packageDetails
					.get("ContainerDetails"));
			//deploymentResult = true;
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
		}
		BaseTask.printMessage("After executing all the tasks");
		return deploymentResult;

	}

}
