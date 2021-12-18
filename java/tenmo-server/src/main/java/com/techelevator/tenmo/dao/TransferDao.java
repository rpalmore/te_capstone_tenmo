package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.TransferDetail;

import java.util.List;

public interface TransferDao {

    //boolean createTransfer(TransferDTO transferDTO);

    TransferDTO createTransfer(TransferDTO transferDTO);

//Return all transfer history
    List<Transfer> getTransferHistory(long id);


    //Return transfer detail
    TransferDetail getTransferDetail(int id);
}

