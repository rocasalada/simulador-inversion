package com.practicascloud.baselineservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoAnual {
	private int agno;
	private Float saldoInicial;
	private Float aportacion;
	private Float rendimiento;
	private Float saldoFinal;
}
