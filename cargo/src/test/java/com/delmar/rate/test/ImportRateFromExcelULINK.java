package com.delmar.rate.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.base.model.City;
import com.delmar.base.model.Port;
import com.delmar.base.service.CityService;
import com.delmar.base.service.PortService;
import com.delmar.rate.busmodel.RateDetailBus;
import com.delmar.rate.busmodel.RateMasterBus;
import com.delmar.rate.model.Ratedetail;
import com.delmar.rate.service.RatedetailService;
import com.delmar.rate.service.RatemasterService;

/**
 * @author Charles Luo luos@delmarchina.com
 * @version V2.0 2015年6月15日 下午4:45:26 类说明
 */
 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ImportRateFromExcelULINK {

	private static String fileName = "E:/MyDesktop/Foxconn Rate/汇通的价格/成都报价单3.xlsx";
	private static String wrongFileName="E:/MyDesktop/Foxconn Rate/汇通的价格/WrongData.xlsx";
	private static String sheetName = "报价表";
	private static int fromi = 11;
	private static int toi = 400;

	 @Autowired
	 private PortService portService;
	 
	 @Autowired
	 private CityService cityService;
	 
	 
	 @Autowired
	 private RatemasterService ratemasterService;	 
	 
	 @Autowired
	 private RatedetailService ratedetailService;	 

	public static void main(String[] args) {
		testImport();
	}
	
    @Test
	public void importRate() {
		File file = new File(fileName);
		XSSFWorkbook wrongworkbook=null;
		//Integer polId=696;  //深圳
		//Integer polId=1066;   //上海
		//Integer polId=1084;  //苏州市
		//Integer polId=1127;  //无锡
		Integer polId=706;  //成都
		
		List<RateMasterBus> rateMasterList=new ArrayList<RateMasterBus>();		
		try {
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
			wrongworkbook = new XSSFWorkbook();			

			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			XSSFSheet SheetWrong = wrongworkbook.createSheet("WrongData");

			// int rowstart = xssfSheet.getFirstRowNum();
			// int rowEnd = xssfSheet.getLastRowNum();

			int rowstart = fromi;
			int rowEnd = toi;
			
			int cellStart = 1;
			int cellEnd = 20;
			
			int wrongRow=1;
			
			String province="";
			Integer provinceCityId=0;
			String city="";
			Integer cityId;
			Integer portId;
			
			
			
			for (int i = rowstart; i <= rowEnd; i++) {
				XSSFRow row = xssfSheet.getRow(i);
				if (null == row)
					continue;
				
				XSSFCell cell = row.getCell(1);
				
				if (!cell.getStringCellValue().equals(""))
				{
					province=cell.getStringCellValue().trim();
					Map<String,String> param=new HashMap<String,String>();
					
					param.put("accessString", " cname like '%"+province+"%' and levelint=2");
					List<City> provinceList=cityService.selectByExample(param);
					if (provinceList.size()==1)
						provinceCityId=provinceList.get(0).getId();
					else
					{
						System.out.println("Wrong Province:"+provinceList);
						XSSFRow cellWrongRow = SheetWrong.createRow(wrongRow);
						for (int j=cellStart;j<=cellEnd;j++)
						{
							XSSFCell cellWrong = cellWrongRow.createCell(j);
							cellWrong.setCellValue(getCellValue(row.getCell(j)));
						}
						cellWrongRow.getCell(1).setCellValue(province);						
						wrongRow++;
						continue;
					}
				}
				
				XSSFCell cellcity = row.getCell(2);
				
				if (!cellcity.getStringCellValue().equals(""))
				{
					city=cellcity.getStringCellValue().trim();
				}
				else
				{
					city=province;
				}
				
				
				//查询他们的代码  直接查询Port
				Map<String,String> paramcity=new HashMap<String,String>();
				paramcity.put("accessString", " cname like '%"+city+"%' and levelint in (3,4) and dbo.GetParentCode(id,2)="+provinceCityId);
				List<City> cityList=cityService.selectByExample(paramcity);
				if (cityList.size()==1)
					cityId=cityList.get(0).getId();		
				else if (cityList.size()==2)
				{
					cityId=cityList.get(0).getId();
				}
				else
				{
					System.out.println("Wrong City:"+city);
					XSSFRow cellWrongRow = SheetWrong.createRow(wrongRow);
					for (int j=cellStart;j<=cellEnd;j++)
					{
						XSSFCell cellWrong = cellWrongRow.createCell(j);
						cellWrong.setCellValue(getCellValue(row.getCell(j)));
					}
					cellWrongRow.getCell(1).setCellValue(province);						
					wrongRow++;
					continue;
				}
				
				
				//得到港口的对应
				
				Map<String,String> paramport=new HashMap<String,String>();
				paramport.put("accessString", " base_city_id="+cityId);
				List<Port> portList=portService.selectByExample(paramport);
				if (portList.size()==1)
					portId=portList.get(0).getId();		
				else
				{
					System.out.println("Wrong Port");
					XSSFRow cellWrongRow = SheetWrong.createRow(wrongRow);
					for (int j=cellStart;j<=cellEnd;j++)
					{
						XSSFCell cellWrong = cellWrongRow.createCell(j);
						cellWrong.setCellValue(getCellValue(row.getCell(j)));
					}
					cellWrongRow.getCell(1).setCellValue(province);						
					wrongRow++;				
					continue;
				}
				
				//执行插入
				RateMasterBus onemaster=new RateMasterBus();
				
				onemaster.setBaseCarrierId(150);
				onemaster.setRateno("成都-->"+province+city);
				onemaster.setFrequence("Every Day");
				onemaster.setMode("Land");
				onemaster.setOrgId(1);
				onemaster.setClientId(5);
				onemaster.setPol(polId);
				onemaster.setPoa(portId);
				onemaster.setPod(portId);
				onemaster.setIsactive(1);
				onemaster.setFlag(0);
				
				XSSFCell cellnormal = row.getCell(4);
				String cellValue=getCellValue(cellnormal);
				if (!cellValue.equals(""))
				{
					String regex = "\\d*";
					Pattern p = Pattern.compile(regex);

					Matcher m = p.matcher(cellValue);
					Integer transtime=0;

					while (m.find()) {
					if (!"".equals(m.group()))
						transtime=new Integer(m.group());
					}
					
					onemaster.setTranstime(transtime);
					
				} else
				{
					onemaster.setTranstime(0);
				}
				
				cellnormal = row.getCell(20);
				cellValue=getCellValue(cellnormal);				
				onemaster.setRemark(cellValue);
				onemaster.setIsactive(1);
				onemaster.setFlag(0);
				onemaster.setUserId(6);
				onemaster.setCreatedby(6);
				onemaster.setUpdatedby(6);
				Date now=new Date();
				onemaster.setCreated(now);
				onemaster.setUpdated(now);
				
				
				
				

				//ratemasterService.insert(onemaster);
				//Integer masterId=onemaster.getId();				
				//插入分纪录
				Integer[] chargeNameId={63,64,65,66,67,68,69,70,71,72,73,74};
				Integer[] baseUnitId={15,15,15,15,15,15,15,15,16,16,16,16};
				for (int k=6;k<=17;k++)
				{
					RateDetailBus onedetail=new RateDetailBus();
					//onedetail.setrRateMasterId(masterId);
					onedetail.setBaseCurrencyId(1);
					onedetail.setFreighttype(0);
					onedetail.setFcllcl(2);			
				
				
   				    onedetail.setBaseChargenameId(chargeNameId[k-6]);
   				    onedetail.setChargename("");
				    onedetail.setBaseUnitId(baseUnitId[k-6]);
				
				    //if (city.equals("鄂尔多斯市"))
				    //	System.out.print("Yes");
					cellnormal = row.getCell(k);
					cellValue=getCellValue(cellnormal);
					if (cellValue.equals(""))
						continue;
					
					onedetail.setPrice(new BigDecimal(cellValue));
				
					
					cellnormal = row.getCell(5);
					cellValue=getCellValue(cellnormal);
					if (!cellValue.equals(""))
   					   onedetail.setMinvalue(new BigDecimal(cellValue));
					else
						onedetail.setMinvalue(new BigDecimal(0));
					
					onedetail.setMaxvalue(new BigDecimal(0));					
					
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		            Date effectBeginDate = sdf.parse("2015-05-01 12:00:00");
		            onedetail.setEffectbgndate(effectBeginDate);
				
	                Date effectEndDate = sdf.parse("2015-08-31 12:00:00");		
	                onedetail.setEffectenddate(effectEndDate);
	                
	                onedetail.setFcllcl(1);
	                onedetail.setBefixed("N");
	                onedetail.setFlag(0);
	                onedetail.setRemark("");
	                
	                onemaster.getRateDetails().add(onedetail);
				}
				
				
				rateMasterList.add(onemaster);
				
				
				//System.out.println(province+"->"+city+": "+portId);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	    //写到磁盘上
	    try {
	        FileOutputStream fileOutputStream = new FileOutputStream(new File(wrongFileName));
	        wrongworkbook.write(fileOutputStream);
	        fileOutputStream.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	   
	    
    
	    for (RateMasterBus oneObj:rateMasterList)
		{
			

			
			List<RateDetailBus> detailList=oneObj.getRateDetails();		
			if (detailList.size()==0)
				continue;
			
			ratemasterService.insert(oneObj);
			Integer masterId=oneObj.getId();
			
/*			if (oneObj.getRateno().indexOf("呼和浩特市")>-1)
				System.out.print("Yes");*/

			
			for (RateDetailBus oneDetailObj:detailList)
			{
				if (oneDetailObj==null)
					continue;
				oneDetailObj.setrRateMasterId(masterId);
				ratedetailService.insert(oneDetailObj);
			}
			
		}
	   
	    System.out.println("Successful");

	    

	}
    
    
    private String getCellValue(XSSFCell cell)
    {
    	
    	switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 数字
			return (new Double(cell.getNumericCellValue())).toString();
		case HSSFCell.CELL_TYPE_STRING: // 字符串
			return cell.getStringCellValue();
		case HSSFCell.CELL_TYPE_BLANK: // 空值
            return "";
		case HSSFCell.CELL_TYPE_ERROR: // 故障
            return "Error";
			
		default:
			return "";
		}    	
    }
    

	// @Test
	public static void testImport() {

		File file = new File(fileName);
		try {
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);

		
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

			// int rowstart = xssfSheet.getFirstRowNum();
			// int rowEnd = xssfSheet.getLastRowNum();

			int rowstart = fromi;
			int rowEnd = toi;
			for (int i = rowstart; i <= rowEnd; i++) {
				XSSFRow row = xssfSheet.getRow(i);
				if (null == row)
					continue;
				int cellStart = 1;
				int cellEnd = 2;

				for (int k = cellStart; k <= cellEnd; k++) {
					XSSFCell cell = row.getCell(k);
					if (null == cell)
						continue;

					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						System.out.print(cell.getNumericCellValue() + "   ");
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						System.out.print(cell.getStringCellValue() + "   ");
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
						System.out.println(cell.getBooleanCellValue() + "   ");
						break;
					case HSSFCell.CELL_TYPE_FORMULA: // 公式
						System.out.print(cell.getCellFormula() + "   ");
						break;
					case HSSFCell.CELL_TYPE_BLANK: // 空值
						System.out.print(" ");
						break;
					case HSSFCell.CELL_TYPE_ERROR: // 故障
						System.out.print(" ");
						break;
					default:
						System.out.print("未知类型   ");
						break;
					}

				}
				System.out.print("\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
