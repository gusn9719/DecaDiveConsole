package app;

import java.util.ArrayList;
import java.util.List;

import cart.CartItemVO;
import cart.CartService;
import cart.CartServiceImpl;
import cart.HashMapCartDAO;
import item.ItemService;
import item.ItemServiceImpl;
import item.ItemVO;
import item.ObjFileHashMapItemDAO;
import member.MemberService;
import member.MemberServiceImpl;
import member.MemberVO;
import member.ObjFileHashMapMemberDAO;
import order.ObjFileHashMapOrderDAO;
import order.OrderItemVO;
import order.OrderService;
import order.OrderServiceImpl;
import order.OrderVO;

public class DecaDiveConsoleApp {
	MyAppReader input = new MyAppReader(); // 입력값 받아오기

	String[] startMenuList = { "종료", "연도별 목록", "연도 + 항목별 목록", "로그인", "회원 가입" };
	String[] adminMenuList = { "로그아웃", "상품 목록", "상품 등록", "상품 정보 수정", "상품 삭제", "회원 목록", "주문 목록" };
	String[] memberMenuList = { "로그아웃", "연도별 목록", "연도 + 항목별 목록", "상품 주문", "주문 목록", "장바구니 상품 담기", "장바구니 보기", "내 정보" };
	String[] cartMenuList = { "돌아가기", "상품 주문", "상품 삭제", "장바구니 비우기" };
	String[] myInfoMenuList = { "돌아가기", "비밀번호 변경", "회원 탈퇴" };

	String[] decaMenuList = { "이전", "80s", "90s", "00s" };
	String[] CategoryMenuList = { "이전", "WALL", "DESK", "TECH", "COMICS", "FASHION" };

	final String CONFIRM = "yes";

	int diveLevel = 0;

	final String ADMIN_ID = "admin";
	final String ADMIN_PWD = "1234";
	final String ADMIN_NAME = "관리자";

	ItemService is = new ItemServiceImpl(new ObjFileHashMapItemDAO());
	MemberService ms = new MemberServiceImpl(new ObjFileHashMapMemberDAO());
	OrderService os = new OrderServiceImpl(new ObjFileHashMapOrderDAO(), is);
	CartService cs = new CartServiceImpl(new HashMapCartDAO());
	MemberVO loggedMember;

	public static void main(String[] args) {
		DecaDiveConsoleApp app = new DecaDiveConsoleApp();

		app.displayWelcome();
		app.controlStartMenu();
	}

	private void displayWelcome() {
		System.out.println("*********************************");
		System.out.println("*   *   *   DecaDive   *    *   *");
		System.out.println("*********************************");

	}

	private void controlStartMenu() {
		int menu;

		do { // "종료", "연도별 목록", "연도 + 항목별 목록", "로그인", "회원 가입"
			menu = selectMenu(startMenuList);
			switch (menu) {
			case 1: // 연도별 목록
				menuDecaList();
				break;
			case 2: // 연도 + 카테고리별 목록
				menuDecaDiveList();
				break;
			case 3: // 로그인
				menuLogin();
				break;
			case 4: // 회원 가입
				menuSignUp();
				break;
			case 0: // 종료
				menuExit();
				break;
			default:
				menuWrongNumber();
			}

		} while (menu != 0);

	}

	private void menuDecaList() {
		// 연도별
		int menu;
		System.out.println("=== Deca List ===");

		do { // "종료", "80s", "90s", "00s"
			menu = selectMenu(decaMenuList);

			displayDecaList(decaMenuList[menu]);

		} while (menu != 0);

	}

	private void displayDecaList(String menu) {
		// 연도별
		List<ItemVO> ItemList = is.listItemsByDecade(menu);
		if(menu == "이전")  return;
		System.out.println("===========================================");
		if (ItemList.isEmpty())
			System.out.println("등록된 상품이 없습니다.");
		else {
			for (ItemVO item : ItemList) {

				System.out.println(item);
			}
		}

		System.out.println("===========================================");
	}

