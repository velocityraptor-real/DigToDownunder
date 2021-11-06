package net.velocityworks.dtdu.objects.base;

public class Living extends Generic {
	protected int x;
	protected int y;
	public Living() {
		super();
	}
	public Living(final String name, final char icon) {
		super(name, icon);
	}
	@Override
	protected void attributes() {
		this.icon = 'l';
		this.name = "Living";
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public void setLocation(final char icon, final int x, final int y) {
		super.setLocation(icon, x, y);
		this.x = x;
		this.y = y;
	}
}
