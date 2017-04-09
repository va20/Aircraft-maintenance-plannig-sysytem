package com.glproject.groupe3.DAOImpl;

import com.glproject.groupe3.DAO.DAOImpl;
import com.glproject.groupe3.DAO.MRODAO;
import com.glproject.groupe3.businessobjects.MRO;

public class MRODAOImpl extends DAOImpl<MRO> implements MRODAO {

	public MRODAOImpl(Class<MRO> typeT) {
		super(typeT);
	}
}
