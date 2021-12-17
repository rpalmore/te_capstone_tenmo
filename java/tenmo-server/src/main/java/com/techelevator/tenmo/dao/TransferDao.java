package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;

import java.util.List;

public interface TransferDao {

    //boolean createTransfer(TransferDTO transferDTO);

    TransferDTO createTransfer(TransferDTO transferDTO);
//Return all transfer history
    List<Transfer> getTransferHistory();

}

