package edu.fundup.model.entity;

public class PostAnimalSupport {
private int postid;
private int memberid;
private int postownerid;
private int membername;
private int supporterphoto;

public PostAnimalSupport(int postid, int memberid, int postownerid, int membername, int supporterphoto) {
this.postid = postid;
this.memberid = memberid;
this.postownerid = postownerid;
this.membername = membername;
this.supporterphoto = supporterphoto;
}


public int getPostid() {
return postid;
}


public void setPostid(int postid) {
this.postid = postid;
}


public int getSupporterid() {
return memberid;
}


public void setSupporterid(int supporterid) {
this.memberid = supporterid;
}


public int getPostownerid() {
return postownerid;
}


public void setPostownerid(int postownerid) {
this.postownerid = postownerid;
}


public int getSupportername() {
return membername;
}


public void setSupportername(int supportername) {
this.membername = supportername;
}


public int getSupporterphoto() {
return supporterphoto;
}


public void setSupporterphoto(int supporterphoto) {
this.supporterphoto = supporterphoto;
}

}