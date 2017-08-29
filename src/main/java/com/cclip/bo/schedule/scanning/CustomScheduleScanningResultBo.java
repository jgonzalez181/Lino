package com.cclip.bo.schedule.scanning;

import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;

import com.cclip.model.schedule.scanning.ScheduleScanningResult;

/** BO Resultado de Barrido */
public class CustomScheduleScanningResultBo extends ScheduleScanningResultBo {

	public ScheduleScanningResult[] find1() throws Exception {

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("id", false) };

		MapperQueryArg[] argList = new MapperQueryArg[1];

		MapperQueryArg a = new MapperQueryArg();
		a.setEqualsTo("erased", false);
		argList[0] = a;

		return find(argList, orderList);

	}

}
