package controller;

import java.util.concurrent.Semaphore;

public class BilheteriaController extends Thread {

	private static int contador = 100;
	private int ingresso;
	private Semaphore semaforo;
	
	
	public BilheteriaController(Semaphore semaforo) {
		super();
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		try {
			login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void login() throws Exception {
		int tempo = (int)((Math.random()* 1501)+ 500);
		if(tempo > 1000) {
			sleep(tempo);
			System.err.println("Timeout");
			System.out.println("#"+getId()+ " ==>  Não será possível concluir a compra");
			return;
		}else {
			sleep(tempo);
		}
		compra();
	}

	private void compra() throws Exception{
		int tempo = (int)((Math.random()*2001)+ 1000);
		ingresso = (int)((Math.random()*4)+ 1);
		
		if(tempo > 2500) {
			sleep(tempo);
			System.err.println("Timeout");
			System.out.println("#"+getId()+ " ==>  Não será possível concluir a compra");
			return;
		}else {
			sleep(tempo);
		}
		validacao();
	}

	private void validacao() {
		
		try {
			semaforo.acquire();
			if (ingresso > contador) {
				System.err.println("Indisponibilidade de ingressos");
				System.out.println("#"+getId()+ " ==>  Não será possível concluir a compra");
				return;
			} else {
				System.out.println("#"+getId()+ " ===> irá comprar: "+ingresso);
				System.out.println("Quantidade de ingressos disponiveis: "+contador);
			}
			contador -= ingresso;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
		
		
	}

}
