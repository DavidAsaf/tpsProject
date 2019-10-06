
--Para conocer el ID del proveedor a través del nombre. 
CREATE OR REPLACE PROCEDURE idTipoProveedor(tipoProveedor IN VARCHAR2, idTipoProv out NUMBER) 
AS
BEGIN
    SELECT CODTIPOPROV INTO idTipoProv FROM TIPOPROVEEDORES WHERE TIPOPROV=tipoProveedor;
END;
/


