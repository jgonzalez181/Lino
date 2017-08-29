
-- ===================================================================================================

-- SELECT DISTINCT LENGTH(TRIM(c2)) FROM cclip.aacc_inmueble OFFSET '0' LIMIT '100'
-- SELECT COUNT(*) FROM cclip.aacc_inmueble WHERE c2 IS NULL OR LENGTH(TRIM(c2)) = 0 --0
-- SELECT COUNT(*) FROM cclip.aacc_inmueble WHERE LENGTH(TRIM(c2)) = 6 --349501


DROP VIEW IF EXISTS cclip.v_aacc_inmueble_header_sin_uf CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_inmueble_header_sin_uf AS 

	SELECT 	id,
		(COALESCE(aacc_inmueble.c12, '') || COALESCE(aacc_inmueble.c13, '') || COALESCE(aacc_inmueble.c14,'') || '000')::VARCHAR AS cadastral_code, 
		'000'::VARCHAR AS sub_cta, 
		null::VARCHAR AS uf_id,
		TRIM(aacc_inmueble.c2)::VARCHAR AS cta_cli, 
		TRIM(aacc_inmueble.c1)::VARCHAR AS city_area_id, 		
		TRIM(aacc_inmueble.c3)::VARCHAR AS dv,
		TRIM(aacc_inmueble.c5)::VARCHAR AS cadastre_type_id

	FROM 	cclip.aacc_inmueble 	
	WHERE	aacc_inmueble.c12 IS NOT NULL
		AND aacc_inmueble.c13 IS NOT NULL
		AND aacc_inmueble.c14 IS NOT NULL
		AND aacc_inmueble.c2 IS NOT NULL
		AND aacc_inmueble.c1 IS NOT NULL

		AND LENGTH(TRIM(aacc_inmueble.c12)) = 4
		AND LENGTH(TRIM(aacc_inmueble.c13)) = 3
		AND LENGTH(TRIM(aacc_inmueble.c14)) = 3
		AND LENGTH(TRIM(aacc_inmueble.c2)) = 6

		AND cclip.is_integer(TRIM(aacc_inmueble.c12)) = true
		AND cclip.is_integer(TRIM(aacc_inmueble.c13)) = true
		AND cclip.is_integer(TRIM(aacc_inmueble.c14)) = true
		AND cclip.is_integer(TRIM(aacc_inmueble.c2)) = true
		
		AND (TRIM(aacc_inmueble.c1) = '322' OR TRIM(aacc_inmueble.c1) = '321')
		
	ORDER BY 	aacc_inmueble.c12 ASC, 
			aacc_inmueble.c13 ASC, 
			aacc_inmueble.c14 ASC;


--  SELECT * FROM cclip.v_aacc_inmueble_header_sin_uf OFFSET '0' LIMIT '100';

--  SELECT COUNT(*) FROM cclip.v_aacc_inmueble_header_sin_uf; --349501
--  SELECT COUNT(*) FROM cclip.aacc_inmueble; -- 349501

-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_inmueble_header_sin_uf CASCADE;

CREATE TABLE cclip.aacc_inmueble_header_sin_uf
(
	id character varying NOT NULL,
	cadastral_code character varying NOT NULL,
	sub_cta character varying NOT NULL,
	uf_id character varying,
	cta_cli character varying NOT NULL,
	city_area_id character varying NOT NULL,	
	dv character varying,
	cadastre_type_id character varying NOT NULL
);

 -- ===================================================================================================

INSERT INTO cclip.aacc_inmueble_header_sin_uf (SELECT * FROM  cclip.v_aacc_inmueble_header_sin_uf);

-- SELECT * FROM  cclip.aacc_inmueble_header_sin_uf OFFSET '0' LIMIT '100';	--0 ms

-- SELECT COUNT(*) FROM  cclip.aacc_inmueble_header_sin_uf; -- 349501

-- ===================================================================================================


-- SELECT * FROM cclip.aacc_unidad WHERE c1 = '200078' 

-- SELECT c70, * FROM cclip.aacc_unidad OFFSET '0' LIMIT '100'
-- SELECT DISTINCT c70 FROM cclip.aacc_unidad OFFSET '0' LIMIT '100'
-- SELECT DISTINCT c71 FROM cclip.aacc_unidad OFFSET '0' LIMIT '100'
-- SELECT DISTINCT c5 FROM cclip.aacc_unidad OFFSET '0' LIMIT '100'
-- SELECT DISTINCT c4 FROM cclip.aacc_unidad OFFSET '0' LIMIT '100'
-- SELECT DISTINCT c16 FROM cclip.aacc_unidad OFFSET '0' LIMIT '100'
-- SELECT DISTINCT c90 FROM cclip.aacc_unidad ORDER BY c90 OFFSET '0' LIMIT '100'
-- SELECT DISTINCT c15 FROM cclip.aacc_unidad ORDER BY c15 OFFSET '0' LIMIT '100'

-- SELECT DISTINCT LENGTH(TRIM(c4)) FROM cclip.aacc_unidad OFFSET '0' LIMIT '100'
-- SELECT COUNT(*) FROM cclip.aacc_unidad WHERE c4 IS NULL OR LENGTH(TRIM(c4)) = 0 --68308
-- SELECT COUNT(*) FROM cclip.aacc_unidad WHERE c4 IS NOT NULL OR LENGTH(TRIM(c4)) <> 0 --463621
-- SELECT COUNT(*) FROM cclip.aacc_unidad WHERE LENGTH(TRIM(c4)) = 6 --463619
-- SELECT COUNT(*) FROM cclip.aacc_unidad WHERE LENGTH(TRIM(c4)) = 2 --2

-- SELECT DISTINCT LENGTH(TRIM(c74)) FROM cclip.aacc_unidad OFFSET '0' LIMIT '100'
-- SELECT COUNT(*) FROM cclip.aacc_unidad WHERE c74 IS NULL OR LENGTH(TRIM(c74)) = 0 --4
-- SELECT COUNT(*) FROM cclip.aacc_unidad WHERE c74 IS NOT NULL OR LENGTH(TRIM(c74)) <> 0 --531925
-- SELECT COUNT(*) FROM cclip.aacc_unidad WHERE c74 IS NOT NULL OR LENGTH(TRIM(c74)) = 6 --531925


DROP VIEW IF EXISTS cclip.v_aacc_unidad_header CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_unidad_header AS 

	SELECT 	id,
		(COALESCE(aacc_unidad.c13, '') || COALESCE(aacc_unidad.c14, '') || SUBSTRING(COALESCE(aacc_unidad.c15,'') FROM 0 FOR 4) || COALESCE(aacc_unidad.c90,''))::VARCHAR AS cadastral_code, 
		--SUBSTRING(COALESCE(aacc_unidad.c15,'') FROM 0 FOR 4) AS ppp,
		COALESCE(aacc_unidad.c90,'') AS sub_cta,
		--COALESCE(aacc_unidad.c15,'') AS cc,		
		TRIM(aacc_unidad.c1)::VARCHAR AS uf_id,		
		TRIM(aacc_unidad.c74)::VARCHAR AS cta_cli, 
		TRIM(aacc_unidad.c5)::VARCHAR AS city_area_id
		

	FROM 	cclip.aacc_unidad 	
	WHERE	aacc_unidad.c13 IS NOT NULL
		AND aacc_unidad.c14 IS NOT NULL
		AND aacc_unidad.c15 IS NOT NULL
		AND aacc_unidad.c74 IS NOT NULL
		AND aacc_unidad.c5 IS NOT NULL
		AND aacc_unidad.c90 IS NOT NULL

		AND aacc_unidad.c1 IS NOT NULL
		AND LENGTH(TRIM(aacc_unidad.c1)) <> 0

		AND LENGTH(TRIM(aacc_unidad.c13)) = 4
		AND LENGTH(TRIM(aacc_unidad.c14)) = 3
		AND LENGTH(TRIM(aacc_unidad.c15)) >= 3
		AND LENGTH(TRIM(aacc_unidad.c74)) = 6
		AND LENGTH(TRIM(aacc_unidad.c90)) = 3

		AND cclip.is_integer(TRIM(aacc_unidad.c13)) = true
		AND cclip.is_integer(TRIM(aacc_unidad.c14)) = true
		AND cclip.is_integer(SUBSTRING(COALESCE(aacc_unidad.c15,'') FROM 0 FOR 4)) = true
		AND cclip.is_integer(TRIM(aacc_unidad.c74)) = true
		AND cclip.is_integer(TRIM(aacc_unidad.c90)) = true
		
		AND (TRIM(aacc_unidad.c5) = '322' OR TRIM(aacc_unidad.c5) = '321')
		
	ORDER BY 	aacc_unidad.c13 ASC, 
			aacc_unidad.c14 ASC, 
			aacc_unidad.c15 ASC,
			aacc_unidad.c1 ASC;

 
 -- SELECT * FROM cclip.v_aacc_unidad_header OFFSET '0' LIMIT '100';

-- SELECT * FROM cclip.v_aacc_unidad_header WHERE cta_cli = '334576' AND city_area_id = '322';
-- SELECT * FROM cclip.v_aacc_unidad_header WHERE cta_cli = '233285' AND city_area_id = '321';

-- SELECT COUNT(*) FROM cclip.v_aacc_unidad_header;
-- SELECT COUNT(*) FROM cclip.aacc_unidad;

-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_unidad_header CASCADE;

CREATE TABLE cclip.aacc_unidad_header
(
	id character varying NOT NULL,
	cadastral_code character varying NOT NULL,
	sub_cta character varying NOT NULL,
	uf_id character varying,
	cta_cli character varying NOT NULL,
	city_area_id character varying NOT NULL	
);


 -- ===================================================================================================

INSERT INTO cclip.aacc_unidad_header (SELECT * FROM  cclip.v_aacc_unidad_header);

-- SELECT * FROM  cclip.aacc_unidad_header OFFSET '0' LIMIT '100';	--0 ms

-- SELECT COUNT(*) FROM  cclip.aacc_unidad_header; -- 495994


-- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_aacc_unidad_header_repe CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_unidad_header_repe AS 

	SELECT 	cadastral_code, 
		cta_cli, 
		city_area_id, 
		COUNT(*) 
	FROM 	cclip.aacc_unidad_header 
	GROUP BY 	cadastral_code, 
			cta_cli, 
			city_area_id 
	HAVING COUNT(*) > 1 ;

 -- SELECT * FROM cclip.v_aacc_unidad_header_repe OFFSET '0' LIMIT '100';
--  SELECT * FROM cclip.v_aacc_unidad_header WHERE cta_cli = '278297' AND city_area_id = '322' AND cadastral_code = '1216001004046';

 -- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_aacc_unidad_header_repe_desc CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_unidad_header_repe_desc AS 

	SELECT 	aacc_unidad_header.*
	FROM 	cclip.aacc_unidad_header 
	JOIN	cclip.v_aacc_unidad_header_repe
		ON aacc_unidad_header.cadastral_code = v_aacc_unidad_header_repe.cadastral_code	
		AND aacc_unidad_header.cta_cli = v_aacc_unidad_header_repe.cta_cli	
		AND aacc_unidad_header.city_area_id = v_aacc_unidad_header_repe.city_area_id;		

 -- SELECT * FROM cclip.v_aacc_unidad_header_repe_desc OFFSET '0' LIMIT '100';

-- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_aacc_inmueble_header CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_inmueble_header AS 

	SELECT 	aacc_inmueble_header_sin_uf.id,
		aacc_inmueble_header_sin_uf.cadastral_code,
		aacc_inmueble_header_sin_uf.sub_cta,
		aacc_unidad_header.uf_id,	
		aacc_inmueble_header_sin_uf.cta_cli,
		aacc_inmueble_header_sin_uf.city_area_id,		
		aacc_inmueble_header_sin_uf.dv,
		aacc_inmueble_header_sin_uf.cadastre_type_id
	FROM 	cclip.aacc_inmueble_header_sin_uf
	JOIN	cclip.aacc_unidad_header
		ON aacc_inmueble_header_sin_uf.cadastral_code = aacc_unidad_header.cadastral_code	
		AND aacc_inmueble_header_sin_uf.cta_cli = aacc_unidad_header.cta_cli	
		AND aacc_inmueble_header_sin_uf.city_area_id = aacc_unidad_header.city_area_id;	

--  SELECT * FROM cclip.v_aacc_inmueble_header OFFSET '0' LIMIT '100';

-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_inmueble_header CASCADE;

CREATE TABLE cclip.aacc_inmueble_header
(
	id character varying NOT NULL,
	cadastral_code character varying NOT NULL,
	sub_cta character varying NOT NULL,
	uf_id character varying NOT NULL,
	cta_cli character varying NOT NULL,
	city_area_id character varying NOT NULL,	
	dv character varying,
	cadastre_type_id character varying NOT NULL
);

 -- ===================================================================================================

INSERT INTO cclip.aacc_inmueble_header (SELECT * FROM  cclip.v_aacc_inmueble_header);

-- SELECT * FROM  cclip.aacc_inmueble_header OFFSET '0' LIMIT '100';	

-- SELECT COUNT(*) FROM  cclip.aacc_inmueble_header; -- 311888


-- ===================================================================================================

-- SELECT * FROM cclip.aacc_subcuenta OFFSET '0' LIMIT '100'

DROP VIEW IF EXISTS cclip.v_aacc_subcuenta_header_sin_cadastral_code_prefix CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_subcuenta_header_sin_cadastral_code_prefix AS 

	SELECT 	aacc_subcuenta.id,
		(COALESCE(aacc_subcuenta.c18, '') )::VARCHAR AS sub_cta, 	
		TRIM(aacc_subcuenta.c27)::VARCHAR AS uf_id,
		TRIM(aacc_subcuenta.c2)::VARCHAR AS cta_cli, 
		TRIM(aacc_subcuenta.c1)::VARCHAR AS city_area_id, 				
		TRIM(aacc_subcuenta.c4)::VARCHAR AS dv,
		'PH'::VARCHAR AS cadastre_type_id

	FROM 	cclip.aacc_subcuenta 	
	WHERE	aacc_subcuenta.c18 IS NOT NULL
		AND aacc_subcuenta.c27 IS NOT NULL
		AND aacc_subcuenta.c2 IS NOT NULL
		AND aacc_subcuenta.c1 IS NOT NULL		

		
		AND LENGTH(TRIM(aacc_subcuenta.c18)) = 3
		AND LENGTH(TRIM(aacc_subcuenta.c2)) = 6

		
		AND cclip.is_integer(TRIM(aacc_subcuenta.c18)) = true
		AND cclip.is_integer(TRIM(aacc_subcuenta.c2)) = true
		
		AND (TRIM(aacc_subcuenta.c1) = '322' OR TRIM(aacc_subcuenta.c1) = '321')
		
	ORDER BY 	aacc_subcuenta.c2 ASC, 
			aacc_subcuenta.c1 ASC, 
			aacc_subcuenta.c18 ASC;

--  SELECT * FROM cclip.v_aacc_subcuenta_header_sin_cadastral_code_prefix OFFSET '0' LIMIT '100';			

-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_subcuenta_header_sin_cadastral_code_prefix CASCADE;

CREATE TABLE cclip.aacc_subcuenta_header_sin_cadastral_code_prefix
(	
	id character varying NOT NULL,
	sub_cta character varying NOT NULL,
	uf_id character varying NOT NULL,
	cta_cli character varying NOT NULL,
	city_area_id character varying NOT NULL,	
	dv character varying,
	cadastre_type_id character varying NOT NULL
);

 -- ===================================================================================================

INSERT INTO cclip.aacc_subcuenta_header_sin_cadastral_code_prefix (SELECT * FROM  cclip.v_aacc_subcuenta_header_sin_cadastral_code_prefix);

-- SELECT * FROM  cclip.aacc_subcuenta_header_sin_cadastral_code_prefix OFFSET '0' LIMIT '100';	

-- SELECT COUNT(*) FROM  cclip.aacc_subcuenta_header_sin_cadastral_code_prefix; -- 493353


-- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_aacc_subcuenta_header CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_subcuenta_header AS 

	SELECT 	aacc_subcuenta_header_sin_cadastral_code_prefix.id,
		aacc_unidad_header.cadastral_code,
		aacc_subcuenta_header_sin_cadastral_code_prefix.sub_cta,
		aacc_subcuenta_header_sin_cadastral_code_prefix.uf_id,	
		aacc_subcuenta_header_sin_cadastral_code_prefix.cta_cli,
		aacc_subcuenta_header_sin_cadastral_code_prefix.city_area_id,
		aacc_subcuenta_header_sin_cadastral_code_prefix.dv,
		aacc_subcuenta_header_sin_cadastral_code_prefix.cadastre_type_id	
	
	FROM 	cclip.aacc_subcuenta_header_sin_cadastral_code_prefix
	JOIN	cclip.aacc_unidad_header
		ON aacc_subcuenta_header_sin_cadastral_code_prefix.sub_cta = aacc_unidad_header.sub_cta	
		AND aacc_subcuenta_header_sin_cadastral_code_prefix.cta_cli = aacc_unidad_header.cta_cli	
		AND aacc_subcuenta_header_sin_cadastral_code_prefix.city_area_id = aacc_unidad_header.city_area_id
		AND aacc_subcuenta_header_sin_cadastral_code_prefix.uf_id = aacc_unidad_header.uf_id;	

