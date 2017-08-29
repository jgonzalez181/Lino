package com.cclip.util;

import java.sql.Timestamp;

import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.model.geo.cadastre.block.CadastreBlock;
import com.cclip.model.geo.cadastre.block.CadastreBlockCensus;
import com.cclip.model.person.Uf;
import com.cclip.model.person.UfCensus;

public class UtilCadastre {

	public static String formatCadastralCode(String code) {

		if (code == null) {
			return null;
		}

		if (code.trim().length() == 0) {
			return null;
		}

		code = code.trim();

		char[] ca = code.toCharArray();

		String c = "";

		for (int i = 0; i < ca.length; i++) {
			if (i == 2) {
				c += "-" + ca[i];
			} else if (i == 4) {
				c += "-" + ca[i];
			} else if (i == 7) {
				c += "-" + ca[i];
			} else if (i == 10) {
				c += "-" + ca[i];
			} else {
				c += ca[i];
			}
		}

		return c;
	}

	public static String formatCadastralCodeB(String code) {

		if (code == null) {
			return null;
		}

		if (code.trim().length() == 0) {
			return null;
		}

		code = code.trim();

		char[] ca = code.toCharArray();

		String c = "";

		for (int i = 0; i < ca.length; i++) {
			if (i == 2) {
				c += "_" + ca[i];
			} else if (i == 4) {
				c += "_" + ca[i];
			} else if (i == 7) {
				c += "_" + ca[i];
			} else {
				c += ca[i];
			}
		}

		return c;
	}

