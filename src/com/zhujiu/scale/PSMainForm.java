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

	TrayIcon trayIcon; // ����ͼ��
	SystemTray tray; // ������ϵͳ���̵�ʵ��
	boolean networkOk = false; // ����
	boolean serviceOk = false; // ����
	public static JTextArea txt2 = new JTextArea(30, 30);

	// icon
	ImageIcon iconNORMAL = null; // ����
	ImageIcon iconNetError = null; // �����쳣
	ImageIcon iconSVRError = null; // ������

	private static JLabel lbStatus = new JLabel("�ɼ�����������...");
	final JFrame frame = new JFrame("���ݲɼ���");

	private static final String ERROR_IO = "ERRORIO";
	private static final String ERROR_URL = "ERRORURL";
	
	private PopupMenu pMenu = new PopupMenu(); // ��������ʽ�˵������������ǲ˵���
	private MenuItem mItemCopy = new MenuItem("����");
	private MenuItem mItemPaste = new MenuItem("ճ��");
	private MenuItem mItemCut = new MenuItem("����");

	private static String logFileName = null;
	private static Properties props = new Properties();
	String SvrIp = "122.112.210.4";
	String lastFlowID = "";
	String SvrPort = "8080";

	private JPopupMenu pop = null; // �����˵�
	private JMenuItem copy = null, paste = null, cut = null; // �������ܲ˵�

	// ��ȡ���һ���ϴ���¼
	String urlLastID = "nbim/api/outif/delivery/lastid";
	String urlDeliveryData = "nbim/api/outif/delivery/datas";
	String urlMixData = "nbim/api/outif/mix/datas";// �ϴ�ÿ����ϱ�
	String urlhello = "nbim/api/outif/scale/mix";// ���ӿ��Ƿ��ܵ���
	String urlPartInfo = "nbim/api/stir/getBuildPartInfo";// ��ȡ������λ��Ϣ

	/**
	 * 1.������־�ļ� 2.��ȡ�����ļ�
	 */
	static {
		logFileName = System.getProperty("user.dir") + "/ScaleLogs.txt";
		InputStream in = null;
		try {
			in = PSMainForm.class.getResourceAsStream("/ini.properties");
			if (in != null)
				props.load(in);
		} catch (IOException e) {
			loginfo("�������ļ� ini.properties ʧ��" + e.getMessage(), false);
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
		// ��ȡ��ʼ������
		SvrIp = props.getProperty("SvrIp");
		SvrPort = props.getProperty("SvrPort");

		// ������
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(0);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setExtendedState(JFrame.NORMAL);
		JPanel jPane2 = new JPanel();
		/*
		 * �����û�����ķ���������������塾����jPanel��塿
		 */
		placeComponents2(jPane2);
		frame.add(jPane2);
		frame.setTitle(BizCost.Message.NORMAL);
		Image image = getImagePojo("/zj.png");

		// Image image = Toolkit.getDefaultToolkit().getImage(
		// this.getClass().getResource("/zj.png"));
		frame.setIconImage(image);
		// ���ڼ�����
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "ȷ���ر���", "��ܰ��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					System.exit(0); // �ر�
				}
			}
		});
		
		MouseAdapter mouseAdapter = new MouseAdapter()// ��������¼�
		{
			public void mouseClicked(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON3)// ֻ��Ӧ����Ҽ������¼�
				{
					pMenu.show(txt2, event.getX(), event.getY());// �����λ����ʾ����ʽ�˵�
				}
			}
		};
		ActionListener menuAction = new ActionListener()// ��Ӧ�����˵�����¼���ֻ��ʾ����
		{// �������ݿ��Լ���д
			public void actionPerformed(ActionEvent e) {
				MenuItem item = (MenuItem) e.getSource();
				if (item == mItemCopy) // �����ˡ����ơ��˵���
				{
					txt2.copy(); 
				} else if (item == mItemPaste) // ��ճ�����˵���
				{
					txt2.paste();  
				} else {
					txt2.cut();
				}
			}
		};
		
		txt2.add(pMenu); // ����ʽ�˵����뵽�ı����У���������ʾ
		txt2.addMouseListener(mouseAdapter); // �ı��������������
		pMenu.add(mItemCopy); // �˵���ĵ����¼�������
		mItemCopy.addActionListener(menuAction);
		pMenu.add(mItemPaste);
		mItemPaste.addActionListener(menuAction);
		pMenu.add(mItemCut);
		mItemCut.addActionListener(menuAction);


		// ����ͼƬ
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

		// ��������
		setTrayIcon();
		// ��ʱ��ȡ�����ϴ�
		getDataByTimer();
		frame.setVisible(true);
	}
	
	// ͼ�����̴���
	public void setTrayIcon() {
		if (SystemTray.isSupported()) {
			// ֧��ϵͳ
			loginfo("֧��ϵͳ���̡�", false);
			tray = SystemTray.getSystemTray(); // ��ñ�����ϵͳ���̵�ʵ��

			iconNORMAL = new ImageIcon(getImagePojo("/OK.png"));
			iconNetError = new ImageIcon(getImagePojo("/ERROR_IO.png"));
			iconSVRError = new ImageIcon(getImagePojo("/ERROR_URL.png"));

			PopupMenu pop = new PopupMenu(); // ����һ���Ҽ�����ʽ�˵�
			final MenuItem show = new MenuItem("�򿪳���");
			final MenuItem exit = new MenuItem("�˳�����");
			pop.add(show);
			pop.add(exit);
			trayIcon = new TrayIcon(iconNORMAL.getImage(), "�ӿڼ�����", pop);// ʵ��������ͼ��
			trayIcon.setImageAutoSize(true);

			// Ϊ����ͼ���������¼�
			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2)// ���˫��ͼ��
					{
						// trayIcon.displayMessage("����", "����һ��������ʾ!",
						// TrayIcon.MessageType.WARNING);
						// trayIcon.displayMessage("����", "����һ��������ʾ!",
						// TrayIcon.MessageType.ERROR);
						// trayIcon.displayMessage("��Ϣ", "����һ����Ϣ��ʾ!",
						// TrayIcon.MessageType.INFO);
						// tray.remove(trayIcon); // ��ϵͳ������ʵ�����Ƴ�����ͼ��
						frame.setExtendedState(JFrame.NORMAL);// ����״̬Ϊ����
						frame.setVisible(true);// ��ʾ������
					}
				}
			});

			// ѡ��ע���¼�
			ActionListener al2 = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// �˳�����
					if (e.getSource() == exit) {
						tray.remove(trayIcon); // ��ϵͳ������ʵ�����Ƴ�����ͼ��
						System.exit(0);// �˳�����
					}
					// �򿪳���
					if (e.getSource() == show) {
						frame.setExtendedState(JFrame.NORMAL);// ����״̬Ϊ����
						frame.setVisible(true);
					}
				}
			};
			exit.addActionListener(al2);
			show.addActionListener(al2);

			try {
				tray.add(trayIcon); // ������ͼ����ӵ�ϵͳ������ʵ����
			} catch (AWTException ex) {
				ex.printStackTrace();
			}

			// Ϊ������ע�ᴰ���¼�
			frame.addWindowListener(new WindowAdapter() {
				// ������С���¼�
				public void windowIconified(WindowEvent e) {
					frame.setVisible(false);// ʹ���ڲ�����
					frame.dispose();// �ͷŵ�ǰ������Դ
				}
			});
		} else {
			loginfo("��֧��ϵͳ���̣�", false);
		}
	}

	// ����������ʱ��ȡ����
	public void getDataByTimer() {
		try {
			ping();
			java.util.Timer timer = new java.util.Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					hello();
					// ���ز�λ����
					getPartInfo();
					// ���
					checkMax();
					if (isInteger(lastFlowID)) {
						List<OutMaster> newDatas = JDBCSqlServer
								.getOutMasterList(Integer.valueOf(lastFlowID));
						List<MixRecipel> mixRecipelDatas = JDBCSqlServer
								.getRecipelDeliveryList(Integer
										.valueOf(lastFlowID));
						// �����ϴ�
						if (newDatas.size() > 0) {
							uploadData(newDatas, urlDeliveryData);
							loginfo("�ϴ�ÿ����������:" + newDatas.size() + "��", true);
						}
						if (mixRecipelDatas.size() > 0) {
							uploadData(mixRecipelDatas, urlMixData);
							loginfo("�ϴ�ÿ������ÿ���������:" + mixRecipelDatas.size()
									+ "��", true);
						}
					}

				}
			}, 0, 15000);
			// ��������
		} catch (Exception ex) {
			System.out.println("Timer exception");
			loginfo("�ϴ������쳣:" + ex.getMessage() + "��", true);
		}
	}

	// ��־��¼
	public static void loginfo(String msg, Boolean appendTime) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(logFileName, true));
			if (appendTime) {
				bw.newLine();
				bw.newLine();
				Calendar c = new GregorianCalendar();
				bw.write(" *****  " + c.get(Calendar.YEAR) + "��"
						+ (c.get(Calendar.MONTH) + 1) + "��"
						+ (c.get(Calendar.DAY_OF_MONTH)) + "��"
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

	// ��ȡʱ���ʽ
	private static String getDateStr() {
		Calendar c = new GregorianCalendar();
		return c.get(Calendar.YEAR) + "��" + (c.get(Calendar.MONTH) + 1) + "��"
				+ (c.get(Calendar.DAY_OF_MONTH)) + "��"
				+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
				+ ":" + c.get(Calendar.SECOND);
	}

	// ƴװurl
	private String composeUrl(String path) {
		return "http://" + SvrIp + ":" + SvrPort + "/" + path;
	}

	// �������
	private boolean ping() {
		if (PingUtil.ping(SvrIp, 3, 500) == false) {
			// trayIcon.displayMessage("����", "���粻ͨ!",
			// TrayIcon.MessageType.WARNING);
			trayIcon.setImage(iconNetError.getImage());
			networkOk = false;
			loginfo("��������ʧ�ܣ�", true);
			lbStatus.setText("��������ʧ�ܣ�");
			frame.setTitle(BizCost.Message.NET_EXC);
			return false;
		} else {
			networkOk = true;
			loginfo("����������", true);
			lbStatus.setText("����������" + getDateStr());
			return true;
		}
	}

	// ������
	private boolean hello() {
		// �������ǿ���
		if (ServiceUtil.hello(composeUrl(urlhello)) == false) {
			// trayIcon.displayMessage("����", "���񲻿���!",
			// TrayIcon.MessageType.WARNING);
			trayIcon.setImage(iconSVRError.getImage());
			serviceOk = false;
			loginfo("���񲻿���!", true);
			lbStatus.setText("���񲻿���! " + getDateStr());
			frame.setTitle(BizCost.Message.INF_EXC);
			return false;
		} else {
			serviceOk = true;
			loginfo("��������!", true);
			lbStatus.setText("��������! " + getDateStr());
			frame.setTitle(BizCost.Message.NORMAL);
			return true;
		}
	}

	// ��ѯĿǰnbimϵͳ��� lastIֵ
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
				// ����json����
				lastFlowID = reponse;
				trayIcon.setImage(iconNORMAL.getImage());
				serviceOk = true;
			}
		} catch (Exception e) {
			lastFlowID = "";
		}
		return lastFlowID;
	}

	// ��ȡ��λ��Ϣ
	private boolean getPartInfo() {
		String reponse = RESTFulGetUtil.doGet(composeUrl(urlPartInfo));
		if (ERROR_IO.equals(reponse)) {
			loginfo("ERROR_IO,��" + urlPartInfo + "��,�ӿ��쳣", true);
			return false;
		} else if (ERROR_URL.equals(reponse)) {
			loginfo("ERROR_URL,��" + urlPartInfo + "��,�ӿ��쳣", true);
			return false;
		}
		if (!"".equals(reponse.toString())) {
			JSONArray json = JSONArray.fromObject(reponse);
			List<StirPlanMenuData> datas = JSONArray.toList(json,
					StirPlanMenuData.class);
			// ����Ϊ��
			txt2.setText("");
			for (StirPlanMenuData data : datas) {
				returnString2(data.getBuildDesc());
			}
			if (datas.size() == 0) {
				returnString2("���޽�����λ��");
			}
		}
		return true;
	}

	// �ϴ����ݴ���
	public <T> void uploadData(List<T> datas, String url) {
		JSONArray json = JSONArray.fromObject(datas);
		String data = json.toString();
		String post = RESTFulPostUtil.doPost(composeUrl(url), data);
		// ����json����
		if (StringUtils.equals(post, "OK")) {
			// lastFlowID = newDatas.get(newDatas.size() -
			// 1).getWflowid();
			loginfo("�ϴ��������ݣ�" + datas.size() + " ��", true);
			trayIcon.setImage(iconNORMAL.getImage());
			lbStatus.setText("�����ϴ��ɹ���" + getDateStr());
		} else {
			trayIcon.setImage(iconNetError.getImage());
			lbStatus.setText("�����ϴ�ʧ�ܣ�" + getDateStr());
			loginfo("�����ϴ�ʧ�ܣ�", true);

			serviceOk = false;
		}
	}

	public static void main(String[] args) {
		// ��������
		new PSMainForm();
	}

	/*
	 * �����û�����ķ����������������
	 */
	private void placeComponents2(JPanel panel) {
		// // ���Ӹ�label
		// JLabel j1 = new JLabel("�ɼ���־");
		//
		// JScrollPane scroll = new JScrollPane(txt1);
		// // �Ѷ����JTextArea�ŵ�JScrollPane����ȥ
		//
		// // �ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
		// scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// panel.add(j1);
		// panel.add(scroll);

		// ���Ӹ�label
		JLabel j2 = new JLabel("������λ");

		JScrollPane scroll1 = new JScrollPane(txt2);
		// �Ѷ����JTextArea�ŵ�JScrollPane����ȥ

		// �ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
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

	// �ж��ַ����Ƿ�Ϊ���ֻ�Ϊ��
	public static boolean isInteger(String str) {
		if (str.equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	// ����ͼƬ����
	public Image getImagePojo(String path) {
		Image image = Toolkit.getDefaultToolkit().getImage(
				this.getClass().getResource(path));
		return image;
	}
}
