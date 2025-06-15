package order;

import java.util.Date;
import java.util.List;

import item.ItemService;

public class OrderServiceImpl implements OrderService {
	private final int COMPLETE = 10;

	private OrderDAO orderDAO;
	private ItemService itemService;

	public OrderServiceImpl(OrderDAO orderDAO, ItemService itemService) {
		this.orderDAO = orderDAO;
		this.itemService = itemService;
	}

	@Override
	public boolean orderItems(OrderVO order) {
		// 주문 정보 추가 ( 주문일, 배송상태, 배송 완료 // 배송 완료도 한 번에 처리.)
		order.setOrderDate(new Date());
		order.setStatus(COMPLETE);
		order.setDeliverDate(new Date());

		for (OrderItemVO item : order.getOrderItemList()) {
			int itemCode = item.getItemCode();
			int newInstock = itemService.detailItemInfo(itemCode).getInstock() - item.getQuantity();
			if (newInstock >= 0) {
				itemService.updateItemInstock(itemCode, newInstock);
			} else {
				return false;
			}
		}

		// 주문 정보 DB에 추가
		orderDAO.insertOrder(order);
		return true;
	}

	@Override
	public List<OrderVO> listMyOrders(String memberId) {
		return orderDAO.selectOrdersOfMember(memberId);
	}

	@Override
	public List<OrderVO> listAllOrder() {
		return orderDAO.selectAllOrder();
	}

}
