package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bean.DB;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class get_air_palen
 */
@WebServlet("/get_air_palen")
public class get_air_palen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_air_palen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String sql = "select * from Airport where sure='0'";
		DB db = new DB();
		db.getConn();
		 JSONObject jsonObject = new JSONObject();
		try {
			Statement statement =db.conn.createStatement();
			 ResultSet set = statement.executeQuery(sql);
			 List<JSONObject> jList = new ArrayList<JSONObject>();
			 while (set.next()) {
				 JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("id", set.getInt(1));
					jsonObject2.put("date", set.getString(2));
					jsonObject2.put("time", set.getString(3));
					jsonObject2.put("departureAirport", set.getString(4));
					jsonObject2.put("arrivalAirport", set.getString(5));
					jsonObject2.put("fightNumber", set.getString(6));
					jsonObject2.put("aircraftType", set.getString(7));
					jsonObject2.put("priceEconomy", set.getString(8));
					jsonObject2.put("priceBusiness", set.getString(9));
					jsonObject2.put("priceFirstClass", set.getString(10));
					jList.add(jsonObject2);
			}
			 jsonObject.put("total", jList.size());
			 jsonObject.put("rows", jList.toString());
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
