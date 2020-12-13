package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import com.example.bean.DB;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class get_all_plan
 */
@WebServlet("/get_all_plan")
public class get_all_plan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_all_plan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int  typeString =Integer.parseInt(request.getParameter("type"));
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String sql = "select * from Airport";
		Map<String,String > map = new HashMap<String, String>();
		DB db = new DB();
		db.getConn();
		 JSONObject jsonObject = new JSONObject();
		try {
			Statement statement =db.conn.createStatement();
			 ResultSet set = statement.executeQuery(sql);
			 while (set.next()) {
				 if (typeString==1) {
					map.put(set.getString(4), set.getString(4));
				}else {

					map.put(set.getString(5), set.getString(5));
				}
			}
			 JSONArray jList = new JSONArray();
			 for (String keyString : map.values()) {
				 jList.add(keyString);
			}
			 jsonObject.put("rows", jList);
			 jsonObject.put("code", 200);
			 jsonObject.put("msg", "查询成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("code", 500);
			jsonObject.put("msg", "查询失败");
		}finally {
			try {
			if (db.conn!=null) {
					db.conn.close();
				
				db.conn=null;
			}} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
