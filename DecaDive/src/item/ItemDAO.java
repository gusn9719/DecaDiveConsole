package item;

import java.util.List;

public interface ItemDAO {
	boolean insertItem(ItemVO item);
	ItemVO selectItem(int itemCode);
	List<ItemVO> selectAllItems();
	List<ItemVO> selectDecaItems(String decade);
	List<ItemVO> selectDecaItemsByCategory(String decade, String category);
	boolean updateItem(ItemVO newItem);
	boolean deleteItem(int itemCode);
 }
