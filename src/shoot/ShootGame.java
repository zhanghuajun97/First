
package shoot;

 

import java.awt.Font;

import java.awt.Graphics;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

import java.io.IOException;

import java.util.Arrays;

import java.util.Random;

import java.util.Timer;

import java.util.TimerTask;

 

import javax.imageio.ImageIO;

import javax.swing.JFrame;

import javax.swing.JPanel;

 

public class ShootGame extends JPanel {

	

	private static final long serialVersionUID = 1L;

	

	//����ͼƬ�Ĵ�С320*568

	public static final int WIDTH = 320;

	public static final int HEIGHT = 568;

	//��Ϸ����̶���С336*607

	public static final int FRAME_WIDTH = 336;

	public static final int FRAME_HEIGHT = 607;

	

	/* 

	 * ��Ϸ������һ�����Ǵ�Ӳ�̼�������Ҫ�õ���ͼƬ���ڴ浱��

	 * ���ҽ�������ʱ����һ�Ρ�����̬��

	 * �����ڳ����е�����ͼƬ�����ᷴ��ʹ�ã�������һ�ݡ�����̬����

	 * ���棬Ϊÿ��ͼƬ����һ����̬������Ȼ���ھ�̬��Ӽ���ÿ��ͼƬ

	 */

	public static BufferedImage background; //����ͼƬ

	public static BufferedImage start; //��ʼͼƬ

	public static BufferedImage airplane; //�л�ͼƬ

	public static BufferedImage bigplane; //��ɻ�

	public static BufferedImage hero0; //Ӣ�ۻ�״̬0

	public static BufferedImage hero1; //Ӣ�ۻ�״̬1

	public static BufferedImage bullet; //�ӵ�

	public static BufferedImage pause; //��ͣͼƬ

	public static BufferedImage gameover; //��Ϸ����

	

	//��̬�飬������ص�������ʱִ��һ�Σ�ר�ż��ؾ�̬��Դ

	static{

		/*

		 * java��Ӳ���м���ͼƬ���ڴ��У�

		 * ImageIO.read������ר�Ŵ�Ӳ���м���ͼƬ�ľ�̬����

		 * ����ʵ������ֱ�ӵ���

		 * ShootGame.class:��õ�ǰ��ļ���������·��

		 * ShootGame.class.getRerource("�ļ���"); �ӵ�ǰ������·������ָ���ļ���������

		 */

		try {
			//System.out.println(ShootGame.class.getResource("background.png"));

			background = ImageIO.read(ShootGame.class.getResource("background.png."));

			airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));