--  SELECT * FROM cclip.v_aacc_subcuenta_header OFFSET '0' LIMIT '100';
--  SELECT * FROM cclip.v_aacc_subcuenta_header WHERE sub_cta <> '000' OFFSET '0' LIMIT '100';


-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_subcuenta_header CASCADE;

CREATE TABLE cclip.aacc_subcuenta_header
(	
	id character varying NOT NULL,
	cadastral_code character varying NOT NULL,
	sub_cta character varying NOT NULL,
	uf_id character varying NOT NULL,
	cta_cli character varying NOT NULL,
	city_area_id character varying NOT NULL,	
	dv character varying,
	cadastre_type_id character varying NOT NULL
);

 -- ===================================================================================================

INSERT INTO cclip.aacc_subcuenta_header (SELECT * FROM  cclip.v_aacc_subcuenta_header);

-- SELECT * FROM  cclip.aacc_subcuenta_header OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.aacc_subcuenta_header; -- 491300

  -- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_aacc_inmueble_subcuenta_header CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_inmueble_subcuenta_header AS 

	(
		SELECT * FROM cclip.aacc_inmueble_header
		UNION ALL 
		SELECT * FROM cclip.aacc_subcuenta_header
	) ORDER BY cadastral_code, uf_id, cta_cli, city_area_id;

-- SELECT * FROM  cclip.v_aacc_inmueble_subcuenta_header OFFSET '0' LIMIT '100';	

-- SELECT COUNT(*) FROM  cclip.v_aacc_inmueble_subcuenta_header; -- 803188

-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_inmueble_subcuenta_header CASCADE;

CREATE TABLE cclip.aacc_inmueble_subcuenta_header
(
	id character varying NOT NULL,
	cadastral_code character varying NOT NULL,
	sub_cta character varying NOT NULL,
	uf_id character varying NOT NULL,
	cta_cli character varying NOT NULL,
	city_area_id character varying NOT NULL,	
	dv character varying,
	cadastre_type_id character varying NOT NULL
);

 -- ===================================================================================================

INSERT INTO cclip.aacc_inmueble_subcuenta_header (SELECT * FROM  cclip.v_aacc_inmueble_subcuenta_header);

-- SELECT * FROM  cclip.aacc_inmueble_subcuenta_header OFFSET '0' LIMIT '100';	--0 ms

-- SELECT COUNT(*) FROM  cclip.aacc_inmueble_subcuenta_header; -- 803188


-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_cadastre_header CASCADE;

CREATE TABLE cclip.aacc_cadastre_header
(
	cadastral_code character varying NOT NULL
);

 -- ===================================================================================================

INSERT INTO cclip.aacc_cadastre_header (SELECT DISTINCT cadastral_code FROM  cclip.aacc_inmueble_subcuenta_header ORDER BY cadastral_code);

-- SELECT * FROM  cclip.aacc_cadastre_header OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.aacc_cadastre_header; -- 477344

 -- ===================================================================================================
/*
DROP VIEW IF EXISTS cclip.v_aacc_cadastre_header CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_cadastre_header AS 	

	SELECT DISTINCT cadastral_code, sub_cta, uf_id, cta_cli, city_area_id FROM cclip.aacc_inmueble_subcuenta_header;

-- SELECT * FROM cclip.v_aacc_cadastre_header OFFSET '0' LIMIT '100';	
*/
 -- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_aacc_cadastre_header_new_case CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_cadastre_header_new_case AS 	

	SELECT cadastral_code FROM cclip.aacc_cadastre_header WHERE cadastral_code::VARCHAR NOT IN (SELECT cadastral_code::VARCHAR FROM cclip.cadastre);

-- SELECT * FROM cclip.v_aacc_cadastre_header_new_case OFFSET '0' LIMIT '100';	

-- SELECT COUNT(*) FROM  cclip.v_aacc_cadastre_header_new_case; -- 80015

-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_cadastre_header_new_case CASCADE;

CREATE TABLE cclip.aacc_cadastre_header_new_case
(
	cadastral_code character varying NOT NULL
);

-- ===================================================================================================

INSERT INTO cclip.aacc_cadastre_header_new_case (SELECT cadastral_code FROM  cclip.v_aacc_cadastre_header_new_case ORDER BY cadastral_code);

-- SELECT * FROM  cclip.aacc_cadastre_header_new_case OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.aacc_cadastre_header_new_case; -- 80015

-- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_aacc_unidad_header_new_case CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_unidad_header_new_case AS 

	SELECT * FROM cclip.aacc_unidad_header WHERE cadastral_code::VARCHAR IN (SELECT cadastral_code::VARCHAR FROM cclip.aacc_cadastre_header_new_case);

-- SELECT * FROM  cclip.v_aacc_unidad_header_new_case OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.v_aacc_unidad_header_new_case; -- 84207


-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_unidad_header_new_case CASCADE;

CREATE TABLE cclip.aacc_unidad_header_new_case
(
	id character varying NOT NULL,
	cadastral_code character varying NOT NULL,
	sub_cta character varying NOT NULL,
	uf_id character varying,
	cta_cli character varying NOT NULL,
	city_area_id character varying NOT NULL	
);

-- ===================================================================================================

INSERT INTO cclip.aacc_unidad_header_new_case (SELECT * FROM  cclip.v_aacc_unidad_header_new_case ORDER BY cadastral_code);

-- SELECT * FROM  cclip.aacc_unidad_header_new_case OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.aacc_unidad_header_new_case; -- 84207

-- ===================================================================================================


DROP VIEW IF EXISTS cclip.v_aacc_inmueble_header_new_case CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_inmueble_header_new_case AS 

	SELECT * FROM cclip.aacc_inmueble_header WHERE cadastral_code::VARCHAR IN (SELECT cadastral_code::VARCHAR FROM cclip.aacc_cadastre_header_new_case);

-- SELECT * FROM  cclip.v_aacc_inmueble_header_new_case OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.v_aacc_inmueble_header_new_case; -- 28388

-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_inmueble_header_new_case CASCADE;

CREATE TABLE cclip.aacc_inmueble_header_new_case
(
	id character varying NOT NULL,
	cadastral_code character varying NOT NULL,
	sub_cta character varying NOT NULL,
	uf_id character varying NOT NULL,
	cta_cli character varying NOT NULL,
	city_area_id character varying NOT NULL,	
	dv character varying,
	cadastre_type_id character varying NOT NULL
);

-- ===================================================================================================

INSERT INTO cclip.aacc_inmueble_header_new_case (SELECT * FROM  cclip.v_aacc_inmueble_header_new_case ORDER BY cadastral_code);

-- SELECT * FROM  cclip.aacc_inmueble_header_new_case OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.aacc_inmueble_header_new_case; -- 28388

-- ===================================================================================================


DROP VIEW IF EXISTS cclip.v_aacc_subcuenta_header_new_case CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_subcuenta_header_new_case AS 

	SELECT * FROM cclip.aacc_subcuenta_header WHERE cadastral_code::VARCHAR IN (SELECT cadastral_code::VARCHAR FROM cclip.aacc_cadastre_header_new_case);

-- SELECT * FROM  cclip.v_aacc_subcuenta_header_new_case OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.v_aacc_subcuenta_header_new_case; -- 84165

-- ===================================================================================================

DROP TABLE IF EXISTS  cclip.aacc_subcuenta_header_new_case CASCADE;

CREATE TABLE cclip.aacc_subcuenta_header_new_case
(
	id character varying NOT NULL,
	cadastral_code character varying NOT NULL,
	sub_cta character varying NOT NULL,
	uf_id character varying NOT NULL,
	cta_cli character varying NOT NULL,
	city_area_id character varying NOT NULL,	
	dv character varying,
	cadastre_type_id character varying NOT NULL
);

-- ===================================================================================================

INSERT INTO cclip.aacc_subcuenta_header_new_case (SELECT * FROM  cclip.v_aacc_subcuenta_header_new_case ORDER BY cadastral_code);

-- SELECT * FROM  cclip.aacc_subcuenta_header_new_case OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.aacc_subcuenta_header_new_case; -- 84165

-- ===================================================================================================



----------------------------------------------------------------------------------------

-- TABLA: UF de AACC

DROP TABLE IF EXISTS cclip.aacc_unidad_new_case CASCADE;
CREATE TABLE cclip.aacc_unidad_new_case
(
	id varchar NOT NULL, -- id
	erased boolean NOT NULL, -- erased
	audit_date_create timestamp NOT NULL, -- auditDateCreate
	audit_user_create varchar NOT NULL, -- auditUserCreate
	c1 varchar, -- c1
	c2 varchar, -- c2
	c3 varchar, -- c3
	c4 varchar, -- c4
	c5 varchar, -- c5
	c6 varchar, -- c6
	c7 varchar, -- c7
	c8 varchar, -- c8
	c9 varchar, -- c9
	c10 varchar, -- c10
	c11 varchar, -- c11
	c12 varchar, -- c12
	c13 varchar, -- c13
	c14 varchar, -- c14
	c15 varchar, -- c15
	c16 varchar, -- c16
	c17 varchar, -- c17
	c18 varchar, -- c18
	c19 varchar, -- c19
	c20 varchar, -- c20
	c21 varchar, -- c21
	c22 varchar, -- c22
	c23 varchar, -- c23
	c24 varchar, -- c24
	c25 varchar, -- c25
	c26 varchar, -- c26
	c27 varchar, -- c27
	c28 varchar, -- c28
	c29 varchar, -- c29
	c30 varchar, -- c30
	c31 varchar, -- c31
	c32 varchar, -- c32
	c33 varchar, -- c33
	c34 varchar, -- c34
	c35 varchar, -- c35
	c36 varchar, -- c36
	c37 varchar, -- c37
	c38 varchar, -- c38
	c39 varchar, -- c39
	c40 varchar, -- c40
	c41 varchar, -- c41
	c42 varchar, -- c42
	c43 varchar, -- c43
	c44 varchar, -- c44
	c45 varchar, -- c45
	c46 varchar, -- c46
	c47 varchar, -- c47
	c48 varchar, -- c48
	c49 varchar, -- c49
	c50 varchar, -- c50
	c51 varchar, -- c51
	c52 varchar, -- c52
	c53 varchar, -- c53
	c54 varchar, -- c54
	c55 varchar, -- c55
	c56 varchar, -- c56
	c57 varchar, -- c57
	c58 varchar, -- c58
	c59 varchar, -- c59
	c60 varchar, -- c60
	c61 varchar, -- c61
	c62 varchar, -- c62
	c63 varchar, -- c63
	c64 varchar, -- c64
	c65 varchar, -- c65
	c66 varchar, -- c66
	c67 varchar, -- c67
	c68 varchar, -- c68
	c69 varchar, -- c69
	c70 varchar, -- c70
	c71 varchar, -- c71
	c72 varchar, -- c72
	c73 varchar, -- c73
	c74 varchar, -- c74
	c75 varchar, -- c75
	c76 varchar, -- c76
	c77 varchar, -- c77
	c78 varchar, -- c78
	c79 varchar, -- c79
	c80 varchar, -- c80
	c81 varchar, -- c81
	c82 varchar, -- c82
	c83 varchar, -- c83
	c84 varchar, -- c84
	c85 varchar, -- c85
	c86 varchar, -- c86
	c87 varchar, -- c87
	c88 varchar, -- c88
	c89 varchar, -- c89
	c90 varchar, -- c90
	c91 varchar, -- c91
	c92 varchar, -- c92
	c93 varchar, -- c93
	c94 varchar, -- c94
	c95 varchar, -- c95
	c96 varchar, -- c96
	c97 varchar, -- c97
	c98 varchar, -- c98
	c99 varchar, -- c99
	c100 varchar, -- c100
	c101 varchar, -- c101
	c102 varchar, -- c102
	c103 varchar, -- c103
	c104 varchar, -- c104
	c105 varchar, -- c105
	c106 varchar, -- c106
	c107 varchar, -- c107
	c108 varchar, -- c108
	c109 varchar, -- c109
	c110 varchar, -- c110
	c111 varchar, -- c111
	c112 varchar, -- c112
	c113 varchar, -- c113
	c114 varchar, -- c114
	c115 varchar, -- c115
	c116 varchar, -- c116
	c117 varchar, -- c117
	c118 varchar, -- c118
	c119 varchar, -- c119
	c120 varchar, -- c120
	c121 varchar, -- c121
	c122 varchar, -- c122
	c123 varchar, -- c123
	c124 varchar, -- c124
	c125 varchar, -- c125
	c126 varchar, -- c126
	c127 varchar, -- c127
	c128 varchar, -- c128
	c129 varchar, -- c129
	c130 varchar, -- c130
	c131 varchar, -- c131
	c132 varchar, -- c132
	c133 varchar, -- c133
	c134 varchar, -- c134
	c135 varchar, -- c135
	c136 varchar, -- c136
	c137 varchar, -- c137
	c138 varchar, -- c138
	c139 varchar, -- c139
	c140 varchar, -- c140
	c141 varchar, -- c141
	c142 varchar, -- c142
	c143 varchar, -- c143
	c144 varchar, -- c144
	c145 varchar, -- c145
	c146 varchar, -- c146
	c147 varchar, -- c147
	c148 varchar, -- c148
	c149 varchar, -- c149
	c150 varchar, -- c150
	c151 varchar, -- c151
	c152 varchar, -- c152
	c153 varchar, -- c153
	c154 varchar, -- c154
	c155 varchar, -- c155
	c156 varchar, -- c156
	c157 varchar, -- c157
	c158 varchar, -- c158
	c159 varchar, -- c159
	c160 varchar, -- c160
	c161 varchar, -- c161
	c162 varchar, -- c162
	c163 varchar, -- c163
	c164 varchar, -- c164
	c165 varchar, -- c165
	c166 varchar, -- c166
	c167 varchar, -- c167
	c168 varchar, -- c168
	c169 varchar, -- c169
	c170 varchar, -- c170
	c171 varchar, -- c171
	c172 varchar, -- c172
	c173 varchar, -- c173
	c174 varchar, -- c174
	c175 varchar, -- c175
	c176 varchar, -- c176
	c177 varchar, -- c177
	c178 varchar, -- c178
	c179 varchar, -- c179
	c180 varchar, -- c180
	c181 varchar, -- c181
	c182 varchar, -- c182
	c183 varchar, -- c183
	c184 varchar, -- c184
	c185 varchar, -- c185
	c186 varchar, -- c186
	c187 varchar, -- c187
	c188 varchar, -- c188
	c189 varchar, -- c189
	c190 varchar, -- c190
	c191 varchar, -- c191
	c192 varchar, -- c192
	c193 varchar, -- c193
	c194 varchar, -- c194
	c195 varchar, -- c195
	c196 varchar, -- c196
	c197 varchar, -- c197
	c198 varchar, -- c198
	c199 varchar, -- c199
	c200 varchar, -- c200
	c201 varchar, -- c201
	c202 varchar, -- c202
	c203 varchar, -- c203
	c204 varchar, -- c204
	c205 varchar, -- c205
	c206 varchar, -- c206
	c207 varchar, -- c207
	c208 varchar, -- c208
	c209 varchar, -- c209
	c210 varchar, -- c210
	c211 varchar, -- c211
	c212 varchar, -- c212
	c213 varchar, -- c213
	c214 varchar, -- c214
	c215 varchar, -- c215
	c216 varchar, -- c216
	c217 varchar, -- c217
	c218 varchar, -- c218
	c219 varchar, -- c219
	c220 varchar, -- c220
	c221 varchar, -- c221
	c222 varchar, -- c222
	c223 varchar, -- c223
	c224 varchar, -- c224
	c225 varchar, -- c225
	c226 varchar, -- c226
	c227 varchar, -- c227
	c228 varchar, -- c228
	c229 varchar, -- c229
	c230 varchar, -- c230
	c231 varchar, -- c231
	c232 varchar, -- c232
	c233 varchar, -- c233
	c234 varchar, -- c234
	c235 varchar, -- c235
	c236 varchar, -- c236
	c237 varchar, -- c237
	c238 varchar, -- c238
	c239 varchar, -- c239
	c240 varchar, -- c240
	c241 varchar, -- c241
	c242 varchar, -- c242
	c243 varchar, -- c243
	c244 varchar, -- c244
	c245 varchar, -- c245
	c246 varchar, -- c246
	c247 varchar, -- c247
	c248 varchar, -- c248
	c249 varchar, -- c249
	c250 varchar, -- c250
	c251 varchar, -- c251
	c252 varchar, -- c252
	c253 varchar, -- c253
	c254 varchar, -- c254
	c255 varchar, -- c255
	c256 varchar, -- c256
	c257 varchar, -- c257
	c258 varchar, -- c258
	c259 varchar, -- c259
	c260 varchar, -- c260
	c261 varchar, -- c261
	c262 varchar, -- c262
	c263 varchar, -- c263
	c264 varchar, -- c264
	c265 varchar, -- c265
	c266 varchar, -- c266
	c267 varchar, -- c267
	c268 varchar, -- c268
	c269 varchar, -- c269
	c270 varchar, -- c270
	c271 varchar, -- c271
	c272 varchar, -- c272
	c273 varchar, -- c273
	c274 varchar, -- c274
	c275 varchar, -- c275
	c276 varchar, -- c276
	c277 varchar, -- c277
	c278 varchar, -- c278
	c279 varchar, -- c279
	c280 varchar, -- c280
	c281 varchar, -- c281
	c282 varchar, -- c282
	c283 varchar, -- c283
	c284 varchar, -- c284
	c285 varchar, -- c285
	c286 varchar, -- c286
	c287 varchar, -- c287
	c288 varchar, -- c288
	c289 varchar, -- c289
	c290 varchar, -- c290
	c291 varchar, -- c291
	c292 varchar, -- c292
	c293 varchar, -- c293
	c294 varchar, -- c294
	c295 varchar, -- c295
	c296 varchar, -- c296
	c297 varchar, -- c297
	c298 varchar, -- c298
	c299 varchar, -- c299
	c300 varchar, -- c300
	c301 varchar, -- c301
	c302 varchar, -- c302
	c303 varchar, -- c303
	c304 varchar, -- c304
	c305 varchar, -- c305
	c306 varchar, -- c306
	c307 varchar, -- c307
	c308 varchar, -- c308
	c309 varchar, -- c309
	c310 varchar, -- c310
	c311 varchar, -- c311
	c312 varchar, -- c312
	c313 varchar, -- c313
	c314 varchar, -- c314
	c315 varchar, -- c315
	c316 varchar, -- c316
	c317 varchar, -- c317
	c318 varchar, -- c318
	c319 varchar, -- c319
	c320 varchar, -- c320
	c321 varchar, -- c321
	c322 varchar, -- c322
	c323 varchar, -- c323
	c324 varchar, -- c324
	c325 varchar, -- c325
	c326 varchar, -- c326
	c327 varchar, -- c327
	c328 varchar, -- c328
	c329 varchar, -- c329
	c330 varchar, -- c330
	c331 varchar, -- c331
	c332 varchar, -- c332
	c333 varchar, -- c333
	c334 varchar, -- c334
	c335 varchar, -- c335
	c336 varchar, -- c336
	c337 varchar, -- c337
	c338 varchar, -- c338
	c339 varchar, -- c339
	c340 varchar, -- c340
	c341 varchar, -- c341
	c342 varchar, -- c342
	c343 varchar, -- c343
	c344 varchar, -- c344
	c345 varchar, -- c345
	c346 varchar, -- c346
	c347 varchar, -- c347
	c348 varchar, -- c348
	c349 varchar, -- c349
	c350 varchar, -- c350
	c351 varchar, -- c351
	c352 varchar, -- c352
	c353 varchar, -- c353
	c354 varchar, -- c354
	c355 varchar, -- c355
	c356 varchar, -- c356
	c357 varchar, -- c357
	c358 varchar, -- c358
	c359 varchar, -- c359
	c360 varchar, -- c360
	c361 varchar, -- c361
	c362 varchar, -- c362
	c363 varchar, -- c363
	c364 varchar, -- c364
	c365 varchar, -- c365
	c366 varchar, -- c366
	c367 varchar, -- c367
	c368 varchar, -- c368
	c369 varchar, -- c369
	c370 varchar, -- c370
	c371 varchar, -- c371
	c372 varchar, -- c372
	c373 varchar, -- c373
	c374 varchar, -- c374
	c375 varchar, -- c375
	c376 varchar, -- c376
	c377 varchar, -- c377
	c378 varchar, -- c378
	c379 varchar, -- c379
	c380 varchar, -- c380
	c381 varchar, -- c381
	c382 varchar, -- c382
	c383 varchar, -- c383
	c384 varchar, -- c384
	c385 varchar, -- c385
	c386 varchar, -- c386
	c387 varchar, -- c387
	c388 varchar, -- c388
	c389 varchar, -- c389
	c390 varchar, -- c390
	c391 varchar, -- c391
	c392 varchar, -- c392
	c393 varchar, -- c393
	c394 varchar, -- c394
	c395 varchar, -- c395
	c396 varchar, -- c396
	c397 varchar, -- c397
	c398 varchar, -- c398
	c399 varchar, -- c399
	c400 varchar, -- c400
	c401 varchar, -- c401
	c402 varchar, -- c402
	c403 varchar, -- c403
	c404 varchar, -- c404
	c405 varchar, -- c405
	c406 varchar, -- c406
	c407 varchar, -- c407
	c408 varchar, -- c408
	c409 varchar, -- c409
	c410 varchar, -- c410
	c411 varchar, -- c411
	c412 varchar, -- c412
	c413 varchar, -- c413
	c414 varchar, -- c414
	c415 varchar, -- c415
	c416 varchar, -- c416
	c417 varchar, -- c417
	c418 varchar, -- c418
	c419 varchar, -- c419
	c420 varchar, -- c420
	c421 varchar, -- c421
	c422 varchar, -- c422
	c423 varchar, -- c423
	c424 varchar, -- c424
	c425 varchar, -- c425
	c426 varchar, -- c426
	c427 varchar, -- c427
	c428 varchar, -- c428
	c429 varchar, -- c429
	c430 varchar, -- c430
	c431 varchar, -- c431
	c432 varchar, -- c432
	c433 varchar, -- c433
	c434 varchar, -- c434
	c435 varchar, -- c435
	c436 varchar, -- c436
	c437 varchar, -- c437
	c438 varchar, -- c438
	c439 varchar, -- c439
	c440 varchar, -- c440
	c441 varchar, -- c441
	c442 varchar, -- c442
	c443 varchar, -- c443
	c444 varchar, -- c444
	c445 varchar, -- c445
	c446 varchar, -- c446
	c447 varchar, -- c447
	c448 varchar, -- c448
	c449 varchar, -- c449
	c450 varchar, -- c450
	c451 varchar, -- c451
	c452 varchar, -- c452
	c453 varchar, -- c453
	c454 varchar, -- c454
	c455 varchar, -- c455
	c456 varchar, -- c456
	c457 varchar, -- c457
	c458 varchar, -- c458
	c459 varchar, -- c459
	c460 varchar, -- c460
	c461 varchar, -- c461
	c462 varchar, -- c462
	c463 varchar, -- c463
	c464 varchar, -- c464
	c465 varchar, -- c465
	c466 varchar, -- c466
	c467 varchar, -- c467
	c468 varchar, -- c468
	c469 varchar, -- c469
	c470 varchar, -- c470
	c471 varchar, -- c471
	c472 varchar, -- c472
	c473 varchar, -- c473
	c474 varchar, -- c474
	c475 varchar, -- c475
	c476 varchar, -- c476
	c477 varchar, -- c477
	c478 varchar, -- c478
	c479 varchar, -- c479
	c480 varchar, -- c480
	c481 varchar, -- c481
	c482 varchar, -- c482
	c483 varchar, -- c483
	c484 varchar, -- c484
	c485 varchar, -- c485
	c486 varchar, -- c486
	c487 varchar, -- c487
	c488 varchar, -- c488
	c489 varchar, -- c489
	c490 varchar, -- c490
	c491 varchar, -- c491
	c492 varchar, -- c492
	c493 varchar, -- c493
	c494 varchar, -- c494
	c495 varchar, -- c495
	c496 varchar, -- c496
	c497 varchar, -- c497
	c498 varchar, -- c498
	c499 varchar, -- c499
	c500 varchar, -- c500

	CONSTRAINT aacc_unidad_new_case_pkey PRIMARY KEY (id)
);

