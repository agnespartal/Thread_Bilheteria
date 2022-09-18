package view;

import java.util.concurrent.Semaphore;

import controller.BilheteriaController;

public class Principal {

	public static void main(String[] args) {
		
		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		
		for (int i = 0; i < 300; i++) {
			Thread bil = new BilheteriaController(semaforo);
			bil.start();
		}
		
	}

}
