
import static org.junit.Assert.*;

import org.junit.Test;
import org.taxi.algeria.database.FetchDatabase;

public class TestFetchDatabase {

	FetchDatabase service;
	
	@Test
	public void testGetEmail()throws Exception{
		service = new FetchDatabase();
		assertEquals(false, service.getEmail("myaemail",100) );
	}
}
