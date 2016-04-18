package io.github.ramanujansghost.s87powers;

public abstract class Power
{
	public String getName()
	{
	    throw new IllegalArgumentException ("Child class did not declare an overridden getName() method using a static getPowerName() method.  This must be done so the power's name can be found easily.");
	}
	public String getDescription()
	{
		throw new IllegalArgumentException ("Child class did not declare an overridden getName() method using a static getPowerName() method.  This must be done so the power's description can be found easily.");
	}
}
