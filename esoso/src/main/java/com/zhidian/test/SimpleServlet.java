
/**
* @Title: SimpleServlet.java
* @Package com.zhidian.test
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-20 上午12:41:58
* @version V1.0
*/
package com.zhidian.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: SimpleServlet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-20 上午12:41:58
 *
 */
public class SimpleServlet extends HttpServlet {

	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("in... do get!");
		super.doGet(req, resp);
	}

}
