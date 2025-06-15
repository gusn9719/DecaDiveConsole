package item;

import java.util.List;

public class ItemServiceImpl implements ItemService {

	private ItemDAO itemDAO;

	public ItemServiceImpl(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	@Override
	public boolean registItem(ItemVO item) {
		return itemDAO.insertItem(item);
	}

	@Override
	public List<ItemVO> listItems() {
		return itemDAO.selectAllItems();
	}

	@Override
	public ItemVO detailItemInfo(int itemCode) {
		return itemDAO.selectItem(itemCode);
	}

	@Override
	public boolean updateItemPrice(int itemCode, int price) {
		ItemVO item = itemDAO.selectItem(itemCode);

		if (item != null) {
			item.setPrice(price);
			itemDAO.updateItem(item);
			return true;
		}

		return false;
	}

	@Override
	public boolean updateItemInstock(int itemCode, int instock) {
		ItemVO item = itemDAO.selectItem(itemCode);

		if (item != null) {
			item.setInstock(instock);
			itemDAO.updateItem(item);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeItem(int itemCode) {
		return itemDAO.deleteItem(itemCode);
	}

	@Override
	public List<ItemVO> listItemsByDecade(String decade) {

		return itemDAO.selectDecaItems(decade);
	}

	@Override
	public List<ItemVO> listItemsByDecadeAndCategory(String decade, String category) {
		return itemDAO.selectDecaItemsByCategory(decade, category);
	}

}
