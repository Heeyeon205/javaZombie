package zombie;

import java.util.Scanner;

public class Controller {
	Scanner sc;
	Hero hero;
	Zombie zombie;
	Boss boss;
	Utils utils;
	private boolean isGameRun;

	private void init() {
		hero = new Hero("히어로", 1, 30, 200, 2);	// name pos power hp
		zombie = new Zombie("좀비", 5, 10, 100);	// name pos power hp
		boss = new Boss("보스", 9, 20, 300, 100); // name pos power hp shield
		utils = Utils.getInstance();
		isGameRun = true;
	}

	private void printGameMenu() {
		mainMenu();
	}

	private void mainMenu() {
		System.out.println("========Main Menu========");
		while (isGameRun) {
			int sel = utils.getValue("[1] 게임 시작 [2] 게임 종료", 0, 3);
			if (sel == 1) {
				gameMenu();
			} else if (sel == 2) {
				System.out.println("게임 종료");
				isGameRun = false;
			}
		}
	}
	
	private void gameMenu() {
		System.out.println("========Game Menu========");
		int move = 1;
		while (isGameRun) {
		int sel = utils.getValue("[1] 앞으로 이동 [2] 게임 종료", 0, 3);
		if(sel == 1) {
			hero.setPos(move);
			System.out.printf("히어로 위치 %d번 방\n", hero.getPos());
			move++;
			if(hero.getPos() == 5) {
				System.out.printf("%s 발견!\n", zombie.getName());
				combatMenu();
			}
			if(hero.getPos() == 9) {
				System.out.printf("%s 등장!\n", boss.getName());
				bossBattleMenu();
			}
			if(hero.getPos() == 10) {
				System.out.println("던전 클리어!");
				break;
			}
		}else if(sel == 2) {
			System.out.println("게임 종료");
			isGameRun = false;
		}
		}
	}

	private void combatMenu() {
		System.out.println("========Battle Mode========");
		while(isGameRun) {
			int sel = utils.getValue("[1] 공격 [2] 포션 사용", 0, 3);
			if(sel == 1) {
				hero.attack(zombie);
				zombie.attack(hero);
			}else if(sel == 2) {
				hero.usePotion();
				zombie.attack(hero);
			}
			if(zombie.getHp() == 0) {
				System.out.println("좀비 전투 불능!");
				return;
			}
			if(hero.getHp() == 0) {
				System.out.println("영웅 사망!\n게임 종료!");
				isGameRun = false;
			}
		}
	}
	
	private void bossBattleMenu() {
		System.out.println("========Boss Battle========");
		while(isGameRun) {
			int sel = utils.getValue("[1] 공격 [2] 포션 사용", 0, 3);
			if(sel == 1) {
				hero.bossAttack(boss);
				boss.attack(hero);
			}else if(sel == 2) {
				hero.usePotion();
				boss.attack(hero);
			}
			if(boss.getHp() == 0) {
				System.out.println("보스 전투 불능!\n게임 승리!");
				isGameRun = false;
			}
			if(hero.getHp() == 0) {
				System.out.println("히어로 사망!\n게임 종료!");
				isGameRun = false;
			}
		}
	}

	public void run() {
		init();
		printGameMenu();
	}

}