----------------------------------------------------------------------------------------

-- TABLA: Parcela de AACC

DROP TABLE IF EXISTS cclip.aacc_inmueble_new_case CASCADE;
CREATE TABLE cclip.aacc_inmueble_new_case
(
	id varchar NOT NULL, -- id
	erased boolean NOT NULL, -- erased
	audit_date_create timestamp NOT NULL, -- auditDateCreate
	audit_user_create varchar NOT NULL, -- auditUserCreate
	c1 varchar, -- c1
	c2 varchar, -- c2
	c3 varchar, -- c3
	c4 varchar, -- c4
	c5 varchar, -- c5
	c6 varchar, -- c6
	c7 varchar, -- c7
	c8 varchar, -- c8
	c9 varchar, -- c9
	c10 varchar, -- c10
	c11 varchar, -- c11
	c12 varchar, -- c12
	c13 varchar, -- c13
	c14 varchar, -- c14
	c15 varchar, -- c15
	c16 varchar, -- c16
	c17 varchar, -- c17
	c18 varchar, -- c18
	c19 varchar, -- c19
	c20 varchar, -- c20
	c21 varchar, -- c21
	c22 varchar, -- c22
	c23 varchar, -- c23
	c24 varchar, -- c24
	c25 varchar, -- c25
	c26 varchar, -- c26
	c27 varchar, -- c27
	c28 varchar, -- c28
	c29 varchar, -- c29
	c30 varchar, -- c30
	c31 varchar, -- c31
	c32 varchar, -- c32
	c33 varchar, -- c33
	c34 varchar, -- c34
	c35 varchar, -- c35
	c36 varchar, -- c36
	c37 varchar, -- c37
	c38 varchar, -- c38
	c39 varchar, -- c39
	c40 varchar, -- c40
	c41 varchar, -- c41
	c42 varchar, -- c42
	c43 varchar, -- c43
	c44 varchar, -- c44
	c45 varchar, -- c45
	c46 varchar, -- c46
	c47 varchar, -- c47
	c48 varchar, -- c48
	c49 varchar, -- c49
	c50 varchar, -- c50
	c51 varchar, -- c51
	c52 varchar, -- c52
	c53 varchar, -- c53
	c54 varchar, -- c54
	c55 varchar, -- c55
	c56 varchar, -- c56
	c57 varchar, -- c57
	c58 varchar, -- c58
	c59 varchar, -- c59
	c60 varchar, -- c60
	c61 varchar, -- c61
	c62 varchar, -- c62
	c63 varchar, -- c63
	c64 varchar, -- c64
	c65 varchar, -- c65
	c66 varchar, -- c66
	c67 varchar, -- c67
	c68 varchar, -- c68
	c69 varchar, -- c69
	c70 varchar, -- c70
	c71 varchar, -- c71
	c72 varchar, -- c72
	c73 varchar, -- c73
	c74 varchar, -- c74
	c75 varchar, -- c75
	c76 varchar, -- c76
	c77 varchar, -- c77
	c78 varchar, -- c78
	c79 varchar, -- c79
	c80 varchar, -- c80
	c81 varchar, -- c81
	c82 varchar, -- c82
	c83 varchar, -- c83
	c84 varchar, -- c84
	c85 varchar, -- c85
	c86 varchar, -- c86
	c87 varchar, -- c87
	c88 varchar, -- c88
	c89 varchar, -- c89
	c90 varchar, -- c90
	c91 varchar, -- c91
	c92 varchar, -- c92
	c93 varchar, -- c93
	c94 varchar, -- c94
	c95 varchar, -- c95
	c96 varchar, -- c96
	c97 varchar, -- c97
	c98 varchar, -- c98
	c99 varchar, -- c99
	c100 varchar, -- c100
	c101 varchar, -- c101
	c102 varchar, -- c102
	c103 varchar, -- c103
	c104 varchar, -- c104
	c105 varchar, -- c105
	c106 varchar, -- c106
	c107 varchar, -- c107
	c108 varchar, -- c108
	c109 varchar, -- c109
	c110 varchar, -- c110
	c111 varchar, -- c111
	c112 varchar, -- c112
	c113 varchar, -- c113
	c114 varchar, -- c114
	c115 varchar, -- c115
	c116 varchar, -- c116
	c117 varchar, -- c117
	c118 varchar, -- c118
	c119 varchar, -- c119
	c120 varchar, -- c120
	c121 varchar, -- c121
	c122 varchar, -- c122
	c123 varchar, -- c123
	c124 varchar, -- c124
	c125 varchar, -- c125
	c126 varchar, -- c126
	c127 varchar, -- c127
	c128 varchar, -- c128
	c129 varchar, -- c129
	c130 varchar, -- c130
	c131 varchar, -- c131
	c132 varchar, -- c132
	c133 varchar, -- c133
	c134 varchar, -- c134
	c135 varchar, -- c135
	c136 varchar, -- c136
	c137 varchar, -- c137
	c138 varchar, -- c138
	c139 varchar, -- c139
	c140 varchar, -- c140
	c141 varchar, -- c141
	c142 varchar, -- c142
	c143 varchar, -- c143
	c144 varchar, -- c144
	c145 varchar, -- c145
	c146 varchar, -- c146
	c147 varchar, -- c147
	c148 varchar, -- c148
	c149 varchar, -- c149
	c150 varchar, -- c150
	c151 varchar, -- c151
	c152 varchar, -- c152
	c153 varchar, -- c153
	c154 varchar, -- c154
	c155 varchar, -- c155
	c156 varchar, -- c156
	c157 varchar, -- c157
	c158 varchar, -- c158
	c159 varchar, -- c159
	c160 varchar, -- c160
	c161 varchar, -- c161
	c162 varchar, -- c162
	c163 varchar, -- c163
	c164 varchar, -- c164
	c165 varchar, -- c165
	c166 varchar, -- c166
	c167 varchar, -- c167
	c168 varchar, -- c168
	c169 varchar, -- c169
	c170 varchar, -- c170
	c171 varchar, -- c171
	c172 varchar, -- c172
	c173 varchar, -- c173
	c174 varchar, -- c174
	c175 varchar, -- c175
	c176 varchar, -- c176
	c177 varchar, -- c177
	c178 varchar, -- c178
	c179 varchar, -- c179
	c180 varchar, -- c180
	c181 varchar, -- c181
	c182 varchar, -- c182
	c183 varchar, -- c183
	c184 varchar, -- c184
	c185 varchar, -- c185
	c186 varchar, -- c186
	c187 varchar, -- c187
	c188 varchar, -- c188
	c189 varchar, -- c189
	c190 varchar, -- c190
	c191 varchar, -- c191
	c192 varchar, -- c192
	c193 varchar, -- c193
	c194 varchar, -- c194
	c195 varchar, -- c195
	c196 varchar, -- c196
	c197 varchar, -- c197
	c198 varchar, -- c198
	c199 varchar, -- c199
	c200 varchar, -- c200
	c201 varchar, -- c201
	c202 varchar, -- c202
	c203 varchar, -- c203
	c204 varchar, -- c204
	c205 varchar, -- c205
	c206 varchar, -- c206
	c207 varchar, -- c207
	c208 varchar, -- c208
	c209 varchar, -- c209
	c210 varchar, -- c210
	c211 varchar, -- c211
	c212 varchar, -- c212
	c213 varchar, -- c213
	c214 varchar, -- c214
	c215 varchar, -- c215
	c216 varchar, -- c216
	c217 varchar, -- c217
	c218 varchar, -- c218
	c219 varchar, -- c219
	c220 varchar, -- c220
	c221 varchar, -- c221
	c222 varchar, -- c222
	c223 varchar, -- c223
	c224 varchar, -- c224
	c225 varchar, -- c225
	c226 varchar, -- c226
	c227 varchar, -- c227
	c228 varchar, -- c228
	c229 varchar, -- c229
	c230 varchar, -- c230
	c231 varchar, -- c231
	c232 varchar, -- c232
	c233 varchar, -- c233
	c234 varchar, -- c234
	c235 varchar, -- c235
	c236 varchar, -- c236
	c237 varchar, -- c237
	c238 varchar, -- c238
	c239 varchar, -- c239
	c240 varchar, -- c240
	c241 varchar, -- c241
	c242 varchar, -- c242
	c243 varchar, -- c243
	c244 varchar, -- c244
	c245 varchar, -- c245
	c246 varchar, -- c246
	c247 varchar, -- c247
	c248 varchar, -- c248
	c249 varchar, -- c249
	c250 varchar, -- c250
	c251 varchar, -- c251
	c252 varchar, -- c252
	c253 varchar, -- c253
	c254 varchar, -- c254
	c255 varchar, -- c255
	c256 varchar, -- c256
	c257 varchar, -- c257
	c258 varchar, -- c258
	c259 varchar, -- c259
	c260 varchar, -- c260
	c261 varchar, -- c261
	c262 varchar, -- c262
	c263 varchar, -- c263
	c264 varchar, -- c264
	c265 varchar, -- c265
	c266 varchar, -- c266
	c267 varchar, -- c267
	c268 varchar, -- c268
	c269 varchar, -- c269
	c270 varchar, -- c270
	c271 varchar, -- c271
	c272 varchar, -- c272
	c273 varchar, -- c273
	c274 varchar, -- c274
	c275 varchar, -- c275
	c276 varchar, -- c276
	c277 varchar, -- c277
	c278 varchar, -- c278
	c279 varchar, -- c279
	c280 varchar, -- c280
	c281 varchar, -- c281
	c282 varchar, -- c282
	c283 varchar, -- c283
	c284 varchar, -- c284
	c285 varchar, -- c285
	c286 varchar, -- c286
	c287 varchar, -- c287
	c288 varchar, -- c288
	c289 varchar, -- c289
	c290 varchar, -- c290
	c291 varchar, -- c291
	c292 varchar, -- c292
	c293 varchar, -- c293
	c294 varchar, -- c294
	c295 varchar, -- c295
	c296 varchar, -- c296
	c297 varchar, -- c297
	c298 varchar, -- c298
	c299 varchar, -- c299
	c300 varchar, -- c300
	c301 varchar, -- c301
	c302 varchar, -- c302
	c303 varchar, -- c303
	c304 varchar, -- c304
	c305 varchar, -- c305
	c306 varchar, -- c306
	c307 varchar, -- c307
	c308 varchar, -- c308
	c309 varchar, -- c309
	c310 varchar, -- c310
	c311 varchar, -- c311
	c312 varchar, -- c312
	c313 varchar, -- c313
	c314 varchar, -- c314
	c315 varchar, -- c315
	c316 varchar, -- c316
	c317 varchar, -- c317
	c318 varchar, -- c318
	c319 varchar, -- c319
	c320 varchar, -- c320
	c321 varchar, -- c321
	c322 varchar, -- c322
	c323 varchar, -- c323
	c324 varchar, -- c324
	c325 varchar, -- c325
	c326 varchar, -- c326
	c327 varchar, -- c327
	c328 varchar, -- c328
	c329 varchar, -- c329
	c330 varchar, -- c330
	c331 varchar, -- c331
	c332 varchar, -- c332
	c333 varchar, -- c333
	c334 varchar, -- c334
	c335 varchar, -- c335
	c336 varchar, -- c336
	c337 varchar, -- c337
	c338 varchar, -- c338
	c339 varchar, -- c339
	c340 varchar, -- c340
	c341 varchar, -- c341
	c342 varchar, -- c342
	c343 varchar, -- c343
	c344 varchar, -- c344
	c345 varchar, -- c345
	c346 varchar, -- c346
	c347 varchar, -- c347
	c348 varchar, -- c348
	c349 varchar, -- c349
	c350 varchar, -- c350
	c351 varchar, -- c351
	c352 varchar, -- c352
	c353 varchar, -- c353
	c354 varchar, -- c354
	c355 varchar, -- c355
	c356 varchar, -- c356
	c357 varchar, -- c357
	c358 varchar, -- c358
	c359 varchar, -- c359
	c360 varchar, -- c360
	c361 varchar, -- c361
	c362 varchar, -- c362
	c363 varchar, -- c363
	c364 varchar, -- c364
	c365 varchar, -- c365
	c366 varchar, -- c366
	c367 varchar, -- c367
	c368 varchar, -- c368
	c369 varchar, -- c369
	c370 varchar, -- c370
	c371 varchar, -- c371
	c372 varchar, -- c372
	c373 varchar, -- c373
	c374 varchar, -- c374
	c375 varchar, -- c375
	c376 varchar, -- c376
	c377 varchar, -- c377
	c378 varchar, -- c378
	c379 varchar, -- c379
	c380 varchar, -- c380
	c381 varchar, -- c381
	c382 varchar, -- c382
	c383 varchar, -- c383
	c384 varchar, -- c384
	c385 varchar, -- c385
	c386 varchar, -- c386
	c387 varchar, -- c387
	c388 varchar, -- c388
	c389 varchar, -- c389
	c390 varchar, -- c390
	c391 varchar, -- c391
	c392 varchar, -- c392
	c393 varchar, -- c393
	c394 varchar, -- c394
	c395 varchar, -- c395
	c396 varchar, -- c396
	c397 varchar, -- c397
	c398 varchar, -- c398
	c399 varchar, -- c399
	c400 varchar, -- c400
	c401 varchar, -- c401
	c402 varchar, -- c402
	c403 varchar, -- c403
	c404 varchar, -- c404
	c405 varchar, -- c405
	c406 varchar, -- c406
	c407 varchar, -- c407
	c408 varchar, -- c408
	c409 varchar, -- c409
	c410 varchar, -- c410
	c411 varchar, -- c411
	c412 varchar, -- c412
	c413 varchar, -- c413
	c414 varchar, -- c414
	c415 varchar, -- c415
	c416 varchar, -- c416
	c417 varchar, -- c417
	c418 varchar, -- c418
	c419 varchar, -- c419
	c420 varchar, -- c420
	c421 varchar, -- c421
	c422 varchar, -- c422
	c423 varchar, -- c423
	c424 varchar, -- c424
	c425 varchar, -- c425
	c426 varchar, -- c426
	c427 varchar, -- c427
	c428 varchar, -- c428
	c429 varchar, -- c429
	c430 varchar, -- c430
	c431 varchar, -- c431
	c432 varchar, -- c432
	c433 varchar, -- c433
	c434 varchar, -- c434
	c435 varchar, -- c435
	c436 varchar, -- c436
	c437 varchar, -- c437
	c438 varchar, -- c438
	c439 varchar, -- c439
	c440 varchar, -- c440
	c441 varchar, -- c441
	c442 varchar, -- c442
	c443 varchar, -- c443
	c444 varchar, -- c444
	c445 varchar, -- c445
	c446 varchar, -- c446
	c447 varchar, -- c447
	c448 varchar, -- c448
	c449 varchar, -- c449
	c450 varchar, -- c450
	c451 varchar, -- c451
	c452 varchar, -- c452
	c453 varchar, -- c453
	c454 varchar, -- c454
	c455 varchar, -- c455
	c456 varchar, -- c456
	c457 varchar, -- c457
	c458 varchar, -- c458
	c459 varchar, -- c459
	c460 varchar, -- c460
	c461 varchar, -- c461
	c462 varchar, -- c462
	c463 varchar, -- c463
	c464 varchar, -- c464
	c465 varchar, -- c465
	c466 varchar, -- c466
	c467 varchar, -- c467
	c468 varchar, -- c468
	c469 varchar, -- c469
	c470 varchar, -- c470
	c471 varchar, -- c471
	c472 varchar, -- c472
	c473 varchar, -- c473
	c474 varchar, -- c474
	c475 varchar, -- c475
	c476 varchar, -- c476
	c477 varchar, -- c477
	c478 varchar, -- c478
	c479 varchar, -- c479
	c480 varchar, -- c480
	c481 varchar, -- c481
	c482 varchar, -- c482
	c483 varchar, -- c483
	c484 varchar, -- c484
	c485 varchar, -- c485
	c486 varchar, -- c486
	c487 varchar, -- c487
	c488 varchar, -- c488
	c489 varchar, -- c489
	c490 varchar, -- c490
	c491 varchar, -- c491
	c492 varchar, -- c492
	c493 varchar, -- c493
	c494 varchar, -- c494
	c495 varchar, -- c495
	c496 varchar, -- c496
	c497 varchar, -- c497
	c498 varchar, -- c498
	c499 varchar, -- c499
	c500 varchar, -- c500

	CONSTRAINT aacc_inmueble_new_case_pkey PRIMARY KEY (id)
);

