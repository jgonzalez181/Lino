package com.cclip.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.Timer;

/*** Muestra un cronometro en el formato hh:mm:ss ***/
public class JLabelTimer extends JLabel {
	
	private long tiempoBase;
	private long avance;
	private Date fecha;
	private Timer tiempo;

	/**
	 * En el constructor se inicia el timer para llamar al metodo actionPeroformed() cada segundo. Se formatea el tiempo y se actualiza el cronometro.
	 ***/
	public JLabelTimer() {
		this.tiempo = new Timer(1000, null);
		this.tiempoBase = 0;
		setText("00:00:00");
		this.tiempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String formato = new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis() - tiempoBase);
				setText(formato);
				avance++;
			}
		});
	}

	/** Se inicia el cronometro en 00:00:00 **/
	public void iniciar() {
		this.avance = 0;
		this.fecha = null;
		try {
			this.fecha = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("es", "MX")).parse("01/01/1970");
		} catch (ParseException pe) {
		}
		this.tiempoBase = System.currentTimeMillis() - this.fecha.getTime();
		if (!this.tiempo.isRunning())
			this.tiempo.start();
		else
			this.tiempo.restart();
	}

	/** Se detiene el cronometro hasta que se de reanudar() o iniciar() **/
	public void pausa() {
		if (this.tiempo.isRunning()) {
			this.tiempo.stop();
			this.tiempoBase += (this.avance * 1000); // Se actuliza tiempoBase
		}
	}

	/** Se reanuda el cronometro a partir del tiempo en que se detuvo **/
	public void reanudar() {
		if (!this.tiempo.isRunning())
			this.tiempo.restart();
	}
}