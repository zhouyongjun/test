package com.zyj.telnet.entry.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.zyj.telnet.TelnetManager;
import com.zyj.telnet.entry.ExecuteResult;
import com.zyj.telnet.entry.Server;
import com.zyj.telnet.entry.cmd.Cmd;
import com.zyj.telnet.entry.cmd.Param;
import com.zyj.telnet.uitl.TelnetUtil;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 486467116846619083L;
	private JPanel contentPane;
	JComboBox jcomboBox_cmd;
	JComboBox comboBox_server;
	JComboBox comboBox_type;
	JLabel label_10;
	TelnetManager telnetMgr = TelnetManager.getInstance();
	private List<JLabel> jlabels_list = new ArrayList<JLabel>();
	private List<JTextField> jtextFields_list = new ArrayList<JTextField>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				for (Server server : telnetMgr.getServer_list()) {
					server.close();
				}
			}
		});
		setTitle("\u540E\u53F0\u64CD\u4F5C\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 547, 395);
		TelnetUtil.SetCompomentBound(this, 547, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u547D\u4EE4\uFF1A");
		
		jcomboBox_cmd = new JComboBox(telnetMgr.getCmd_list().toArray());
		jcomboBox_cmd.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setParamListVisiable((Cmd)jcomboBox_cmd.getSelectedItem());
			}
		});
		
		JButton button_exec = new JButton("\u6267\u884C");
		button_exec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cmd cmd = (Cmd)jcomboBox_cmd.getSelectedItem();
				List<Param> param_list = cmd.getParam_list();
				Server server = (Server) comboBox_server.getSelectedItem();
				for (int i=0;i<param_list.size();i++) {
					Param p = param_list.get(i);
					JTextField field = jtextFields_list.get(i);
					if(!field.isVisible()) {
						JOptionPane.showMessageDialog(MainFrame.this, "有参数未展现出来，赶快请求火力支援！！！", "执行结果", JOptionPane.ERROR_MESSAGE);
						return;
					}
					p.setValue(field.getText());
				}
				ExecuteResult result = null;
				if (comboBox_type.isVisible()) {
					result = server.cmd_telnet(cmd,comboBox_type.getSelectedIndex());
				}else {
					result = server.cmd_telnet(cmd);
				}
				if (result.isSucc()) {
					JOptionPane.showMessageDialog(MainFrame.this, result.getMsg(), "服务器："+server.toString(), JOptionPane.INFORMATION_MESSAGE);					
				}else {
					JOptionPane.showMessageDialog(MainFrame.this, result.getMsg(), "服务器："+server.toString(), JOptionPane.ERROR_MESSAGE);	
				}
				}
		});
		
		JLabel label_1 = new JLabel("\u53C2\u65701\uFF1A");
		
		JLabel label_2 = new JLabel("\u53C2\u65702\uFF1A");
		
		JLabel label_3 = new JLabel("\u53C2\u65703\uFF1A");
		
		JLabel label_4 = new JLabel("\u53C2\u65704\uFF1A");
		
		JLabel label_5 = new JLabel("\u53C2\u65705\uFF1A");
		
		JLabel label_6 = new JLabel("\u53C2\u65706\uFF1A");
		
		JLabel label_7 = new JLabel("\u53C2\u65707\uFF1A");
		
		JLabel label_8 = new JLabel("\u53C2\u65708\uFF1A");
		
		jlabels_list.add(label_1);
		jlabels_list.add(label_2);
		jlabels_list.add(label_3);
		jlabels_list.add(label_4);
		jlabels_list.add(label_5);
		jlabels_list.add(label_6);
		jlabels_list.add(label_7);
		jlabels_list.add(label_8);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JTextField textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JTextField textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JTextField textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JTextField textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JTextField textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		jtextFields_list.add(textField_1);
		jtextFields_list.add(textField_2);
		jtextFields_list.add(textField_3);
		jtextFields_list.add(textField_4);
		jtextFields_list.add(textField_5);
		jtextFields_list.add(textField_6);
		jtextFields_list.add(textField_7);
		jtextFields_list.add(textField_8);
		
		comboBox_server = new JComboBox(telnetMgr.getServer_list().toArray());
		
		JLabel label_9 = new JLabel("\u670D\u52A1\u5668\uFF1A");
		
		label_10 = new JLabel("\u540D\u5B57\u7C7B\u578B\uFF1A");
		
		comboBox_type = new JComboBox();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(label_9)
						.addComponent(label_10))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_type, 0, 137, Short.MAX_VALUE)
								.addComponent(jcomboBox_cmd, 0, 137, Short.MAX_VALUE)
								.addComponent(comboBox_server, 0, 137, Short.MAX_VALUE))
							.addGap(48)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_5)
								.addComponent(label_1)
								.addComponent(label_4)
								.addComponent(label_3)
								.addComponent(label_2)
								.addComponent(label_6)
								.addComponent(label_8)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(195)
							.addComponent(label_7)))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
						.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
						.addComponent(textField_8, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
						.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
						.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
						.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
						.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
						.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
					.addContainerGap(50, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(215)
					.addComponent(button_exec, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(228, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(24)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(25)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(jcomboBox_cmd, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(4)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_3))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(24)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBox_server, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_9))))
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_5))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_6))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_7)
										.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(45)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBox_type, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_10)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(242)
							.addComponent(label_8)))
					.addGap(31)
					.addComponent(button_exec, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		contentPane.setLayout(gl_contentPane);
		init();
	}
	/**
	 * 初始化参数
	 */
	public void init() {
		setParamListVisiable((Cmd)jcomboBox_cmd.getSelectedItem());
	}
	
	/**
	 * 设置参数可见
	 * @param param_list
	 * @param size
	 */
	public void setParamListVisiable(Cmd cmd) {
		setParamListNotVisiable(jlabels_list.size());
		List<Param> param_list = cmd.getParam_list();
		for (int i=0;i<param_list.size();i++) {
			Param param = param_list.get(i);
			JLabel jlabel = jlabels_list.get(i);
			JTextField field = jtextFields_list.get(i);
			jlabel.setVisible(true);
			jlabel.setText(param.getName());
			field.setVisible(true);
			field.setText(param.getValue() == null ? "" : param.getValue());
		}
		if (cmd.getType_names().size() > 0) {
			comboBox_type.setVisible(true);
			label_10.setVisible(true);
			comboBox_type.setModel(new DefaultComboBoxModel(cmd.getType_names().toArray()));
		}
	
	}
	
	/**
	 * 设置所有参数不可见
	 * @param size
	 */
	public void setParamListNotVisiable(int size) {
		for (int i=0;i<size;i++) {
			JLabel jlabel = jlabels_list.get(i);
			JTextField field = jtextFields_list.get(i);
			jlabel.setVisible(false);
			field.setVisible(false);
		}
		comboBox_type.setVisible(false);
		label_10.setVisible(false);
	}
	
	public void setjtextFieldValue(int index,String value) {
		jtextFields_list.get(index).setText(value);
	}
}
