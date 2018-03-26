package calc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calc extends JFrame {
	
	//�������� ������� ������ ����������� ��� ����������� ����������� ������������� ������������ �������, ���������� � ������ �����������
	CalcListener objCalcListener = new  CalcListener();
	
	//���������� ���������� 
	static TextField calcTF ;
	
	
	//���������� ������� ������ �� 0 �� 9
	//����������� ������ ����� ��� ��������� � �������� ��������� � ������� ������ � ������ ����������� �������
	static JButton [] digiBut = new JButton[10];
	
	//���������� � ����������� �������� �������� �������������� ������, �� ���� ������� ����� ������������ ������� ����� ������
	//����� ���������� ������ � �������� ������ �� ���������� ����������� ��������� ������ � ���� ����� ������� �������� ������ 
	//��������� ����� ������ ������������ ���� � ���-�� ������
	static String [] strButFPDel = {"+/-",".","BS","="};
	static JButton [] butFPDel = new JButton[strButFPDel.length];
	static String [] strButFPZnak = {" / "," * "," - "," + "};
	static JButton [] butFPZnak = new JButton[strButFPZnak.length];
	static String [] strButFPBS = {"C","MC","%","SQR"};
	static JButton [] butFPBS = new JButton[strButFPBS.length];
	static String [] strButFPMem = {"MS","MR","M+","M-"};
	static JButton [] butFPMem = new JButton[strButFPMem.length];
	
			
	/*
	5.2.	����������� ����� ������� ���� ������������,  � ���������� ��������, ��������� � ��������� ��� ��������� ����� ������:
	 textField |  �����   |    +/- , |   ����. ����� | �+ �- ��  | �� ������ BS CE C
	 */
	
	//������� ����������� � �������������� ����������� ��������
		public Calc(String arg0) throws HeadlessException 
		{
			super(arg0);
			
			//���������� ���������� � ������� ����
			setLocation(300, 300);
			setSize(300,	400);
			//������������� ����� �������� ��������� �� �������. ����
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
			
			//������ �������� ���������� ���� ������
			setLayout(new BorderLayout());
			//������� ��� ������ - ���� ��� ����������, ������ ��� ���������� ����
			JPanel panTF = new JPanel();
			//��������� ���� ����� ������� �� ��� �����, ����� �������� �������, ������ ������� �������
			JPanel panKeys = new JPanel(new GridLayout(2,1,10,10));
			//������ �� ������� ������� (�����)
			panTF.setBorder(BorderFactory.createEtchedBorder());
			panKeys.setBorder(BorderFactory.createEtchedBorder());
			
			//�������� ���������� ��� ������� ������
			calcTF = new TextField();
		    //��������� ������������� ���������� ��� ��������� ��������� ��� ��������� ������� ����� 
			calcTF.setText("");
			// ������� �������� ������ ����������
			calcTF.setPreferredSize(new Dimension(280, 40));
			//���������� ����� ��� ������������� � ����������
			Font tFFont = Font.decode("Arial-Bold-32");
			calcTF.setFont(tFFont);
			panTF.add(calcTF);
			
			
			/*
			 31.3.1.	����������� � �������� � �������: 
			 31.3.1.1.	� ���������� ��������� ���������������� ���� � ��������� �����������  ������� ����� �� ���������.
			 */
			
			calcTF.setFocusable(false);
			
			
			
						
			//��������� ������ ���������� � �������� ����, ������ ����������
			add(panTF, BorderLayout.NORTH);
			
			
			//������� ��� ������ ��� ��������� �����, ������� ������ � ����������� ������ ������ tFKeys
			//������ ���� ��� ����
			JPanel digiPanel = new JPanel();
			//������ ���� �������
			digiPanel.setBorder(BorderFactory.createEtchedBorder());
			
			//������ ���� ��� ������ �������������� ������
			JPanel funcKeysPanel = new JPanel();
			//������ ���� �������
			funcKeysPanel.setBorder(BorderFactory.createEtchedBorder());
			
			
			//---------------��������� ������ �������� ������-----------------------------------------------------
			
			//������ �������� ���������� ��� ������ � ��������� ���������
			//��� ��� ������� ������ �� 2 ����  - �� ���� 3 �� 3 ��� ���� �� 1 �� 9 , � ��������� ������� ������ 0
			//������� �������� ��� ������������� ���������� ��������
			digiPanel.setLayout(new BoxLayout(digiPanel, BoxLayout.Y_AXIS));
			digiPanel.setLayout(new BoxLayout(digiPanel, BoxLayout.Y_AXIS));
			//��������  ������ ������ ���� �� 1 �� 9
			JPanel dig19 = new JPanel();
			//�������� ������ ������� 0
			JPanel dig0	= new JPanel();
			//������ ��������� ���������� ��� ���������� ����
			dig19.setLayout(new GridLayout(3, 3,2,2));	//������� 3 �� 3 ��� ���� 1 - 9
			dig0.setLayout(new GridLayout(1, 1));	//������ �� ���� ���������, ��� ������������ ������ 0 �� ��������� ������ ����� ������ �������� ������
			
			//������� ������			
			//���������� ����� ��� �������� � ������� ����� 16,  ������ 
			Font digiFont = Font.decode("Arial-Bold-16");
			
			//������� � ��������� ������ � �����
			//� ������������ ������ ������ �� �������
			//������� ������ �� 0 �� 9 ������ �������� �������� �� ������ �����
			for (int i = 0; i < digiBut.length; i++) 
				{ //���� �������� ������
					//�������� ������� ������
					digiBut[i] = new JButton(String.valueOf(i));
					
					//�������������� ��������� ��� ������ ����� ����������� ������� � �� ���� (�����)
					digiBut[i].setFont(digiFont);
					digiBut[i].setForeground(Color.blue);
					//��������� ������� ������ � ���� ���������, ��� ������ 0 ���� ���������, ��� ���� ��������� ������
					if (i == 0) 
						{
						 	dig0.add(digiBut[i]);
						}
					else
						{
							dig19.add(digiBut[i]);
						}
					//��������� ��������� ������� ������� ������ - �������������� ������ �� ������ ����������� ������� �����������
					digiBut[i].addActionListener(objCalcListener);
					digiBut[i].addKeyListener(objCalcListener);
					digiBut[i].setFocusable(true);
					
					
					
					/*
					 32.2.1.	�����������: �����������  ������ �� ���� ������ ��� ������� Ctrl (������� Ctrl  ����� ������������ �������� ^ ),
					  ��� ������ ������� �����. (����� ������ ��� ������������� � �� ���� ������ ��� �������� �����, ����� ������ ��� ���������
					   ����� ������ �������). ������������ ����� ��������� ������ ������������.
					 */
					
					//�������� �� �������� � ��������, ����������� � �������� ������� ��������� ����, ������ ���������� ��������� �������,
					//��� ��������� ������� � ���� ������ ������ ����������
					digiBut[i].addMouseListener(new MouseAdapter() {
				
						@Override
						// ���������� ����� ���� �� �������� �������
						public void mouseClicked(MouseEvent event)
						{ // ���� ���� ���������
							// TODO Auto-generated method stub
							//����� ���� � ������� ��� ��������
							System.out.println("������ ������ ����:  " + event.getButton() );
							System.out.println("������ ���. �������:" + event.getMouseModifiersText( event.getModifiers()  )	);
							// �������� ������� ������� ������� ������� ������ � �������� �������� �������
							// ���� ������ �������� ���������� - ��� ����������� ������� ������� ����� - ����� ������ ������� �����
							if (   event.getMouseModifiersText( event.getModifiers()  ).equalsIgnoreCase("Ctrl+Button1")) 
								{ //������� ������� + �������� �������
									CalcOperations.equalPushing();  // ����� ������ ������� ������� �����
								} //������� ������� + �������� �������
						} //���� ���� ���������
					});
					
					
					
				} //���� �������� ������
			
			
			
			
			
			//��������� 2 ��������� ������ �� ����� ��������� ������ ����
			digiPanel.add(dig19);
			digiPanel.add(dig0);
			
			
			//---------------------��������� ������ ������ �������������� ������---------------------------
			
			//�������������� ���������� �������� �� ������� ����� ���������� ������ �����������
			//��� ������� ����� ��������� ���� ������
			//������ �������� ���������� ��� ������ �������������� ������
			funcKeysPanel.setLayout(new BoxLayout(funcKeysPanel, BoxLayout.X_AXIS));
			
			//������� ���������
			JPanel funcPanel1 = new JPanel();
			funcPanel1.setBorder(BorderFactory.createEtchedBorder());
			JPanel funcPanel2 = new JPanel(); 
			funcPanel2.setBorder(BorderFactory.createEtchedBorder());
			JPanel funcPanel3 = new JPanel();
			funcPanel3.setBorder(BorderFactory.createEtchedBorder());
			JPanel funcPanel4 = new JPanel();
			funcPanel4.setBorder(BorderFactory.createEtchedBorder());
			
			//��� ������ ��������� �������������� ������ ����� ������ �������� �� ������� +/- � �������
			// ����� ������� ��� ��� ������������� ������ ��������������(�������� ����. �����) � ������ �����( ��������� ������)
			//��� ���� ��������� ���������� ����������
			
		//---------� ���������� ����������� ���������� � ����������� ������ ���������� � ������� ����� ������----------
			funcPanel1.setLayout(new GridLayout(butFPDel.length,1,3,3));
			//������� �������� � �����, ������ �� ����� ��������� ����� � ��������� ������ � ���� ���������
			for (int i = 0; i < butFPDel.length; i++) 
				{
					//��������� ��� ������� ������ �����, ��� � ���� �������, ������� ������ �� ������ � ������� ��������� �������
					butFPDel[i] = new JButton(strButFPDel[i]);
					butFPDel[i].setFont(digiFont);
					butFPDel[i].setForeground(Color.blue);
					if (i == 3) {butFPDel[i].setBackground(Color.GREEN);	}
					funcPanel1.add(butFPDel[i]);
					//��������� ��������� �������
					butFPDel[i].addActionListener(objCalcListener);
					butFPDel[i].addKeyListener(objCalcListener);
					butFPDel[i].setFocusable(true);
					
				}
			//��������� ���������1 � ������ �����. ������
			funcKeysPanel.add(funcPanel1);
			
			
			//��� ������ ��������� �������������� ������ ����� ������ �������������� �������� �������� �� �������
			//��� ���� ��������� ���������� ����������
			funcPanel2.setLayout(new GridLayout(butFPZnak.length,1,3,3));
			
			//---------� ���������� ����������� ���������� � ����������� ������ ���������� � ������� ����� ������----------
			
			//������� �������� � �����, ������ �� ����� ��������� ����� � ��������� ������ � ���� ���������, ��������� ��������� �������
			for (int i = 0; i < butFPZnak.length; i++) 
				{
					butFPZnak[i] = new JButton(strButFPZnak[i]);
					butFPZnak[i].setFont(digiFont);
					butFPZnak[i].setForeground(Color.blue);
					funcPanel2.add(butFPZnak[i]);
					//��������� ��������� �������
					butFPZnak[i].addActionListener(objCalcListener);
					butFPZnak[i].addKeyListener(objCalcListener);
					butFPZnak[i].setFocusable(true);
				}
			//��������� ���������2 � ������ �����. ������
			funcKeysPanel.add(funcPanel2);
			
			
			//��� ������� ��������� �������������� ������ ����� ������ ����������� �����, BS, CE, C
			//��� ���� ��������� ���������� ����������
			funcPanel3.setLayout(new GridLayout(butFPBS.length,1,3,3));
			
			//---------� ���������� ����������� ���������� � ����������� ������ ���������� � ������� ����� ������----------
			
			//������� �������� � �����, ������ �� ����� ��������� ����� � ��������� ������ � ���� ���������
			for (int i = 0; i < butFPBS.length; i++) 
				{
					butFPBS[i] = new JButton(strButFPBS[i]);
					butFPBS[i].setFont(digiFont);
					butFPBS[i].setForeground(Color.blue);
					funcPanel3.add(butFPBS[i]);
					//��������� ��������� �������
					butFPBS[i].addActionListener(objCalcListener);
					butFPBS[i].addKeyListener(objCalcListener);
					butFPBS[i].setFocusable(true);
				}
			//��������� ���������3 � ������ �����. ������
			funcKeysPanel.add(funcPanel3);
			
			
			//��� ��������� ��������� �������������� ������ ����� ������ �+, �- � ��
			//��� ���� ��������� ���������� ����������
			funcPanel4.setLayout(new GridLayout(butFPMem.length,1,3,3));
			
			//---------� ���������� ����������� ���������� � ����������� ������ ���������� � ������� ����� ������----------
			
			//������� �������� � �����, ������ �� ����� ��������� ����� � ��������� ������ � ���� ���������
			for (int i = 0; i < butFPMem.length; i++) 
				{
					butFPMem[i] = new JButton(strButFPMem[i]);
					butFPMem[i].setFont(digiFont);
					butFPMem[i].setForeground(Color.blue);
					funcPanel4.add(butFPMem[i]);
					//��������� ��������� �������
					butFPMem[i].addActionListener(objCalcListener);
					butFPMem[i].addKeyListener(objCalcListener);
					butFPMem[i].setFocusable(true);
				}
			//��������� ���������4 � ������ �������������� ������ 
			funcKeysPanel.add(funcPanel4);
			//panKeys.add(funcPanel4, BorderLayout.EAST);
			
			//���������  ��� ������� ��������� ������ � ����������� ������ ����
			panKeys.add(digiPanel, BorderLayout.CENTER);
			panKeys.add(funcKeysPanel, BorderLayout.SOUTH);
			
						
			add(panKeys, BorderLayout.CENTER);
			
			
			//�������� ��������� ����
			setVisible(true);
			
			
			
			
			
		}
	
	
	
	
	public static void main(String[] args) {
	
		//�������� ������� ���� ������������
		new Calc("��� �����������");	
	

	}




	
		
	

}
