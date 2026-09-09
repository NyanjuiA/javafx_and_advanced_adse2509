package com.adse2509.sess02_generics;

/**
 * A generic class that stores and manages a value of type {@code T}.
 * 
 * @param <T> the type of the value held by this class
 * @author Nyanjui.
 */
public class GenericClass<T> 
{
	// Class field
	private T varT;

	public GenericClass(T varT) 
	{
		this.varT = varT;
	}

	public T getVarT() {
		return varT;
	}

	public void setVarT(T varT) {
		this.varT = varT;
	}
	
	/** 
	 * Returns the run time type name of the stored value.
	 * 
	 * @return the fully qualified type name of {@code T}
	 * @throws NullPointerException when the stored value is {@code null}
	 */
	public String showType() throws NullPointerException
	{
		return this.varT.getClass().getTypeName();
	}

}
