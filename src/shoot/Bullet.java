package shoot;

public class Bullet extends Flyer {
    
	private int speed=3;//子弹上升的速度为3
	
	/*
	 * 子弹类的带参构造方法
	 * 因为子弹对象创造的位置要根据英雄飞机的位置决定
	 * 所以子弹对名的x和y要从外界传入
	 * @param x英雄机指定子弹创造位置的x坐标
	 * @param y英雄飞机指定创造位置的y坐标
	 * @see shoot.Flyer#step()
	 */
	public Bullet(int x,int y) {
		image=ShootGame.bullet;
		width=image.getWidth();
		height=image.getHeight();
		this.x=x;
		this.y=y;
	}
	@Override
	public void step() {
		// TODO Auto-generated method stub
		//子弹每次向上移动一个speed长度
        y-=speed;
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		//子弹的y坐标+子弹的高度<0,越界
		return (y+height)<0;
	}

}
