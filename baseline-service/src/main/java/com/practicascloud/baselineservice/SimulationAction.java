package com.practicascloud.baselineservice;

import java.util.ArrayList;

public class SimulationAction {
	private Simulation simulation;
	
	public SimulationAction(String[] inputs) {
		printPresentation();
		simulation = new Simulation();
		setInputValues(inputs);
		printEntrada();
		investmentSimulation();
		printSalida();
		printGoodBye();
	}
	
	private void printPresentation() {
		System.out.println();
		System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
		System.out.println("▌                                                     ▐ ");
		System.out.println("▌           SIMULACIÓN   DE   INVERSIÓN               ▐ ");
		System.out.println("▌                                                     ▐ ");
		System.out.println("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
	}
	
	private void printGoodBye() {
		System.out.println();
		System.out.println("◍◍◍◍◍◍◍◍◍◍◍◍◍◍◍◍     SIMULACIÓN   TERMINADA    ◍◍◍◍◍◍◍◍◍◍◍◍◍◍◍◍◍◍");
	}
	
	private void printEntrada() {
		System.out.println();                                                        
		System.out.println("=> Entradas:");
		System.out.println("  ──────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("  │ Inversión inicial ");
		System.out.print("│  Aportación anual ");
		System.out.print("│ % Incremento anual ");
		System.out.print("│ Años de inversión ");
		System.out.println("│ % Rendimiento │");
		System.out.println("  │────────────────────────────────────────────────────────────────────────────────────────────────│");
		System.out.print(String.format("  │ $%16s ",   String.format("%,.2f", simulation.getInversionInicial())));
		System.out.print(String.format("│ $%16s ",   String.format("%,.2f", simulation.getAportacionAnual())));
		System.out.print(String.format("│ %18s ",   simulation.getPctIncrementoAportacionAnual()+" %"));
		System.out.print(String.format("│ %17s ", simulation.getAgnosInversion()));
		System.out.println(String.format("│ %15s ",  simulation.getPctRendimientoInversion()+" % │"));
		System.out.println("  ──────────────────────────────────────────────────────────────────────────────────────────────────");
	}
	
	private void printSalida() {
		System.out.println();                                                        
		System.out.println("=> Salidas:");
		System.out.println("  - Ganancia por inversión: $ " + String.format("%,.2f",simulation.getGananciaInversion()));
		System.out.println("  - Monto Final: $ " + String.format("%,.2f",simulation.getMontoFinal()));
		System.out.println();
		System.out.println("  ──────────────────────────────────────────────────────────────────────────");
		System.out.print("  │  Año ");
		System.out.print("│  Saldo inicial  ");
		System.out.print("│   Aportación   ");
		System.out.print("│  Rendimiento  ");
		System.out.println("│  Saldo final │");
		for(EstadoAnual edoAnual: simulation.getEstadosAnuales()) {
			System.out.println("  │────────────────────────────────────────────────────────────────────────│");
			System.out.print(String.format("  │%4d  ", edoAnual.getAgno()));
			System.out.print(String.format("│ $%14s ",   String.format("%,.2f",  edoAnual.getSaldoInicial())));
			System.out.print(String.format("│ $%13s ",   String.format("%,.2f",  edoAnual.getAportacion())));
			System.out.print(String.format("│ $%12s ",   String.format("%,.2f",  edoAnual.getRendimiento())));
			System.out.println(String.format("│ $%13s ",   String.format("%,.2f",  edoAnual.getSaldoFinal())+" │"));
		}
		System.out.println("  ──────────────────────────────────────────────────────────────────────────");
	}
	
	private boolean setInputValues(String[] inputs) {
		simulation.setInversionInicial(Float.parseFloat(inputs[0]));
		simulation.setAportacionAnual(Float.parseFloat(inputs[1]));
		simulation.setPctIncrementoAportacionAnual(Integer.parseInt(inputs[2]));
		simulation.setAgnosInversion(Integer.parseInt(inputs[3]));
		simulation.setPctRendimientoInversion(Integer.parseInt(inputs[4]));
				
		return true;
	}
	
	private boolean investmentSimulation() {
		simulation.setEstadosAnuales(new ArrayList<EstadoAnual>());
		for(int i=0; i < simulation.getAgnosInversion(); i++) {
			EstadoAnual estadoAnual = new EstadoAnual();
			/** Setting Año */
			estadoAnual.setAgno(i+1);
			
			if(i==0) {
				/** Setting Saldo Inicial */
				estadoAnual.setSaldoInicial(simulation.getInversionInicial());
				/** Setting Aportación */
				estadoAnual.setAportacion(simulation.getAportacionAnual());
			}else {
				/** Setting Saldo Inicial */
				estadoAnual.setSaldoInicial(simulation.getEstadosAnuales().get(i-1).getSaldoFinal());
				/** Setting Aportación */
				
				Float aportacion = (simulation.getEstadosAnuales().get(i-1).getAportacion())*((float)1+((float) simulation.getPctIncrementoAportacionAnual()/(float)100));
				estadoAnual.setAportacion(aportacion);
			}
			
			/** Setting Rendimiento */
			estadoAnual.setRendimiento((estadoAnual.getSaldoInicial()+estadoAnual.getAportacion())*((float) simulation.getPctRendimientoInversion()/(float) 100));
			
			/** Setting Saldo final */
			estadoAnual.setSaldoFinal(estadoAnual.getSaldoInicial()+estadoAnual.getAportacion()+estadoAnual.getRendimiento());
			
			/**Adding estado Anual to the List*/
			simulation.getEstadosAnuales().add(estadoAnual);
			
			/** Setting Monto final */
			simulation.setMontoFinal(estadoAnual.getSaldoFinal());
		}
		
		/** Setting Ganancia por inversión */
		simulation.setGananciaInversion(simulation.getMontoFinal()-simulation.getInversionInicial()-(simulation.getAportacionAnual()*simulation.getAgnosInversion()));
			
		return true;
	}
}
