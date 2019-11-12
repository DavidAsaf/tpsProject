--Para entrada de factura
CREATE OR REPLACE PROCEDURE EntraFactura (
	p_Codigo NUMBER,
        p_NumFactura VARCHAR2, 
	p_cont_O_credit NUMBER, 
	p_CantidadDiasCredit NUMBER, 
	p_Estado NUMBER, 
	p_CodigoBodega NUMBER,
	p_CodigoArticulo NUMBER,
	p_Fecha DATE, 
	p_Factura VARCHAR2, 
	p_CCF VARCHAR2, 
	p_Ticket VARCHAR2, 
	p_AjusteInventario VARCHAR2,
	p_CodigoProveedor NUMBER, 
	p_eUnidad NUMBER, 
	p_ePrecio NUMBER, 
	p_Detalle VARCHAR2)
AS
		v_eTotal NUMBER; 
		v_sUnidad NUMBER; 
		v_sUnit NUMBER; 
		v_sUnidadAnterior NUMBER;
		v_sCostoPromedio NUMBER; 
		v_sTotal NUMBER;
		v_sTotalAnterior NUMBER;
BEGIN

		v_eTotal := (p_eUnidad * p_ePrecio);
		
		 SELECT SSUnidad INTO v_sUnidadAnterior FROM (SELECT sUnidad AS SSUnidad FROM HistorialFacturas WHERE CodigoArticulo = p_CodigoArticulo ORDER BY Codigo DESC) 
         WHERE ROWNUM <= 1;

		IF (v_sUnidadAnterior IS NULL)
			THEN
				v_sUnidadAnterior := 0;
			END IF;

		v_sUnidad := (v_sUnidadAnterior + p_eUnidad);
		
		 SELECT SSTotal INTO v_sTotalAnterior FROM (SELECT sTotal AS SSTotal FROM HistorialFacturas 
								WHERE	CodigoArticulo = p_CodigoArticulo ORDER BY Codigo DESC) 
         WHERE ROWNUM <= 1;

		IF (v_sTotalAnterior IS NULL)
			THEN
				v_sTotalAnterior := 0;
        END IF;

		v_sCostoPromedio := ((v_eTotal + v_sTotalAnterior) / v_sUnidad);
	
		v_sTotal := (v_sUnidad * v_sCostoPromedio);

		INSERT INTO HistorialFacturas 
			(Codigo,NumFactura, cont_O_credit, CantidadDiasCredit, Estado, CodigoBodega, 
			CodigoArticulo, Fecha, Factura, CCF, Ticket, AjusteInventario, CodigoProveedor, eUnidad, ePrecio, eTotal, 
			sUnidad, sCostoPromedio, sTotal, Detalle, oUnidad, oPrecio, oTotal, oCliente) 
	
		VALUES 
			(p_Codigo, p_NumFactura, p_cont_O_credit, p_CantidadDiasCredit, p_Estado, p_CodigoBodega, p_CodigoArticulo, p_Fecha, p_Factura,
			p_CCF, p_Ticket, p_AjusteInventario, p_CodigoProveedor, p_eUnidad, p_ePrecio, v_eTotal, v_sUnidad, v_sCostoPromedio, 
			v_sTotal, p_Detalle, 0, 0, 0, ' --- ');

		UPDATE Articulos SET Existencia = Existencia + p_eUnidad WHERE CodigoArticulo = p_CodigoArticulo;
END;
/



--Salida factura
CREATE OR REPLACE PROCEDURE SalidaFactura (
    p_Codigo NUMBER,
    p_NumFactura VARCHAR2,
	p_Fecha DATE,
	p_cont_O_credit NUMBER,
	p_CantidadDiasCredit NUMBER,
	p_oCliente VARCHAR2,
	p_CodigoArticulo NUMBER,	
	p_oUnidad NUMBER,
	p_CodigoBodega NUMBER)
AS
			v_oPrecio NUMBER;
			v_oTotal NUMBER;
			v_sUnidad NUMBER; 
			v_sTotal NUMBER;
			v_CCF VARCHAR2(50);
			v_Estado NUMBER;
			v_sCostoPromedio NUMBER;
	BEGIN

		 SELECT ssCostoPromedio INTO v_sCostoPromedio FROM (SELECT sCostoPromedio AS ssCostoPromedio FROM HistorialFacturas 
								WHERE CodigoArticulo = p_CodigoArticulo ORDER BY Codigo DESC) 
        WHERE ROWNUM <= 1;

		IF (v_sCostoPromedio IS NULL)
			THEN
				v_sCostoPromedio := 0;
			END IF;
		
		v_Estado := 1;

		v_oPrecio := v_sCostoPromedio;

		v_oTotal := (v_oPrecio * p_oUnidad);

		SELECT ssUnidad INTO v_sUnidad FROM (SELECT sUnidad AS ssUnidad FROM HistorialFacturas WHERE CodigoArticulo = p_CodigoArticulo 
						ORDER BY Codigo DESC) 
        WHERE ROWNUM <= 1;
        
        v_sUnidad := v_sUnidad - p_oUnidad;
		
		v_sTotal := (v_sUnidad * v_sCostoPromedio);

		v_CCF := p_NumFactura;

		INSERT INTO HistorialFacturas 
			(Codigo, NumFactura, cont_O_credit, CantidadDiasCredit, Estado,CodigoBodega, CodigoArticulo,Fecha, CCF, 
			oUnidad, oPrecio, oTotal, oCliente, sUnidad, sCostoPromedio, sTotal)
		VALUES 
			(p_Codigo, p_NumFactura, p_cont_O_credit, p_CantidadDiasCredit, v_Estado, p_CodigoBodega, p_CodigoArticulo, p_Fecha,
			v_CCF, p_oUnidad, v_oPrecio, v_oTotal, p_oCliente, v_sUnidad, v_sCostoPromedio, v_sTotal);

		UPDATE Articulos SET Existencia = Existencia - p_oUnidad WHERE CodigoArticulo = p_CodigoArticulo;
	END;
