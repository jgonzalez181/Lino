package com.cclip.dao.geo.cadastre.aacc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.ResultList;
import org.utiljdbc.UtilJdbcOld;

import com.cclip.model.geo.cadastre.aacc.Unl;

public class UnlDao {

	protected UtilJdbcOld utilJdbc;

	public void setUtilJdbc(UtilJdbcOld utilJdbc) {
		this.utilJdbc = utilJdbc;
	}

	public ResultList find(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit, String table) {
		// table = "cclip.aacc_datos_postales";

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, table, argList, new MapperResultFind(), offSet, limit, orderList);

		return r;

	}

	public Unl[] find(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, String table) {
		// table = "cclip.aacc_datos_postales";

		Object[] r = utilJdbc.findByExample(connectionWrapper, table, argList, new MapperResultFind(), orderList);

		if (r != null) {
			Unl[] unlList = new Unl[r.length];

			for (int i = 0; i < r.length; i++) {
				unlList[i] = (Unl) r[i];
			}

			return unlList;

		}

		return null;

	}

	public class MapperResultFind implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			Unl dto = new Unl();

			/* id */
			dto.setId(rs.getString("id"));

			/* erased */
			dto.setErased(rs.getBoolean("erased"));

			/* auditDateCreate */
			dto.setAuditDateCreate(rs.getTimestamp("audit_date_create"));

			/* auditUserCreate */
			dto.setAuditUserCreate(rs.getString("audit_user_create"));

			/* c1 */
			dto.setC1(rs.getString("c1"));

			/* c2 */
			dto.setC2(rs.getString("c2"));

			/* c3 */
			dto.setC3(rs.getString("c3"));

			/* c4 */
			dto.setC4(rs.getString("c4"));

			/* c5 */
			dto.setC5(rs.getString("c5"));

			/* c6 */
			dto.setC6(rs.getString("c6"));

			/* c7 */
			dto.setC7(rs.getString("c7"));

			/* c8 */
			dto.setC8(rs.getString("c8"));

			/* c9 */
			dto.setC9(rs.getString("c9"));

			/* c10 */
			dto.setC10(rs.getString("c10"));

			/* c11 */
			dto.setC11(rs.getString("c11"));

			/* c12 */
			dto.setC12(rs.getString("c12"));

			/* c13 */
			dto.setC13(rs.getString("c13"));

			/* c14 */
			dto.setC14(rs.getString("c14"));

			/* c15 */
			dto.setC15(rs.getString("c15"));

			/* c16 */
			dto.setC16(rs.getString("c16"));

			/* c17 */
			dto.setC17(rs.getString("c17"));

			/* c18 */
			dto.setC18(rs.getString("c18"));

			/* c19 */
			dto.setC19(rs.getString("c19"));

			/* c20 */
			dto.setC20(rs.getString("c20"));

			/* c21 */
			dto.setC21(rs.getString("c21"));

			/* c22 */
			dto.setC22(rs.getString("c22"));

			/* c23 */
			dto.setC23(rs.getString("c23"));

			/* c24 */
			dto.setC24(rs.getString("c24"));

			/* c25 */
			dto.setC25(rs.getString("c25"));

			/* c26 */
			dto.setC26(rs.getString("c26"));

			/* c27 */
			dto.setC27(rs.getString("c27"));

			/* c28 */
			dto.setC28(rs.getString("c28"));

			/* c29 */
			dto.setC29(rs.getString("c29"));

			/* c30 */
			dto.setC30(rs.getString("c30"));

			/* c31 */
			dto.setC31(rs.getString("c31"));

			/* c32 */
			dto.setC32(rs.getString("c32"));

			/* c33 */
			dto.setC33(rs.getString("c33"));

			/* c34 */
			dto.setC34(rs.getString("c34"));

			/* c35 */
			dto.setC35(rs.getString("c35"));

			/* c36 */
			dto.setC36(rs.getString("c36"));

			/* c37 */
			dto.setC37(rs.getString("c37"));

			/* c38 */
			dto.setC38(rs.getString("c38"));

			/* c39 */
			dto.setC39(rs.getString("c39"));

			/* c40 */
			dto.setC40(rs.getString("c40"));

			/* c41 */
			dto.setC41(rs.getString("c41"));

			/* c42 */
			dto.setC42(rs.getString("c42"));

			/* c43 */
			dto.setC43(rs.getString("c43"));

			/* c44 */
			dto.setC44(rs.getString("c44"));

			/* c45 */
			dto.setC45(rs.getString("c45"));

			/* c46 */
			dto.setC46(rs.getString("c46"));

			/* c47 */
			dto.setC47(rs.getString("c47"));

			/* c48 */
			dto.setC48(rs.getString("c48"));

			/* c49 */
			dto.setC49(rs.getString("c49"));

			/* c50 */
			dto.setC50(rs.getString("c50"));

			/* c51 */
			dto.setC51(rs.getString("c51"));

			/* c52 */
			dto.setC52(rs.getString("c52"));

			/* c53 */
			dto.setC53(rs.getString("c53"));

			/* c54 */
			dto.setC54(rs.getString("c54"));

			/* c55 */
			dto.setC55(rs.getString("c55"));

			/* c56 */
			dto.setC56(rs.getString("c56"));

			/* c57 */
			dto.setC57(rs.getString("c57"));

			/* c58 */
			dto.setC58(rs.getString("c58"));

			/* c59 */
			dto.setC59(rs.getString("c59"));

			/* c60 */
			dto.setC60(rs.getString("c60"));

			/* c61 */
			dto.setC61(rs.getString("c61"));

			/* c62 */
			dto.setC62(rs.getString("c62"));

			/* c63 */
			dto.setC63(rs.getString("c63"));

			/* c64 */
			dto.setC64(rs.getString("c64"));

			/* c65 */
			dto.setC65(rs.getString("c65"));

			/* c66 */
			dto.setC66(rs.getString("c66"));

			/* c67 */
			dto.setC67(rs.getString("c67"));

			/* c68 */
			dto.setC68(rs.getString("c68"));

			/* c69 */
			dto.setC69(rs.getString("c69"));

			/* c70 */
			dto.setC70(rs.getString("c70"));

			/* c71 */
			dto.setC71(rs.getString("c71"));

			/* c72 */
			dto.setC72(rs.getString("c72"));

			/* c73 */
			dto.setC73(rs.getString("c73"));

			/* c74 */
			dto.setC74(rs.getString("c74"));

			/* c75 */
			dto.setC75(rs.getString("c75"));

			/* c76 */
			dto.setC76(rs.getString("c76"));

			/* c77 */
			dto.setC77(rs.getString("c77"));

			/* c78 */
			dto.setC78(rs.getString("c78"));

			/* c79 */
			dto.setC79(rs.getString("c79"));

			/* c80 */
			dto.setC80(rs.getString("c80"));

			/* c81 */
			dto.setC81(rs.getString("c81"));

			/* c82 */
			dto.setC82(rs.getString("c82"));

			/* c83 */
			dto.setC83(rs.getString("c83"));

			/* c84 */
			dto.setC84(rs.getString("c84"));

			/* c85 */
			dto.setC85(rs.getString("c85"));

			/* c86 */
			dto.setC86(rs.getString("c86"));

			/* c87 */
			dto.setC87(rs.getString("c87"));

			/* c88 */
			dto.setC88(rs.getString("c88"));

			/* c89 */
			dto.setC89(rs.getString("c89"));

			/* c90 */
			dto.setC90(rs.getString("c90"));

			/* c91 */
			dto.setC91(rs.getString("c91"));

			/* c92 */
			dto.setC92(rs.getString("c92"));

			/* c93 */
			dto.setC93(rs.getString("c93"));

			/* c94 */
			dto.setC94(rs.getString("c94"));

			/* c95 */
			dto.setC95(rs.getString("c95"));

			/* c96 */
			dto.setC96(rs.getString("c96"));

			/* c97 */
			dto.setC97(rs.getString("c97"));

			/* c98 */
			dto.setC98(rs.getString("c98"));

			/* c99 */
			dto.setC99(rs.getString("c99"));

			/* c100 */
			dto.setC100(rs.getString("c100"));

			/* c101 */
			dto.setC101(rs.getString("c101"));

			/* c102 */
			dto.setC102(rs.getString("c102"));

			/* c103 */
			dto.setC103(rs.getString("c103"));

			/* c104 */
			dto.setC104(rs.getString("c104"));

			/* c105 */
			dto.setC105(rs.getString("c105"));

			/* c106 */
			dto.setC106(rs.getString("c106"));

			/* c107 */
			dto.setC107(rs.getString("c107"));

			/* c108 */
			dto.setC108(rs.getString("c108"));

			/* c109 */
			dto.setC109(rs.getString("c109"));

			/* c110 */
			dto.setC110(rs.getString("c110"));

			/* c111 */
			dto.setC111(rs.getString("c111"));

			/* c112 */
			dto.setC112(rs.getString("c112"));

			/* c113 */
			dto.setC113(rs.getString("c113"));

			/* c114 */
			dto.setC114(rs.getString("c114"));

			/* c115 */
			dto.setC115(rs.getString("c115"));

			/* c116 */
			dto.setC116(rs.getString("c116"));

			/* c117 */
			dto.setC117(rs.getString("c117"));

			/* c118 */
			dto.setC118(rs.getString("c118"));

			/* c119 */
			dto.setC119(rs.getString("c119"));

			/* c120 */
			dto.setC120(rs.getString("c120"));

			/* c121 */
			dto.setC121(rs.getString("c121"));

			/* c122 */
			dto.setC122(rs.getString("c122"));

			/* c123 */
			dto.setC123(rs.getString("c123"));

			/* c124 */
			dto.setC124(rs.getString("c124"));

			/* c125 */
			dto.setC125(rs.getString("c125"));

			/* c126 */
			dto.setC126(rs.getString("c126"));

			/* c127 */
			dto.setC127(rs.getString("c127"));

			/* c128 */
			dto.setC128(rs.getString("c128"));

			/* c129 */
			dto.setC129(rs.getString("c129"));

			/* c130 */
			dto.setC130(rs.getString("c130"));

			/* c131 */
			dto.setC131(rs.getString("c131"));

			/* c132 */
			dto.setC132(rs.getString("c132"));

			/* c133 */
			dto.setC133(rs.getString("c133"));

			/* c134 */
			dto.setC134(rs.getString("c134"));

			/* c135 */
			dto.setC135(rs.getString("c135"));

			/* c136 */
			dto.setC136(rs.getString("c136"));

			/* c137 */
			dto.setC137(rs.getString("c137"));

			/* c138 */
			dto.setC138(rs.getString("c138"));

			/* c139 */
			dto.setC139(rs.getString("c139"));

			/* c140 */
			dto.setC140(rs.getString("c140"));

			/* c141 */
			dto.setC141(rs.getString("c141"));

			/* c142 */
			dto.setC142(rs.getString("c142"));

			/* c143 */
			dto.setC143(rs.getString("c143"));

			/* c144 */
			dto.setC144(rs.getString("c144"));

			/* c145 */
			dto.setC145(rs.getString("c145"));

			/* c146 */
			dto.setC146(rs.getString("c146"));

			/* c147 */
			dto.setC147(rs.getString("c147"));

			/* c148 */
			dto.setC148(rs.getString("c148"));

			/* c149 */
			dto.setC149(rs.getString("c149"));

			/* c150 */
			dto.setC150(rs.getString("c150"));

			/* c151 */
			dto.setC151(rs.getString("c151"));

			/* c152 */
			dto.setC152(rs.getString("c152"));

			/* c153 */
			dto.setC153(rs.getString("c153"));

			/* c154 */
			dto.setC154(rs.getString("c154"));

			/* c155 */
			dto.setC155(rs.getString("c155"));

			/* c156 */
			dto.setC156(rs.getString("c156"));

			/* c157 */
			dto.setC157(rs.getString("c157"));

			/* c158 */
			dto.setC158(rs.getString("c158"));

			/* c159 */
			dto.setC159(rs.getString("c159"));

			/* c160 */
			dto.setC160(rs.getString("c160"));

			/* c161 */
			dto.setC161(rs.getString("c161"));

			/* c162 */
			dto.setC162(rs.getString("c162"));

			/* c163 */
			dto.setC163(rs.getString("c163"));

			/* c164 */
			dto.setC164(rs.getString("c164"));

			/* c165 */
			dto.setC165(rs.getString("c165"));

			/* c166 */
			dto.setC166(rs.getString("c166"));

			/* c167 */
			dto.setC167(rs.getString("c167"));

			/* c168 */
			dto.setC168(rs.getString("c168"));

			/* c169 */
			dto.setC169(rs.getString("c169"));

			/* c170 */
			dto.setC170(rs.getString("c170"));

			/* c171 */
			dto.setC171(rs.getString("c171"));

			/* c172 */
			dto.setC172(rs.getString("c172"));

			/* c173 */
			dto.setC173(rs.getString("c173"));

			/* c174 */
			dto.setC174(rs.getString("c174"));

			/* c175 */
			dto.setC175(rs.getString("c175"));

			/* c176 */
			dto.setC176(rs.getString("c176"));

			/* c177 */
			dto.setC177(rs.getString("c177"));

			/* c178 */
			dto.setC178(rs.getString("c178"));

			/* c179 */
			dto.setC179(rs.getString("c179"));

			/* c180 */
			dto.setC180(rs.getString("c180"));

			/* c181 */
			dto.setC181(rs.getString("c181"));

			/* c182 */
			dto.setC182(rs.getString("c182"));

			/* c183 */
			dto.setC183(rs.getString("c183"));

			/* c184 */
			dto.setC184(rs.getString("c184"));

			/* c185 */
			dto.setC185(rs.getString("c185"));

			/* c186 */
			dto.setC186(rs.getString("c186"));

			/* c187 */
			dto.setC187(rs.getString("c187"));

			/* c188 */
			dto.setC188(rs.getString("c188"));

			/* c189 */
			dto.setC189(rs.getString("c189"));

			/* c190 */
			dto.setC190(rs.getString("c190"));

			/* c191 */
			dto.setC191(rs.getString("c191"));

			/* c192 */
			dto.setC192(rs.getString("c192"));

			/* c193 */
			dto.setC193(rs.getString("c193"));

			/* c194 */
			dto.setC194(rs.getString("c194"));

			/* c195 */
			dto.setC195(rs.getString("c195"));

			/* c196 */
			dto.setC196(rs.getString("c196"));

			/* c197 */
			dto.setC197(rs.getString("c197"));

			/* c198 */
			dto.setC198(rs.getString("c198"));

			/* c199 */
			dto.setC199(rs.getString("c199"));

			/* c200 */
			dto.setC200(rs.getString("c200"));

			/* c201 */
			dto.setC201(rs.getString("c201"));

			/* c202 */
			dto.setC202(rs.getString("c202"));

			/* c203 */
			dto.setC203(rs.getString("c203"));

			/* c204 */
			dto.setC204(rs.getString("c204"));

			/* c205 */
			dto.setC205(rs.getString("c205"));

			/* c206 */
			dto.setC206(rs.getString("c206"));

			/* c207 */
			dto.setC207(rs.getString("c207"));

			/* c208 */
			dto.setC208(rs.getString("c208"));

			/* c209 */
			dto.setC209(rs.getString("c209"));

			/* c210 */
			dto.setC210(rs.getString("c210"));

			/* c211 */
			dto.setC211(rs.getString("c211"));

			/* c212 */
			dto.setC212(rs.getString("c212"));

			/* c213 */
			dto.setC213(rs.getString("c213"));

			/* c214 */
			dto.setC214(rs.getString("c214"));

			/* c215 */
			dto.setC215(rs.getString("c215"));

			/* c216 */
			dto.setC216(rs.getString("c216"));

			/* c217 */
			dto.setC217(rs.getString("c217"));

			/* c218 */
			dto.setC218(rs.getString("c218"));

			/* c219 */
			dto.setC219(rs.getString("c219"));

			/* c220 */
			dto.setC220(rs.getString("c220"));

			/* c221 */
			dto.setC221(rs.getString("c221"));

			/* c222 */
			dto.setC222(rs.getString("c222"));

			/* c223 */
			dto.setC223(rs.getString("c223"));

			/* c224 */
			dto.setC224(rs.getString("c224"));

			/* c225 */
			dto.setC225(rs.getString("c225"));

			/* c226 */
			dto.setC226(rs.getString("c226"));

			/* c227 */
			dto.setC227(rs.getString("c227"));

			/* c228 */
			dto.setC228(rs.getString("c228"));

			/* c229 */
			dto.setC229(rs.getString("c229"));

			/* c230 */
			dto.setC230(rs.getString("c230"));

			/* c231 */
			dto.setC231(rs.getString("c231"));

			/* c232 */
			dto.setC232(rs.getString("c232"));

			/* c233 */
			dto.setC233(rs.getString("c233"));

			/* c234 */
			dto.setC234(rs.getString("c234"));

			/* c235 */
			dto.setC235(rs.getString("c235"));

			/* c236 */
			dto.setC236(rs.getString("c236"));

			/* c237 */
			dto.setC237(rs.getString("c237"));

			/* c238 */
			dto.setC238(rs.getString("c238"));

			/* c239 */
			dto.setC239(rs.getString("c239"));

			/* c240 */
			dto.setC240(rs.getString("c240"));

			/* c241 */
			dto.setC241(rs.getString("c241"));

			/* c242 */
			dto.setC242(rs.getString("c242"));

			/* c243 */
			dto.setC243(rs.getString("c243"));

			/* c244 */
			dto.setC244(rs.getString("c244"));

			/* c245 */
			dto.setC245(rs.getString("c245"));

			/* c246 */
			dto.setC246(rs.getString("c246"));

			/* c247 */
			dto.setC247(rs.getString("c247"));

			/* c248 */
			dto.setC248(rs.getString("c248"));

			/* c249 */
			dto.setC249(rs.getString("c249"));

			/* c250 */
			dto.setC250(rs.getString("c250"));

			/* c251 */
			dto.setC251(rs.getString("c251"));

			/* c252 */
			dto.setC252(rs.getString("c252"));

			/* c253 */
			dto.setC253(rs.getString("c253"));

			/* c254 */
			dto.setC254(rs.getString("c254"));

			/* c255 */
			dto.setC255(rs.getString("c255"));

			/* c256 */
			dto.setC256(rs.getString("c256"));

			/* c257 */
			dto.setC257(rs.getString("c257"));

			/* c258 */
			dto.setC258(rs.getString("c258"));

			/* c259 */
			dto.setC259(rs.getString("c259"));

			/* c260 */
			dto.setC260(rs.getString("c260"));

			/* c261 */
			dto.setC261(rs.getString("c261"));

			/* c262 */
			dto.setC262(rs.getString("c262"));

			/* c263 */
			dto.setC263(rs.getString("c263"));

			/* c264 */
			dto.setC264(rs.getString("c264"));

			/* c265 */
			dto.setC265(rs.getString("c265"));

			/* c266 */
			dto.setC266(rs.getString("c266"));

			/* c267 */
			dto.setC267(rs.getString("c267"));

			/* c268 */
			dto.setC268(rs.getString("c268"));

			/* c269 */
			dto.setC269(rs.getString("c269"));

			/* c270 */
			dto.setC270(rs.getString("c270"));

			/* c271 */
			dto.setC271(rs.getString("c271"));

			/* c272 */
			dto.setC272(rs.getString("c272"));

			/* c273 */
			dto.setC273(rs.getString("c273"));

			/* c274 */
			dto.setC274(rs.getString("c274"));

			/* c275 */
			dto.setC275(rs.getString("c275"));

			/* c276 */
			dto.setC276(rs.getString("c276"));

			/* c277 */
			dto.setC277(rs.getString("c277"));

			/* c278 */
			dto.setC278(rs.getString("c278"));

			/* c279 */
			dto.setC279(rs.getString("c279"));

			/* c280 */
			dto.setC280(rs.getString("c280"));

			/* c281 */
			dto.setC281(rs.getString("c281"));

			/* c282 */
			dto.setC282(rs.getString("c282"));

			/* c283 */
			dto.setC283(rs.getString("c283"));

			/* c284 */
			dto.setC284(rs.getString("c284"));

			/* c285 */
			dto.setC285(rs.getString("c285"));

			/* c286 */
			dto.setC286(rs.getString("c286"));

			/* c287 */
			dto.setC287(rs.getString("c287"));

			/* c288 */
			dto.setC288(rs.getString("c288"));

			/* c289 */
			dto.setC289(rs.getString("c289"));

			/* c290 */
			dto.setC290(rs.getString("c290"));

			/* c291 */
			dto.setC291(rs.getString("c291"));

			/* c292 */
			dto.setC292(rs.getString("c292"));

			/* c293 */
			dto.setC293(rs.getString("c293"));

			/* c294 */
			dto.setC294(rs.getString("c294"));

			/* c295 */
			dto.setC295(rs.getString("c295"));

			/* c296 */
			dto.setC296(rs.getString("c296"));

			/* c297 */
			dto.setC297(rs.getString("c297"));

			/* c298 */
			dto.setC298(rs.getString("c298"));

			/* c299 */
			dto.setC299(rs.getString("c299"));

			/* c300 */
			dto.setC300(rs.getString("c300"));

			/* c301 */
			dto.setC301(rs.getString("c301"));

			/* c302 */
			dto.setC302(rs.getString("c302"));

			/* c303 */
			dto.setC303(rs.getString("c303"));

			/* c304 */
			dto.setC304(rs.getString("c304"));

			/* c305 */
			dto.setC305(rs.getString("c305"));

			/* c306 */
			dto.setC306(rs.getString("c306"));

			/* c307 */
			dto.setC307(rs.getString("c307"));

			/* c308 */
			dto.setC308(rs.getString("c308"));

			/* c309 */
			dto.setC309(rs.getString("c309"));

			/* c310 */
			dto.setC310(rs.getString("c310"));

			/* c311 */
			dto.setC311(rs.getString("c311"));

			/* c312 */
			dto.setC312(rs.getString("c312"));

			/* c313 */
			dto.setC313(rs.getString("c313"));

			/* c314 */
			dto.setC314(rs.getString("c314"));

			/* c315 */
			dto.setC315(rs.getString("c315"));

			/* c316 */
			dto.setC316(rs.getString("c316"));

			/* c317 */
			dto.setC317(rs.getString("c317"));

			/* c318 */
			dto.setC318(rs.getString("c318"));

			/* c319 */
			dto.setC319(rs.getString("c319"));

			/* c320 */
			dto.setC320(rs.getString("c320"));

			/* c321 */
			dto.setC321(rs.getString("c321"));

			/* c322 */
			dto.setC322(rs.getString("c322"));

			/* c323 */
			dto.setC323(rs.getString("c323"));

			/* c324 */
			dto.setC324(rs.getString("c324"));

			/* c325 */
			dto.setC325(rs.getString("c325"));

			/* c326 */
			dto.setC326(rs.getString("c326"));

			/* c327 */
			dto.setC327(rs.getString("c327"));

			/* c328 */
			dto.setC328(rs.getString("c328"));

			/* c329 */
			dto.setC329(rs.getString("c329"));

			/* c330 */
			dto.setC330(rs.getString("c330"));

			/* c331 */
			dto.setC331(rs.getString("c331"));

			/* c332 */
			dto.setC332(rs.getString("c332"));

			/* c333 */
			dto.setC333(rs.getString("c333"));

			/* c334 */
			dto.setC334(rs.getString("c334"));

			/* c335 */
			dto.setC335(rs.getString("c335"));

			/* c336 */
			dto.setC336(rs.getString("c336"));

			/* c337 */
			dto.setC337(rs.getString("c337"));

			/* c338 */
			dto.setC338(rs.getString("c338"));

			/* c339 */
			dto.setC339(rs.getString("c339"));

			/* c340 */
			dto.setC340(rs.getString("c340"));

			/* c341 */
			dto.setC341(rs.getString("c341"));

			/* c342 */
			dto.setC342(rs.getString("c342"));

			/* c343 */
			dto.setC343(rs.getString("c343"));

			/* c344 */
			dto.setC344(rs.getString("c344"));

			/* c345 */
			dto.setC345(rs.getString("c345"));

			/* c346 */
			dto.setC346(rs.getString("c346"));

			/* c347 */
			dto.setC347(rs.getString("c347"));

			/* c348 */
			dto.setC348(rs.getString("c348"));

			/* c349 */
			dto.setC349(rs.getString("c349"));

			/* c350 */
			dto.setC350(rs.getString("c350"));

			/* c351 */
			dto.setC351(rs.getString("c351"));

			/* c352 */
			dto.setC352(rs.getString("c352"));

			/* c353 */
			dto.setC353(rs.getString("c353"));

			/* c354 */
			dto.setC354(rs.getString("c354"));

			/* c355 */
			dto.setC355(rs.getString("c355"));

			/* c356 */
			dto.setC356(rs.getString("c356"));

			/* c357 */
			dto.setC357(rs.getString("c357"));

			/* c358 */
			dto.setC358(rs.getString("c358"));

			/* c359 */
			dto.setC359(rs.getString("c359"));

			/* c360 */
			dto.setC360(rs.getString("c360"));

			/* c361 */
			dto.setC361(rs.getString("c361"));

			/* c362 */
			dto.setC362(rs.getString("c362"));

			/* c363 */
			dto.setC363(rs.getString("c363"));

			/* c364 */
			dto.setC364(rs.getString("c364"));

			/* c365 */
			dto.setC365(rs.getString("c365"));

			/* c366 */
			dto.setC366(rs.getString("c366"));

			/* c367 */
			dto.setC367(rs.getString("c367"));

			/* c368 */
			dto.setC368(rs.getString("c368"));

			/* c369 */
			dto.setC369(rs.getString("c369"));

			/* c370 */
			dto.setC370(rs.getString("c370"));

			/* c371 */
			dto.setC371(rs.getString("c371"));

			/* c372 */
			dto.setC372(rs.getString("c372"));

			/* c373 */
			dto.setC373(rs.getString("c373"));

			/* c374 */
			dto.setC374(rs.getString("c374"));

			/* c375 */
			dto.setC375(rs.getString("c375"));

			/* c376 */
			dto.setC376(rs.getString("c376"));

			/* c377 */
			dto.setC377(rs.getString("c377"));

			/* c378 */
			dto.setC378(rs.getString("c378"));

			/* c379 */
			dto.setC379(rs.getString("c379"));

			/* c380 */
			dto.setC380(rs.getString("c380"));

			/* c381 */
			dto.setC381(rs.getString("c381"));

			/* c382 */
			dto.setC382(rs.getString("c382"));

			/* c383 */
			dto.setC383(rs.getString("c383"));

			/* c384 */
			dto.setC384(rs.getString("c384"));

			/* c385 */
			dto.setC385(rs.getString("c385"));

			/* c386 */
			dto.setC386(rs.getString("c386"));

			/* c387 */
			dto.setC387(rs.getString("c387"));

			/* c388 */
			dto.setC388(rs.getString("c388"));

			/* c389 */
			dto.setC389(rs.getString("c389"));

			/* c390 */
			dto.setC390(rs.getString("c390"));

			/* c391 */
			dto.setC391(rs.getString("c391"));

			/* c392 */
			dto.setC392(rs.getString("c392"));

			/* c393 */
			dto.setC393(rs.getString("c393"));

			/* c394 */
			dto.setC394(rs.getString("c394"));

			/* c395 */
			dto.setC395(rs.getString("c395"));

			/* c396 */
			dto.setC396(rs.getString("c396"));

			/* c397 */
			dto.setC397(rs.getString("c397"));

			/* c398 */
			dto.setC398(rs.getString("c398"));

			/* c399 */
			dto.setC399(rs.getString("c399"));

			/* c400 */
			dto.setC400(rs.getString("c400"));

			/* c401 */
			dto.setC401(rs.getString("c401"));

			/* c402 */
			dto.setC402(rs.getString("c402"));

			/* c403 */
			dto.setC403(rs.getString("c403"));

			/* c404 */
			dto.setC404(rs.getString("c404"));

			/* c405 */
			dto.setC405(rs.getString("c405"));

			/* c406 */
			dto.setC406(rs.getString("c406"));

			/* c407 */
			dto.setC407(rs.getString("c407"));

			/* c408 */
			dto.setC408(rs.getString("c408"));

			/* c409 */
			dto.setC409(rs.getString("c409"));

			/* c410 */
			dto.setC410(rs.getString("c410"));

			/* c411 */
			dto.setC411(rs.getString("c411"));

			/* c412 */
			dto.setC412(rs.getString("c412"));

			/* c413 */
			dto.setC413(rs.getString("c413"));

			/* c414 */
			dto.setC414(rs.getString("c414"));

			/* c415 */
			dto.setC415(rs.getString("c415"));

			/* c416 */
			dto.setC416(rs.getString("c416"));

			/* c417 */
			dto.setC417(rs.getString("c417"));

			/* c418 */
			dto.setC418(rs.getString("c418"));

			/* c419 */
			dto.setC419(rs.getString("c419"));

			/* c420 */
			dto.setC420(rs.getString("c420"));

			/* c421 */
			dto.setC421(rs.getString("c421"));

			/* c422 */
			dto.setC422(rs.getString("c422"));

			/* c423 */
			dto.setC423(rs.getString("c423"));

			/* c424 */
			dto.setC424(rs.getString("c424"));

			/* c425 */
			dto.setC425(rs.getString("c425"));

			/* c426 */
			dto.setC426(rs.getString("c426"));

			/* c427 */
			dto.setC427(rs.getString("c427"));

			/* c428 */
			dto.setC428(rs.getString("c428"));

			/* c429 */
			dto.setC429(rs.getString("c429"));

			/* c430 */
			dto.setC430(rs.getString("c430"));

			/* c431 */
			dto.setC431(rs.getString("c431"));

			/* c432 */
			dto.setC432(rs.getString("c432"));

			/* c433 */
			dto.setC433(rs.getString("c433"));

			/* c434 */
			dto.setC434(rs.getString("c434"));

			/* c435 */
			dto.setC435(rs.getString("c435"));

			/* c436 */
			dto.setC436(rs.getString("c436"));

			/* c437 */
			dto.setC437(rs.getString("c437"));

			/* c438 */
			dto.setC438(rs.getString("c438"));

			/* c439 */
			dto.setC439(rs.getString("c439"));

			/* c440 */
			dto.setC440(rs.getString("c440"));

			/* c441 */
			dto.setC441(rs.getString("c441"));

			/* c442 */
			dto.setC442(rs.getString("c442"));

			/* c443 */
			dto.setC443(rs.getString("c443"));

			/* c444 */
			dto.setC444(rs.getString("c444"));

			/* c445 */
			dto.setC445(rs.getString("c445"));

			/* c446 */
			dto.setC446(rs.getString("c446"));

			/* c447 */
			dto.setC447(rs.getString("c447"));

			/* c448 */
			dto.setC448(rs.getString("c448"));

			/* c449 */
			dto.setC449(rs.getString("c449"));

			/* c450 */
			dto.setC450(rs.getString("c450"));

			/* c451 */
			dto.setC451(rs.getString("c451"));

			/* c452 */
			dto.setC452(rs.getString("c452"));

			/* c453 */
			dto.setC453(rs.getString("c453"));

			/* c454 */
			dto.setC454(rs.getString("c454"));

			/* c455 */
			dto.setC455(rs.getString("c455"));

			/* c456 */
			dto.setC456(rs.getString("c456"));

			/* c457 */
			dto.setC457(rs.getString("c457"));

			/* c458 */
			dto.setC458(rs.getString("c458"));

			/* c459 */
			dto.setC459(rs.getString("c459"));

			/* c460 */
			dto.setC460(rs.getString("c460"));

			/* c461 */
			dto.setC461(rs.getString("c461"));

			/* c462 */
			dto.setC462(rs.getString("c462"));

			/* c463 */
			dto.setC463(rs.getString("c463"));

			/* c464 */
			dto.setC464(rs.getString("c464"));

			/* c465 */
			dto.setC465(rs.getString("c465"));

			/* c466 */
			dto.setC466(rs.getString("c466"));

			/* c467 */
			dto.setC467(rs.getString("c467"));

			/* c468 */
			dto.setC468(rs.getString("c468"));

			/* c469 */
			dto.setC469(rs.getString("c469"));

			/* c470 */
			dto.setC470(rs.getString("c470"));

			/* c471 */
			dto.setC471(rs.getString("c471"));

			/* c472 */
			dto.setC472(rs.getString("c472"));

			/* c473 */
			dto.setC473(rs.getString("c473"));

			/* c474 */
			dto.setC474(rs.getString("c474"));

			/* c475 */
			dto.setC475(rs.getString("c475"));

			/* c476 */
			dto.setC476(rs.getString("c476"));

			/* c477 */
			dto.setC477(rs.getString("c477"));

			/* c478 */
			dto.setC478(rs.getString("c478"));

			/* c479 */
			dto.setC479(rs.getString("c479"));

			/* c480 */
			dto.setC480(rs.getString("c480"));

			/* c481 */
			dto.setC481(rs.getString("c481"));

			/* c482 */
			dto.setC482(rs.getString("c482"));

			/* c483 */
			dto.setC483(rs.getString("c483"));

			/* c484 */
			dto.setC484(rs.getString("c484"));

			/* c485 */
			dto.setC485(rs.getString("c485"));

			/* c486 */
			dto.setC486(rs.getString("c486"));

			/* c487 */
			dto.setC487(rs.getString("c487"));

			/* c488 */
			dto.setC488(rs.getString("c488"));

			/* c489 */
			dto.setC489(rs.getString("c489"));

			/* c490 */
			dto.setC490(rs.getString("c490"));

			/* c491 */
			dto.setC491(rs.getString("c491"));

			/* c492 */
			dto.setC492(rs.getString("c492"));

			/* c493 */
			dto.setC493(rs.getString("c493"));

			/* c494 */
			dto.setC494(rs.getString("c494"));

			/* c495 */
			dto.setC495(rs.getString("c495"));

			/* c496 */
			dto.setC496(rs.getString("c496"));

			/* c497 */
			dto.setC497(rs.getString("c497"));

			/* c498 */
			dto.setC498(rs.getString("c498"));

			/* c499 */
			dto.setC499(rs.getString("c499"));

			/* c500 */
			dto.setC500(rs.getString("c500"));

			return dto;
		}
	}

}
