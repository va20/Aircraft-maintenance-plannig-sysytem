package com.glproject.groupe3.DAO;

public abstract class AbstractDAOFactory {

	public abstract DAO getFlightDAO();

	public abstract DAO getPlaneDAO();

	public abstract DAO getStaffDAO();

	public abstract DAO getGenericTaskDAO();

	public abstract DAO getTaskDAO();

	public abstract DAO getMRODAO();

	public static AbstractDAOFactory getFactory(Factory type) {
		if (type.equals(Factory.ES_DAO_FACTORY))
			return new DAOFactory();

		return null;
	}
}