	public static CadastreCensus buildByCadastre(Cadastre c, boolean id) {
		CadastreCensus cs = new CadastreCensus();

		if (id) {
			cs.setId(c.getId());
			cs.setErased(c.getErased());
		} else {
			cs.setErased(false);
		}

		cs.setDateCreate(c.getDateCreate()); 
		cs.setDateDelete(c.getDateDelete()); 
		cs.setCadastralCode(c.getCadastralCode());
		cs.setM2(c.getM2());
		cs.setM2Covered(c.getM2Covered());
		cs.setCtaCli(c.getCtaCli());
		cs.setSubCtaCli(c.getSubCtaCli());
		cs.setDv(c.getDv());

		cs.setUserWaterService(c.getUserWaterService());
		cs.setUserWaterServiceDni(c.getUserWaterServiceDni());
		cs.setUserWaterServiceCuit(c.getUserWaterServiceCuit());
		cs.setWaterMeter(c.getWaterMeter());

		cs.setComment(c.getComment());

		cs.setInmLocality(c.getInmLocality());
		cs.setInmNeighbourhoodComment(c.getInmNeighbourhoodComment());
		cs.setInmStreet(c.getInmStreet());
		cs.setInmStreetNumber(c.getInmStreetNumber());
		cs.setInmStreetNumberEstimated(c.getInmStreetNumberEstimated());
		cs.setInmBuildingFloor(c.getInmBuildingFloor());
		cs.setInmBuildingRoom(c.getInmBuildingRoom());
		cs.setInmBuilding(c.getInmBuilding());
		cs.setInmPostalCode(c.getInmPostalCode());
		cs.setInmCommentAddress(c.getInmCommentAddress());
		cs.setInmLat(c.getInmLat());
		cs.setInmLng(c.getInmLng());

		cs.setUserPostalCountryCode(c.getUserPostalCountryCode());
		cs.setUserPostalCountry(c.getUserPostalCountry());
		cs.setUserPostalAdminAreaLevel1Code(c.getUserPostalAdminAreaLevel1Code());
		cs.setUserPostalAdminAreaLevel1(c.getUserPostalAdminAreaLevel1());
		cs.setUserPostalAdminAreaLevel2(c.getUserPostalAdminAreaLevel2());
		cs.setUserPostalLocality(c.getUserPostalLocality());
		cs.setUserPostalNeighbourhood(c.getUserPostalNeighbourhood());
		cs.setUserPostalStreet(c.getUserPostalStreet());
		cs.setUserPostalStreetNumber(c.getUserPostalStreetNumber());
		cs.setUserPostalBuildingFloor(c.getUserPostalBuildingFloor());
		cs.setUserPostalBuildingRoom(c.getUserPostalBuildingRoom());
		cs.setUserPostalBuilding(c.getUserPostalBuilding());
		cs.setUserPostalPostalCode(c.getUserPostalPostalCode());
		cs.setUserPostalCommentAddress(c.getUserPostalCommentAddress());
		cs.setUserPostalLat(c.getUserPostalLat());
		cs.setUserPostalLng(c.getUserPostalLng());
		cs.setUserPhone(c.getUserPhone());

		cs.setCodeReasonLow(c.getCodeReasonLow());
		cs.setReasonLow(c.getReasonLow());
		cs.setCantPh(c.getCantPh());
		cs.setDateScanning0(c.getDateScanning0());
		cs.setDateScanning1(c.getDateScanning1());
		cs.setDateScanning2(c.getDateScanning2());

		cs.setAuditDateCreate(new Timestamp(System.currentTimeMillis()));

		// /** auditUserCreate */
		// private String auditUserCreate = null;
		//
		// /** auditDateUpdate */
		// private Timestamp auditDateUpdate = null;
		//
		// /** auditUserUpdate */
		// private String auditUserUpdate = null;
		//
		// /** auditVersion */
		// private Long auditVersion = null;

		cs.setAuditVersionLabel(c.getAuditVersionLabel());
		
		// /** auditVersionCodeLabel */
		// private String auditVersionCodeLabel = null;
		//
		// /** auditVersionLabel */
		// private String auditVersionLabel = null;

		cs.setFact(c.getFact());

		cs.setM2CoveredShared(c.getM2CoveredShared());

		cs.setM2CoveredExpanded(c.getM2CoveredExpanded());

		cs.setM2Percent(c.getM2Percent());
		
		cs.setYearBuilding(c.getYearBuilding());

		cs.setUserIva(c.getUserIva());

		cs.setUserWaterSituation(c.getUserWaterSituation());

//		cs.setUfCensus(buildByUf(c.getUf(), id));

		cs.setCityArea(c.getCityArea());

		cs.setCadastreType(c.getCadastreType());

		cs.setCadastreSituation(c.getCadastreSituation());

		cs.setInmNeighbourhood(c.getInmNeighbourhood());

		cs.setCadastreSubDivisionType(c.getCadastreSubDivisionType());

		cs.setScheduleScanningResult0(c.getScheduleScanningResult0());
		cs.setScheduleScanningResult1(c.getScheduleScanningResult1());
		cs.setScheduleScanningResult2(c.getScheduleScanningResult2());
		
		cs.setCadastreActivityType(c.getCadastreActivityType());
		
		cs.setCadastreConstructiveType(c.getCadastreConstructiveType());

		if (c.getCadastreBlockList() != null) {
			for (CadastreBlock cadastreBlock : c.getCadastreBlockList()) {
//				cs.addCadastreBlockCensusList(buildByCadastreBlock(cadastreBlock, cs, id));
			}
		}

		return cs;

	}

	private static UfCensus buildByUf(Uf uf, boolean id) {
		
		if(uf == null){
			return null;
		}
		
		UfCensus ufc = new UfCensus();

		if (id) {
			ufc.setId(uf.getId());
			ufc.setErased(uf.getErased());
		} else {
			ufc.setErased(false);
		}

		ufc.setName(uf.getName());
		ufc.setDni(uf.getDni());
		ufc.setCuit(uf.getCuit());
		ufc.setPhone(uf.getPhone());
		ufc.setComment(uf.getComment());
		ufc.setCountryCode(uf.getCountryCode());
		ufc.setCountry(uf.getCountry());
		ufc.setAdminAreaLevel1Code(uf.getAdminAreaLevel1Code());
		ufc.setAdminAreaLevel1(uf.getAdminAreaLevel1());
		ufc.setAdminAreaLevel2(uf.getAdminAreaLevel2());
		ufc.setLocality(uf.getLocality());
		ufc.setStreet(uf.getStreet());
		ufc.setStreetNumber(uf.getStreetNumber());
		ufc.setBuildingRoom(uf.getBuildingRoom());
		ufc.setBuildingFloor(uf.getBuildingFloor());
		ufc.setBuilding(uf.getBuilding());
		ufc.setCommentAddress(uf.getCommentAddress());
		ufc.setLat(uf.getLat());
		ufc.setLng(uf.getLng());
		ufc.setAuditDateCreate(new Timestamp(System.currentTimeMillis()));
		ufc.setIva(uf.getIva());
		ufc.setNeighbourhood(uf.getNeighbourhood());

		return ufc;
	}

