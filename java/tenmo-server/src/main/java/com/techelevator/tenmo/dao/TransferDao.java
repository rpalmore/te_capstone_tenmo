package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.TransferDetail;

import java.util.List;

public interface TransferDao {

    TransferDTO createTransfer(TransferDTO transferDTO);

    List<Transfer> getTransferHistory(long id);

    TransferDetail getTransferDetail(int id);
}

