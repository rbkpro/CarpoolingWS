
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.taxi.algeria.model.Driver;
import org.taxi.algeria.service.DriverService;


public class DriverServiceTest {
	
	private Driver driver = new Driver();
	
	@BeforeClass
	public static void testBeforeClassMethod(){
		
	}
	
	@AfterClass
	public static void testAfterClassMethod(){
		
	}


	@Before 
	public void setUP(){
		
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void setDriverTest() throws Exception{
		driver.setBirthDate(new Date());
		driver.setDriverID("rbkpro");
		driver.setPassword("");
		DriverService service = new DriverService();
		service.getDriver(driver.getDriverID(), driver.getPassword(), driver.getRegID());
		
	}
	

}