	private static Uf buildByUfCensus(UfCensus uf, boolean id) {
		Uf ufc = new Uf();

		if (id) {
			ufc.setId(uf.getId());
			ufc.setErased(uf.getErased());
		} else {
			ufc.setErased(false);
		}

		ufc.setName(uf.getName());
		ufc.setDni(uf.getDni());
		ufc.setCuit(uf.getCuit());
		ufc.setPhone(uf.getPhone());
		ufc.setComment(uf.getComment());
		ufc.setCountryCode(uf.getCountryCode());
		ufc.setCountry(uf.getCountry());
		ufc.setAdminAreaLevel1Code(uf.getAdminAreaLevel1Code());
		ufc.setAdminAreaLevel1(uf.getAdminAreaLevel1());
		ufc.setAdminAreaLevel2(uf.getAdminAreaLevel2());
		ufc.setLocality(uf.getLocality());
		ufc.setStreet(uf.getStreet());
		ufc.setStreetNumber(uf.getStreetNumber());
		ufc.setBuildingRoom(uf.getBuildingRoom());
		ufc.setBuildingFloor(uf.getBuildingFloor());
		ufc.setBuilding(uf.getBuilding());
		ufc.setCommentAddress(uf.getCommentAddress());
		ufc.setLat(uf.getLat());
		ufc.setLng(uf.getLng());
		ufc.setAuditDateCreate(new Timestamp(System.currentTimeMillis()));
		ufc.setIva(uf.getIva());
		ufc.setNeighbourhood(uf.getNeighbourhood());

		return ufc;
	}

	private static CadastreBlockCensus buildByCadastreBlock(CadastreBlock c, CadastreCensus cs, boolean id) {
		CadastreBlockCensus cb = new CadastreBlockCensus();

		if (id) {
			cb.setId(c.getId());
			cb.setErased(c.getErased());
		} else {
			cb.setErased(false);
		}

		cb.setYearBuilding(c.getYearBuilding());
		cb.setMonthBuilding(c.getMonthBuilding());
		cb.setM2Covered(c.getM2Covered());
		cb.setDemolition(c.getDemolition());
		cb.setComment(c.getComment());
		cb.setFacade(c.getFacade());
		cb.setRoofStructure(c.getRoofStructure());
		cb.setHomes(c.getHomes());
		cb.setInteriorWalls(c.getInteriorWalls());
		cb.setCeiling(c.getCeiling());
		cb.setKitchen(c.getKitchen());
		cb.setToilets(c.getToilets());
		cb.setEntranceHall(c.getEntranceHall());
		cb.setFacilities(c.getFacilities());
		cb.setCarpentry(c.getCarpentry());
		cb.setCoverStructure(c.getCoverStructure());
		cb.setOrnamentation(c.getOrnamentation());
		cb.setStainedGlassLighting(c.getStainedGlassLighting());
		cb.setBuffetDining(c.getBuffetDining());
		cb.setPoints(c.getPoints());
		cb.setApc(c.getApc());
		cb.setRate(c.getRate());
		cb.setApcDesc(c.getApcDesc());

		cb.setAuditDateCreate(new Timestamp(System.currentTimeMillis()));

		// /** auditUserCreate */
		// private String auditUserCreate = null;
		//
		// /** auditDateUpdate */
		// private Timestamp auditDateUpdate = null;
		//
		// /** auditUserUpdate */
		// private String auditUserUpdate = null;
		//
		// /** auditVersion */
		// private Long auditVersion = null;
		//
		// /** auditVersionCodeLabel */
		// private String auditVersionCodeLabel = null;
		//
		// /** auditVersionLabel */
		// private String auditVersionLabel = null;

		cb.setCadastreActivityType(c.getCadastreActivityType());
//		cb.setCadastreCensus(cs);
		cb.setCadastreConstructiveType(c.getCadastreConstructiveType());
		cb.setCadastreDestinationType(c.getCadastreDestinationType());

		return cb;
	}

