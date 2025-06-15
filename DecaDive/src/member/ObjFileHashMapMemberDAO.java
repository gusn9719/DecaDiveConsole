package member;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjFileHashMapMemberDAO implements MemberDAO {
	private Map<String, MemberVO> memberDB = new HashMap<>();
	private int memberSeq = 111;
	private final String DATA_FILE = "./data/memberDB.obj";

	public ObjFileHashMapMemberDAO() {
		loadMembers();
	}

	private void loadMembers() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE));) {

			memberDB = (Map<String, MemberVO>) ois.readObject();
			for (MemberVO member : memberDB.values()) {
				if (member.getMemberNo() >= memberSeq)
					memberSeq = member.getMemberNo() + 1;
			}

		} catch (FileNotFoundException e) {
			System.out.println("[멤버 정보 DB로딩] " + DATA_FILE + "이 없습니다.");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void saveMembers() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE));) {

			oos.writeObject(memberDB);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insertMember(MemberVO member) {
		member.setMemberNo(memberSeq++);
		member.setRegDate(new Date());

		memberDB.put(member.getId(), member);
		saveMembers();
		return true;
	}

	@Override
	public MemberVO selectMember(String id) {
		return memberDB.get(id);
	}

	@Override
	public List<MemberVO> selectAllMembers() {
		return new ArrayList<>(memberDB.values());
	}

	@Override
	public boolean updateMember(MemberVO newMember) {
		memberDB.put(newMember.getId(), newMember);
		saveMembers();
		return true;
	}

	@Override
	public boolean deleteMember(String id) {
		boolean removed = memberDB.remove(id) != null;
		if (removed) {
			saveMembers();
		}
		return removed;
	}

}