----------------------------------------------------------------------------------------

-- TABLA: SubDivision de AACC

DROP TABLE IF EXISTS cclip.aacc_subcuenta_new_case CASCADE;
CREATE TABLE cclip.aacc_subcuenta_new_case
(
	id varchar NOT NULL, -- id
	erased boolean NOT NULL, -- erased
	audit_date_create timestamp NOT NULL, -- auditDateCreate
	audit_user_create varchar NOT NULL, -- auditUserCreate
	c1 varchar, -- c1
	c2 varchar, -- c2
	c3 varchar, -- c3
	c4 varchar, -- c4
	c5 varchar, -- c5
	c6 varchar, -- c6
	c7 varchar, -- c7
	c8 varchar, -- c8
	c9 varchar, -- c9
	c10 varchar, -- c10
	c11 varchar, -- c11
	c12 varchar, -- c12
	c13 varchar, -- c13
	c14 varchar, -- c14
	c15 varchar, -- c15
	c16 varchar, -- c16
	c17 varchar, -- c17
	c18 varchar, -- c18
	c19 varchar, -- c19
	c20 varchar, -- c20
	c21 varchar, -- c21
	c22 varchar, -- c22
	c23 varchar, -- c23
	c24 varchar, -- c24
	c25 varchar, -- c25
	c26 varchar, -- c26
	c27 varchar, -- c27
	c28 varchar, -- c28
	c29 varchar, -- c29
	c30 varchar, -- c30
	c31 varchar, -- c31
	c32 varchar, -- c32
	c33 varchar, -- c33
	c34 varchar, -- c34
	c35 varchar, -- c35
	c36 varchar, -- c36
	c37 varchar, -- c37
	c38 varchar, -- c38
	c39 varchar, -- c39
	c40 varchar, -- c40
	c41 varchar, -- c41
	c42 varchar, -- c42
	c43 varchar, -- c43
	c44 varchar, -- c44
	c45 varchar, -- c45
	c46 varchar, -- c46
	c47 varchar, -- c47
	c48 varchar, -- c48
	c49 varchar, -- c49
	c50 varchar, -- c50
	c51 varchar, -- c51
	c52 varchar, -- c52
	c53 varchar, -- c53
	c54 varchar, -- c54
	c55 varchar, -- c55
	c56 varchar, -- c56
	c57 varchar, -- c57
	c58 varchar, -- c58
	c59 varchar, -- c59
	c60 varchar, -- c60
	c61 varchar, -- c61
	c62 varchar, -- c62
	c63 varchar, -- c63
	c64 varchar, -- c64
	c65 varchar, -- c65
	c66 varchar, -- c66
	c67 varchar, -- c67
	c68 varchar, -- c68
	c69 varchar, -- c69
	c70 varchar, -- c70
	c71 varchar, -- c71
	c72 varchar, -- c72
	c73 varchar, -- c73
	c74 varchar, -- c74
	c75 varchar, -- c75
	c76 varchar, -- c76
	c77 varchar, -- c77
	c78 varchar, -- c78
	c79 varchar, -- c79
	c80 varchar, -- c80
	c81 varchar, -- c81
	c82 varchar, -- c82
	c83 varchar, -- c83
	c84 varchar, -- c84
	c85 varchar, -- c85
	c86 varchar, -- c86
	c87 varchar, -- c87
	c88 varchar, -- c88
	c89 varchar, -- c89
	c90 varchar, -- c90
	c91 varchar, -- c91
	c92 varchar, -- c92
	c93 varchar, -- c93
	c94 varchar, -- c94
	c95 varchar, -- c95
	c96 varchar, -- c96
	c97 varchar, -- c97
	c98 varchar, -- c98
	c99 varchar, -- c99
	c100 varchar, -- c100
	c101 varchar, -- c101
	c102 varchar, -- c102
	c103 varchar, -- c103
	c104 varchar, -- c104
	c105 varchar, -- c105
	c106 varchar, -- c106
	c107 varchar, -- c107
	c108 varchar, -- c108
	c109 varchar, -- c109
	c110 varchar, -- c110
	c111 varchar, -- c111
	c112 varchar, -- c112
	c113 varchar, -- c113
	c114 varchar, -- c114
	c115 varchar, -- c115
	c116 varchar, -- c116
	c117 varchar, -- c117
	c118 varchar, -- c118
	c119 varchar, -- c119
	c120 varchar, -- c120
	c121 varchar, -- c121
	c122 varchar, -- c122
	c123 varchar, -- c123
	c124 varchar, -- c124
	c125 varchar, -- c125
	c126 varchar, -- c126
	c127 varchar, -- c127
	c128 varchar, -- c128
	c129 varchar, -- c129
	c130 varchar, -- c130
	c131 varchar, -- c131
	c132 varchar, -- c132
	c133 varchar, -- c133
	c134 varchar, -- c134
	c135 varchar, -- c135
	c136 varchar, -- c136
	c137 varchar, -- c137
	c138 varchar, -- c138
	c139 varchar, -- c139
	c140 varchar, -- c140
	c141 varchar, -- c141
	c142 varchar, -- c142
	c143 varchar, -- c143
	c144 varchar, -- c144
	c145 varchar, -- c145
	c146 varchar, -- c146
	c147 varchar, -- c147
	c148 varchar, -- c148
	c149 varchar, -- c149
	c150 varchar, -- c150
	c151 varchar, -- c151
	c152 varchar, -- c152
	c153 varchar, -- c153
	c154 varchar, -- c154
	c155 varchar, -- c155
	c156 varchar, -- c156
	c157 varchar, -- c157
	c158 varchar, -- c158
	c159 varchar, -- c159
	c160 varchar, -- c160
	c161 varchar, -- c161
	c162 varchar, -- c162
	c163 varchar, -- c163
	c164 varchar, -- c164
	c165 varchar, -- c165
	c166 varchar, -- c166
	c167 varchar, -- c167
	c168 varchar, -- c168
	c169 varchar, -- c169
	c170 varchar, -- c170
	c171 varchar, -- c171
	c172 varchar, -- c172
	c173 varchar, -- c173
	c174 varchar, -- c174
	c175 varchar, -- c175
	c176 varchar, -- c176
	c177 varchar, -- c177
	c178 varchar, -- c178
	c179 varchar, -- c179
	c180 varchar, -- c180
	c181 varchar, -- c181
	c182 varchar, -- c182
	c183 varchar, -- c183
	c184 varchar, -- c184
	c185 varchar, -- c185
	c186 varchar, -- c186
	c187 varchar, -- c187
	c188 varchar, -- c188
	c189 varchar, -- c189
	c190 varchar, -- c190
	c191 varchar, -- c191
	c192 varchar, -- c192
	c193 varchar, -- c193
	c194 varchar, -- c194
	c195 varchar, -- c195
	c196 varchar, -- c196
	c197 varchar, -- c197
	c198 varchar, -- c198
	c199 varchar, -- c199
	c200 varchar, -- c200
	c201 varchar, -- c201
	c202 varchar, -- c202
	c203 varchar, -- c203
	c204 varchar, -- c204
	c205 varchar, -- c205
	c206 varchar, -- c206
	c207 varchar, -- c207
	c208 varchar, -- c208
	c209 varchar, -- c209
	c210 varchar, -- c210
	c211 varchar, -- c211
	c212 varchar, -- c212
	c213 varchar, -- c213
	c214 varchar, -- c214
	c215 varchar, -- c215
	c216 varchar, -- c216
	c217 varchar, -- c217
	c218 varchar, -- c218
	c219 varchar, -- c219
	c220 varchar, -- c220
	c221 varchar, -- c221
	c222 varchar, -- c222
	c223 varchar, -- c223
	c224 varchar, -- c224
	c225 varchar, -- c225
	c226 varchar, -- c226
	c227 varchar, -- c227
	c228 varchar, -- c228
	c229 varchar, -- c229
	c230 varchar, -- c230
	c231 varchar, -- c231
	c232 varchar, -- c232
	c233 varchar, -- c233
	c234 varchar, -- c234
	c235 varchar, -- c235
	c236 varchar, -- c236
	c237 varchar, -- c237
	c238 varchar, -- c238
	c239 varchar, -- c239
	c240 varchar, -- c240
	c241 varchar, -- c241
	c242 varchar, -- c242
	c243 varchar, -- c243
	c244 varchar, -- c244
	c245 varchar, -- c245
	c246 varchar, -- c246
	c247 varchar, -- c247
	c248 varchar, -- c248
	c249 varchar, -- c249
	c250 varchar, -- c250
	c251 varchar, -- c251
	c252 varchar, -- c252
	c253 varchar, -- c253
	c254 varchar, -- c254
	c255 varchar, -- c255
	c256 varchar, -- c256
	c257 varchar, -- c257
	c258 varchar, -- c258
	c259 varchar, -- c259
	c260 varchar, -- c260
	c261 varchar, -- c261
	c262 varchar, -- c262
	c263 varchar, -- c263
	c264 varchar, -- c264
	c265 varchar, -- c265
	c266 varchar, -- c266
	c267 varchar, -- c267
	c268 varchar, -- c268
	c269 varchar, -- c269
	c270 varchar, -- c270
	c271 varchar, -- c271
	c272 varchar, -- c272
	c273 varchar, -- c273
	c274 varchar, -- c274
	c275 varchar, -- c275
	c276 varchar, -- c276
	c277 varchar, -- c277
	c278 varchar, -- c278
	c279 varchar, -- c279
	c280 varchar, -- c280
	c281 varchar, -- c281
	c282 varchar, -- c282
	c283 varchar, -- c283
	c284 varchar, -- c284
	c285 varchar, -- c285
	c286 varchar, -- c286
	c287 varchar, -- c287
	c288 varchar, -- c288
	c289 varchar, -- c289
	c290 varchar, -- c290
	c291 varchar, -- c291
	c292 varchar, -- c292
	c293 varchar, -- c293
	c294 varchar, -- c294
	c295 varchar, -- c295
	c296 varchar, -- c296
	c297 varchar, -- c297
	c298 varchar, -- c298
	c299 varchar, -- c299
	c300 varchar, -- c300
	c301 varchar, -- c301
	c302 varchar, -- c302
	c303 varchar, -- c303
	c304 varchar, -- c304
	c305 varchar, -- c305
	c306 varchar, -- c306
	c307 varchar, -- c307
	c308 varchar, -- c308
	c309 varchar, -- c309
	c310 varchar, -- c310
	c311 varchar, -- c311
	c312 varchar, -- c312
	c313 varchar, -- c313
	c314 varchar, -- c314
	c315 varchar, -- c315
	c316 varchar, -- c316
	c317 varchar, -- c317
	c318 varchar, -- c318
	c319 varchar, -- c319
	c320 varchar, -- c320
	c321 varchar, -- c321
	c322 varchar, -- c322
	c323 varchar, -- c323
	c324 varchar, -- c324
	c325 varchar, -- c325
	c326 varchar, -- c326
	c327 varchar, -- c327
	c328 varchar, -- c328
	c329 varchar, -- c329
	c330 varchar, -- c330
	c331 varchar, -- c331
	c332 varchar, -- c332
	c333 varchar, -- c333
	c334 varchar, -- c334
	c335 varchar, -- c335
	c336 varchar, -- c336
	c337 varchar, -- c337
	c338 varchar, -- c338
	c339 varchar, -- c339
	c340 varchar, -- c340
	c341 varchar, -- c341
	c342 varchar, -- c342
	c343 varchar, -- c343
	c344 varchar, -- c344
	c345 varchar, -- c345
	c346 varchar, -- c346
	c347 varchar, -- c347
	c348 varchar, -- c348
	c349 varchar, -- c349
	c350 varchar, -- c350
	c351 varchar, -- c351
	c352 varchar, -- c352
	c353 varchar, -- c353
	c354 varchar, -- c354
	c355 varchar, -- c355
	c356 varchar, -- c356
	c357 varchar, -- c357
	c358 varchar, -- c358
	c359 varchar, -- c359
	c360 varchar, -- c360
	c361 varchar, -- c361
	c362 varchar, -- c362
	c363 varchar, -- c363
	c364 varchar, -- c364
	c365 varchar, -- c365
	c366 varchar, -- c366
	c367 varchar, -- c367
	c368 varchar, -- c368
	c369 varchar, -- c369
	c370 varchar, -- c370
	c371 varchar, -- c371
	c372 varchar, -- c372
	c373 varchar, -- c373
	c374 varchar, -- c374
	c375 varchar, -- c375
	c376 varchar, -- c376
	c377 varchar, -- c377
	c378 varchar, -- c378
	c379 varchar, -- c379
	c380 varchar, -- c380
	c381 varchar, -- c381
	c382 varchar, -- c382
	c383 varchar, -- c383
	c384 varchar, -- c384
	c385 varchar, -- c385
	c386 varchar, -- c386
	c387 varchar, -- c387
	c388 varchar, -- c388
	c389 varchar, -- c389
	c390 varchar, -- c390
	c391 varchar, -- c391
	c392 varchar, -- c392
	c393 varchar, -- c393
	c394 varchar, -- c394
	c395 varchar, -- c395
	c396 varchar, -- c396
	c397 varchar, -- c397
	c398 varchar, -- c398
	c399 varchar, -- c399
	c400 varchar, -- c400
	c401 varchar, -- c401
	c402 varchar, -- c402
	c403 varchar, -- c403
	c404 varchar, -- c404
	c405 varchar, -- c405
	c406 varchar, -- c406
	c407 varchar, -- c407
	c408 varchar, -- c408
	c409 varchar, -- c409
	c410 varchar, -- c410
	c411 varchar, -- c411
	c412 varchar, -- c412
	c413 varchar, -- c413
	c414 varchar, -- c414
	c415 varchar, -- c415
	c416 varchar, -- c416
	c417 varchar, -- c417
	c418 varchar, -- c418
	c419 varchar, -- c419
	c420 varchar, -- c420
	c421 varchar, -- c421
	c422 varchar, -- c422
	c423 varchar, -- c423
	c424 varchar, -- c424
	c425 varchar, -- c425
	c426 varchar, -- c426
	c427 varchar, -- c427
	c428 varchar, -- c428
	c429 varchar, -- c429
	c430 varchar, -- c430
	c431 varchar, -- c431
	c432 varchar, -- c432
	c433 varchar, -- c433
	c434 varchar, -- c434
	c435 varchar, -- c435
	c436 varchar, -- c436
	c437 varchar, -- c437
	c438 varchar, -- c438
	c439 varchar, -- c439
	c440 varchar, -- c440
	c441 varchar, -- c441
	c442 varchar, -- c442
	c443 varchar, -- c443
	c444 varchar, -- c444
	c445 varchar, -- c445
	c446 varchar, -- c446
	c447 varchar, -- c447
	c448 varchar, -- c448
	c449 varchar, -- c449
	c450 varchar, -- c450
	c451 varchar, -- c451
	c452 varchar, -- c452
	c453 varchar, -- c453
	c454 varchar, -- c454
	c455 varchar, -- c455
	c456 varchar, -- c456
	c457 varchar, -- c457
	c458 varchar, -- c458
	c459 varchar, -- c459
	c460 varchar, -- c460
	c461 varchar, -- c461
	c462 varchar, -- c462
	c463 varchar, -- c463
	c464 varchar, -- c464
	c465 varchar, -- c465
	c466 varchar, -- c466
	c467 varchar, -- c467
	c468 varchar, -- c468
	c469 varchar, -- c469
	c470 varchar, -- c470
	c471 varchar, -- c471
	c472 varchar, -- c472
	c473 varchar, -- c473
	c474 varchar, -- c474
	c475 varchar, -- c475
	c476 varchar, -- c476
	c477 varchar, -- c477
	c478 varchar, -- c478
	c479 varchar, -- c479
	c480 varchar, -- c480
	c481 varchar, -- c481
	c482 varchar, -- c482
	c483 varchar, -- c483
	c484 varchar, -- c484
	c485 varchar, -- c485
	c486 varchar, -- c486
	c487 varchar, -- c487
	c488 varchar, -- c488
	c489 varchar, -- c489
	c490 varchar, -- c490
	c491 varchar, -- c491
	c492 varchar, -- c492
	c493 varchar, -- c493
	c494 varchar, -- c494
	c495 varchar, -- c495
	c496 varchar, -- c496
	c497 varchar, -- c497
	c498 varchar, -- c498
	c499 varchar, -- c499
	c500 varchar, -- c500

	CONSTRAINT aacc_subcuenta_new_case_pkey PRIMARY KEY (id)
);

