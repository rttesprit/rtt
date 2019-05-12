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
    void disableUser(Member m);
    Member getUserById(int id);
    String getCode(String mail);
    ArrayList<Member> getByRole(String role);
    void BecomeContributorMember(String type,String ccnumber,String cvv,Member connectedMember);
    void updatePassword(String newPwd,int id);
    void updatePhoto(String photoName,int id);
    ArrayList<String> getAllLogin();
    String getMd5(String pwd);
}
