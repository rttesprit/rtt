package edu.fundup.utils;

import edu.fundup.model.entity.Member;

public final class UserSession {

    private static UserSession Instance;
    public Member member;

    private UserSession(){
    }

    public static UserSession getInstance() {
        if(Instance == null) {
            Instance = new UserSession();
        }
        return Instance;
    }

    public void setMember(Member m) {
       member = m;
    }
    public Member getMember(){
        return Instance.member;
    }
    public void cleanUserSession() {
        Instance.member = null;
    }
}
