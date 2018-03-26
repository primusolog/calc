package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CalcListener implements ActionListener, KeyListener {
//����� ��� ������������ ������� ������ ������������
	
	
	//���������� ����� ���������� ������������, � ����� ���������� ������������������� �������� ���������
	//String  tempTF;
	static double operand1, operand2, operandM;
	
	//���������� ���� ��������, � �����. � ����������� ������� 28.2.2.2 ��� ����� ���� ���
	static char operation;
		
	//���������� � ����������� �������� ������� ������
//	 int pushDigCounter = 0;		//������� ��� �������� ������
	static int pushFuncCounter = 0;		//������� ��� ������ ��������

	//������������� ��������� ��� �������������� ��������� ��� ��������� �������� ������
	

	public static void main(String[] args) {
		
	
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		//���� ��������� �������� ������
		//�.�. ������� ����������� � �����, �� � ��� ����������� ������������ ����� ������� ������� � ������� � ������� ����� ����� ������������ ����
		for ( int i = 0; i < Calc.digiBut.length; i++)
			//���� ����� ������� ������ � ����� ������ �� ������� ���������
			//����� ��������� ������� �������� ������� ����� (����������� ����� � ������� �����)
			if ((event.getSource() == Calc.digiBut[i])||(event.getSource()==Calc.butFPDel[1])) 
				{ //���� ������ ����� ��� �����
					//�����  ������� ������ �� ������� ��� ������������
					//System.out.println("������ ������ " + event.getActionCommand() );
					
					//������������� � ��������� �������� ������ ����� ����������� �������� � ��������� �������
					Calc.calcTF.setText(Calc.calcTF.getText() + event.getActionCommand());
			
					
					//����� �������� �������  ������� ��� ������������
					//System.out.println("������� ������� ������� " + pushFuncCounter);
					//������� �� ����� ��� �������� ��������� �� �������
					break;
				} //���� ������ ����� ��� �����
		
		
		
		
		//���� ��������� �������������� ������ ������ del ( ������� +/- , del, = ) 
		//������� � ������ ���������� ����� ������ � ��������� �������� ������
		//��� ��� � ������ ������ ������ �����������, ��� ������ ��������� ���� - ���������� ������ � ������� ��������� ����
		switch (event.getActionCommand()) 
		{
		
		case "+/-":
			System.out.println("������ ������ " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(CalcOperations.plusMinus()));
			break;
			
		case "%":
			System.out.println("������ ������ " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(CalcOperations.getPercent()));
			if (pushFuncCounter == 1) 
			{
				operand2 = CalcOperations.getPercent();
			}
			break;
		
		case "=":
			CalcOperations.equalPushing();
			break;
		
		// ������� ������� �����
		case "C":
			System.out.println("������ ������ " + event.getActionCommand() );
			//����� ������ ������� ������� ������ �
			CalcOperations.resetPushing();
			break;
		
		//������� ������� ����� ������
		case "MC":
			System.out.println("������ ������ " + event.getActionCommand() );
			//������������� � ���� �������� ���������� ������
			operandM = 0;
			break;
			
		//������� ������� ��������
		case "BS":
			System.out.println("������ ������ " + event.getActionCommand() );
			Calc.calcTF.setText(CalcOperations.delLastDigit());
			break;
			
		//���������� ������ � �����������
		case "SQR":
			System.out.println("������ ������ " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(CalcOperations.getSqr()));
			break;
			
		//���������� ���������� � ������
		case "MS":
			System.out.println("������ ������ " + event.getActionCommand() );
			operandM = CalcOperations.getTFDigitValue();
			break;
		
		//������ �� ������ � ���������
		case "MR":
			System.out.println("������ ������ " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(operandM));
			break;
		
		//����������� � ���������� ����������� ������ ������ � ���������� ���������� � ����������
		case "M+":
			System.out.println("������ ������ " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(operandM + CalcOperations.getTFDigitValue() ));
			break;
			
		//���������  ���������� �� ����������� ������ ������ � ���������� ���������� � ����������
		case "M-":
			System.out.println("������ ������ " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(operandM - CalcOperations.getTFDigitValue() ));
			break;
			
			
			
		
		default:
			break;
		}
		
		
		
		
		
		
		//���� ��������� �������������� ������  ������ �������� (�������)
		//�������� � ����� ������ ��� ����������� ������������ ������� ������ � ������ �� ������� ������ ������ ������
		 for ( int i = 0; i < Calc.butFPZnak.length; i++)
		 { //������ �� ������� ������ ��������
			//���� ����� ������� ������ � ����� ������ �� ������� ���������
			 if (event.getSource() == Calc.butFPZnak[i]) 
				{ //������ ������� �������� (������)	
				 	
				  	//����� �� ������� ������� ������� ��� ������������
					//System.out.println("������ ������ " + event.getActionCommand() );
					
					//����� ������ ������ �������� ������� �������������� ������ ��������		
					
					//���� �� ����� ������� �������� �� ���������, �.�. ��������� ����� ������� ����� � ������� ������ ������� ��������
					//�� ���� ��� ������� ������� �������� ����� ����  - ��������� ��� � ��������� ��������� ��������
					if (pushFuncCounter == 0)
						{ // ���� ������� ������� ������ �������� ����� ����
							
							//������� �������� ���������� � ������ � ������ ��������, 
							//���� ����������� �������� ����� ����� ����� ������� ����� �������� ���������� ����� ��� ����� ������� �����
							//������� ���������� � ������� ������ ����. ������ ���������� �� ������� ��
							operand1 = 	CalcOperations.getTFDigitValue();
							operand2 = 	CalcOperations.getTFDigitValue();
							
							//������ � ��������� �������� �������� ������� �������
							Calc.calcTF.setText(event.getActionCommand());
							
							//� ������������ � �������� � ���� �������� ���� ��� ������� �������� ������� ������� ��������
							//��� ����� ������� ������������� �� ���� ������ � ��� ���, �������������� ����� ������� �� �������� �������,
							//�������� ����� ��� ������� ���������� ����������� ������ ������
							operation = (event.getActionCommand()).trim().charAt(0);
							//����� ���� �������� �� ����� ��� ������������
							System.out.println("���� ��������  " + operation);
							
							//����������� ������� �������� (������� ������ ��������) �� �������
							pushFuncCounter++;
							//����� ��� �������� ���������
							System.out.println();
							System.out.println("�������1 = " +  operand1);
							System.out.println("�������2 = " +  operand2);
							System.out.println("������� ������� "+ pushFuncCounter);
							System.out.println();
						} //������� ������� ������ �������� ����� ����
				
					//���� ������� ������� ������� ������ 0 (�.�. ������ ����� ��� ��������� �����, � ����� ����� ������� ���� ������� ������ �����)
					//����� ������� �� ������ ������� ����� �������� � �������� ��������, ��� ���� ��������� ���������� � �������1
					else if (pushFuncCounter > 0) 
						{  //������� ������� ������ �������� ������ ����
						  // ������� ������������ ������� ������ �� ������ ��������������
						  operand2 = CalcOperations.getTFDigitValue();
					
						  //����� ������ ���������� ��������
						  CalcOperations.makeOper();
						  
						 //������ � ��������� �������� �������� ������� �������
						 Calc.calcTF.setText(event.getActionCommand());
						 //� ���� �������� ���� ��� ������� �������� ������� ������� ��������
						 //��� ����� ������� ������������� �� ���� ������ � ��� ���, �������������� ����� ������� �� �������� �������,
						 //�������� ����� ��� ������� ���������� ����������� ������ ������	
						 operation = (event.getActionCommand()).trim().charAt(0);
							
							
						 //����� ���� �������� �� ����� ��� ������������
						 System.out.println("���� ����� ��������  " + operation);
						 
						 // ����������� �� ������� ������� ��������
						 pushFuncCounter++;
						  
						  //����� ��� �������� ���������
						  System.out.println();
						  System.out.println("�������1 = " +  operand1);
						  System.out.println("�������2 = " +  operand2);
						  System.out.println("������� ������� "+ pushFuncCounter);
						  System.out.println();
						}
					
					//������� �� ����� ��� �������� ��������� �� �������
					break;
					
		
				
				} //������ ������� �������� (������)
		
	 
		 } //���� ��������� ������ �������� (�������)
			 
	}
	
	
//----------------------------------------������������ �����������---------------------------------------------------------	

	/*
	 * (non-Javadoc)
	31.3.1.2.	�������� � ����������� ��������� ������ ����������� � ��� ������� ����� �� �����  � ���������� ������ ���������
	 �����. �����. ��������� , ����� ��������� ����� �������� ����� ����� ������� ����� ��� ��������������� ������ ����.
	 */
	
	
	//���������� ������� ������ �� ����������
	@Override
	public void keyPressed(KeyEvent event) 
	{ // ������ ����������� ������� ������ �� ����������
		// TODO Auto-generated method stub
		
		//����� ���������� ������� ������� ��� ��������
//		System.out.println(event.paramString());
//		System.out.println(event.getKeyChar());
//		System.out.println(event.getKeyText( event.getKeyCode() ));
//		System.out.println(event.getKeyText(  event.getExtendedKeyCode()   ));
		
		
			//���� ��������� ������� � ���������� �������� ������ � �����
			//�.�. ������� ����������� � �����, �� � ��� ����������� ������������ ����� ������� ������� � ������� � ������� ����� ����� ������������ ����
			for ( int i = 0; i < Calc.digiBut.length; i++)
			{ //���� ��������� ������� �������� ������ � �����
				
				//�������� �� ������������ ������� ������� ������� �����
				//����� ��������� ������� �������� ������� ����� (����������� ����� � ������� �����)
				if (((event.getKeyChar()) >= '0') && ((event.getKeyChar()) <= '9')) 
					{ //���� ������ �����
						//�����  ������� ������ �� ������� ��� ������������
//						System.out.println("������ ������ " + event.getKeyChar() );
						//��������� � ��������� ����� ����������� �������� � ������� ������� ������� �����
						Calc.calcTF.setText( Calc.calcTF.getText() + event.getKeyChar());
						//��������� ������ �� ������ �� ������� �������� ������ ��������������� ������� �����
						Calc.digiBut[Integer.valueOf(event.getKeyText(event.getKeyCode()))].requestFocus();
						//������� �� ����� ��� �������� ��������� �� �������
						break;
					} //���� ������ ����� 
				else if (event.getKeyChar() == '.')
					{ //���� ������ �����
						//�����  ������� ������ �� ������� ��� ������������
//						System.out.println("������ ������ " + event.getKeyChar() );
						//��������� � ��������� ����� ����������� �������� � ������� ������� �����
						Calc.calcTF.setText( Calc.calcTF.getText() + event.getKeyChar());
						//��������� ������ �� ������ �����
						Calc.butFPDel[1].requestFocus();
						//������� �� ����� ��� �������� ��������� �� �������
						break;
					}
			
						//����� �������� �������  ������� ��� ������������
//						System.out.println("������� ������� ������� " + pushFuncCounter);
						
			} //���� ��������� ������� �������� ������ � �����
			
			
			
			
			
			
	
		// 31.3.1.3.	�������� ������������ ��������� �������������� �������� � ����� ��� ������� �� ��������  ����������
		// ������ +-*/  ���������� �� ��  ������, ��� ��� ���������� ��� ����� �� ��������  +-*/   � ���� ������������.
						 
			//���� �������� ��������  ��������� ����� ��� ������� ������ ������ ( ���������� ������������)
			
		/*
		33.1.	�����������: 
		33.1.1.	����������� ��������� ������ ������������ � Ctrl ������� ��������� ����� �������������:
		CE - ^C, �+ - ^+, M- - ^- -.
		*/
		
		//���������� ������� ���������� ^C
		if ( (  (event.getModifiers() & event.CTRL_MASK)  !=0 ) &&  (event.getKeyCode()  ==  event.VK_C )) 
		{ //������ ���������� ������ ^C
			System.out.println( "������ ���������� ������ ^C - ���������� ������� � (�����) ");
			//����� ������ ������� ������� ������ �
			CalcOperations.resetPushing();
		} //������ ���������� ������ ^C	
		
		//���������� ������� ���������� ^+
		else if ( (  (event.getModifiersEx() & event.CTRL_DOWN_MASK)  !=0 ) &&  (event.getKeyChar()  == '+' /* event.VK_PLUS*/ )) 
			// � ������ ���� ��������������� ���������, ������-�� � ��� ������� �������� ������, � ������� �������� ��� ��������
		{ //������ ���������� ������ ^+
			System.out.println( "������ ���������� ������ ^+ - ���������� ������� M+ ");
			Calc.calcTF.setText(String.valueOf(operandM + CalcOperations.getTFDigitValue() ));
		} //������ ���������� ������ ^+	
		
		//���������� ������� ���������� ^-
		else if ( (  (event.getModifiersEx() & event.CTRL_DOWN_MASK)  !=0 ) &&  (event.getKeyChar()  ==  event.VK_MINUS )) 
		{ //������ ���������� ������ ^-
			System.out.println( "������ ���������� ������ ^C - ���������� ������� M- ");
			Calc.calcTF.setText(String.valueOf(operandM - CalcOperations.getTFDigitValue() ));
		} //������ ���������� ������ ^-	
		
		//���������� ������� ������-������� ( �����.��������)
		else 
		{	//���� ��������� �������������� ������  ������ �������� (�������)
			//�������� � ����� ������ ��� ����������� ������������ ������� ������ � ������ �� ������� ������ ������ ������
			 for ( int i = 0; i < Calc.butFPZnak.length; i++)
			 { //������ �� ������� ������ ��������
				//���� ������ ������� ������ � ������ ������ �� ������� ������ ���������
				 if ( event.getKeyChar() == Calc.strButFPZnak[i].charAt(1) ) //�� ����������� ������ �������� �������� ����� ������� ������
					{ //������ ������� �������� (������)
					 //��������� ������ �� ������
					 Calc.butFPZnak[i].requestFocus();
					  	//����� �� ������� ������� ������� ��� ������������
//						System.out.println("������ ������ " + event.getKeyChar() );
						
						//����� ������ ������ �������� ������� �������������� ������ ��������		
						
						//���� �� ����� ������� �������� �� ���������, �.�. ��������� ����� ������� ����� � ������� ������ ������� ��������
						//�� ���� ��� ������� ������� �������� ����� ����  - ��������� ��� � ��������� ��������� ��������
						if (pushFuncCounter == 0)
							{ // ���� ������� ������� ������ �������� ����� ����
								
								//������� �������� ���������� � ������ � ������ ��������, 
								//���� ����������� �������� ����� ����� ����� ������� ����� �������� ���������� ����� ��� ����� ������� �����
								//������� ���������� � ������� ������ ����. ������ ���������� �� ������� ��
								operand1 = 	CalcOperations.getTFDigitValue();
								operand2 = 	CalcOperations.getTFDigitValue();
								
								//������ � ��������� �������� �������� ������� �������
								Calc.calcTF.setText(" " + event.getKeyChar()+" ");
								
								//� ������������ � �������� � ���� �������� ���� ��� ������� �������� ������� ������� ��������
								//��� ����� ������� ������������� �� ���� ������ � ��� ���, �������������� ����� ������� �� �������� �������,
								//�������� ����� ��� ������� ���������� ����������� ������ ������
								operation = (event.getKeyChar());
								//����� ���� �������� �� ����� ��� ������������
								System.out.println("���� ��������  " + operation);
								
								//����������� ������� �������� (������� ������ ��������) �� �������
								pushFuncCounter++;
								//����� ��� �������� ���������
								System.out.println();
								System.out.println("�������1 = " +  operand1);
								System.out.println("�������2 = " +  operand2);
								System.out.println("������� ������� "+ pushFuncCounter);
								System.out.println();
							} //������� ������� ������ �������� ����� ����
					
						//���� ������� ������� ������� ������ 0 (�.�. ������ ����� ��� ��������� �����, � ����� ����� ������� ���� ������� ������ �����)
						//����� ������� �� ������ ������� ����� �������� � �������� ��������, ��� ���� ��������� ���������� � �������1
						else if (pushFuncCounter > 0) 
							{  //������� ������� ������ �������� ������ ����
							  // ������� ������������ ������� ������ �� ������ ��������������
							  operand2 = CalcOperations.getTFDigitValue();
						
							  //����� ������ ���������� ��������
							  CalcOperations.makeOper();
							  
							 //������ � ��������� �������� �������� ������� �������
							 Calc.calcTF.setText(" " + event.getKeyChar()+" ");
							 //� ���� �������� ���� ��� ������� �������� ������� ������� ��������
							 //��� ����� ������� ������������� �� ���� ������ � ��� ���, �������������� ����� ������� �� �������� �������,
							 //�������� ����� ��� ������� ���������� ����������� ������ ������	
							 operation = (event.getKeyChar());
								
								
							 //����� ���� �������� �� ����� ��� ������������
							 System.out.println("���� ����� ��������  " + operation);
							 
							 // ����������� �� ������� ������� ��������
							 pushFuncCounter++;
							  
							  //����� ��� �������� ���������
							  System.out.println();
							  System.out.println("�������1 = " +  operand1);
							  System.out.println("�������2 = " +  operand2);
							  System.out.println("������� ������� "+ pushFuncCounter);
							  System.out.println();
							}
						
						//������� �� ����� ��� �������� ��������� �� �������
						break;
								
					} //������ ������� �������� (������)
					 
			 } //���� ��������� ������ �������� (�������)
		}
		
		/*
		 31.3.1.4.	�������� ���������  ������ C, CE, BS �  �� (���������  ������), �+(������ + ������� � �������), �R (������ ������,
		  �.�.����� � ���������), MS (��������� ������� � ������� � ������) - . ����� ������� ���� ������, ���� � ����� �� 
		  ����� ������������ � ������������ ����-���� �������� �������, ������ � ��.  
		 ������������ ������ � ������� ������� �����:  � � �,  BS � ��������, �� � Delete, M+ - PgUp, MR � Insert, MS - Pause. 
		 ��������� ������ JButton ����� �������������� ���������  � ��������� �� � ������� ����������� ������ (��� �������������) Ctrl, Shift, Alt
		 */
		
		//���������� ������� � ���������� ���� ������ ����� ������ ���������� ����-����
			 switch (event.getKeyText( event.getKeyCode() )) 
			 { //���������� ������� � ���������� ����������� ������ � � �,  BS � ��������, �� � Delete, M+ - PgUp, MR � Insert, MS - Pause
		    	
			 	//������� ������� ������
			 	case "C":
			 		System.out.println("������ c ���������� ������� " + event.getKeyText( event.getKeyCode() ) );
					Calc.calcTF.setText("");
					operand1 = 0;
					operand2 = 0;
					pushFuncCounter = 0;
					//��������� ������ ��� ��������� ������
					Calc.butFPBS[0].requestFocus();
					break;	
				
				//������� ������� �������� ���������� �������
			 	case "Backspace":
			 		System.out.println("������ c ���������� ������� " + event.getKeyText( event.getKeyCode() ) );
					Calc.calcTF.setText(CalcOperations.delLastDigit());
					//��������� ������ ��� ��������� ������
					Calc.butFPDel[2].requestFocus();
					break;		
			 
				//������� ������� ����� ������ (MC)
				case "Delete":
					System.out.println("������ c ���������� ������� " + event.getKeyText( event.getKeyCode() ) );
					//������������� � ���� �������� ���������� ������
					operandM = 0;
					//��������� ������ ��� ��������� ������
					Calc.butFPBS[2].requestFocus();
					break;
					
					//���������� ���������� � ������ (MS)
				case "Pause":
					System.out.println("������ c ���������� ������� " + event.getKeyText( event.getKeyCode() ) );
					operandM = CalcOperations.getTFDigitValue();
					//��������� ������ ��� ��������� ������
					Calc.butFPMem[0].requestFocus();
					break;
				
				//������ �� ������ � ���������
				case "Insert":
					System.out.println("������ c ���������� ������� " + event.getKeyText( event.getKeyCode() ) );
					Calc.calcTF.setText(String.valueOf(operandM));
					//��������� ������ ��� ��������� ������
					Calc.butFPMem[1].requestFocus();
					break;
				
				//����������� � ���������� ����������� ������ ������ � ���������� ���������� � ����������
				case "Page Up":
					System.out.println("������ c ���������� ������� " + event.getKeyText( event.getKeyCode() ) );
					Calc.calcTF.setText(String.valueOf(operandM + CalcOperations.getTFDigitValue() ));
					//��������� ������ ��� ��������� ������
					Calc.butFPMem[2].requestFocus();
					break;
		
		
		
			 } //���������� ������� � ���������� ����������� ������ � � �,  BS � ��������, �� � Delete, M+ - PgUp, MR � Insert, MS - Pause
	
			 
						 
			 
	} // ����� ����������� ������� ������ �� ����������


	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
