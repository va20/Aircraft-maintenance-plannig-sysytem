package com.example.glproject.DAO;

import com.example.glproject.businessobjects.MRO;

/**
 * Created by info on 20/02/17.
 */
public interface MRODao {
	boolean add(MRO mro);

	boolean delete(int id);

	MRO getByid(int id);
}
