
--Para conocer el ID del proveedor a través del nombre. 
CREATE OR REPLACE PROCEDURE idTipoProveedor(tipoProveedor IN VARCHAR2, idTipoProv out NUMBER) 
AS
BEGIN
    SELECT CODTIPOPROV INTO idTipoProv FROM TIPOPROVEEDORES WHERE TIPOPROV=tipoProveedor;
END;
/

--Para el siguiente id del proveedor 
CREATE OR REPLACE PROCEDURE nextCodProv(idProveedor out NUMBER) 
AS
BEGIN
    SELECT CodigoProveedor.NEXTVAL INTO idproveedor FROM DUAL;
END;
/

--Para encontrar los telefonos del proveedor
CREATE OR REPLACE PROCEDURE telProveedor(idProv IN INT, tipoTel IN VARCHAR2, telefonoP out VARCHAR2) 
AS
BEGIN
    SELECT telefono INTO telefonoP FROM telefonosproveedor WHERE codigoProveedor = idProv and tipo = tipoTel;
END;
/


--Para el editado de telefonos proveedor.
CREATE OR REPLACE PROCEDURE telEditarProveedor(idProv IN INT, telefonoT IN VARCHAR2, telefonoC IN VARCHAR2) 
AS
BEGIN
    UPDATE TELEFONOSPROVEEDOR SET TELEFONO = telefonoT WHERE CODIGOPROVEEDOR = idProv AND TIPO = 'T';
    UPDATE TELEFONOSPROVEEDOR SET TELEFONO = telefonoC WHERE CODIGOPROVEEDOR = idProv AND TIPO = 'C';
END;
/


--Para encontrar el id del telefono a eliminar 
CREATE OR REPLACE PROCEDURE findIdTelProveedor(idProv IN INT, tipoTel IN VARCHAR2, idTel OUT INT) 
AS
BEGIN
    SELECT idtelefono INTO idTel FROM telefonosproveedor WHERE codigoProveedor = idProv and tipo = tipoTel;
END;
/


--Para averiguar el id de la bodega. 
CREATE OR REPLACE PROCEDURE findIdBodega(bodega IN VARCHAR2, idBod OUT INT) 
AS
BEGIN
    SELECT CodigoBodega INTO idBod FROM Bodegas WHERE NombreBodega = bodega;
END;
/


--Para averiguar el id del proveedor, para formulario entrada...
CREATE OR REPLACE PROCEDURE findIdProveedor(proveedor IN VARCHAR2, idProveedor OUT INT) 
AS
BEGIN
    SELECT CodigoProveedor INTO idProveedor FROM Proveedores WHERE Nombres = proveedor;
END;
/


--Para averiguar el id del proveedor, para formulario entrada...
CREATE OR REPLACE PROCEDURE findIdArticulo(articulo IN VARCHAR2, idArticulo OUT INT) 
AS
BEGIN
    SELECT CodigoArticulo INTO idArticulo FROM Articulos WHERE NombreArticulo = articulo;
END;
/









--MARIO

CREATE OR REPLACE PROCEDURE nextCodBodegas(idBodegas out NUMBER) 
AS
BEGIN
    SELECT CodigoBodega.NEXTVAL INTO idBodegas FROM DUAL;
END;
/

CREATE OR REPLACE PROCEDURE nextCodArticulo(idArticulo out NUMBER) 
AS
BEGIN
    SELECT articulo.NEXTVAL INTO idArticulo FROM DUAL;
END;
/

CREATE OR REPLACE PROCEDURE nextCodUsuario(idUsuario out NUMBER) 
AS
BEGIN
    SELECT Codigousuario.NEXTVAL INTO idUsuario FROM DUAL;
END;
/


CREATE OR REPLACE PROCEDURE idTipoUsuario(tipoUsu IN VARCHAR2, idTipousuario out NUMBER) 
AS
BEGIN
    SELECT CodTipoUsuario INTO idTipousuario  FROM TipoUsuarios WHERE TipoUsuario=tipoUsu;
END;
/


CREATE OR REPLACE PROCEDURE idGrupos(grupos IN VARCHAR2, idgrupos out NUMBER) 
AS
BEGIN
    SELECT CodigoGrupo INTO idgrupos  FROM Grupos WHERE NombreGrupo=grupos;
END;
/

CREATE OR REPLACE PROCEDURE idLineas(lineas IN VARCHAR2, idlineas out NUMBER) 
AS
BEGIN
    SELECT CodigoLinea INTO idlineas  FROM Lineas WHERE NombreLineas =lineas;
END;
/

CREATE OR REPLACE PROCEDURE idBodegas(bodega IN VARCHAR2, idbodegas out NUMBER) 
AS
BEGIN
    SELECT CodigoBodega INTO idbodegas  FROM Bodegas WHERE NombreBodega=bodega;
END;
/


--Para encontrar los id de grupo
CREATE OR REPLACE PROCEDURE findIdGrupo(Grupo IN VARCHAR2, idGrupo out NUMBER) 
AS
BEGIN
    SELECT CodigoGrupo INTO idGrupo FROM Grupos WHERE NombreGrupo=Grupo;
END;
/



CREATE OR REPLACE PROCEDURE EditarGrupo(idGrupo IN NUMBER, Grupo IN VARCHAR2, Comi IN VARCHAR2)
AS
BEGIN
    UPDATE Grupos SET NombreGrupo = Grupo, Comision = Comi WHERE CodigoGrupo = idGrupo; 
END;
/



CREATE OR REPLACE PROCEDURE EditarSubGrupo(IdSubGrupo IN NUMBER, Grupo IN NUMBER, SubGrupo IN VARCHAR2)
AS
BEGIN
    UPDATE Lineas SET CodigoGrupo = Grupo, NombreLineas = SubGrupo WHERE CodigoLinea = IdSubGrupo; 
END;
/