/


--Para ajuste individual
CREATE OR REPLACE PROCEDURE AjusteInventarioIndividual (
	p_Fecha DATE,
	p_CodigoArticulo NUMBER,	
	p_oUnidad NUMBER,
	p_CodigoBodega NUMBER)
AS
			v_oPrecio NUMBER;
			v_oTotal NUMBER;
			v_sUnidad NUMBER; 
			v_sTotal NUMBER;
			v_CCF VARCHAR2(50);
			v_Estado NUMBER;
			v_sCostoPromedio NUMBER;
	BEGIN

		 SELECT ssCostoPromedio INTO v_sCostoPromedio FROM (SELECT sCostoPromedio AS ssCostoPromedio FROM HistorialFacturas WHERE CodigoArticulo = p_CodigoArticulo ORDER BY Codigo DESC) 
         WHERE ROWNUM <= 1;

		 SELECT ooPrecio INTO v_oPrecio  FROM (SELECT oPrecio AS ooPrecio FROM HistorialFacturas WHERE CodigoArticulo = p_CodigoArticulo ORDER BY Codigo DESC) 
         WHERE ROWNUM <= 1;

		v_oTotal := (v_oPrecio * p_oUnidad);

		SELECT ssUnidad INTO v_sUnidad FROM (SELECT sUnidad AS ssUnidad FROM HistorialFacturas WHERE CodigoArticulo = p_CodigoArticulo ORDER BY Codigo DESC) 
        WHERE ROWNUM <= 1;
        
        v_sUnidad := v_sUnidad - p_oUnidad;
		
		v_sTotal := (v_sUnidad * v_sCostoPromedio);

		INSERT INTO HistorialFacturas 
			(Codigo, NumFactura, cont_O_credit, CantidadDiasCredit, Estado,CodigoBodega, CodigoArticulo,Fecha, CCF, 
			oUnidad, oPrecio, oTotal, oCliente, sUnidad, sCostoPromedio, sTotal, AjusteInventario, Factura, Ticket)
		VALUES 
			(HistorialFacturaSeq.nextval,'---', 0, 0, 1, p_CodigoBodega, p_CodigoArticulo, p_Fecha,
			'---', p_oUnidad, v_oPrecio, v_oTotal, '---', v_sUnidad, v_sCostoPromedio, v_sTotal, 'Ajuste', '---', '---');

		UPDATE Articulos SET Existencia = Existencia - p_oUnidad WHERE CodigoArticulo = p_CodigoArticulo;
	END;
/


--Para Ajuste de inventario anual
CREATE OR REPLACE PROCEDURE AjusteInventario (
    p_Fecha DATE,
    p_CodigoArticulo NUMBER)
AS
		v_sUnidad NUMBER; 
		v_sCostoPromedio NUMBER; 
		v_sTotal NUMBER;
BEGIN

		SELECT ssUnidad, ssCostoPromedio, ssTotal INTO v_sUnidad, v_sCostoPromedio, v_sTotal 
        FROM (SELECT sUnidad AS ssUnidad, sCostoPromedio AS ssCostoPromedio, sTotal AS ssTotal 
              FROM HistorialFacturas
              WHERE CodigoArticulo = p_CodigoArticulo
              ORDER BY Codigo DESC) 
        WHERE ROWNUM <= 1;

		INSERT INTO HistorialFacturas (Codigo, NumFactura, Cont_o_Credit, CantidadDiasCredit, Estado, CodigoBodega, 
                    CodigoArticulo, Fecha, Factura, CCF, Ticket, AjusteInventario, CodigoProveedor, eUnidad, ePrecio, eTotal, 
                    oUnidad, oPrecio, oTotal, oCliente, sUnidad, sCostoPromedio, sTotal, Detalle)
		VALUES
			(HistorialFacturaSeq.nextval, '',0,0,0,NULL,p_CodigoArticulo, p_Fecha,NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL, NULL, 
			NULL,NULL,v_sUnidad,v_sCostoPromedio,v_sTotal,NULL);
END;
/




CREATE OR REPLACE PROCEDURE insertarDetalleFact(p_codigo IN NUMBER, p_nit IN VARCHAR2, p_nrc IN VARCHAR2, p_giro IN VARCHAR2, p_dui IN VARCHAR2, p_direccion IN VARCHAR2,
                                                    p_ivacreditofiscal IN VARCHAR2)
AS
BEGIN
    INSERT INTO DetallesFactura (Codigo, Nit, NRC, Giro, Dui, Direccion, IvaCreditoFiscal) VALUES (p_codigo, p_nit, p_nrc, p_giro, p_dui, p_direccion, p_ivacreditofiscal);
END;
/


CREATE OR REPLACE PROCEDURE precioArticulo(idArticulo IN NUMBER, idBodega IN NUMBER, valor out NUMBER) 
AS
BEGIN
    SELECT Precio INTO valor FROM (SELECT (a.Utilidad + h.sCostoPromedio) AS Precio FROM Articulos a
    INNER JOIN HistorialFacturas h ON a.CodigoArticulo = h.CodigoArticulo
    WHERE a.CodigoBodega = idBodega AND a.CodigoArticulo = idArticulo
    ORDER BY Precio desc)
    WHERE rownum <=1;
END;
/












