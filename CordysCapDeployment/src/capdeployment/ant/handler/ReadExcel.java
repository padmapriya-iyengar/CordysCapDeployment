package capdeployment.ant.handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.ant.BuildException;

import capdeployment.ant.exception.DeploymentException;
import capdeployment.ant.messages.DeploymentMessages;
import capdeployment.ant.settings.DeploymentSettings;

import com.cordys.deployment.ant.handler.BaseHandler;
import com.cordys.deployment.ant.tasks.BaseTask;

public class ReadExcel  extends BaseHandler {
	public static String filePath = null;

	public ReadExcel(String ldapRoot, DeploymentSettings settings) {
		super(ldapRoot);
		// TODO Auto-generated constructor stub
		filePath = settings.getFilePath();
		loadFile();
		initialize();
	}

	public static XSSFWorkbook wb = null;
	public static Map<String, ArrayList<String>> excelInfo = null;

	public static void loadFile() {
		FileInputStream fis = null;
		try {
			if (null != filePath && !("").equals(filePath))
				fis = new FileInputStream(filePath);
			else
				throw new DeploymentException(
						DeploymentMessages.NO_FILE_EXISTS,
						new Object[] { filePath });
			if (null != fis)
				wb = new XSSFWorkbook(fis);
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured in the static block");
			throw new DeploymentException(
					DeploymentMessages.UNEXPECTED_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
	}

	@SuppressWarnings("null")
	public static ArrayList<String> readPackageDetails(XSSFWorkbook wb) {
		ArrayList<String> packages = null;
		XSSFSheet sheet = null;
		Iterator<Row> rowItr = null;
		XSSFRow row = null;
		Iterator<Cell> cellItr = null;
		String cellValue = "";
		int rowValue = 9;
		try {
			BaseTask.printMessage("Before reading the package details");
			packages = new ArrayList<String>();
			sheet = wb.getSheetAt(0);
			if (null != sheet) {
				rowItr = sheet.rowIterator();
				while (rowItr.hasNext()) {
					row = sheet.getRow(rowValue);
					if (null != row) {
						cellItr = row.cellIterator();
						while (cellItr.hasNext()) {
							cellValue = String.valueOf(cellItr.next());
							if (null != cellValue && !("").equals(cellValue))
								packages.add(cellValue);
						}
						rowValue++;
					} else
						break;
				}
			} else
				throw new DeploymentException(DeploymentMessages.NO_SHEET,
						new Object[] { sheet.getSheetName() });
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while reading the package details");
			throw new DeploymentException(
					DeploymentMessages.UNEXPECTED_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		BaseTask.printMessage("After reading the package details");
		return packages;
	}

	@SuppressWarnings("null")
	public static ArrayList<String> readInterfaceDetails(XSSFWorkbook wb) {
		ArrayList<String> interfaces = null;
		XSSFSheet sheet = null;
		Iterator<Row> rowItr = null;
		XSSFRow row = null;
		Iterator<Cell> cellItr = null;
		String cellValue = "";
		int rowValue = 9;
		try {
			BaseTask.printMessage("Before reading the interface details");
			interfaces = new ArrayList<String>();
			sheet = wb.getSheetAt(1);
			if (null != sheet) {
				rowItr = sheet.rowIterator();
				while (rowItr.hasNext()) {
					row = sheet.getRow(rowValue);
					if (null != row) {
						cellItr = row.cellIterator();
						while (cellItr.hasNext()) {
							cellValue += String.valueOf(cellItr.next()) + "$$";
						}
						if (null != cellValue && !("").equals(cellValue)) {
							cellValue = cellValue.substring(0,
									cellValue.length() - 2);
							interfaces.add(cellValue);
						}
						cellValue = "";
						rowValue++;
					} else
						break;
				}
			} else
				throw new DeploymentException(DeploymentMessages.NO_SHEET,
						new Object[] { sheet.getSheetName() });
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while reading the interface details");
			throw new DeploymentException(
					DeploymentMessages.UNEXPECTED_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		BaseTask.printMessage("After reading the interface details");
		return interfaces;
	}

	@SuppressWarnings("null")
	public static ArrayList<String> readJREDetails(XSSFWorkbook wb) {
		ArrayList<String> jrePath = null;
		XSSFSheet sheet = null;
		Iterator<Row> rowItr = null;
		XSSFRow row = null;
		Iterator<Cell> cellItr = null;
		String cellValue = "";
		int rowValue = 9;
		try {
			BaseTask.printMessage("Before reading the jre details");
			jrePath = new ArrayList<String>();
			sheet = wb.getSheetAt(2);
			if (null != sheet) {
				rowItr = sheet.rowIterator();
				while (rowItr.hasNext()) {
					row = sheet.getRow(rowValue);
					if (null != row) {
						cellItr = row.cellIterator();
						while (cellItr.hasNext()) {
							cellValue += String.valueOf(cellItr.next()) + "$$";
						}
						if (null != cellValue && !("").equals(cellValue)) {
							cellValue = cellValue.substring(0,
									cellValue.length() - 2);
							jrePath.add(cellValue);
						}
						cellValue = "";
						rowValue++;
					} else
						break;
				}
			} else
				throw new DeploymentException(DeploymentMessages.NO_SHEET,
						new Object[] { sheet.getSheetName() });
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while reading the jre details");
			throw new DeploymentException(
					DeploymentMessages.UNEXPECTED_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		BaseTask.printMessage("After reading the jre details");
		return jrePath;
	}

	@SuppressWarnings("null")
	public static ArrayList<String> readRoleDetails(XSSFWorkbook wb) {
		ArrayList<String> roles = null;
		XSSFSheet sheet = null;
		Iterator<Row> rowItr = null;
		XSSFRow row = null;
		Iterator<Cell> cellItr = null;
		String cellValue = "";
		int rowValue = 9;
		try {
			BaseTask.printMessage("Before reading the role details");
			roles = new ArrayList<String>();
			sheet = wb.getSheetAt(3);
			if (null != sheet) {
				rowItr = sheet.rowIterator();
				while (rowItr.hasNext()) {
					row = sheet.getRow(rowValue);
					if (null != row) {
						cellItr = row.cellIterator();
						while (cellItr.hasNext()) {
							cellValue += String.valueOf(cellItr.next()) + "$$";
						}
						if (null != cellValue && !("").equals(cellValue)) {
							cellValue = cellValue.substring(0,
									cellValue.length() - 2);
							roles.add(cellValue);
						}
						cellValue = "";
						rowValue++;
					} else
						break;
				}
			} else
				throw new DeploymentException(DeploymentMessages.NO_SHEET,
						new Object[] { sheet.getSheetName() });
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while reading the role details");
			throw new DeploymentException(
					DeploymentMessages.UNEXPECTED_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		BaseTask.printMessage("After reading the role details");
		return roles;
	}

	@SuppressWarnings("null")
	public static ArrayList<String> readContainerDetails(XSSFWorkbook wb) {
		ArrayList<String> containers = null;
		XSSFSheet sheet = null;
		Iterator<Row> rowItr = null;
		XSSFRow row = null;
		Iterator<Cell> cellItr = null;
		String cellValue = "";
		int rowValue = 9;
		try {
			BaseTask.printMessage("Before reading the container details");
			containers = new ArrayList<String>();
			sheet = wb.getSheetAt(4);
			if (null != sheet) {
				rowItr = sheet.rowIterator();
				while (rowItr.hasNext()) {
					row = sheet.getRow(rowValue);
					if (null != row) {
						cellItr = row.cellIterator();
						while (cellItr.hasNext()) {
							cellValue = String.valueOf(cellItr.next());
							if (null != cellValue && !("").equals(cellValue))
								containers.add(cellValue);
						}
						rowValue++;
					} else
						break;
				}
			} else
				throw new DeploymentException(DeploymentMessages.NO_SHEET,
						new Object[] { sheet.getSheetName() });
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while reading the container details");
			throw new DeploymentException(
					DeploymentMessages.UNEXPECTED_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		BaseTask.printMessage("After reading the container details");
		return containers;
	}

	public static void readSheets() throws IOException {
		try {
			BaseTask.printMessage("Before reading the sheets");
			excelInfo = new HashMap<String, ArrayList<String>>();
			/*excelInfo.put("PackageDetails", readPackageDetails(wb));
			excelInfo.put("InterfaceDetails", readInterfaceDetails(wb));
			excelInfo.put("JREDetails", readJREDetails(wb));
			excelInfo.put("RoleDetails", readRoleDetails(wb));*/
			excelInfo.put("ContainerDetails", readContainerDetails(wb));
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while reading the reading the sheets");
			throw new DeploymentException(
					DeploymentMessages.UNEXPECTED_EXCEPTION,
					new Object[] { exp.getMessage() });
		} finally {
			wb.close();
		}
		BaseTask.printMessage("After reading the sheets");
	}

	public void execute() throws BuildException {
		boolean status = false;
		try {
			BaseTask.printMessage("Before cap deployment");
			readSheets();
			BaseTask.printMessage("Before deploying the cap");
			status = ExecuteTask.capDeployment(excelInfo);
			if (status)
				BaseTask.printMessage("Successfully deployed the cap and performed the post deployment activities");
			else
				BaseTask.printMessage("Could not complete the deployment");
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while performing the deployment");
			throw new DeploymentException(
					DeploymentMessages.UNEXPECTED_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		BaseTask.printMessage("After cap deployment");
	}

}
