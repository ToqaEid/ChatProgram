/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObject;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author toqae
 */
public class UserMsg implements Serializable{
    private User sender;
    private User receiver;
    private String msg;
    private Date msgDate;

    public UserMsg() {
        sender = null;
        receiver = null;
        msg = null;
        msgDate = null;
    }
     public UserMsg(User sender, User receiver, String msg) {
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
    }
    
    public UserMsg(User sender, User receiver, String msg, Date msgDate) {
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
        this.msgDate = msgDate;
    }
    

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getMsg() {
        return msg;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }
    
}
