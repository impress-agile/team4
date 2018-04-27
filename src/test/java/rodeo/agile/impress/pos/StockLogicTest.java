package rodeo.agile.impress.pos;

import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class StockLogicTest {

	private StockDao dao = null;
	private StockLogic stockLogic = null;

	@Before
	public void setup() {
		dao = mock(StockDao.class);
		stockLogic = new StockLogic(dao);
	}


	@Test
	public void testInsertMethodShouldBeCalledIfValuesAreValid() throws ClassNotFoundException, SQLException {
		stockLogic.add("ValidName", 5, 10);

		verify(dao, times(1)).insert("ValidName", 5, 10);
	}

	@Test (expected=RuntimeException.class)
	public void testExceptionShouldBeThrownIfPriceIsZero() throws ClassNotFoundException, SQLException {
		stockLogic.add("ValidName", 0, 0);

		verify(dao, times(1)).insert("ValidName", 0, 0);
	}

	@Test
	public void testUpdateMethodShouldBeCalledIfValuesAreValid() throws ClassNotFoundException, SQLException {
		stockLogic.update("ValidName", 50, 100);

		verify(dao, times(1)).update("ValidName", 50, 100);
	}



}
