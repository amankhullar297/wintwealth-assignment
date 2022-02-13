package com.wintweatlh.assignment;

import com.wintweatlh.assignment.service.ExcelService;
import com.wintweatlh.assignment.service.implementation.ExcelServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WintwealthAssignmentApplicationTests {

	@Test
	void contextLoads() throws Exception{
		ExcelService excelService = new ExcelServiceImpl();
		//List<List<Object>> objects = excelService.readFromExcel("C:\\Users\\WELCOME\\Desktop\\test.xlsx", 0);
	}

}
