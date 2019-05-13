package edu.fundup.utils;

import edu.fundup.model.entity.Member;

import java.util.Observable;

public class ObservableUser extends Observable {
    private Member onlineMember;


    public ObservableUser(Member onlineMember) {
        this.onlineMember = onlineMember;
    }

    public Member getOnlineMember() {
        return onlineMember;
    }


    public void setOnlineMember(Member onlineMember) {
        this.onlineMember = onlineMember;
        setChanged();
        notifyObservers(onlineMember);
    }

    @Override
    public String toString(){
        return this.onlineMember.toString();
    }

}