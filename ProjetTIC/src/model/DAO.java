package model;

import java.sql.Connection;

public abstract class DAO<T> {
	
	public Connection connect = Connect.getInstance();
	
	public abstract T create(T obj);
	public abstract boolean delete(T obj);
	public abstract T update(T obj);
	
	public abstract T find(int id);
}
