package com.adse2509.classes;

public class SimpleCircle 
{
	protected float radius;

	
	
	public SimpleCircle() { this.radius = 0.0f;	}


	public SimpleCircle(float radius) 
	{
		this.radius = radius;
	}


	/**
	 * @return the radius
	 */
	public float getRadius() {
		return radius;
	}


	/**
	 * @param radius the radius to set
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	public float calcArea()
	{ 
		return (float) (Math.PI * Math.pow(this.radius, 2)); 
	}
	
	public float calcCircumference()
	{ 
		return (float) (Math.PI * (this.radius * 2)); 
	}


	@Override
	public String toString() 
	{
		return "SimpleCircle [radius = " + radius + "]";
	}
	
	
}
