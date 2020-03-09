package com.test;

/**
 */
import java.util.List;

/**
* Auto-generated: 2017-12-07 16:49:6
*
* @website http://www.sshfortress.org/java2pojo/
*/
public class JsonRootBean {

   private List<List<Data>> data;
   private String showMessage;
   private int status;
   public void setData(List<List<Data>> data) {
        this.data = data;
    }
    public List<List<Data>> getData() {
        return data;
    }

   public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }
    public String getShowMessage() {
        return showMessage;
    }

   public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

}