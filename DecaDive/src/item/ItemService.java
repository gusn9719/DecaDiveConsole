package item;

import java.util.List;

public interface ItemService {
	boolean registItem(ItemVO item);

	List<ItemVO> listItems();

	ItemVO detailItemInfo(int itemCode);

	boolean updateItemPrice(int itemCode, int price);

	boolean updateItemInstock(int itemCode, int instock);

	boolean removeItem(int itemCode);

	/** 사용 가능한 연도대 목록 반환 */
	List<ItemVO> listItemsByDecade(String decade);

	/** 사용 가능한 카테고리 목록 반환 */
	List<ItemVO> listItemsByDecadeAndCategory(String decade, String category);
}
