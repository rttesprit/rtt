package edu.fundup.model.iservice;

import edu.fundup.model.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IMemberService {
    void RegisterContributor(Member m);
    void RegisterPaperlessMember(Member m);
    void forgotPwd(Member m);
    int SignIn(Member m) throws SQLException;
    void RegisterEntreprise(Member m);
    ArrayList<Member> getByRole(String role);
}
