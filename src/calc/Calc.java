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
	
	//создание объекта класса КалкЛисенер для обеспечения возможности использования обработчиков событий, написанных в классе КалкЛисенер
	CalcListener objCalcListener = new  CalcListener();
	
	//объявление текстфилда 
	static TextField calcTF ;
	
	
	//объявление массива кнопок от 0 до 9
	//модификатор статик нужен для видимости и удобства обращения к массиву кнопок в классе обработчике событий
	static JButton [] digiBut = new JButton[10];
	
	//объявление и определение массивов значений функциональных кнопок, на базе которых будут определяться массивы самих кнопок
	//такое объявление вместе с заданием одного из параметров ГридЛэйаута кнопочной панели в виде длины массива значений кнопок 
	//позволяет гибко менять конфигурацию окна и кол-во кнопок
	static String [] strButFPDel = {"+/-",".","BS","="};
	static JButton [] butFPDel = new JButton[strButFPDel.length];
	static String [] strButFPZnak = {" / "," * "," - "," + "};
	static JButton [] butFPZnak = new JButton[strButFPZnak.length];
	static String [] strButFPBS = {"C","MC","%","SQR"};
	static JButton [] butFPBS = new JButton[strButFPBS.length];
	static String [] strButFPMem = {"MS","MR","M+","M-"};
	static JButton [] butFPMem = new JButton[strButFPMem.length];
	
			
	/*
	5.2.	Программным путем создать окно калькулятора,  с отдельными панелями, границами и лейаутами для различных групп кнопок:
	 textField |  цифры   |    +/- , |   ариф. Знаки | М+ М- МС  | кв корень BS CE C
	 */
	
	//создаем конструктор с использованием Суперкласса ДжиФрейм
		public Calc(String arg0) throws HeadlessException 
		{
			super(arg0);
			
			//определяем координаты и размеры окна
			setLocation(300, 300);
			setSize(300,	400);
			//устанавливаем режим закрытия программы по закрыти. окна
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
			
			//задаем менеджер размещения типа Бордер
			setLayout(new BorderLayout());
			//создаем две панели - одна для текстфилда, другая для клавишного поля
			JPanel panTF = new JPanel();
			//клавишное поле будет разбито на две части, слева цифровые клавиши, справа клавиши функций
			JPanel panKeys = new JPanel(new GridLayout(2,1,10,10));
			//делаем на панелях бордеры (рамки)
			panTF.setBorder(BorderFactory.createEtchedBorder());
			panKeys.setBorder(BorderFactory.createEtchedBorder());
			
			//создание текстфилда для верхней панели
			calcTF = new TextField();
		    //первичная инициализация текстфилда для избежания эксепшена при обработке вервого ввода 
			calcTF.setText("");
			// зададим желаемый размер текстфилда
			calcTF.setPreferredSize(new Dimension(280, 40));
			//определяем шрифт для использования в текстфилде
			Font tFFont = Font.decode("Arial-Bold-32");
			calcTF.setFont(tFFont);
			panTF.add(calcTF);
			
			
			/*
			 31.3.1.	Калькулятор и проблемы с фокусом: 
			 31.3.1.1.	В ТекстФилде отключить неконтролируемый ввод и обработку ТекстФилдом  нажатия Клавы по умолчанию.
			 */
			
			calcTF.setFocusable(false);
			
			
			
						
			//добавляем панель текстфилда в основное окно, задаем размещение
			add(panTF, BorderLayout.NORTH);
			
			
			//создаем две панели для кнопочных полей, которые войдут в центральную панель экрана tFKeys
			//первое поле для цифр
			JPanel digiPanel = new JPanel();
			//задаем полю рамочку
			digiPanel.setBorder(BorderFactory.createEtchedBorder());
			
			//второе поле для блоков функциональных клавиш
			JPanel funcKeysPanel = new JPanel();
			//задаем полю рамочку
			funcKeysPanel.setBorder(BorderFactory.createEtchedBorder());
			
			
			//---------------настройка панели цифровых клавиш-----------------------------------------------------
			
			//задаем менеджер размещения для панели с цифровыми клавишами
			//еще раз поделим панель на 2 поля  - на поле 3 на 3 для цифр от 1 до 9 , и отдельную большую кнопку 0
			//зададим менеджер для вертикального размещения подполей
			digiPanel.setLayout(new BoxLayout(digiPanel, BoxLayout.Y_AXIS));
			digiPanel.setLayout(new BoxLayout(digiPanel, BoxLayout.Y_AXIS));
			//создадим  панель клавиш цифр от 1 до 9
			JPanel dig19 = new JPanel();
			//создадим панель клавиши 0
			JPanel dig0	= new JPanel();
			//задаем менеджеры размещения для подпанелей цифр
			dig19.setLayout(new GridLayout(3, 3,2,2));	//решетка 3 на 3 для цифр 1 - 9
			dig0.setLayout(new GridLayout(1, 1));	//рештка на один квадратик, для растягивания кнопки 0 на возможный размер общей панели цифровых клавиш
			
			//создаем кнопки			
			//определяем шрифт для надписей в кнопках Эриал 16,  жирный 
			Font digiFont = Font.decode("Arial-Bold-16");
			
			//создаем и добавляем кнопки в цикле
			//в конструкторе кнопки задаем ее подпись
			//подписи кнопок от 0 до 9 удобно задавать опираясь на индекс цикла
			for (int i = 0; i < digiBut.length; i++) 
				{ //цикл создания кнопок
					//создание текущей кнопки
					digiBut[i] = new JButton(String.valueOf(i));
					
					//предварительно установим для кнопки шрифт заполняющей надписи и ее цвет (синий)
					digiBut[i].setFont(digiFont);
					digiBut[i].setForeground(Color.blue);
					//добавляем текущую кнопку в свою подпанель, для кнопки 0 одна подпанель, для всех остальных другая
					if (i == 0) 
						{
						 	dig0.add(digiBut[i]);
						}
					else
						{
							dig19.add(digiBut[i]);
						}
					//добавляем слушатель события нажатия кнопки - композиционный объект из класса обработчика событий КалкЛисенер
					digiBut[i].addActionListener(objCalcListener);
					digiBut[i].addKeyListener(objCalcListener);
					digiBut[i].setFocusable(true);
					
					
					
					/*
					 32.2.1.	Калькулятор: Реализовать  щелчок по цифр кнопке при нажатом Ctrl (клавиша Ctrl  часто обозначается шапочкой ^ ),
					  как сигнал нажатия равно. (очень удобно при использовании – не надо лишний раз нажимать равно, можно просто при последней
					   цифре нажать Контрол). Использовать метод сравнения текста модификатора.
					 */
					
					//выполним по аналогии с занятием, присоединим к цифровым кнопкам слушатель мыши, причем используем анонимный адаптер,
					//что позволяет держать в коде только нужный обработчик
					digiBut[i].addMouseListener(new MouseAdapter() {
				
						@Override
						// обработчик клика мыши по цифровым кнопкам
						public void mouseClicked(MouseEvent event)
						{ // если клик состоялся
							// TODO Auto-generated method stub
							//вывод инфы о событии для проверки
							System.out.println("Нажата кнопка мыши:  " + event.getButton() );
							System.out.println("Нажата упр. клавиша:" + event.getMouseModifiersText( event.getModifiers()  )	);
							// проверка условия нажатия клавиши Контрол вместе с нажатием цифровой клавиши
							// если нажата заданная комбинация - это равносильно нажатию клавиши равно - вызов метода нажатия равно
							if (   event.getMouseModifiersText( event.getModifiers()  ).equalsIgnoreCase("Ctrl+Button1")) 
								{ //нажатие Контрол + цифровая клавиша
									CalcOperations.equalPushing();  // вызов метода нажатия клавиши равно
								} //нажатие Контрол + цифровая клавиша
						} //если клик состоялся
					});
					
					
					
				} //цикл создания кнопок
			
			
			
			
			
			//добавляем 2 подпанели кнопок на общую кнопочную панель цифр
			digiPanel.add(dig19);
			digiPanel.add(dig0);
			
			
			//---------------------настройка панели блоков функциональных клавиш---------------------------
			
			//предполагается разместить заданные по условию блоки нецифровых клавиш вертикально
			//для каждого блока создается своя панель
			//задаем менеджер размещения для панели функциональных клавиш
			funcKeysPanel.setLayout(new BoxLayout(funcKeysPanel, BoxLayout.X_AXIS));
			
			//создаем подпанели
			JPanel funcPanel1 = new JPanel();
			funcPanel1.setBorder(BorderFactory.createEtchedBorder());
			JPanel funcPanel2 = new JPanel(); 
			funcPanel2.setBorder(BorderFactory.createEtchedBorder());
			JPanel funcPanel3 = new JPanel();
			funcPanel3.setBorder(BorderFactory.createEtchedBorder());
			JPanel funcPanel4 = new JPanel();
			funcPanel4.setBorder(BorderFactory.createEtchedBorder());
			
			//для первой подпанели функциональных клавиш берем кнопки заданные по заданию +/- и запятая
			// также мозьмем для нее дополнительно кнопку редактирования(удаления посл. знака) и кнопку равно( выполнить расчет)
			//для этой подпанели используем гридЛэйаут
			
		//---------в результате оптимизации объявление и определение кнопок перенесено в область полей класса----------
			funcPanel1.setLayout(new GridLayout(butFPDel.length,1,3,3));
			//создаем кнопочки в цикле, задаем им ранее созданный шрифт и добавляем кнопки в свою подпанель
			for (int i = 0; i < butFPDel.length; i++) 
				{
					//определим для текущей кнопки шрифт, фон и цвет надписи, добавим кнопки на панель и добавим слушатель событий
					butFPDel[i] = new JButton(strButFPDel[i]);
					butFPDel[i].setFont(digiFont);
					butFPDel[i].setForeground(Color.blue);
					if (i == 3) {butFPDel[i].setBackground(Color.GREEN);	}
					funcPanel1.add(butFPDel[i]);
					//добавляем слушатель событий
					butFPDel[i].addActionListener(objCalcListener);
					butFPDel[i].addKeyListener(objCalcListener);
					butFPDel[i].setFocusable(true);
					
				}
			//добавляем подпанель1 в панель функц. клавиш
			funcKeysPanel.add(funcPanel1);
			
			
			//для второй подпанели функциональных клавиш берем кнопки арифметических операций заданные по заданию
			//для этой подпанели используем гридЛэйаут
			funcPanel2.setLayout(new GridLayout(butFPZnak.length,1,3,3));
			
			//---------в результате оптимизации объявление и определение кнопок перенесено в область полей класса----------
			
			//создаем кнопочки в цикле, задаем им ранее созданный шрифт и добавляем кнопки в свою подпанель, добавляем слушатель событий
			for (int i = 0; i < butFPZnak.length; i++) 
				{
					butFPZnak[i] = new JButton(strButFPZnak[i]);
					butFPZnak[i].setFont(digiFont);
					butFPZnak[i].setForeground(Color.blue);
					funcPanel2.add(butFPZnak[i]);
					//добавляем слушатель событий
					butFPZnak[i].addActionListener(objCalcListener);
					butFPZnak[i].addKeyListener(objCalcListener);
					butFPZnak[i].setFocusable(true);
				}
			//добавляем подпанель2 в панель функц. клавиш
			funcKeysPanel.add(funcPanel2);
			
			
			//для третьей подпанели функциональных клавиш берем кнопки квадратного корня, BS, CE, C
			//для этой подпанели используем гридЛэйаут
			funcPanel3.setLayout(new GridLayout(butFPBS.length,1,3,3));
			
			//---------в результате оптимизации объявление и определение кнопок перенесено в область полей класса----------
			
			//создаем кнопочки в цикле, задаем им ранее созданный шрифт и добавляем кнопки в свою подпанель
			for (int i = 0; i < butFPBS.length; i++) 
				{
					butFPBS[i] = new JButton(strButFPBS[i]);
					butFPBS[i].setFont(digiFont);
					butFPBS[i].setForeground(Color.blue);
					funcPanel3.add(butFPBS[i]);
					//добавляем слушатель событий
					butFPBS[i].addActionListener(objCalcListener);
					butFPBS[i].addKeyListener(objCalcListener);
					butFPBS[i].setFocusable(true);
				}
			//добавляем подпанель3 в панель функц. клавиш
			funcKeysPanel.add(funcPanel3);
			
			
			//для четвертой подпанели функциональных клавиш берем кнопки М+, М- и МС
			//для этой подпанели используем гридЛэйаут
			funcPanel4.setLayout(new GridLayout(butFPMem.length,1,3,3));
			
			//---------в результате оптимизации объявление и определение кнопок перенесено в область полей класса----------
			
			//создаем кнопочки в цикле, задаем им ранее созданный шрифт и добавляем кнопки в свою подпанель
			for (int i = 0; i < butFPMem.length; i++) 
				{
					butFPMem[i] = new JButton(strButFPMem[i]);
					butFPMem[i].setFont(digiFont);
					butFPMem[i].setForeground(Color.blue);
					funcPanel4.add(butFPMem[i]);
					//добавляем слушатель событий
					butFPMem[i].addActionListener(objCalcListener);
					butFPMem[i].addKeyListener(objCalcListener);
					butFPMem[i].setFocusable(true);
				}
			//добавляем подпанель4 в панель функциональных клавиш 
			funcKeysPanel.add(funcPanel4);
			//panKeys.add(funcPanel4, BorderLayout.EAST);
			
			//добавляем  две главных кнопочных панели в центральную панель окна
			panKeys.add(digiPanel, BorderLayout.CENTER);
			panKeys.add(funcKeysPanel, BorderLayout.SOUTH);
			
						
			add(panKeys, BorderLayout.CENTER);
			
			
			//включаем видимость окна
			setVisible(true);
			
			
			
			
			
		}
	
	
	
	
	public static void main(String[] args) {
	
		//создание объекта окна калькулятора
		new Calc("Мой калькулятор");	
	

	}




	
		
	

}
