package it.academy.services;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.PermissionDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

import java.math.BigInteger;

public interface UserService {

    RespDTO<BigInteger> getNumberOfEntries();

    RespListDTO<PermissionDTOReq> findPermissions();

    RespListDTO<PermissionDTOReq> findPermissions(int pageNumber, int listSize);

    RespListDTO<PermissionDTOReq> findPermissions(ParametersForSearchDTO parameters);


}
