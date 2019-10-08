
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