	private void menuDecaDiveList() {
		// 연도 + 목록별
		int[] menu = new int[2];
		System.out.println("=== Deca List ===");

		do { // "WALL", "DESK", "TECH", "COMICS", "FASHION"
			menu[0] = selectMenu(decaMenuList);
			if(menu[0] == 0) return;
			menu[1] = selectMenu(CategoryMenuList);
			if(menu[1] == 0) return;
			displayDecaDiveList(decaMenuList[menu[0]], CategoryMenuList[menu[1]]);

		} while (menu[0] != 0 && menu[1] != 0 && diveLevel != 0);
	}

	private void displayDecaDiveList(String decaMenu, String categoryMenu) {
		// 연도 + 목록별
		List<ItemVO> ItemList = is.listItemsByDecadeAndCategory(decaMenu, categoryMenu);

		System.out.println("===========================================");
		if (ItemList.isEmpty())
			System.out.println("등록된 상품이 없습니다.");
		else {
			for (ItemVO item : ItemList) {

				System.out.println(item);
			}
		}

		System.out.println("===========================================");
	}

	private void menuLogin() {
		System.out.println("=== 로그인 ===");
		String id = input.readString(">> id : ");
		String password = input.readString(">> password : ");

		// 관리자
		if (id.equals(ADMIN_ID) && password.equals(ADMIN_PWD)) {
			loggedMember = new MemberVO(ADMIN_ID, ADMIN_PWD, ADMIN_NAME);
			System.out.println("관리자 모드로 변경합니다.");
			controlAdminMenu();
		} else {
			// 회원
			loggedMember = ms.login(id, password);

			if (loggedMember != null) {
				System.out.println("[로그인] " + loggedMember.getUsername() + "님 안녕하세요.");
				controlMemberMenu();
			} else {

				// 아니면
				System.out.println("로그인을 하지 못했습니다.");
			}

		}
	}

	private void controlMemberMenu() {
		int menu;

		do { // "로그아웃", "연도별 목록", "연도 + 항목별 목록", "상품 주문", "상품 목록", "장바구니 상품 담기", "장바구니 보기",
				// "내 정보"
			menu = selectMenu(memberMenuList);
			switch (menu) {
			case 1: // 연도별 목록
				menuDecaList();
				break;
			case 2: // 연도 + 항목별 목록
				menuDecaDiveList();
				break;
			case 3: // "상품 주문"
				menuItemOrder();
				break;
			case 4: // "주문 목록"
				menuOrderList();
				break;
			case 5: // "장바구니 상품 담기"
				menuAddItem2Cart();
				break;
			case 6: // "장바구니 보기"
				menuCartView();
				break;
			case 7: // "내 정보"
				menuMyInfo();
				break;
			case 0: // 종료
				menuLogout();
				break;
			default:
				menuWrongNumber();
			}

		} while (menu != 0);

	}

	private void menuMyInfo() {
		System.out.println("*** 내 정보 *** ");
		System.out.println(loggedMember);
		System.out.println("-------------------------------------------");

		controlMyInfoMenu();

		System.out.println("-------------------------------------------");
	}

	private void controlMyInfoMenu() {
		int menu;
		do {
			menu = selectMenu(myInfoMenuList);
			switch (menu) {
			case 1:
				menuUpdatePassword();
				break;
			case 2:
				menuMemberExit();
				break;
			case 0:
				break;
			default:
				menuWrongNumber();
			}
		} while (menu != 0 && loggedMember != null);
	}

	private void menuMemberExit() {
		System.out.println("*** 회원 탈퇴 *** ");
		String password = input.readString(">> 비밀번호 : ");
		if (ms.removeMember(loggedMember.getId(), password)) {
			System.out.println("[회원 탈퇴] 회원정보, 주문정보를 삭제하였습니다. 그동안 서비스를 이용해 주셔서 감사.");
			loggedMember = null;
		} else {
			System.out.println("[회원 탈퇴 실패] 회원 탈퇴 처리에 실패.");
		}

	}

	private void menuUpdatePassword() {
		System.out.println("*** 비밀번호 수정 *** ");
		String oldPassword = input.readString(">> 기존 비밀번호 : ");
		String newPassword = input.readString(">> 새 비밀번호 : ");

		if (ms.updatePassword(loggedMember.getId(), oldPassword, newPassword)) {
			System.out.println("[비밀번호 수정] 비밀 번호 수정했습니다.");
		} else {
			System.out.println("[비밀번호 수정 실패] 비밀 번호 수정 실패했습니다.");
		}
	}

