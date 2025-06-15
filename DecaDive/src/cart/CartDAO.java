package cart;

import java.util.List;

public interface CartDAO {
	boolean insertCartItem(CartItemVO item);

	CartItemVO selectCartItem(int itemCode);

	List<CartItemVO> selectAllCartItem();

	boolean deleteCartItem(int itemCode);

	boolean clear();

}
