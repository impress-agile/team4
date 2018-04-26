package rodeo.agile.impress.pos;

import java.sql.SQLException;

public class StockLogic {

	private StockDao dao;

	public StockLogic(StockDao dao) {
		this.dao = dao;
	}

	public void add(String name, int price, int num) throws ClassNotFoundException, SQLException {
		if (price == 0) {
			throw new RuntimeException("Failed to add item which price is 0.");
		}
		dao.insert(name, price, num);
	}

	public void update(String name, int price, int num) throws ClassNotFoundException, SQLException {
		dao.update(name, price, num);

	}

	public int select_count(String name) throws ClassNotFoundException, SQLException {
		return dao.select_count(name);
	}

}
