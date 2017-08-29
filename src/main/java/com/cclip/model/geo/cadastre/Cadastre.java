package com.cclip.model.geo.cadastre;

import com.cclip.model.person.Iva;
import com.cclip.model.person.Uf;
import com.cclip.model.geo.CityArea;
import com.cclip.model.geo.Neighbourhood;
import com.cclip.model.geo.cadastre.subdivision.CadastreSubDivisionType;
import com.cclip.model.schedule.scanning.ScheduleScanningResult;
import com.cclip.model.geo.cadastre.block.CadastreConstructiveType;
import com.cclip.model.geo.cadastre.block.CadastreBlock;
import com.cclip.model.schedule.scanning.ScheduleScanningItem;
import com.cclip.model.schedule.census.ScheduleCensusItem;

import java.sql.Timestamp;
import java.sql.Date;

/** Catastro de Parcela de Tierra */
public class Cadastre {

	/** id */
	private String id = null;

	/** erased */
	private Boolean erased = false;

	/** Fecha de Alta */
	private Timestamp dateCreate = null;

	/** Fecha de Baja */
	private Timestamp dateDelete = null;

	/** Código Catastral */
	private String cadastralCode = null;

	/** Metros Cuadrados */
	private Double m2 = null;

	/** Metros Cuadrados Cubiertos */
	private Double m2Covered = null;

	/** Cuenta Cliente */
	private String ctaCli = null;

	/** Sub Cuenta Cliente */
	private String subCtaCli = null;

	/** Dígito Verificador */
	private String dv = null;

	/** Usuario del Servicio de Agua */
	private String userWaterService = null;

	/** DNI Titular */
	private String userWaterServiceDni = null;

	/** CUIL/CUIT Titular */
	private String userWaterServiceCuit = null;

	/** Medidor */
	private Boolean waterMeter = null;

	/** Comentario */
	private String comment = null;

	/** Localidad */
	private String inmLocality = null;

	/** Vecindario */
	private String inmNeighbourhoodComment = null;

	/** Calle */
	private String inmStreet = null;

	/** Número de Calle */
	private String inmStreetNumber = null;

	/** Número de Calle Estimado */
	private Boolean inmStreetNumberEstimated = null;

	/** Planta del Edificio */
	private String inmBuildingFloor = null;

	/** Departamento de Edificio */
	private String inmBuildingRoom = null;

	/** Edificio */
	private String inmBuilding = null;

	/** Código Postal */
	private String inmPostalCode = null;

	/** Comentario del Domicilio */
	private String inmCommentAddress = null;

	/** Latitud */
	private Double inmLat = null;

	/** Longitud */
	private Double inmLng = null;

	/** País del Titular - Código ISO 3166-1 Alfa-2 */
	private String userPostalCountryCode = null;

	/** País del Usuario */
	private String userPostalCountry = null;

	/** Provincia del Usuario - Código ISO 3166-2 */
	private String userPostalAdminAreaLevel1Code = null;

	/** Provincia del Usuario */
	private String userPostalAdminAreaLevel1 = null;

	/** Departamento / Distrito del Usuario */
	private String userPostalAdminAreaLevel2 = null;

	/** Localidad del Usuario */
	private String userPostalLocality = null;

	/** Vecindario del Usuario */
	private String userPostalNeighbourhood = null;

	/** Calle del Usuario */
	private String userPostalStreet = null;

	/** Número de Calle del Usuario */
	private String userPostalStreetNumber = null;

	/** Planta del Edificio del Usuario */
	private String userPostalBuildingFloor = null;

	/** Departamento de Edificio del Usuario */
	private String userPostalBuildingRoom = null;

	/** Edificio del Usuario */
	private String userPostalBuilding = null;

	/** Código Postal del Usuario */
	private String userPostalPostalCode = null;

	/** Comentario del Domicilio del Usuario */
	private String userPostalCommentAddress = null;

	/** Latitud del Usuario */
	private Double userPostalLat = null;

	/** Longitud del Usuario */
	private Double userPostalLng = null;

	/** Teléfono del Usuario */
	private String userPhone = null;

	/** Código de Motivo de Baja */
	private String codeReasonLow = null;

	/** Motivo de Baja */
	private String reasonLow = null;