	public static Cadastre buildByCadastreCensus(CadastreCensus c, boolean id) {
		Cadastre cs = new Cadastre();

		if (id) {
			cs.setId(c.getId());
			cs.setErased(c.getErased());
		} else {
			cs.setErased(false);
		}

		cs.setDateCreate(c.getDateCreate()); 
		cs.setDateDelete(c.getDateDelete()); 
		cs.setCadastralCode(c.getCadastralCode());
		cs.setM2(c.getM2());
		cs.setM2Covered(c.getM2Covered());
		cs.setCtaCli(c.getCtaCli());
		cs.setSubCtaCli(c.getSubCtaCli());
		cs.setDv(c.getDv());

		cs.setUserWaterService(c.getUserWaterService());
		cs.setUserWaterServiceDni(c.getUserWaterServiceDni());
		cs.setUserWaterServiceCuit(c.getUserWaterServiceCuit());
		cs.setWaterMeter(c.getWaterMeter());

		cs.setComment(c.getComment());

		cs.setInmLocality(c.getInmLocality());
		cs.setInmNeighbourhoodComment(c.getInmNeighbourhoodComment());
		cs.setInmStreet(c.getInmStreet());
		cs.setInmStreetNumber(c.getInmStreetNumber());
		cs.setInmStreetNumberEstimated(c.getInmStreetNumberEstimated());
		cs.setInmBuildingFloor(c.getInmBuildingFloor());
		cs.setInmBuildingRoom(c.getInmBuildingRoom());
		cs.setInmBuilding(c.getInmBuilding());
		cs.setInmPostalCode(c.getInmPostalCode());
		cs.setInmCommentAddress(c.getInmCommentAddress());
		cs.setInmLat(c.getInmLat());
		cs.setInmLng(c.getInmLng());

		cs.setUserPostalCountryCode(c.getUserPostalCountryCode());
		cs.setUserPostalCountry(c.getUserPostalCountry());
		cs.setUserPostalAdminAreaLevel1Code(c.getUserPostalAdminAreaLevel1Code());
		cs.setUserPostalAdminAreaLevel1(c.getUserPostalAdminAreaLevel1());
		cs.setUserPostalAdminAreaLevel2(c.getUserPostalAdminAreaLevel2());
		cs.setUserPostalLocality(c.getUserPostalLocality());
		cs.setUserPostalNeighbourhood(c.getUserPostalNeighbourhood());
		cs.setUserPostalStreet(c.getUserPostalStreet());
		cs.setUserPostalStreetNumber(c.getUserPostalStreetNumber());
		cs.setUserPostalBuildingFloor(c.getUserPostalBuildingFloor());
		cs.setUserPostalBuildingRoom(c.getUserPostalBuildingRoom());
		cs.setUserPostalBuilding(c.getUserPostalBuilding());
		cs.setUserPostalPostalCode(c.getUserPostalPostalCode());
		cs.setUserPostalCommentAddress(c.getUserPostalCommentAddress());
		cs.setUserPostalLat(c.getUserPostalLat());
		cs.setUserPostalLng(c.getUserPostalLng());
		cs.setUserPhone(c.getUserPhone());

		cs.setCodeReasonLow(c.getCodeReasonLow());
		cs.setReasonLow(c.getReasonLow());
		cs.setCantPh(c.getCantPh());
		cs.setDateScanning0(c.getDateScanning0());
		cs.setDateScanning1(c.getDateScanning1());
		cs.setDateScanning2(c.getDateScanning2());

		cs.setAuditDateCreate(new Timestamp(System.currentTimeMillis()));

		// /** auditUserCreate */
		// private String auditUserCreate = null;
		//
		// /** auditDateUpdate */
		// private Timestamp auditDateUpdate = null;
		//
		// /** auditUserUpdate */
		// private String auditUserUpdate = null;
		//
		// /** auditVersion */
		// private Long auditVersion = null;
		
		cs.setAuditVersionLabel(c.getAuditVersionLabel());

		// /** auditVersionCodeLabel */
		// private String auditVersionCodeLabel = null;
		//
		// /** auditVersionLabel */
		// private String auditVersionLabel = null;

		cs.setFact(c.getFact());

		cs.setM2CoveredShared(c.getM2CoveredShared());

		cs.setM2CoveredExpanded(c.getM2CoveredExpanded());

		cs.setM2Percent(c.getM2Percent());
		
		cs.setYearBuilding(c.getYearBuilding());

		cs.setUserIva(c.getUserIva());

		cs.setUserWaterSituation(c.getUserWaterSituation());

//		if(c.getUfCensus() != null){
//			cs.setUf(buildByUfCensus(c.getUfCensus(), id));
//		}
		

		cs.setCityArea(c.getCityArea());

		cs.setCadastreType(c.getCadastreType());

		cs.setCadastreSituation(c.getCadastreSituation());

		cs.setInmNeighbourhood(c.getInmNeighbourhood());

		cs.setCadastreSubDivisionType(c.getCadastreSubDivisionType());

		cs.setScheduleScanningResult0(c.getScheduleScanningResult0());
		cs.setScheduleScanningResult1(c.getScheduleScanningResult1());
		cs.setScheduleScanningResult2(c.getScheduleScanningResult2());
		
		cs.setCadastreActivityType(c.getCadastreActivityType());
		
		cs.setCadastreConstructiveType(c.getCadastreConstructiveType());

//		if (c.getCadastreBlockCensusList() != null) {
//			for (CadastreBlockCensus cadastreBlock : c.getCadastreBlockCensusList()) {
//				cs.addCadastreBlockList(buildByCadastreBlockCensus(cadastreBlock, cs, id));
//			}
//		}

		return cs;

	}

