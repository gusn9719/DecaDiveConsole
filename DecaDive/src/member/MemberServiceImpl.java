package member;

import java.util.List;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memberDAO;

	public MemberServiceImpl(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public boolean registMember(MemberVO member) {
		return memberDAO.insertMember(member);
	}

	@Override
	public List<MemberVO> listMembers() {
		return memberDAO.selectAllMembers();
	}

	@Override
	public MemberVO detailMemberInfo(String id) {
		return memberDAO.selectMember(id);
	}

	@Override
	public MemberVO login(String id, String password) {
		MemberVO member = memberDAO.selectMember(id);

		if (member != null && member.getPassword().equals(password))
			return member;

		return null;
	}

	@Override
	public boolean updatePassword(String id, String oldPassword, String newPassword) {
		MemberVO member = memberDAO.selectMember(id);

		if (member == null)
			return false;

		if (member.getPassword().equals(oldPassword)) {
			member.setPassword(newPassword);
			return memberDAO.updateMember(member);
		}

		return false;
	}

	@Override
	public boolean addMemberInfo(String id, String mobile, String email, String address) {
		MemberVO member = memberDAO.selectMember(id);
		if (member == null)
			return false;

		member.setMobile(mobile);
		member.setEmail(email);
		member.setAddress(address);

		return memberDAO.updateMember(member);
	}

	@Override
	public boolean removeMember(String id, String password) {
		MemberVO member = memberDAO.selectMember(id);

		if (member == null)
			return false;

		if (!member.getPassword().equals(password))
			return false;

		return memberDAO.deleteMember(id);
	}

}
