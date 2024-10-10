package ac.ku.Lorna.services;


import ac.ku.Lorna.models.Member;
import ac.ku.Lorna.utilities.QueryExecutor;

import java.sql.SQLException;
import java.util.List;

public class MemberService {

    // Load members from database
    public List<Member> loadMembers(int limit, int offset) {
        String query = "SELECT * FROM members LIMIT ? OFFSET ?";
        try {
            return QueryExecutor.executeQuery(query, rs ->
                    new Member(
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getDouble("shares"),
                            rs.getDouble("registration_fee")
                    ), limit, offset
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Add new member to the database
    public void addMember(Member member) {
        String insertQuery = "INSERT INTO members (name, age, shares, registration_fee) VALUES (?, ?, ?, ?)";
        try {
            QueryExecutor.executeUpdate(insertQuery, member.getName(), member.getAge(), member.getShares(), member.getRegistrationFee());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit existing member in the database
    public void editMember(Member member, int memberId) {
        String updateQuery = "UPDATE members SET name = ?, age = ?, shares = ?, registration_fee = ? WHERE id = ?";
        try {
            QueryExecutor.executeUpdate(updateQuery, member.getName(), member.getAge(), member.getShares(), member.getRegistrationFee(), memberId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //DELETE A MEMBER FROM THE DATABASE
    public void deleteMember(String memberName) {
        String deleteQuery = "DELETE FROM members WHERE name = ?";
        try {
            QueryExecutor.executeUpdate(deleteQuery, memberName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Get the total count of membrs for pagination
    public int getTotalMemberCount() {
        String countQuery = "SELECT COUNT(*) FROM members";
        try {
            List<Integer> counts = QueryExecutor.executeQuery(countQuery, rs -> {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            });
            return (counts != null && !counts.isEmpty()) ? counts.get(0) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}