			bigplane = ImageIO.read(ShootGame.class.getResource("bigplane.png"));

			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));

			start = ImageIO.read(ShootGame.class.getResource("start.png"));

			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));

			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));

			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));

			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	

	/*

	 * Ϊ��Ϸ�еĽ�ɫ�������ݽṹ��������

	 * 1��Ӣ�ۻ�����

	 * 1���洢���е��ˣ��л��ʹ�ɻ����Ķ�������

	 * 1���洢�����ӵ��Ķ�������

	 */

	public Hero hero = new Hero();

	public Flyer[] flyers = {}; //�洢���е��˶��������

	public Bullet[] bullets = {}; //�洢�����ӵ����������

	

	//������Ϸ״̬����ǰ״̬������Ĭ��Ϊ��ʼ״̬

	private int state = START;

	//������Ϸ״̬�ı�ѡ�����

	public static final int START = 0;

	public static final int RUNNING = 1;

	public static final int PAUSE = 2;

	public static final int GAME_OVER = 3;

	

 

	public static void main(String[] args) {

		

		/*

		 * java�л��ƴ��壺JFrame���󡪡�����

		 * Ҫ���ڴ����л������ݣ�����ҪǶ�뱳����塪��JPanel

		 */

		JFrame frame = new JFrame("ShootGame");

		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);//(336, 607);

		frame.setAlwaysOnTop(true); //���ô����ö�

		//���ô���ر�ͬʱ���˳�����

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null); //���ô����λ�ã�null��ʾ����

		

		/*�ڴ�����Ƕ�뱳�������󡪡�JPanel*/

		ShootGame game = new ShootGame(); //��������������

		frame.add(game); //������������Ƕ�뵽���������

		/*����Ĭ�ϲ��ɼ����������setVisible����������ʾ����*/

		frame.setVisible(true); //�Զ����ô����paint����

		game.action();

	}

	

	/**

	 * ��Ϸ����ʱҪ������

	 */

	public void action(){

		

		/*��Ϸ��ʼʱ��Ҫ��������¼��ļ���*/

		//step1: ����MouseAdapter�����ڲ��ࡪ���¼�����Ӧ����

		MouseAdapter l = new MouseAdapter(){

			//step2: ��дϣ��������¼���������ƶ�

			@Override

			public void mouseMoved(MouseEvent e) {

				//ֻ����RUNNING״̬��Ӣ�ۻ��Ÿ�������ƶ�

				if(state == RUNNING){

					//step3: ��������λ��

					int x = e.getX();

					int y = e.getY();

					//step4: �����λ�ô���Ӣ�ۻ���move����

					hero.move(x, y);

				}

			}

 

			@Override

			public void mouseClicked(MouseEvent e) {

				if(state == START || state == PAUSE){ //START����PAUSE״̬�������Ż�ĸ�ΪRUNNING״̬

					state = RUNNING;

				}else if(state == RUNNING){ //��Ϸ�����ͣ

					state = PAUSE;

				}else if(state == GAME_OVER){ //��Ϸ�����󵥻�����Ϸ��ʼ��

					state = START;

					//��GAME_OVER��START��Ҫ���³�ʼ����Ϸ����

					flyers = new Flyer[0];

					bullets = new Bullet[0];

					hero = new Hero();

				}

			}

 

 

			@Override

			public void mouseExited(MouseEvent e) {

				if(state == RUNNING){

					//���ڴ���RUNNING״̬�£�����Ƴ�����ͣ

					state = PAUSE;

				}

			}

			

			@Override

			public void mouseEntered(MouseEvent e) {

				if(state == PAUSE){

					state = RUNNING;

				}

			}

 

		}; //�����ڲ���Ҫ�ԷֺŽ�β

		/*step5: Ҫ��Ӧ����¼������뽫����¼����ӵ�����ļ�������*/

		this.addMouseMotionListener(l); //֧�������ƶ��¼�����֧����굥���¼�

		this.addMouseListener(l);; //֧����굥���¼�

		

		//step1: ������ʱ��

		Timer timer = new Timer();

		//step2: ���ö�ʱ�������schedule���������ƻ�

		//       ��һ��������TimerTask���͵������ڲ���

		//               ������дrun�����������ġ���Ҫ��ʲô��

		timer.schedule(new TimerTask(){

			//���ȶ���һ����ʱ������index����¼run�������еĴ���

			private int runTimes = 0;

 

			@Override

			public void run() {

				//����repaint���������๦��ֻ��RUNNING״̬��ִ��

				if(state == RUNNING){

					//ÿִ��һ��run������runTimes��+1

					runTimes++;

					

					//ÿ500��������һ�ε���

					if(runTimes % 50 == 0){

						nextOne(); //�Զ�����������˶���

					}

					//����ÿһ�����󣬵��ö����step�������ƶ�һ�ζ����λ��

					for(int i = 0;i < flyers.length;i++){

						flyers[i].step();

					}

					

					//ÿ300��������һ���ӵ�

					if(runTimes % 30 == 0){

						shoot(); //����һ���ӵ�

					}

					//�����ӵ������ÿһ�������ƶ�λ��

					for(int i = 0;i < bullets.length;i++){

						bullets[i].step();

					}

					

					//Ӣ�ۻ�����Ч��

					hero.step();

					

					//�����ӵ��͵��˵���ײ���

					boom();

					

					//Ӣ�ۻ���ײ���

					hit();

					

					//����Խ����

					outOfBounds();

				}	

				/*ǿ����ֻҪ���淢���仯���������repaint�������»��ƽ���*/

				repaint();

			}

			

		}, 10,10); //����ÿ��10����仯һ��

		

	}

 

 

	@Override

	public void paint(Graphics g) {

		//step1: ���Ʊ���ͼƬ

		g.drawImage(background, 0, 0, null);

		//step2: ����Ӣ�ۻ�

		paintHero(g);

		//step3: �������Ƶ�������

		paintFlyers(g);

		//step4: ���������ӵ�����

		paintBullets(g);

		//���Ʒ���������ֵ

		paintScore_Life(g);

		

		//������Ϸ״̬���Ʋ�ͬͼƬ

		if(state == START){

			g.drawImage(start, 0, 0, null);

		}else if(state == PAUSE){

			g.drawImage(pause, 0, 0, null);

		}else if(state == GAME_OVER){

			g.drawImage(gameover, 0, 0, null);

		}

		

	}

	

	/**

	 * ����Ӣ�ۻ�����ķ���

	 * @param g ����

	 */

	public void paintHero(Graphics g){

		g.drawImage(hero.image, hero.x, hero.y, null);

	}

	

	/**

	 * �����������飬�����������е��˵ķ���

	 * @param g 

	 */

	public void paintFlyers(Graphics g){

		for(int i = 0;i < flyers.length;i++){

			g.drawImage(flyers[i].image, flyers[i].x, flyers[i].y, null);

		}

	}

	

	/**

	 * �����ӵ����飬�������������ӵ��ķ���

	 * @param g

	 */

	public void paintBullets(Graphics g){

		for(int i = 0;i < bullets.length;i++){

			g.drawImage(bullets[i].image, bullets[i].x, bullets[i].y, null);

		}

	}

	

	/**

	 * �������1�����˶���

	 * ÿ����һ���µ��ˣ� flyers�����Ҫ����1

	 * Ȼ���µ��˷����������һ��Ԫ��

	 */

	public void nextOne(){

		Random r = new Random();

		Flyer f = null;

		if(r.nextInt(20) == 0){ //ֻ�������ȡ0ʱ�Ŵ�����ɻ�

			f = new BigPlane();

		}else{ //����ȫ�����ɵл�

			f = new Airplane();

		}

		//��flyers��������1

		flyers = Arrays.copyOf(flyers, flyers.length + 1);

		//���µ��˷�������ĩβ

		flyers[flyers.length - 1] = f;

	}

	

	/**

	 * ���Ӣ�ۻ���������ӵ�����

	 * ���µ��ӵ����󱣴浽�ӵ������У�ͳһ����

	 */

	public void shoot(){

		Bullet[] newBullets = hero.shoot(); //���Ӣ�ۻ����ص����ӵ�����

		//���ݷ������ӵ��������������ӵ�����

				bullets = Arrays.copyOf(bullets, bullets.length + newBullets.length);

				//��newBullets�����п�������Ԫ�ص�bullets����ĩβ

				System.arraycopy(newBullets, 0, bullets, bullets.length - newBullets.length, newBullets.length);


		

	}

	

	/**

	 * �����ӵ�����͵������飬������ײ���

	 * һ��������ײ���ӵ��͵��˶�����һ��

	 */

	public void boom(){

		for(int i = 0;i < bullets.length;i++){

			for(int j = 0;j < flyers.length;j++){

				if(Flyer.boom(bullets[i], flyers[j])){

					//ΪӢ�ۻ���÷����ͽ���

					hero.getScore_Award(flyers[j]);

					//�ӵ���������ɾ�������еĵл�

					//step1�� ʹ�õ����������һ��Ԫ���滻�����еĵл�

					flyers[j] = flyers[flyers.length - 1];

					//step2: ѹ������

					flyers = Arrays.copyOf(flyers, flyers.length - 1);

					//���ӵ�������ɾ�����ел����ӵ�

					bullets[i] = bullets[bullets.length - 1];

					bullets = Arrays.copyOf(bullets, bullets.length -1);

					i--; //�ڷ���һ����ײ���ӵ���Ҫ��һ��Ԫ�أ����¼�⵱ǰλ��

					break; //ֻҪ������ײ���˳���ǰ���������ѭ��

				}

			}

		}

	}

	

	/**

	 * ���Ʒ���������ֵ�ķ���

	 * @param g

	 */

	public void paintScore_Life(Graphics g){

		int x = 10; //���������Ͻǵ�x����

		int y = 15; //���������Ͻǵ�y����

		Font font = new Font(Font.SANS_SERIF,Font.BOLD,14);

		g.setFont(font); //��������Ļ��ʶ���

		//���Ƶ�һ��:����

		g.drawString("SCORE: " + hero.getScore(), x, y);

		//���Ƶڶ��У�����ֵ��y��������20����λ

		y += 20;

		g.drawString("LIFE: " + hero.getLife(), x, y);

	}

	

	/**

	 * ������з������Ƿ�Խ��

	 */

	public void outOfBounds(){

		//������е����Ƿ�Խ��

		Flyer[] Flives = new Flyer[flyers.length];

		//�����������飬�����ĵ��˶���浽��������

		//����Flives����ļ�����index: 1.��ʾ��һ���������λ��

		//						  2.ͳ��Flives��һ���ж���Ԫ��

		int index = 0;

		for(int i = 0;i < flyers.length;i++){

			if(!flyers[i].outOfBounds()){ //û��Խ��Ķ���

				Flives[index] = flyers[i];

				index++;

			} //����������

			//index�Ǵ�����ĸ���

			//Flives�������Ǵ��Ķ��󣬸���Ϊindex

			//��Flives����ѹ��Ϊindex��С

			//ѹ����������� Ӧ�滻��flyers����

		}

		flyers = Arrays.copyOf(Flives, index);

		

		//��������ӵ��Ƿ�Խ��

		Bullet[] Blives = new Bullet[bullets.length];

		index = 0;

		for(int i = 0;i < bullets.length;i++){

			if(!bullets[i].outOfBounds()){

				Blives[index] = bullets[i];

				index++;

			}

		}

		bullets = Arrays.copyOf(Blives, index);

	}

	

	/**

	 * �����������飬�ж�Ӣ�ۻ���ÿ�������Ƿ���ײ

	 */

	public void hit(){

		Flyer[] lives = new Flyer[flyers.length];

		//��¼���ĵ���

		int index = 0;

		for(int i = 0;i < flyers.length;i++){

			if(!hero.hit(flyers[i])){

				lives[index] = flyers[i];

				index++;

			}

		}

		if(hero.getLife() <= 0){ //���Ӣ�ۻ�����ֵС�ڵ���0����Ϸ���� 

			state = GAME_OVER;

		}

		//ѹ���������飬���滻����

		flyers = Arrays.copyOf(lives, index);

		

	}

}