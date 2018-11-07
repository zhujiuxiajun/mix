package com.zhujiu.scale;

/**
 * @author Administrator
 *
 */
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.apache.commons.lang.StringUtils;

import com.zhujiu.util.PingUtil;
import com.zhujiu.util.ServiceUtil;

import net.sf.json.JSONArray;

public class PSMainForm {
	private static final long serialVersionUID = -2216276219179107707L;

	TrayIcon trayIcon; // 托盘图标
	SystemTray tray; // 本操作系统托盘的实例
	boolean networkOk = false; // 网络
	boolean serviceOk = false; // 服务
	public static JTextArea txt2 = new JTextArea(30, 30);

	// icon
	ImageIcon iconNORMAL = null; // 正常
	ImageIcon iconNetError = null; // 网络异常
	ImageIcon iconSVRError = null; // 服务不用

	private static JLabel lbStatus = new JLabel("采集程序启动中...");
	final JFrame frame = new JFrame("数据采集器");

	private static final String ERROR_IO = "ERRORIO";
	private static final String ERROR_URL = "ERRORURL";
	
	private PopupMenu pMenu = new PopupMenu(); // 创建弹出式菜单，下面三项是菜单项
	private MenuItem mItemCopy = new MenuItem("复制");
	private MenuItem mItemPaste = new MenuItem("粘贴");
	private MenuItem mItemCut = new MenuItem("剪切");

	private static String logFileName = null;
	private static Properties props = new Properties();
	String SvrIp = "122.112.210.4";
	String lastFlowID = "";
	String SvrPort = "8080";

	private JPopupMenu pop = null; // 弹出菜单
	private JMenuItem copy = null, paste = null, cut = null; // 三个功能菜单

	// 获取最近一次上传记录
	String urlLastID = "nbim/api/outif/delivery/lastid";
	String urlDeliveryData = "nbim/api/outif/delivery/datas";
	String urlMixData = "nbim/api/outif/mix/datas";// 上传每车配合比
	String urlhello = "nbim/api/outif/scale/mix";// 检查接口是否能调用
	String urlPartInfo = "nbim/api/stir/getBuildPartInfo";// 获取浇筑部位信息

