package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CalcListener implements ActionListener, KeyListener {
//класс для обработчиков нажатий клавиш калькулятора
	
	
	//объявление полей текстФилда калькулятора, а также набираемых последовательностей символов операндов
	//String  tempTF;
	static double operand1, operand2, operandM;
	
	//объявление поля операции, в соотв. с постановкой задания 28.2.2.2 она будет типа Чар
	static char operation;
		
	//объявление и определение счетчика нажатий клавиш
//	 int pushDigCounter = 0;		//счетчик для цифровых клавиш
	static int pushFuncCounter = 0;		//счетчик для клавиш операций

	//инициализация тестфилда для предотвращения эксепшена при обработке цифровых клавиш
	

	public static void main(String[] args) {
		
	
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		//блок обработки цифровых клавиш
		//т.к. клавиши создавались в цикле, то и для определения соответствия между нажатой кнопкой и кнопкой в массиве также будем использовать цикл
		for ( int i = 0; i < Calc.digiBut.length; i++)
			//если адрес нажатой кнопки и адрес кнопки из массива совпадают
			//также добавлено условие проверки нажатия точки (разделитель целой и дробной части)
			if ((event.getSource() == Calc.digiBut[i])||(event.getSource()==Calc.butFPDel[1])) 
				{ //если нажата цифра или точка
					//вывод  нажатой кнопки на консоль для самоконтроля
					//System.out.println("нажата кнопка " + event.getActionCommand() );
					
					//устанваливаем в текстфилд значение равное сумме предыдущего значения и набранной клавише
					Calc.calcTF.setText(Calc.calcTF.getText() + event.getActionCommand());
			
					
					//вывод счетчика нажатий  функций для самопроверки
					//System.out.println("счетчик нажатий функций " + pushFuncCounter);
					//выходим из цикла при успешной отработке по условию
					break;
				} //если нажата цифра или точка
		
		
		
		
		//блок обработки функциональных клавиш панели del ( клавиши +/- , del, = ) 
		//клавишу с точкой обработали ранее вместе с клавишами цифровой панели
		//так как у разных кнопок разные обработчики, нет смысла применять цикл - используем анализ с помощью оператора кейс
		switch (event.getActionCommand()) 
		{
		
		case "+/-":
			System.out.println("нажата кнопка " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(CalcOperations.plusMinus()));
			break;
			
		case "%":
			System.out.println("нажата кнопка " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(CalcOperations.getPercent()));
			if (pushFuncCounter == 1) 
			{
				operand2 = CalcOperations.getPercent();
			}
			break;
		
		case "=":
			CalcOperations.equalPushing();
			break;
		
		// нажатие клавиши сброс
		case "C":
			System.out.println("нажата кнопка " + event.getActionCommand() );
			//вызов метода нажатия клавиши сброса С
			CalcOperations.resetPushing();
			break;
		
		//нажатие клавиши сброс памяти
		case "MC":
			System.out.println("нажата кнопка " + event.getActionCommand() );
			//устанавливаем в ноль значение переменной памяти
			operandM = 0;
			break;
			
		//нажатие клавиши бэкспейс
		case "BS":
			System.out.println("нажата кнопка " + event.getActionCommand() );
			Calc.calcTF.setText(CalcOperations.delLastDigit());
			break;
			
		//квадратный корень в перспективе
		case "SQR":
			System.out.println("нажата кнопка " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(CalcOperations.getSqr()));
			break;
			
		//сохранение текстфилда в память
		case "MS":
			System.out.println("нажата кнопка " + event.getActionCommand() );
			operandM = CalcOperations.getTFDigitValue();
			break;
		
		//чтение из памяти в текстфилд
		case "MR":
			System.out.println("нажата кнопка " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(operandM));
			break;
		
		//прибавление к текстфилду содержимого ячейки памяти и сохранение результата в текстфилде
		case "M+":
			System.out.println("нажата кнопка " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(operandM + CalcOperations.getTFDigitValue() ));
			break;
			
		//вычитание  текстфилда из содержимого ячейки памяти и сохранение результата в текстфилде
		case "M-":
			System.out.println("нажата кнопка " + event.getActionCommand() );
			Calc.calcTF.setText(String.valueOf(operandM - CalcOperations.getTFDigitValue() ));
			break;
			
			
			
		
		default:
			break;
		}
		
		
		
		
		
		
		//блок обработки функциональных клавиш  панели операций (значков)
		//проходим в цикле кнопки для определения соответствия нажатой кнопки и кнопки из массива кнопок второй панели
		 for ( int i = 0; i < Calc.butFPZnak.length; i++)
		 { //проход по массиву клавиш операций
			//если адрес нажатой кнопки и адрес кнопки из массива совпадают
			 if (event.getSource() == Calc.butFPZnak[i]) 
				{ //нажата клавиша операции (значок)	
				 	
				  	//вывод на консоль нажатой клавиши для самопроверки
					//System.out.println("нажата кнопка " + event.getActionCommand() );
					
					//далее делаем анализ счетчика нажатий функциональных клавиш операций		
					
					//если до этого момента операции не вводились, т.е. произешел набор первого числа и нажатие первой клавиши операции
					//то пока что счетчик нажатий операций равен нулю  - проверяем это и выполняем следующие действия
					if (pushFuncCounter == 0)
						{ // если счетчик нажатий клавиш операций равен нулю
							
							//заносим значение текстфилда в первый и второй операнды, 
							//этим разрешается ситуация когда сразу после первого ввода операции нажимается равно без ввода второго числа
							//операнд определяем с помощью вызова спец. метода созданного по условию ДЗ
							operand1 = 	CalcOperations.getTFDigitValue();
							operand2 = 	CalcOperations.getTFDigitValue();
							
							//ставим в текстфилд значение операции нажатой клавиши
							Calc.calcTF.setText(event.getActionCommand());
							
							//в соответствии с заданием в поле операции типа чар занесем значение нажатой клавиши операции
							//для этого сделаем преобразовние из типа стринг в тип чар, предварительно убрав пробелы из значения клавиши,
							//введеные ранее для задания примерного соотношения ширины клавиш
							operation = (event.getActionCommand()).trim().charAt(0);
							//вывод поля операции на экран для самопроверки
							System.out.println("поле операции  " + operation);
							
							//увеличиваем счетчик операций (нажатий клавиш операций) на единицу
							pushFuncCounter++;
							//вывод для проверки операндов
							System.out.println();
							System.out.println("операнд1 = " +  operand1);
							System.out.println("операнд2 = " +  operand2);
							System.out.println("счетчик функций "+ pushFuncCounter);
							System.out.println();
						} //счетчик нажатий клавиш операций равен нулю
				
					//если счетчик нажатия функций больше 0 (т.е. первое число уже вводилось ранее, а далее после функции было введено второе число)
					//тогда занесем во второй операнд новое значение и выполним операцию, при этом результат помещается в операнд1
					else if (pushFuncCounter > 0) 
						{  //счетчик нажатий клавиш операций больше нуля
						  // операнд определяется вызовом метода из класса КалкОперейшенз
						  operand2 = CalcOperations.getTFDigitValue();
					
						  //вызов метода выполнения операции
						  CalcOperations.makeOper();
						  
						 //ставим в текстфилд значение операции нажатой клавиши
						 Calc.calcTF.setText(event.getActionCommand());
						 //в поле операции типа чар занесем значение нажатой клавиши операции
						 //для этого сделаем преобразовние из типа стринг в тип чар, предварительно убрав пробелы из значения клавиши,
						 //введеные ранее для задания примерного соотношения ширины клавиш	
						 operation = (event.getActionCommand()).trim().charAt(0);
							
							
						 //вывод поля операции на экран для самопроверки
						 System.out.println("поле новой операции  " + operation);
						 
						 // увеличиваем на единицу счетчик операций
						 pushFuncCounter++;
						  
						  //вывод для проверки операндов
						  System.out.println();
						  System.out.println("операнд1 = " +  operand1);
						  System.out.println("операнд2 = " +  operand2);
						  System.out.println("счетчик функций "+ pushFuncCounter);
						  System.out.println();
						}
					
					//выходим из цикла при успешной отработке по условию
					break;
					
		
				
				} //нажата клавиша операции (значок)
		
	 
		 } //блок обработки клавиш операции (значков)
			 
	}
	
	
//----------------------------------------Клавиатурные обработчики---------------------------------------------------------	

	/*
	 * (non-Javadoc)
	31.3.1.2.	Добавить в Калькулятор обработку кнопок клавиатурой – при нажатии цифры на Клаве  в ТекстФилде должна появиться
	 соотв. цифра. Проверить , чтобы обработка Клавы работала сразу после запуска проги без предварительных кликов мыши.
	 */
	
	
	//обработчик нажатия клавиш на клавиатуре
	@Override
	public void keyPressed(KeyEvent event) 
	{ // начало обработчика нажатия клавиш на клавиатуре
		// TODO Auto-generated method stub
		
		//вывод параметров нажатой клавиши для контроля
//		System.out.println(event.paramString());
//		System.out.println(event.getKeyChar());
//		System.out.println(event.getKeyText( event.getKeyCode() ));
//		System.out.println(event.getKeyText(  event.getExtendedKeyCode()   ));
		
		
			//блок обработки нажатия с клавиатуры цифровых клавиш и точки
			//т.к. клавиши создавались в цикле, то и для определения соответствия между нажатой кнопкой и кнопкой в массиве также будем использовать цикл
			for ( int i = 0; i < Calc.digiBut.length; i++)
			{ //блок обработки нажатий цифровых клавиш и точки
				
				//проверка на соответствие символа нажатой клавиши цифре
				//также добавлено условие проверки нажатия точки (разделитель целой и дробной части)
				if (((event.getKeyChar()) >= '0') && ((event.getKeyChar()) <= '9')) 
					{ //если нажата цифра
						//вывод  нажатой кнопки на консоль для самоконтроля
//						System.out.println("нажата кнопка " + event.getKeyChar() );
						//установка в текстфилд суммы предыдущего значения и символа нажатой клавиши цифры
						Calc.calcTF.setText( Calc.calcTF.getText() + event.getKeyChar());
						//установка фокуса на кнопку из массива цифровых кнопок соответствующую нажатой цифре
						Calc.digiBut[Integer.valueOf(event.getKeyText(event.getKeyCode()))].requestFocus();
						//выходим из цикла при успешной отработке по условию
						break;
					} //если нажата цифра 
				else if (event.getKeyChar() == '.')
					{ //если нажата точка
						//вывод  нажатой кнопки на консоль для самоконтроля
//						System.out.println("нажата кнопка " + event.getKeyChar() );
						//установка в текстфилд суммы предыдущего значения и символа нажатой точки
						Calc.calcTF.setText( Calc.calcTF.getText() + event.getKeyChar());
						//установка фокуса на кнопку точки
						Calc.butFPDel[1].requestFocus();
						//выходим из цикла при успешной отработке по условию
						break;
					}
			
						//вывод счетчика нажатий  функций для самопроверки
//						System.out.println("счетчик нажатий функций " + pushFuncCounter);
						
			} //блок обработки нажатий цифровых клавиш и точки
			
			
			
			
			
			
	
		// 31.3.1.3.	Добавить клавиатурную обработку арифметических действий – чтобы при нажатии НА ЦИФРОВОЙ  клавиатуре
		// знаков +-*/  вызывались ТЕ ЖЕ  МЕТОДЫ, что уже вызываются при клике по баттонам  +-*/   в окне калькулятора.
						 
			//блок является аналогом  подобного блока для нажятия клавиш мышкой ( обработчик экшенПерформ)
			
		/*
		33.1.	Калькулятор: 
		33.1.1.	Реализовать обработку кнопок Калькулятора с Ctrl помощью механизма масок модификаторов:
		CE - ^C, М+ - ^+, M- - ^- -.
		*/
		
		//обработчик нажатия комбинации ^C
		if ( (  (event.getModifiers() & event.CTRL_MASK)  !=0 ) &&  (event.getKeyCode()  ==  event.VK_C )) 
		{ //нажата комбинация клавиш ^C
			System.out.println( "нажата комбинация клавиш ^C - равноценно клавише С (сброс) ");
			//вызов метода нажатия клавиши сброса С
			CalcOperations.resetPushing();
		} //нажата комбинация клавиш ^C	
		
		//обработчик нажатия комбинации ^+
		else if ( (  (event.getModifiersEx() & event.CTRL_DOWN_MASK)  !=0 ) &&  (event.getKeyChar()  == '+' /* event.VK_PLUS*/ )) 
			// в строке выше закоментирована константа, почему-то с ней вылазит простыня ошибок, с простым символом все работает
		{ //нажата комбинация клавиш ^+
			System.out.println( "нажата комбинация клавиш ^+ - равноценно клавише M+ ");
			Calc.calcTF.setText(String.valueOf(operandM + CalcOperations.getTFDigitValue() ));
		} //нажата комбинация клавиш ^+	
		
		//обработчик нажатия комбинации ^-
		else if ( (  (event.getModifiersEx() & event.CTRL_DOWN_MASK)  !=0 ) &&  (event.getKeyChar()  ==  event.VK_MINUS )) 
		{ //нажата комбинация клавиш ^-
			System.out.println( "нажата комбинация клавиш ^C - равноценно клавише M- ");
			Calc.calcTF.setText(String.valueOf(operandM - CalcOperations.getTFDigitValue() ));
		} //нажата комбинация клавиш ^-	
		
		//обработчик нажатий клавиш-значков ( арифм.операций)
		else 
		{	//блок обработки функциональных клавиш  панели операций (значков)
			//проходим в цикле кнопки для определения соответствия нажатой кнопки и кнопки из массива кнопок второй панели
			 for ( int i = 0; i < Calc.butFPZnak.length; i++)
			 { //проход по массиву клавиш операций
				//если символ нажатой кнопки и символ кнопки из массива кнопок совпадают
				 if ( event.getKeyChar() == Calc.strButFPZnak[i].charAt(1) ) //из трехзначной строки значения операции берем средний значок
					{ //нажата клавиша операции (значок)
					 //установка фокуса на кнопку
					 Calc.butFPZnak[i].requestFocus();
					  	//вывод на консоль нажатой клавиши для самопроверки
//						System.out.println("нажата кнопка " + event.getKeyChar() );
						
						//далее делаем анализ счетчика нажатий функциональных клавиш операций		
						
						//если до этого момента операции не вводились, т.е. произешел набор первого числа и нажатие первой клавиши операции
						//то пока что счетчик нажатий операций равен нулю  - проверяем это и выполняем следующие действия
						if (pushFuncCounter == 0)
							{ // если счетчик нажатий клавиш операций равен нулю
								
								//заносим значение текстфилда в первый и второй операнды, 
								//этим разрешается ситуация когда сразу после первого ввода операции нажимается равно без ввода второго числа
								//операнд определяем с помощью вызова спец. метода созданного по условию ДЗ
								operand1 = 	CalcOperations.getTFDigitValue();
								operand2 = 	CalcOperations.getTFDigitValue();
								
								//ставим в текстфилд значение операции нажатой клавиши
								Calc.calcTF.setText(" " + event.getKeyChar()+" ");
								
								//в соответствии с заданием в поле операции типа чар занесем значение нажатой клавиши операции
								//для этого сделаем преобразовние из типа стринг в тип чар, предварительно убрав пробелы из значения клавиши,
								//введеные ранее для задания примерного соотношения ширины клавиш
								operation = (event.getKeyChar());
								//вывод поля операции на экран для самопроверки
								System.out.println("поле операции  " + operation);
								
								//увеличиваем счетчик операций (нажатий клавиш операций) на единицу
								pushFuncCounter++;
								//вывод для проверки операндов
								System.out.println();
								System.out.println("операнд1 = " +  operand1);
								System.out.println("операнд2 = " +  operand2);
								System.out.println("счетчик функций "+ pushFuncCounter);
								System.out.println();
							} //счетчик нажатий клавиш операций равен нулю
					
						//если счетчик нажатия функций больше 0 (т.е. первое число уже вводилось ранее, а далее после функции было введено второе число)
						//тогда занесем во второй операнд новое значение и выполним операцию, при этом результат помещается в операнд1
						else if (pushFuncCounter > 0) 
							{  //счетчик нажатий клавиш операций больше нуля
							  // операнд определяется вызовом метода из класса КалкОперейшенз
							  operand2 = CalcOperations.getTFDigitValue();
						
							  //вызов метода выполнения операции
							  CalcOperations.makeOper();
							  
							 //ставим в текстфилд значение операции нажатой клавиши
							 Calc.calcTF.setText(" " + event.getKeyChar()+" ");
							 //в поле операции типа чар занесем значение нажатой клавиши операции
							 //для этого сделаем преобразовние из типа стринг в тип чар, предварительно убрав пробелы из значения клавиши,
							 //введеные ранее для задания примерного соотношения ширины клавиш	
							 operation = (event.getKeyChar());
								
								
							 //вывод поля операции на экран для самопроверки
							 System.out.println("поле новой операции  " + operation);
							 
							 // увеличиваем на единицу счетчик операций
							 pushFuncCounter++;
							  
							  //вывод для проверки операндов
							  System.out.println();
							  System.out.println("операнд1 = " +  operand1);
							  System.out.println("операнд2 = " +  operand2);
							  System.out.println("счетчик функций "+ pushFuncCounter);
							  System.out.println();
							}
						
						//выходим из цикла при успешной отработке по условию
						break;
								
					} //нажата клавиша операции (значок)
					 
			 } //блок обработки клавиш операции (значков)
		}
		
		/*
		 31.3.1.4.	Добавить обработку  кнопок C, CE, BS и  МС (обнуление  памяти), М+(память + операнд с дисплея), МR (чтение памяти,
		  т.е.вывод в ТекстФилд), MS (запомнить операнд с дисплея в память) - . Можно сделать свои кнопки, если в жизни вы 
		  часто встречаетесь с вычислениями чего-либо например степень, корень и пр.  
		 Соответствие клавиш и Батонов сделать таким:  С – С,  BS – бэкспейс, МС – Delete, M+ - PgUp, MR – Insert, MS - Pause. 
		 Остальные кнопки JButton будут обрабатываться клавишами  в следующем ДЗ с помощью управляющих клавиш (или модификаторов) Ctrl, Shift, Alt
		 */
		
		//обработчик нажатия с клавиатуры этих клавиш будет сделан оператором свич-кейс
			 switch (event.getKeyText( event.getKeyCode() )) 
			 { //обработчик нажатия с клавиатуры управляющих клавиш С – С,  BS – бэкспейс, МС – Delete, M+ - PgUp, MR – Insert, MS - Pause
		    	
			 	//нажатие клавиши сброса
			 	case "C":
			 		System.out.println("нажата c клавиатуры клавиша " + event.getKeyText( event.getKeyCode() ) );
					Calc.calcTF.setText("");
					operand1 = 0;
					operand2 = 0;
					pushFuncCounter = 0;
					//установка фокуса для выделения кнопки
					Calc.butFPBS[0].requestFocus();
					break;	
				
				//нажатие клавиши удаления последнего символа
			 	case "Backspace":
			 		System.out.println("нажата c клавиатуры клавиша " + event.getKeyText( event.getKeyCode() ) );
					Calc.calcTF.setText(CalcOperations.delLastDigit());
					//установка фокуса для выделения кнопки
					Calc.butFPDel[2].requestFocus();
					break;		
			 
				//нажатие клавиши сброс памяти (MC)
				case "Delete":
					System.out.println("нажата c клавиатуры клавиша " + event.getKeyText( event.getKeyCode() ) );
					//устанавливаем в ноль значение переменной памяти
					operandM = 0;
					//установка фокуса для выделения кнопки
					Calc.butFPBS[2].requestFocus();
					break;
					
					//сохранение текстфилда в память (MS)
				case "Pause":
					System.out.println("нажата c клавиатуры клавиша " + event.getKeyText( event.getKeyCode() ) );
					operandM = CalcOperations.getTFDigitValue();
					//установка фокуса для выделения кнопки
					Calc.butFPMem[0].requestFocus();
					break;
				
				//чтение из памяти в текстфилд
				case "Insert":
					System.out.println("нажата c клавиатуры клавиша " + event.getKeyText( event.getKeyCode() ) );
					Calc.calcTF.setText(String.valueOf(operandM));
					//установка фокуса для выделения кнопки
					Calc.butFPMem[1].requestFocus();
					break;
				
				//прибавление к текстфилду содержимого ячейки памяти и сохранение результата в текстфилде
				case "Page Up":
					System.out.println("нажата c клавиатуры клавиша " + event.getKeyText( event.getKeyCode() ) );
					Calc.calcTF.setText(String.valueOf(operandM + CalcOperations.getTFDigitValue() ));
					//установка фокуса для выделения кнопки
					Calc.butFPMem[2].requestFocus();
					break;
		
		
		
			 } //обработчик нажатия с клавиатуры управляющих клавиш С – С,  BS – бэкспейс, МС – Delete, M+ - PgUp, MR – Insert, MS - Pause
	
			 
						 
			 
	} // конец обработчика нажатия клавиш на клавиатуре


	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
