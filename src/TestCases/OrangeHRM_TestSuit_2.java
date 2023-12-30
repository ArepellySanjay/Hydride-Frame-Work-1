package TestCases;

import org.testng.annotations.Test;

import AppUtils.UtilsDemo;
import AppUtils.XL_Utils_Next;
import OrangeHRM_Libreary.EmpRegistration;
import OrangeHRM_Libreary.Login;

public class OrangeHRM_TestSuit_2 extends UtilsDemo
{
	
	
	String tcfile = "E:\\selinium\\myproject\\Z_hybrid_frameWork\\TestCase.files\\Hybride.Testing.xlsx";
	String tcsheet = "TestCases";
	String tssheet = "TestSteps";
	String xofile = "E:\\selinium\\myproject\\Z_hybrid_frameWork\\TestCase.files\\Hybride.Testing2.xlsx" ;
	
	
     @Test
	public void CheckOrangeHM() throws Throwable 
	{
		
       int tccount =XL_Utils_Next.getRowcount(tcfile, tcsheet);
		int tscount =  XL_Utils_Next.getRowcount(tcfile, tssheet);
		
		
		String tcid,tcexeflag;
		String tstcid,Keyword;
		
		Login lp = new Login();
	    String uid,pwd;
	    
	    boolean res = false;    
	    String stepresult,tcresult;
	    
	    EmpRegistration emp = new EmpRegistration();
	    String fname,lname;
	    
		
		for(int i = 1;i<=tccount;i++)
		{
		
	     tcid =	XL_Utils_Next.getStringData(tcfile, tcsheet, i, 0);
		 tcexeflag = XL_Utils_Next.getStringData(tcfile, tcsheet, i, 2);       //to selecting
		 
		 if(tcexeflag.equalsIgnoreCase("y"))                                 //to verify user select or not for execute
		 {
			 //System.out.println(tcid+"is select to execute");
			 
			 for(int j=1;j<=tscount;j++)
			 {
				tstcid = XL_Utils_Next.getStringData(tcfile, tssheet, j, 0);
				if(tstcid.equalsIgnoreCase(tcid))                                  //both are matching
				{
					
					Keyword = XL_Utils_Next.getStringData(tcfile, tssheet, j, 4);   //read the keyword  data
					switch(Keyword.toLowerCase())
					{
					case "adminlogin":
						uid = XL_Utils_Next.getStringData(tcfile, tssheet, j, 5);
					    pwd = XL_Utils_Next.getStringData(tcfile, tssheet, j, 6);
					    lp.login(uid, pwd);
					    res = lp.isdisplayed();
					     break;
					case "logout":
					  res =	lp.logout();              //  logout : what is expected
						     break;                        //login page should be displayed					
					case "invalidlogin":
						uid = XL_Utils_Next.getStringData(tcfile, tssheet, j, 5);
						pwd = XL_Utils_Next.getStringData(tcfile, tssheet, j, 6);
						lp.login(uid, pwd);
				     	res =	lp.isErrMsgDisplayed();
					    break;
					case "empreg":
					
						fname = XL_Utils_Next.getStringData(tcfile, tssheet, j, 5);
				     	lname = XL_Utils_Next.getStringData(tcfile, tssheet, j, 6);
				     	res =	emp.addemployee(fname, lname);
				     	break;
				     	
					}
					    
					//code to step result
					  
					if(res)
					{
						stepresult = "pass";
						XL_Utils_Next.getsetData(tcfile, tssheet, j, 3, stepresult, xofile);
						XL_Utils_Next.FillGreenColour(tcfile, tssheet, j, 3, xofile);
					}else
					{
						stepresult = "fail";
						XL_Utils_Next.getsetData(tcfile, tssheet, j, 3, stepresult, xofile);
						XL_Utils_Next.FillRedColour(tcfile, tssheet, j, 3, xofile);
						
					}
					
					//code to update test case result
					
					tcresult = XL_Utils_Next.getStringData(tcfile, tcsheet, i, 3);   // reading this value empty
					if(!tcresult.equalsIgnoreCase("fail"))                             //  if it is not fail then update the result
					{
						XL_Utils_Next.getsetData(tcfile, tcsheet, i, 3, stepresult, xofile);    //update the  test case summary with step summary     
		                XL_Utils_Next.FillGreenColour(tcfile, tcsheet, i, 3, xofile);			
					}                                                                           //pass update pass
					
					
					
					/*tcresult = XL_Utils_Next.getStringData(tcfile, tcsheet, i, 3);  // after updateing here again i am reading tese case result
					if(tcresult.equalsIgnoreCase("pass"))
					{
						XL_Utils_Next.FillGreenColour(tcfile, tcsheet, i, 3, xofile);     //for colour
					}else
					{
						XL_Utils_Next.FillRedColour(tcfile, tcsheet, i, 3, xofile);
					}*/
				}
				 
			 }
			 
			 
			 
			 
			 
			 
		 }else
		 {
			 XL_Utils_Next.getsetData(tcfile, tcsheet, i, 3, "Blocked", xofile);
			 XL_Utils_Next.FillRedColour(tcfile, tcsheet, i, 3, xofile);
		 }
		
		
		 
		 
		 
		}
		
		
		
		
		
       
	}
	
}
















