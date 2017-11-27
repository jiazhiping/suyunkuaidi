package cn.itcast.bos.test.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

public class POITest {

	@Test
	public void testPOI(){
		//使用POI读取excel
		try {
			//1.使用workbook读取整个excel
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(new File("E:\\current_course\\速运快递\\资料\\day04\\03_区域测试数据\\区域导入测试数据.xls")));
			//2.获取单个sheet对象
			HSSFSheet sheet = wb.getSheetAt(0);
			//3.循环获取行row对象
			for(Row row : sheet){
				//4.获取单元格cell对象
				String value1 = row.getCell(0).getStringCellValue();
				String value2 = row.getCell(1).getStringCellValue();
				String value3 = row.getCell(2).getStringCellValue();
				String value4 = row.getCell(3).getStringCellValue();
				String value5 = row.getCell(4).getStringCellValue();
				//5.输出结果
				System.out.println(value1+"--"+value2+"--"+value3+"--"+value4+"--"+value5);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
