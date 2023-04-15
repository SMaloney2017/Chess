package com.chess.engine.util;

public enum Color
{
	DARK
		{
			@Override
			public int getMagnitude()
			{
				return 1;
			}
		},
	LIGHT
		{
			@Override
			public int getMagnitude()
			{
				return -1;
			}
		};

	public abstract int getMagnitude();
}