	/** Cantidad de PH */
	private Integer cantPh = null;

	/** Fecha Barrido -2 */
	private Date dateScanning2 = null;

	/** Fecha Barrido -1 */
	private Date dateScanning1 = null;

	/** Fecha Barrido 0 */
	private Date dateScanning0 = null;

	/** auditDateCreate */
	private Timestamp auditDateCreate = null;

	/** auditUserCreate */
	private String auditUserCreate = null;

	/** auditDateUpdate */
	private Timestamp auditDateUpdate = null;

	/** auditUserUpdate */
	private String auditUserUpdate = null;

	/** auditVersion */
	private Long auditVersion = null;

	/** auditVersionCodeLabel */
	private String auditVersionCodeLabel = null;

	/** auditVersionLabel */
	private String auditVersionLabel = null;

	/** SubDivision de Hecho */
	private Boolean fact = false;

	/** Metros Cuadrados Cubiertos Compartidos */
	private Double m2CoveredShared = null;

	/** Metros Cuadrados Cubiertos Ampliados */
	private Double m2CoveredExpanded = null;

	/** Porcentaje de PH */
	private Double m2Percent = null;

	/** Año Bloque de PH */
	private Integer yearBuilding = null;

	/** Progreso de Construcción */
	private Double progressConstructionPrev = null;

	/** Condición de IVA */
	private Iva userIva = null;

	/** Situación de Usuario del Servicio */
	private UserWaterSituation userWaterSituation = null;

	/** Unidad de Facturación */
	private Uf uf = null;

	/** Area (Zona) de Ciudad */
	private CityArea cityArea = null;

	/** Tipo de Catastro */
	private CadastreType cadastreType = null;

	/** Situación de Catastro */
	private CadastreSituation cadastreSituation = null;

	/** Vecindario */
	private Neighbourhood inmNeighbourhood = null;

	/** Tipo de PH */
	private CadastreSubDivisionType cadastreSubDivisionType = null;

	/** Resultado de Barrido */
	private ScheduleScanningResult scheduleScanningResult2 = null;

	/** Resultado de Barrido */
	private ScheduleScanningResult scheduleScanningResult1 = null;

	/** Resultado de Barrido */
	private ScheduleScanningResult scheduleScanningResult0 = null;

	/** Tipo de Actividad */
	private CadastreActivityType cadastreActivityType = null;

	/** Tipo de Medidor */
	private WaterMeterType waterMeterType = null;

	/** Tipo Constructivo */
	private CadastreConstructiveType cadastreConstructiveType = null;

	/** Listado - Bloque Constructivo */
	private CadastreBlock[] cadastreBlockList = new CadastreBlock[0];

	/** Listado - Item de Barrido de Manzana Catastral */
	private ScheduleScanningItem[] scheduleScanningItemList = new ScheduleScanningItem[0];

	/** Listado - Item Censo de Parcela */
	private ScheduleCensusItem[] scheduleCensusItemList = new ScheduleCensusItem[0];

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getErased() {
		return this.erased;
	}

	public void setErased(Boolean erased) {
		this.erased = erased;
	}

