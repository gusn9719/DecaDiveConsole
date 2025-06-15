package cart;

import java.util.List;

public class CartServiceImpl implements CartService {
	private CartDAO cartDAO;

	public CartServiceImpl(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	@Override
	public boolean addItem2Cart(CartItemVO item) {
		return cartDAO.insertCartItem(item);
	}

	@Override
	public CartItemVO getCartItemInfo(int itemCode) {
		return cartDAO.selectCartItem(itemCode);
	}

	@Override
	public List<CartItemVO> listCartItems() {
		return cartDAO.selectAllCartItem();
	}

	@Override
	public boolean isCartEmpty() {
		// return cartDAO.selectAllCartItem().isEmpty(); 같은 기능
		return listCartItems().isEmpty();
	}

	@Override
	public boolean removeCartItem(int itemCode) {
		return cartDAO.deleteCartItem(itemCode);
	}

	@Override
	public boolean clearCart() {
		return cartDAO.clear();
	}

}
