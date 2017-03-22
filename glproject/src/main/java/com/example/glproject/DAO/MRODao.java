package com.example.glproject.DAO;

import com.example.glproject.businessobjects.MRO;

/**
 * Created by info on 20/02/17.
 */
public interface MRODao {
	void add(MRO mro);

	void delete(long id);

	void update(MRO mro);

	MRO getByid(long id);
}
