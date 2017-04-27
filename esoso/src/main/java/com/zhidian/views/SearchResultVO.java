
/**
* @Title: SearchResultVO.java
* @Package com.zhidian.views
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-21 上午3:33:05
* @version V1.0
*/
package com.zhidian.views;

/**
 * @ClassName: SearchResultVO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-21 上午3:33:05
 *
 */
public class SearchResultVO {
	private String title;
	private String contents;
	private String url;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/* (非 Javadoc)
	* <p>Title: toString</p>
	* <p>Description: </p>
	* @return
	* @see java.lang.Object#toString()
	*/
	@Override
	public String toString() {
		return "SearchResultVO [title=" + title + ", contents=" + contents
				+ ", url=" + url + "]";
	}
	
}
