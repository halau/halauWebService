package jp.sample.jaxrs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.sample.jaxrs.domain.Item;

public class PostgresqlConnector {
	
	public Item[] pullRecords(){
		// 結果準備
		Item[] results = null;
		try {
			String url = "jdbc:postgresql://163.43.32.167/money";
			String username = "postgres";
			String password = "tametame";
			Class.forName("org.postgresql.Driver");    
			Connection db = DriverManager.getConnection(url, username, password);
			// 件数取得
			PreparedStatement countSt = 
				db.prepareStatement("SELECT COUNT(*) FROM buyinfo_mst;");
			ResultSet countRs = countSt.executeQuery();
			int count = 0;
			while (countRs.next()){
				count = countRs.getInt(1);
			}
			countRs.close();
			results = new Item[count];
			// 結果取得
			PreparedStatement selectSt = 
				db.prepareStatement("SELECT * FROM buyinfo_mst;");
			ResultSet selectRs = selectSt.executeQuery();
			selectRs.getRow();
			
			int cnt = 0;
			while(selectRs.next()){
				Item tempItem = new Item();
				tempItem.setPrice(selectRs.getInt("money"));
				tempItem.setItemname((String)selectRs.getObject("item_name"));
				tempItem.setLocation((String)selectRs.getObject("buyinfo_note"));
				tempItem.setCategory((String)selectRs.getObject("item_cd"));
				tempItem.setCreatedAt(selectRs.getDate("regist_date"));
				tempItem.setUpdatedAt(selectRs.getDate("last_update"));
				tempItem.setId((String)selectRs.getObject("buyinfo_id"));
				results[cnt] = (Item)tempItem.clone();
				cnt++;
			}
			selectRs.close();
			db.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public int putRecord(int price, String itemname){
		int insertNum = 0;
		try {
			String url = "jdbc:postgresql://163.43.32.167/money";
			String username = "postgres";
			String password = "tametame";
			Class.forName("org.postgresql.Driver");    
			Connection db = DriverManager.getConnection(url, username, password);

			PreparedStatement selectSeqSt =
				db.prepareStatement("SELECT LPAD(NEXTVAL('seq_buyinfo_mst')::text, 10, '0')");
			ResultSet rs = selectSeqSt.executeQuery();
			rs.next();
			String buyinfoId = rs.getString(1);
			
			PreparedStatement insertSt = 
				db.prepareStatement("INSERT INTO buyinfo_mst (buyinfo_id, money, item_name, item_cd, cycle_buy_id, buyout_date, last_update) VALUES (?, ?, ?, '3', '1', current_date, current_date);");
			insertSt.setString(1, buyinfoId);
			insertSt.setInt(2, price);
			insertSt.setString(3, itemname);
			insertNum = insertSt.executeUpdate();
			System.out.println(insertNum + "件データを挿入しました。");
			insertSt.close();
			db.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insertNum;
	}
}
