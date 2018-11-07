package com.zhujiu.scale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCSqlServer {
	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static Connection getConnection() {
		ResourceBundle resource = ResourceBundle
				.getBundle("com.zhujiu.scale.config");// 不需要文件后缀
		Connection con = null;
		String DriverName = resource.getString("DriverName");
		String dbURL = resource.getString("DbURL");
		String userName = resource.getString("UserName");
		String userPwd = resource.getString("UserPwd");
		try {
			Class.forName(DriverName); // 加载驱动
			con = DriverManager.getConnection(dbURL, userName, userPwd);
		} catch (Exception e) {
			e.printStackTrace();
			PSMainForm.loginfo("获取本地连接异常:【"+e.getMessage()+"】", true);
		}
		return con;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getOutMasterList(3).size());
		System.out.println(getRecipelDeliveryList(4).size());
	}

	/**
	 * 获取sqlServer拌合站出库信息，只获取当天实时出库信息，同步到nbim系统 查询 vnbim_delivery 视图
	 * 
	 * @param maxId
	 * @return
	 */

	public static List<OutMaster> getOutMasterList(Integer lastId) {
		// 实例化List对象
		List<OutMaster> list = new ArrayList<OutMaster>();
		String sql = "select * From vnbim_delivery where 1=1 ";
		// + " CONVERT(varchar(100),DeliveryDate, 23) = "
		// + " CONVERT(varchar(100),GETDATE(), 23)";
		if (null != lastId && lastId > 0) {
			sql += " and DeliveryID >" + lastId;
		}
		Connection con;
		Statement st;
		// 获取连接
		try {
			con = getConnection();
			st = con.createStatement();
			// 执行查询
			ResultSet rs = st.executeQuery(sql);

			// 判断光标向后移动，并判断是否有效
			while (rs.next()) {
				// 实例化OutMaster对象
				OutMaster out = new OutMaster();
				out.setDeliveryId(rs.getInt("DeliveryID"));
				out.setPartName(rs.getString("partname"));
				out.setRecipeNo(rs.getString("RecipeNo"));
				out.setDeliveryQty(rs.getFloat("deliveryQty"));
				String str = rs.getString("DeliveryDate");
				try {
					out.setEndTime(format.parse(
							str.substring(0, str.lastIndexOf("."))).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				out.setTanker(rs.getString("tanker"));
				out.setDeliveryMan(rs.getString("deliveryMan"));
				out.setOutCount(rs.getInt("OutCount"));// 出库车次序号
				out.setEndCount(rs.getFloat("endcount"));// 当前累计方量
				out.setRcpcement(rs.getFloat("rcpcement"));// //水泥 单位Kg
				out.setActualcement(rs.getFloat("actualcement"));
				out.setRcpstone(rs.getFloat("rcpstone"));
				out.setActualstone(rs.getFloat("Actualstone"));
				out.setRcpstone25(rs.getFloat("rcpstone25"));
				out.setActualstone25(rs.getFloat("Actualstone25"));
				out.setRcpsandNature(rs.getFloat("rcpsandNature"));
				out.setActualsandNature(rs.getFloat("actualsandNature"));
				out.setRcpsand(rs.getFloat("rcpsand"));
				out.setActualsand(rs.getFloat("actualsand"));
				out.setRcpflyash(rs.getFloat("rcpflyash"));
				out.setActualflyash(rs.getFloat("actualflyash"));
				out.setRcpadditive(rs.getFloat("rcpadditive"));
				out.setActualadditive(rs.getFloat("actualadditive"));
				out.setRcpwater(rs.getFloat("rcpwater"));
				out.setActualwater(rs.getFloat("actualwater"));
				out.setRcpoth1(rs.getFloat("rcpoth1"));
				out.setActualoth1(rs.getFloat("ActualOth1"));
				out.setRcpoth2(rs.getFloat("rcpoth2"));
				out.setActualoth2(rs.getFloat("ActualOth2"));
				list.add(out);
			}
			rs.close(); // 关闭ResultSet
			st.close(); // 关闭Statement
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return list;
	}

	/**
	 * 获取没车配合比 ， 同步到nbim系统中间表
	 * 
	 * @param lastId
	 * @return
	 */
	public static List<MixRecipel> getRecipelDeliveryList(Integer lastId) {
		// 实例化List对象
		List<MixRecipel> list = new ArrayList<MixRecipel>();
		String sql = "select * From vnbim_recipel_delivery where 1=1 ";
		if (null != lastId && lastId > 0) {
			sql += " and DeliveryID >" + lastId;
		}
		Connection con;
		Statement st;
		// 获取连接
		try {
			con = getConnection();
			st = con.createStatement();
			// 执行查询
			ResultSet rs = st.executeQuery(sql);

			// 判断光标向后移动，并判断是否有效
			while (rs.next()) {
				// 实例化OutMaster对象
				MixRecipel out = new MixRecipel();
				out.setDeliveryId(rs.getInt("DeliveryID"));
				out.setPartName(rs.getString("partname"));
				out.setRecipeNo(rs.getString("RecipeNo"));
				String str = rs.getString("DeliveryDate");
				try {
					out.setDeliveryDate(format.parse(
							str.substring(0, str.lastIndexOf("."))).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				out.setCement(rs.getFloat("cement"));
				out.setStone(rs.getFloat("stone"));
				out.setStone25(rs.getFloat("stone25"));
				out.setSandNature(rs.getFloat("sandNature"));
				out.setSand(rs.getFloat("sand"));
				out.setAdditive(rs.getFloat("additive"));
				out.setFlyash(rs.getFloat("flyash"));
				out.setOth1(rs.getFloat("oth1"));
				out.setOth2(rs.getFloat("oth2"));
				out.setWater(rs.getFloat("water"));
				out.setTld(rs.getString("tld"));
				list.add(out);
			}
			rs.close(); // 关闭ResultSet
			st.close(); // 关闭Statement
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return list;
	}

}