	private void menuCartView() {
		System.out.println("*** 장바구니 보기 ***");
		displayCartItemList();

		if (!cs.isCartEmpty())
			controlCartMenu();
	}

	private void controlCartMenu() {
		int menu;
		do {
			menu = selectMenu(cartMenuList);
			switch (menu) {
			case 1:
				menuCartOrder();
				break;
			case 2:
				menuCartItemDelete();
				break;
			case 3:
				menuCartClear();
				break;
			case 0:
				break;
			default:
				menuWrongNumber();
			}
		} while (menu != 0 && !cs.isCartEmpty());
	}

	private void menuCartItemDelete() {
		System.out.println("*** 장바구니 상품 삭제 ***");
		displayCartItemList();
		int itemCode = input.readInt(">> 상품 번호 : ");
		CartItemVO item = cs.getCartItemInfo(itemCode);

		if (item == null) {
			System.out.println("없는 상품입니다.");
		} else {
			cs.removeCartItem(itemCode);
			System.out.println("장바구니에서 해당 상품을 삭제하였습니다.");
		}
		displayCartItemList();
	}

	private void menuCartOrder() {
		System.out.println("*** 장바구니 상품 주문 ***");
		displayCartItemList();

		// 주문 상품 목록
		List<OrderItemVO> orderItemList = new ArrayList<>();
		int totalPrice = 0;
		for (CartItemVO cartItem : cs.listCartItems()) {
			ItemVO item = is.detailItemInfo(cartItem.getItemCode());
			int price = item.getPrice() * cartItem.getQuantity();
			totalPrice += price;
			orderItemList.add(new OrderItemVO(cartItem.getItemCode(), cartItem.getQuantity(), price));
		}

		// 주문 정보 생성
		OrderVO order = new OrderVO(loggedMember.getId(), orderItemList, totalPrice);

		// 배송 정보 추가
		setDeliveryInfo();
		order.setMobile(loggedMember.getMobile());
		order.setAddress(loggedMember.getAddress());

		//
		displayOrderInfo(order);

		String confirm = input.readString(">> 위와 같은 내용을 주문 및 결제를 진행하시겠습니까? ('" + CONFIRM + "'이면 주문 실행) : ");

		if (confirm.equals(CONFIRM)) {
			if (os.orderItems(order)) {
				cs.clearCart();
				System.out.println("주문이 완료");
				System.out.println("배송이 완료");
			} else {
				System.out.println("주문 실패");
			}
		} else {
			System.out.println("주문 취소");
		}

	}

	private void displayOrderInfo(OrderVO order) {
		System.out.println(order);

	}

	private void setDeliveryInfo() {
		if (loggedMember.getMobile() == null) {
			System.out.println("*** 배송정보 입력 ***");
			String mobile = input.readString(">> 모바일 번호 : ");

			String email = input.readString(">> 이메일 주소 : ");

			String address = input.readString(">> 주소 : ");

			loggedMember.setMobile(mobile);
			loggedMember.setEmail(email);
			loggedMember.setAddress(address);

			ms.addMemberInfo(loggedMember.getId(), mobile, email, address);
			// loggedMember = ms.detailMemberInfo(loggedMember.getId());
		}
	}

	private void menuCartClear() {
		System.out.println("*** 장바구니 비우기 ***");
		String confirm = input.readString(">> 장바구니의 모든 상품을 삭제 하시겠습니까? ('" + CONFIRM + "'이면 삭제) : ");

		if (confirm.equals(CONFIRM)) {
			cs.clearCart();
			System.out.println("장바구니의 모든 상품을 삭제하였습니다.");
		} else {
			System.out.println("장바구니 비우기 취소");
		}

	}

	private void displayCartItemList() {
		if (cs.isCartEmpty()) {

			System.out.println("장바구니가 비어있습니다.");

		} else {

			System.out.println("-------------------------------------------");

			for (CartItemVO item : cs.listCartItems()) {
				System.out.println(item);
			}

			System.out.println("-------------------------------------------");

		}

	}

