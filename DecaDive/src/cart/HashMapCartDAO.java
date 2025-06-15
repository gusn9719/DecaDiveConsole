package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapCartDAO implements CartDAO {
	Map<Integer, CartItemVO> cartDB = new HashMap<>();

	@Override
	public boolean insertCartItem(CartItemVO item) {
		cartDB.put(item.getItemCode(), item);
		return true;
	}

	@Override
	public CartItemVO selectCartItem(int itemCode) {
		return cartDB.get(itemCode);
	}

	@Override
	public List<CartItemVO> selectAllCartItem() {
		return new ArrayList<>(cartDB.values());
	}

	@Override
	public boolean deleteCartItem(int itemCode) {
		// remove는 삭제한 객체를 반환하기에 반환된 값이 null이면 삭제를 실패. 
		// 이거는 null이 아닐 때는 true를 반환한다는 뜻이니까 삭제가 성공하면 true
		return cartDB.remove(itemCode) != null;
	}

	@Override
	public boolean clear() {
		cartDB.clear();
		return true;
	}

}
