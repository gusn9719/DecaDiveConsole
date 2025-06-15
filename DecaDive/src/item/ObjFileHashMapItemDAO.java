package item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjFileHashMapItemDAO implements ItemDAO {

	private Map<Integer, ItemVO> itemDB = new HashMap<>();
	private int itemSeq = 1;

	private final String DATA_FILE = "./data/itemDB.obj";

	public ObjFileHashMapItemDAO() {
		loadItems();
	}

	private void loadItems() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE));) {

			itemDB = (Map<Integer, ItemVO>) ois.readObject();
			itemSeq = Collections.max(itemDB.keySet()) + 1;

		} catch (FileNotFoundException e) {
			System.out.println("[상품 정보 DB로딩]" + DATA_FILE + "이 없습니다.");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void saveItems() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE));) {
			oos.writeObject(itemDB);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insertItem(ItemVO item) {
		item.setItemCode(itemSeq++);
		itemDB.put(item.getItemCode(), item);
		saveItems();
		return true;
	}

	@Override
	public ItemVO selectItem(int itemCode) {
		return itemDB.get(itemCode);
	}

	@Override
	public List<ItemVO> selectAllItems() {
		return new ArrayList<>(itemDB.values());
	}

	@Override
	public List<ItemVO> selectDecaItems(String decade) {
		List<ItemVO> itemList = new ArrayList<>();
		for (ItemVO item : selectAllItems()) {
			if (item.getDecade().equals(decade)) {
				itemList.add(item);
			}
		}
		return itemList;
	}

	@Override
	public List<ItemVO> selectDecaItemsByCategory(String decade, String category) {
		List<ItemVO> itemList = new ArrayList<>();
		for (ItemVO item : selectAllItems()) {
			if (item.getDecade().equals(decade) && item.getCategory().equals(category)) {
				itemList.add(item);
			}
		}
		return itemList;
	}

	@Override
	public boolean updateItem(ItemVO newItem) {
		if (!itemDB.containsKey(newItem.getItemCode()))
			return false;
		itemDB.put(newItem.getItemCode(), newItem);
		saveItems();
		return true;
	}

	@Override
	public boolean deleteItem(int itemCode) {
		boolean removed = itemDB.remove(itemCode) != null;
		if (removed)
			saveItems();
		return removed;

	}

}