	private void menuAddItem2Cart() {
		System.out.println("=== 장바구니에 상품 담기 ===");

		displayAvailableItemList();
		int itemCode = input.readInt(">> 상품 번호 : ");
		ItemVO item = is.detailItemInfo(itemCode);

		if (item == null) {
			System.out.println("없는 상품입니다.");
			return;
		}

		int quantity = input.readInt(">> 주문량 (" + item.getInstock() + "개 이내) : ");
		if (quantity > item.getInstock()) {
			System.out.println("주문량이 재고량보다 큽니다.");
			return;
		}

		// 장바구니에 있나?
		// 없으면, 장바구니에 담기
		if (cs.getCartItemInfo(itemCode) == null) {
			cs.addItem2Cart(new CartItemVO(itemCode, quantity));
			System.out.println("장바구니에 추가했습니다.");
		} else {
			System.out.println("이미 장바구니에 있는 상품입니다.");
		}
	}

	private void menuItemOrder() {
		System.out.println("=== 상품 주문 ===");
		displayAvailableItemList();
		int itemCode = input.readInt(">> 상품 번호 : ");
		ItemVO item = is.detailItemInfo(itemCode);

		if (item == null) {
			System.out.println("없는 상품입니다.");
			return;
		}

		int quantity = input.readInt(">> 주문량 (" + item.getInstock() + "개 이내) : ");
		if (quantity > item.getInstock()) {
			System.out.println("주문량이 재고량보다 큽니다.");
			return;
		}

		// 주문 상품 목록
		List<OrderItemVO> orderItemList = new ArrayList<>();
		int price = item.getPrice() * quantity;
		orderItemList.add(new OrderItemVO(itemCode, quantity, price));

		// 주문 정보 생성
		OrderVO order = new OrderVO(loggedMember.getId(), orderItemList, price);

		// 배송 정보 추가
		setDeliveryInfo();
		order.setMobile(loggedMember.getMobile());
		order.setAddress(loggedMember.getAddress());

		if (os.orderItems(order)) {
			System.out.println("주문이 완료");
			System.out.println("배송이 완료");
		} else {
			System.out.println("주문 실패");
		}
	}

	private void displayAvailableItemList() {
		List<ItemVO> itemList = is.listItems();
		System.out.println("-------------------------------------------");
		if (itemList.isEmpty())
			System.out.println("주문 가능한 상품이 없습니다.");
		else {
			int count = 0;
			for (ItemVO item : itemList) {
				if (item.getInstock() > 0) {
					System.out.println(item);
					count++;
				}
			}
			if (count == 0)
				System.out.println("주문 가능한 상품이 없습니다.");
		}
		System.out.println("-------------------------------------------");

		
	}

	private void menuSignUp() {
		System.out.println("=== 회원가입 ===");
		String id = input.readString(">> id : ");
		String password = input.readString(">> password : ");
		String username = input.readString(">> username : ");

		if (ms.registMember(new MemberVO(id, password, username))) {
			System.out.println("회원 가입이 완료되었습니다. 서비스 이용을 위한 로그인을 해주세요. ");
		} else {
			System.out.println("회원 가입에 실패했습니다. ");
		}

	}

	private void menuExit() {
		System.out.println("DecaDive 서비스를 종료합니다.");

	}

	private void menuWrongNumber() {
		System.out.println("없는 메뉴입니다.");

	}

	private void controlAdminMenu() {
		int menu;
		do {

			menu = selectMenu(adminMenuList);
			switch (menu) {
			case 1:
				menuItemList();
				break;
			case 2:
				menuItemRegist();
				break;
			case 3:
				menuItemUpdate();
				break;
			case 4:
				menuItemRemove();
				break;
			case 5:
				menuMemberList();
				break;
			case 6:
				menuOrderList();
				break;
			case 0:
				menuLogout();
				break;
			default:
				menuWrongNumber();
			}

		} while (menu != 0 && loggedMember != null); // 원래는 관리자 아이디인지 확인
	}

