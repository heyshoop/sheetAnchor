package cn.sheetanchor.wechat.entity.message;

/** 
 * 消息基类（普通用户-> 公众帐号）
 *  
 */
public class TextMessage extends BaseMessage {
	
	// 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }   
}
