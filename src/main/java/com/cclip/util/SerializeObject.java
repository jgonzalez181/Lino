package com.cclip.util;

import com.cclip.model.person.CensusTaker;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class SerializeObject {

	public static String toJson(Object obj) {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.setMode(XStream.NO_REFERENCES);
		// xstream.alias("product", Continent.class);

		String s = xstream.toXML(obj);

//		JSONSerializer serializer = new JSONSerializer();
//		String s = serializer.serialize(obj);

		System.out.println("=========================== JSON ======================================");

		System.out.println(s);
		
		String r = SO.readFilePlainText("/home/java/json.json");

//		ScheduleCensus sc = (ScheduleCensus) xstream.fromXML(r);
		CensusTaker sc = (CensusTaker) xstream.fromXML(r);

		System.out.println(sc.getErased());

		System.out.println("======================================================================");

		
		return s;

		// xstream = new XStream();
		// xstream.setMode(XStream.NO_REFERENCES);
		// // xstream.alias("product", Continent.class);
		//
		//
	}

}