	private void menuItemList() {
		System.out.println("=== 상품 전체 목록 ===");
		displayItemList();
	}

	private void menuItemRegist() {
		System.out.println("=== 상품 등록 ===");
		int decade = input.readInt(">> 연도 : 1. 80s | 2. 90s | 3. 00s ");
		int category = input.readInt(">> 항목 : 1. WALL | 2. DESK | 3. TECH | 4. COMICS | 5. FASHION ");
		String name = input.readString(">> 상품 이름 : ");
		int price = input.readInt(">> 가격 : ");
		int instock = input.readInt(">> 재고량 : ");

		if (is.registItem(new ItemVO(decaMenuList[decade], CategoryMenuList[category], name, price, instock))) {
			System.out.println("상품을 등록했습니다.");
			displayItemList();
		} else {
			System.out.println("상품 등록에 실패했습니다.");
		}
	}

	private void menuItemUpdate() {
		System.out.println("=== 상품 정보 수정 ===");
		displayItemList();
		int itemCode = input.readInt(">> 상품 번호 : ");
		int select = input.readInt(">> 수정할 정보 선택 (1. 가격, 2. 재고량) ");
		if (select == 1) { // 가격
			int price = input.readInt(">> 새 가격: ");
			if (is.updateItemPrice(itemCode, price)) {
				System.out.println("[상품 정보 수정] 가격을 수정하였습니다.");
			} else {
				System.out.println("[상품 정보 수정 오류] 없는 상품입니다.");
			}

		} else if (select == 2) { // 재고량
			int instock = input.readInt(">> 새 재고량 ");
			if (is.updateItemInstock(itemCode, instock)) {
				System.out.println("[상품 정보 수정] 재고량을 수정하였습니다.");
			} else {
				System.out.println("[상품 정보 수정 오류] 없는 상품입니다.");
			}
		} else {
			System.out.println("[상품 정보 수정 취소] 지원하지 않는 기능입니다.");
		}
	}

	private void menuItemRemove() {
		System.out.println("=== 상품 삭제 ===");
		displayItemList();
		int itemCode = input.readInt(">> 상품 번호 : ");
		String confirm = input.readString("선택한 상품을 삭제하시겠습니까? ('" + CONFIRM + "'를 입력하면 실행) : ");
		if (confirm.equals(CONFIRM)) {

			if (is.removeItem(itemCode)) {
				System.out.println("[상품 삭제] 상품을 삭제했습니다.");
			} else {
				System.out.println("[상품 정보 수정 오류] 없는 상품입니다.");
			}
		} else {
			System.out.println("[상품 삭제 취소] 상품 삭제를 취소했습니다.");
		}

	}

	private void menuMemberList() {
		System.out.println("=== 회원 목록 조회 ===");
		System.out.println("===========================================");
		List<MemberVO> memberList = ms.listMembers();
		if (memberList.isEmpty()) {
			System.out.println("회원이 없습니다.");
		} else {
			for (MemberVO member : memberList) {
				System.out.println(member);
			}
		}

		System.out.println("===========================================");
	}

	private void menuOrderList() {
		if (loggedMember.getId().equals(ADMIN_ID)) {
			System.out.println(os.listAllOrder());
		} else {
			System.out.println(os.listMyOrders(loggedMember.getId()));
		}
	}

	private void menuLogout() {
		System.out.println("[로그아웃] " + loggedMember.getUsername() + "님, 안녕히 가십시오.");
		loggedMember = null;
	}

	private void displayItemList() {
		List<ItemVO> itemList = is.listItems();
		System.out.println("=======================================");
		if (itemList.isEmpty())
			System.out.println("등록된 상품이 없습니다.");
		else {
			for (ItemVO item : itemList) {
				System.out.println(item);
			}
		}
		System.out.println("=======================================");
	}

	private int selectMenu(String[] menuList) {
		System.out.println("=======================================");
		for (int i = 1; i < menuList.length; i++) {
			System.out.println(i + ". " + menuList[i]);
		}

		System.out.println("0. " + menuList[0]);
		System.out.println("=======================================");

		return input.readInt(">> 메뉴 선택 : ");
	}

}
