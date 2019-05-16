package edu.fundup.model.entity;

public class PostAnimalSettings {

public PostAnimalSettings() {
		super();
	}

private int settid;
private int memberid;
private int postid;
boolean isCommentnotif;
boolean isSupportnotif;
boolean isReachsupporters;
int supportgoal;
boolean isVisiblePost;

public PostAnimalSettings(int settid,int memberid, int postid, boolean isCommentnotif, boolean isSupportnotif, boolean isReachsupporters,
		int supportgoal, boolean isVisiblePost) {
this.memberid = memberid;
this.postid = postid;
this.isCommentnotif = isCommentnotif;
this.isSupportnotif = isSupportnotif;
this.isReachsupporters = isReachsupporters;
this.supportgoal = supportgoal;
this.isVisiblePost = isVisiblePost;
}

public PostAnimalSettings(int memberid, int postid, boolean isCommentnotif, boolean isSupportnotif, boolean isReachsupporters,
		int supportgoal, boolean isVisiblePost) {
this.memberid = memberid;
this.postid = postid;
this.isCommentnotif = isCommentnotif;
this.isSupportnotif = isSupportnotif;
this.isReachsupporters = isReachsupporters;
this.supportgoal = supportgoal;
this.isVisiblePost = isVisiblePost;
}

public PostAnimalSettings(int settid,int memberid, int postid, boolean isCommentnotif, boolean isSupportnotif, boolean isvisiblePost) {
this.memberid = memberid;
this.postid = postid;
this.isCommentnotif = isCommentnotif;
this.isSupportnotif = isSupportnotif;
this.isVisiblePost = isvisiblePost;
}

public PostAnimalSettings(int memberid, int postid, boolean isCommentnotif, boolean isSupportnotif, boolean isvisiblePost) {
this.memberid = memberid;
this.postid = postid;
this.isCommentnotif = isCommentnotif;
this.isSupportnotif = isSupportnotif;
this.isVisiblePost = isvisiblePost;
}


public int getSettid() {
	return settid;
}

public void setSettid(int settid) {
	this.settid = settid;
}

public int getMemberid() {
	return memberid;
}

public void setMemberid(int memberid) {
	this.memberid = memberid;
}

public void setSupportgoal(int supportgoal) {
	this.supportgoal = supportgoal;
}

public void setVisiblePost(boolean isVisiblePost) {
	this.isVisiblePost = isVisiblePost;
}

public int getUserid() {
return memberid;
}

public void setUserid(int userid) {
this.memberid = userid;
}

public int getPostid() {
return postid;
}

public void setPostid(int postid) {
this.postid = postid;
}

public boolean isCommentnotif() {
return isCommentnotif;
}

public void setCommentnotif(boolean isCommentnotif) {
this.isCommentnotif = isCommentnotif;
}

public boolean isSupportnotif() {
return isSupportnotif;
}

public void setSupportnotif(boolean isSupportnotif) {
this.isSupportnotif = isSupportnotif;
}

public boolean isReachsupporters() {
return isReachsupporters;
}

public void setReachsupporters(boolean isReachsupporters) {
this.isReachsupporters = isReachsupporters;
}

public int getSupportgoal() {
return supportgoal;
}

public void setsupportgoal(int supportgoal) {
this.supportgoal = supportgoal;
}

public boolean isVisiblePost() {
return isVisiblePost;
}

public void setisVisiblePost(boolean isVisiblePost) {
this.isVisiblePost = isVisiblePost;
}

}

