package com.practicascloud.baselineservice;


import java.util.ArrayList;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Simulation {
	/*
	 * Input Data
	 */
	
	@Valid
	@NotBlank(message = "[Arg 01] - La inversión inicial es un campo requerido")
	@Min(value = 1000, message = "[Arg 01] - La inversión Inicial mínima son $1'000.00 MXP (un mil pesos mexicanos)")
	@NumberFormat(style = Style.CURRENCY)
	private float	inversionInicial;
	
	@Min(value = 0, message = "[Arg 02] - La aportación anual debe ser mayor o igual a cero '0'")
	@NumberFormat(style = Style.CURRENCY)
	private float	aportacionAnual;
	
	@Min(value = 0, message = "[Arg 03] - El porcentaje de incremento a la aportación anual debe ser mayor o igual a cero '0'")
	@NumberFormat(style = Style.PERCENT)
	private int	pctIncrementoAportacionAnual;
	
	@NotBlank(message = "[Arg 04] - La duración en años de inversión es un campo requerido")
	@Min(value = 1, message = "[Arg 04] - La duración en años de inversión debe ser mayor a cero '0'")
	@NumberFormat(style = Style.NUMBER)
	private int 	agnosInversion;
	
	@NotBlank(message = "[Arg 05] - El porcentaje de rendimiento de la inversión ficticia es un campo requerido")
	@Min(value = 1, message = "[Arg 05] - El porcentaje de rendimiento de la inversión ficticia mínimo debe ser maor a cero '0'")
	@NumberFormat(style = Style.PERCENT)
	private int	pctRendimientoInversion;
	
	/*
	 * Output Data
	 */
	private ArrayList<EstadoAnual> estadosAnuales;
	
	private Float montoFinal;
	
	private Float gananciaInversion;
}