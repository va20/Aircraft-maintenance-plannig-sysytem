package com.example.glproject.DAO;

import com.example.glproject.businessobjects.Mro;

/**
 * Created by info on 20/02/17.
 */
public interface MroDao {
	boolean add(Mro mro);

	boolean delete(int id);

	Mro getByid(int id);
}