----------------------------------------------------------------------------------------

-- TABLA: Bloque de AACC

DROP TABLE IF EXISTS cclip.aacc_superficie_new_case CASCADE;
CREATE TABLE cclip.aacc_superficie_new_case
(
	id varchar NOT NULL, -- id
	erased boolean NOT NULL, -- erased
	audit_date_create timestamp NOT NULL, -- auditDateCreate
	audit_user_create varchar NOT NULL, -- auditUserCreate
	c1 varchar, -- c1
	c2 varchar, -- c2
	c3 varchar, -- c3
	c4 varchar, -- c4
	c5 varchar, -- c5
	c6 varchar, -- c6
	c7 varchar, -- c7
	c8 varchar, -- c8
	c9 varchar, -- c9
	c10 varchar, -- c10
	c11 varchar, -- c11
	c12 varchar, -- c12
	c13 varchar, -- c13
	c14 varchar, -- c14
	c15 varchar, -- c15
	c16 varchar, -- c16
	c17 varchar, -- c17
	c18 varchar, -- c18
	c19 varchar, -- c19
	c20 varchar, -- c20
	c21 varchar, -- c21
	c22 varchar, -- c22
	c23 varchar, -- c23
	c24 varchar, -- c24
	c25 varchar, -- c25
	c26 varchar, -- c26
	c27 varchar, -- c27
	c28 varchar, -- c28
	c29 varchar, -- c29
	c30 varchar, -- c30
	c31 varchar, -- c31
	c32 varchar, -- c32
	c33 varchar, -- c33
	c34 varchar, -- c34
	c35 varchar, -- c35
	c36 varchar, -- c36
	c37 varchar, -- c37
	c38 varchar, -- c38
	c39 varchar, -- c39
	c40 varchar, -- c40
	c41 varchar, -- c41
	c42 varchar, -- c42
	c43 varchar, -- c43
	c44 varchar, -- c44
	c45 varchar, -- c45
	c46 varchar, -- c46
	c47 varchar, -- c47
	c48 varchar, -- c48
	c49 varchar, -- c49
	c50 varchar, -- c50
	c51 varchar, -- c51
	c52 varchar, -- c52
	c53 varchar, -- c53
	c54 varchar, -- c54
	c55 varchar, -- c55
	c56 varchar, -- c56
	c57 varchar, -- c57
	c58 varchar, -- c58
	c59 varchar, -- c59
	c60 varchar, -- c60
	c61 varchar, -- c61
	c62 varchar, -- c62
	c63 varchar, -- c63
	c64 varchar, -- c64
	c65 varchar, -- c65
	c66 varchar, -- c66
	c67 varchar, -- c67
	c68 varchar, -- c68
	c69 varchar, -- c69
	c70 varchar, -- c70
	c71 varchar, -- c71
	c72 varchar, -- c72
	c73 varchar, -- c73
	c74 varchar, -- c74
	c75 varchar, -- c75
	c76 varchar, -- c76
	c77 varchar, -- c77
	c78 varchar, -- c78
	c79 varchar, -- c79
	c80 varchar, -- c80
	c81 varchar, -- c81
	c82 varchar, -- c82
	c83 varchar, -- c83
	c84 varchar, -- c84
	c85 varchar, -- c85
	c86 varchar, -- c86
	c87 varchar, -- c87
	c88 varchar, -- c88
	c89 varchar, -- c89
	c90 varchar, -- c90
	c91 varchar, -- c91
	c92 varchar, -- c92
	c93 varchar, -- c93
	c94 varchar, -- c94
	c95 varchar, -- c95
	c96 varchar, -- c96
	c97 varchar, -- c97
	c98 varchar, -- c98
	c99 varchar, -- c99
	c100 varchar, -- c100
	c101 varchar, -- c101
	c102 varchar, -- c102
	c103 varchar, -- c103
	c104 varchar, -- c104
	c105 varchar, -- c105
	c106 varchar, -- c106
	c107 varchar, -- c107
	c108 varchar, -- c108
	c109 varchar, -- c109
	c110 varchar, -- c110
	c111 varchar, -- c111
	c112 varchar, -- c112
	c113 varchar, -- c113
	c114 varchar, -- c114
	c115 varchar, -- c115
	c116 varchar, -- c116
	c117 varchar, -- c117
	c118 varchar, -- c118
	c119 varchar, -- c119
	c120 varchar, -- c120
	c121 varchar, -- c121
	c122 varchar, -- c122
	c123 varchar, -- c123
	c124 varchar, -- c124
	c125 varchar, -- c125
	c126 varchar, -- c126
	c127 varchar, -- c127
	c128 varchar, -- c128
	c129 varchar, -- c129
	c130 varchar, -- c130
	c131 varchar, -- c131
	c132 varchar, -- c132
	c133 varchar, -- c133
	c134 varchar, -- c134
	c135 varchar, -- c135
	c136 varchar, -- c136
	c137 varchar, -- c137
	c138 varchar, -- c138
	c139 varchar, -- c139
	c140 varchar, -- c140
	c141 varchar, -- c141
	c142 varchar, -- c142
	c143 varchar, -- c143
	c144 varchar, -- c144
	c145 varchar, -- c145
	c146 varchar, -- c146
	c147 varchar, -- c147
	c148 varchar, -- c148
	c149 varchar, -- c149
	c150 varchar, -- c150
	c151 varchar, -- c151
	c152 varchar, -- c152
	c153 varchar, -- c153
	c154 varchar, -- c154
	c155 varchar, -- c155
	c156 varchar, -- c156
	c157 varchar, -- c157
	c158 varchar, -- c158
	c159 varchar, -- c159
	c160 varchar, -- c160
	c161 varchar, -- c161
	c162 varchar, -- c162
	c163 varchar, -- c163
	c164 varchar, -- c164
	c165 varchar, -- c165
	c166 varchar, -- c166
	c167 varchar, -- c167
	c168 varchar, -- c168
	c169 varchar, -- c169
	c170 varchar, -- c170
	c171 varchar, -- c171
	c172 varchar, -- c172
	c173 varchar, -- c173
	c174 varchar, -- c174
	c175 varchar, -- c175
	c176 varchar, -- c176
	c177 varchar, -- c177
	c178 varchar, -- c178
	c179 varchar, -- c179
	c180 varchar, -- c180
	c181 varchar, -- c181
	c182 varchar, -- c182
	c183 varchar, -- c183
	c184 varchar, -- c184
	c185 varchar, -- c185
	c186 varchar, -- c186
	c187 varchar, -- c187
	c188 varchar, -- c188
	c189 varchar, -- c189
	c190 varchar, -- c190
	c191 varchar, -- c191
	c192 varchar, -- c192
	c193 varchar, -- c193
	c194 varchar, -- c194
	c195 varchar, -- c195
	c196 varchar, -- c196
	c197 varchar, -- c197
	c198 varchar, -- c198
	c199 varchar, -- c199
	c200 varchar, -- c200
	c201 varchar, -- c201
	c202 varchar, -- c202
	c203 varchar, -- c203
	c204 varchar, -- c204
	c205 varchar, -- c205
	c206 varchar, -- c206
	c207 varchar, -- c207
	c208 varchar, -- c208
	c209 varchar, -- c209
	c210 varchar, -- c210
	c211 varchar, -- c211
	c212 varchar, -- c212
	c213 varchar, -- c213
	c214 varchar, -- c214
	c215 varchar, -- c215
	c216 varchar, -- c216
	c217 varchar, -- c217
	c218 varchar, -- c218
	c219 varchar, -- c219
	c220 varchar, -- c220
	c221 varchar, -- c221
	c222 varchar, -- c222
	c223 varchar, -- c223
	c224 varchar, -- c224
	c225 varchar, -- c225
	c226 varchar, -- c226
	c227 varchar, -- c227
	c228 varchar, -- c228
	c229 varchar, -- c229
	c230 varchar, -- c230
	c231 varchar, -- c231
	c232 varchar, -- c232
	c233 varchar, -- c233
	c234 varchar, -- c234
	c235 varchar, -- c235
	c236 varchar, -- c236
	c237 varchar, -- c237
	c238 varchar, -- c238
	c239 varchar, -- c239
	c240 varchar, -- c240
	c241 varchar, -- c241
	c242 varchar, -- c242
	c243 varchar, -- c243
	c244 varchar, -- c244
	c245 varchar, -- c245
	c246 varchar, -- c246
	c247 varchar, -- c247
	c248 varchar, -- c248
	c249 varchar, -- c249
	c250 varchar, -- c250
	c251 varchar, -- c251
	c252 varchar, -- c252
	c253 varchar, -- c253
	c254 varchar, -- c254
	c255 varchar, -- c255
	c256 varchar, -- c256
	c257 varchar, -- c257
	c258 varchar, -- c258
	c259 varchar, -- c259
	c260 varchar, -- c260
	c261 varchar, -- c261
	c262 varchar, -- c262
	c263 varchar, -- c263
	c264 varchar, -- c264
	c265 varchar, -- c265
	c266 varchar, -- c266
	c267 varchar, -- c267
	c268 varchar, -- c268
	c269 varchar, -- c269
	c270 varchar, -- c270
	c271 varchar, -- c271
	c272 varchar, -- c272
	c273 varchar, -- c273
	c274 varchar, -- c274
	c275 varchar, -- c275
	c276 varchar, -- c276
	c277 varchar, -- c277
	c278 varchar, -- c278
	c279 varchar, -- c279
	c280 varchar, -- c280
	c281 varchar, -- c281
	c282 varchar, -- c282
	c283 varchar, -- c283
	c284 varchar, -- c284
	c285 varchar, -- c285
	c286 varchar, -- c286
	c287 varchar, -- c287
	c288 varchar, -- c288
	c289 varchar, -- c289
	c290 varchar, -- c290
	c291 varchar, -- c291
	c292 varchar, -- c292
	c293 varchar, -- c293
	c294 varchar, -- c294
	c295 varchar, -- c295
	c296 varchar, -- c296
	c297 varchar, -- c297
	c298 varchar, -- c298
	c299 varchar, -- c299
	c300 varchar, -- c300
	c301 varchar, -- c301
	c302 varchar, -- c302
	c303 varchar, -- c303
	c304 varchar, -- c304
	c305 varchar, -- c305
	c306 varchar, -- c306
	c307 varchar, -- c307
	c308 varchar, -- c308
	c309 varchar, -- c309
	c310 varchar, -- c310
	c311 varchar, -- c311
	c312 varchar, -- c312
	c313 varchar, -- c313
	c314 varchar, -- c314
	c315 varchar, -- c315
	c316 varchar, -- c316
	c317 varchar, -- c317
	c318 varchar, -- c318
	c319 varchar, -- c319
	c320 varchar, -- c320
	c321 varchar, -- c321
	c322 varchar, -- c322
	c323 varchar, -- c323
	c324 varchar, -- c324
	c325 varchar, -- c325
	c326 varchar, -- c326
	c327 varchar, -- c327
	c328 varchar, -- c328
	c329 varchar, -- c329
	c330 varchar, -- c330
	c331 varchar, -- c331
	c332 varchar, -- c332
	c333 varchar, -- c333
	c334 varchar, -- c334
	c335 varchar, -- c335
	c336 varchar, -- c336
	c337 varchar, -- c337
	c338 varchar, -- c338
	c339 varchar, -- c339
	c340 varchar, -- c340
	c341 varchar, -- c341
	c342 varchar, -- c342
	c343 varchar, -- c343
	c344 varchar, -- c344
	c345 varchar, -- c345
	c346 varchar, -- c346
	c347 varchar, -- c347
	c348 varchar, -- c348
	c349 varchar, -- c349
	c350 varchar, -- c350
	c351 varchar, -- c351
	c352 varchar, -- c352
	c353 varchar, -- c353
	c354 varchar, -- c354
	c355 varchar, -- c355
	c356 varchar, -- c356
	c357 varchar, -- c357
	c358 varchar, -- c358
	c359 varchar, -- c359
	c360 varchar, -- c360
	c361 varchar, -- c361
	c362 varchar, -- c362
	c363 varchar, -- c363
	c364 varchar, -- c364
	c365 varchar, -- c365
	c366 varchar, -- c366
	c367 varchar, -- c367
	c368 varchar, -- c368
	c369 varchar, -- c369
	c370 varchar, -- c370
	c371 varchar, -- c371
	c372 varchar, -- c372
	c373 varchar, -- c373
	c374 varchar, -- c374
	c375 varchar, -- c375
	c376 varchar, -- c376
	c377 varchar, -- c377
	c378 varchar, -- c378
	c379 varchar, -- c379
	c380 varchar, -- c380
	c381 varchar, -- c381
	c382 varchar, -- c382
	c383 varchar, -- c383
	c384 varchar, -- c384
	c385 varchar, -- c385
	c386 varchar, -- c386
	c387 varchar, -- c387
	c388 varchar, -- c388
	c389 varchar, -- c389
	c390 varchar, -- c390
	c391 varchar, -- c391
	c392 varchar, -- c392
	c393 varchar, -- c393
	c394 varchar, -- c394
	c395 varchar, -- c395
	c396 varchar, -- c396
	c397 varchar, -- c397
	c398 varchar, -- c398
	c399 varchar, -- c399
	c400 varchar, -- c400
	c401 varchar, -- c401
	c402 varchar, -- c402
	c403 varchar, -- c403
	c404 varchar, -- c404
	c405 varchar, -- c405
	c406 varchar, -- c406
	c407 varchar, -- c407
	c408 varchar, -- c408
	c409 varchar, -- c409
	c410 varchar, -- c410
	c411 varchar, -- c411
	c412 varchar, -- c412
	c413 varchar, -- c413
	c414 varchar, -- c414
	c415 varchar, -- c415
	c416 varchar, -- c416
	c417 varchar, -- c417
	c418 varchar, -- c418
	c419 varchar, -- c419
	c420 varchar, -- c420
	c421 varchar, -- c421
	c422 varchar, -- c422
	c423 varchar, -- c423
	c424 varchar, -- c424
	c425 varchar, -- c425
	c426 varchar, -- c426
	c427 varchar, -- c427
	c428 varchar, -- c428
	c429 varchar, -- c429
	c430 varchar, -- c430
	c431 varchar, -- c431
	c432 varchar, -- c432
	c433 varchar, -- c433
	c434 varchar, -- c434
	c435 varchar, -- c435
	c436 varchar, -- c436
	c437 varchar, -- c437
	c438 varchar, -- c438
	c439 varchar, -- c439
	c440 varchar, -- c440
	c441 varchar, -- c441
	c442 varchar, -- c442
	c443 varchar, -- c443
	c444 varchar, -- c444
	c445 varchar, -- c445
	c446 varchar, -- c446
	c447 varchar, -- c447
	c448 varchar, -- c448
	c449 varchar, -- c449
	c450 varchar, -- c450
	c451 varchar, -- c451
	c452 varchar, -- c452
	c453 varchar, -- c453
	c454 varchar, -- c454
	c455 varchar, -- c455
	c456 varchar, -- c456
	c457 varchar, -- c457
	c458 varchar, -- c458
	c459 varchar, -- c459
	c460 varchar, -- c460
	c461 varchar, -- c461
	c462 varchar, -- c462
	c463 varchar, -- c463
	c464 varchar, -- c464
	c465 varchar, -- c465
	c466 varchar, -- c466
	c467 varchar, -- c467
	c468 varchar, -- c468
	c469 varchar, -- c469
	c470 varchar, -- c470
	c471 varchar, -- c471
	c472 varchar, -- c472
	c473 varchar, -- c473
	c474 varchar, -- c474
	c475 varchar, -- c475
	c476 varchar, -- c476
	c477 varchar, -- c477
	c478 varchar, -- c478
	c479 varchar, -- c479
	c480 varchar, -- c480
	c481 varchar, -- c481
	c482 varchar, -- c482
	c483 varchar, -- c483
	c484 varchar, -- c484
	c485 varchar, -- c485
	c486 varchar, -- c486
	c487 varchar, -- c487
	c488 varchar, -- c488
	c489 varchar, -- c489
	c490 varchar, -- c490
	c491 varchar, -- c491
	c492 varchar, -- c492
	c493 varchar, -- c493
	c494 varchar, -- c494
	c495 varchar, -- c495
	c496 varchar, -- c496
	c497 varchar, -- c497
	c498 varchar, -- c498
	c499 varchar, -- c499
	c500 varchar, -- c500

	CONSTRAINT aacc_superficie_new_case_pkey PRIMARY KEY (id)
);



