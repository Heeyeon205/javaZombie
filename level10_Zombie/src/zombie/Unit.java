package zombie;

import java.util.Random;
//유닛은 전부 이동가능하며 pos는 현재 위치를 나타낸다 
//맵은 ~10까지 존재하며 한칸씩 이동 가능하다
//유닛들은 전부 attack 메서드를 구현한다 
abstract public class Unit {
	private String name;
	private final int MAX_HP;
	private int hp;
	private int pos;
	private int power;
	private int damage;
	private Utils utils;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getMAX_HP() {
		return MAX_HP;
	}
	
	public Unit(String name, int pos, int power, int hp) {
		this.name = name;
		this.hp = hp;
		this.pos = pos;
		this.power = power;
		MAX_HP = hp;
		utils = Utils.getInstance();
	}
	
	@Override
	public String toString() {
		return String.format("%s의 남은 체력: %d/%d\n", name, hp, MAX_HP);
	}

	protected void attack(Unit unit) {
		this.setDamage(utils.getRandomValue(power)+1);
		unit.setHp(unit.getHp()-damage);
		if(unit.getHp() <= 0) {
			unit.setHp(0);
		}
		System.out.printf("%s가 %s를 %d의 데미지로 공격했습니다!\n %s\n", name, unit.getName(), damage, unit);
	}
}