	private static CadastreBlock buildByCadastreBlockCensus(CadastreBlockCensus c, Cadastre cs, boolean id) {
		CadastreBlock cb = new CadastreBlock();

		if (id) {
			cb.setId(c.getId());
			cb.setErased(c.getErased());
		} else {
			cb.setErased(false);
		}

		cb.setYearBuilding(c.getYearBuilding());
		cb.setMonthBuilding(c.getMonthBuilding());
		cb.setM2Covered(c.getM2Covered());
		cb.setDemolition(c.getDemolition());
		cb.setComment(c.getComment());
		cb.setFacade(c.getFacade());
		cb.setRoofStructure(c.getRoofStructure());
		cb.setHomes(c.getHomes());
		cb.setInteriorWalls(c.getInteriorWalls());
		cb.setCeiling(c.getCeiling());
		cb.setKitchen(c.getKitchen());
		cb.setToilets(c.getToilets());
		cb.setEntranceHall(c.getEntranceHall());
		cb.setFacilities(c.getFacilities());
		cb.setCarpentry(c.getCarpentry());
		cb.setCoverStructure(c.getCoverStructure());
		cb.setOrnamentation(c.getOrnamentation());
		cb.setStainedGlassLighting(c.getStainedGlassLighting());
		cb.setBuffetDining(c.getBuffetDining());
		cb.setPoints(c.getPoints());
		cb.setApc(c.getApc());
		cb.setRate(c.getRate());
		cb.setApcDesc(c.getApcDesc());

		cb.setAuditDateCreate(new Timestamp(System.currentTimeMillis()));

		// /** auditUserCreate */
		// private String auditUserCreate = null;
		//
		// /** auditDateUpdate */
		// private Timestamp auditDateUpdate = null;
		//
		// /** auditUserUpdate */
		// private String auditUserUpdate = null;
		//
		// /** auditVersion */
		// private Long auditVersion = null;
		//
		// /** auditVersionCodeLabel */
		// private String auditVersionCodeLabel = null;
		//
		// /** auditVersionLabel */
		// private String auditVersionLabel = null;

		cb.setCadastreActivityType(c.getCadastreActivityType());
		cb.setCadastre(cs);
		cb.setCadastreConstructiveType(c.getCadastreConstructiveType());
		cb.setCadastreDestinationType(c.getCadastreDestinationType());

		return cb;
	}

}