-- ===================================================================================================

INSERT INTO cclip.aacc_unidad_new_case ( SELECT * FROM cclip.aacc_unidad a WHERE TRIM(a.id)::VARCHAR IN (SELECT id FROM cclip.aacc_unidad_header_new_case));

-- SELECT * FROM  cclip.aacc_unidad_new_case OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.aacc_unidad_new_case; -- 84207

-- SELECT COUNT(*) FROM  cclip.aacc_unidad_header_new_case; -- 84207

-- SELECT * FROM  cclip.aacc_unidad_header_new_case OFFSET '0' LIMIT '100';


UPDATE cclip.aacc_unidad_new_case SET 
		
		c500 = subquery.cadastral_code,
		c499 = subquery.sub_cta,
		c498 = subquery.uf_id,
		c497 = subquery.cta_cli,
		c496 = subquery.city_area_id
	FROM (
		SELECT * FROM cclip.aacc_unidad_header_new_case
	) AS subquery
WHERE 	cclip.aacc_unidad_new_case.id = subquery.id;

/*
UPDATE cclip.aacc_unidad_new_case SET c500 = (SELECT cadastral_code FROM cclip.aacc_unidad_header_new_case WHERE  aacc_unidad_header_new_case.id = aacc_unidad_new_case.id);
UPDATE cclip.aacc_unidad_new_case SET c499 = (SELECT sub_cta FROM cclip.aacc_unidad_header_new_case WHERE  aacc_unidad_header_new_case.id = aacc_unidad_new_case.id);
UPDATE cclip.aacc_unidad_new_case SET c498 = (SELECT uf_id FROM cclip.aacc_unidad_header_new_case WHERE  aacc_unidad_header_new_case.id = aacc_unidad_new_case.id);
UPDATE cclip.aacc_unidad_new_case SET c497 = (SELECT ct_cli FROM cclip.aacc_unidad_header_new_case WHERE  aacc_unidad_header_new_case.id = aacc_unidad_new_case.id);
UPDATE cclip.aacc_unidad_new_case SET c496 = (SELECT city_area_id FROM cclip.aacc_unidad_header_new_case WHERE  aacc_unidad_header_new_case.id = aacc_unidad_new_case.id);
*/
-- ===================================================================================================

INSERT INTO cclip.aacc_inmueble_new_case ( SELECT * FROM cclip.aacc_inmueble a WHERE TRIM(a.id)::VARCHAR IN (SELECT id FROM cclip.aacc_inmueble_header_new_case));

-- SELECT * FROM  cclip.aacc_inmueble_new_case OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.aacc_inmueble_new_case; -- 28388

-- SELECT COUNT(*) FROM  cclip.v_aacc_inmueble_header_new_case; -- 28388

-- SELECT COUNT(*) FROM  cclip.aacc_inmueble_new_case WHERE c494 = 'PH'; -- 28388

-- SELECT * FROM  cclip.v_aacc_inmueble_header_new_case OFFSET '0' LIMIT '100';

UPDATE cclip.aacc_inmueble_new_case SET 
		
		c500 = subquery.cadastral_code,
		c499 = subquery.sub_cta,
		c498 = subquery.uf_id,
		c497 = subquery.cta_cli,
		c496 = subquery.city_area_id,
		c495 = subquery.dv,
		c494 = subquery.cadastre_type_id
	FROM (
		SELECT * FROM cclip.aacc_inmueble_header_new_case
	) AS subquery
WHERE 	cclip.aacc_inmueble_new_case.id = subquery.id;


-- ===================================================================================================

INSERT INTO cclip.aacc_subcuenta_new_case ( SELECT * FROM cclip.aacc_subcuenta a WHERE TRIM(a.id)::VARCHAR IN (SELECT id FROM cclip.aacc_subcuenta_header_new_case));

-- SELECT * FROM  cclip.aacc_subcuenta_new_case OFFSET '0' LIMIT '100';

-- SELECT COUNT(*) FROM  cclip.aacc_subcuenta_new_case; -- 84165

-- SELECT COUNT(*) FROM  cclip.v_aacc_subcuenta_header_new_case; -- 84165

-- SELECT * FROM  cclip.v_aacc_subcuenta_header_new_case OFFSET '0' LIMIT '100';

UPDATE cclip.aacc_subcuenta_new_case SET 
		
		c500 = subquery.cadastral_code,
		c499 = subquery.sub_cta,
		c498 = subquery.uf_id,
		c497 = subquery.cta_cli,
		c496 = subquery.city_area_id,
		c495 = subquery.dv,
		c494 = subquery.cadastre_type_id
	FROM (
		SELECT * FROM cclip.aacc_subcuenta_header_new_case
	) AS subquery
WHERE 	cclip.aacc_subcuenta_new_case.id = subquery.id;

-- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_uf_aacc CASCADE;

CREATE OR REPLACE VIEW cclip.v_uf_aacc AS 

	SELECT 	TRIM(c498)::varchar AS id,
		false AS erased, -- erased
		INITCAP(LOWER(TRIM(c3))) AS name, -- Nombre
		CASE 	WHEN c25 IS NULL THEN null
			WHEN TRIM(c25)::VARCHAR = '0' THEN null
			WHEN cclip.is_number(TRIM(c25)::VARCHAR) = true THEN REPLACE(TRIM(c25), '.0', '')
			ELSE TRIM(c25)::VARCHAR
		END AS dni, -- DNI	
		CASE 	WHEN c25 IS NULL THEN null
			WHEN TRIM(c25)::VARCHAR = '0' THEN null
			WHEN cclip.is_number(TRIM(c25)::VARCHAR) = true THEN REPLACE(TRIM(c25), '.0', '')
			ELSE TRIM(c25)::VARCHAR
		END AS cuit, -- CUIL/CUIT	
		--TRIM(c25) AS dni, -- DNI
		--TRIM(c25) AS cuit, -- CUIL/CUIT
		TRIM(c49) AS phone, -- Teléfono
		('DDZZMMPPPppp:' || COALESCE(c500, '') || ', Cta. Cli:' || COALESCE(c497, '') || ' ' || COALESCE(c496, '')) AS comment, -- Comentario
		'AR' AS country_code, -- País - Código ISO 3166-1 Alfa-2
		'Argentina' AS country, -- País
		'AR-X' AS admin_area_level1_code, -- Provincia - Código ISO 3166-2
		'Córdoba' AS admin_area_level1, -- Provincia
		'Capital' AS admin_area_level2, -- Departamento / Distrito
		'Córdoba' AS locality, -- Localidad
		INITCAP(LOWER(TRIM(c6))) AS street, -- Calle
		TRIM(c7) AS street_number, -- Número de Calle
		TRIM(c8) AS building_floor, -- Planta del Edificio
		TRIM(c9) AS building_room, -- Departamento de Edificio
		null::VARCHAR AS building, -- Edificio
		INITCAP(LOWER(TRIM(c10))) AS comment_address, -- Comentario del Domicilio
		null::double precision AS lat, -- Latitud
		null::double precision AS lng, -- Longitud
		audit_date_create, -- auditDateCreate
		audit_user_create, -- auditUserCreate
		audit_date_create AS audit_date_update, -- auditDateUpdate
		audit_user_create AS audit_user_update, -- auditUserUpdate
		1 AS audit_version, -- auditVersion
		'20' AS audit_version_code_label, -- auditVersionCodeLabel
		'Proveniente de Base Congelada' AS audit_version_label, -- auditVersionLabel
	
		CASE 	WHEN TRIM(c26) IN (SELECT id FROM cclip.iva) THEN TRIM(c26)
			ELSE null
		END AS iva_id, -- Condición de IVA

		CASE 	WHEN TRIM(c11) IN (SELECT id FROM cclip.neighbourhood) THEN TRIM(c11)
			ELSE null
		END AS neighbourhood_id -- Vecindario		
		
	FROM 	cclip.aacc_unidad_new_case
	WHERE	TRIM(c498)::VARCHAR NOT IN ( SELECT id::VARCHAR FROM cclip.uf);

-- SELECT * FROM cclip.v_uf_aacc LIMIT 100;
-- SELECT COUNT(*) FROM  cclip.v_uf_aacc; -- 7411

-- ===================================================================================================

DELETE FROM cclip.uf WHERE uf.id IN ( SELECT id FROM cclip.v_uf_aacc);

INSERT INTO cclip.uf (SELECT * FROM cclip.v_uf_aacc WHERE v_uf_aacc.id::VARCHAR NOT IN ( SELECT id::VARCHAR FROM cclip.uf));

-- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_cadastre_aacc CASCADE;

