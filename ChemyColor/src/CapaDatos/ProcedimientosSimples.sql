
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