	public Timestamp getDateCreate() {
		return this.dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Timestamp getDateDelete() {
		return this.dateDelete;
	}

	public void setDateDelete(Timestamp dateDelete) {
		this.dateDelete = dateDelete;
	}

	public String getCadastralCode() {
		return this.cadastralCode;
	}

	public void setCadastralCode(String cadastralCode) {
		this.cadastralCode = cadastralCode;
	}

	public Double getM2() {
		return this.m2;
	}

	public void setM2(Double m2) {
		this.m2 = m2;
	}

	public Double getM2Covered() {
		return this.m2Covered;
	}

	public void setM2Covered(Double m2Covered) {
		this.m2Covered = m2Covered;
	}

	public String getCtaCli() {
		return this.ctaCli;
	}

	public void setCtaCli(String ctaCli) {
		this.ctaCli = ctaCli;
	}

	public String getSubCtaCli() {
		return this.subCtaCli;
	}

	public void setSubCtaCli(String subCtaCli) {
		this.subCtaCli = subCtaCli;
	}

	public String getDv() {
		return this.dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public String getUserWaterService() {
		return this.userWaterService;
	}

	public void setUserWaterService(String userWaterService) {
		this.userWaterService = userWaterService;
	}

	public String getUserWaterServiceDni() {
		return this.userWaterServiceDni;
	}

	public void setUserWaterServiceDni(String userWaterServiceDni) {
		this.userWaterServiceDni = userWaterServiceDni;
	}

	public String getUserWaterServiceCuit() {
		return this.userWaterServiceCuit;
	}

	public void setUserWaterServiceCuit(String userWaterServiceCuit) {
		this.userWaterServiceCuit = userWaterServiceCuit;
	}

	public Boolean getWaterMeter() {
		return this.waterMeter;
	}

	public void setWaterMeter(Boolean waterMeter) {
		this.waterMeter = waterMeter;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getInmLocality() {
		return this.inmLocality;
	}

	public void setInmLocality(String inmLocality) {
		this.inmLocality = inmLocality;
	}

	public String getInmNeighbourhoodComment() {
		return this.inmNeighbourhoodComment;
	}

	public void setInmNeighbourhoodComment(String inmNeighbourhoodComment) {
		this.inmNeighbourhoodComment = inmNeighbourhoodComment;
	}

	public String getInmStreet() {
		return this.inmStreet;
	}

	public void setInmStreet(String inmStreet) {
		this.inmStreet = inmStreet;
	}

	public String getInmStreetNumber() {
		return this.inmStreetNumber;
	}

	public void setInmStreetNumber(String inmStreetNumber) {
		this.inmStreetNumber = inmStreetNumber;
	}

	public Boolean getInmStreetNumberEstimated() {
		return this.inmStreetNumberEstimated;
	}

	public void setInmStreetNumberEstimated(Boolean inmStreetNumberEstimated) {
		this.inmStreetNumberEstimated = inmStreetNumberEstimated;
	}

	public String getInmBuildingFloor() {
		return this.inmBuildingFloor;
	}

	public void setInmBuildingFloor(String inmBuildingFloor) {
		this.inmBuildingFloor = inmBuildingFloor;
	}

	public String getInmBuildingRoom() {
		return this.inmBuildingRoom;
	}

	public void setInmBuildingRoom(String inmBuildingRoom) {
		this.inmBuildingRoom = inmBuildingRoom;
	}

	public String getInmBuilding() {
		return this.inmBuilding;
	}

	public void setInmBuilding(String inmBuilding) {
		this.inmBuilding = inmBuilding;
	}

	public String getInmPostalCode() {
		return this.inmPostalCode;
	}

	public void setInmPostalCode(String inmPostalCode) {
		this.inmPostalCode = inmPostalCode;
	}

	public String getInmCommentAddress() {
		return this.inmCommentAddress;
	}

	public void setInmCommentAddress(String inmCommentAddress) {
		this.inmCommentAddress = inmCommentAddress;
	}

	public Double getInmLat() {
		return this.inmLat;
	}

	public void setInmLat(Double inmLat) {
		this.inmLat = inmLat;
	}

	public Double getInmLng() {
		return this.inmLng;
	}

	public void setInmLng(Double inmLng) {
		this.inmLng = inmLng;
	}

	public String getUserPostalCountryCode() {
		return this.userPostalCountryCode;
	}

	public void setUserPostalCountryCode(String userPostalCountryCode) {
		this.userPostalCountryCode = userPostalCountryCode;
	}

	public String getUserPostalCountry() {
		return this.userPostalCountry;
	}

	public void setUserPostalCountry(String userPostalCountry) {
		this.userPostalCountry = userPostalCountry;
	}

	public String getUserPostalAdminAreaLevel1Code() {
		return this.userPostalAdminAreaLevel1Code;
	}

	public void setUserPostalAdminAreaLevel1Code(String userPostalAdminAreaLevel1Code) {
		this.userPostalAdminAreaLevel1Code = userPostalAdminAreaLevel1Code;
	}

	public String getUserPostalAdminAreaLevel1() {
		return this.userPostalAdminAreaLevel1;
	}

	public void setUserPostalAdminAreaLevel1(String userPostalAdminAreaLevel1) {
		this.userPostalAdminAreaLevel1 = userPostalAdminAreaLevel1;
	}

	public String getUserPostalAdminAreaLevel2() {
		return this.userPostalAdminAreaLevel2;
	}

	public void setUserPostalAdminAreaLevel2(String userPostalAdminAreaLevel2) {
		this.userPostalAdminAreaLevel2 = userPostalAdminAreaLevel2;
	}

	public String getUserPostalLocality() {
		return this.userPostalLocality;
	}

	public void setUserPostalLocality(String userPostalLocality) {
		this.userPostalLocality = userPostalLocality;
	}

	public String getUserPostalNeighbourhood() {
		return this.userPostalNeighbourhood;
	}

	public void setUserPostalNeighbourhood(String userPostalNeighbourhood) {
		this.userPostalNeighbourhood = userPostalNeighbourhood;
	}

	public String getUserPostalStreet() {
		return this.userPostalStreet;
	}

	public void setUserPostalStreet(String userPostalStreet) {
		this.userPostalStreet = userPostalStreet;
	}

	public String getUserPostalStreetNumber() {
		return this.userPostalStreetNumber;
	}

	public void setUserPostalStreetNumber(String userPostalStreetNumber) {
		this.userPostalStreetNumber = userPostalStreetNumber;
	}

	public String getUserPostalBuildingFloor() {
		return this.userPostalBuildingFloor;
	}

	public void setUserPostalBuildingFloor(String userPostalBuildingFloor) {
		this.userPostalBuildingFloor = userPostalBuildingFloor;
	}

	public String getUserPostalBuildingRoom() {
		return this.userPostalBuildingRoom;
	}

	public void setUserPostalBuildingRoom(String userPostalBuildingRoom) {
		this.userPostalBuildingRoom = userPostalBuildingRoom;
	}

	public String getUserPostalBuilding() {
		return this.userPostalBuilding;
	}

	public void setUserPostalBuilding(String userPostalBuilding) {
		this.userPostalBuilding = userPostalBuilding;
	}

	public String getUserPostalPostalCode() {
		return this.userPostalPostalCode;
	}

	public void setUserPostalPostalCode(String userPostalPostalCode) {
		this.userPostalPostalCode = userPostalPostalCode;
	}

	public String getUserPostalCommentAddress() {
		return this.userPostalCommentAddress;
	}

	public void setUserPostalCommentAddress(String userPostalCommentAddress) {
		this.userPostalCommentAddress = userPostalCommentAddress;
	}

	public Double getUserPostalLat() {
		return this.userPostalLat;
	}

	public void setUserPostalLat(Double userPostalLat) {
		this.userPostalLat = userPostalLat;
	}

	public Double getUserPostalLng() {
		return this.userPostalLng;
	}

	public void setUserPostalLng(Double userPostalLng) {
		this.userPostalLng = userPostalLng;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getCodeReasonLow() {
		return this.codeReasonLow;
	}

	public void setCodeReasonLow(String codeReasonLow) {
		this.codeReasonLow = codeReasonLow;
	}

	public String getReasonLow() {
		return this.reasonLow;
	}

	public void setReasonLow(String reasonLow) {
		this.reasonLow = reasonLow;
	}

	public Integer getCantPh() {
		return this.cantPh;
	}

	public void setCantPh(Integer cantPh) {
		this.cantPh = cantPh;
	}

	public Date getDateScanning2() {
		return this.dateScanning2;
	}

	public void setDateScanning2(Date dateScanning2) {
		this.dateScanning2 = dateScanning2;
	}

	public Date getDateScanning1() {
		return this.dateScanning1;
	}

	public void setDateScanning1(Date dateScanning1) {
		this.dateScanning1 = dateScanning1;
	}

	public Date getDateScanning0() {
		return this.dateScanning0;
	}

	public void setDateScanning0(Date dateScanning0) {
		this.dateScanning0 = dateScanning0;
	}

	public Timestamp getAuditDateCreate() {
		return this.auditDateCreate;
	}

	public void setAuditDateCreate(Timestamp auditDateCreate) {
		this.auditDateCreate = auditDateCreate;
	}

	public String getAuditUserCreate() {
		return this.auditUserCreate;
	}

	public void setAuditUserCreate(String auditUserCreate) {
		this.auditUserCreate = auditUserCreate;
	}

	public Timestamp getAuditDateUpdate() {
		return this.auditDateUpdate;
	}

	public void setAuditDateUpdate(Timestamp auditDateUpdate) {
		this.auditDateUpdate = auditDateUpdate;
	}

	public String getAuditUserUpdate() {
		return this.auditUserUpdate;
	}

	public void setAuditUserUpdate(String auditUserUpdate) {
		this.auditUserUpdate = auditUserUpdate;
	}

	public Long getAuditVersion() {
		return this.auditVersion;
	}

	public void setAuditVersion(Long auditVersion) {
		this.auditVersion = auditVersion;
	}

	public String getAuditVersionCodeLabel() {
		return this.auditVersionCodeLabel;
	}

	public void setAuditVersionCodeLabel(String auditVersionCodeLabel) {
		this.auditVersionCodeLabel = auditVersionCodeLabel;
	}

	public String getAuditVersionLabel() {
		return this.auditVersionLabel;
	}

	public void setAuditVersionLabel(String auditVersionLabel) {
		this.auditVersionLabel = auditVersionLabel;
	}

	public Boolean getFact() {
		return this.fact;
	}

	public void setFact(Boolean fact) {
		this.fact = fact;
	}

	public Double getM2CoveredShared() {
		return this.m2CoveredShared;
	}

	public void setM2CoveredShared(Double m2CoveredShared) {
		this.m2CoveredShared = m2CoveredShared;
	}

	public Double getM2CoveredExpanded() {
		return this.m2CoveredExpanded;
	}

	public void setM2CoveredExpanded(Double m2CoveredExpanded) {
		this.m2CoveredExpanded = m2CoveredExpanded;
	}

	public Double getM2Percent() {
		return this.m2Percent;
	}

	public void setM2Percent(Double m2Percent) {
		this.m2Percent = m2Percent;
	}

	public Integer getYearBuilding() {
		return this.yearBuilding;
	}

	public void setYearBuilding(Integer yearBuilding) {
		this.yearBuilding = yearBuilding;
	}

	public Double getProgressConstructionPrev() {
		return this.progressConstructionPrev;
	}

	public void setProgressConstructionPrev(Double progressConstructionPrev) {
		this.progressConstructionPrev = progressConstructionPrev;
	}

	public Iva getUserIva() {
		return this.userIva;
	}

	public void setUserIva(Iva userIva) {
		this.userIva = userIva;
	}

	public UserWaterSituation getUserWaterSituation() {
		return this.userWaterSituation;
	}

	public void setUserWaterSituation(UserWaterSituation userWaterSituation) {
		this.userWaterSituation = userWaterSituation;
	}

	public Uf getUf() {
		return this.uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public CityArea getCityArea() {
		return this.cityArea;
	}

	public void setCityArea(CityArea cityArea) {
		this.cityArea = cityArea;
	}

	public CadastreType getCadastreType() {
		return this.cadastreType;
	}

	public void setCadastreType(CadastreType cadastreType) {
		this.cadastreType = cadastreType;
	}

	public CadastreSituation getCadastreSituation() {
		return this.cadastreSituation;
	}

	public void setCadastreSituation(CadastreSituation cadastreSituation) {
		this.cadastreSituation = cadastreSituation;
	}

	public Neighbourhood getInmNeighbourhood() {
		return this.inmNeighbourhood;
	}

	public void setInmNeighbourhood(Neighbourhood inmNeighbourhood) {
		this.inmNeighbourhood = inmNeighbourhood;
	}

	public CadastreSubDivisionType getCadastreSubDivisionType() {
		return this.cadastreSubDivisionType;
	}

	public void setCadastreSubDivisionType(CadastreSubDivisionType cadastreSubDivisionType) {
		this.cadastreSubDivisionType = cadastreSubDivisionType;
	}

	public ScheduleScanningResult getScheduleScanningResult2() {
		return this.scheduleScanningResult2;
	}

	public void setScheduleScanningResult2(ScheduleScanningResult scheduleScanningResult2) {
		this.scheduleScanningResult2 = scheduleScanningResult2;
	}

	public ScheduleScanningResult getScheduleScanningResult1() {
		return this.scheduleScanningResult1;
	}

	public void setScheduleScanningResult1(ScheduleScanningResult scheduleScanningResult1) {
		this.scheduleScanningResult1 = scheduleScanningResult1;
	}

	public ScheduleScanningResult getScheduleScanningResult0() {
		return this.scheduleScanningResult0;
	}

	public void setScheduleScanningResult0(ScheduleScanningResult scheduleScanningResult0) {
		this.scheduleScanningResult0 = scheduleScanningResult0;
	}

	public CadastreActivityType getCadastreActivityType() {
		return this.cadastreActivityType;
	}

	public void setCadastreActivityType(CadastreActivityType cadastreActivityType) {
		this.cadastreActivityType = cadastreActivityType;
	}

	public WaterMeterType getWaterMeterType() {
		return this.waterMeterType;
	}

	public void setWaterMeterType(WaterMeterType waterMeterType) {
		this.waterMeterType = waterMeterType;
	}

	public CadastreConstructiveType getCadastreConstructiveType() {
		return this.cadastreConstructiveType;
	}

	public void setCadastreConstructiveType(CadastreConstructiveType cadastreConstructiveType) {
		this.cadastreConstructiveType = cadastreConstructiveType;
	}

	public CadastreBlock[] getCadastreBlockList() {
		if (cadastreBlockList == null) {
			cadastreBlockList = new CadastreBlock[0];
		}
		return this.cadastreBlockList;
	}

	public void setCadastreBlockList(CadastreBlock[] cadastreBlockList) {

		if (cadastreBlockList == null)
			throw new IllegalArgumentException("Argument is null.");

		for (int i = 0; i < cadastreBlockList.length; i++) {

			if (cadastreBlockList[i] == null)
				throw new IllegalArgumentException("Argument is null. Index " + i);

		}

		this.cadastreBlockList = cadastreBlockList;
	}

	public CadastreBlock getCadastreBlockList(Integer index) {
		if (cadastreBlockList == null) {
			cadastreBlockList = new CadastreBlock[0];
		}
		if (index >= cadastreBlockList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + cadastreBlockList.length);

		return this.cadastreBlockList[index];
	}

	public CadastreBlock removeCadastreBlockList(Integer index) {

		if (cadastreBlockList == null) {
			cadastreBlockList = new CadastreBlock[0];
		}

		if (index >= cadastreBlockList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + cadastreBlockList.length);

		CadastreBlock oldValue = cadastreBlockList[index];

		CadastreBlock[] listTmp = new CadastreBlock[this.cadastreBlockList.length - 1];

		for (int i = 0; i < cadastreBlockList.length; i++) {

			if (i != index) {

				listTmp[i] = cadastreBlockList[i];

			}

		}

		this.cadastreBlockList = listTmp;

		return oldValue;
	}

	public void addCadastreBlockList(CadastreBlock cadastreBlock) {
		if (cadastreBlock == null)
			throw new IllegalArgumentException("Argument is null.");

		cadastreBlock.setCadastre(this);

		if (cadastreBlockList == null) {
			cadastreBlockList = new CadastreBlock[1];
			cadastreBlockList[0] = cadastreBlock;
		} else {
			CadastreBlock[] listTmp = new CadastreBlock[this.cadastreBlockList.length + 1];
			listTmp[listTmp.length - 1] = cadastreBlock;
			System.arraycopy(this.cadastreBlockList, 0, listTmp, 0, this.cadastreBlockList.length);
			this.cadastreBlockList = listTmp;
		}
	}

	public ScheduleScanningItem[] getScheduleScanningItemList() {
		if (scheduleScanningItemList == null) {
			scheduleScanningItemList = new ScheduleScanningItem[0];
		}
		return this.scheduleScanningItemList;
	}

	public void setScheduleScanningItemList(ScheduleScanningItem[] scheduleScanningItemList) {

		if (scheduleScanningItemList == null)
			throw new IllegalArgumentException("Argument is null.");

		for (int i = 0; i < scheduleScanningItemList.length; i++) {

			if (scheduleScanningItemList[i] == null)
				throw new IllegalArgumentException("Argument is null. Index " + i);

		}

		this.scheduleScanningItemList = scheduleScanningItemList;
	}

	public ScheduleScanningItem getScheduleScanningItemList(Integer index) {
		if (scheduleScanningItemList == null) {
			scheduleScanningItemList = new ScheduleScanningItem[0];
		}
		if (index >= scheduleScanningItemList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleScanningItemList.length);

		return this.scheduleScanningItemList[index];
	}

	public ScheduleScanningItem removeScheduleScanningItemList(Integer index) {

		if (scheduleScanningItemList == null) {
			scheduleScanningItemList = new ScheduleScanningItem[0];
		}

		if (index >= scheduleScanningItemList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleScanningItemList.length);

		ScheduleScanningItem oldValue = scheduleScanningItemList[index];

		ScheduleScanningItem[] listTmp = new ScheduleScanningItem[this.scheduleScanningItemList.length - 1];

		for (int i = 0; i < scheduleScanningItemList.length; i++) {

			if (i != index) {

				listTmp[i] = scheduleScanningItemList[i];

			}

		}

		this.scheduleScanningItemList = listTmp;

		return oldValue;
	}

	public void addScheduleScanningItemList(ScheduleScanningItem scheduleScanningItem) {
		if (scheduleScanningItem == null)
			throw new IllegalArgumentException("Argument is null.");

		scheduleScanningItem.setCadastre(this);

		if (scheduleScanningItemList == null) {
			scheduleScanningItemList = new ScheduleScanningItem[1];
			scheduleScanningItemList[0] = scheduleScanningItem;
		} else {
			ScheduleScanningItem[] listTmp = new ScheduleScanningItem[this.scheduleScanningItemList.length + 1];
			listTmp[listTmp.length - 1] = scheduleScanningItem;
			System.arraycopy(this.scheduleScanningItemList, 0, listTmp, 0, this.scheduleScanningItemList.length);
			this.scheduleScanningItemList = listTmp;
		}
	}

	public ScheduleCensusItem[] getScheduleCensusItemList() {
		if (scheduleCensusItemList == null) {
			scheduleCensusItemList = new ScheduleCensusItem[0];
		}
		return this.scheduleCensusItemList;
	}

	public void setScheduleCensusItemList(ScheduleCensusItem[] scheduleCensusItemList) {

		if (scheduleCensusItemList == null)
			throw new IllegalArgumentException("Argument is null.");

		for (int i = 0; i < scheduleCensusItemList.length; i++) {

			if (scheduleCensusItemList[i] == null)
				throw new IllegalArgumentException("Argument is null. Index " + i);

		}

		this.scheduleCensusItemList = scheduleCensusItemList;
	}

	public ScheduleCensusItem getScheduleCensusItemList(Integer index) {
		if (scheduleCensusItemList == null) {
			scheduleCensusItemList = new ScheduleCensusItem[0];
		}
		if (index >= scheduleCensusItemList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleCensusItemList.length);

		return this.scheduleCensusItemList[index];
	}

	public ScheduleCensusItem removeScheduleCensusItemList(Integer index) {

		if (scheduleCensusItemList == null) {
			scheduleCensusItemList = new ScheduleCensusItem[0];
		}

		if (index >= scheduleCensusItemList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleCensusItemList.length);

		ScheduleCensusItem oldValue = scheduleCensusItemList[index];

		ScheduleCensusItem[] listTmp = new ScheduleCensusItem[this.scheduleCensusItemList.length - 1];

		for (int i = 0; i < scheduleCensusItemList.length; i++) {

			if (i != index) {

				listTmp[i] = scheduleCensusItemList[i];

			}

		}

		this.scheduleCensusItemList = listTmp;

		return oldValue;
	}

	public void addScheduleCensusItemList(ScheduleCensusItem scheduleCensusItem) {
		if (scheduleCensusItem == null)
			throw new IllegalArgumentException("Argument is null.");

		scheduleCensusItem.setCadastre(this);

		if (scheduleCensusItemList == null) {
			scheduleCensusItemList = new ScheduleCensusItem[1];
			scheduleCensusItemList[0] = scheduleCensusItem;
		} else {
			ScheduleCensusItem[] listTmp = new ScheduleCensusItem[this.scheduleCensusItemList.length + 1];
			listTmp[listTmp.length - 1] = scheduleCensusItem;
			System.arraycopy(this.scheduleCensusItemList, 0, listTmp, 0, this.scheduleCensusItemList.length);
			this.scheduleCensusItemList = listTmp;
		}
	}

	public String toString() {

		return this.getClass() + ": " + this.id;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cadastre other = (Cadastre) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Cadastre cloneId() {
		Cadastre other = new Cadastre();
		other.setId(this.getId());

		return other;
	}

	public String toStringReasonLow() {
		String r = null;

		if (getCodeReasonLow() != null) {
			r = "(" + getCodeReasonLow() + ")";

			if (getReasonLow() != null) {
				r += " " + getReasonLow();
			}
		}

		return r;
	}

	public String toStringInmAddres() {
		String r = "";

		if (this.getInmStreet() != null) {
			r += this.getInmStreet();
		}

		if (this.getInmStreetNumber() != null) {
			r += " " + this.getInmStreetNumber();
		}

		if (this.getInmStreetNumberEstimated() != null && this.getInmStreetNumberEstimated() == true) {
			r += " (estimado)";
		}

		if (this.getInmBuilding() != null) {
			r += " " + this.getInmBuilding();
		}

		if (this.getInmBuildingFloor() != null) {
			r += " " + this.getInmBuildingFloor();
		}

		if (this.getInmBuildingRoom() != null) {
			r += " " + this.getInmBuildingRoom();
		}

		if (this.getInmNeighbourhood() != null) {
			r += " " + this.getInmNeighbourhood();
		}

		if (this.getInmNeighbourhoodComment() != null) {
			r += " " + this.getInmNeighbourhoodComment();
		}

		if (this.getInmLocality() != null) {
			r += " " + this.getInmLocality();
		}

//		if (this.getInmPostalCode() != null) {
//			r += " CP:" + this.getInmPostalCode();
//		}
		
		if (this.getInmNeighbourhood() != null) {
			r += " CP:" + this.getInmNeighbourhood().getId().trim().substring(0, 4);
		}

		if (this.getInmCommentAddress() != null) {
//			r += " " + this.getInmCommentAddress();
		}

		r = r.trim();

		if (r.length() == 0) {
			r = null;
		}

		return r;
	}

	public String toStringUserWaterService() {

		String r = "";

		if (this.getUserWaterSituation() != null) {
			r += " " + this.getUserWaterSituation();
		}

		if (this.getUserWaterService() != null) {
			r += " " + this.getUserWaterService();
		}

		if (this.getUserWaterServiceCuit() != null) {
			r += " (CUIL:" + this.getUserWaterServiceCuit() + ")";
		}

		if (this.getUserWaterServiceDni() != null) {
			r += " (DNI:" + this.getUserWaterServiceDni() + ")";
		}		

		if (this.getUserPhone() != null) {
			r += " (TE: " + this.getUserPhone() + ")";
		}

		if (this.toStringUserWaterServiceAddres() != null) {
			r += "\n" + this.toStringUserWaterServiceAddres();
		}
		
		if (this.getUserIva() != null) {
			r += " [IVA: " + this.getUserIva() + "] ";
		}

		r = r.trim();

		if (r.length() == 0) {
			r = null;
		}

		return r;
	}

	public String toStringUserWaterServiceAddres() {
		String r = "";

		if (this.getUserPostalStreet() != null) {
			r += this.getUserPostalStreet();
		}

		if (this.getUserPostalStreetNumber() != null) {
			r += " " + this.getUserPostalStreetNumber();
		}

		if (this.getUserPostalBuilding() != null) {
			r += " " + this.getUserPostalBuilding();
		}

		if (this.getUserPostalBuildingFloor() != null) {
			r += " " + this.getUserPostalBuildingFloor();
		}

		if (this.getUserPostalBuildingRoom() != null) {
			r += " " + this.getUserPostalBuildingRoom();
		}

		if (this.getUserPostalNeighbourhood() != null) {
			r += " " + this.getUserPostalNeighbourhood();
		}

		if (this.getUserPostalLocality() != null) {
			r += " " + this.getUserPostalLocality();
		}

		if (this.getUserPostalPostalCode() != null) {
			r += " CP:" + this.getUserPostalPostalCode();
		}

		if (this.getUserPostalCommentAddress() != null) {
			r += " " + this.getUserPostalCommentAddress();
		}

		r = r.trim();

		if (r.length() == 0) {
			r = null;
		}

		return r;
	}
}
