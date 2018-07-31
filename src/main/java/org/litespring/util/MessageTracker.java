package org.litespring.util;/**
 * Created by DELL on 2018/7/31.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * user is lwb
 **/


public class MessageTracker {
    private static List<String> TRACKER_MESSAGES = new ArrayList<String>();

    public static void addMsg(String msg){
        TRACKER_MESSAGES.add(msg);
    }

    public static void clearMsg(){
        TRACKER_MESSAGES.clear();
    }

    public static List<String> getMsgs(){
        return TRACKER_MESSAGES;
    }

}