CREATE OR REPLACE VIEW cclip.v_cadastre_aacc AS 

	SELECT 	id::VARCHAR, 
		false::BOOLEAN AS erased, 
		TRIM(c4)::TIMESTAMP AS date_create, 
		--TRIM(c4) AS date_createx, 
		TRIM(c17)::TIMESTAMP AS date_delete, 
		TRIM(c500)::VARCHAR AS cadastral_code, 
		TRIM(c20)::double precision AS m2, 
		TRIM(c21)::double precision AS m2_covered, 
		TRIM(c497)::VARCHAR AS cta_cli, 
		TRIM(c499)::VARCHAR AS sub_cta_cli, 
		TRIM(c495)::VARCHAR AS dv, 
		(	
			SELECT	INITCAP(LOWER(TRIM(c9)))::VARCHAR 
			FROM 	cclip.aacc_subcuenta_new_case 
			WHERE 	aacc_subcuenta_new_case.c500::VARCHAR = aacc_inmueble_new_case.c500::VARCHAR
				AND aacc_subcuenta_new_case.c499::VARCHAR = aacc_inmueble_new_case.c499::VARCHAR
				AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c498::VARCHAR
				--AND aacc_subcuenta_new_case.c497::VARCHAR = aacc_inmueble_new_case.c497::VARCHAR
				--AND aacc_subcuenta_new_case.c496::VARCHAR = aacc_inmueble_new_case.c496::VARCHAR
				--AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c494::VARCHAR
		) AS user_water_service, -- lo sacamos de subcuenta
		--null AS user_water_service, 
		(	
			SELECT	CASE 	WHEN c11 IS NULL THEN null
					WHEN TRIM(c11)::VARCHAR = '0' THEN null
					WHEN cclip.is_number(TRIM(c11)::VARCHAR) = true THEN REPLACE(TRIM(c11), '.0', '')::VARCHAR
					ELSE TRIM(c11)::VARCHAR
				END AS dni -- DNI	
			FROM 	cclip.aacc_subcuenta_new_case 
			WHERE 	aacc_subcuenta_new_case.c500::VARCHAR = aacc_inmueble_new_case.c500::VARCHAR
				AND aacc_subcuenta_new_case.c499::VARCHAR = aacc_inmueble_new_case.c499::VARCHAR
				AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c498::VARCHAR
				--AND aacc_subcuenta_new_case.c497::VARCHAR = aacc_inmueble_new_case.c497::VARCHAR
				--AND aacc_subcuenta_new_case.c496::VARCHAR = aacc_inmueble_new_case.c496::VARCHAR
				--AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c494::VARCHAR
		) AS user_water_service_dni, -- lo sacamos de subcuenta
		--null AS user_water_service_dni, 
		(	
			SELECT	CASE 	WHEN c11 IS NULL THEN null
					WHEN TRIM(c11)::VARCHAR = '0' THEN null
					WHEN cclip.is_number(TRIM(c11)::VARCHAR) = true THEN REPLACE(TRIM(c11), '.0', '')::VARCHAR
					ELSE TRIM(c11)::VARCHAR
				END AS cuit -- CUIT	
			FROM 	cclip.aacc_subcuenta_new_case 
			WHERE 	aacc_subcuenta_new_case.c500::VARCHAR = aacc_inmueble_new_case.c500::VARCHAR
				AND aacc_subcuenta_new_case.c499::VARCHAR = aacc_inmueble_new_case.c499::VARCHAR
				AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c498::VARCHAR
				--AND aacc_subcuenta_new_case.c497::VARCHAR = aacc_inmueble_new_case.c497::VARCHAR
				--AND aacc_subcuenta_new_case.c496::VARCHAR = aacc_inmueble_new_case.c496::VARCHAR
				--AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c494::VARCHAR
		) AS user_water_service_cuit, -- lo sacamos de subcuenta
		--null AS user_water_service_cuit, 
		false::BOOLEAN AS water_meter, 
		('DDZZMMPPPppp:' || COALESCE(c500, '')  || ', UF:' || COALESCE(c498, '') || ', Cta. Cli:' || COALESCE(c497, '') || ' ' || COALESCE(c496, ''))::VARCHAR AS comment, -- Comentario
		'Córdoba' AS inm_locality, 
		INITCAP(LOWER(TRIM(c11))) || ' ' || COALESCE(TRIM(c6), '')::VARCHAR AS inm_neighbourhood_comment, 
		INITCAP(LOWER(TRIM(c9)))::VARCHAR AS inm_street, 
		TRIM(c10)::VARCHAR AS inm_street_number, 
		null::BOOLEAN AS inm_street_number_estimated, 
		null::VARCHAR AS inm_building_floor, -- lo sacamos de subcuenta, pero en la madre va null
		null::VARCHAR AS inm_building_room, -- lo sacamos de subcuenta, pero en la madre va null
		null::VARCHAR AS inm_building, 
		null::VARCHAR AS inm_postal_code, 
		INITCAP(LOWER(TRIM(c11))) || ' ' || COALESCE(TRIM(c6), '') AS inm_comment_address, 
		null::double precision AS inm_lat, 
		null::double precision AS inm_lng, 
		null AS user_postal_country_code, 
		null AS user_postal_country, 
		null AS user_postal_admin_area_level1_code, 
		null AS user_postal_admin_area_level1, 
		null AS user_postal_admin_area_level2, 
		null AS user_postal_locality, 
		null AS user_postal_neighbourhood, 
		null AS user_postal_street, 
		null AS user_postal_street_number, 
		null AS user_postal_building_floor, 
		null AS user_postal_building_room, 
		null AS user_postal_building, 
		null AS user_postal_postal_code, 
		null AS user_postal_comment_address, 
		null::double precision AS user_postal_lat, 
		null::double precision AS user_postal_lng, 
		null AS user_phone, 
		TRIM(c18) AS code_reason_low, 
		TRIM(c19) AS reason_low, 
		TRIM(c15)::INTEGER AS cant_ph, 
		null::TIMESTAMP AS date_scanning2, 
		null::TIMESTAMP AS date_scanning1, 
		null::TIMESTAMP AS date_scanning0, 
		audit_date_create, 
		audit_user_create, 
		audit_date_create AS audit_date_update, 
		audit_user_create AS audit_user_update, 
		1 AS audit_version, 
		'20' AS audit_version_code_label, 
		'Proveniente de Base Congelada' AS audit_version_label, 
		false::BOOLEAN AS fact, 
		TRIM(c23)::double precision AS m2_covered_shared, 
		TRIM(c25)::double precision AS m2_covered_expanded, 
		/*
		(	
			SELECT	TRIM(c19) 
			FROM 	cclip.aacc_subcuenta_new_case 
			WHERE 	aacc_subcuenta_new_case.c500::VARCHAR = aacc_inmueble_new_case.c500::VARCHAR
				AND aacc_subcuenta_new_case.c499::VARCHAR = aacc_inmueble_new_case.c499::VARCHAR
				AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c498::VARCHAR
				--AND aacc_subcuenta_new_case.c497::VARCHAR = aacc_inmueble_new_case.c497::VARCHAR
				--AND aacc_subcuenta_new_case.c496::VARCHAR = aacc_inmueble_new_case.c496::VARCHAR
				--AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c494::VARCHAR
		) AS m2_percent, -- lo sacamos de subcuenta
		*/
		null::double precision AS m2_percent, 
		null::INTEGER AS year_building, 
		null::double precision AS progress_construction_prev, 
		null AS user_iva_id, 
		null AS user_water_situation_id, 
		TRIM(c498) AS uf_id, 
		TRIM(c496) AS city_area_id, 
		TRIM(c494) AS cadastre_type_id, 
		null AS cadastre_situation_id, 
		CASE 	WHEN TRIM(c6) IN (SELECT id FROM cclip.neighbourhood) THEN TRIM(c6)
			ELSE null
		END AS inm_neighbourhood_id, 
		(	
			SELECT	TRIM(c7) 
			FROM 	cclip.aacc_subcuenta_new_case 
			WHERE 	aacc_subcuenta_new_case.c500::VARCHAR = aacc_inmueble_new_case.c500::VARCHAR
				AND aacc_subcuenta_new_case.c499::VARCHAR = aacc_inmueble_new_case.c499::VARCHAR
				AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c498::VARCHAR
				--AND aacc_subcuenta_new_case.c497::VARCHAR = aacc_inmueble_new_case.c497::VARCHAR
				--AND aacc_subcuenta_new_case.c496::VARCHAR = aacc_inmueble_new_case.c496::VARCHAR
				--AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c494::VARCHAR
		) AS cadastre_sub_division_type_id, -- lo sacamos de subcuenta
		--null AS cadastre_sub_division_type_id, -- lo sacamos de subcuenta
		null AS schedule_scanning_result2_id, 
		null AS schedule_scanning_result1_id, 
		null AS schedule_scanning_result0_id, 
		(	
			SELECT	CASE 	WHEN TRIM(c22)  IN (SELECT id FROM cclip.cadastre_activity_type) THEN TRIM(c22)
					ELSE null
				END AS 	cadastre_activity_type_id
				
			FROM 	cclip.aacc_subcuenta_new_case 
			WHERE 	aacc_subcuenta_new_case.c500::VARCHAR = aacc_inmueble_new_case.c500::VARCHAR
				AND aacc_subcuenta_new_case.c499::VARCHAR = aacc_inmueble_new_case.c499::VARCHAR
				AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c498::VARCHAR
				--AND aacc_subcuenta_new_case.c497::VARCHAR = aacc_inmueble_new_case.c497::VARCHAR
				--AND aacc_subcuenta_new_case.c496::VARCHAR = aacc_inmueble_new_case.c496::VARCHAR
				--AND aacc_subcuenta_new_case.c498::VARCHAR = aacc_inmueble_new_case.c494::VARCHAR
		) AS cadastre_activity_type_id, -- lo sacamos de subcuenta
		null AS water_meter_type_id, 
		null AS cadastre_constructive_type_id
		
	  FROM cclip.aacc_inmueble_new_case;
	--  WHERE	TRIM(id)::VARCHAR NOT IN ( SELECT id::VARCHAR FROM cclip.cadastre);


-- SELECT * FROM cclip.v_cadastre_aacc LIMIT 100;
-- SELECT * FROM cclip.v_cadastre_aacc WHERE cadastre_type_id = 'PH' AND cadastre_activity_type_id IS NOT NULL LIMIT 100;
-- SELECT COUNT(*) FROM  cclip.v_cadastre_aacc; -- 28388

-- ===================================================================================================
/*
DROP TABLE IF EXISTS cclip.cadastre_aacc CASCADE;

CREATE TABLE cclip.cadastre_aacc
(
	  id character varying NOT NULL,
	  erased boolean NOT NULL,
	  date_create timestamp without time zone,
	  date_delete timestamp without time zone,
	  cadastral_code character varying NOT NULL,
	  m2 double precision,
	  m2_covered double precision,
	  cta_cli character varying,
	  sub_cta_cli character varying,
	  dv character varying,
	  user_water_service character varying,
	  user_water_service_dni character varying,
	  user_water_service_cuit character varying,
	  water_meter boolean,
	  comment character varying,
	  inm_locality character varying,
	  inm_neighbourhood_comment character varying,
	  inm_street character varying,
	  inm_street_number character varying,
	  inm_street_number_estimated boolean,
	  inm_building_floor character varying,
	  inm_building_room character varying,
	  inm_building character varying,
	  inm_postal_code character varying,
	  inm_comment_address character varying,
	  inm_lat double precision,
	  inm_lng double precision,
	  user_postal_country_code character varying,
	  user_postal_country character varying,
	  user_postal_admin_area_level1_code character varying,
	  user_postal_admin_area_level1 character varying,
	  user_postal_admin_area_level2 character varying,
	  user_postal_locality character varying,
	  user_postal_neighbourhood character varying,
	  user_postal_street character varying,
	  user_postal_street_number character varying,
	  user_postal_building_floor character varying,
	  user_postal_building_room character varying,
	  user_postal_building character varying,
	  user_postal_postal_code character varying,
	  user_postal_comment_address character varying,
	  user_postal_lat double precision,
	  user_postal_lng double precision,
	  user_phone character varying,
	  code_reason_low character varying,
	  reason_low character varying,
	  cant_ph integer,
	  date_scanning2 date,
	  date_scanning1 date,
	  date_scanning0 date,
	  audit_date_create timestamp without time zone NOT NULL,
	  audit_user_create character varying NOT NULL,
	  audit_date_update timestamp without time zone,
	  audit_user_update character varying,
	  audit_version bigint NOT NULL,
	  audit_version_code_label character varying,
	  audit_version_label character varying,
	  fact boolean NOT NULL,
	  m2_covered_shared double precision,
	  m2_covered_expanded double precision,
	  m2_percent double precision,
	  year_building integer,
	  progress_construction_prev double precision,
	  user_iva_id character varying,
	  user_water_situation_id character varying,
	  uf_id character varying,
	  city_area_id character varying,
	  cadastre_type_id character varying,
	  cadastre_situation_id character varying,
	  inm_neighbourhood_id character varying,
	  cadastre_sub_division_type_id character varying,
	  schedule_scanning_result2_id character varying,
	  schedule_scanning_result1_id character varying,
	  schedule_scanning_result0_id character varying,
	  cadastre_activity_type_id character varying,
	  water_meter_type_id character varying,
	  cadastre_constructive_type_id character varying
 ); 
*/
--INSERT INTO cclip.cadastre_aacc (SELECT * FROM cclip.v_cadastre_aacc /*WHERE v_cadastre_aacc.cadastral_code::VARCHAR NOT IN ( SELECT cadastral_code::VARCHAR FROM cclip.cadastre)*/);
INSERT INTO cclip.cadastre (SELECT * FROM cclip.v_cadastre_aacc WHERE v_cadastre_aacc.id::VARCHAR NOT IN ( SELECT id::VARCHAR FROM cclip.cadastre));

-- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_aacc_subcuenta_new_case_2 CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_subcuenta_new_case_2 AS 

	SELECT 	aacc_subcuenta_new_case.*
	FROM 	cclip.aacc_subcuenta_new_case 
	JOIN	cclip.aacc_inmueble_new_case
		ON SUBSTRING(aacc_subcuenta_new_case.c500 FROM 0 FOR 11)::VARCHAR = SUBSTRING(aacc_inmueble_new_case.c500 FROM 0 FOR 11)::VARCHAR		
		--AND aacc_subcuenta_new_case.c497::VARCHAR = aacc_inmueble_new_case.c497::VARCHAR -- cta_cli
		--AND aacc_subcuenta_new_case.c496::VARCHAR = aacc_inmueble_new_case.c496::VARCHAR -- city_area_id
		AND aacc_subcuenta_new_case.c494::VARCHAR = aacc_inmueble_new_case.c494::VARCHAR -- cadastre_type_id

	WHERE 	aacc_subcuenta_new_case.c499::VARCHAR <> '000';

-- SELECT * FROM cclip.v_aacc_subcuenta_new_case_2 LIMIT 100
-- SELECT COUNT(*) FROM cclip.v_aacc_subcuenta_new_case_2 -- 12427

-- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_cadastre_ph_aacc CASCADE;

CREATE OR REPLACE VIEW cclip.v_cadastre_ph_aacc AS 

	SELECT 	id::VARCHAR, 
		false::BOOLEAN AS erased, 
		TRIM(c5)::TIMESTAMP AS date_create, 		
		null::TIMESTAMP AS date_delete, 
		TRIM(c500)::VARCHAR AS cadastral_code, 
		null::double precision AS m2,
		null::double precision AS m2_covered, 
		TRIM(c497)::VARCHAR AS cta_cli, 
		TRIM(c499)::VARCHAR AS sub_cta_cli, 
		TRIM(c495)::VARCHAR AS dv, 
		INITCAP(LOWER(TRIM(c9)))::VARCHAR AS user_water_service,		
		CASE 	WHEN c11 IS NULL THEN null
			WHEN TRIM(c11)::VARCHAR = '0' THEN null
			WHEN cclip.is_number(TRIM(c11)::VARCHAR) = true THEN REPLACE(TRIM(c11), '.0', '')::VARCHAR
			ELSE TRIM(c11)::VARCHAR
		END  AS user_water_service_dni,
		CASE 	WHEN c11 IS NULL THEN null
			WHEN TRIM(c11)::VARCHAR = '0' THEN null
			WHEN cclip.is_number(TRIM(c11)::VARCHAR) = true THEN REPLACE(TRIM(c11), '.0', '')::VARCHAR
			ELSE TRIM(c11)::VARCHAR
		END  AS user_water_service_cuit,
		CASE	WHEN TRIM(C20) = 'S' THEN true::BOOLEAN
			ELSE false::BOOLEAN		 
		END AS water_meter, 
		('DDZZMMPPPppp:' || COALESCE(c500, '')  || ', UF:' || COALESCE(c498, '') || ', Cta. Cli:' || COALESCE(c497, '') || ' ' || COALESCE(c496, ''))::VARCHAR AS comment, -- Comentario
		'Córdoba' AS inm_locality, 		
		null::VARCHAR AS inm_neighbourhood_comment, 
		null::VARCHAR AS inm_street, 
		null::VARCHAR AS inm_street_number, 
		null::BOOLEAN AS inm_street_number_estimated, 
		TRIM(c16)::VARCHAR AS inm_building_floor, 
		TRIM(c17)::VARCHAR AS inm_building_room,
		null::VARCHAR AS inm_building, 
		null::VARCHAR AS inm_postal_code, 
		null AS inm_comment_address, 
		null::double precision AS inm_lat, 
		null::double precision AS inm_lng, 
		null AS user_postal_country_code, 
		null AS user_postal_country, 
		null AS user_postal_admin_area_level1_code, 
		null AS user_postal_admin_area_level1, 
		null AS user_postal_admin_area_level2, 
		null AS user_postal_locality, 
		null AS user_postal_neighbourhood, 
		null AS user_postal_street, 
		null AS user_postal_street_number, 
		null AS user_postal_building_floor, 
		null AS user_postal_building_room, 
		null AS user_postal_building, 
		null AS user_postal_postal_code, 
		null AS user_postal_comment_address, 
		null::double precision AS user_postal_lat, 
		null::double precision AS user_postal_lng, 
		null AS user_phone, 
		null AS code_reason_low, 
		null AS reason_low, 
		null::INTEGER AS cant_ph, 
		null::TIMESTAMP AS date_scanning2, 
		null::TIMESTAMP AS date_scanning1, 
		null::TIMESTAMP AS date_scanning0, 
		audit_date_create, 
		audit_user_create, 
		audit_date_create AS audit_date_update, 
		audit_user_create AS audit_user_update, 
		1 AS audit_version, 
		'20' AS audit_version_code_label, 
		'Proveniente de Base Congelada' AS audit_version_label, 
		false::BOOLEAN AS fact, 
		null::double precision AS m2_covered_shared, 
		null::double precision AS m2_covered_expanded, 		
		TRIM(c19)::double precision AS m2_percent, 
		TRIM(c15)::INTEGER AS year_building, 
		null::double precision AS progress_construction_prev, 
		null AS user_iva_id, 
		null AS user_water_situation_id, 
		TRIM(c498) AS uf_id, 
		TRIM(c496) AS city_area_id, 
		TRIM(c494) AS cadastre_type_id, 
		null AS cadastre_situation_id, 
		null inm_neighbourhood_id, 
		TRIM(c7) AS cadastre_sub_division_type_id, 		
		null AS schedule_scanning_result2_id, 
		null AS schedule_scanning_result1_id, 
		null AS schedule_scanning_result0_id, 

		CASE 	WHEN TRIM(c22)  IN (SELECT id FROM cclip.cadastre_activity_type) THEN TRIM(c22)
			ELSE null
		END AS 	cadastre_activity_type_id
		--TRIM(c22)  AS cadastre_activity_type_id, 
		
		null AS water_meter_type_id, 
		null AS cadastre_constructive_type_id
		
	  FROM cclip.v_aacc_subcuenta_new_case_2;
	 -- WHERE	TRIM(id)::VARCHAR NOT IN ( SELECT id::VARCHAR FROM cclip.cadastre);


