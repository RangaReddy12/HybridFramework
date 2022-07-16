package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilites.ExcelFileUtil;

public class DriverScript  extends AppUtil{
	String inputpath="D:\\Selenium_9oclockBatch\\Hybrid_Framework\\TestInput\\DataEngine.xlsx";
	String outputpath ="D:\\Selenium_9oclockBatch\\Hybrid_Framework\\TestOutput\\HybridResults.xlsx";
	ExtentReports report;
	ExtentTest test;
	String TCSheet ="TestCases";
	String TSSheet ="TestSteps";
	@Test
	public void stratTest()throws Throwable
	{
		report = new ExtentReports("./Reports/"+FunctionLibrary.generateDate()+"  "+"Hybrid.html");
		boolean res=false;
		String tcres="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in bot sheets
		int TCCount =xl.rowCount(TCSheet);
		int TSCount =xl.rowCount(TSSheet);
		//count no of cells
		int tccells=xl.cellCount(TCSheet);
		int tscell =xl.cellCount(TSSheet);
		Reporter.log(TCCount+"     "+TSCount+"     "+tccells+"      "+tscell,true);
		//iterate all rows in TCSheet
		for(int i=1;i<=TCCount;i++)
		{
				//read odule name 
			String ModuleName =xl.getCellData(TCSheet, i, 1);
			test= report.startTest(ModuleName);
			//read module status cell
			String Modulestatus =xl.getCellData(TCSheet, i, 2);
			if(Modulestatus.equalsIgnoreCase("Y"))
			{
				String tcid =xl.getCellData(TCSheet, i, 0);
				//iterate all rows in TSSheet
				for(int j=1;j<=TSCount;j++)
				{
					//read description cell
					String Description =xl.getCellData(TSSheet, j, 2);
					//read tsid cell
					String tsid =xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						//read keyword cell
						String keyword =xl.getCellData(TSSheet, j, 4);
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							res =FunctionLibrary.verifyLogin(para1, para2);
							test.log(LogStatus.INFO, Description);
						}
						else if(keyword.equalsIgnoreCase("BranchCreation"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							String para3 =xl.getCellData(TSSheet, j, 7);
							String para4 =xl.getCellData(TSSheet, j, 8);
							String para5 =xl.getCellData(TSSheet, j, 9);
							String para6 =xl.getCellData(TSSheet, j, 10);
							String para7 =xl.getCellData(TSSheet, j, 11);
							String para8 =xl.getCellData(TSSheet, j, 12);
							String para9 =xl.getCellData(TSSheet, j, 13);
							FunctionLibrary.clickBranches();
							res =FunctionLibrary.verifyBranchCreation(para1, para2, para3, para4, para5, para6, para7, para8, para9);
							test.log(LogStatus.INFO, Description);
						}
						else if(keyword.equalsIgnoreCase("BranchUpdate"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							String para5 =xl.getCellData(TSSheet, j, 9);
							FunctionLibrary.clickBranches();
							res=FunctionLibrary.verifyBranchUpdate(para1, para2, para5);
							test.log(LogStatus.INFO, Description);
						}
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							res =FunctionLibrary.verifyLogout();
							test.log(LogStatus.INFO, Description);
						}
						String tsres="";
						if(res)
						{
							//write as pass into TSSheet
							tsres="Pass";
							xl.setCellData(TSSheet, j, 3, tsres, outputpath);
							test.log(LogStatus.PASS, Description);
						}
						else
						{
							//write as Fail into TSSheet
							tsres="Fail";
							xl.setCellData(TSSheet, j, 3, tsres, outputpath);
							test.log(LogStatus.FAIL, Description);
						}
						tcres=tsres;
					}
					}
				//write as tcres into TCSheet
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
				report.endTest(test);
				report.flush();
				}
			else
			{
				//write as blocked into status cell which tc is flag N
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
		}
	}

}











