import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class CalcFrame {

	private JFrame frame;
	private static JTextField txtInput, txtMemory, decimalMemory;
	private static JRadioButton btn_Decimal, btn_Hexadecimal, btn_Octal, btn_Binary;
	private static JLabel lbl_Dec, lbl_Hex, lbl_Oct, lbl_Bin, solved;
	private static boolean lastEntryOperator = false;
	private static String equalsMemory;
	private static int unclosedParaCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalcFrame window = new CalcFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void operandClicked(String btnId){
		checkforZero();
		if(lastEntryOperator){
			txtInput.setText("");
		}
		txtInput.setText(txtInput.getText() + btnId);
		update();
		lastEntryOperator = false;
	}
	
	public static void operatorClicked(String btnId){
		memoryUpdate();
		if(lastEntryOperator){
			txtMemory.setText(txtMemory.getText().substring(0, txtMemory.getText().length() - txtInput.getText().length() - 3));
			decimalMemory.setText(decimalMemory.getText().substring(0, decimalMemory.getText().length() - txtInput.getText().length() - 3));
		}
		decimalMemory.setText(decimalMemory.getText() + btnId);
		txtMemory.setText(txtMemory.getText() + txtInput.getText() + btnId);
		lastEntryOperator = true;
		
	}
	
	public static void update(){
		if(btn_Decimal.isSelected()){
			lbl_Dec.setText(txtInput.getText());
			lbl_Bin.setText(Integer.toBinaryString(Integer.parseInt(txtInput.getText())));
			lbl_Oct.setText(Integer.toOctalString(Integer.parseInt(txtInput.getText())));
			lbl_Hex.setText(Integer.toHexString(Integer.parseInt(txtInput.getText().toUpperCase())));
			while(lbl_Bin.getText().length() % 4 != 0 && Integer.parseInt(lbl_Bin.getText(), 2) != 0) {
				lbl_Bin.setText("0" + lbl_Bin.getText());
			}
		}
		if(btn_Hexadecimal.isSelected()) {
			lbl_Hex.setText(txtInput.getText().toUpperCase());
			int decimal = Integer.parseInt(txtInput.getText(), 16);
			lbl_Dec.setText(""+ decimal);
			lbl_Oct.setText(Integer.toOctalString(decimal));
			lbl_Bin.setText(Integer.toBinaryString(decimal));
			while(lbl_Bin.getText().length() % 4 != 0 && Integer.parseInt(lbl_Bin.getText(), 2) != 0) {
				lbl_Bin.setText("0" + lbl_Bin.getText());
			}
			
		}
		if(btn_Octal.isSelected()) {
			lbl_Oct.setText(txtInput.getText());
			int decimal = Integer.parseInt(txtInput.getText(), 8);
			lbl_Dec.setText(""+ decimal);
			lbl_Hex.setText(Integer.toHexString(decimal).toUpperCase());
			lbl_Bin.setText(Integer.toBinaryString(decimal));
			while(lbl_Bin.getText().length() % 4 != 0 && Integer.parseInt(lbl_Bin.getText(), 2) != 0) {
				lbl_Bin.setText("0" + lbl_Bin.getText());
			}
			
		}
		if(btn_Binary.isSelected()) {
			lbl_Bin.setText(txtInput.getText());
			int decimal = Integer.parseInt(txtInput.getText(), 2);
			lbl_Dec.setText(""+ decimal);
			lbl_Oct.setText(Integer.toOctalString(decimal));
			lbl_Hex.setText(Integer.toHexString(decimal).toUpperCase());
			while(lbl_Bin.getText().length() % 4 != 0 && Integer.parseInt(lbl_Bin.getText(), 2) != 0) {
				lbl_Bin.setText("0" + lbl_Bin.getText());
			}			
		}
		
		lbl_Hex.setText(lbl_Hex.getText().toUpperCase());
	}
	
	public static void memoryUpdate(){
		if(txtInput.getText().length() > 0){
			if(btn_Decimal.isSelected()){
				decimalMemory.setText(decimalMemory.getText() + txtInput.getText());
			}
			if(btn_Hexadecimal.isSelected()) {

				int decimal = Integer.parseInt(txtInput.getText(), 16);
				decimalMemory.setText(decimalMemory.getText() + decimal);
				
			}
			if(btn_Octal.isSelected()) {
				int decimal = Integer.parseInt(txtInput.getText(), 8);
				decimalMemory.setText(decimalMemory.getText() + decimal);
				
			}
			if(btn_Binary.isSelected()) {
				int decimal = Integer.parseInt(txtInput.getText(), 2);
				decimalMemory.setText(decimalMemory.getText() + decimal);
				
			}
		}
	}
	
	public static void checkforZero(){
		if(txtInput.getText().length() == 0){
			txtInput.setText("0");
		}
		if(txtInput.getText().length() == 1 && txtInput.getText().startsWith("0")){
			txtInput.setText("");
		}
		if(solved.getText().startsWith("1")){
			txtInput.setText("");
			solved.setText("0");
		}

	}
	
	/**
	 * Create the application.
	 */
	public CalcFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Font f = new Font(null, 0, 20);
		frame = new JFrame("Calculator");
		frame.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 20));
		frame.getContentPane().setBackground(UIManager.getColor("Button.Background"));
		frame.setBounds(100, 100, 315, 573);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[45][45][45:45:45][45][45][45]", "[][40.00][][][][][][14.00][][][43][43][43][43][43][43][43]"));
		frame.setFocusable(true);
		unclosedParaCount = 0;
		JButton btnKeypad = new JButton("Kpd");
		btnKeypad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnKeypad.setBackground(UIManager.getColor("Button.background"));
		btnKeypad.setEnabled(false);
		btnKeypad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnKeypad.setBorder(null);
		
		JLabel label_4 = new JLabel("≡");
		frame.getContentPane().add(label_4, "cell 0 0 1 2,alignx center,growy");
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		JLabel lblProgrammer = new JLabel("Programmer");
		frame.getContentPane().add(lblProgrammer, "cell 1 0 2 2,growx");
		lblProgrammer.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		solved = new JLabel("0");
		solved.setVisible(false);
		frame.getContentPane().add(solved, "cell 5 1");
		
		decimalMemory = new JTextField();
		decimalMemory.setBackground(Color.WHITE);
		decimalMemory.setEnabled(true);
		decimalMemory.setEditable(false);
		decimalMemory.setVisible(false);
		frame.getContentPane().add(decimalMemory, "cell 0 2,growx");
		decimalMemory.setColumns(10);
		
		txtMemory = new JTextField();
		txtMemory.setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().add(txtMemory, "cell 0 3 6 1,grow");
		txtMemory.setColumns(10);
		txtMemory.setEditable(false);
		txtMemory.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMemory.setBorder(null);
		
		txtInput = new JTextField();
		txtInput.setBackground(UIManager.getColor("Button.background"));
		txtInput.setText("0");
		frame.getContentPane().add(txtInput, "cell 0 4 6 2,grow");
		txtInput.setFont(new Font("Segoe UI", Font.BOLD, 20));
		txtInput.setEditable(false);
		txtInput.setHorizontalAlignment(SwingConstants.RIGHT);
		txtInput.setBorder(null);
		
		btn_Hexadecimal = new JRadioButton("HEX");
		btn_Hexadecimal.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_Hexadecimal.setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().add(btn_Hexadecimal, "cell 0 6,grow");
		
		lbl_Hex = new JLabel("0");
		lbl_Hex.setFont(new Font("Segoe UI", Font.BOLD, 12));
		frame.getContentPane().add(lbl_Hex, "cell 1 6 5 1");
		
		btn_Decimal = new JRadioButton("DEC");
		btn_Decimal.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_Decimal.setBackground(UIManager.getColor("Button.background"));
		btn_Decimal.setSelected(true);
		frame.getContentPane().add(btn_Decimal, "cell 0 7,grow");
		
		lbl_Dec = new JLabel("0");
		lbl_Dec.setFont(new Font("Segoe UI", Font.BOLD, 12));
		frame.getContentPane().add(lbl_Dec, "cell 1 7 5 1");
		
		btn_Octal = new JRadioButton("OCT");
		btn_Octal.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_Octal.setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().add(btn_Octal, "cell 0 8,grow");
		
		lbl_Oct = new JLabel("0");
		lbl_Oct.setFont(new Font("Segoe UI", Font.BOLD, 12));
		frame.getContentPane().add(lbl_Oct, "cell 1 8 5 1");
		
		btn_Binary = new JRadioButton("BIN");
		btn_Binary.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_Binary.setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().add(btn_Binary, "cell 0 9,grow");
		
		lbl_Bin = new JLabel("0");
		lbl_Bin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		frame.getContentPane().add(lbl_Bin, "cell 1 9 5 1");
		frame.getContentPane().add(btnKeypad, "cell 0 10,grow");
		
		JButton btnBitpad = new JButton("Bpd");
		btnBitpad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnBitpad.setBackground(UIManager.getColor("Button.background"));
		btnBitpad.setEnabled(false);
		btnBitpad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBitpad.setBorder(null);
		frame.getContentPane().add(btnBitpad, "cell 1 10,grow");
		
		JButton btnWord = new JButton("DWORD");
		btnWord.setEnabled(false);
		btnWord.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnWord.getText() == "QWORD"){
					btnWord.setText("DWORD");
				}
				else if(btnWord.getText() == "BYTE"){
					btnWord.setText("QWORD");
				}
				else if(btnWord.getText() == "WORD"){
					btnWord.setText("BYTE");
				}
				else {
					btnWord.setText("WORD");
				}
			}
		});
		frame.getContentPane().add(btnWord, "cell 2 10 2 1,grow");
		btnWord.setBorder(null);
		btnWord.setBackground(UIManager.getColor("Button.background"));
		
		JButton btnMs = new JButton("MS");
		btnMs.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnMs.setBackground(UIManager.getColor("Button.background"));
		btnMs.setEnabled(false);
		frame.getContentPane().add(btnMs, "cell 4 10,grow");
		btnMs.setBorder(null);
		
		JButton btnMr = new JButton("MR");
		btnMr.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnMr.setBackground(UIManager.getColor("Button.background"));
		btnMr.setEnabled(false);
		frame.getContentPane().add(btnMr, "cell 5 10,grow");
		btnMr.setBorder(null);
		
		JButton btnLsh = new JButton("Lsh");
		btnLsh.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnLsh.setBackground(new Color(245, 245, 245));
		btnLsh.setEnabled(false);
		frame.getContentPane().add(btnLsh, "cell 0 11,grow");
		btnLsh.setBorder(null);
		
		JButton btnRsh = new JButton("Rsh");
		btnRsh.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnRsh.setBackground(new Color(245, 245, 245));
		btnRsh.setEnabled(false);
		frame.getContentPane().add(btnRsh, "cell 1 11,grow");
		btnRsh.setBorder(null);
		
		JButton btnOr = new JButton("Or");
		btnOr.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnOr.setBackground(new Color(245, 245, 245));
		btnOr.setEnabled(false);
		frame.getContentPane().add(btnOr, "cell 2 11,grow");
		btnOr.setBorder(null);
		
		JButton btnXor = new JButton("Xor");
		btnXor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnXor.setBackground(new Color(245, 245, 245));
		btnXor.setEnabled(false);
		frame.getContentPane().add(btnXor, "cell 3 11,grow");
		btnXor.setBorder(null);
		
		JButton btnNot = new JButton("Not");
		btnNot.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnNot.setBackground(new Color(245, 245, 245));
		btnNot.setEnabled(false);
		frame.getContentPane().add(btnNot, "cell 4 11,grow");
		btnNot.setBorder(null);
		
		JButton btnAnd = new JButton("And");
		btnAnd.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAnd.setBackground(new Color(245, 245, 245));
		btnAnd.setEnabled(false);
		frame.getContentPane().add(btnAnd, "cell 5 11,grow");
		btnAnd.setBorder(null);
		
		JToggleButton tgl_UpArrow = new JToggleButton("↑");
		tgl_UpArrow.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		tgl_UpArrow.setBackground(new Color(245, 245, 245));
		tgl_UpArrow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tgl_UpArrow.isSelected()){
					btnLsh.setText("Lsh");
					btnRsh.setText("Rsh");
				}
				else if(tgl_UpArrow.isSelected()){
					btnLsh.setText("RoL");
					btnRsh.setText("RoR");
				}
			}
		});
		tgl_UpArrow.setBorder(null);
		frame.getContentPane().add(tgl_UpArrow, "cell 0 12,grow");
		
		JButton btn_Mod = new JButton("Mod");
		btn_Mod.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btn_Mod.setBackground(new Color(245, 245, 245));
		btn_Mod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operatorClicked(" % ");
			}
		});
		btn_Mod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Mod.setBorder(null);
		frame.getContentPane().add(btn_Mod, "cell 1 12,grow");
		
		JButton btn_ClearEntry = new JButton("CE");
		btn_ClearEntry.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_ClearEntry.setBackground(new Color(245, 245, 245));
		btn_ClearEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_ClearEntry.setBorder(null);
		btn_ClearEntry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txtInput.setText("0");
				lbl_Dec.setText("0");
				lbl_Hex.setText("0");
				lbl_Oct.setText("0");
				lbl_Bin.setText("0");
			}
		});
		frame.getContentPane().add(btn_ClearEntry, "cell 2 12,grow");
		
		JButton btn_Clear = new JButton("C");
		btn_Clear.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_Clear.setBackground(new Color(245, 245, 245));
		btn_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Clear.setBorder(null);
		btn_Clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtInput.setText("0");
				txtMemory.setText("");
				decimalMemory.setText("");
				lbl_Dec.setText("0");
				lbl_Hex.setText("0");
				lbl_Oct.setText("0");
				lbl_Bin.setText("0");
			}
		});
		frame.getContentPane().add(btn_Clear, "cell 3 12,grow");
		
		JButton btn_Backspace = new JButton("\u2190");
		btn_Backspace.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn_Backspace.setBackground(new Color(245, 245, 245));
		btn_Backspace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Backspace.setBorder(null);
		btn_Backspace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkforZero();
				if(txtInput.getText().length() > 1){
					txtInput.setText(txtInput.getText().substring(0, txtInput.getText().length() - 1));
				}
				else if(txtInput.getText().length() <= 1){
					txtInput.setText("0");
				}
				update();

			}
		});
		frame.getContentPane().add(btn_Backspace, "cell 4 12,grow");
		
		JButton btn_Divide = new JButton("\u00F7");
		btn_Divide.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn_Divide.setBackground(new Color(245, 245, 245));
		btn_Divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Divide.setBorder(null);
		btn_Divide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operatorClicked(" / ");
			}
		});
		btn_Divide.getInputMap().put(KeyStroke.getKeyStroke("/"), "doSomething");
		frame.getContentPane().add(btn_Divide, "cell 5 12,grow");
		
		JButton btn_A = new JButton("A");
		btn_A.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_A.setBackground(Color.WHITE);
		btn_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_A.setBorder(null);
		btn_A.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("A");

			}
		});
		btn_A.setEnabled(false);
		frame.getContentPane().add(btn_A, "cell 0 13,grow");
		
		JButton btn_B = new JButton("B");
		btn_B.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_B.setBackground(Color.WHITE);
		btn_B.setBorder(null);
		btn_B.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("B");
			}
		});
		btn_B.setEnabled(false);
		frame.getContentPane().add(btn_B, "cell 1 13,grow");
		
		JButton btn_7 = new JButton("7");
		btn_7.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn_7.setBackground(Color.WHITE);
		btn_7.setBorder(null);
		btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("7");
			}
		});
		frame.getContentPane().add(btn_7, "cell 2 13,grow");
		
		JButton btn_8 = new JButton("8");
		btn_8.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn_8.setBackground(Color.WHITE);
		btn_8.setBorder(null);
		btn_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("8");

			}
		});
		frame.getContentPane().add(btn_8, "cell 3 13,grow");
		
		JButton btn_9 = new JButton("9");
		btn_9.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn_9.setBackground(Color.WHITE);
		btn_9.setBorder(null);
		btn_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("9");

			}
		});
		frame.getContentPane().add(btn_9, "cell 4 13,grow");
		
		JButton btn_Multiply = new JButton("x");
		btn_Multiply.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn_Multiply.setBackground(new Color(245, 245, 245));
		btn_Multiply.setBorder(null);
		btn_Multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Multiply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operatorClicked(" * ");
			}
		});
		frame.getContentPane().add(btn_Multiply, "cell 5 13,grow");
		
		JButton btn_C = new JButton("C");
		btn_C.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_C.setBackground(Color.WHITE);
		btn_C.setBorder(null);
		btn_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_C.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("C");
			}
		});
		btn_C.setEnabled(false);
		frame.getContentPane().add(btn_C, "cell 0 14,grow");
		
		JButton btn_D = new JButton("D");
		btn_D.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_D.setBackground(Color.WHITE);
		btn_D.setBorder(null);
		btn_D.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("D");
			}
		});
		btn_D.setEnabled(false);
		frame.getContentPane().add(btn_D, "cell 1 14,grow");
		
		JButton btn_4 = new JButton("4");
		btn_4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn_4.setBackground(Color.WHITE);
		btn_4.setBorder(null);
		btn_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("4");
			}
		});
		frame.getContentPane().add(btn_4, "cell 2 14,grow");
		
		JButton btn_5 = new JButton("5");
		btn_5.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn_5.setBackground(Color.WHITE);
		btn_5.setBorder(null);
		btn_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("5");
			}
		});
		frame.getContentPane().add(btn_5, "cell 3 14,grow");
		
		JButton btn_6 = new JButton("6");
		btn_6.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn_6.setBackground(Color.WHITE);
		btn_6.setBorder(null);
		btn_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("6");
			}
		});
		frame.getContentPane().add(btn_6, "cell 4 14,grow");
		
		JButton btn_Minus = new JButton("-");
		btn_Minus.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn_Minus.setBackground(new Color(245, 245, 245));
		btn_Minus.setBorder(null);
		btn_Minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Minus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operatorClicked(" - ");
			}
		});
		frame.getContentPane().add(btn_Minus, "cell 5 14,grow");
		
		JButton btn_E = new JButton("E");
		btn_E.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_E.setBackground(Color.WHITE);
		btn_E.setBorder(null);
		btn_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_E.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("E");
			}
		});
		btn_E.setEnabled(false);
		frame.getContentPane().add(btn_E, "cell 0 15,grow");
		
		JButton btn_F = new JButton("F");
		btn_F.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn_F.setBackground(Color.WHITE);
		btn_F.setBorder(null);
		btn_F.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("F");
			}
		});
		btn_F.setEnabled(false);
		frame.getContentPane().add(btn_F, "cell 1 15,grow");
		
		JButton btn_1 = new JButton("1");
		btn_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn_1.setBackground(Color.WHITE);
		btn_1.setBorder(null);
		btn_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("1");
			}
		});
		frame.getContentPane().add(btn_1, "cell 2 15,grow");
		
		JButton btn_2 = new JButton("2");
		btn_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn_2.setBackground(Color.WHITE);
		btn_2.setBorder(null);
		btn_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("2");
			}
		});
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btn_2, "cell 3 15,grow");
		
		JButton btn_3 = new JButton("3");
		btn_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn_3.setBackground(Color.WHITE);
		btn_3.setBorder(null);
		btn_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("3");
			}
		});
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btn_3, "cell 4 15,grow");
		
		JButton btn_Plus = new JButton("+");
		btn_Plus.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn_Plus.setBackground(new Color(245, 245, 245));
		btn_Plus.setBorder(null);
		btn_Plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operatorClicked(" + ");
			}
		});
		frame.getContentPane().add(btn_Plus, "cell 5 15,grow");
		
		JButton btn_OpenPar = new JButton("(");
		btn_OpenPar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn_OpenPar.setBackground(new Color(245, 245, 245));
		btn_OpenPar.setBorder(null);
		btn_OpenPar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMemory.setText(txtMemory.getText() + "(");
				decimalMemory.setText(decimalMemory.getText() +"(");
				unclosedParaCount++;
			}
		});
		btn_OpenPar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btn_OpenPar, "cell 0 16,grow");
		
		JButton btn_ClosePar = new JButton(")");
		btn_ClosePar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn_ClosePar.setBackground(new Color(245, 245, 245));
		btn_ClosePar.setBorder(null);
		btn_ClosePar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_ClosePar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(unclosedParaCount > 0){
					memoryUpdate();
					txtMemory.setText(txtMemory.getText() + txtInput.getText() + ")");
					decimalMemory.setText(decimalMemory.getText() + ")");
					txtInput.setText("");
					unclosedParaCount++;
				}

			}
		});
		frame.getContentPane().add(btn_ClosePar, "cell 1 16,grow");
		
		JButton btn_PlusMinus = new JButton("±");
		btn_PlusMinus.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn_PlusMinus.setBackground(new Color(245, 245, 245));
		btn_PlusMinus.setBorder(null);
		btn_PlusMinus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtInput.getText().isEmpty()){
					txtInput.setText("0");
				}
				if(decimalMemory.getText().isEmpty()){
					decimalMemory.setText("0");
				}
				if(txtInput.getText().startsWith("-")){
					txtInput.setText(txtInput.getText().substring(1, txtInput.getText().length()));
				}
				else if(!txtInput.getText().startsWith("0")){
					txtInput.setText("-"+txtInput.getText());
					update();
					lastEntryOperator = false;
				}
			}
		});
		btn_PlusMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btn_PlusMinus, "cell 2 16,grow");
		
		JButton btn_0 = new JButton("0");
		btn_0.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn_0.setBackground(Color.WHITE);
		btn_0.setBorder(null);
		btn_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operandClicked("0");
			}
		});
		frame.getContentPane().add(btn_0, "cell 3 16,grow");
		
		JButton btn_Dot = new JButton(".");
		btn_Dot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Dot.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn_Dot.setBackground(new Color(245, 245, 245));
		btn_Dot.setVerticalAlignment(SwingConstants.CENTER);
		btn_Dot.setBorder(null);
		btn_Dot.setEnabled(false);
		frame.getContentPane().add(btn_Dot, "cell 4 16,grow");
		
		
		JButton btn_Equals = new JButton("=");
		btn_Equals.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn_Equals.setBackground(new Color(245, 245, 245));
		btn_Equals.setBorder(null);
		btn_Equals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Equals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(solved.getText().startsWith("0")){
					memoryUpdate();
					equalsMemory = decimalMemory.getText().substring(decimalMemory.getText().length() - txtInput.getText().length() - 3, decimalMemory.getText().length());
					txtMemory.setText(txtMemory.getText() + txtInput.getText());
					int result = Evaluator.evaluateExpression(decimalMemory.getText());
					if(btn_Hexadecimal.isSelected()) {
						txtInput.setText(Integer.toHexString(result));
						txtInput.setText(txtInput.getText().toUpperCase());
					}
					if(btn_Octal.isSelected()) {
						txtInput.setText(Integer.toOctalString(result));
					}
					if(btn_Binary.isSelected()) {
						txtInput.setText(Integer.toBinaryString(result));
					}
					if(btn_Decimal.isSelected()){
						txtInput.setText("" + result);
					}
					update();
					txtMemory.setText("");
					decimalMemory.setText("");
					solved.setText("1");
				}
				else if(solved.getText().startsWith("1")){
					memoryUpdate();
					int result = Evaluator.evaluateExpression(decimalMemory.getText() + equalsMemory);
					if(btn_Hexadecimal.isSelected()) {
						txtInput.setText(Integer.toHexString(result));
						txtInput.setText(txtInput.getText().toUpperCase());
					}
					if(btn_Octal.isSelected()) {
						txtInput.setText(Integer.toOctalString(result));
					}
					if(btn_Binary.isSelected()) {
						txtInput.setText(Integer.toBinaryString(result));
					}
					if(btn_Decimal.isSelected()){
						txtInput.setText("" + result);
					}
					update();
					txtMemory.setText("");
					decimalMemory.setText("");
				}
			}
		});
		frame.getContentPane().add(btn_Equals, "cell 5 16,grow");
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(btn_Hexadecimal);
		btnGroup.add(btn_Decimal);
		btnGroup.add(btn_Octal);
		btnGroup.add(btn_Binary);
		
		btn_Decimal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_2.setEnabled(true);
				btn_3.setEnabled(true);
				btn_4.setEnabled(true);
				btn_5.setEnabled(true);
				btn_6.setEnabled(true);
				btn_7.setEnabled(true);
				btn_8.setEnabled(true);
				btn_9.setEnabled(true);
				btn_A.setEnabled(false);
				btn_B.setEnabled(false);
				btn_C.setEnabled(false);
				btn_D.setEnabled(false);
				btn_E.setEnabled(false);
				btn_F.setEnabled(false);
				txtInput.setText(lbl_Dec.getText());
				solved.setText("1");
			}
		});
		
		btn_Hexadecimal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_2.setEnabled(true);
				btn_3.setEnabled(true);
				btn_4.setEnabled(true);
				btn_5.setEnabled(true);
				btn_6.setEnabled(true);
				btn_7.setEnabled(true);
				btn_8.setEnabled(true);
				btn_9.setEnabled(true);
				btn_A.setEnabled(true);
				btn_B.setEnabled(true);
				btn_C.setEnabled(true);
				btn_D.setEnabled(true);
				btn_E.setEnabled(true);
				btn_F.setEnabled(true);
				txtInput.setText(lbl_Hex.getText());
				solved.setText("1");
			}
		});
		
		btn_Octal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_2.setEnabled(true);
				btn_3.setEnabled(true);
				btn_4.setEnabled(true);
				btn_5.setEnabled(true);
				btn_6.setEnabled(true);
				btn_7.setEnabled(true);
				btn_8.setEnabled(false);
				btn_9.setEnabled(false);
				btn_A.setEnabled(false);
				btn_B.setEnabled(false);
				btn_C.setEnabled(false);
				btn_D.setEnabled(false);
				btn_E.setEnabled(false);
				btn_F.setEnabled(false);
				txtInput.setText(lbl_Oct.getText());
				solved.setText("1");
			}
		});
		
		btn_Binary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_2.setEnabled(false);
				btn_3.setEnabled(false);
				btn_4.setEnabled(false);
				btn_5.setEnabled(false);
				btn_6.setEnabled(false);
				btn_7.setEnabled(false);
				btn_8.setEnabled(false);
				btn_9.setEnabled(false);
				btn_A.setEnabled(false);
				btn_B.setEnabled(false);
				btn_C.setEnabled(false);
				btn_D.setEnabled(false);
				btn_E.setEnabled(false);
				btn_F.setEnabled(false);
				txtInput.setText(lbl_Bin.getText());
				solved.setText("1");
			}
		});
		
		InputMap im = btn_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke("NUMPAD1"), "add1");
		im.put(KeyStroke.getKeyStroke("1"), "add1");
		ActionMap ap = btn_1.getActionMap();
		ap.put("add1", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_1.doClick();
				operandClicked("1");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("2"), "add2");
		im.put(KeyStroke.getKeyStroke("NUMPAD2"), "add2");
		ap.put("add2", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_2.doClick();
				operandClicked("2");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("3"), "add3");
		im.put(KeyStroke.getKeyStroke("NUMPAD3"), "add3");
		ap.put("add3", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_3.doClick();
				operandClicked("3");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("4"), "add4");
		im.put(KeyStroke.getKeyStroke("NUMPAD4"), "add4");
		ap.put("add4", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_4.doClick();
				operandClicked("4");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("5"), "add5");
		im.put(KeyStroke.getKeyStroke("NUMPAD5"), "add5");
		ap.put("add5", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_5.doClick();
				operandClicked("5");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("6"), "add6");
		im.put(KeyStroke.getKeyStroke("NUMPAD6"), "add6");
		ap.put("add6", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_6.doClick();
				operandClicked("6");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("7"), "add7");
		im.put(KeyStroke.getKeyStroke("NUMPAD7"), "add7");
		ap.put("add7", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_7.doClick();
				operandClicked("7");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("8"), "add8");
		im.put(KeyStroke.getKeyStroke("NUMPAD8"), "add8");
		ap.put("add8", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_8.doClick();
				operandClicked("8");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("9"), "add9");
		im.put(KeyStroke.getKeyStroke("NUMPAD9"), "add9");
		ap.put("add9", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_9.doClick();
				operandClicked("9");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("0"), "add0");
		im.put(KeyStroke.getKeyStroke("NUMPAD0"), "add0");
		ap.put("add0", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_0.doClick();
				operandClicked("0");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("A"), "addA");
		ap.put("addA", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(btn_Hexadecimal.isSelected()){
					btn_A.doClick();
					operandClicked("A");
				}
			}
		});
		
		im.put(KeyStroke.getKeyStroke("B"), "addB");
		ap.put("addB", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(btn_Hexadecimal.isSelected()){
					btn_B.doClick();
					operandClicked("B");
				}
			}
		});
		
		im.put(KeyStroke.getKeyStroke("C"), "addC");
		ap.put("addC", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(btn_Hexadecimal.isSelected()){
					btn_C.doClick();
					operandClicked("C");
				}
			}
		});
		
		im.put(KeyStroke.getKeyStroke("D"), "addD");
		ap.put("addD", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(btn_Hexadecimal.isSelected()){
					btn_D.doClick();
					operandClicked("D");
				}
			}
		});
		
		im.put(KeyStroke.getKeyStroke("E"), "addE");
		ap.put("addE", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(btn_Hexadecimal.isSelected()){
					btn_E.doClick();
					operandClicked("E");
				}
			}
		});
		
		im.put(KeyStroke.getKeyStroke("F"), "addF");
		ap.put("addF", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(btn_Hexadecimal.isSelected()){
					btn_F.doClick();
					operandClicked("F");
				}
			}
		});
		
		im.put(KeyStroke.getKeyStroke(107, 0), "addition");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.SHIFT_DOWN_MASK), "addition");
		ap.put("addition", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_Plus.doClick();
				operatorClicked(" + ");
			}
		});
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "subtraction");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "subtraction");
		ap.put("subtraction", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_Minus.doClick();
				operatorClicked(" - ");
			}
		});
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "multiply");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.SHIFT_DOWN_MASK), "multiply");
		ap.put("multiply", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_Multiply.doClick();
				operatorClicked(" * ");
			}
		});
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), "divide");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), "divide");
		ap.put("divide", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_Divide.doClick();
				operatorClicked(" / ");
			}
		});
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, KeyEvent.SHIFT_DOWN_MASK), "modulo");
		ap.put("modulo", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_Mod.doClick();
				operatorClicked(" % ");
			}
		});
		
		im.put(KeyStroke.getKeyStroke("ENTER"), "equals");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "equals");
		ap.put("equals", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_Equals.doClick();
				if(solved.getText().startsWith("0")){
					memoryUpdate();
					equalsMemory = decimalMemory.getText().substring(decimalMemory.getText().length() - txtInput.getText().length() - 3, decimalMemory.getText().length());
					txtMemory.setText(txtMemory.getText() + txtInput.getText());
					int result = Evaluator.evaluateExpression(decimalMemory.getText());
					if(btn_Hexadecimal.isSelected()) {
						txtInput.setText(Integer.toHexString(result));
						txtInput.setText(txtInput.getText().toUpperCase());
					}
					if(btn_Octal.isSelected()) {
						txtInput.setText(Integer.toOctalString(result));
					}
					if(btn_Binary.isSelected()) {
						txtInput.setText(Integer.toBinaryString(result));
					}
					if(btn_Decimal.isSelected()){
						txtInput.setText("" + result);
					}
					update();
					txtMemory.setText("");
					decimalMemory.setText("");
					solved.setText("1");
				}
				else if(solved.getText().startsWith("1")){
					memoryUpdate();
					int result = Evaluator.evaluateExpression(decimalMemory.getText() + equalsMemory);
					if(btn_Hexadecimal.isSelected()) {
						txtInput.setText(Integer.toHexString(result));
						txtInput.setText(txtInput.getText().toUpperCase());
					}
					if(btn_Octal.isSelected()) {
						txtInput.setText(Integer.toOctalString(result));
					}
					if(btn_Binary.isSelected()) {
						txtInput.setText(Integer.toBinaryString(result));
					}
					if(btn_Decimal.isSelected()){
						txtInput.setText("" + result);
					}
					update();
					txtMemory.setText("");
					decimalMemory.setText("");
				}
			}
		});
		
	}
}