-- SELECT * FROM cclip.v_cadastre_ph_aacc LIMIT 100;
-- SELECT COUNT(*) FROM  cclip.v_cadastre_ph_aacc; -- 12427

-- ===================================================================================================
/*
DROP TABLE IF EXISTS cclip.cadastre_ph_aacc CASCADE;

CREATE TABLE cclip.cadastre_ph_aacc
(
	  id character varying NOT NULL,
	  erased boolean NOT NULL,
	  date_create timestamp without time zone,
	  date_delete timestamp without time zone,
	  cadastral_code character varying NOT NULL,
	  m2 double precision,
	  m2_covered double precision,
	  cta_cli character varying,
	  sub_cta_cli character varying,
	  dv character varying,
	  user_water_service character varying,
	  user_water_service_dni character varying,
	  user_water_service_cuit character varying,
	  water_meter boolean,
	  comment character varying,
	  inm_locality character varying,
	  inm_neighbourhood_comment character varying,
	  inm_street character varying,
	  inm_street_number character varying,
	  inm_street_number_estimated boolean,
	  inm_building_floor character varying,
	  inm_building_room character varying,
	  inm_building character varying,
	  inm_postal_code character varying,
	  inm_comment_address character varying,
	  inm_lat double precision,
	  inm_lng double precision,
	  user_postal_country_code character varying,
	  user_postal_country character varying,
	  user_postal_admin_area_level1_code character varying,
	  user_postal_admin_area_level1 character varying,
	  user_postal_admin_area_level2 character varying,
	  user_postal_locality character varying,
	  user_postal_neighbourhood character varying,
	  user_postal_street character varying,
	  user_postal_street_number character varying,
	  user_postal_building_floor character varying,
	  user_postal_building_room character varying,
	  user_postal_building character varying,
	  user_postal_postal_code character varying,
	  user_postal_comment_address character varying,
	  user_postal_lat double precision,
	  user_postal_lng double precision,
	  user_phone character varying,
	  code_reason_low character varying,
	  reason_low character varying,
	  cant_ph integer,
	  date_scanning2 date,
	  date_scanning1 date,
	  date_scanning0 date,
	  audit_date_create timestamp without time zone NOT NULL,
	  audit_user_create character varying NOT NULL,
	  audit_date_update timestamp without time zone,
	  audit_user_update character varying,
	  audit_version bigint NOT NULL,
	  audit_version_code_label character varying,
	  audit_version_label character varying,
	  fact boolean NOT NULL,
	  m2_covered_shared double precision,
	  m2_covered_expanded double precision,
	  m2_percent double precision,
	  year_building integer,
	  progress_construction_prev double precision,
	  user_iva_id character varying,
	  user_water_situation_id character varying,
	  uf_id character varying,
	  city_area_id character varying,
	  cadastre_type_id character varying,
	  cadastre_situation_id character varying,
	  inm_neighbourhood_id character varying,
	  cadastre_sub_division_type_id character varying,
	  schedule_scanning_result2_id character varying,
	  schedule_scanning_result1_id character varying,
	  schedule_scanning_result0_id character varying,
	  cadastre_activity_type_id character varying,
	  water_meter_type_id character varying,
	  cadastre_constructive_type_id character varying
 ); 
*/
-- INSERT INTO cclip.cadastre_ph_aacc (SELECT * FROM cclip.v_cadastre_ph_aacc /*WHERE v_cadastre_ph_aacc.cadastral_code::VARCHAR NOT IN ( SELECT cadastral_code::VARCHAR FROM cclip.cadastre)*/);
INSERT INTO cclip.cadastre (SELECT * FROM cclip.v_cadastre_ph_aacc WHERE v_cadastre_ph_aacc.id::VARCHAR NOT IN ( SELECT id::VARCHAR FROM cclip.cadastre));

-- ===================================================================================================

-- SELECT * FROM cclip.aacc_superficie LIMIT 100;

DROP VIEW IF EXISTS cclip.v_aacc_superficie_sin_cadastre_id CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_superficie_sin_cadastre_id AS 

	SELECT 	id,
		false::BOOLEAN AS erased,
		TRIM(aacc_superficie.c15)::INTEGER AS year_building,
		null::INTEGER AS month_building,		
		TRIM(C9)::double precision AS m2_covered,
		false::BOOLEAN AS demolition,
		null AS comment,
		null AS facade, 
		null AS roof_structure, 
		null AS homes, 
		null AS interior_walls, 
		null AS ceiling, 
		null AS kitchen, 
		null AS toilets, 
		null AS entrance_hall, 
		null AS facilities, 
		null AS carpentry, 
		null AS cover_structure, 
		null AS ornamentation, 
		null AS stained_glass_lighting, 
		null AS buffet_dining, 
		null::INTEGER AS points, 
		null::BOOLEAN AS apc, 
		null::INTEGER AS rate, 
		null AS apc_desc, 
		audit_date_create, 
		audit_user_create, 
		audit_date_create AS audit_date_update, 
		audit_user_create AS audit_user_update, 
		1 AS audit_version, 
		'20' AS audit_version_code_label, 
		'Proveniente de Base Congelada' AS audit_version_label, 
		null AS cadastre_activity_type_id, 

		null AS cadastre_id, 

		TRIM(aacc_superficie.c13)::VARCHAR AS cadastre_constructive_type_id, 
		TRIM(aacc_superficie.c14)::VARCHAR AS cadastre_destination_type_id,
				
		
		CASE	WHEN LENGTH(TRIM(aacc_superficie.c3)) = 1 THEN '00' || TRIM(aacc_superficie.c3)
			WHEN LENGTH(TRIM(aacc_superficie.c3)) = 2 THEN '0' || TRIM(aacc_superficie.c3)
			WHEN LENGTH(TRIM(aacc_superficie.c3)) = 3 THEN TRIM(aacc_superficie.c3)
			ELSE null
		END AS 	sub_cta,

		--TRIM(aacc_superficie.c3)::VARCHAR AS sub_cta, 
		TRIM(aacc_superficie.c2)::VARCHAR AS cta_cli, 
		TRIM(aacc_superficie.c1)::VARCHAR AS city_area_id, 		
		TRIM(aacc_superficie.c4)::VARCHAR AS dv

		

	FROM 	cclip.aacc_superficie 	
	WHERE	aacc_superficie.c3 IS NOT NULL
		AND aacc_superficie.c2 IS NOT NULL
		AND aacc_superficie.c1 IS NOT NULL
		AND aacc_superficie.c15 IS NOT NULL

		AND LENGTH(TRIM(aacc_superficie.c2)) = 6
		AND LENGTH(TRIM(aacc_superficie.c15)) = 4
		
		AND cclip.is_integer(TRIM(aacc_superficie.c2)) = true
		AND cclip.is_integer(TRIM(aacc_superficie.c5)) = true
		
		AND (TRIM(aacc_superficie.c1) = '322' OR TRIM(aacc_superficie.c1) = '321')
		
	ORDER BY 	aacc_superficie.c2 ASC, 
			aacc_superficie.c3 ASC;
			

-- SELECT * FROM cclip.v_aacc_superficie_sin_cadastre_id OFFSET '0' LIMIT '100';
--  SELECT COUNT(*) FROM cclip.v_aacc_superficie_sin_cadastre_id; -- 815347
--  SELECT COUNT(*) FROM cclip.aacc_superficie; -- 815347

-- ===================================================================================================

-- SELECT SUBSTRING('DDZZMMMPPPppp' FROM 11 FOR 13)

DROP VIEW IF EXISTS cclip.v_aacc_cadastre_block CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_cadastre_block AS 

	 SELECT v_aacc_superficie_sin_cadastre_id.id,
		v_aacc_superficie_sin_cadastre_id.erased,
		v_aacc_superficie_sin_cadastre_id.year_building,
		v_aacc_superficie_sin_cadastre_id.month_building,		
		v_aacc_superficie_sin_cadastre_id.m2_covered,
		v_aacc_superficie_sin_cadastre_id.demolition,
		v_aacc_superficie_sin_cadastre_id.comment,
		v_aacc_superficie_sin_cadastre_id.facade, 
		v_aacc_superficie_sin_cadastre_id.roof_structure, 
		v_aacc_superficie_sin_cadastre_id.homes, 
		v_aacc_superficie_sin_cadastre_id.interior_walls, 
		v_aacc_superficie_sin_cadastre_id.ceiling, 
		v_aacc_superficie_sin_cadastre_id.kitchen, 
		v_aacc_superficie_sin_cadastre_id.toilets, 
		v_aacc_superficie_sin_cadastre_id.entrance_hall, 
		v_aacc_superficie_sin_cadastre_id.facilities, 
		v_aacc_superficie_sin_cadastre_id.carpentry, 
		v_aacc_superficie_sin_cadastre_id.cover_structure, 
		v_aacc_superficie_sin_cadastre_id.ornamentation, 
		v_aacc_superficie_sin_cadastre_id.stained_glass_lighting, 
		v_aacc_superficie_sin_cadastre_id.buffet_dining, 
		v_aacc_superficie_sin_cadastre_id.points, 
		v_aacc_superficie_sin_cadastre_id.apc, 
		v_aacc_superficie_sin_cadastre_id.rate, 
		v_aacc_superficie_sin_cadastre_id.apc_desc, 
		v_aacc_superficie_sin_cadastre_id.audit_date_create, 
		v_aacc_superficie_sin_cadastre_id.audit_user_create, 
		v_aacc_superficie_sin_cadastre_id.audit_date_update, 
		v_aacc_superficie_sin_cadastre_id.audit_user_update, 
		v_aacc_superficie_sin_cadastre_id.audit_version, 
		v_aacc_superficie_sin_cadastre_id.audit_version_code_label, 
		v_aacc_superficie_sin_cadastre_id.audit_version_label, 
		v_aacc_superficie_sin_cadastre_id.cadastre_activity_type_id, 
		
		v_cadastre_aacc.id AS cadastre_id, 
		
		v_aacc_superficie_sin_cadastre_id.cadastre_constructive_type_id, 
		v_aacc_superficie_sin_cadastre_id.cadastre_destination_type_id
		
	 FROM 	cclip.v_aacc_superficie_sin_cadastre_id
	 JOIN	cclip.v_cadastre_aacc
		ON v_cadastre_aacc.cta_cli = v_aacc_superficie_sin_cadastre_id.cta_cli
		AND v_cadastre_aacc.city_area_id = v_aacc_superficie_sin_cadastre_id.city_area_id	
		AND SUBSTRING(v_cadastre_aacc.cadastral_code FROM 11 FOR 13)::VARCHAR = v_aacc_superficie_sin_cadastre_id.sub_cta;

-- SELECT * FROM cclip.v_aacc_cadastre_block OFFSET '0' LIMIT '100';
-- SELECT COUNT(*) FROM cclip.v_aacc_cadastre_block; -- 

-- ===================================================================================================

INSERT INTO cclip.cadastre_block (SELECT * FROM cclip.v_aacc_cadastre_block WHERE v_aacc_cadastre_block.id::VARCHAR NOT IN ( SELECT id::VARCHAR FROM cclip.cadastre_block));

-- ===================================================================================================

DROP VIEW IF EXISTS cclip.v_aacc_ph_cadastre_block CASCADE;

CREATE OR REPLACE VIEW cclip.v_aacc_ph_cadastre_block AS 

	 SELECT v_aacc_superficie_sin_cadastre_id.id,
		v_aacc_superficie_sin_cadastre_id.erased,
		v_aacc_superficie_sin_cadastre_id.year_building,
		v_aacc_superficie_sin_cadastre_id.month_building,		
		v_aacc_superficie_sin_cadastre_id.m2_covered,
		v_aacc_superficie_sin_cadastre_id.demolition,
		v_aacc_superficie_sin_cadastre_id.comment,
		v_aacc_superficie_sin_cadastre_id.facade, 
		v_aacc_superficie_sin_cadastre_id.roof_structure, 
		v_aacc_superficie_sin_cadastre_id.homes, 
		v_aacc_superficie_sin_cadastre_id.interior_walls, 
		v_aacc_superficie_sin_cadastre_id.ceiling, 
		v_aacc_superficie_sin_cadastre_id.kitchen, 
		v_aacc_superficie_sin_cadastre_id.toilets, 
		v_aacc_superficie_sin_cadastre_id.entrance_hall, 
		v_aacc_superficie_sin_cadastre_id.facilities, 
		v_aacc_superficie_sin_cadastre_id.carpentry, 
		v_aacc_superficie_sin_cadastre_id.cover_structure, 
		v_aacc_superficie_sin_cadastre_id.ornamentation, 
		v_aacc_superficie_sin_cadastre_id.stained_glass_lighting, 
		v_aacc_superficie_sin_cadastre_id.buffet_dining, 
		v_aacc_superficie_sin_cadastre_id.points, 
		v_aacc_superficie_sin_cadastre_id.apc, 
		v_aacc_superficie_sin_cadastre_id.rate, 
		v_aacc_superficie_sin_cadastre_id.apc_desc, 
		v_aacc_superficie_sin_cadastre_id.audit_date_create, 
		v_aacc_superficie_sin_cadastre_id.audit_user_create, 
		v_aacc_superficie_sin_cadastre_id.audit_date_update, 
		v_aacc_superficie_sin_cadastre_id.audit_user_update, 
		v_aacc_superficie_sin_cadastre_id.audit_version, 
		v_aacc_superficie_sin_cadastre_id.audit_version_code_label, 
		v_aacc_superficie_sin_cadastre_id.audit_version_label, 
		v_aacc_superficie_sin_cadastre_id.cadastre_activity_type_id, 
		
		v_cadastre_ph_aacc.id AS cadastre_id, 
		
		v_aacc_superficie_sin_cadastre_id.cadastre_constructive_type_id, 
		v_aacc_superficie_sin_cadastre_id.cadastre_destination_type_id
		
	 FROM 	cclip.v_aacc_superficie_sin_cadastre_id
	 JOIN	cclip.v_cadastre_ph_aacc
		ON v_cadastre_ph_aacc.cta_cli = v_aacc_superficie_sin_cadastre_id.cta_cli
		AND v_cadastre_ph_aacc.city_area_id = v_aacc_superficie_sin_cadastre_id.city_area_id	
		AND SUBSTRING(v_cadastre_ph_aacc.cadastral_code FROM 11 FOR 13)::VARCHAR = v_aacc_superficie_sin_cadastre_id.sub_cta;

-- SELECT * FROM cclip.v_aacc_ph_cadastre_block OFFSET '0' LIMIT '100';
--  SELECT COUNT(*) FROM cclip.v_aacc_ph_cadastre_block; -- 13051

-- ===================================================================================================

INSERT INTO cclip.cadastre_block (SELECT * FROM cclip.v_aacc_ph_cadastre_block WHERE v_aacc_ph_cadastre_block.id::VARCHAR NOT IN ( SELECT id::VARCHAR FROM cclip.cadastre_block));

-- ===================================================================================================

SELECT 'FIN', now();