	/**
	 * 1.创建日志文件 2.读取配置文件
	 */
	static {
		logFileName = System.getProperty("user.dir") + "/ScaleLogs.txt";
		InputStream in = null;
		try {
			in = PSMainForm.class.getResourceAsStream("/ini.properties");
			if (in != null)
				props.load(in);
		} catch (IOException e) {
			loginfo("打开属性文件 ini.properties 失败" + e.getMessage(), false);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public PSMainForm() {
		// 读取初始化参数
		SvrIp = props.getProperty("SvrIp");
		SvrPort = props.getProperty("SvrPort");

		// 程序框架
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(0);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setExtendedState(JFrame.NORMAL);
		JPanel jPane2 = new JPanel();
		/*
		 * 调用用户定义的方法并添加组件到面板【配置jPanel面板】
		 */
		placeComponents2(jPane2);
		frame.add(jPane2);
		frame.setTitle(BizCost.Message.NORMAL);
		Image image = getImagePojo("/zj.png");

		// Image image = Toolkit.getDefaultToolkit().getImage(
		// this.getClass().getResource("/zj.png"));
		frame.setIconImage(image);
		// 窗口监听器
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "确定关闭吗？", "温馨提示",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					System.exit(0); // 关闭
				}
			}
		});
		
		MouseAdapter mouseAdapter = new MouseAdapter()// 监听鼠标事件
		{
			public void mouseClicked(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON3)// 只响应鼠标右键单击事件
				{
					pMenu.show(txt2, event.getX(), event.getY());// 在鼠标位置显示弹出式菜单
				}
			}
		};
		ActionListener menuAction = new ActionListener()// 响应单击菜单项的事件，只是示例，
		{// 具体内容可自己编写
			public void actionPerformed(ActionEvent e) {
				MenuItem item = (MenuItem) e.getSource();
				if (item == mItemCopy) // 单击了“复制”菜单项
				{
					txt2.copy(); 
				} else if (item == mItemPaste) // “粘贴”菜单项
				{
					txt2.paste();  
				} else {
					txt2.cut();
				}
			}
		};
		
		txt2.add(pMenu); // 弹出式菜单加入到文本框中，否则不能显示
		txt2.addMouseListener(mouseAdapter); // 文本框加入鼠标监听器
		pMenu.add(mItemCopy); // 菜单项的单击事件监听器
		mItemCopy.addActionListener(menuAction);
		pMenu.add(mItemPaste);
		mItemPaste.addActionListener(menuAction);
		pMenu.add(mItemCut);
		mItemCut.addActionListener(menuAction);


		// 设置图片
		// con = frame.getContentPane();
		// zPanel = new ZPanel();
		// zPanel.setImagePath("main/resource/OK.png");
		// zPanel.setPreferredSize(new Dimension(zPanel.getImgWidth(), zPanel
		// .getImgHeight()));
		// imgSp = new JScrollPane();
		// imgSp.setViewportView(zPanel);
		// imgSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// imgSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// con.add(imgSp, BorderLayout.CENTER);

		// 托盘配置
		setTrayIcon();
		// 定时读取数据上传
		getDataByTimer();
		frame.setVisible(true);
	}
	
	// 图标托盘处理
	public void setTrayIcon() {
		if (SystemTray.isSupported()) {
			// 支持系统
			loginfo("支持系统托盘。", false);
			tray = SystemTray.getSystemTray(); // 获得本操作系统托盘的实例

			iconNORMAL = new ImageIcon(getImagePojo("/OK.png"));
			iconNetError = new ImageIcon(getImagePojo("/ERROR_IO.png"));
			iconSVRError = new ImageIcon(getImagePojo("/ERROR_URL.png"));

			PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单
			final MenuItem show = new MenuItem("打开程序");
			final MenuItem exit = new MenuItem("退出程序");
			pop.add(show);
			pop.add(exit);
			trayIcon = new TrayIcon(iconNORMAL.getImage(), "接口监视器", pop);// 实例化托盘图标
			trayIcon.setImageAutoSize(true);

			// 为托盘图标监听点击事件
			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2)// 鼠标双击图标
					{
						// trayIcon.displayMessage("警告", "这是一个警告提示!",
						// TrayIcon.MessageType.WARNING);
						// trayIcon.displayMessage("错误", "这是一个错误提示!",
						// TrayIcon.MessageType.ERROR);
						// trayIcon.displayMessage("信息", "这是一个信息提示!",
						// TrayIcon.MessageType.INFO);
						// tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
						frame.setExtendedState(JFrame.NORMAL);// 设置状态为正常
						frame.setVisible(true);// 显示主窗体
					}
				}
			});

			// 选项注册事件
			ActionListener al2 = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 退出程序
					if (e.getSource() == exit) {
						tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
						System.exit(0);// 退出程序
					}
					// 打开程序
					if (e.getSource() == show) {
						frame.setExtendedState(JFrame.NORMAL);// 设置状态为正常
						frame.setVisible(true);
					}
				}
			};
			exit.addActionListener(al2);
			show.addActionListener(al2);

			try {
				tray.add(trayIcon); // 将托盘图标添加到系统的托盘实例中
			} catch (AWTException ex) {
				ex.printStackTrace();
			}

			// 为主窗体注册窗体事件
			frame.addWindowListener(new WindowAdapter() {
				// 窗体最小化事件
				public void windowIconified(WindowEvent e) {
					frame.setVisible(false);// 使窗口不可视
					frame.dispose();// 释放当前窗体资源
				}
			});
		} else {
			loginfo("不支持系统托盘！", false);
		}
	}

	// 监听器，定时获取数据
	public void getDataByTimer() {
		try {
			ping();
			java.util.Timer timer = new java.util.Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					hello();
					// 返回部位数据
					getPartInfo();
					// 检测
					checkMax();
					if (isInteger(lastFlowID)) {
						List<OutMaster> newDatas = JDBCSqlServer
								.getOutMasterList(Integer.valueOf(lastFlowID));
						List<MixRecipel> mixRecipelDatas = JDBCSqlServer
								.getRecipelDeliveryList(Integer
										.valueOf(lastFlowID));
						// 数据上传
						if (newDatas.size() > 0) {
							uploadData(newDatas, urlDeliveryData);
							loginfo("上传每车出库数据:" + newDatas.size() + "条", true);
						}
						if (mixRecipelDatas.size() > 0) {
							uploadData(mixRecipelDatas, urlMixData);
							loginfo("上传每车出库每车配比数据:" + mixRecipelDatas.size()
									+ "条", true);
						}
					}

				}
			}, 0, 15000);
			// 监听结束
		} catch (Exception ex) {
			System.out.println("Timer exception");
			loginfo("上传数据异常:" + ex.getMessage() + "条", true);
		}
	}

	// 日志记录
	public static void loginfo(String msg, Boolean appendTime) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(logFileName, true));
			if (appendTime) {
				bw.newLine();
				bw.newLine();
				Calendar c = new GregorianCalendar();
				bw.write(" *****  " + c.get(Calendar.YEAR) + "年"
						+ (c.get(Calendar.MONTH) + 1) + "月"
						+ (c.get(Calendar.DAY_OF_MONTH)) + "日"
						+ c.get(Calendar.HOUR_OF_DAY) + ":"
						+ c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND)
						+ "  *****");
			}
			bw.newLine();
			bw.write(msg);
			bw.flush();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (Exception e) {
				}
				bw = null;
			}
		}
	}

	// 获取时间格式
	private static String getDateStr() {
		Calendar c = new GregorianCalendar();
		return c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月"
				+ (c.get(Calendar.DAY_OF_MONTH)) + "日"
				+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
				+ ":" + c.get(Calendar.SECOND);
	}

	// 拼装url
	private String composeUrl(String path) {
		return "http://" + SvrIp + ":" + SvrPort + "/" + path;
	}

	// 检查网络
	private boolean ping() {
		if (PingUtil.ping(SvrIp, 3, 500) == false) {
			// trayIcon.displayMessage("警告", "网络不通!",
			// TrayIcon.MessageType.WARNING);
			trayIcon.setImage(iconNetError.getImage());
			networkOk = false;
			loginfo("网络连接失败！", true);
			lbStatus.setText("网络连接失败！");
			frame.setTitle(BizCost.Message.NET_EXC);
			return false;
		} else {
			networkOk = true;
			loginfo("网络正常！", true);
			lbStatus.setText("网络正常！" + getDateStr());
			return true;
		}
	}

	// 检查服务
	private boolean hello() {
		// 检查服务是可以
		if (ServiceUtil.hello(composeUrl(urlhello)) == false) {
			// trayIcon.displayMessage("警告", "服务不可用!",
			// TrayIcon.MessageType.WARNING);
			trayIcon.setImage(iconSVRError.getImage());
			serviceOk = false;
			loginfo("服务不可用!", true);
			lbStatus.setText("服务不可用! " + getDateStr());
			frame.setTitle(BizCost.Message.INF_EXC);
			return false;
		} else {
			serviceOk = true;
			loginfo("服务正常!", true);
			lbStatus.setText("服务正常! " + getDateStr());
			frame.setTitle(BizCost.Message.NORMAL);
			return true;
		}
	}

	// 查询目前nbim系统最大 lastI值
	private String checkMax() {
		try {
			String reponse = RESTFulGetUtil.doGet(composeUrl(urlLastID));
			if (ERROR_IO.equals(reponse)) {
				trayIcon.setImage(iconSVRError.getImage());
				networkOk = false;
			} else if (ERROR_URL.equals(reponse)) {
				trayIcon.setImage(iconSVRError.getImage());
				serviceOk = false;
			} else {
				// 处理json对象
				lastFlowID = reponse;
				trayIcon.setImage(iconNORMAL.getImage());
				serviceOk = true;
			}
		} catch (Exception e) {
			lastFlowID = "";
		}
		return lastFlowID;
	}

	// 获取部位信息
	private boolean getPartInfo() {
		String reponse = RESTFulGetUtil.doGet(composeUrl(urlPartInfo));
		if (ERROR_IO.equals(reponse)) {
			loginfo("ERROR_IO,【" + urlPartInfo + "】,接口异常", true);
			return false;
		} else if (ERROR_URL.equals(reponse)) {
			loginfo("ERROR_URL,【" + urlPartInfo + "】,接口异常", true);
			return false;
		}
		if (!"".equals(reponse.toString())) {
			JSONArray json = JSONArray.fromObject(reponse);
			List<StirPlanMenuData> datas = JSONArray.toList(json,
					StirPlanMenuData.class);
			// 设置为空
			txt2.setText("");
			for (StirPlanMenuData data : datas) {
				returnString2(data.getBuildDesc());
			}
			if (datas.size() == 0) {
				returnString2("暂无浇筑部位！");
			}
		}
		return true;
	}

	// 上传数据处理
	public <T> void uploadData(List<T> datas, String url) {
		JSONArray json = JSONArray.fromObject(datas);
		String data = json.toString();
		String post = RESTFulPostUtil.doPost(composeUrl(url), data);
		// 处理json对象
		if (StringUtils.equals(post, "OK")) {
			// lastFlowID = newDatas.get(newDatas.size() -
			// 1).getWflowid();
			loginfo("上传出库数据：" + datas.size() + " 条", true);
			trayIcon.setImage(iconNORMAL.getImage());
			lbStatus.setText("数据上传成功！" + getDateStr());
		} else {
			trayIcon.setImage(iconNetError.getImage());
			lbStatus.setText("数据上传失败！" + getDateStr());
			loginfo("数据上传失败！", true);

			serviceOk = false;
		}
	}

	public static void main(String[] args) {
		// 创建托盘
		new PSMainForm();
	}

	/*
	 * 调用用户定义的方法并添加组件到面板
	 */
	private void placeComponents2(JPanel panel) {
		// // 增加个label
		// JLabel j1 = new JLabel("采集日志");
		//
		// JScrollPane scroll = new JScrollPane(txt1);
		// // 把定义的JTextArea放到JScrollPane里面去
		//
		// // 分别设置水平和垂直滚动条自动出现
		// scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// panel.add(j1);
		// panel.add(scroll);

		// 增加个label
		JLabel j2 = new JLabel("浇筑部位");

		JScrollPane scroll1 = new JScrollPane(txt2);
		// 把定义的JTextArea放到JScrollPane里面去

		// 分别设置水平和垂直滚动条自动出现
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(j2);
		panel.add(scroll1);
	}

	public void returnString2(String message) {
		Font x = new Font("Serif", 0, 16);
		txt2.setFont(x);
		txt2.append(message + "\n");
	}

	// 判断字符串是否为数字或为空
	public static boolean isInteger(String str) {
		if (str.equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	// 返回图片对象
	public Image getImagePojo(String path) {
		Image image = Toolkit.getDefaultToolkit().getImage(
				this.getClass().getResource(path));
		return image;
	}
}
